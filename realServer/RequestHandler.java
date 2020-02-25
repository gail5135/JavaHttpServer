package realServer;

import realServer.resources.enums.MIME;
import realServer.resources.enums.Status;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RequestHandler {
    // Field
    private String PACKAGE_PATH = "src/realServer/resources";
    private Path BAD_REQUEST_PAGE_PATH = Paths.get(PACKAGE_PATH, "400.html");
    private Path FORBIDDEN_PAGE_PATH = Paths.get(PACKAGE_PATH, "403.html");
    private Path NOT_FOUND_PAGE_PATH = Paths.get(PACKAGE_PATH, "404.html");
    private String HTML_MIME = "text/html;charset=utf8";
    private MIME mime;

    // Method
    public Response handleRequest(Request request) throws IOException{

        if(request == null){
            return new Response(Status.BAD_REQUEST, HTML_MIME, Files.readAllBytes(BAD_REQUEST_PAGE_PATH));
        }

        if(request.getMethod().equals("GET")) {
            Path resourcePath = Paths.get(PACKAGE_PATH, request.getPath()).normalize();

            if (!resourcePath.startsWith(PACKAGE_PATH)) {
                return new Response(Status.FORBIDDEN, HTML_MIME, Files.readAllBytes(FORBIDDEN_PAGE_PATH));
            }

            if (Files.isRegularFile(resourcePath)) {
                String mime = this.getMIME(resourcePath).toString();
                return new Response(Status.OK, mime, Files.readAllBytes(resourcePath));
            }
        }

        return new Response(Status.NOT_FOUND, HTML_MIME, Files.readAllBytes(NOT_FOUND_PAGE_PATH));
    }

    public MIME getMIME(Path path){
        String fileName = path.getFileName().toString();
        String ext = fileName.substring(fileName.indexOf(".") + 1);

        return this.mime = MIME.valueOf(ext);
    }
}
