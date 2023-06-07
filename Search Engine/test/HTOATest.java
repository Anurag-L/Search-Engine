import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class HTOATest {
    @Test
    public void constructors(){
        HashTableOpenAddressing hashtable = new HashTableOpenAddressing<>(), linear
                = new HashTableOpenAddressing(1), quad = new HashTableOpenAddressing(2, 23, 0.5);
        Assertions.assertEquals(hashtable.size(),0);
        Assertions.assertTrue(linear.isEmpty());
        Assertions.assertEquals(quad.getClass(),HashTableOpenAddressing.class);
    }
    @Test
    public void DoubleHashing(){
        HashTableOpenAddressing hashtable = new HashTableOpenAddressing<>(), linear
                = new HashTableOpenAddressing(1), quad = new HashTableOpenAddressing(2, 23, 0.5);
        Assertions.assertEquals(hashtable.size(),0);
        Assertions.assertTrue(linear.isEmpty());
        Assertions.assertEquals(quad.getClass(),HashTableOpenAddressing.class);
        hashtable.put("hi",1);
        hashtable.put("abc",2);
        hashtable.put("Aa",1);
        hashtable.put("BB",2); // same hashvalue as Aa so collision
        hashtable.put("hi",2); // reinserting same key
        hashtable.put("xyz",3);
        hashtable.put("abcd",234);
        hashtable.put("sd",123);
        hashtable.put("sde",1223); //resize
        hashtable.put("sdet",2123);
        Assertions.assertEquals(hashtable.size(),9);
        Assertions.assertEquals(hashtable.get("hi"),2); // reinserted vallue
        Assertions.assertTrue(hashtable.containsKey("hi"));
        Assertions.assertEquals(hashtable.get("BB"),2); // collision place
        Assertions.assertTrue(hashtable.containsKey("BB"));
        Assertions.assertEquals(hashtable.get("sd"),123); // simple case
        Assertions.assertTrue(hashtable.containsKey("sd"));
        Assertions.assertNull(hashtable.get("jdsafy")); // non existent key
        Assertions.assertFalse(hashtable.containsKey("jdsafy"));
        hashtable.remove("BB"); // complex case
        Assertions.assertFalse(hashtable.containsKey("BB"));
        Assertions.assertEquals(hashtable.size(),8);
        hashtable.remove("sd"); // complex case
        Assertions.assertFalse(hashtable.containsKey("sd"));
        Assertions.assertEquals(hashtable.size(),7);
        hashtable.remove("hi"); // simple case
        Assertions.assertFalse(hashtable.containsKey("hi"));
        Assertions.assertEquals(hashtable.size(),6);
    }
    @Test
    public void linearProibing(){
        HashTableOpenAddressing hashtable = new HashTableOpenAddressing(1), quad =
                new HashTableOpenAddressing(2, 23, 0.5);
        Assertions.assertEquals(hashtable.size(),0);

        Assertions.assertTrue(hashtable.isEmpty());
        Assertions.assertEquals(quad.getClass(),HashTableOpenAddressing.class);
        hashtable.put("hi",1);
        hashtable.put("abc",2);
        hashtable.put("Aa",1);
        hashtable.put("BB",2); // same hashvalue as Aa so collision
        hashtable.put("hi",2); // reinserting same key
        hashtable.put("xyz",3);
        hashtable.put("abcd",234);
        hashtable.put("sd",123);
        hashtable.put("sde",1223); //resize
        hashtable.put("sdet",2123);
        Assertions.assertEquals(hashtable.size(),9);
        Assertions.assertEquals(hashtable.get("hi"),2); // reinserted vallue
        Assertions.assertTrue(hashtable.containsKey("hi"));
        Assertions.assertEquals(hashtable.get("BB"),2); // collision place
        Assertions.assertTrue(hashtable.containsKey("BB"));
        Assertions.assertEquals(hashtable.get("sd"),123); // simple case
        Assertions.assertTrue(hashtable.containsKey("sd"));
        Assertions.assertNull(hashtable.get("jdsafy")); // non existent key
        Assertions.assertFalse(hashtable.containsKey("jdsafy"));
        hashtable.remove("BB"); // complex case
        Assertions.assertFalse(hashtable.containsKey("BB"));
        Assertions.assertEquals(hashtable.size(),8);
        hashtable.remove("sd"); // complex case
        Assertions.assertFalse(hashtable.containsKey("sd"));
        Assertions.assertEquals(hashtable.size(),7);
        hashtable.remove("hi"); // simple case
        Assertions.assertFalse(hashtable.containsKey("hi"));
        Assertions.assertEquals(hashtable.size(),6);
    }
    @Test
    public void quadraticProbing(){
        HashTableOpenAddressing hashtable = new HashTableOpenAddressing(2, 23,
                0.5);
        Assertions.assertEquals(hashtable.size(),0);
        Assertions.assertTrue(hashtable.isEmpty());
        Assertions.assertEquals(hashtable.getClass(),HashTableOpenAddressing.class);
        hashtable.put("hi",1);
        hashtable.put("abc",2);
        hashtable.put("Aa",1);
        hashtable.put("BB",2); // same hashvalue as Aa so collision
        hashtable.put("hi",2); // reinserting same key
        hashtable.put("xyz",3);
        hashtable.put("abcd",234);
        hashtable.put("sd",123);
        hashtable.put("sde",1223); //resize
        hashtable.put("sdet",2123);
        Assertions.assertEquals(hashtable.size(),9);
        Assertions.assertEquals(hashtable.get("hi"),2); // reinserted vallue
        Assertions.assertTrue(hashtable.containsKey("hi"));
        Assertions.assertEquals(hashtable.get("BB"),2); // collision place
        Assertions.assertTrue(hashtable.containsKey("BB"));
        Assertions.assertEquals(hashtable.get("sd"),123); // simple case
        Assertions.assertTrue(hashtable.containsKey("sd"));
        Assertions.assertNull(hashtable.get("jdsafy")); // non existent key
        Assertions.assertFalse(hashtable.containsKey("jdsafy"));

        hashtable.remove("BB"); // complex case
        Assertions.assertFalse(hashtable.containsKey("BB"));
        Assertions.assertEquals(hashtable.size(),8);
        hashtable.remove("sd"); // complex case
        Assertions.assertFalse(hashtable.containsKey("sd"));
        Assertions.assertEquals(hashtable.size(),7);
        hashtable.remove("hi"); // simple case
        Assertions.assertFalse(hashtable.containsKey("hi"));
        Assertions.assertEquals(hashtable.size(),6);
    } }
