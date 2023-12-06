import javax.xml.crypto.Data;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;

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
                byte[] bytes = new byte[512];

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

                } else if (command.startsWith("get ")) {
                    //get name from a file
                    String fileName = command.substring(4);
                    File file = new File(filePath, fileName);

                    //judge whether the file exists or is a file
                    if (file.exists() && file.isFile()) {

                        // if is true, send "OK" to client, tell it filename is valid, start to read content from this file
                        bytes="OK".getBytes();
                        DatagramPacket OK = new DatagramPacket(bytes, bytes.length, clientAddress, clientPort);
                        datagramSocket.send(OK);

                        BufferedReader br=null;
                        try {

                            //read file
                            br=new BufferedReader(new FileReader(file));
                            String line=null;
                            while ((line=br.readLine())!=null) {
                                System.out.println(line);
                                bytes = line.getBytes();
                                DatagramPacket sendToClient = new DatagramPacket(bytes, bytes.length, clientAddress, clientPort);
                                datagramSocket.send(sendToClient);
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

                        //tell client end reading file, this operation ends
                        bytes = "END".getBytes();
                        DatagramPacket end= new DatagramPacket(bytes, bytes.length, clientAddress, clientPort);
                        datagramSocket.send(end);
                        System.out.println("END");
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
}
