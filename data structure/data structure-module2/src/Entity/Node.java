package Entity;

public class Node {
    private Object data;//element in stack
    private Node next;//next node in linked list

    //constructor
    public Node(Object data) {
        this.data = data;
    }

    //getter and setter

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
