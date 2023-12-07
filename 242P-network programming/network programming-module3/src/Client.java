import javax.xml.crypto.Data;
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {

        DatagramSocket ds = null;
        //client send information to port 8010
        int port = 8010;
        BufferedReader keyboard = null;

        try{
            ds=new DatagramSocket();
            InetAddress inetAddress = InetAddress.getLocalHost();
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                //get the command
                System.out.println("please input 'index' or 'get <file>' or 'END'");
                String command = keyboard.readLine();
                byte[] bytes = new byte[512];

                //three situations: index, get <file>,invalid input
                if (command.equals("index")) {
                    //send order to server
                    DatagramPacket sendToServer = new DatagramPacket(command.getBytes(), command.getBytes().length,inetAddress,port);
                    ds.send(sendToServer);
                    DatagramPacket receiveFromServer=null;

                    //receive data from server
                    while (true) {
                        receiveFromServer = new DatagramPacket(bytes, bytes.length);
                        ds.receive(receiveFromServer);
                        String res = new String(receiveFromServer.getData(),0,receiveFromServer.getLength());

                        //if receiving message is "end", it says getting all files in the directory, this process should finish
                        if (res.equals("END")) {
                            break;
                        }
                        System.out.println(res);
                    }
                } else if (command.startsWith("get ")) {
                    //send order to server
                    DatagramPacket sendToServer = new DatagramPacket(command.getBytes(), command.getBytes().length,inetAddress,port);
                    ds.send(sendToServer);
                    DatagramPacket receiveFromServer=null;

                    receiveFromServer = new DatagramPacket(bytes, bytes.length);
                    ds.receive(receiveFromServer);
                    String reply = new String(receiveFromServer.getData(),0,receiveFromServer.getLength());

                    //judge whether the file exists

                    if (reply.equals("OK")) {
                        System.out.println("OK");

                        //start to receive content
                        int sequenceNumber;
                        boolean isLast;
                        int currentSequence=0;

                        while(true){
                            byte[] message=new byte[1024];//store information from server every time
                            receiveFromServer=new DatagramPacket(message,message.length);
                            ds.receive(receiveFromServer);
                            message=receiveFromServer.getData();

                            sequenceNumber=((message[0]&0xff)<<8)+(message[1]&0xff);
                            isLast=(message[2]&0xff)==1;
                            //System.out.println((int)sequenceNumber);

                            //right:sequenceNumber=currentSequence+1;
                            if(sequenceNumber==currentSequence+1){
                                currentSequence=sequenceNumber;
                                System.out.println(new String(message,3,message.length-3));
                            }

                            //inform server "i get the No.currentSequence part"
                            byte[] replyNumber=new byte[2];
                            replyNumber[0]=(byte) (currentSequence>>8);
                            replyNumber[1]=(byte)currentSequence;
                            DatagramSocket datagramSocket=new DatagramSocket();
                            DatagramPacket sendReplyNumber=new DatagramPacket(replyNumber,replyNumber.length,inetAddress,10001);
                            datagramSocket.send(sendReplyNumber);

                            //judge whether is the last part
                            if(isLast){
                                break;
                            }
                        }
                    }else{
                        System.out.println("Error: file doesn't find");
                    }

//                    while (true) {
//                        //receiving information from server
//
//
//                        //"ok" means this file exists, continue to read this file
//
//
//                        //"error" means this file doesn't exist, end this process
//                        if (reply.equals("error")) {
//                            System.out.println("Error: file doesn't find");
//                            break;
//                        }
//                        //"end" means finish reading this file, should end this process
//                        if (reply.equals("END")) {
//                            break;
//                        }
//
//                        //display every row of content from this file
//                        System.out.println(reply);
//                    }
//                    //end this loop, end operations
                } else if (command.equals("END")) {
                    System.out.println("exiting system");
                    break;
                } else {
                    System.out.println("Invalid input");
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (ds != null) {
                ds.close();
            }
        }
    }
}
