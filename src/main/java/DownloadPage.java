import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

public class DownloadPage {
    final static Logger logger = Logger.getLogger(DownloadPage.class);
    private final String URL;
    private final Connection connect;

    public DownloadPage(String URL){
        this.URL = URL;
        this.connect = Jsoup.connect(this.URL);
    }

    public Document getHTML() throws IOException {
        Connection.Response response = connect.execute();
        return response.parse();
    }

    public void savePage(Document document){
        File page = new File("src/main/files", "page.html");
        try {
            FileUtils.writeStringToFile(page, document.outerHtml(), "UTF-8");
        }catch (IOException e){
            logger.error("Невозможно записать данные в файл");
        }
    }

    public String getURL() {
        return URL;
    }
}
