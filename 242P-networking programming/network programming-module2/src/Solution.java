import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Solution {
    public static void main(String[] args) {

        ExecutorService exec= Executors.newFixedThreadPool(20);//set the number of threadPool

        for(String filename:args) {// iterates through all the files and directories listed on the command line
            //System.out.println(filename);
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    //initial reader
                    BufferedReader bf = null;
                    int count = 0;//counter

                    try {
                        bf = new BufferedReader(new FileReader(filename));
                        String read = bf.readLine();
                        while (read != null) {
                            count++;//read a new line from this file
                            read = bf.readLine();//continue to read;
                        }
                    } catch (Exception e) {
                        System.out.println("Error: "+e.getMessage());

                    }
                    System.out.println(filename + ": " + count);
                }
            });
        }

        exec.shutdown();//finish reading all files and then close the thread
    }
}
