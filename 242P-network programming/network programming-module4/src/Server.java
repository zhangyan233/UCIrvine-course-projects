import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
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

        //director doesn't exist
        if ( ! directory.exists() ||! directory.isDirectory()) {
            System.out.println("Specified directory does not exist.");
            return;
        }


        //create threadPool
        ExecutorService pool= Executors.newFixedThreadPool(50);

        ServerSocket serverSocket=null;

        try {
            serverSocket=new ServerSocket(80);

            Socket socket=null;

            while(true){
                socket=serverSocket.accept();
                Runnable task=new ThreadServer(socket,filePath);
                pool.submit(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        try{
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
