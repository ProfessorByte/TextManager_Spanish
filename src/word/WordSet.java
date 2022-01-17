package word;

import java.util.HashSet;
import java.util.Iterator;

public class WordSet {

    private HashSet<Word> set;

    public WordSet() {
        set = new HashSet<>();
    }

    public void add(Word aWord) {
        set.add(aWord);
    }

    public boolean contains(Word aWord) {
        return set.contains(aWord);
    }

    public void clear() {
        set.clear();
    }

    public Iterator<Word> iterator() {
        return set.iterator();
    }

    public int size() {
        return set.size();
    }

    public HashSet<Word> getSet() {
        return set;
    }

    public void setSet(HashSet<Word> aSet) {
        set = aSet;
    }
}