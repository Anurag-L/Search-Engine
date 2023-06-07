import java.util.ArrayList;
import java.util.HashMap;
public class TrieNode {
    HashMap<Character, TrieNode> children;
    boolean isWord;
    int frequency;
    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
        frequency = 0;
    }
    public String toString () {
        return children.toString();
    }
}