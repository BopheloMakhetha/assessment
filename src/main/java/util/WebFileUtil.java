package util;

import domain.WebFile;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WebFileUtil {

    private final static String CHARSET = "iso-8859-9";

    public void populateWebfile(WebFile webFile) throws Exception {
        InputStream inputStream = webFile.getUrl().openStream();
        BufferedReader buffer = getBufferedReaderFromInputStream(inputStream);

        int byteRead;
        while ((byteRead = buffer.read()) != -1) {
            webFile.addContent((char) byteRead);
        }
        buffer.close();
    }

    private BufferedReader getBufferedReaderFromInputStream(InputStream inputStream) throws Exception {
        return new BufferedReader(new InputStreamReader(inputStream, CHARSET));
    }
}
