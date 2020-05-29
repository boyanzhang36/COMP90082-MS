package com.medsec.util;

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
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;

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
                dataManager.ConnectionDB();
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

    public boolean userHandler(JSONObject patient) {
        System.out.println(patient.toJSONString());
        dataManager.processPatient(patient);
        return false;
    }

    public boolean apptHandler(JSONObject appointment) {
        System.out.println(appointment.toJSONString());
        dataManager.processAppointment(appointment);
        return false;
    }

    public boolean doctorHandler(JSONObject doctor) {
        System.out.println(doctor.toJSONString());
        dataManager.processDoctor(doctor);
        return false;
    }

    public boolean hospitalHandler(JSONObject hospital) {
        System.out.println(hospital.toJSONString());
        dataManager.processHospital(hospital);
        return false;
    }

    public boolean pathologyHandler(JSONObject pathology) {
        System.out.println(pathology.toJSONString());
        dataManager.processPathology(pathology);
        return false;
    }

    public boolean radiologyHandler(JSONObject radiology) {
        System.out.println(radiology.toJSONString());
        dataManager.processRadiology(radiology);
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
        String pid = fileName.substring(fileName.lastIndexOf("-") + 1,
                fileName.lastIndexOf(".")).trim();
        String eachFilePath = "\\result\\" + pid + "\\" + fileName;
        String filePath = webappsDir + eachFilePath;
//        System.out.println("#############################"+webappsDir);
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
        dataManager.processFile(file, eachFilePath);
        return false;
    }

}