package test;

import task.Task1;
import task.Task2;

public class Solution {
    public static void main(String[] args) {
        //test Task1
//        Integer[] nums= {4,9,10,13,17,17,19,21};
//        int target=17;
//
//        Integer[] nums1= {2,2,5,9,12,13,15,27,33,35,40,40,40,42,43,45,50,56,56,56,56,56,57,63,65,65,70,72};
//        int target1=56;
//
//        Task1 task1=new Task1();
//        Integer[] firstAndLast = task1.getFirstAndLast(nums1, target1);
//        for (Integer integer : firstAndLast) {
//            System.out.print(integer+" ");
//        }
//        System.out.println();

        //test task2
        Task2 task2=new Task2();
        Integer[][] matrix={{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target2=2;
        boolean flag=task2.isExisted(matrix,target2);

        Integer[][] matrix2={{1,4,6,10,15,17,18},{21,24,27,30,33,35,38},{42,43,48,50,57,59,61},{62,65,68,72,75,77,78},{82,86,88,90,93,96,99}};
        int target3=10;
        boolean flag2=task2.isExisted(matrix2,target3);
        System.out.println(flag2);
    }
}
