package task1;

import java.util.ArrayList;

public class MergeSort {
    public char[] MergeSortString(char[] ch){
        mergesort(ch,0,ch.length-1);
        return ch;
    }

    public void mergesort(char[] ch,int left,int right){
        if(left<right){
            //find the position of index to split the ch to two parts
            int mid=left+(right-left)/2;

            //each of parts continues to mergesort
            mergesort(ch,left,mid);
            mergesort(ch,mid+1,right);

            //merge two arrays
            merge(ch,left,mid,right);
        }
    }

    public void merge(char[] ch, int left, int mid,int right){
        //the length of two parts
        int lenLeft=mid-left+1;
        int lenRight=right-mid;

        //initial two substr to place elements in two arrays
        ArrayList<Character> leftSubstr=new ArrayList<>();
        ArrayList<Character> rightSubstr=new ArrayList<>();

        for(int i=0;i<lenLeft;i++){
            leftSubstr.add(ch[i+left]);
        }

        for(int i=0;i<lenRight;i++){
            rightSubstr.add(ch[mid+1+i]);
        }

        //compare char of two arrays, the smaller one will be put in the origin array
        int i=0;//the first index of left
        int j=0;//the first index of right
        int index=left;//the first index need to be sorted
        while(i<lenLeft&&j<lenRight){
            char leftLetter=leftSubstr.get(i);
            char rightLetter=rightSubstr.get(j);

            if(leftLetter-'0'<=rightLetter-'0'){
                ch[index]=leftLetter;
                i++;
            }else{
                ch[index]=rightLetter;
                j++;
            }
            index++;
        }

        //consider two different situations of remain String
        while(i<lenLeft){
            ch[index]=leftSubstr.get(i);
            i++;
            index++;
        }
        while(j<lenRight){
            ch[index]=rightSubstr.get(j);
            j++;
            index++;
        }
    }
}
