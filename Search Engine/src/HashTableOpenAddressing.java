import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static java.lang.Math.abs;
public class HashTableOpenAddressing<K, V> extends Dictionary<K,V> {
    private int capacity;
    private int size;
    private int previousPrime;
    double hashing.
    private int mode;
    public static int LINEARPROBING = 1;
    public static int QUADRATICPROBING = 2;
    public static int DOUBLEHASHING = 3;
    private double loadFactor;
    private Entry<K, V>[] table;

    public HashTableOpenAddressing() {
        this(DOUBLEHASHING, 11, 0.75);
    }

    public HashTableOpenAddressing(int mode) {
        this(mode, 11, 0.75);
    }

    public HashTableOpenAddressing(int capacity, double loadFactor) {
        this(DOUBLEHASHING, capacity, loadFactor);
    }

    public HashTableOpenAddressing(int mode, int capacity, double loadFactor) {
        this.mode = mode;
        capacity = nextPrime(capacity);
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.table = new Entry[capacity];
        this.previousPrime = previousPrime(capacity - 1);
        this.size = 0;
    }

    private int previousPrime(int number) {
        while (true) {
            if (isPrime(number)) {
                return number;
            }
            number--;
        }
    }

    private int hash2(K key) {
        return (previousPrime - Math.abs((key).hashCode() % previousPrime));
    }

    private int getNextIndex(K key, int offset) {
        if (mode == 1) {
            return Math.abs((hash(key) % capacity) + offset) % capacity;
        } else if (mode == 2) {
            return (int) (Math.abs(((hash(key)) + (Long.valueOf(offset) * offset)))
                    % capacity);
        } else {
            return (int) (Math.abs(((hash(key)) +
                    (Long.valueOf(offset) * hash2(key)))) % capacity);
        }
    }

    public void put(K key, V value) {
        int offset = 0;
        int firstTime = getNextIndex(key, 0), cur = firstTime;
        boolean b = true;
        if (containsKey(key)) {
            while (b == true || firstTime != cur) {
                if (this.table[cur] != null) {
                    if (this.table[cur].getKey().equals(key)) {
                        if (this.table[cur].getActive() == false) {
                            this.size++;
                        }
                        this.table[cur] = new Entry<>(key, value);
                        return;
                    }
                }
                offset++;
                cur = getNextIndex(key, offset);
                b = false;
            }
            return;
        }
        while (b == true || firstTime != cur) {
            if (this.table[cur] != null) {
                if (this.table[cur].getActive() == false ||
                        this.table[cur].getKey().equals(key)) {
                    if (this.table[cur].getActive() == false) {
                        this.size++;
                    }
                    this.table[cur] = new Entry<>(key, value);
                    break;

                }
            } else {
                this.size++;
                this.table[cur] = new Entry<>(key, value);
                break;
            }
            offset++;
            cur = getNextIndex(key, offset);
            b = false;
        }
        if (firstTime == cur && b == false) {
            throw new RuntimeException();
        }
        if (size > loadFactor * capacity) {
            resize();
        }
        return;
    }

    public V get(K key) {
        int offset = 0;
        int firstTime = getNextIndex(key, 0), cur = firstTime;
        boolean b = true;
        while (b == true || firstTime != cur) {
            if (this.table[cur] != null) {
                if (this.table[cur].getKey().equals(key) &&
                        this.table[cur].isActive) {
                    return this.table[cur].getValue();
                }
            } else {
                return null;
            }
            offset++;
            cur = getNextIndex(key, offset);
            b = false;
        }
        if (firstTime == cur && b == false) {
            throw new RuntimeException();
        }
        return null;
    }

    public boolean containsKey(K key) {
        int offset = 0;
        while (offset < capacity) {
            int k = getNextIndex(key, offset);
            if (this.table[k] == null) {
                return false;
            }
            if (this.table[k].getKey().equals(key) && this.table[k].isActive) {
                return true;
            }

//
            offset++;
        }
        return false;
    }

    public boolean remove(K key) {
        int offset = 0;
        if (!containsKey(key)) {
            return false;
        }
        int firstTime = getNextIndex(key, 0), cur = firstTime;
        boolean b = true;
        while (b == true || cur != firstTime) {
            int k = cur;
            if (this.table[k] == null) {
                return false;
            }
            if (this.table[k].getKey().equals(key)) {
                this.table[k].setActive(false);
                this.size--;
                return true;
            }
            offset++;
            cur = getNextIndex(key, offset);
            b = false;
        }
        if (b == false && firstTime == cur) {
            throw new RuntimeException();
            return false;
            return false;
        }
        public int size () {
            return size;
        }
        public boolean isEmpty () {
            return size == 0;
        }

        private int hash (K key){
            return Math.abs(key.hashCode() % this.capacity);
        }
        private boolean isPrime ( int number){
            for (int i = 2; i <= number / 2; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }

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
        previousPrime = previousPrime(capacity - 1);
        Entry<K, V>[] temp = this.table;
        this.table = (Entry<K, V>[]) new Entry[capacity];
        this.size = 0;
        for (int i = 0; i < prevCapacity; i++) {
            if (temp[i] != null) {
                put(temp[i].getKey(), temp[i].getValue());
                if (temp[i].getActive() == false) {
                    remove(temp[i].getKey());
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int index = 0;
        for (Entry<K, V> entry : table) {
            sb.append(index + ": ");
            index++;
            if (entry != null) {
                sb.append(entry.getKey() + "=" + entry.getValue() + ",");
            }
            sb.append(";");
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }

    private class Entry<K, V> {
        private K key;
        private V value;
        private boolean isActive;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            isActive = true;
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

        public boolean getActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }
    }
}