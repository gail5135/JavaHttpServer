package realServer;


import javax.sound.midi.SysexMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParser {

    private Pattern requestLinePattern = Pattern.compile("(?<method>.*) (?<path>.*?) (?<version>.*)");
    private String requestLine;
    private String method;
    private String targetPath;
    private String httpVersion;
    private StringBuffer header;
    private StringBuffer body = null;
    private HashMap<String, String> parameters = null;


    public RequestParser(InputStream in) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

        // requestLine
        requestLine = bufferedReader.readLine();
        header = new StringBuffer();

        // header
        String line = bufferedReader.readLine();
        while(!line.isEmpty()){
            header.append(line+"\r\n");
            line = bufferedReader.readLine();
        }

        // body (if body exists)
//        line = bufferedReader.readLine();
        System.out.println(line+"lineTest");
//        if(line != null){
//            body = new StringBuffer();
//            body.append(line+"\r\n");
//            line = bufferedReader.readLine();
//
//            while(line != null){
//                body.append(line+"\r\n");
//                line = bufferedReader.readLine();
//            }
//        }

        // For debugging
        System.out.println(requestLine);
        System.out.println(header);
        System.out.println(body);

        // method | targetPath | httpVersion
        if(requestLine != null) {
            String[] tempStr = requestLine.split(" ");
            method      = tempStr[0];
            targetPath  = tempStr[1];
            httpVersion = tempStr[2];
        }

        // if method is 'get' and targetPath has 'parameters'
        if(method.equals("GET")){
            this.processingGetMethod();
        }
        else if(method.equals("POST")){
            this.processingPostMethod();
        }
    }

    public void processingGetMethod(){
        String parameterLine;
        if(targetPath.contains("?")){
            parameterLine = targetPath.substring(targetPath.indexOf("?")+1);
            targetPath = targetPath.substring(1,targetPath.indexOf("?"));

            this.parseGetParameters(parameterLine);
        }
    }

    public void processingPostMethod(){

    }

    public void parseGetParameters(String parameterLine){
        this.parameters = new HashMap<>();
        String[] parameterKeyValue = parameterLine.split("&");

        for(String temp : parameterKeyValue){
            String[] keyValue = temp.split("=");
            this.parameters.put(keyValue[0], keyValue[1]);
        }
    }

    public void parsePost(){

    }

    public Request getRequest(){
        if(method.equals("GET")){
            if(parameters == null) {
                return new Request(method, targetPath, httpVersion);
            }
            else {
                return new Request(method, targetPath, httpVersion, parameters);
            }
        }
        else if(method.equals("POST") && body != null){
            return new Request(method, targetPath, httpVersion, body);
        }
        else {
            return new Request(method, targetPath, httpVersion);
        }
    }
}
