package task;

import Entity.Node;

public class Queue {
    private Integer size;//the size of queue
    private Node head;//the first of queue
    private Node rear;// the end of queue

    //initial the queue
    public Queue() {
        size=0;
        head=null;
        rear=null;
    }

    //add elements to the end of queue
    public void enqueue(Object obj){
        Node newNode=new Node(obj);

        //whether the head is null
        if(head==null){
            head=newNode;
        }else{
            rear.setNext(newNode);
        }

        //change the rear
        rear=newNode;
        size++;
    }

    //delete the first element in a queue
    public Object dequeue() throws Exception {
        Object topData;

        //whether the head is null
        if(head==null){
            throw new Exception("queue is null");
        }else{
            //delete the head node of a queue
            topData=head.getData();
            Node temp=head;
            head=head.getNext();
            temp=null;

            //judge whether there is no element in a queue, if so, rear is null
            if(head==null){
                rear=null;
            }
            size--;
        }
        return topData;
    }

    //get the first element
    public Object poll() throws Exception {
        Object topData;
        //whether the head is null
        if(head==null){
            throw new Exception("queue is null");
        }else{
            topData=head.getData();
        }
        return topData;
    }

    public Integer size(){
        return this.size;
    }

}
