import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
public class SortedArrayListTest {
    @Test
    public void Constructors(){
        SortedArrayList x = new SortedArrayList();
        Assertions.assertEquals(x.size(), 0);
        x= new SortedArrayList(Integer.class, 2);
        Assertions.assertEquals(x.size(),0);
    }
    @Test
    public void get(){
        SortedArrayList x = new SortedArrayList();
        x.add(0);
        x.add(12);
        x.add(6);
        x.add(-100);
        Assertions.assertThrows(IndexOutOfBoundsException.class,()-> {
            x.get(-1);
        });
        Assertions.assertThrows(IndexOutOfBoundsException.class,()-> {
            x.get(5);
        });
        Assertions.assertEquals(-100,x.get(0));
    }
    @Test
    public void add(){
        SortedArrayList x = new SortedArrayList(Integer.class,2);
        x.add(0);
        x.add(12);
        x.add(6);
        x.add(-100);
        Assertions.assertEquals(-100,x.get(0));
    }
    @Test
    public void deletion(){
        SortedArrayList x = new SortedArrayList(Integer.class,3);
        x.add(8);
        x.add(-3);
        x.add(4);
        x.add(6);
        Assertions.assertThrows(IndexOutOfBoundsException.class,()-> {
            x.delete(-1);
        });
        Assertions.assertThrows(IndexOutOfBoundsException.class,()-> {
            x.delete(5);
        });
        x.delete(2);
        Assertions.assertEquals(x.get(2),8);
    }
    @Test
    public void search(){
        SortedArrayList x = new SortedArrayList(Integer.class,3);
        x.add(8);
        x.add(-3);
        x.add(4);
        x.add(6);
        Assertions.assertEquals(x.search(-3),0);
        Assertions.assertEquals(x.search(8),3);
        Assertions.assertEquals(x.search(100),-1);
    }
    @Test
    public void equals(){
        SortedArrayList x = new SortedArrayList(Integer.class,3);
        x.add(8);
        x.add(-3);
        x.add(4);
        x.add(6);
        SortedArrayList x1 = new SortedArrayList(Integer.class,3);
        x1.add(8);
        x1.add(-3);
        x1.add(4);
        x1.add(6);
        Assertions.assertFalse(x.equals(x1));
        x1.add(6);
        Assertions.assertTrue(x.equals(x1));
        SortedArrayList x2 = new SortedArrayList(Integer.class,3);
        x2.add(-8);
        x2.add(0);
        x2.add(1);
        x2.add(2);
        Assertions.assertFalse(x.equals(x2));
        ArrayList x3 = new ArrayList(3);
        x3.add(-8);
        x3.add(0);
        x3.add(1);
        x3.add(2);
        Assertions.assertFalse(x3.equals(x2));
        Assertions.assertEquals(x.toString(),x1.toString());
    } }
