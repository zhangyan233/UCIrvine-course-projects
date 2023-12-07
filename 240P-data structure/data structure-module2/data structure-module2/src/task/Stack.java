package task;

import Entity.Node;

//use linked list to implement stack
public class Stack {
    private Integer size;//the number of elements in stack
    private Node root;//the first node of linked list

    //initial stack
    public Stack() {
        this.size=0;
        this.root=null;
    }

    //add element in a stack
    public void push(Object obj){
        Node newNode=new Node(obj);

        //whether stack is empty
        //yes:assign newNode to root
        //no: assign newNode before root
        if(root==null){
            root=newNode;
        }else{
            Node temp=root;
            root=newNode;
            newNode.setNext(temp);
        }
        size++;
    }

    //delete the first element of a stack
    public Object pop() throws Exception {
        Object popData;
        //whether stack is empty,yes: throw an exception; no: continue
        if(root==null){
            throw new Exception("stack is empty");
        }else{
            popData=root.getData();
            Node temp=root;
            root=root.getNext();
            temp=null;
            size--;
        }
        return popData;
    }

    //get the first element of a stack
    public Object peek() throws Exception {
        Object topData;
        //whether stack is empty,yes: throw an exception; no: continue
        if(root==null){
            throw new Exception("stack is empty");
        }else{
            topData=root.getData();
        }
        return topData;
    }

    public Integer size(){
        return size;
    }
}
