package test;

import task.BST;

import java.io.*;

public class Solution {
    public static void main(String[] args){
        BST bts=new BST();

        //read file
        File readFile=new File("src/tree-input.txt");
        readFile(bts,readFile);

        //write inorderfile
        File writerInorderFile=new File("src/inorder.txt");
        StringBuilder inorderString=new StringBuilder();
        bts.inorder(bts.getRoot(),inorderString);
        writeFile(inorderString.toString(),writerInorderFile);

        //write preorderfile
        File writePreorderFile=new File("src/preorder.txt");
        StringBuilder preorderString=new StringBuilder();
        bts.preorder(bts.getRoot(),preorderString);
        writeFile(preorderString.toString(),writePreorderFile);

        //write postorderfile
        File writePostorderFile=new File("src/postorder.txt");
        StringBuilder postorderString=new StringBuilder();
        bts.postorder(bts.getRoot(),postorderString);
        writeFile(postorderString.toString(),writePostorderFile);

        //writer levelFile
        File levelFile=new File("src/levelFile.txt");
        StringBuilder levelString=new StringBuilder();
        bts.levelTraveral(bts.getRoot(),levelString);
        writeFile(levelString.toString(),levelFile);
    }

    //write file
    public static void writeFile(String content,File file){
        BufferedWriter bw=null;
        try {
            bw=new BufferedWriter(new FileWriter(file));
            int offset=0;//start position of writing every time
            int length=41;//the length of student's information
            while(offset+length<content.length()){
                bw.write(content,offset,length);
                offset+=length;
                bw.newLine();
                bw.flush();
            }
            bw.flush();
            bw.close();;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //read file
    public static void readFile(BST bts, File file){
        BufferedReader br=null;
        try {
            br = new BufferedReader(new FileReader(file));
            String text="";
            while((text=br.readLine())!=null){
                int IDnumber=Integer.parseInt(text.substring(1,8));//StudentNumber

                //StudentLastName
                String lastNumber="";
                int index=8;
                while(Character.isLetter(text.charAt(index))){
                    lastNumber+=(text.charAt(index));
                    index++;
                }

                //department
                String department=text.substring(33,37);

                //program
                index=37;
                String program="";
                while(Character.isLetter(text.charAt(index))){
                    program+=(text.charAt(index));
                    index++;
                }

                //year
                int year=Integer.parseInt(text.substring(41));

                //according operation do next, I is insert, D is delete
                char operation=text.charAt(0);
                if(operation=='I'){
                    bts.setRoot(bts.insert(bts.getRoot(),IDnumber,lastNumber,department,program,year));
                }else if(operation=='D'){
                    bts.setRoot(bts.delete(bts.getRoot(),lastNumber));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
