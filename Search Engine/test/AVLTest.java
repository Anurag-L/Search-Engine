import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
public class AVLTest {
    @Test
    public void primaryConstructorTest(){
        AVL a = new AVL<>();
        Assertions.assertEquals(a.size(),0);
        Assertions.assertEquals(a.height(),0);
        Assertions.assertNull(a.root());
    }
    @Test
    public void secondaryConstructorTest(){
        BinaryNode<Integer> node1 = new BinaryNode<Integer>(1),node2=(new
                BinaryNode<Integer>(5));
        BinaryNode<Integer> a1 = new BinaryNode<>(3,node1,node2,null);
        AVL a = new AVL<>(a1);
        Assertions.assertEquals(3,a.root().data());
        Assertions.assertEquals(2,a.height());
        Assertions.assertEquals(3,a.size());
    }
    @Test
    public void updateHeightTest(){
        AVL a = new AVL<>();
        a.insert(3);
        a.insert(2);
        a.insert(1);
        a.updateHeight();
        Assertions.assertEquals(a.height(),3);
        Assertions.assertEquals(a.root().left().height(),2);
    }
    @Test
    public void preOrderListTest(){
        AVL a = new AVL<>(), b = new AVL();
        a.insert(4);
        a.insert(2);
        a.insert(3);
        a.insert(1);
        a.insert(6);
        a.insert(5);
        a.insert(8);
        ArrayList<Integer> l = new ArrayList<>();
        Assertions.assertEquals(b.preOrderList(),l);
        l.add(3);
        l.add(2);
        l.add(1);
        l.add(5);
        l.add(4);
        l.add(6);
        l.add(8);

        Assertions.assertEquals(a.preOrderList(), l);
    }
    @Test
    public void inOrderListTest(){
        AVL a = new AVL<>(), b = new AVL();
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
        AVL a = new AVL<>(), b = new AVL();
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
        l.add(2);
        l.add(4);
        l.add(8);
        l.add(6);
        l.add(5);
        l.add(3);
        Assertions.assertEquals(a.postOrderList(), l);
    }
    @Test
    public void extractRightTest() {
        AVL a = new AVL<>(), b = new AVL();
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
        AVL a = new AVL<>(), b = new AVL();
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
        AVL a = new AVL<>(), b = new AVL();
        a.insert(20);
        a.insert(4);
        a.insert(15);
        Assertions.assertEquals(a.root().data(),15);
        a= new AVL();
        a.insert(20);
        a.insert(4);
        a.insert(26);
        a.insert(3);
        a.insert(9);
        a.insert(15);
        Assertions.assertEquals(a.root().data(),9);
        a= new AVL();
        a.insert(4);
        a.insert(20);
        a.insert(15);
        Assertions.assertEquals(a.root().data(),15);
        a= new AVL();
        a.insert(4);
        a.insert(3);
        a.insert(2);
        Assertions.assertEquals(a.root().data(),3);
    }
    @Test
    public void deleteTest(){
        AVL a = new AVL<>(), b = new AVL();
        a.insert(2);
        a.insert(1);
        a.insert(4);
        a.insert(5);
        a.insert(3);
        TreePrinter.print(a);
        a.delete(1);
        TreePrinter.print(a);
        Assertions.assertEquals(a.root().data(),4);
        a.delete(5);
        TreePrinter.print(a);
        assertNull(a.delete(1));
    }
}
