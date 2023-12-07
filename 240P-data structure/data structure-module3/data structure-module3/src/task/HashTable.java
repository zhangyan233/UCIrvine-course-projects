package task;

import Entity.Node;

public class HashTable<T> {
    private static Integer initialCapacity = 16;//set default initialSize of hashtable
    private Node[] entries;//use a built-in Array to put entries
    private Integer size;// the number of elements in a hashtable

    //no point out the capacity of hashtable,use the initialCapacity
    public HashTable() {
        this.entries = new Node[initialCapacity];
        this.size = 0;
    }

    //set the capacity of hashtable
    public HashTable(Integer capacity) {
        this.entries = new Node[capacity];
        this.size = 0;
    }

    //calculate the hash
    public Integer hash(String obj) {
        //the solution of calculate hashcode cited by course material

        int h = 0;
        for (int i = 0; i < obj.length(); i++) {
            h = 31 * h + obj.charAt(i);
        }

        //compression function cited by the division method from course material
        //Data processing to avoid negative numbers due to overflow
        return (h & Integer.MAX_VALUE) % entries.length;
    }

    //insert an entry to hashtable
    public void insert(String key, T value) {
        //calculate the hashcode of the key
        int index = hash(key);
        Node newNode = new Node(key, value);


        if (entries[index] == null) {
            //The first position is empty
            entries[index] = newNode;
            size++;
        } else {
            //The first position is occupied, we need do two operation:
            //1. find whether there is the same key in linked list
            //2. if not, we need find the end of list, and insert the entry
            Node start = entries[index];
            while (start != null) {
                if (start.getKey() == key) {
                    start.setValue(value);
                    return;
                }

                if (start.getNext() == null) {
                    start.setNext(newNode);
                    size++;
                    return;
                }
                start = start.getNext();
            }
        }
    }

    //find whether hashtable contains the key
    public boolean contains(String key) {
        //calculate the hashcode of key
        int index = hash(key);

        if (entries[index] == null) {
            return false;
        }

        Node start = entries[index];
        while (start != null) {
            if (start.getKey().equals(key)) {
                return true;
            }
            start = start.getNext();
        }
        return false;
    }

    public Integer size() {
        return size;
    }


    //getter and setter
    public static Integer getInitialCapacity() {
        return initialCapacity;
    }

    public static void setInitialCapacity(Integer initialCapacity) {
        HashTable.initialCapacity = initialCapacity;
    }

    public Node[] getEntries() {
        return entries;
    }

    public void setEntries(Node[] entries) {
        this.entries = entries;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
