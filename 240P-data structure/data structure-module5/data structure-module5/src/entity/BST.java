package entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BST {
    private Node root;

    //default constructor
    public BST() {
        this.root = null;
    }
    public Node getRoot() {
        return root;
    }
    public void setRoot(Node root) {
        this.root = root;
    }

    //insert a node in bst
    public Node insert(Node root,Node node){
        //if root is null, insert node at root; if not, find the position of node
        if(root==null){
            root=node;
            return root;
        }else if(root.getData()>node.getData()){//if node smaller than root, it should be placed at left
            root.setLeft(insert(root.getLeft(),node));
        }else{//if node larger than root, it should be placed at right
            root.setRight(insert(root.getRight(),node));
        }
        return root;
    }

    //delete data from bst
    public Node delete(Node root, Integer data){
        //if root'data is data, delete root; if not, find the position of data
        if(root==null){
            return null;
        }else if(root.getData()>data){//data smaller than data, it most be the left of data
            root.setLeft(delete(root.getLeft(),data));
        }else if(root.getData()<data){//data larger than data, it most be the right of data
            root.setRight(delete(root.getRight(),data));
        }else if(root.getData()==data){
            //if root has left and right point, should find the maxElement in the left to replace root
            if(root.getLeft()!=null&&root.getRight()!=null){
                //find the maxElement in leftSubTree
                Node maxElement=maxEle(root.getLeft());

                //assign maxElement to root
                root.setData(maxElement.getData());

                //delete maxElement
                root.setLeft(delete(root.getLeft(),maxElement.getData()));
                return root;
            }else if(root.getLeft()!=null&&root.getRight()==null){
                return root.getLeft();
            }else if(root.getRight()!=null&&root.getLeft()==null){
                return root.getRight();
            }else{
                return null;
            }
        }
        return root;
    }

    //find the max element in root
    public Node maxEle(Node root){
        if(root.getRight()!=null){
            return maxEle(root.getRight());
        }else{
            return root;
        }
    }

    //level traversal
    public void levelTraveral(Node root, List<Integer> nodeList) {
        if (root == null) {
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node node = q.poll();
            nodeList.add(node.getData());

            //if node has left or right, put them in queue
            if (node.getLeft() != null) {
                q.add(node.getLeft());
            }
            if (node.getRight() != null) {
                q.add(node.getRight());
            }
        }
    }

    //inorder traversal
    public void inorderTraversal(Node root, List<Integer> nodeList){
        if(root==null){
            return;
        }
        inorderTraversal(root.getLeft(),nodeList);
        nodeList.add(root.getData());
        inorderTraversal(root.getRight(),nodeList);

    }


}
