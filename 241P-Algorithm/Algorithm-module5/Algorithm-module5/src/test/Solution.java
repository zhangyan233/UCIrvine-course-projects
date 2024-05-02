package test;

import task.Task1;
import task.Task2;

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        //test task1
//        Task1 task1=new Task1();
//        String s1="ab";
//        String s2="eidbaooo";
//        boolean check = task1.Check(s1, s2);
//        System.out.println(check);
//
//        s2="eidboaoo";
//        check=task1.Check(s1,s2);
//        System.out.println(check);

        //test task2
        //initial
        Task2 task2=new Task2();
        //int[] input={1,1,1,1,1,1,1,1};
        int[] input={1,2,3,4,5,6,7,8};
        ArrayList<Integer> possibleAns=new ArrayList<>();
        boolean[] isVisited=new boolean[9];
        ArrayList<Integer> placement=new ArrayList<>();

        task2.findSuitablePostion(1,possibleAns,0,isVisited,placement,input);

        int ans=Integer.MAX_VALUE;
        for (Integer possibleAn : possibleAns) {
            ans=Math.min(ans,possibleAn);
        }
        System.out.println(ans);
    }
}
