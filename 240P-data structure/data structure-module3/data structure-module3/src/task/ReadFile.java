package task;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;


public class ReadFile {
    public Integer ReadFile(File file) throws IOException {
        HashTable hashTable=new HashTable(256);//initial size of hashtable is 128
        BufferedReader br=null;//use the class of BufferReader to help improve the efficiency of reading file

        try {
            br=new BufferedReader(new FileReader(file));//use the class of filereader to read file
            String read=br.readLine();
            while(read!=null){
                int length=read.length();
                int index=0;//the first index of read

                while(index<length){
                    //judge whether the char is letter, if so, continue and get the word
                    if(Character.isLetter(read.charAt(index))){
                        String key ="";

                        while(index<length&&Character.isLetter(read.charAt(index))){
                            char ch=read.charAt(index);
                            //if the letter is upper, we should transfer it, because apple and Apple actually the same.
                            if(Character.isUpperCase(ch)){
                                ch=Character.toLowerCase(ch);
                            }

                            key+=ch;
                            index++;
                        }

                        //sort the string: transfer it to char[] firstly, after sorting the char[], and transfer it to string
                        char[] temp=key.toCharArray();
                        Arrays.sort(temp);
                        key=new String(temp);

                        //judge whether hashtable contains the key
                        boolean flag=hashTable.contains(key);
                        if(flag==false){
                            hashTable.insert(key,null);
                        }
                    }
                    index++;
                }
                read=br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        br.close();
        return hashTable.size();
    }
}
