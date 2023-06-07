import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class HTCTest {
    @Test
    public void constructorTest(){
        HashTableWithChaining h = new HashTableWithChaining(), h1 = new
                HashTableWithChaining(3), h2 = new HashTableWithChaining(11,0.55);
        Assertions.assertNotNull(h);
        Assertions.assertNotNull(h1);
        Assertions.assertNotNull(h2);
        Assertions.assertEquals(h.size(),0);
        Assertions.assertEquals(h.toString(),h1.toString());
        Assertions.assertEquals(h.getClass(),HashTableWithChaining.class);
    }
    @Test
    public void putTests(){
        HashTableWithChaining h = new HashTableWithChaining();
        h.put(1,1);
        h.put(12,1); // same hash value so collision
        Assertions.assertEquals(h.size(),2);
        h.put(1,3);
        Assertions.assertEquals(h.get(1),3);
        Assertions.assertEquals(h.size(),2); // size on putting existent key
        h.put(8,13); // different hash value
        h.put(23,13); // collision again
        Assertions.assertEquals(h.get(1),3);
        Assertions.assertEquals(h.get(23),13);
        Assertions.assertEquals(h.toString(),"{0: 1=3,12=1,23=13,;1: 8=13}");
        h.put(24,15); // before hitting resize is mapped to different place to key
        1 but after resize mapped into same
        h.put(47,16);
        h.put(60,19);
        h.put(17,8);
        h.put(18,9);
        Assertions.assertEquals(h.size(),9);
        Assertions.assertEquals(h.toString(),"{0: 23=13,;1: 1=3,24=15,47=16,;2:
                8=13,;3: 12=1,;4: 60=19,;5: 17=8,;6: 18=9}");
}
    @Test
    public void getTests(){
        HashTableWithChaining h = new HashTableWithChaining();
        h.put(1,1);
        h.put(12,1); // same hash value so collision
        Assertions.assertEquals(h.size(),2);
        h.put(1,3);
        Assertions.assertEquals(h.get(1),3); // getting an updated value
        Assertions.assertEquals(h.size(),2); // size on putting existent key
        h.put(8,13); // different hash value
        h.put(23,13); // collision again
        Assertions.assertEquals(h.get(1),3); // from beginning of chain
        Assertions.assertEquals(h.get(23),13); // from end of chain
        Assertions.assertEquals(h.get(12),1); // from middle of chain
        Assertions.assertEquals(h.toString(),"{0: 1=3,12=1,23=13,;1: 8=13}");
        h.put(24,15); // before hitting resize, is mapped to different place to key
        1 but after resize mapped into same

        h.put(47,16);
        h.put(60,19);
        h.put(17,8);
        h.put(18,9);
        Assertions.assertEquals(h.get(18),9); // simple get
    }
    @Test
    public void containsTest(){
        HashTableWithChaining h = new HashTableWithChaining();
        h.put(1,1);
        h.put(12,1); // same hash value so collision
        Assertions.assertEquals(h.size(),2);
        h.put(1,3);
        Assertions.assertTrue(h.containsKey(1)); // after updating value
        Assertions.assertEquals(h.size(),2); // size on putting existent key
        h.put(8,13); // different hash value
        h.put(23,13); // collision again
        Assertions.assertTrue(h.containsKey(1)); // from beginning of chain
        Assertions.assertTrue(h.containsKey(23)); // from end of chain
        Assertions.assertTrue(h.containsKey(12)); // from middle of chain
        Assertions.assertEquals(h.toString(),"{0: 1=3,12=1,23=13,;1: 8=13}");
        h.put(24,15); // before hitting resize, is mapped to different place to key
        1 but after resize mapped into same
        h.put(47,16);
        h.put(60,19);
        h.put(17,8);
        h.put(18,9);
        Assertions.assertFalse(h.containsKey(25)); // place of hashvalue in table
        is empty
        Assertions.assertFalse(h.containsKey(70)); // place of hashvalue in table
        is not empty but key is non existent
        Assertions.assertTrue(h.containsKey(18)); // simple case
    }
    @Test
    public void removeTest(){
        HashTableWithChaining h = new HashTableWithChaining();
        h.put(1,1);
        h.put(12,1); // same hash value so collision
        Assertions.assertEquals(h.size(),2);
        h.put(1,3);
        Assertions.assertTrue(h.containsKey(1)); // after updating value
        Assertions.assertEquals(h.size(),2); // size on putting existent key
        h.put(8,13); // different hash value
        h.put(23,13); // collision again
        Assertions.assertTrue(h.containsKey(1));
        h.remove(1); // from beginning of chain
        Assertions.assertFalse(h.containsKey(1));
        Assertions.assertEquals(h.size(),3);
        Assertions.assertTrue(h.containsKey(23));
        h.remove(23); // from end of chain
        Assertions.assertFalse(h.containsKey(23));
        Assertions.assertEquals(h.size(),2);
        Assertions.assertTrue(h.containsKey(12));
        h.remove(12); // simple case
        Assertions.assertFalse(h.containsKey(12));
        Assertions.assertEquals(h.size(),1);

        h.put(1,3);
        h.put(12,1);
        h.put(8,13);
        h.put(23,13);
        h.put(24,15); // before hitting resize, is mapped to different place to key
        1 but after resize mapped into same
        h.put(47,16);
        h.put(60,19);
        h.put(17,8);
        h.put(18,9);
        Assertions.assertEquals(h.size(),9);
        Assertions.assertFalse(h.containsKey(25));
        h.remove(25); // place of hashvalue in table is empty
        Assertions.assertEquals(h.size(),9); //unaltered
        h.remove(70); // place of hashvalue in table is not empty but key is non
        existent
        Assertions.assertTrue(h.containsKey(18)); // simple case
        Assertions.assertEquals(h.size(),9); // unaltered
    } }
