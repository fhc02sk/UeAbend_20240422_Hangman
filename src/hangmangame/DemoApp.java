package hangmangame;

import java.util.ArrayList;
import java.util.Collections;

public class DemoApp {

    public static void main(String[] args) throws DataFileException {

        DictionaryLoader dl = new DictionaryLoader();
        
        ArrayList<String> words = dl.load();

        System.out.println("words = " + words);

        Collections.sort(words, new StringLengthComparator());

        System.out.println("words = " + words);
                
        
    }
}
