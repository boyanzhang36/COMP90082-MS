package Client;

import SocketConnection.QueryCommand;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Reads data from the GENIE Server and sends raw JSON string to our DB
 */
public class JSONWriter {

        private static JSONWriter instance = null;
        private final String AUTH_JSON =
                "src/main/resources/TestData/authentication_client.json";

        private long lastUpdate = 0;

        protected JSONWriter() {
            // Exists only to defeat instantiation.
        }

        public static JSONWriter getInstance() throws IOException {
            if(instance == null) {
                instance = new JSONWriter();
            }
            return instance;
        }

        /** Adds Commands for data to update
         * @param connectionSocket
         * @throws Exception
         */
        public void sendUpdateData(Socket connectionSocket) throws
                Exception {
            sendAuthentication(connectionSocket);
//            ArrayList<QueryCommand> commands = new ArrayList<>();
            // Update Patient data
//            commands.add(QueryCommand.PATIENT);
            // Update Appointment data
//            commands.add(QueryCommand.APPOINTMENT);
            // Update File data
//            commands.add(QueryCommand.FILE);
            //sendGENIEData(commands, connectionSocket);
//            htmlToJSON(connectionSocket, QueryCommand.APPOINTMENT, "src/main/resources/test.html");
            if (GenieUI.FILE_EXTENSION.equals("html") && GenieUI.COMMAND != QueryCommand.FILE){
                sendHtml(connectionSocket, GenieUI.COMMAND, GenieUI.FILE_UPLOAD_PATH);
            }
            else if (GenieUI.FILE_EXTENSION.equals("xls") && GenieUI.COMMAND != QueryCommand.FILE){
                sendExcel(connectionSocket, GenieUI.COMMAND, GenieUI.FILE_UPLOAD_PATH);
            }
            else if (GenieUI.FILE_EXTENSION.equals("pdf") && GenieUI.COMMAND == QueryCommand.FILE){
                sendFile(connectionSocket, GenieUI.COMMAND, GenieUI.FILE_UPLOAD_PATH);
            }
            sendDisconnect(connectionSocket);
            System.out.println("Upload Finished!");
        }

        /**
         * After established connection, authentication first
         */
        private void sendAuthentication(Socket connectionSocket) {
            try {
                OutputStream os = connectionSocket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);

                JSONParser parser = new JSONParser();
                Object obj = parser.parse(new FileReader(AUTH_JSON));
                JSONObject jsonObject = (JSONObject) obj;
                jsonObject.put("command", QueryCommand.AUTHENTICATION.toString());
//            String encryptedMsg = SymmetricEncrypt.getInstance().encrypt(jsonObject.toString());
                dos.writeUTF(jsonObject + "\n");
                dos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * Send disconnection after completing all DB queries
         */
        private void sendDisconnect(Socket connectionSocket){
            try {
                OutputStream os = connectionSocket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("command", QueryCommand.DISCONNECTION.toString());
//            String encryptedMsg = SymmetricEncrypt.getInstance().encrypt(jsonObject.toString());
                dos.writeUTF(jsonObject + "\n");
                dos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * Extract html file data and send from client to the server
         */
        private void sendHtml(Socket connectionSocket, QueryCommand command, String uploadPath) {

            try {
                OutputStream os = connectionSocket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                UploadFileManager uploadHtml = new UploadFileManager(uploadPath);
                System.out.println("Uploaded Success");
                Element htmlTable = uploadHtml.readHtmlFile();
                Elements htmlTrs = htmlTable.select("tr");

                Elements tdHeads = htmlTrs.get(0).select("td");

//            JSONArray jsonObjectDoc = new JSONArray();
                for (int i = 1; i < htmlTrs.size(); i++){
                    JSONObject msg = new JSONObject();
                    msg.put("command", command.toString());

                    JSONObject jsonObject = new JSONObject();
                    Elements tds = htmlTrs.get(i).select("td");
                    for (int j = 0; j < tds.size(); j++){

                        Element pHead = tdHeads.get(j).select("p").get(0);
                        Element pContent = tds.get(j).select("font").get(0);

                        if (pContent.hasClass("p")){
                            pContent = pContent.select("p").get(0);
                        }

                        if (pContent != null){
                            jsonObject.put(pHead.text(), pContent.text());
                        }
                        else{
                            jsonObject.put(pHead.text(), pContent);
                        }

                    }
//                jsonObjectDoc.add(jsonObject);
                    msg.put("doc", jsonObject);
                    System.out.println(msg);
                    dos.writeUTF(msg + "\n");
                    dos.flush();

                }
            } catch (NullPointerException e) {
                System.out.println("File content not found!");
//                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
        * Extract excel file data and send from client to the server
        */
        private void sendExcel(Socket connectionSocket, QueryCommand command, String uploadPath){

            try {
                OutputStream os = connectionSocket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                UploadFileManager uploadExcel = new UploadFileManager(uploadPath);
                System.out.println("Uploaded Success");
                HSSFSheet excelSheet = uploadExcel.readExcelFile();
                HSSFRow excelHeads = excelSheet.getRow(0);

                int rowNumber = excelSheet.getLastRowNum();
                System.out.println(rowNumber);
                for (int i = 1; i < rowNumber + 1; i++){
                    JSONObject msg = new JSONObject();
                    msg.put("command", command.toString());

                    JSONObject jsonObject = new JSONObject();
                    HSSFRow excelRow = excelSheet.getRow(i);
                    int cellNumber = excelRow.getLastCellNum();
                    for (int j = 0; j < cellNumber; j++){
                        HSSFCell excelHead = excelHeads.getCell(j);
                        HSSFCell excelCellContent = excelRow.getCell(j);

                        excelHead.setCellType(CellType.STRING);

                        if (excelCellContent != null){
                            excelCellContent.setCellType(CellType.STRING);
                            jsonObject.put(excelHead.getStringCellValue(), excelCellContent.getStringCellValue());
                        }
                        else {
                            jsonObject.put(excelHead.getStringCellValue(), excelCellContent);
                        }
                    }

                    msg.put("doc", jsonObject);
                    System.out.println(msg);
                    dos.writeUTF(msg + "\n");
                    dos.flush();

                }

            } catch (NullPointerException e) {
                System.out.println("File content not found!");
//                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        /**
        * Send file data from client to the server
        */
        private void sendFile(Socket connectionSocket, QueryCommand command, String uploadPath){

            try {
                OutputStream os = connectionSocket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);

                UploadFileManager uploadedFile = new UploadFileManager(uploadPath);
                System.out.println("Uploaded Success");
                File myFile = uploadedFile.readFile();

                JSONObject msg = new JSONObject();
                JSONObject jsonObject = new JSONObject();

                msg.put("command", command.toString());
                jsonObject.put("FileName", myFile.getName());
                jsonObject.put("FileSize", myFile.length());
                msg.put("doc", jsonObject);
                System.out.println(msg);
                dos.writeUTF(msg + "\n");
                dos.flush();

                sendData(connectionSocket, myFile);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        /**
        * Send file as byte stream through socket
        */
        private void sendData(Socket connectionSocket, File myFile) throws IOException {
            //Send file
            byte[] mybytearray = new byte[(int) myFile.length()];

            FileInputStream fis = new FileInputStream(myFile);
            BufferedInputStream bis = new BufferedInputStream(fis);

            DataInputStream dis = new DataInputStream(bis);
            dis.readFully(mybytearray, 0, mybytearray.length);

            OutputStream os = connectionSocket.getOutputStream();

            //Sending file name and file size to the server
            DataOutputStream dos = new DataOutputStream(os);
//          dos.writeUTF(myFile.getName());
//          dos.writeLong(mybytearray.length);
            dos.write(mybytearray, 0, mybytearray.length);
            dos.flush();

            //dos.close();
        }



        /*************************************NOT USED**********************************************/

    /**
     * Generate path for file
     * @param pSurname
     * @param pFName
     * @param pID
     * @param fRName
     * @return
     */
//    public File fetch(String pSurname, String pFName, int pID,
//                             String fRName) {
//        String fPath = GenieUI.GENIE_INSTALL_PATH + "/Images/" +
//                pSurname.charAt(0) + "/" +
//                pSurname + pFName.charAt(0) + Integer.toString(pID) + "/" +
//                fRName;
//        return new File(fPath);
//    }

    /**
     * Send GENIE data from client to the server
     *
     */
//    private long sendGENIEData(ArrayList<QueryCommand> commands, Socket connectionSocket) throws Exception {
//        OutputStream os = connectionSocket.getOutputStream();
//        DataOutputStream dos = new DataOutputStream(os);
//
//        QueryManager queryManager = new QueryManager();
//        // Need to login to GENIE DB first
//        if (!queryManager.login())
//            return this.lastUpdate;
//
//        // Store last changed date
//        long lastUpdateTemp = lastUpdate;
//
//        String query, fields;
//
//        // Executes all commands
//        for (QueryCommand command : commands) {
//            switch (command) {
//                case PATIENT:
//					fields = "*";
//                    query = "SELECT " + fields + " FROM Patient WHERE " +
//						"LastUpdated > '" + this.lastUpdate + "'";
//                    System.out.println(query);
//                    break;
//                case APPOINTMENT:
//					fields = "*";
//                    query = "SELECT " + fields + " FROM Appt WHERE " +
//						"LastUpdated > '" + this.lastUpdate + "'";
//                    System.out.println(query);
//                    break;
//                case FILE:
//					fields = "*";
//                    query = "SELECT " + fields + " FROM Graphic";
//                    System.out.println(query);
//                    break;
//                default:
//                    throw new Exception("Invalid Command Exception");
//            }
//
//            JSONArray queryResult = queryManager.query(query);
//
//            // Stores commands into JSONObjects
//            for (int i = 0; i < queryResult.size(); i++) {
//                JSONObject jsonObject = (JSONObject) queryResult.get(i);
//                if (!command.equals(QueryCommand.FILE))
//                    lastUpdateTemp = Math.max(lastUpdateTemp, Long.parseLong(jsonObject.get
//                            ("LastUpdated").toString()));
//                try {
////                    String encryptedMsg = SymmetricEncrypt.getInstance().encrypt(msg.toString());
//                    // Send file if FILE QUERY
//                    if (command.equals(QueryCommand.FILE)){
//                        // Send file meta data
//                        JSONObject msg = new JSONObject();
//                        msg.put("command", command.toString());
//                        // File myFile = new File((String) jsonObject.get("PathName"));
//                        File myFile = new File(AUTH_JSON);
//                        jsonObject.put("FileName", myFile.getName());
//                        jsonObject.put("FileSize", myFile.length());
//                        msg.put("doc", jsonObject);
//
//                        fields = "Id,Surname,FirstName,MiddleName,DOB," +
//                                "EmailAddress,AddressLine1,Suburb,State,LastUpdated";
//                        String patientQuery = "SELECT " + fields + " FROM Patient " +
//                                "WHERE " +
//                                "Id = " + jsonObject.get("PT_Id_Fk").toString();
//
//                        JSONArray patientQueryArray = queryManager.query(patientQuery);
//
//                        if (patientQueryArray == null)
//                            throw new Exception("Patient not found");
//
//                        JSONObject patientObject = (JSONObject) patientQueryArray.get(0);
//
//                        File file = fetch(
//                                patientObject.get("SURNAME").toString(),
//                                patientObject.get("FIRSTNAME").toString(),
//                                Integer.valueOf(patientObject.get("ID").toString()),
//                                jsonObject.get("RealName").toString());
//
//                        dos.writeUTF(msg + "\n");
//                        System.out.println(msg);
//                        dos.flush();
//
//                        // Send file bytedata
//                        sendFile(connectionSocket, myFile);
//                    }
//                    else{
//                        JSONObject msg = new JSONObject();
//                        msg.put("command", command.toString());
//                        msg.put("doc", jsonObject);
//                        dos.writeUTF(msg + "\n");
//                        dos.flush();
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        // Calls disconnect
//        queryManager.logout();
//        return lastUpdateTemp;
//    }
}
