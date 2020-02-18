public class ResponseHandler {
    // Field
    private String httpResponse;

    // Constructor
    public ResponseHandler(){

    }

    // Method
    public String ok(){
        httpResponse = "HTTP/1.1 200 OK\r\n\r\n";
        return httpResponse;
    }

    public String bad(){
        httpResponse = "HTTP/1.1 400 BAD\r\n\r\n";
        return httpResponse;
    }

    public String notFound(){
        httpResponse = "HTTP/1.1 404 Not Found\r\n\r\n";
        return httpResponse;
    }
}
