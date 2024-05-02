package task;

import java.util.ArrayList;
import java.util.List;

public class Task1 {
    private boolean compareTwoList(int[] I1,int[] I2){
        for (int i = 0; i < 255; i++) {
            if(I1[i]!=I2[i]){
                return false;
            }
        }
        return true;
    }


    public boolean Check(String s1,String s2){
        int len1=s1.length();
        int len2=s2.length();

        //special case
        if(len1>len2){
            return false;
        }

        //create a list to displace the time of every character in s1
        int[] freqInS1=new int[255];
        for(int i=0;i<s1.length();i++){
            char ch=s1.charAt(i);
            freqInS1[ch]++;
        }


        //create a list to displace the time of every character in substring of s2 whose length is same as s1
        int[] freqInS2=new int[255];

        //i: startIndex of substring of s2 whose length is same as s1
        int i=0;

        //j:endIndex of substring of s2 whose length is same as s1
        int j=0;

        while(j<len2){
            //make s2[j] to freqInS2
            freqInS2[s2.charAt(j)]++;

            //if substring' length smaller than s2, continue to add
            if(j-i+1<len1) {
                j++;
            }else if(j-i+1==len1){//if substring' length is equal with s2, compare with it, if equal, return true
                boolean flag=compareTwoList(freqInS1,freqInS2);
                if(flag){
                    return true;
                }
                //if not delete the first one of substring,change startIndex i of substring, and add s2[j+1], to make sure the substring'length is equal wth s1
                freqInS2[s2.charAt(i)]--;
                i++;
                j++;
            }
        }

        //if go through the loop, the function doesn't return true, s1's permutations isn't the substring of s2.
        return false;

    }

}
