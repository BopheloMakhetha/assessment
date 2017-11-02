package integration;

import domain.WebFile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import util.WebFileUtil;

import java.net.URL;

@Category(WebFileUtil.class)
public class WebFileUtilIntegrationTest {

    String url = "https://github.com/egis/handbook/blob/master/Tech-Stack.md";

    @Test
    public void testDownloadSourceFromLink() throws Exception {
        WebFileUtil webFileUtil = new WebFileUtil();
        WebFile webFile = new WebFile(new URL(url));
        webFileUtil.populateWebfile(webFile);

        Assert.assertNotNull(webFile.getFileContent());
        Assert.assertTrue(webFile.getFileContent().contains("<!DOCTYPE html>"));
    }

}
