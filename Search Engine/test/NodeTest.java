import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {
    Node a=new Node("aaaa",1), b = new Node("zzzz",2);
        Assertions.assertEquals(a.getKeyword(),"aaaa");
        Assertions.assertTrue(a.compareTo(b)<0);
        Assertions.assertEquals(a.getReferences().size(),0);
        a.insertReference("AA.com");
        Assertions.assertEquals(a.getReferences().size(),1);
        Assertions.assertFalse(a.equals(b));
        Assertions.assertTrue(a.equals(a));
        Assertions.assertFalse(a.equals("a"));
        Assertions.assertNotEquals(a.toString(),b.toString());
}
