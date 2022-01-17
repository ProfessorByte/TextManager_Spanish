package spell;

import java.io.EOFException;
import java.io.IOException;
import java.util.Scanner;

import word.Word;
import dictionary.*;
import document.Document;

public class Spellchecker {

    private Scanner inputUserScanner = new Scanner(System.in);

    public void correct(String fileName) throws IOException {
        FileDictionary dictMain;
        MemDictionary dictIgnored;
        String dictPath = "dict.txt";
        String docInPath = "resText/"+fileName+".txt";
        String docOutPath = "out/out.txt";
        try {
            dictMain = new FileDictionary(dictPath);
            dictIgnored = new MemDictionary();
            proccessDocument(docInPath, docOutPath, dictMain, dictIgnored);
            dictMain.save();
            System.out.println("El domumento " + fileName + " ha sido procesado.\nResultados en out.txt");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("file " + dictPath + " not found");
        }
    }

    private void proccessDocument(String aInput, String aOutput, Dictionary aDictMain, Dictionary aDictIgnored) throws IOException {
        Word word;
        Document document;
        try {
            document = new Document(aInput, aOutput);
            while (true) {
                try {
                    word = document.getWord();
                    if (!aDictMain.contain(word) && !aDictIgnored.contain(word)) {
                        word = consultUser(word, aDictMain, aDictIgnored);
                    }
                    document.putWord(word);
                } catch (EOFException e) {
                    break;
                }
            }
            document.close();
            inputUserScanner.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("file " + aInput + " not found");
        }
    }

    private Word consultUser(Word aWord, Dictionary aDictMain, Dictionary aDictIgnored) throws IOException {
        String answer;
        String wordBuffer;
        do {
            System.out.println("Palabra no reconocida: " + aWord.getWord() + "\nAceptar (a) - Ignorar (i) - Reemplazar (r): ");
            answer = inputUserScanner.nextLine();
        } while (!answer.equals("a") && !answer.equals("r") && !answer.equals("i"));
        if (answer.equals("a")) {
            aDictMain.add(aWord);
        } else if (answer.equals("i")) {
            aDictIgnored.add(aWord);
        } else {
            boolean s = false;
            while (!s) {
                System.out.println("Ingrese la palabra que utilizara como reemplazo: ");
                wordBuffer = inputUserScanner.nextLine();
                if (aDictMain.contain(new Word(wordBuffer))) {
                    s = true;
                    aWord.setWord(wordBuffer);
                }else{
                    System.out.println("Palabra incorrecta");
                }
            }
        }
        return aWord;
    }
}