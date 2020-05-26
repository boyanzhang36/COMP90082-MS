package Client;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;


public class UploadFileManager {

    private static String UPLOAD_PATH = null;
    private static File uploadedFile = null;

    protected UploadFileManager(String uploadPath){

        this.UPLOAD_PATH = uploadPath;
        this.uploadedFile = new File(this.UPLOAD_PATH);

    }

    public Element readHtmlFile() {

        Element htmlTable = null;

        try {
            Document doc = Jsoup.parse(uploadedFile, "UTF-8");

            htmlTable = doc.select("table").get(0);

        } catch (IOException e) {
            System.out.println("File not found!");
//            e.printStackTrace();
        }

        return htmlTable;
    }

    public File readExcelFile(){
        return null;
    }

    public File readFile(){
        return uploadedFile;
    }

}
