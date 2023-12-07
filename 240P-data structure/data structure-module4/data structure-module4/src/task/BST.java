package task;

import entity.Student;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BST {
    private Student root;//root pointer

    //default constructor
    public BST() {
        this.root = null;
    }

    //getter and setter
    public Student getRoot() {
        return root;
    }

    public void setRoot(Student root) {
        this.root = root;
    }

    //insert a student to bst
    public Student insert(Student root, Integer studentNumber, String studentLastName, String department, String program, Integer year) {
        //create new Student
        Student newStudent = new Student(studentNumber, studentLastName, department, program, year);

        if (root == null) {
            root = newStudent;
            return root;
        } else if (root.getLastName().compareTo(studentLastName) > 0) {
            //if lastName of newStudent smaller than root, the newStudent must be placed at leftchild of root
            root.setLeft(insert(root.getLeft(), studentNumber, studentLastName, department, program, year));
        } else {
            //if lastName of newStudent larger than root, the newStudent must be placed at rightchild of root
            root.setRight(insert(root.getRight(), studentNumber, studentLastName, department, program, year));
        }

        return root;
    }

    //delete a student from root
    public Student delete(Student root, String StudentLastName) {

        //find the location of the student in bst
        if (root == null) {
            return null;
        } else if (root.getLastName().compareTo(StudentLastName) > 0) {
            root.setLeft(delete(root.getLeft(), StudentLastName));
        } else if (root.getLastName().compareTo(StudentLastName) < 0) {
            root.setRight(delete(root.getRight(), StudentLastName));
        } else if (root.getLastName().equals(StudentLastName)) {//get the position of the student
            //if root has left and right point, should find the maxStudent in the left to replace root
            if (root.getLeft() != null && root.getRight() != null) {
                //find the maxLastName in leftSubTree
                Student maxStudent = maxStu(root.getLeft());

                //assign maxStudent to root
                root.setIDNumber(maxStudent.getIDNumber());
                root.setLastName(maxStudent.getLastName());
                root.setHomeDepartment(maxStudent.getHomeDepartment());
                root.setProgramName(maxStudent.getProgramName());
                root.setYear(maxStudent.getYear());

                //delete maxStudent
                root.setLeft(delete(root.getLeft(), maxStudent.getLastName()));
                return root;
            } else if (root.getLeft() != null && root.getRight() == null) {
                return root.getLeft();
            } else if (root.getRight() != null && root.getLeft() == null) {
                return root.getRight();
            } else {
                return null;
            }
        }
        return root;
    }

    //find the max element in root
    public Student maxStu(Student root) {
        if (root.getRight() != null) {
            return maxStu(root.getRight());
        } else {
            return root;
        }
    }

    //inorder traversal
    public void inorder(Student root, StringBuilder studentList) {
        if (root == null) {
            return;
        }
        inorder(root.getLeft(), studentList);
        studentList.append(root.toString());
        inorder(root.getRight(), studentList);
    }

    //preorder traversal
    public void preorder(Student root, StringBuilder studentList) {
        if (root == null) {
            return;
        }
        studentList.append(root.toString());
        preorder(root.getLeft(), studentList);
        preorder(root.getRight(), studentList);
    }

    //postorder traversal
    public void postorder(Student root, StringBuilder studentList) {
        if (root == null) {
            return;
        }
        postorder(root.getLeft(), studentList);
        postorder(root.getRight(), studentList);
        studentList.append(root.toString());
    }

    public void levelTraveral(Student root, StringBuilder studentList) {
        if (root == null) {
            return;
        }

        Queue<Student> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Student node = q.peek();
            q.poll();
            studentList.append(node.toString());

            //if node has left or right, put them in queue
            if (node.getLeft() != null) {
                q.add(node.getLeft());
            }
            if (node.getRight() != null) {
                q.add(node.getRight());
            }
        }


    }
}
