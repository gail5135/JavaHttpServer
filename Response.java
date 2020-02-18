public class Response {
    private ResponseHandler responseHandler;
    private int statusCode;
    private String statusLine;

    public Response(RequestHandler requestHandler){
        responseHandler = new ResponseHandler();
        statusCode = requestHandler.getStatusCode();
        statusLine = makeStatusLine();
    }

    public String makeStatusLine(){
        if(statusCode == HttpStatusCode.OK){
            responseHandler.ok();
        }
        return "testStatusLine";
    }

    public String getStatusLine(){
        return this.statusLine;
    }
}
