package testServer.main.java;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

public class MimeDetector {

    private Properties prop;

    public MimeDetector(String configFileName){
        InputStream in = this.getClass().getResourceAsStream(configFileName);
        Properties p = new Properties();

        try {
            p.load(in);
        } catch (IOException e){
            e.printStackTrace();
        }

        this.prop = p;
    }

    public String getMime(Path path){
        String fileName = path.getFileName().toString();
        String ext = fileName.substring(fileName.indexOf(".") + 1);
        return  this.prop.getProperty(ext, "application/octet-stream");
    }
}
