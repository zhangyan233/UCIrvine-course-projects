import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server {
    public static void main(String[] args) {

        //judge the filePath
        if (args.length == 0) {
            System.out.println("Usage:  java FileServer <directory>");
            return;
        }

        String filePath=args[0];
        File directory = new File(filePath);

        // judge whether it exists or not
        if ( ! directory.exists() ) {
            System.out.println("Specified directory does not exist.");
            return;
        }

        //judge whether it is a directory
        if (! directory.isDirectory() ) {
            System.out.println("The specified file is not a directory.");
            return;
        }

        ExecutorService pool= Executors.newFixedThreadPool(50);
        //server listen to port 8010
        int port=8010;
        DatagramSocket datagramSocket = null;

        try {
            //server listen to localhost
            InetAddress serverAddress = InetAddress.getLocalHost();
            datagramSocket = new DatagramSocket(port,serverAddress);

            //set server waiting time 20s
            datagramSocket.setSoTimeout(2000000);
            while(true){
                    //receive command from client
                    byte[] bytes = new byte[1024];
                    DatagramPacket clientRequest = new DatagramPacket(bytes, bytes.length);
                    System.out.println("server is getting command");
                    datagramSocket.receive(clientRequest);
                    System.out.println("server recevied command");
                    ThreadServer task = new ThreadServer(datagramSocket,clientRequest,filePath);
                    pool.submit(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pool.shutdown();
        try{
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS); //wait until all threads finish before shutdown
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
