import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
public class BSTTest {
    @Test
    public void primaryConstructorTest(){
        BST a = new BST<>();
        Assertions.assertEquals(a.size(),0);
        Assertions.assertEquals(a.height(),0);
        Assertions.assertNull(a.root());
    }
    @Test
    public void secondaryConstructorTest(){
        BinaryNode<Integer> node1 = new BinaryNode<Integer>(1),node2=(new
                BinaryNode<Integer>(5));
        BinaryNode<Integer> a1 = new BinaryNode<>(3,node1,node2,null);
        BST a = new BST<>(a1);
        Assertions.assertEquals(3,a.root().data());
        Assertions.assertEquals(2,a.height());
        Assertions.assertEquals(3,a.size());
    }
    @Test
    public void updateHeightTest(){
        BST a = new BST<>();
        a.insert(3);
        a.insert(2);
        a.insert(1);
        a.updateHeight();
        Assertions.assertEquals(a.height(),3);
        Assertions.assertEquals(a.root().left().height(),2);
    }
    @Test
    public void preOrderListTest(){
        BST a = new BST<>(), b = new BST();
        a.insert(4);
        a.insert(2);
        a.insert(3);
        a.insert(1);
        a.insert(6);
        a.insert(5);
        a.insert(8);
        ArrayList<Integer> l = new ArrayList<>();
        Assertions.assertEquals(b.preOrderList(),l);
        l.add(4);
        l.add(2);
        l.add(1);
        l.add(3);
        l.add(6);
        l.add(5);
        l.add(8);
        Assertions.assertEquals(a.preOrderList(), l);
    }
    @Test
    public void inOrderListTest(){

        BST a = new BST<>(), b = new BST();
        a.insert(4);
        a.insert(2);
        a.insert(3);
        a.insert(1);
        a.insert(6);
        a.insert(5);
        a.insert(8);
        ArrayList<Integer> l = new ArrayList<>();
        Assertions.assertEquals(b.inOrderList(),l);
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);
        l.add(6);
        l.add(8);
        Assertions.assertEquals(a.inOrderList(), l);
    }
    @Test
    public void postOrderListTest(){
        BST a = new BST<>(), b = new BST();
        a.insert(4);
        a.insert(2);
        a.insert(3);
        a.insert(1);
        a.insert(6);
        a.insert(5);
        a.insert(8);
        ArrayList<Integer> l = new ArrayList<>();
        Assertions.assertEquals(b.postOrderList(),l);
        l.add(1);
        l.add(3);
        l.add(2);
        l.add(5);
        l.add(8);
        l.add(6);
        l.add(4);
        Assertions.assertEquals(a.postOrderList(), l);
    }
    @Test
    public void extractRightTest() {
        BST a = new BST<>(), b = new BST();
        a.insert(4);
        a.insert(2);
        a.insert(3);
        a.insert(1);
        a.insert(6);
        a.insert(5);
        a.insert(8);
        Assertions.assertEquals(a.extractRightMost(a.root()).data(),8);
        Assertions.assertNull(a.extractRightMost(a.root().left().left().left()));
    }
    @Test
    public void searchTest(){
        BST a = new BST<>(), b = new BST();

        a.insert(4);
        a.insert(2);
        a.insert(3);
        a.insert(1);
        a.insert(6);
        a.insert(5);
        a.insert(8);
        Assertions.assertNull(a.search(7));
        Assertions.assertEquals(a.search(8).data(),8);
        Assertions.assertEquals(a.search(6).data(),6);
        Assertions.assertEquals(a.search(1).data(),1);
        Assertions.assertEquals(a.search(3).data(),3);
    }
    @Test
    public void insertTest() {
        BST a = new BST<>(), b = new BST();
        a.insert(4); // null
        Assertions.assertEquals(a.size(),1);
        a.insert(2); // left
        Assertions.assertEquals(a.size(),2);
        a.insert(3); // left right
        Assertions.assertEquals(a.size(),3);
        a.insert(1); // left left
        Assertions.assertEquals(a.size(),4);
        a.insert(6); // right
        Assertions.assertEquals(a.size(),5);
        a.insert(5); // right left
        Assertions.assertEquals(a.size(),6);
        a.insert(8); // right right
        Assertions.assertEquals(a.size(),7);
        Assertions.assertEquals(a.root().data(),4);
    }
    @Test
    public void deleteTest(){
        BST a = new BST<>(), b = new BST();
        a.insert(4);
        a.insert(2);
        a.insert(3);
        a.insert(1);
        a.insert(6);
        a.insert(5);
        a.insert(8);
        Assertions.assertNull(a.delete(9));
        Assertions.assertEquals(a.delete(4).data(),4);
        Assertions.assertEquals(a.root().data(),3);
        Assertions.assertEquals(a.delete(1).data(),1);
        a.delete(5);
        Assertions.assertEquals(a.delete(6).data(),6);
        Assertions.assertEquals(a.size(),3);
    }
}
