package test;

import task1.MergeSort;
import task1.QuickSort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        MergeSort mergeSort=new MergeSort();
        QuickSort quickSort=new QuickSort();

//        String[] strings = {"bucket","rat","mango","tango","ogtan","tar"};
        String[] strings = {"apple","tan","bear","ant","erab","banana","add","tissue","issuet","nta"};
        //String[] strings = {"apply","ppaly","omit","ret","ter","play","ypla","at","she","hes","love"};
        HashMap<String, ArrayList<String>> mp=new HashMap<>();

        for (String string : strings) {
            char[] ch=string.toCharArray();

            //test mergesort
            char[] newChar=mergeSort.MergeSortString(ch);

            //test quicksort
//            int len=ch.length;
//            quickSort.quickSort(ch, 0, len - 1);


            //produce new String
            StringBuilder sb=new StringBuilder();
            for (char c : ch) {
                sb.append(c);
            }
            String newStr = sb.toString();

            ArrayList<String> stringList=new ArrayList<>();

            //judge whether the newStr is in the mp
            if(mp.containsKey(newStr)){
                stringList=mp.get(newStr);
            }

            stringList.add(string);
            mp.put(newStr,stringList);
        }

        //words have the same sort of letter will be put in the common array
        ArrayList<ArrayList<String>> ans=new ArrayList<>();
        for(Map.Entry<String,ArrayList<String>> entry:mp.entrySet()){
            ArrayList<String> value=entry.getValue();
            ans.add(value);
        }

        for (int i = 0; i < ans.size(); i++) {
            for (String s : ans.get(i)) {
                System.out.print(s+' ');
            }
            System.out.println();
        }

    }
}
