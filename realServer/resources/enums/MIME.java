package realServer.resources.enums;

public enum  MIME {
    html("text/html"),
    css("text/css"),
    gif("image/gif"),
    jpg("image/jpeg"),
    png("image/png"),
    arc("application/octet-stream"),
    js("application/javascript"),
    mp3("audio/mpeg"),
    multipart("multipart/form-data");

    public final String MIME;

    MIME(String MIME) {
        this.MIME = MIME;
    }
}
