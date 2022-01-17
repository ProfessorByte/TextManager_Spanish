package dictionary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import word.*;

public abstract class Dictionary {
    
    WordSet set;

    public Dictionary() {
        set = new WordSet();
    }

    public void add(Word aWord) {
        set.add(aWord);
    }

    public boolean contain(Word aWord) {
        return set.contains(aWord);
    }

    public void clear() {
        set.clear();
    }

    public void fromStringList(List<String> list) {
        Iterator<String> iter = list.iterator();

        while (iter.hasNext()) {
            Word word = new Word(iter.next());
            set.add(word);
        }
    }

    public List<String> toStringList() {
        List<String> list = new ArrayList<String>();
        Iterator<Word> iter = set.iterator();

        while (iter.hasNext()) {
            list.add(iter.next().getWord());
        }
        return list;
    }

    public int size() {
        return set.size();
    }

    public WordSet getSet() {
        return set;
    }

    public void setSet(WordSet aSet) {
        set = aSet;
    }
}