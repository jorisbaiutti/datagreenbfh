package stemmer;

public class NullStemmer implements Stemmer {
    String currentWord = null;

    @Override
    public void add(char[] w, int wLen) {
        currentWord = new String(w);
    }

    @Override
    public void stem() {

    }

    @Override
    public String toString() {
        return currentWord;
    }
}
