package document;

import word.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Document {

    private BufferedReader input;

    private BufferedWriter output;

    public Document(String aInput, String aOutput) throws IOException {
        input = new BufferedReader(new FileReader(aInput));
        output = new BufferedWriter(new FileWriter(aOutput));
    }

    public void close() throws IOException {
        input.close();
        output.close();
    }

    public Word getWord() throws IOException, EOFException {
        Word word = new Word();
        StringBuilder buffer = new StringBuilder();
        int c = 0;
        boolean isGetting = false;
        while ((c = input.read()) != -1) {
            if (!Character.isLetterOrDigit(c)) {
                if (!isGetting) {
                    output.write(c);
                } else {
                    break;
                }
            } else {
                buffer.append((char) c);
                isGetting = true;
                input.mark(c);
            }
        }
        if (c == -1) {
            throw new EOFException();
        }
        input.reset();
        word.setWord(buffer.toString());
        return word;
    }

    public void putWord(Word aWord) throws IOException {
        output.write(aWord.getWord());
        output.flush();
    }

    public BufferedReader getInput() {
        return input;
    }

    public void setInput(BufferedReader aInput) {
        input = aInput;
    }

    public BufferedWriter getOutput() {
        return output;
    }

    public void setOutput(BufferedWriter aOutput) {
        output = aOutput;
    }
}