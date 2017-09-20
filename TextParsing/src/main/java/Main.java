import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;

public class Main {

    public static void main(String[] args) {
        Path filePath = Paths.get("C:\\BFH\\Hamlet.txt");
        HashMap<String,Long> words = new HashMap<>();
        try {
            Files.readAllLines(filePath).stream()
                    .map(line -> line.split("\\s+"))
                    .flatMap(Arrays::stream)
                    .filter(w -> w.matches("\\w+"))
                    .filter(w -> !w.matches("^[+-]?(\\d*\\.)?\\d+$"))
                    .forEach(w ->{
                        if (!words.containsKey(w)) {
                            words.put(w, 1l);
                        }else{
                            words.put(w, words.get(w) + 1);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        words.keySet().stream().forEach(k ->{
            System.out.println(k + ":  " + words.get(k));
        });
        Path outputPath = Paths.get("C:\\BFH\\output.csv");
        try {
            Files.write(outputPath, () -> words.entrySet().stream()
                    .<CharSequence>map(e -> e.getKey() + "," + e.getValue())
                    .iterator());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
