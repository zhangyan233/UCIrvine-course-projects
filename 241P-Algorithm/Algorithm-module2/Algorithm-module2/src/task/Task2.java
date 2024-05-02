package task;

public class Task2 {
    public boolean isExisted(Integer[][] matrix,int target){
        int m=matrix.length;
        int n=matrix[0].length;

        //special case
        if(m==0||n==0){
            return false;
        }

        int left=0;
        //consider the two-dimensional array as a one-dimensional array
        int right=m*n-1;
        while(left<right){
            int mid=left+(right-left)/2;
            //Restore to the corresponding coordinate value of the two-dimensional array
            int compare=matrix[mid/n][mid%n];

            if(compare==target){
                return true;
            }else if(compare>target){//the target must not at the right of mid
                right=mid-1;
            }else{//the target must at the right of mid
                left=mid+1;
            }
        }

        //The value that ends the loop may also be the index of target
        if(matrix[left/n][left%n]==target){
            return true;
        }

        return false;
    }
}
