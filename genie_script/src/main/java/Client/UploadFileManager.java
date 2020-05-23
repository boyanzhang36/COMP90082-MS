package Client;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;


public class UploadFileManager {

    private static String UPLOAD_PATH = null;
    private static Element htmlTable = null;

    protected UploadFileManager(String uploadPath){

        this.UPLOAD_PATH = uploadPath;

    }

    public Element readHtmlFile() {

        try {

            File htmlFile = new File(UPLOAD_PATH);
            Document doc = Jsoup.parse(htmlFile, "UTF-8");

            htmlTable = doc.select("table").get(0);

        } catch (IOException e) {
            System.out.println("File not found!");
//            e.printStackTrace();
        }

        return htmlTable;

    }

}
