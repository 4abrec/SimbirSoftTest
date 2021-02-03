import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import java.util.*;

public class GetStatistics {
    final static Logger logger = Logger.getLogger(GetStatistics.class);
    private final Document document;
    private final Map<String,Integer> wordAndCount;
    private List<String> words;

    public GetStatistics(Document document){
        this.document = document;
        this.wordAndCount = new HashMap<>();
    }

    private void receiveWordAndCount(){
        for(String word: words){
            if (!wordAndCount.containsKey(word))
            {
                wordAndCount.put(word, 0);
            }
            wordAndCount.put(word, wordAndCount.get(word) + 1);
        }
    }
    public void receiveAndPrintWordsAndCount(){
        try {
            receiveWords();
            receiveWordAndCount();
            printWordAndCount();
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
    private void printWordAndCount(){
        for (String word : wordAndCount.keySet())
        {
            System.out.println(word + " - " + wordAndCount.get(word));
        }
    }

    private void receiveWords(){
        String text= document.body().text();
        StringBuilder builder = new StringBuilder(text); //чтобы при replace каждый раз не создавался новый объект строки
        List<Character> separators = Arrays.asList
                (',','.','?','!','[',']','(',')','«','»','/','{','}','-','–','—','!','"',';',':','\n','\t','\r');
        char c;
        for(int i = 0; i < text.length(); i++){
            c = text.charAt(i);
            if(separators.contains(c))
                builder.replace(i, i + 1, " ");
        }
        text = builder.toString().trim().replaceAll(" +", " ").toUpperCase(Locale.ROOT);
        words = Arrays.asList(text.split(" "));
    }

    public Map<String, Integer> getWordAndCount() {
        return wordAndCount;
    }
}
