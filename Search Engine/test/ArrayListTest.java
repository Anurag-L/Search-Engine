import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
public class ArrayListTest {
    @Test
    public void Constructors(){
        ArrayList x = new ArrayList();
        Assertions.assertEquals(x.size(), 0);
        x= new ArrayList( 2);
        Assertions.assertEquals(x.size(),0);
    }
    @Test
    public void get(){
        ArrayList x = new ArrayList();
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
        Assertions.assertEquals(-100,x.get(3));
    }
    @Test
    public void add(){
        ArrayList x = new ArrayList(2);
        x.add(0);
        x.add(12);
        x.add(6);
        x.add(-100);
        Assertions.assertEquals(0,x.get(0));
    }
    @Test
    public void deletion(){
        ArrayList x = new ArrayList(3);
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
        Assertions.assertEquals(x.get(2),6);
    }
    @Test
    public void search(){
        ArrayList x = new ArrayList(3);
        x.add(8);
        x.add(-3);
        x.add(4);
        x.add(6);
        Assertions.assertEquals(x.search(-3),1);
        Assertions.assertEquals(x.search(8),0);
        Assertions.assertEquals(x.search(100),-1);
    }
    @Test
    public void equals(){
        ArrayList x = new ArrayList(3);
        x.add(8);
        x.add(-3);
        x.add(4);
        x.add(6);
        ArrayList x1 = new ArrayList(3);
        x1.add(8);
        x1.add(-3);
        x1.add(4);
        Assertions.assertFalse(x.equals(x1));
        x1.add(6);
        Assertions.assertTrue(x.equals(x1));
        ArrayList x2 = new ArrayList(3);
        x2.add(-8);
        x2.add(0);
        x2.add(1);
        x2.add(2);
        Assertions.assertFalse(x.equals(x2));
        Assertions.assertEquals(x.toString(),x1.toString());
    } }
