import java.util.ArrayList;
public class Trie {
    TrieNode root;
    public Trie(){
        root = new TrieNode();
    }
    public Trie(TrieNode root){
        this.root = root;
    }
    public TrieNode getRoot(){
        return this.root;
    }
    public void setRoot(TrieNode root){
        this.root = root;
    }
    void insert(String word) {
        word = word.toLowerCase();
        TrieNode cur = this.root;
        int n = word.length();
        for(int i=0;i<n;i++){
            if(!cur.children.containsKey(word.charAt(i))){
                cur.children.put(word.charAt(i),new TrieNode());
            }
            cur = cur.children.get(word.charAt(i));
        }
        cur.isWord=true;
        cur.frequency+=1;
    }
    boolean search(String word) {
        word = word.toLowerCase();
        TrieNode cur = this.root;
        int n = word.length();
        for(int i=0;i<n;i++){
            if(!cur.children.containsKey(word.charAt(i))){
                return false;
            }
            cur = cur.children.get(word.charAt(i));
        }
        return cur.isWord;
    }
void delete(String word){
    word = word.toLowerCase();
    if(!search(word))return;
    boolean b = true;
    int i=-1,n=word.length();
    TrieNode cur = root;
    deleteHelper(word,b,i,n,cur);
}
    boolean deleteHelper(String word, boolean b, int i, int n, TrieNode cur){
        if(i==n-1){
            cur.isWord = false;
            if(cur.children.size()==0){
                return true;
            }
            return false;
        }
        if(deleteHelper(word,b,i+1,n,cur.children.get(word.charAt(i+1)))){
            cur.children.remove(word.charAt(i+1));
            if(!cur.children.isEmpty() || cur.isWord){
                return false;
            }
            return true;
        }
        return false;
    }
    void dfs(StringBuilder stringBuilder , TrieNode cur , ArrayList<Entry> ls){
        if(cur.isWord){
            ls.add(new Entry(cur.frequency,stringBuilder.toString()));
        }
        for(Character c: cur.children.keySet()){
            stringBuilder.append(c.charValue());
            dfs(stringBuilder,cur.children.get(c),ls);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        } }
    public ArrayList<Entry> generateWordsFromPrefix(String prefix){
        ArrayList<Entry> ls = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        TrieNode cur = root;
        int n = prefix.length();
        for(int i=0;i<n;i++){
            if(!cur.children.containsKey(prefix.charAt(i))){
                return ls;
            }
            cur = cur.children.get(prefix.charAt(i));
            stringBuilder.append(prefix.charAt(i));
        }
        dfs(stringBuilder,cur,ls);
        return ls;
    } }