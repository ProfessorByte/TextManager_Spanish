package paragraph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ParagraphManager {
    
    public void divide(String fileName) {
        ArrayList<String> paragraphs = new ArrayList<>();
        String docInPath = "resText/" + fileName + ".txt";
        try(BufferedReader br = new BufferedReader(new FileReader(docInPath))) {
            String line;
            String paragraph = "";
            while ((line = br.readLine()) != null) {
                if (line.charAt(line.length() - 1) == '.') {
                    paragraph += line+"\r\n";
                    paragraphs.add(paragraph);
                    paragraph = "";
                } else {
                    paragraph += line+"\r\n";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            for (int i = 0; i < paragraphs.size(); i++) {
                BufferedWriter bw = new BufferedWriter(new FileWriter("out/outDivide" + i + ".txt"));
                bw.write(paragraphs.get(i));
                bw.flush();
                bw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void joinParts(ArrayList<String> parts){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("out/outUnited.txt"))){
            for(String part : parts){
                BufferedReader br = new BufferedReader(new FileReader("resText/"+part+".txt"));
                String line;
                while((line = br.readLine())!=null){
                    bw.write(line+"\r\n");
                }
            }
            bw.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void searchAndReplace(String fileName, ArrayList<String> words, String text){
        ArrayList<String> paragraphs = new ArrayList<>();
        String docInPath = "resText/" + fileName + ".txt";
        try(BufferedReader br = new BufferedReader(new FileReader(docInPath))) {
            String line;
            String paragraph = "";
            while ((line = br.readLine()) != null) {
                if (line.charAt(line.length() - 1) == '.') {
                    paragraph += line+"\r\n";
                    paragraphs.add(paragraph);
                    paragraph = "";
                } else {
                    paragraph += line+"\r\n";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            for (int i = 0; i < paragraphs.size(); i++) {
                BufferedWriter bw = new BufferedWriter(new FileWriter("temp/temp" + i + ".txt"));
                bw.write(paragraphs.get(i));
                bw.flush();
                bw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        File filesF = new File("temp");
        File[] files = filesF.listFiles();
        int[] quantity = new int[files.length];
        for(File f : files){
            int i = 0;
            for(String word : words){
                try(BufferedReader br = new BufferedReader(new FileReader(f))){
                    String line;
                    while((line = br.readLine())!=null){
                        if(line.contains(word)){
                            quantity[i]++;
                        }
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            i++;
        }
        int maxIdx = 0;
        for(int i = 0; i<quantity.length-1; i++){
            if(quantity[i]>quantity[i+1] || quantity[i]==quantity[i+1]){
                maxIdx = i;
            }else if (quantity[i]<quantity[i+1]){
                maxIdx = i+1;
            }
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("temp/temp"+maxIdx+".txt"))){
            bw.write(text);
            bw.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
        ArrayList<String> parts = new ArrayList<>();
        for(int i = 0; i<files.length; i++){
            parts.add("temp"+i);
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("out/outPagraphReplaced.txt"))){
            for(String part : parts){
                BufferedReader br = new BufferedReader(new FileReader("temp/"+part+".txt"));
                String line;
                while((line = br.readLine())!=null){
                    bw.write(line+"\r\n");
                }
            }
            bw.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
        for(File f : files){
            f.delete();
        }
    }
}
