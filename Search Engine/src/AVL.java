import java.util.*;
import static java.lang.Math.max;
public class AVL<E extends Comparable<E>> implements Tree<E>{
    private int height;
    private int size;
    private BinaryNode<E> root;
    private int RRotations;
    private int LRotations;
    public AVL(){
        this.root = null;
        this.height = 0;
        this.size = 0;
        this.RRotations = 0;
        this.LRotations = 0;
    }
    public AVL(BinaryNode<E> root) {
        if (root == null) {
            this.root = null;
            this.height = 0;
            this.size = 0;
            this.RRotations = 0;
            this.LRotations = 0;
            return;
        }
        this.root = root;
        this.height = root.height();
        this.size = root.size();
        this.RRotations = 0;
        this.LRotations = 0;
//        this.root = null;
//        this.conshelper(root);
//        updateHeight();
//        this.height = this.root.height();
//        this.size = root.size();
    }
    private void conshelper (BinaryNode < E > node) {
        if (node == null) return;
        this.insert(node.data());
        if (node.hasLeft()) {
            conshelper(node.left());
        }
        if (node.hasRight()) {
            conshelper(node.right());
        }
    }

        public int getRRotations () {
            return this.RRotations;
        }
        public int getLRotations () {
            return this.LRotations;
        }
        public BinaryNode<E> root () {
            return this.root;
        }
        public int height () {
            return this.height;
        }
        public int size () {
            return this.size;
        }
        public boolean isBalanced () {
            return root.isBalanced();
        }

        public void updateHeight () {
            if (root == null) {
                this.height = 0;
                return;
            }
            helperUpdate(root);
            this.height = root.height();
        }
        public int helperUpdate (BinaryNode < E > node) {
            if (node == null) {
                return 0;
            }
            node.setHeight(1 +
                    max(helperUpdate(node.left()), helperUpdate(node.right())));
            return node.height();
        }
        private void preOrderListHelper (BinaryNode < E > root, List < E > res){
            if (root == null) return;
            res.add(root.data());
            preOrderListHelper(root.left(), res);
            preOrderListHelper(root.right(), res);
        }


        public List<E> preOrderList () {
            List<E> res = new ArrayList<>();
            preOrderListHelper(this.root, res);
            return res;
        }
        private void inOrderListHelper (BinaryNode < E > root, List < E > res){
            if (root == null) return;
            inOrderListHelper(root.left(), res);
            res.add(root.data());
            inOrderListHelper(root.right(), res);
        }

        public List<E> inOrderList () {
            List<E> res = new ArrayList<>();
            inOrderListHelper(this.root, res);
            return res;
        }
        private void postOrderListHelper (BinaryNode < E > root, List < E > res){
            if (root == null) return;
            postOrderListHelper(root.left(), res);
            postOrderListHelper(root.right(), res);
            res.add(root.data());
        }

        public List<E> postOrderList () {
            List<E> res = new ArrayList<>();
            postOrderListHelper(this.root, res);
            return res;
        }

        public void rotateRight (BinaryNode < E > x) {
            if (x.left() == null) return;
            this.LRotations++;
            BinaryNode<E> B = x.left().right();
            BinaryNode<E> y = x.left();
            E val = x.data();
            y.setParent(x.parent());
            if (y.parent() == null) {
                this.root = y;
            } else {
                if (x.parent().left() == x) {
                    x.parent().setLeft(y);
                } else {
                    x.parent().setRight(y);
                }
            }
            x.setLeft(B);
            y.setRight(x);
            int lh = 0, rh = 0;
            if (x.hasRight()) rh = x.right().height();
            else rh = 0;
            if (x.hasLeft()) lh = x.left().height();
            else lh = 0;
            x.setHeight(Math.max(rh, lh) + 1);
            if (y.hasLeft()) lh = y.left().height();
            else lh = 0;
            if (y.hasRight()) rh = y.right().height();
            else rh = 0;
            y.setHeight(Math.max(lh, rh) + 1);
        }

        public void rotateLeft (BinaryNode < E > y) {
            if (y.right() == null) return;
            this.LRotations++;
            BinaryNode<E> B = y.right().left();
            BinaryNode<E> x = y.right();
            x.setParent(y.parent());
            if (x.parent() == null) {
                this.root = x;
            } else {
                if (x.parent().left() == y) {
                    x.parent().setLeft(x);
                } else {
                    x.parent().setRight(x);
                }
            }
            y.setRight(B);
            x.setLeft(y);
            int lh = 0, rh = 0;
            if (y.hasLeft()) lh = y.left().height();
            if (y.hasRight()) rh = y.right().height();
            y.setHeight(Math.max(lh, rh) + 1);
            if (x.hasRight()) rh = x.right().height();
            else rh = 0;
            x.setHeight(Math.max(rh, y.height()) + 1);
        }

        public void possibleRotateRight (BinaryNode < E > node) {
            int lh = 0, rh = 0;
            if (node.hasLeft()) {
                lh = node.left().height();
            }
            if (node.hasRight()) {
                rh = node.right().height();
            }
            if (lh > rh) {
//                int lrh=0;
//                if(node.left().hasRight()){
//                    lrh=node.left().right().height();
                //            }
//            if(lh-lrh==1){
//
// }
                rotateLeft(node.left());
                rotateRight(node);
            }
        }

        public void possibleRotateLeft (BinaryNode < E > node) {
            int lh = 0, rh = 0;
            if (node.hasLeft()) {
                lh = node.left().height();
            }
            if (node.hasRight()) {
                rh = node.right().height();
            }
            if (rh > lh) {
                rotateRight(node.right());
                int rlh = 0;
                if (node.right().hasLeft()) {
                    rlh = node.right().left().height();
                    rotateLeft(node);
                }
            }

            public void mkBalanced (BinaryNode < E > node) {
                int lh = 0, rh = 0;
                if (node.hasLeft()) {
                    lh = node.left().height();
                }
                if (node.hasRight()) {
                    rh = node.right().height();
                }
                if (lh - rh > 1) {
                    if (node.hasLeft()) {
                        possibleRotateLeft(node.left());
                        rotateRight(node);
                    }
                }
                if (rh - lh > 1) {
                    if (node.hasRight()) {
                        possibleRotateRight(node.right());
                        rotateLeft(node);
                    }
                }
            }

            public BinaryNode<E> extractRightMost (BinaryNode < E > curNode) {
                if (curNode == null) return null;
                if (curNode.right() == null) return curNode;
                return extractRightMost(curNode.right());
            }
            private BinaryNode<E> searchHelper (BinaryNode < E > cur, E elem){
                if (cur == null) return null;
                if (cur.data().equals(elem)) {
                    return cur;
                }
                if (cur.data().compareTo(elem) > 0) {
                    return searchHelper(cur.left(), elem);
                }
                return searchHelper(cur.right(), elem);
            }

            public BinaryNode<E> search (E elem){
                return searchHelper(this.root, elem);
            }

            public void insert (E elem){
                if (root == null) {
                    this.root = new BinaryNode<>(elem, null, null, null);
                    this.size = 1;
                    this.height = 1;
                    return;
                }
                insertHelper(this.root, elem);
                this.size += 1;
                updateHeight();
            }
            private void insertHelper (BinaryNode < E > cur, E elem){
                if (cur.data().compareTo(elem) > 0) {
                    if (cur.left() == null) {
                        cur.setLeft(new BinaryNode<>(elem, null, null, cur));
                        cur.setHeight(max(2, cur.height()));
                        mkBalanced(cur);
                        return;
                    }
                    insertHelper(cur.left(), elem);
                } else if (cur.data().compareTo(elem) == 0) {
                    this.size -= 1;
                    return;
                } else if (cur.right() == null) {
                    cur.setRight(new BinaryNode<>(elem, null, null, cur));
                    cur.setHeight(max(2, cur.height()));
                    mkBalanced(cur);
                    return;
                } else
                    insertHelper(cur.right(), elem);
                int lh = 0, rh = 0;
                if (cur.hasLeft()) {
                    lh = cur.left().height();
                }
                if (cur.hasRight()) {
                    rh = cur.right().height();
                }
                mkBalanced(cur);
                cur.setHeight(Math.max(lh, rh) + 1);
            }
            public BinaryNode<E> delete (E elem){
                BinaryNode<E> x = deleteHelper(this.root, elem);
                if (x == null) {
                    // do nothing
                } else {
                    updateHeight();
                    this.size -= 1;
                    while (x.hasParent()) {
                        x = x.parent();
                        int lh = 0, rh = 0;
                        if (x.hasLeft()) {
                            lh = x.left().height();
                        } else if (x.hasRight()) {
                            rh = x.right().height();
                        }
                        x.setHeight(lh + rh + 1);
                        mkBalanced(x);
                    }
                }
                //
                mkBalanced(root);
                updateHeight();
                return x;
            }
            public void helper (BinaryNode < E > root) {
                if (root == null) return;
                helper(root.left());
                helper(root.right());
                mkBalanced(root);
            }
            public BinaryNode<E> deleteHelper (BinaryNode < E > cur, E elem){
                if (cur == null) {
                    return null;
                }
                int comp = cur.data().compareTo(elem);
                BinaryNode<E> res;
                if (comp == 0) {
                    if (cur.left() == null && cur.right() == null) {
                        if (cur.parent() == null) {
                            this.root = null;
                            this.height = 0;
                            this.size = 1;
                            return cur; //chill
                        } else {
                            if (cur.parent().getLeft() == cur) {
                                cur.parent().setLeft(null);
                            } else {
                                cur.parent().setRight(null);
                            }
                            return cur; //traverse parents
                        }
                    } else if (cur.left() == null) {
                        BinaryNode<E> x = new
                                BinaryNode<>(cur.data(), cur.left(), cur.right(), cur.parent());
                        cur.setData(cur.right().data());
                        cur.setLeft(cur.right().left());
                        cur.setRight(cur.right().right());
                        x.setParent(cur);
                        return x; //traverse parents
                    } else {
                        BinaryNode<E> x = extractRightMost(cur.left());
                        if (cur.left() == x) {
                            E tmp = cur.data();
                            cur.setLeft(x.left());
                            cur.left().setParent(cur);
                            cur.setData(x.data());
                            x.setData(tmp);
                            return x; //traverse parents
                        } else {
                            x.parent().setRight(null);
                            cur.setData(x.data());
                            x.setData(elem);
                            return x; //traverse parents
                        }
                    }
                } else if (comp > 0) {
                    res = deleteHelper(cur.left(), elem);
                    int lh = 0, rh = 0;
                    if (cur.hasLeft()) {
                        lh = cur.left().height();
                    }
                    if (cur.hasRight()) {
                        rh = cur.right().height();
                    }
                    mkBalanced(cur);
                    cur.setHeight(Math.max(lh, rh) + 1);
                } else {
                    res = deleteHelper(cur.right(), elem);
                    int lh = 0, rh = 0;
                    if (cur.hasLeft()) {
                        lh = cur.left().height();
                    }
                    if (cur.hasRight()) {
                        rh = cur.right().height();
                    }
                    mkBalanced(cur);
                    cur.setHeight(Math.max(lh, rh) + 1);
                }
                return res;
            }
            static <E extends Comparable<E>>Tree<E> mkAVL (Collection < E > elems) {
                Tree<E> result = new AVL<>();
                for (E e : elems) result.insert(e);
                return result;
            }
            public TreePrinter.PrintableNode getLeft () {
                return this.root.hasLeft() ? this.root.left() : null;
            }
            public TreePrinter.PrintableNode getRight () {
                return this.root.hasRight() ? this.root.right() : null;
            }
            public String getText () {
                return (this.root != null) ? this.root.getText() : "";
            }
        }
    }