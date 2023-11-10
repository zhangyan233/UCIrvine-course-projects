package task;

import java.util.ArrayList;
//use binary search to solve the problem
public class Task1 {
    public Integer[] getFirstAndLast(Integer[] nums,int target){

        int length=nums.length;
        //initial the result
        Integer[] ans={-1,-1};

        //special case
        if(length==0){
            return ans;
        }


        //search the first index of target
        int left=0;
        int right=length-1;
        while(left<right){
            int mid=left+(right-left)/2;
            //since nums is sorted, if nums[mid]>=target, the leftIndex of target must not appear in the right of mid
            if(nums[mid]>=target){
                right=mid;
            }else{
                //if(nums[mid]<target),the leftIndex of target must appear in the right of mid
                left=mid+1;
            }
        }

        //the array without target
        if(nums[left]!=target){
            return ans;
        }

        ans[0]=left;
        //find the last index of array
        left=0;
        right=length-1;
        while(left<right){
            int mid=left+(right-left+1)/2;
            //if nums[mid]<=target, the rightIndex of target must not appear in the left of mid
            if(nums[mid]<=target){
                left=mid;
            }else{
                //if nums[mid]>target, the rightIndex of target must appear in the left of mid
                right=mid-1;
            }
        }
        ans[1]=left;
        return ans;
    }
}
