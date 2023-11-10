package test;

import entity.BST;
import entity.Node;
import task.BSTToHeapTransformer;
import task.HeapBuilder;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {

        //test heapBuilder
        HeapBuilder hp=new HeapBuilder();
        List<Integer> list=new ArrayList<>();
        list.add(5);
        list.add(9);
        list.add(3);
        list.add(10);
        list.add(17);
        list.add(31);
        list.add(8);
        list.add(21);
        list.add(15);
        list.add(65);
        list.add(39);
        list.add(41);

        //create maxHeap
        Node maxNode=hp.buildMaxHeap(list);
        Node minNode=hp.buildMinHeap(list);

        List<Integer> nodeList=new ArrayList<>();
        BST bts=new BST();
        bts.levelTraveral(maxNode,nodeList);
        for (Integer integer : nodeList) {
            System.out.println(integer);
        }

        //test BSTTOHeapTransformer
//        Node node1=new Node(10);
//        Node node2=new Node(33);
//        Node node3=new Node(5);
//        Node node4=new Node(19);
//        Node node5=new Node(29);
//        Node node6=new Node(15);
//        Node node7=new Node(32);
//        Node node8=new Node(28);
//        BST bst=new BST();
//        bst.setRoot(bst.insert(bst.getRoot(),node1));
//        bst.setRoot(bst.insert(bst.getRoot(),node2));
//        bst.setRoot(bst.insert(bst.getRoot(),node3));
//        bst.setRoot(bst.insert(bst.getRoot(),node4));
//        bst.setRoot(bst.insert(bst.getRoot(),node5));
//        bst.setRoot(bst.insert(bst.getRoot(),node6));
//        bst.setRoot(bst.insert(bst.getRoot(),node7));
//        bst.setRoot(bst.insert(bst.getRoot(),node8));
//
//        List<Integer> nodeList=new ArrayList<>();
//        bst.inorderTraversal(bst.getRoot(), nodeList);
//
//        //traversal BST
//        for (Integer integer : nodeList) {
//            System.out.print(integer+" ");
//        }
//        System.out.println();
//
//        List<Node> maxList=new BSTToHeapTransformer().createMaxHeap(bst);
//        List<Node> minList=new BSTToHeapTransformer().bstToMinHeap(bst);
//
//        //traversal bst transfer maxHeap
//        for (Node node : maxList) {
//            System.out.print(node.getData()+" ");
//        }
//        System.out.println();
//
//        //traversal bst transfer minHeap
//        for (Node node : minList) {
//            System.out.print(node.getData()+" ");
//        }
//        System.out.println();
//
   }
}
