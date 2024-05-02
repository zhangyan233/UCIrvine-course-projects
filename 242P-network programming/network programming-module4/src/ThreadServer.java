import java.io.*;
import java.net.Socket;
import java.util.concurrent.Callable;

public class ThreadServer implements Runnable {
    private Socket socket;
    private String filePath;

    //initial thread
    public ThreadServer(Socket socket, String filePath) {
        this.socket = socket;
        this.filePath = filePath;
    }

    @Override
    public void run() {
        BufferedReader brFromClient=null;
        PrintWriter pwToClient=null;

        try{
            brFromClient=new BufferedReader(new InputStreamReader(socket.getInputStream()));//reader used to read information from client
            pwToClient=new PrintWriter(new BufferedOutputStream(socket.getOutputStream()),true);//writer used to write information to client

            while(true) {
                //1.get the command
                String command = brFromClient.readLine();

                //have two situations: "index","get <file>",
                if (command.equals("index")) {
                    //get the all filename from the filePath
                    File directory = new File(filePath);
                    String[] files = directory.list();

                    //traverse all file and send them to client
                    if (files != null) {
                        for (String file : files) {
                            pwToClient.println(file);
                        }
                    }

                    //end traversing, send "END" to client means, inform client this operation finish
                    pwToClient.println("END");

                } else if (command.startsWith("get ")) {
                    String fileName = command.substring(4);

                    //get the filePath
                    File file = new File(new File(filePath), fileName);

                    //there are two situations,
                    if (file.exists() && file.isFile()) {//file exists and is a file

                        pwToClient.println("OK");
                        BufferedReader brReadFile=new BufferedReader(new FileReader(file));
                        String fileContent=null;
                        fileContent=brReadFile.readLine();

                        //send to content from this file
                        while ((fileContent = brReadFile.readLine()) != null) {
                            pwToClient.println(fileContent);
                        }

                        //end reading, send "END" to client means, inform client this operation finish
                        pwToClient.println("END");
                    } else {
                        //file doesn't exist or is not a file, send "error" to client to inform client this mistake
                        pwToClient.println("error");
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(pwToClient!=null){
                try {
                    pwToClient.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(brFromClient!=null){
                try {
                    brFromClient.close();
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
