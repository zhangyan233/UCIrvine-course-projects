package task;

import java.util.ArrayList;

public class Task2 {
    //check whether number i can be placed in the position
    //i:number i chess; position: want to put i in this,
    //placement: before put i, the location of other chess
    private boolean checkAdded(int i, int position, ArrayList<Integer> placement) {
        //element in the first colomn doesn't need to worry about whether there is an element in diagonal
        if (i == 1) {
            return true;
        }

        //if(abs(i-beforechess)==abs(position-placement[beforechess]), it means they can attack each other by diagnose
        for (int i1 = 1; i1 < i; i1++) {
            if(Math.abs(i-i1)==Math.abs(position-placement.get(i1-1))){
                return false;
            }
            
        }

        return true;
    }

    //i:number i chess;
    //possibleAns: place all possible ans;
    //movement: before moving i the sum of movement
    //isVisited: whether the position has been placed a chess;
    //placement: current position for chess;
    //input: original position for chess
    public void findSuitablePostion(int i, ArrayList<Integer> possibleAns, int movement, boolean[] isVisited, ArrayList<Integer> placement, int[] input) {
        //if placement is placed 8 elements, all check have been moved and satisfy need, put movement in the arr
        if (placement.size() == 8) {
            possibleAns.add(movement);
            return;
        }

        //if not, find the position to put this element
        for (int position = 1; position <= 8; position++) {
            if (isVisited[position] == false) {
                //check whether element i can be placed in this position
                boolean flag = checkAdded(i, position, placement);

                if (flag) {
                    //continue,make this position visited, add movement, and record the position of i in placement
                    isVisited[position] = true;
                    movement += (input[i-1] == position ? 0 : 1);
                    placement.add(position);
                    findSuitablePostion(i + 1, possibleAns, movement, isVisited, placement, input);

                    //Backtracking to find all possible answer
                    placement.remove(i-1);
                    movement -= (input[i-1] == position ? 0 : 1);
                    isVisited[position] = false;
                }
            }
        }

        return;
    }
}
