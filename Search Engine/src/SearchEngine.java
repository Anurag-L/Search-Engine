import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.*;
import java.util.Locale;
import java.util.Scanner;
public class SearchEngine {
    private int mode;
    private List<Node> nodeList;
    public SearchEngine(int mode) throws IOException {
        this.mode=mode;
        if(mode==1)
            this.nodeList = new ArrayList<Node>();
        else if(mode==2)
            this.nodeList = new SortedArrayList<Node>();
        if(mode==3){
            this.nodeTree = new BST<>();
        }
        else if(mode == 4){
            this.nodeTree = new AVL<>();
        }
        else if(mode==5){
            this.nodeTable = new HashTableOpenAddressing<>();
        }
        else if(this.mode==6){
            this.nodeTable = new HashTableWithChaining<>();
        }
        else
            throw new IOException();
        buildList();
    }
    public List<Node> getNodeList(){
        return this.nodeList;
    }

    public Tree<Node> getNodeTree(){
        return this.nodeTree;
    }

    public Dictionary<String, Node> getNodeTree(){
        return this.nodeTable;
    }
    public void buildList() throws IOException {
        System.out.println("reading");
        BufferedReader reader = new BufferedReader(new FileReader("dataset.txt"));
        String url;
        while((url = reader.readLine()) != null){
            Document doc = Jsoup.connect(url).get();
            String text = doc.body().text().toLowerCase();
            String[] words = text.split("\\s+"); // splits by whitespace
            // logic here
            if(this.mode<=2){
                for (int i = 0; i < words.length; i++) {
                    Node word = new Node(words[i], mode);
                    if (this.nodeList.search(word) == -1) {
                        word.insertReference(url);
                        this.nodeList.add(word);
                    } else {
                        if (this.nodeList.get(this.nodeList.search(word)).getReferences().search(url) == -1)
                            this.nodeList.get(this.nodeList.search(word)).insertReference(url);
                    }
                }
            }
            else if(this.mode<=4){
                for (String word : words) {
                    Node x = new Node(word);
                    if(nodeTree.search(x)==null){
                        x.insertReference(url);
                        this.nodeTree.insert(x);
                    }
                    else{ if(!
                            this.nodeTree.search(x).data().getReferences().contains(url)){
                        this.nodeTree.search(x).data().insertReference(url);
                    } }
                }
            }
            else{
                int count = 0;
                for (String word : words) {
                    if(this.nodeTable.containsKey(word)){
                        if(!this.nodeTable.get(word).getReferences().contains(url))
                            this.nodeTable.get(word).getReferences().add(url);
                    } else{
                        Node x = new Node(word);
                        x.insertReference(url);
                        this.nodeTable.put(word,x);
                    }
                }
            }
        }
        reader.close();
        System.out.println("Finished reading through all URLs");
    }
    public List<String> search(String term) {
        if(this.mode<=2){
            System.out.println("Searching for " + term + " using data structure mode " + mode + "...");
            Node x = new Node(term, this.mode);
            int n = this.nodeList.size();
            for (int i = 0; i < n; i++) {
                if (x.equals(this.nodeList.get(i))) {
                    List<String> ls;
                    if (this.mode == 1) {
                        ls = new ArrayList<>();
                    } else ls = new SortedArrayList<>();
                    ls = this.nodeList.get(i).getReferences();
                    int J = ls.size();
                    System.out.println("Displaying results for " + term + ":");
                    for (int j = 0; j < J; j++) {
                        System.out.println((j + 1) + ". URL " + (j + 1) + ": " + ls.get(j));
                    }
                    return ls;
                }
            }
            List<String> ls;
            if (this.mode == 1) {
                ls = new ArrayList<>();
            } else ls = new SortedArrayList<>();
            return ls;
            System.out.println("Displaying results for " + term + ":");
        }
        else if(this.mode<=4){
            System.out.println("Searching for " + term + " using data structure mode "
                    + mode + "...");
            Node x = new Node(term);
            BinaryNode<Node> y = this.nodeTree.search(x);
            if(y==null) {
                System.out.println("No results to display");
                return new ArrayList<>();
            }
            ArrayList<String> ll = y.data().getReferences();
            int i=1;
            System.out.println("Displaying results for \" + term + \":\"");
            for(String s:ll){
                System.out.println(i+". URL "+i+": "+s);
                i++; }
            return ll;
        }
        else{
            System.out.println("Searching for " + term + " using data structure mode "
                    + mode + "...");
            if(this.nodeTable.containsKey(term)==false) {
                System.out.println("No urls with that word");
                return new ArrayList<>();
            } else{
                ArrayList<String> x = this.nodeTable.get(term).getReferences();
                int X = x.size();
                for(int i=0;i<X;i++){
                    System.out.println("Url "+i+": "+x.get(i));
                }
                return x; }
        }
    }


    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter mode as in what data structure to use:");
        System.out.println("    1. Array List ");
        System.out.println("    2. Sorted Array List");
        int mode = input.nextInt();
        System.out.println("Building Search Engine...");
        SearchEngine engine = new SearchEngine(mode);
        String answer = "y";
        while (answer.equals("y")) {
            input.nextLine();
            System.out.print("Search (enter a term to query): ");
            String term = input.nextLine();
            engine.search(term);
            System.out.print("Would you like to search another term (y/n)? ");
            answer = input.nextLine();
        }
        input.close();
}
}