package dictionary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileDictionary extends Dictionary {

    private String loadPath;

    public FileDictionary(String aLoadPath) {
        loadPath = aLoadPath;
        try {
            load(loadPath);
        } catch (IOException ex) {
            Logger.getLogger(FileDictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void load(String aLoadPath) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        String bufferLine = null;
        BufferedReader buffer = new BufferedReader(new FileReader(aLoadPath));
        while ((bufferLine = buffer.readLine()) != null) {
            list.add(bufferLine);
        }
        buffer.close();
        fromStringList(list);
    }

    public void save() throws IOException {
        BufferedWriter input = new BufferedWriter(new FileWriter(loadPath));
        List<String> list = toStringList();
        Iterator<String> iter = list.iterator();

        while (iter.hasNext()) {
            input.write(iter.next());
            input.newLine();
        }
        input.flush();
        input.close();
    }

    public void save(String aFile) throws IOException {
        setLoadPath(aFile);
        save();
    }

    public String getLoadPath() {
        return loadPath;
    }

    public void setLoadPath(String aLoadPath) {
        loadPath = aLoadPath;
    }
}