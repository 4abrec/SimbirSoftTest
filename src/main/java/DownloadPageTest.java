import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class DownloadPageTest {

    @Test
    public void documentNotNull() throws IOException {
        DownloadPage page = new DownloadPage("https://www.simbirsoft.com/");
        Document document = page.getHTML();
        Assertions.assertNotNull(document);
    }
}
