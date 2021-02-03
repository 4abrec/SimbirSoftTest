import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args){
        DownloadPage page;
        Document document;
        String URL;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            System.out.println("Введите адрес веб-страницы:");
            URL = reader.readLine();
        } catch (IOException e){
            logger.error(e.getMessage());
            return;
        }
        try {
            page = new DownloadPage(URL);
            document = page.getHTML();
        } catch (IllegalArgumentException e) {
            logger.error("Ошибка соединения. Проверьте правильность введенной ссылки");
            return;
        } catch (IOException e) {
            logger.error("Ошибка получения HTML кода страницы");
            return;
        }
        page.savePage(document);
        GetStatistics statistics = new GetStatistics(document);
        statistics.receiveAndPrintWordsAndCount();
        DataBase.writeStatisticToDB(statistics.getWordAndCount());

    }
}
