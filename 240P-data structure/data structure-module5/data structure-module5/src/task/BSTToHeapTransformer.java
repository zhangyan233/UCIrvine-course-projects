package task;

import entity.BST;
import entity.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BSTToHeapTransformer {
    HeapBuilder bp;

    public List<Node> createMaxHeap(BST bts) {
        //Initial arr
        Integer currentSize = 0;
        ArrayList<Node> arr = new ArrayList<>();

        Queue<Node> q = new LinkedList<>();
        q.add(bts.getRoot());
        while (!q.isEmpty()) {
            Node node = q.poll();
            int i = currentSize;
            arr.add(node);
            currentSize++;

            //compare the data of node and the data of its parent, if it larger than its parent, they need swap
            while (i != 0 && arr.get(i).getData() > arr.get((i - 1) / 2).getData()) {
                Node temp = arr.get(i);
                arr.set(i, arr.get((i - 1) / 2));
                arr.set((i - 1) / 2, temp);
                i=(i-1)/2;
            }

            if (node.getLeft() != null) {
                q.add(node.getLeft());
            }

            if (node.getRight() != null) {
                q.add(node.getRight());
            }
        }

        return arr;
    }

    public List<Node> bstToMinHeap(BST bst) {
        //Initial arr
        Integer currentSize = 0;
        ArrayList<Node> arr = new ArrayList<>();

        Queue<Node> q = new LinkedList<>();
        q.add(bst.getRoot());
        while (!q.isEmpty()) {
            Node node = q.poll();
            int i = currentSize;
            arr.add(node);
            currentSize++;

            ////compare the data of node and the data of its parent, if it smaller than its parent, they need swap
            while (i != 0 && arr.get(i).getData() < arr.get((i - 1) / 2).getData()) {
                Node temp = arr.get(i);
                arr.set(i, arr.get((i - 1) / 2));
                arr.set((i - 1) / 2, temp);
                i=(i-1)/2;
            }

            if (node.getLeft() != null) {
                q.add(node.getLeft());
            }

            if (node.getRight() != null) {
                q.add(node.getRight());
            }
        }
        return arr;
    }


}
