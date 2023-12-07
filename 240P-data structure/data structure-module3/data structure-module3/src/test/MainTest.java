package test;

import Entity.Node;
import task.HashTable;
import task.ReadFile;

import java.io.File;
import java.io.IOException;

public class MainTest {
    public static void main(String[] args){

        //        test hashtable

//        HashTable hashTable=new HashTable();
//        hashTable.insert("1",10);
//        hashTable.insert("2",10);
//        hashTable.insert("2",15);
//        hashTable.insert("4",20);
//        hashTable.insert("5",25);
//        hashTable.insert("6",25);
//        hashTable.insert("7",30);
//        hashTable.insert("8",30);
//        hashTable.insert("18",40);
//        hashTable.insert("21",70);
//        hashTable.insert("25",60);
//        hashTable.insert("37",90);
//        hashTable.insert("48",40);
//
//        //traverse the element in the hashtable
//        for(int i=0;i<hashTable.getEntries().length;i++){
//            if(hashTable.getEntries()[i]!=null) {
//                //start is first element in a hashcode list
//                Node start = hashTable.getEntries()[i];
//                while (start != null) {
//                    System.out.print(start.getKey() + " " + start.getValue() + "    ");
//                    start = start.getNext();
//                }
//                System.out.println();
//            }
//        }
//        System.out.println(hashTable.size());

        //test readfile
        Integer ans=0;
        ReadFile readFile=new ReadFile();
        try {
            ans = readFile.ReadFile(new File("src/task/pride-and-prejudice.txt"));//use the relative address of file
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(ans);


    }
}
