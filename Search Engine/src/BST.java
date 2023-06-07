import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BST<E extends Comparable<E>> implements Tree<E> {

    private int height;
    private int size;
    private BinaryNode<E> root;

    public BST(){
        this.root = null;
        this.height = 0;
        this.size = 0;
    }

    public BST(BinaryNode<E> root){
        this.root=root;
        if(root==null)this.height=0;
        else this.height= root().height();
        if(root==null)this.size=0;
        else this.size= root().size();
    }

    public BinaryNode<E> root() {
        return this.root;
    }

    public int height() {
        return this.height;
    }
    public int size() {
        return this.size;
    }
    public boolean isBalanced() {
        return root.isBalanced();
    }

    public void updateHeight() {
        if(root==null) {
            this.height = 0;
            return; }
        helperUpdate(root);
    }
    public int helperUpdate(BinaryNode<E> node){
        if(node==null){
            return 0; }
        node.setHeight(1+Math.max(helperUpdate(node.left()),helperUpdate(node.right())));
        return node.height();
    }

    // Traversals that return lists
    // TODO: Preorder traversal

    private void preOrderListHelper(BinaryNode<E> cur, List<E> res){
        if(cur==null)return;
        res.add(cur.data());
        preOrderListHelper(cur.left(),res);
        preOrderListHelper(cur.right(),res);
    }

    public List<E> preOrderList() {
        List<E> res = new ArrayList<>();
        preOrderListHelper(this.root,res);
        return res;
    }

    private void inOrderListHelper(BinaryNode<E> root, List<E> res){
        if(root==null)return;
        inOrderListHelper(root.left(),res);
        res.add(root.data());
        inOrderListHelper(root.right(),res);
    }

    public List<E> inOrderList() {
        List<E> res = new ArrayList<>();
        inOrderListHelper(this.root,res);
        return res;
    }

    private void postOrderListHelper(BinaryNode<E> root, List<E> res){
        if(root==null)return;
        postOrderListHelper(root.left(),res);
        postOrderListHelper(root.right(),res);
        res.add(root.data());
    }

    public List<E> postOrderList() {
        List<E> res = new ArrayList<>();
        postOrderListHelper(this.root,res);
        return res;
    }

    public BinaryNode<E> extractRightMost(BinaryNode<E> curNode) {
        if(curNode==null)return null;
        if(curNode.right()==null) return curNode;
        return extractRightMost(curNode.right());
    }

    private BinaryNode<E> searchHelper(BinaryNode<E> cur, E elem) {
        if(cur==null)return null;
        if(cur.data().equals(elem)){
            return cur; }
        if(cur.data().compareTo(elem)>0){
            return searchHelper(cur.left(),elem);
        }
        return searchHelper(cur.right(), elem);
    }

    public BinaryNode<E> search(E elem) {
        return searchHelper(this.root,elem);
    }

    public void insert(E elem) {
        if(root==null){
            this.root=new BinaryNode<>(elem,null,null,null);
            this.size=1;
            this.height=1;
            return;
        }
        insertHelper(this.root,elem);
        updateHeight();
        this.height=this.root().height();
        this.size+=1;
    }

    private void insertHelper(BinaryNode<E> cur, E elem) {
        if(cur.data().compareTo(elem)>0){
            if(cur.left()==null){
                cur.setLeft(new BinaryNode<>(elem,null,null,cur));
                return; }
            insertHelper(cur.left(),elem);
            return; }
        else if(cur.data().compareTo(elem)==0){
            this.size-=1;
            return; }
        if(cur.right()==null){
            cur.setRight(new BinaryNode<>(elem,null,null,cur));
            return;
        }
        insertHelper(cur.right(),elem);
    }

    public BinaryNode<E> deleteHelper(BinaryNode<E> cur, E elem){
        if(cur==null){
            return null;
        }
        int comp = cur.data().compareTo(elem);
        if(comp==0){
            if(cur.left()==null && cur.right()==null){
                if(cur.parent()==null){
                    this.root=null;
                    this.height = -1;
                    this.size = 1;
                    return cur;
                } else{
                    if(cur.parent().getLeft()==cur){
                        cur.parent().setLeft(null);
                    }
                    else{
                        cur.parent().setRight(null);
                    }
                    return cur; }
            }
            else if (cur.left()==null) {
                BinaryNode<E> x = new
                        BinaryNode<>(cur.data(),cur.left(),cur.right(),cur.parent());
                cur.setData(cur.right().data());
                cur.setLeft(cur.right().left());
                cur.setRight(cur.right().right());
                return x;
            } else{
                BinaryNode<E> x = extractRightMost(cur.left());
                if(cur.left()==x){
                    E tmp = cur.data();
                    cur.setLeft(x.left());
                    if(x.left()!=null)
                        cur.left().setParent(cur);
                    cur.setData(x.data());
                    x.setData(tmp);
                    return x;
                } else{
                    x.parent().setRight(null);
                    cur.setData(x.data());
                    x.setData(elem);
                    return x;
                } }
        }
        else if(comp>0){
            return deleteHelper(cur.left(),elem);
        }
        else{
            return deleteHelper(cur.right(),elem);
        }
    }

    // TODO: delete
    public BinaryNode<E> delete(E elem) {
        BinaryNode<E> x = deleteHelper(this.root,elem);
        if(x==null){
            // do nothing
        }
        else{
            this.size-=1;
            updateHeight();
            if(this.root()!=null)
                this.height=this.root().height();
        }
        return x;
    }
    static <E extends Comparable<E>> Tree<E> mkBST (Collection<E> elems) {
        Tree<E> result = new BST<>();
        for (E e : elems) result.insert(e);
        return result;
    }
    public TreePrinter.PrintableNode getLeft() {
        return this.root.hasLeft() ? this.root.left() : null;
    }
    public TreePrinter.PrintableNode getRight() {
        return this.root.hasRight() ? this.root.right() : null;
    }
    public String getText() {
        return (this.root != null) ? this.root.getText() : "";
    }
}
