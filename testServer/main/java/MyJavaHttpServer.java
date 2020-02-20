package testServer.main.java;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyJavaHttpServer {

    private static int PORT = 8090;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        RequestParser parser = new RequestParser();
        RequestHandler handler = new RequestHandler();

        System.out.println("HTTP realServer.Server Start! Listening at " + PORT + "!");

        while(true){
            try{
                Socket socket = serverSocket.accept();
                Thread worker = new WorkerThread(socket, parser, handler);
                worker.start();
            } catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}
