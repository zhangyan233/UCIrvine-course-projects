package Entity;

public class Node<T> {
    private String key;//key
    private T value;//value
    private Node next;//linked list of same hash

    //constructor
    public Node(String key, T value) {
        this.key = key;
        this.value = value;
        this.next=null;
    }

    //getter and setter
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
