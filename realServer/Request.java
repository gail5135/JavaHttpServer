package realServer;

public class Request {

    private String method;
    private String path;
    private String httpVersion;
    private Object body = null;

    public Request(String method, String path, String httpVersion){
        this.method = method;
        this.path = path;
        this.httpVersion = httpVersion;
    }

    public Request(String method, String path, String httpVersion, Object body){
        this.method = method;
        this.path = path;
        this.httpVersion = httpVersion;
        this.body = body;
    }

    public String getPath(){
        return path;
    }
}
