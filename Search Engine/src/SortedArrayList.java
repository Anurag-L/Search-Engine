import java.lang.reflect.Array;
public class SortedArrayList<E extends Comparable> extends List<E> {
    private int size;
    private int capacity;
    private Object[] ls;
    element
    public SortedArrayList(){
        this.size=0;
        this.capacity=10;
        this.ls=new Object[10];
    }
    public SortedArrayList(Class<E> c, int capacity){
        this.size=0;
        this.capacity=capacity;
        this.ls= new Object[capacity];
    }
    public int size(){
        return this.size;
    }
    public E get(int index) throws IndexOutOfBoundsException{
        if(index >= this.size){
            throw new IndexOutOfBoundsException();
        }
        return (E) this.ls[index];
    }
    public void add(E value) {
        if(this.size==this.capacity){
            Object[] x= new Object[2*capacity];
            this.capacity=2*this.capacity;
            for(int i=0;i<this.size;i++){
                x[i]=this.ls[i];
            }
            this.ls=x; }
        int i=0;
        while(i<this.size && value.compareTo(this.ls[i])>=0){
            i++; }
        int j=this.size-1;
        while(j>=i && j>=0){
            this.ls[j+1] = this.ls[j];
            j--; }
        this.ls[i]=value;
        this.size++;
    }

    public void delete(int index) throws IndexOutOfBoundsException{
        boolean b=true;
        if(index>=this.size || index<0){
            throw new IndexOutOfBoundsException();
        }
        for(int i=index;i<this.size-1;i++){
            this.ls[i]=this.ls[i+1];
        }
        this.size--;
    }

    public int search(E value){
        int low = 0;
        int high = this.size - 1;
        int mid = 0;
        while(low <= high){
            mid  = (low + (high - low)/2);
            if(this.ls[mid].equals(value)){
                return mid; }
            if(value.compareTo(this.ls[mid]) > 0){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1; }

    public boolean equals(Object o){
        if(o instanceof SortedArrayList<?>){
            SortedArrayList x=(SortedArrayList) o;
            if(x.size()!=this.size){
                return false;
            }
            for(int i=0;i<this.size;i++){
                if(this.ls[i] != x.get(i)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    public String toString(){
        String ret = "";
        for(int i = 0; i < this.capacity; i++){
            ret += i + ": "+ this.ls[i] + "\n";
        }
        return ret; }
}