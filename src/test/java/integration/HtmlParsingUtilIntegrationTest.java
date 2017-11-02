package integration;

import domain.WebFile;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import util.HtmlParsingUtil;
import util.WebFileUtil;

import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Category(HtmlParsingUtil.class)
public class HtmlParsingUtilIntegrationTest {

    String sourceHtml;

    @Before
    public void setup() throws Exception{
        URL url = Paths.get("C:\\Users\\bophelo.mkhwanazi\\Desktop\\assessment\\src\\test\\java\\resource\\sample.html").toUri().toURL();
        WebFile webFile = new WebFile(url);
        WebFileUtil webFileUtil = new WebFileUtil();
        webFileUtil.populateWebfile(webFile);
        sourceHtml = webFile.getFileContent();
    }

    @Test
    public void testThatTablesGetExtractedFromHtml(){
        HtmlParsingUtil htmlParsingUtil = new HtmlParsingUtil();
        List<Element> tables = new ArrayList<>();
        tables = htmlParsingUtil.extractTableFromHTML(Jsoup.parse(sourceHtml), tables);

        Assert.assertEquals(2, tables.size());
        Assert.assertEquals("Jill",tables.get(0).select("td").get(0).text());
    }
}
