package realServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.format.DateTimeFormatter;

public class MyServer {
    // Field
    public static final String RESPONSE_RESOURCE = MyServer.class.getClassLoader().getResource("").getPath()+ "realServer/";
    public Request request;
    public RequestParser requestParser;
    public Response response;

    // Constructor
    public MyServer(int portNum) throws IOException{
        ServerSocket serverSocket = new ServerSocket(portNum);

        System.out.println("HTTP realServer.Server Start! Listening at " + portNum + "!\r\n");

        while(true){
            try {

                Socket s = serverSocket.accept();
                requestParser = new RequestParser(s.getInputStream());
                request = requestParser.getRequest();
                response = new RequestHandler().handleRequest(request);
                response.sendResponse(s.getOutputStream());
                s.close();

            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
