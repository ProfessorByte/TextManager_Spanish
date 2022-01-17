package word;

public class Word {

    private String word;

    public Word() {
        word = "";
    }

    public Word(String aWord) {
        word = aWord;
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }

    @Override
    public boolean equals(Object aObj) {
        if (aObj instanceof Word) {
            Word w = (Word) aObj;
            return this.word.equals(w.word);
        }
        return false;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String aWord) {
        word = aWord;
    }
}
