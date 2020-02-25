package realServer;

import java.util.HashMap;

public class Request {

    private String method;
    private String path;
    private String httpVersion;
    private HashMap<String, String> parameters = null;
    private Object body = null;

    public Request(String method, String path, String httpVersion){
        this.method = method;
        this.path = path;
        this.httpVersion = httpVersion;
    }

    public Request(String method, String path, String httpVersion, HashMap parameters){
        this.method = method;
        this.path = path;
        this.httpVersion = httpVersion;
        this.parameters = parameters;
    }

    public Request(String method, String path, String httpVersion, Object body){
        this.method = method;
        this.path = path;
        this.httpVersion = httpVersion;
        this.body = body;
    }

    public String getMethod() { return method; }

    public String getHttpVersion() { return httpVersion; }

    public String getPath(){
        return path;
    }
}
