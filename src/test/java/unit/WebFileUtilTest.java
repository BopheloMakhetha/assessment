package unit;

import domain.WebFile;
import org.junit.Test;
import util.WebFileUtil;
import java.net.URL;
import java.nio.file.Paths;

import static org.mockito.Mockito.*;

public class WebFileUtilTest {

    @Test
    public void testDownloadSourceFromLink() throws Exception{
        WebFile webFile = mock(WebFile.class);

        //unable to mock URL, so using a test file instead
        URL url = Paths.get("C:\\Users\\bophelo.mkhwanazi\\Desktop\\assessment\\src\\test\\java\\resource\\test.html").toUri().toURL();

        when(webFile.getUrl()).thenReturn(url);

        WebFileUtil webFileUtil = new WebFileUtil();
        webFileUtil.populateWebfile(webFile);

        verify(webFile, times(4)).addContent(anyChar());
    }
}
