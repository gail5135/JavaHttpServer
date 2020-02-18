import java.io.IOException;
import java.io.InputStream;

public class Request {
    private InputStream input;
    private String url;
    private String method ;

    public Request(InputStream input){
        this.input = input;
    }

    public void parse() {
        StringBuffer requestBuffer = new StringBuffer(2048);
        int readSuccess;
        byte[] buffers = new byte[2048];

        try {
            readSuccess = input.read(buffers);
        } catch (IOException e){
            e.printStackTrace();
            readSuccess = -1;
        }

        for(int i=0; i < readSuccess; ++i){
            requestBuffer.append((char)buffers[i]);
        }

        System.out.println(requestBuffer.toString());
        parseUrl(requestBuffer.toString());
    }

    public void parseUrl(String requestString){
        int index1, index2;

        index1 = requestString.indexOf(" ");
        method = requestString.substring(0, index1);

        if(index1 != 1){
            index2 = requestString.indexOf(" ", index1+1);
            if(index2 > index1){
                url = requestString.substring(index1 + 1, index2);
            }
        }
    }

    public String getUrl(){
        return this.url;
    }

    public String getMethod(){
        return this.method;
    }
}
