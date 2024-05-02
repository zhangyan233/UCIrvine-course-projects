package task;

import Entity.Node;

public class StackWithTwoQs {
    private Queue q1;
    private Queue q2;

    //initially
    public StackWithTwoQs() {
        q1=new Queue();
        q2=new Queue();
    }

    //add an element in a stack
    public void push(Object obj) throws Exception {
        //make q1 empty, and elements from q1 are transfered to q2 orderly,ex: q1:1,2→q1: ;q2:1,2
        while(q1.size()!=0){
            q2.enqueue(q1.dequeue());
        }

        //obj become the first element of ex:q1:obj; q2:1,2， make sure the lastest one is in the top
        q1.enqueue(obj);

        //make the former elements orderly return q1, ex:q1: obj,1,2 q2:
        while(q2.size()!=0){
            q1.enqueue(q2.dequeue());
        }
        //actually, elements are placed in q1
    }

    //delete the end of stack
    public Object pop() throws Exception {
        Object res;
        if(q1.size()==0){
            throw new Exception("stack is empty");
        }else{
            res=q1.dequeue();
        }
        return res;
    }

    //the top element of a stack
    public Object peek() throws Exception {
        Object res;
        if(q1.size()==0){
            throw new Exception("stack is empty");
        }else{
            res=q1.poll();
        }
        return res;
    }

    public Integer size(){
        return q1.size();
    }
}
