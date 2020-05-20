package Client;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;


public class UploadFileManager {

    private static String UPLOAD_PATH = null;

    protected UploadFileManager(){
        System.out.println(GenieUI.GENIE_INSTALL_PATH);

        this.UPLOAD_PATH = "C:/Users/HWK/Desktop/t.html";

    }

    public void readExcelFile() {

        try {
            File htmlFile = new File(UPLOAD_PATH);
            Document doc = Jsoup.parse(htmlFile, "UTF-8");
            Element table = doc.select("table").get(0);
            System.out.println(table);
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
