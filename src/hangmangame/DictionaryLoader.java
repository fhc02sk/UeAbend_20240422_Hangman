package hangmangame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DictionaryLoader {

    public ArrayList<String> load() throws DataFileException {

        ArrayList<String> result = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(".\\data\\words.txt")))
        {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }
            return result;

        } catch (IOException e) {
            throw new DataFileException("Fehler beim Einlesen", e);
        }

    }
}
