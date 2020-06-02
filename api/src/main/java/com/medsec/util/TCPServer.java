package com.medsec.util;

import com.medsec.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.net.ssl.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.util.HashMap;

/**
 * TCPServer handles JSON GENIE Data sent from the script
 */
public class TCPServer implements Runnable{

//    private static final int PORT = 11111;

    private static final Logger log = LogManager.getLogger();
//    private static final Path PATH = Paths.get
//            ("src/main/resources/").toAbsolutePath();
//    private static final String GENIE_DB_NAME = "TestData/appointment.json";
    private static final String CLIENT_KEY_STORE_PASSWORD = "client";
    private static final String CLIENT_TRUST_KEY_STORE_PASSWORD = "client";
    private static final String CLIENT_KEY_PATH = "/client_ks.jks";
    private static final String TRUST_SERVER_KEY_PATH = "/serverTrust_ks.jks";

    public static String GENIE_INSTALL_PATH = "";

    SSLServerSocket serverSocket;

    public TCPServer(SSLServerSocket s){
            this.serverSocket = s;
    }

    public void run()
    {
        System.out.println("Threaded Server Running");
//        ServerSocket serverSocket = new ServerSocket(PORT);
        while(true)
        {
            try {
            Socket sock = serverSocket.accept();
//            com.medsec.util.TCPServer server = new com.medsec.util.TCPServer(sock);
            TCPServerProcess s = new TCPServerProcess(sock);
            // Multi-threading, possibly not needed but better to have
            Thread serverThread = new Thread(s);
            serverThread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
//            int nbRunning = 0;
//            for (Thread t : Thread.getAllStackTraces().keySet()) {
//                if (t.getState()==Thread.State.RUNNABLE) nbRunning++;
//            }
//            System.out.println("Total: " + nbRunning);

//            serverThread.sleep(3000);
//            serverThread.interrupt();
//            serverThread.sleep(200);
//            serverThread.interrupt();

        }
    }

    public static void initialSSLServerSocket(int PORT) {
        try {
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(TCPServer.class.getResourceAsStream(CLIENT_KEY_PATH), CLIENT_KEY_STORE_PASSWORD.toCharArray());
            KeyStore tks = KeyStore.getInstance("JKS");
            tks.load(TCPServer.class.getResourceAsStream(TRUST_SERVER_KEY_PATH), CLIENT_TRUST_KEY_STORE_PASSWORD.toCharArray());


            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, CLIENT_KEY_STORE_PASSWORD.toCharArray());

            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(tks);

            SSLContext context = SSLContext.getInstance("SSL");

            context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

            SSLServerSocketFactory ssf = context.getServerSocketFactory();
//            SSLServerSocket serverSocket = initialSSLServerSocket(PORT);
            SSLServerSocket s = (SSLServerSocket) ssf.createServerSocket(PORT);
            TCPServer socketServer = new TCPServer(s);
            Thread serverThread = new Thread(socketServer);
            serverThread.start();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}

class TCPServerProcess implements Runnable{
    private static final Logger log = LogManager.getLogger();
    private volatile boolean flag = false;
    Socket connectionSocket;
    DataManager dataManager = DataManager.getInstance();
    ServletContext sc = null;


    public TCPServerProcess(Socket s){
        try{
            System.out.println("Client Connected");
            connectionSocket = s;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        try{
            // BufferedReader can handle arbitrary length string
            String msg;
            InputStream in = connectionSocket.getInputStream();

            DataInputStream dataInputStream = new DataInputStream(in);
            try {
//                dataManager.ConnectionDB();
//                while (true){
//                    if(Thread.currentThread().isInterrupted()){
//                        System.out.println("Yes,I am interruted,but I am still running");
//                        return;
//
//                    }else{
//                        System.out.println("not yet interrupted");
//                    }
//                }
//                System.out.println("Previous: "+flag);
                while(!flag && (msg = dataInputStream.readUTF()) != null) {
//                    System.out.println(msg);
//                    msg = dataInputStream.readUTF();
//                        String data = SymmetricEncrypt.getInstance().decrypt(msg);
                    flag = processData(msg);
//                    System.out.println(flag);
//                    if(dataInputStream.available() > 0){
//
//                    }
                }
                Thread.currentThread().interrupt();
                if (Thread.currentThread().isInterrupted())
                {
                    log.info(Thread.currentThread().getName() +" is closed");
                }
//                System.out.println(Thread.currentThread().getName());
//                connectionSocket.close();
//                System.out.println("Client Disconnected");
            } catch (SocketException e) {
                System.out.println("closed connection");
            }
            connectionSocket.close();
        }catch(Exception e){
            e.printStackTrace();}
    }

    public boolean processData(String data) throws IOException {
        JSONObject json = JSONReader.getInstance().parse(data);
        String command = (String) json.get("command");

        if (command.equals(QueryCommand.AUTHENTICATION.toString())){
            String secret = (String) json.get("secret");
            return authenticationHandler(secret);
        }
        else if (command.equals(QueryCommand.PATIENT.toString()))
            return userHandler((JSONObject) json.get("doc"));
        else if (command.equals(QueryCommand.APPOINTMENT.toString()))
            return apptHandler((JSONObject) json.get("doc"));
        else if (command.equals(QueryCommand.DOCTOR.toString()))
            return doctorHandler((JSONObject) json.get("doc"));
        else if (command.equals(QueryCommand.HOSPITAL.toString()))
            return hospitalHandler((JSONObject) json.get("doc"));
        else if (command.equals(QueryCommand.PATHOLOGY.toString()))
            return pathologyHandler((JSONObject) json.get("doc"));
        else if (command.equals(QueryCommand.RADIOLOGY.toString()))
            return radiologyHandler((JSONObject) json.get("doc"));
        else if (command.equals(QueryCommand.RESOURCE.toString()))
            return resourceHandler((JSONObject) json.get("doc"));
        else if (command.equals(QueryCommand.FILE.toString()))
            return fileHandler((JSONObject) json.get("doc"));
        else if (command.equals(QueryCommand.DISCONNECTION.toString()))
            return true;
        else
            return true;
    }

    public boolean authenticationHandler(String secretAuthenticate) {
        try {
            JSONParser parser = new JSONParser();
//            Object obj = parser.parse(new FileReader("target/classes/TestData/authentication_server.json"));
//            System.out.println(TCPServer.class.getResource("/TestData/authentication_server.json").getPath());
            Object obj = parser.parse(new FileReader(TCPServer.class.getResource("/TestData/authentication_server.json").getPath()));
            System.out.println(TCPServer.class.getResource("/TestData/authentication_server.json").getPath());
            JSONObject authentication = (JSONObject) obj;
            String secret = (String) authentication.get("secret");
            if (secret.equals(secretAuthenticate)) {
                log.info("Authentication success");
                return false;
            } else {
                log.info("Authentication failed");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    /** process user data, insert new user or update existed user */
    public boolean userHandler(JSONObject user) {
//        System.out.println(patient.toJSONString());
//        dataManager.processPatient(patient);
//        return false;
        Database db = new Database();
        String id = (String) user.get("PatientId");
        if (!isPatientExist(id)) {
            log.info("insert new patient");
            User patient = dataManager.processPatient(user);
            db.insertUser(patient);
        } else {
            log.info("update existed patient");
            User patient = dataManager.processPatient(user);
            db.updateUser(patient);
        }
        return false;
    }

    /** process appointment data, insert new appointment or update existed appointment */
    public boolean apptHandler(JSONObject appt) {
//        System.out.println(appointment.toJSONString());
//        dataManager.processAppointment(appointment);
//        return false;
        Database db = new Database();
        String id = (String) appt.get("Id");
        if (!isApptExist(id)) {
            log.info("insert new appointment");
            Appointment apptointment = dataManager.processAppt(appt);
            db.insertAppointment(apptointment);
            try {
                PushNotification pn = new PushNotification();
                HashMap<String, String> pnResult = pn.sendNotification(apptointment);
                log.info("Push notification for new appointment");
                for (String token: pnResult.keySet()) {
                    log.info("FCMToken: " + token + " Push Notification Request:" + pnResult.get(token));
                }
            } catch (IOException e) {
                log.error("Push notification error");
            }
        } else {
            log.info("update exist appointment");
            Appointment apptointment = dataManager.processAppt(appt);
            db.updateAppointment(apptointment);
        }
        return false;
    }

    public boolean doctorHandler(JSONObject dctor) {
//        System.out.println(doctor.toJSONString());
//        dataManager.processDoctor(doctor);
//        return false;
        Database db = new Database();
        String id = (String) dctor.get("id");
        if (!isDoctorExist(id)) {
            log.info("insert new doctor");
            Doctor doctor = dataManager.processDoctor(dctor);
            db.addDoctor(doctor);
        } else {
            log.info("update existed doctor");
            Doctor doctor = dataManager.processDoctor(dctor);
            db.updateDoctor(doctor);
        }
        return false;
    }

    public boolean hospitalHandler(JSONObject hspital) {
//        System.out.println(hospital.toJSONString());
//        dataManager.processHospital(hospital);
//        return false;
        Database db = new Database();
        String id = (String) hspital.get("id");
        if (!isHospitalExist(id)) {
            log.info("insert new hospital");
            Hospital hospital = dataManager.processHospital(hspital);
            db.addHospital(hospital);
        } else {
            log.info("update existed hospital");
            Hospital hospital = dataManager.processHospital(hspital);
            db.updateHospital(hospital);
        }
        return false;
    }

    public boolean pathologyHandler(JSONObject pthology) {
//        System.out.println(pathology.toJSONString());
//        dataManager.processPathology(pathology);
//        return false;
        Database db = new Database();
        String id = (String) pthology.get("id");
        if (!isPathologyExist(id)) {
            log.info("insert new pathology");
            Pathology pathology = dataManager.processPathology(pthology);
            db.addPathology(pathology);
        } else {
            log.info("update existed pathology");
            Pathology pathology = dataManager.processPathology(pthology);
            db.updatePathology(pathology);
        }
        return false;
    }

    public boolean radiologyHandler(JSONObject rdiology) {
//        System.out.println(radiology.toJSONString());
//        dataManager.processRadiology(radiology);
//        return false;
        Database db = new Database();
        String id = (String) rdiology.get("id");
        if (!isRadiologyExist(id)) {
            log.info("insert new radiology");
            Radiology radiology = dataManager.processRadiology(rdiology);
            db.addRadiology(radiology);
        } else {
            log.info("update existed radiology");
            Radiology radiology = dataManager.processRadiology(rdiology);
            db.updateRadiology(radiology);
        }
        return false;
    }

    public boolean resourceHandler(JSONObject rsource) {
//        System.out.println(resource.toJSONString());
//        dataManager.processResource(resource);
//        return false;
        Database db = new Database();
        String id = (String) rsource.get("id");
        if (!isResourceExist(id)) {
            log.info("insert new resource");
            Resource resource = dataManager.processResource(rsource);
            db.insertResource(resource);
        } else {
            log.info("update existed resource");
            Resource resource = dataManager.processResource(rsource);
            db.updateResource(resource);
        }
        return false;
    }

    public boolean fileHandler(JSONObject file) throws IOException {
        int bytesRead = 0;
        int current = 0;
        InputStream in = null;
//        String path = System.getProperty("user.dir");

        String resoucePath=TCPServer.class.getResource("/").getPath();
        String webappsDir=(new File(resoucePath,"../../")).getCanonicalPath();
//        String path = TCPServer.class.getResource("\\").getPath();
        String fileName = (String)file.get("FileName");
        String apptid = fileName.substring(fileName.lastIndexOf("-") + 1,
                fileName.lastIndexOf(".")).trim();
//        String eachFilePath = "\\result\\" + apptid + "\\" + fileName;
        String eachFilePath = "/result/" + apptid + "/" + fileName;
        String filePath = webappsDir + eachFilePath;
        System.out.println(eachFilePath);
        System.out.println(filePath);

        try {
            in = connectionSocket.getInputStream();
            File newFile = new File(filePath);
            if (!newFile.exists()){
                newFile.getParentFile().getParentFile().mkdir();
                newFile.getParentFile().mkdir();
                newFile.createNewFile();
            }
            DataInputStream clientData = new DataInputStream(in);
//            String fileName = (String)file.get("FileName");
            OutputStream output = new FileOutputStream(filePath);

            long size = (long)file.get("FileSize");
            byte[] buffer = new byte[1024];


            while (size > 0 && (bytesRead = clientData.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1)
            {

                output.write(buffer, 0, bytesRead);
                size -= bytesRead;
            }
            output.flush();
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
//        dataManager.processFile(file, eachFilePath);
        String id = apptid;
        com.medsec.entity.File appointmentFile = new com.medsec.entity.File().id(id)
                .title(fileName).link(eachFilePath).apptid(apptid);
        Database db = new Database();
        if (!isFileExist(apptid)){
            log.info("insert new File");
            db.insertFile(appointmentFile);
        }

        return false;

    }

    /** check if the patient is already in the database */
    public boolean isPatientExist(String id){
        Database db = new Database();
        User patient = db.getUserById(id);
        return patient != null;
    }

    /** check if the appointment is already in the database */
    public boolean isApptExist(String id){
        Database db = new Database();
        Appointment appt = db.getAppointment(id);
        return appt != null;
    }

    public boolean isDoctorExist(String id){
        Database db = new Database();
        Doctor dctor = db.selectOneDoctor(id);
        return dctor != null;
    }

    public boolean isHospitalExist(String id){
        Database db = new Database();
        Hospital hspital = db.selectOneHospital(id);
        return hspital != null;
    }

    public boolean isPathologyExist(String id){
        Database db = new Database();
        Pathology pthology = db.selectOnePathology(id);
        return pthology != null;
    }

    public boolean isRadiologyExist(String id){
        Database db = new Database();
        Radiology rology = db.selectOneRadiology(id);
        return rology != null;
    }

    public boolean isResourceExist(String id){
        Database db = new Database();
        Resource rsource = db.getResource(id);
        return rsource != null;
    }

    public boolean isFileExist(String id){
        Database db = new Database();
        com.medsec.entity.File file = db.selectFileById(id);
        return file != null;
    }
}