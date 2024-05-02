package Entity;

//If there is a deleted ID value, put it into this linked list.
public class AvailableID {
    private Integer ID;//available ID number
    private AvailableID next;//next available ID

    //constructor
    public AvailableID(Integer ID) {
        this.ID = ID;
    }

    //getting and setting interface

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public AvailableID getNext() {
        return next;
    }

    public void setNext(AvailableID next) {
        this.next = next;
    }
}
