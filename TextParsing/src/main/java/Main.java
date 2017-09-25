import stemmer.PorterStemmer;
import stemmer.Stemmer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Path filePath = Paths.get("C:\\git\\datagreenbfh\\TextParsing\\Hamlet.txt");
        HashMap<String, Long> words = new HashMap<>();
        Stemmer stemmer = new PorterStemmer();

        try {
            Files.readAllLines(filePath).stream()
                    .map(line -> line.split(" "))
                    .flatMap(Arrays::stream)
                    .map(w -> w.replaceAll("[^a-zA-Z]", "").toLowerCase())
                    .filter(w -> w.length() > 0)
                    .forEach(w -> {
                        char[] wordAsChar = w.toCharArray();
                        stemmer.add(wordAsChar, wordAsChar.length);
                        stemmer.stem();
                        String stemmedWord = stemmer.toString();
                        if (!words.containsKey(stemmedWord)) {
                            words.put(stemmedWord, 1l);
                        } else {
                            words.put(stemmedWord, words.get(stemmedWord) + 1);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        words.keySet().stream().forEach(k -> {
            System.out.println(k + ":  " + words.get(k));
        });
        Path outputPath = Paths.get("C:\\git\\datagreenbfh\\TextParsing\\output.csv");
        try {
            Files.write(outputPath, () -> words.entrySet().stream()
                    .<CharSequence>map(e -> e.getKey() + "," + e.getValue())
                    .iterator());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
