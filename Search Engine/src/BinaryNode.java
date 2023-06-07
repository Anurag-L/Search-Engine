public class BinaryNode<E extends Comparable<E>> implements TreePrinter.PrintableNode{
    private E data;
    private BinaryNode<E> left;
    private BinaryNode<E> right;
    private int height;
    private int size;
    private BinaryNode<E> parent;

    public BinaryNode(E data){
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.height = 1;
        this.size = 1;

    }

    // TODO: Set up the BinaryNode
    public BinaryNode(E data, BinaryNode<E> left, BinaryNode<E> right, BinaryNode<E> parent){
        this.data=data;
        setLeft(left);
        setRight(right);
        setParent(parent);
        if(left!=null && right!=null)
            this.height=max(left.height,right.height)+1;
        else if(left!=null)
            this.height=left.height+1;
        else if(right!=null)
            this.height=right.height+1;
        else this.height=1;
        if(left!=null && right!=null)
            this.size=left.size+right.size+1;
        else if(left!=null)
            this.size=left.size+1;
        else if(right!=null)
            this.size=right.size+1;
        else this.size=1;
    }

    public int helperUpdate1(BinaryNode<E> node){
        if(node==null){
            return -1;
        }
        node.setHeight(1+Math.max(helperUpdate1(node.left()),helperUpdate1(node.right())));
        return node.height();
    }

    E data() { return this.data; };
    BinaryNode<E> left() {
        return this.left;
    }
    BinaryNode<E> right() {
        return this.right;
    }
    BinaryNode<E> parent() { return this.parent; }

    void setLeft(BinaryNode<E> left) {
        this.left = left;
        if(left != null) left.setParent(this);
    }
    void setRight(BinaryNode<E> right) {
        this.right = right;
        if(right != null) right.setParent(this);
    }
    void setParent(BinaryNode<E> parent) {
        this.parent = parent;
    }
    void setData(E data) { this.data = data; }
    void setHeight(int h){
        this.height = h;
    }

    int height() {
        return this.height;
    }
    int size() { return this.size; }
    boolean isBalanced() {
        int leftHeight = (hasLeft()) ? left.height() : 0;
        int rightHeight = (hasRight()) ? right().height() : 0;
        return Math.abs(leftHeight - rightHeight) < 2;
    }
    boolean hasLeft(){
        return left == null ? false : true;
    }
    boolean hasRight(){
        return right == null ? false :true;
    }
    boolean hasParent(){
        return parent == null ? false :true;
    }

    public boolean equals(BinaryNode<E> other){
        if(other == null) return false;
        return other.data.equals(this.data);
    }

    public TreePrinter.PrintableNode getLeft() {
        return left == null ? null :  left;
    }
    public TreePrinter.PrintableNode getRight() {
        return right == null ? null : right;
    }
    public String getText() {
        return String.valueOf(data);
    }
    public String toString(){
        String ret = "";
        return "root " + this.data + " Left: " +(hasLeft() ? left.data : null)  + " Right: " +(hasRight() ? right.data : null) +
                " parent: " + (hasParent() ? parent.data : null) ;
    }

}
