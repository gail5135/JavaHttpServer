package realServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    // Field
    public static final String RESPONSE_RESOURCE = MyServer.class.getClassLoader().getResource("").getPath()+ "/public/";
    public Request request;
    public RequestParser requestParser;
    public Response response;

    // Constructor
    public MyServer(int portNum) throws IOException{
        ServerSocket serverSocket = new ServerSocket(portNum);

        System.out.println("HTTP realServer.Server Start! Listening at " + portNum + "!");

        while(true){
            try {
                Socket s = serverSocket.accept();

                requestParser = new RequestParser(s.getInputStream());
                request = requestParser.getRequest();
                response = new RequestHandler().handleRequest(request);

                response.sendResponse(s.getOutputStream());

            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
