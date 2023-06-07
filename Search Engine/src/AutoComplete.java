import java.io.IOException;
import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class AutoComplete {
    private Trie trie;
    private String file = "dataset.txt";

    public AutoComplete() throws IOException {
        this.trie = new Trie();
        buildTrie();
    }

    public AutoComplete(String file) throws IOException {
        this.trie = new Trie();
        this.file = file;
        buildTrie();
    }

    public Trie getTrie(){
        return this.trie;
    }


    // TODO: Build the trie from the words from the file.
    public void buildTrie() throws IOException {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            System.out.print(file + " loaded. ");
            Instant start = Instant.now();
            String line;
            int wordCount = 0;
            while((line = reader.readLine()) != null){
                String text = line.toLowerCase();
                text = text.replaceAll("\\p{Punct}", "");
                String[] words = text.split("\\s+"); // splits by whitespace
                for(String word: words){
                    word.toLowerCase();
                    if(!trie.search(word))wordCount++;
                    trie.insert(word);
                }
            }
            Instant end = Instant.now();
            long time = Duration.between(start, end).toMillis();
            System.out.print(wordCount + " words loaded into Trie in " + time + " ms\n");
        }
        catch (IOException e){
            throw e;
        }

    }


    // TODO: Returns a list of the top 6 ranked (frequency) words starting with the
    //       given prefix (must use BubbleSort)
    //       Hint: don't overthink this.
    public ArrayList<Entry> autoComplete(String prefix){
        ArrayList<Entry> a = new
                ArrayList<>();//bubbleSort(trie.generateWordsFromPrefix(prefix));
        int n = prefix.length();
        for(int i=0;i<n;i++){
            char c = prefix.charAt(i);
            for(int j=0;j<26;j++) {
                prefix = prefix.substring(0,i) + (char)('a'+j) +
                        prefix.substring(i+1);
                a.addAll(trie.generateWordsFromPrefix(prefix));
            }
            prefix = prefix.substring(0,i) + c + prefix.substring(i+1);
        }
        ArrayList<Entry> res = new ArrayList<>();
        a = bubbleSort(a);
        for(int i=0;i<Math.min(a.size(),6);i++){
            res.add(a.get(i));
        }
        return res;
    }

    // TODO: Implement BubbleSort. Sort by frequency of the Entry. Return the list of entries sorted.
    //     Hint: https://media.geeksforgeeks.org/wp-content/cdn-uploads/gq/2014/02/bubble-sort1.png
    public ArrayList<Entry> bubbleSort(ArrayList<Entry> ls){
        int changes = 1 , n = ls.size();
        while(changes>0){
            changes=0;
            for(int i=0;i<n-1;i++){
                if(ls.get(i).getFrequency()<ls.get(i+1).getFrequency()){
                    Entry temp = ls.get(i+1);
                    ls.set(i + 1, ls.get(i));
                    ls.set(i,temp);
                    changes=1; }
            } }
        return ls;
    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String file = input.nextLine();
        AutoComplete a = new AutoComplete(file);
        String answer = "y";
        while (answer.equals("y")) {
            System.out.print("Enter word to find prefix (0 to quit program): ");
            String term = input.nextLine();
            if(term.equals("0")){
                System.out.println("buh bye");
                break;
            }
            for(Entry e : a.autoComplete(term)){
                System.out.println(e);
            }
            System.out.println("==========================================");
        }
        input.close();

    }
}
