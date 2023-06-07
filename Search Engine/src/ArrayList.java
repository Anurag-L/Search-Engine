import java.lang.reflect.Array;
public class ArrayList<E extends Comparable> extends List<E> {
    private int size;
    private int capacity;
    private Object[] ls;
    element
    public ArrayList(){
        ls = new Object[10];
        this.capacity=10;
        this.size=0;
    }
    holding x element
    public ArrayList(int capacity){
        ls = new Object[capacity];
        this.capacity=capacity;
        this.size=0;
    }
    public int size(){
        return this.size;
    }
    public E get(int index) throws IndexOutOfBoundsException{
        if(index >= this.size || index<0){
            throw new IndexOutOfBoundsException();
        }
        return (E) this.ls[index];
    }
    arraylist, resizes to double capacity if no space
    public void add(E value){
        if(this.size==this.capacity){
            Object[] temp = new Object[2*capacity];
            for(int i=0;i<size;i++){
                temp[i]=ls[i];
            }
            temp[size]=(E)value;
            this.size++;
            this.capacity=2*this.capacity;
            ls=temp;
        } else{
            this.ls[this.size]=(E)value;
            this.size++;
        }
    }
    public void delete(int index) throws IndexOutOfBoundsException{
        if(index<0 || index>=this.size)throw new IndexOutOfBoundsException();
        for(int i=index+1;i<this.size;i++){
            ls[i-1]=ls[i];
        }
        this.size--;
    }
    public int search(E value) {
        if (value instanceof Object) {
            for (int i = 0; i < this.size; i++) {
                    if (value == (E) ls[i]) {
                        return i;
                    }
                }
        }
        return -1;

    }
    public boolean equals(Object o){
        if(o instanceof ArrayList<?>){
            ArrayList x=(ArrayList) o;
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
        for(int i = 0; i < this.size; i++){
            ret += i + ": "+ this.ls[i] + "\n";
        }
        return ret;
    }
}