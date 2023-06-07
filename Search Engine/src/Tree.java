import java.util.List;

interface Tree<E extends Comparable<E>> extends TreePrinter.PrintableNode {
    BinaryNode<E> root();

    int height();
    boolean isBalanced();
    void updateHeight();
    int size();

    List<E> preOrderList();
    List<E> inOrderList();
    List<E> postOrderList();

    BinaryNode<E> extractRightMost(BinaryNode<E> curNode);

    BinaryNode<E> search(E elem);
    void insert(E elem);
    BinaryNode<E> delete (E elem);
}
