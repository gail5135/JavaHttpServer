package realServer;

import realServer.resources.enums.MIME;
import testServer.main.java.enums.Status;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class RequestHandler {
    // Field
    private Path BAD_REQUEST_HTML_PATH = Paths.get("resources","400.html");
    private Path FORBIDDEN_HTML_PATH = Paths.get("resources/403.html");
    private Path NOT_FOUND_HTML_PATH = Paths.get("resources/404.html");
    private Request request;
    private static String HTML_MIME = "text/html;charset=utf8";
    private MIME mime;

    // Method
    public Response handleRequest(Request request) throws IOException{
        System.out.println(this.getClass().getResource("").getPath()+BAD_REQUEST_HTML_PATH);


        if(request == null){
            return new Response(Status.BAD_REQUEST, HTML_MIME, Files.readAllBytes(BAD_REQUEST_HTML_PATH));
        }

        Path resourcePath = Paths.get("./resources/", request.getPath()).normalize();
        System.out.println(Files.readAllBytes(resourcePath).toString());

        if(!resourcePath.startsWith("./resources/")){
            return new Response(Status.FORBIDDEN, HTML_MIME, Files.readAllBytes(FORBIDDEN_HTML_PATH));
        }

        if(Files.isRegularFile(resourcePath)){
            String mime = this.getMIME(resourcePath).toString();
            return new Response(Status.OK, mime, Files.readAllBytes(resourcePath));
        }

        return null;
    }

    public MIME getMIME(Path path){
        String fileName = path.getFileName().toString();
        String ext = fileName.substring(fileName.indexOf(".") + 1);

        return this.mime = MIME.valueOf(ext);
    }
}
