package realServer;

import java.util.HashMap;

public enum  HttpStatusCode {
    _200("200 OK"),
    _400("400 Bad realServer.Request"),
    _404("404 Not Found");

    private final String status;

    HttpStatusCode(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return status;
    }
}
