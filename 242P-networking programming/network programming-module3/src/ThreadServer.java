import javax.xml.crypto.Data;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class ThreadServer implements Runnable {
    private DatagramSocket datagramSocket;
    private DatagramPacket clientRequest;
    private String filePath;


    public ThreadServer(DatagramSocket datagramSocket,DatagramPacket clientRequest,String filePath) {
        this.datagramSocket=datagramSocket;
        this.clientRequest=clientRequest;
        this.filePath = filePath;
    }

    @Override
    public void run() {

        try {
                byte[] bytes = new byte[1024];

                //get address and port from client
                InetAddress clientAddress = clientRequest.getAddress();
                int clientPort = clientRequest.getPort();

                String command = new String(clientRequest.getData(),0,clientRequest.getLength());
                System.out.println(command);

                //command has two situations: index, get *.txt
                if (command.equals("index")) {
                    //send the all filename from the filePath
                    File directory = new File(filePath);
                    String[] files = directory.list();

                    //traverse all file
                    if (files != null) {
                        for (String file : files) {
                            //send information to client
                            bytes=file.getBytes();
                            DatagramPacket sendToClient = new DatagramPacket(bytes, bytes.length,clientAddress,clientPort);
                            datagramSocket.send(sendToClient);
                        }
                    }

                    // when end reading all filename, send "end" to client, tell it this operation should over
                    bytes="END".getBytes();
                    DatagramPacket sendToClient = new DatagramPacket(bytes, bytes.length, clientAddress, clientPort);
                    datagramSocket.send(sendToClient);

                } else {
                    //get name from a file
                    String fileName = command.substring(4);
                    File file = new File(filePath, fileName);

                    //judge whether the file exists or is a file
                    if (file.exists() && file.isFile()) {

                        // if is true, send "OK" to client, tell it filename is valid, start to read content from this file
                        bytes="OK".getBytes();
                        DatagramPacket OK = new DatagramPacket(bytes, bytes.length, clientAddress, clientPort);
                        datagramSocket.send(OK);

                        /*------------------make all content from file become string--------------------------*/

                        StringBuilder sb=new StringBuilder();
                        BufferedReader br=null;
                        try {

                            //read file
                            br=new BufferedReader(new FileReader(file));
                            String line=null;
                            while ((line=br.readLine())!=null) {
                                sb.append(line);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                if(br!=null) {
                                    br.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        String content=sb.toString();
                        byte[] fileBytes = content.getBytes();
                        //System.out.println(new String(fileBytes)+"end");

                        ///*------------------divide content to several parts--------------------------*/
                        int sequenceNumber=0;//set order
                        int askSequence=0;//whether client receive correctly

                        for (int i = 0; i < fileBytes.length; i+=1021) {
                            //System.out.println(i);
                            sequenceNumber+=1;

                            //create Message
                            byte[] message=new byte[1024];
                            message[0]=(byte) (sequenceNumber>>8);//sequence to make sure correctly
                            message[1]=(byte) sequenceNumber;

                            //judge whether read the end of file
                            if((i+1021)>=fileBytes.length){
                                message[2]=(byte) 1;
                                System.arraycopy(fileBytes,i,message,3,fileBytes.length-i);
                            }else{
                                message[2]=(byte) 0;
                                System.arraycopy(fileBytes,i,message,3,1021);
                            }

                           //System.out.println(new String(message));

                            //send the part to client
                            DatagramPacket sendToClient=new DatagramPacket(message,message.length,clientAddress,clientPort);
                            datagramSocket.send(sendToClient);

                            boolean ackRec;//whether client receive this part

                            while(true){
                                byte[] ack=new byte[2];
                                DatagramSocket replyNumber=new DatagramSocket(10001,clientAddress);
                                DatagramPacket ackClientReceive=new DatagramPacket(ack,ack.length);
                                replyNumber.setSoTimeout(20000);

                                try {
                                    replyNumber.receive(ackClientReceive);
                                    askSequence=((ack[0]&0xff)<<8)+(ack[1]&0xff);
                                    //System.out.println((int)askSequence);
                                    ackRec=true;
                                } catch (IOException e) {
                                    ackRec=false;
                                    e.printStackTrace();
                                }

                                //System.out.println((int)askSequence+" "+sequenceNumber);
                                replyNumber.close();

                                //judge whether the order is correct
                                if(ackRec&&askSequence==sequenceNumber){
                                    //client receive this part, exit loop and continue to work
                                    break;
                                }else{
                                    datagramSocket.send(sendToClient);
                                }

                            }
                        }
                    } else {
                        //this file doesn't exist
                        bytes="error".getBytes();
                        DatagramPacket sendToClient = new DatagramPacket(bytes, bytes.length, clientAddress, clientPort);
                        datagramSocket.send(sendToClient);
                    }
                }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException e){
            System.out.println("Timeout reached!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int replyNumber(byte[] data){
        return (int)data[0];
    }
}
