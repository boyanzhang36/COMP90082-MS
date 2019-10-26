package Client;

import SocketConnection.QueryCommand;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
            ArrayList<QueryCommand> commands = new ArrayList<>();
            // Update Patient data
            commands.add(QueryCommand.PATIENT);
            // Update Appointment data
            commands.add(QueryCommand.APPOINTMENT);
            // Update File data
//            commands.add(QueryCommand.FILE);
            sendGENIEData(commands, connectionSocket);
            sendDisconnect(connectionSocket);
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
     * Generate path for file
     * @param pSurname
     * @param pFName
     * @param pID
     * @param fRName
     * @return
     */
    public File fetch(String pSurname, String pFName, int pID,
                             String fRName) {
        String fPath = GenieUI.GENIE_INSTALL_PATH + "/Images/" +
                pSurname.charAt(0) + "/" +
                pSurname + pFName.charAt(0) + Integer.toString(pID) + "/" +
                fRName;
        return new File(fPath);
    }

    /**
     * Send file as byte stream through socket
     */
    private void sendFile(Socket connectionSocket, File myFile) throws IOException {
        //Send file
        byte[] mybytearray = new byte[(int) myFile.length()];

        FileInputStream fis = new FileInputStream(myFile);
        BufferedInputStream bis = new BufferedInputStream(fis);

        DataInputStream dis = new DataInputStream(bis);
        dis.readFully(mybytearray, 0, mybytearray.length);

        OutputStream os = connectionSocket.getOutputStream();

        //Sending file name and file size to the server
        DataOutputStream dos = new DataOutputStream(os);
//        dos.writeUTF(myFile.getName());
//        dos.writeLong(mybytearray.length);
        dos.write(mybytearray, 0, mybytearray.length);
        dos.flush();

        //dos.close();

    }

    /**
     * Send GENIE data from client to the server
     *
     */
    private long sendGENIEData(ArrayList<QueryCommand> commands, Socket connectionSocket) throws Exception {
        OutputStream os = connectionSocket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        QueryManager queryManager = new QueryManager();
        // Need to login to GENIE DB first
        if (!queryManager.login())
            return this.lastUpdate;

        // Store last changed date
        long lastUpdateTemp = lastUpdate;

        String query, fields;

        // Executes all commands
        for (QueryCommand command : commands) {
            switch (command) {
                case PATIENT:
					fields = "*";
                    query = "SELECT " + fields + " FROM Patient WHERE " +
						"LastUpdated > '" + this.lastUpdate + "'";
                    System.out.println(query);
                    break;
                case APPOINTMENT:
					fields = "*";
                    query = "SELECT " + fields + " FROM Appt WHERE " +
						"LastUpdated > '" + this.lastUpdate + "'";
                    System.out.println(query);
                    break;
                case FILE:
					fields = "*";
                    query = "SELECT " + fields + " FROM Graphic";
                    System.out.println(query);
                    break;
                default:
                    throw new Exception("Invalid Command Exception");
            }

            JSONArray queryResult = queryManager.query(query);

            // Stores commands into JSONObjects
            for (int i = 0; i < queryResult.size(); i++) {
                JSONObject jsonObject = (JSONObject) queryResult.get(i);
                if (!command.equals(QueryCommand.FILE))
                    lastUpdateTemp = Math.max(lastUpdateTemp, Long.parseLong(jsonObject.get
                            ("LastUpdated").toString()));
                try {
//                    String encryptedMsg = SymmetricEncrypt.getInstance().encrypt(msg.toString());
                    // Send file if FILE QUERY
                    if (command.equals(QueryCommand.FILE)){
                        // Send file meta data
                        JSONObject msg = new JSONObject();
                        msg.put("command", command.toString());
                        // File myFile = new File((String) jsonObject.get("PathName"));
                        File myFile = new File(AUTH_JSON);
                        jsonObject.put("FileName", myFile.getName());
                        jsonObject.put("FileSize", myFile.length());
                        msg.put("doc", jsonObject);

                        fields = "Id,Surname,FirstName,MiddleName,DOB," +
                                "EmailAddress,AddressLine1,Suburb,State,LastUpdated";
                        String patientQuery = "SELECT " + fields + " FROM Patient " +
                                "WHERE " +
                                "Id = " + jsonObject.get("PT_Id_Fk").toString();

                        JSONArray patientQueryArray = queryManager.query(patientQuery);

                        if (patientQueryArray == null)
                            throw new Exception("Patient not found");

                        JSONObject patientObject = (JSONObject) patientQueryArray.get(0);

                        File file = fetch(
                                patientObject.get("SURNAME").toString(),
                                patientObject.get("FIRSTNAME").toString(),
                                Integer.valueOf(patientObject.get("ID").toString()),
                                jsonObject.get("RealName").toString());

                        dos.writeUTF(msg + "\n");
                        System.out.println(msg);
                        dos.flush();

                        // Send file bytedata
                        sendFile(connectionSocket, myFile);
                    }
                    else{
                        JSONObject msg = new JSONObject();
                        msg.put("command", command.toString());
                        msg.put("doc", jsonObject);
                        dos.writeUTF(msg + "\n");
                        dos.flush();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        // Calls disconnect
        queryManager.logout();
        return lastUpdateTemp;
    }
}
