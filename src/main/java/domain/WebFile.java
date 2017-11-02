package domain;

import java.net.MalformedURLException;
import java.net.URL;

public class WebFile {

    private StringBuilder fileContent;
    private final URL url;

    public WebFile(URL url) throws MalformedURLException {
        this.url = url;
        this.fileContent = new StringBuilder();
    }

    public String getFileContent() {
        return fileContent.toString();
    }

    public void addContent(char content){
        this.fileContent.append(content);
    }

    public URL getUrl() {
        return url;
    }


}
