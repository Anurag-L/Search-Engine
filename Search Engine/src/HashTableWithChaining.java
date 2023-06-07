import java.util.*;
public class HashTableWithChaining<K, V> extends Dictionary<K,V> {
    private int capacity;  // size of the table
    private int size;  // number of elements in the table
    private double loadFactor;
    private List<LinkedList<Entry<K, V>>> table;  // hash table

    // Entry class to hold key-value pairs
    private class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    public HashTableWithChaining() {
        this(11, 0.75);  // default initial capacity of 10
    }

    public HashTableWithChaining(int capacity) {
        this(capacity, 0.75);
    }

    public HashTableWithChaining(int capacity, double loadFactor) {
        capacity = nextPrime(capacity);
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.size = 0;
        this.table = new ArrayList<LinkedList<Entry<K, V>>>(capacity);
        for (int i = 0; i < capacity; i++) {
            this.table.add(new LinkedList<>());
        }
    }

    public void put(K key, V value) {
        if (containsKey(key)) {
            int hash = hash(key), n = table.get(hash).size();
            for (int i = 0; i < n; i++) {
                if (this.table.get(hash).get(i).getKey().equals(key)) {
                    this.table.get(hash).get(i).setValue(value);
                    return;
                }
            }
        }
        this.size++;
        int hash = hash(key);
        this.table.get(hash).add(new Entry<>(key, value));
        if ((size) > loadFactor * capacity) {
            resize();
        }
    }

    private boolean isPrime(int number) {
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int nextPrime(int number) {
        while (true) {
            if (isPrime(number)) {
                return number;
            }
            number++;
        }
    }

    private void resize() {
        int prevCapacity = capacity;
        capacity *= 2;
        capacity = nextPrime(capacity);
        List<LinkedList<Entry<K, V>>> temp = this.table;
        this.table = new ArrayList<LinkedList<Entry<K, V>>>(capacity);
        for (int i = 0; i < capacity; i++) {
            this.table.add(new LinkedList<>());
        }
        this.size = 0;
        for (int i = 0; i < prevCapacity; i++) {

            int n = temp.get(i).size();
            for (int j = 0; j < n; j++) {
                this.put(temp.get(i).get(j).getKey(), temp.get(i).get(j).getValue());
            }
        }
    }

    public V get(K key) {
        int hash = hash(key), n = table.get(hash).size();
        for (int i = 0; i < n; i++) {
            if (this.table.get(hash).get(i).getKey().equals(key)) {
                return this.table.get(hash).get(i).getValue();
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        int hash = hash(key), n = table.get(hash).size();
        for (int i = 0; i < n; i++) {
            if (this.table.get(hash).get(i).getKey().equals(key)) {
                this.table.get(hash).remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean remove(K key) {
        int hash = hash(key), n = table.get(hash).size();
        for (int i = 0; i < n; i++) {
            if (this.table.get(hash).get(i).getKey().equals(key)) {
                this.table.get(hash).remove(i);
                this.size--;
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (LinkedList<Entry<K, V>> list : table) {
            list.clear();
        }
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % this.capacity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int index = 0;
        for (LinkedList<Entry<K, V>> list : table) {
            if (list.size() > 0) {
                sb.append(index + ": ");
                for (Entry<K, V> entry : list) {
                    sb.append(entry.getKey() + "=" + entry.getValue() + ",");
                }
                index++;
                sb.append(";");
            }
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }
}