package Entity;

// user's information
public class User {
    private Integer id;// unique Id for user
    private String name;
    private String address;
    private String SSN;// social security
    private Integer initialAmount;// initial deposit money;
    private User next;// next user

    // constructor for User
    public User(String name,String address, String SSN, Integer initialAmount) {
        this.name=name;
        this.address = address;
        this.SSN = SSN;
        this.initialAmount = initialAmount;
    }

    //getter and setter interface for User
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public Integer getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(Integer initialAmount) {
        this.initialAmount = initialAmount;
    }

    public User getNext() {
        return next;
    }

    public void setNext(User next) {
        this.next = next;
    }

}
