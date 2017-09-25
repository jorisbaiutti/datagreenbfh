package stemmer;

public interface Stemmer {
    void add(char[] w, int wLen);
    void stem();
    String toString();
}
