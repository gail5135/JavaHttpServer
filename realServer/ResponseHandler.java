package realServer;

public class ResponseHandler {
    // Field
    private String httpResponse;
    private HttpStatusCode statusCode;
    private String contentType;
    private int contentLength;
    private byte[] body;

    // Constructor
    public ResponseHandler(HttpStatusCode statusCode, String contentType, byte[] body){
        this.statusCode = statusCode;
        this.contentType = contentType;
        this.contentLength = body.length;
        this.body = body;
    }

    // Method
    public String ok(){
        statusCode = HttpStatusCode._200;
        System.out.println(statusCode);
        httpResponse = "HTTP/1.1 "+statusCode+"\r\n\r\n" + "Connection: close\r\n\r\n"+this.body;
        return httpResponse;
    }

    public String bad(){
        statusCode = HttpStatusCode._400;
        httpResponse = "HTTP/1.1 "+statusCode+"\r\n\r\n";
        return httpResponse;
    }

    public String notFound(){
        statusCode = HttpStatusCode._404;
        httpResponse = "HTTP/1.1 "+statusCode+"\r\n\r\n";
        return httpResponse;
    }

    public void setBody(byte[] body){
        this.body = body;
    }
}
