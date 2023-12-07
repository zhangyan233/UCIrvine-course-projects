package task1;

import java.util.Random;

public class QuickSort {
    public void quickSort(char[] ch,int start,int end){
        if(start>=end){
            return;
        }
        //produce target randomly, +1 to avoid choose the start to make the worse case
        Random r=new Random();
        int randomIndex=r.nextInt(end-start)+1+start;

        //swap first element in ch and target
        char temp=ch[start];
        ch[start]=ch[randomIndex];
        ch[randomIndex]=temp;

        int left=start;
        int right=end;

        while(left<right){
            while(left<right&&ch[right]>=ch[start]){
                right-=1;
            }

            while(left<right&&ch[left]<=ch[start]){
                left+=1;
            }

            //swap ch[left],ch[right],in order to make the right index is larger than ch[start],left index is smaller than ch[start]
            if(left<right){
                char c=ch[left];
                ch[left]=ch[right];
                ch[right]=c;
            }
        }

        //place the target in the suitable index
        char help=ch[left];
        ch[left]=ch[start];
        ch[start]=help;

        //continue to sort in two remain arrays
        quickSort(ch,start,left-1);
        quickSort(ch,left+1,end);
    }
}
