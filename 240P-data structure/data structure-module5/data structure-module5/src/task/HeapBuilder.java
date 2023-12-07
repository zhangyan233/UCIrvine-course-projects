package task;

import entity.Node;

import java.util.List;

public class HeapBuilder {

    //create MaxHeap
    public Node buildMaxHeap(List<Integer> values){
        //the last nonleaf Node
        int currentSize=(values.size()-1)/2;

        //from the last nonleaf Node to heapify
        for(int i=currentSize;i>=0;i--){
            maxHeapify(values,values.size(),i);
        }

        Node root=new Node(values.get(0));
        root=createList(root,0,values);
        return root;
    }

    //cite: https://www.geeksforgeeks.org/building-heap-from-array/
    public void maxHeapify(List<Integer> values, int size, int i) {
        //regard the i as root
        int largest=i;
        int leftIndex=i*2+1;
        int rightIndex=i*2+2;

        //find the largest one in i,leftIndex,rightIndex
        if(leftIndex<size&&values.get(leftIndex)>values.get(largest)){
            largest=leftIndex;
        }
        if(rightIndex<size&&values.get(rightIndex)>values.get(largest)){
            largest=rightIndex;
        }

        //if largest is not i, swap them
        if(largest!=i){
            int temp= values.get(i);
            values.set(i,values.get(largest));
            values.set(largest,temp);

            //continue heapify until find the location of this element
            maxHeapify(values,size,largest);
        }
    }


    //create Minheap
    public Node buildMinHeap(List<Integer> values){
        //the last nonleaf node
        int currentSize=(values.size()-1)/2;

        //from the last nonleaf Node to heapify
        for(int i=currentSize;i>=0;i--){
            minHeapify(values,values.size(),i);
        }

        Node root=new Node(values.get(0));
        root=createList(root,0,values);
        return root;
    }

    private void minHeapify(List<Integer> values, int size, int i) {
        //regard the i as root
        int smallest =i;
        int leftIndex=i*2+1;
        int rightIndex=i*2+2;

        //find the largest one in i,leftIndex,rightIndex
        if(leftIndex<size&&values.get(leftIndex)<values.get(smallest)){
            smallest =leftIndex;
        }
        if(rightIndex<size&&values.get(rightIndex)<values.get(smallest)){
            smallest =rightIndex;
        }

        //if largest is not i, swap them
        if(smallest !=i){
            int temp= values.get(i);
            values.set(i,values.get(smallest));
            values.set(smallest,temp);

            //continue heapify until find the location of this element
            minHeapify(values,size, smallest);
        }

    }

    public Node createList(Node root, int index,List<Integer> values){
        int leftIndex=index*2+1;
        int rightIndex=index*2+2;
        if(leftIndex< values.size()){
            root.setLeft(new Node(values.get(leftIndex)));
            createList(root.getLeft(),leftIndex,values);
        }
        if(rightIndex< values.size()){
            root.setRight(new Node(values.get(rightIndex)));
            createList(root.getRight(),rightIndex,values);
        }
        return root;
    }
}
