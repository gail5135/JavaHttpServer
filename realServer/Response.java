package realServer;

import testServer.main.java.enums.Status;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Response{
    private final Status status;
    private final String contentType;
    private final int contentLength;
    private final byte[] body;
    private DateTimeFormatter rfc1123Formatter = DateTimeFormatter.RFC_1123_DATE_TIME;
    private final String CRLF = "\r\n";

    public Response(Status status, String contentType, byte[] body) {
        this.status = status;
        this.contentType = contentType;
        this.contentLength = body.length;
        this.body = body;
    }

    public void sendResponse(OutputStream out) throws IOException {
        System.out.println("1");
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        System.out.println("2");

        String response =
            "HTTP/1.1 " + status.statusCode + CRLF +
            "Date: " + rfc1123Formatter.format(now) + CRLF +
            "Server: SimpleJavaHttpServer" + CRLF +
            "Content-Type: " + contentType + CRLF +
            "Content-Length: " + String.valueOf(contentLength) + CRLF +
            "Connection: Close" + CRLF +
            CRLF;

        System.out.println("3");

        out.write(response.getBytes("UTF-8"));

        System.out.println("4");

        out.write(body);

        System.out.println("5");

//        out.flush();
    }
}
