package test;

import task.OperateString;
import task.Queue;
import task.Stack;
import task.StackWithTwoQs;

import javax.swing.*;

public class MainTest {
    public static void main(String[] args) throws Exception {
        //test Stack
        Stack stackTest1=new Stack();
        stackTest1.push(1);
        stackTest1.push(2);
        stackTest1.push(5);
        stackTest1.push(3);
        stackTest1.push(10);
        stackTest1.push(7);

//        while(stackTest1.size()>0){
//            System.out.println(stackTest1.peek());
//            stackTest1.pop();
//        }

//        while(stackTest1.size()>=0){
//            System.out.println(stackTest1.peek());
//            stackTest1.pop();
//        }

        //test operateString
//        OperateString ops=new OperateString();
//        String str="72/9-   3+15/5*3-1+90+64/8*2";//valid
//        String str= "10 + 20 * 2";//valid
//        String str="foo / 30 + 7";//invalid
//
//        Integer res = ops.CalculateString(str);
//        System.out.println(res);

//        //test Queue
//        Queue queueTask=new Queue();
//        queueTask.enqueue('a');
//        queueTask.enqueue('b');
//        queueTask.enqueue('c');
//        queueTask.enqueue('d');
//        queueTask.enqueue('e');
//        queueTask.enqueue('f');
//
//        while(queueTask.size()>0){
//            System.out.println(queueTask.poll());
//            queueTask.dequeue();
//        }

//        //test stackWithTwoQs
        StackWithTwoQs swt=new StackWithTwoQs();
        swt.push('1');
        swt.push('2');
        swt.push('3');
        swt.push('4');
        swt.push('5');
        swt.push('6');
        swt.push('7');

        while(swt.size()>0){
            System.out.println(swt.peek());
            swt.pop();
        }


    }
}
