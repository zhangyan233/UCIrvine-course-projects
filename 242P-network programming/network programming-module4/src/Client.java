import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket socket=null;
        BufferedReader brFromServer=null;
        BufferedReader brFromOthers=null;
        PrintWriter bwToServer=null;

        try {
                socket=new Socket("127.0.0.1",80);
                brFromServer=new BufferedReader(new InputStreamReader(socket.getInputStream()));//reader which used to read information return from server
                brFromOthers=new BufferedReader(new InputStreamReader(System.in));//reader which used to read information from keyboard
                bwToServer=new PrintWriter(new BufferedOutputStream(socket.getOutputStream()),true);//writer which used to write information to server

            while(true){
                //get the command
                System.out.println("please input 'index' or 'get <file>' or 'END'" );
                String command=brFromOthers.readLine();

                //four situations: index, get <file>,END, invalid input
                if(command.equals("index")){
                    //transfer command to server
                    bwToServer.println(command);

                    //get response from server
                    String response=null;
                    response=brFromServer.readLine();

                    //when reading "END" means have finished reading fileName, exits this loop
                    while(!response.equals("END")) {
                        System.out.println(response);
                        response=brFromServer.readLine();
                    }
                }else if(command.startsWith("get ")){
                    bwToServer.println(command);
                    String response=brFromServer.readLine();

                    //response has two possibilities: "OK", "ERROR"
                    if(response.equals("OK")){//the file exists

                        //when reading "END" means have finished reading this file, exits this loop
                        while(!response.equals("END")) {
                            System.out.println(response);
                            response=brFromServer.readLine();
                        }

                    }else{
                        //"Error" means: in this directory, don't find the file
                        System.out.println("Error: Don't find this file in server");
                    }
                }else if(command.equals("END")){
                    //all operations have been done, need to end this system
                    System.out.println("system ends");
                    break;

                }else{
                    //invalid operation command
                    System.out.println("Invalid input");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bwToServer!=null){
                try {
                    bwToServer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(brFromOthers!=null){
                try {
                    brFromOthers.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(brFromServer!=null){
                try {
                    brFromOthers.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
