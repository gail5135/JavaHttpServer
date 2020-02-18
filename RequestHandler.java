import java.util.ArrayList;
import java.util.HashMap;

public class RequestHandler {
    // Field
    private HashMap<String, String> parameters = new HashMap<>();
    private int statusCode;


    // Constructor
    public RequestHandler(String method, String url){
        if(method.equals("GET")){
            if(!url.equals("/")) {
                parseGetParameter(url);
            }
        }
        else if(method.equals("POST")){

        }
        else{
            statusCode = HttpStatusCode.NotFound;
        }
    }

    // Method
    public void parseGetParameter(String url){
        String[] keyValue = url.substring(2).split("&");

        for (String str : keyValue) {
            String[] temp = str.split("=");
            if(temp[1].length() < 1024) {
                parameters.put(temp[0], temp[1]);
            }
            else{
                parameters = null;
                return;
            }
        }
    }

    public HashMap<String,String> getParameters(){
        return this.parameters;
    }

    public int getStatusCode(){
        return this.statusCode;
    }

    // test method
    public void showGetParameter(){
        for(String key : parameters.keySet()){
            System.out.println("key : "+key+" || value : "+parameters.get(key));
        }
    }
}
