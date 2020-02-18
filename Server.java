import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    // Field
    public static final String ROOT_DIR = "C:\\Users\\gail5135\\Desktop";

    // Constructor
    public Server(int portNum){
        try (ServerSocket server = new ServerSocket(portNum)){
            System.out.println("Listening for connection on port 8090 ....");

            while(true) {
                try (Socket socket = server.accept()) {
                    Request req = new Request(socket.getInputStream());
                    req.parse();

                    RequestHandler requestHandler = new RequestHandler(req.getMethod(), req.getUrl());

                    Response res = new Response(requestHandler);
                    System.out.println(res.getStatusLine());
                    socket.getOutputStream().write(res.getStatusLine().getBytes("UTF-8"));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
