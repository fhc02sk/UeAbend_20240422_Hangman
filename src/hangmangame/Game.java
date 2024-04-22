package hangmangame;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private char[] currentWord;
    private char[] foundWord;
    private int counter;

    public Game() {
        counter = 0;
        loadWord();
    }

    public void loadWord() {
        DictionaryLoader dl = new DictionaryLoader();
        try {
            ArrayList<String> words = dl.load();
            Collections.sort(words, new StringLengthComparator());

            // Sicherheitsabfrage und Reset für den Fall der Fälle
            if (counter >= words.size()){
                counter = 0;
            }

            String word = words.get(counter);
            counter++;

            currentWord = word.toCharArray();
            foundWord = new char[currentWord.length];
            for (int i = 0; i < foundWord.length; i++) {
                foundWord[i] = '_';
            }

        } catch (DataFileException e) {
            throw new RuntimeException(e);
        }
    }

    public String tryCharacter(char c) {

        for (int i = 0; i < currentWord.length; i++ ){
            if (currentWord[i] == c) {
                foundWord[i] = currentWord[i];
            }
        }

        String s = String.copyValueOf(foundWord);
        return s;
    }

    public boolean isFinished() {
        for (int i = 0; i < foundWord.length; i++) {
            if (foundWord[i] == '_'){
                return false;
            }
        }

        // wenn wir hier herkommen, Spiel beendet, neues Spiel startet
        loadWord();
        return true;
    }

    public boolean isFinished2() {
        boolean found = true;
        for (int i = 0; i < foundWord.length; i++) {
            if (foundWord[i] == '_'){
                found = false;
            }
        }

        // wenn wir hier herkommen, Spiel beendet, neues Spiel startet
        loadWord();
        return found;
    }

    public boolean isFinished3() {
        String sFound = String.copyValueOf(foundWord);
        String sCurrent = String.copyValueOf(currentWord);

        boolean finished = sFound.equals(sCurrent);
        if (finished)
        {
            loadWord();
        }

        return finished;
    }

}
