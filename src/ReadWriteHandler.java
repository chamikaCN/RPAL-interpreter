import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ReadWriteHandler {
    private static ReadWriteHandler instance = null;

    private ReadWriteHandler() {
    }

    public static ReadWriteHandler getInstance() {
        if (instance == null)
            instance = new ReadWriteHandler();

        return instance;
    }

    public ArrayList<String> readFile(String filepath) {
        File file = new File(filepath);
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                lines.add(st);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

}

