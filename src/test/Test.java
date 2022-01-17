package test;

import java.io.IOException;
import java.util.ArrayList;
import paragraph.ParagraphManager;
import spell.Spellchecker;

public class Test {

    public static void main(String[] args) throws IOException {
        
        Spellchecker corrector = new Spellchecker();
        corrector.correct("input0");
        
        ParagraphManager pm = new ParagraphManager();
        
        pm.divide("input0");
        
        ArrayList<String> docs = new ArrayList<>();
        docs.add("input0");
        docs.add("input1");
        docs.add("input2");
        pm.joinParts(docs);
        
        ArrayList<String> words = new ArrayList<>();
        words.add("documento");
        words.add("ejemplo");
        words.add("frase");
        pm.searchAndReplace("input0", words, "Este es el texto reemplazado");
    }
}