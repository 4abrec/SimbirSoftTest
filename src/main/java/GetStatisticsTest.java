import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetStatisticsTest {

    @Test
    public void statisticsShouldBeRight() throws IOException {
        DownloadPage page = new DownloadPage("https://lab.hakim.se/checkwave/");
        Document document = page.getHTML();
        GetStatistics stats = new GetStatistics(document);
        stats.receiveStatistics();
        Map<String, Integer> testMap = stats.getWordAndCount();
        Map<String, Integer> expectedMap = new HashMap<>();
        expectedMap.put("FOLLOW", 1);
        expectedMap.put("GITHUB", 1);
        expectedMap.put("@HAKIMEL", 1);
        expectedMap.put("SOURCE", 1);
        expectedMap.put("TWEET", 1);
        expectedMap.put("THIS", 1);
        expectedMap.put("ON", 1);
        Assertions.assertEquals(expectedMap,testMap);
    }

    @Test
    public void statisticsNotNull() throws IOException {
        DownloadPage page = new DownloadPage("https://www.simbirsoft.com/");
        Document document = page.getHTML();
        GetStatistics stats = new GetStatistics(document);
        stats.receiveStatistics();
        Map<String, Integer> testMap = stats.getWordAndCount();
        Assertions.assertNotNull(testMap);
    }
}
