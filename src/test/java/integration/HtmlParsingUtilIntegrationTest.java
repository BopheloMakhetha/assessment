package integration;

import domain.Table;
import domain.TableType;
import domain.WebFile;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import util.HtmlParsingUtil;
import util.WebFileUtil;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Category(HtmlParsingUtil.class)
public class HtmlParsingUtilIntegrationTest {

    String sourceHtml;
    HtmlParsingUtil htmlParsingUtil = new HtmlParsingUtil();

    @Before
    public void setup() throws Exception{
        URL url = new URL("https://github.com/egis/handbook/blob/master/Tech-Stack.md");
        WebFile webFile = new WebFile(url);
        WebFileUtil webFileUtil = new WebFileUtil();
        webFileUtil.populateWebfile(webFile);
        sourceHtml = webFile.getFileContent();
    }

    @Test
    public void testThatTablesGetExtractedFromHtml(){
        List<Element> tables = new ArrayList<>();
        tables = htmlParsingUtil.extractTablesFromHTML(Jsoup.parse(sourceHtml), tables);

        Assert.assertEquals(4, tables.size());
        Assert.assertEquals("Tech",tables.get(0).select("th").get(0).text());
    }

    @Test
    public void testThatTableClassificationWorksForProgrammingStack() throws Exception {
        List<Element> tables = new ArrayList<>();
        tables = htmlParsingUtil.extractTablesFromHTML(Jsoup.parse(sourceHtml), tables);

        TableType tableType = htmlParsingUtil.getTableType(tables.get(0));

        Assert.assertEquals(TableType.PROGRAMMING_STACK, tableType);
    }

    @Test
    public void testThatTableClassificationWorksForBuildStack() throws Exception {
        List<Element> tables = new ArrayList<>();
        tables = htmlParsingUtil.extractTablesFromHTML(Jsoup.parse(sourceHtml), tables);

        TableType tableType = htmlParsingUtil.getTableType(tables.get(1));

        Assert.assertEquals(TableType.BUILD_STACK, tableType);
    }

    @Test
    public void testThatTableClassificationWorksForInfrastructure() throws Exception {
        List<Element> tables = new ArrayList<>();
        tables = htmlParsingUtil.extractTablesFromHTML(Jsoup.parse(sourceHtml), tables);

        TableType tableType = htmlParsingUtil.getTableType(tables.get(2));

        Assert.assertEquals(TableType.INFRASTRUCTURE, tableType);
    }

    @Test
    public void testThatTableClassificationWorksForMonitoring() throws Exception {
        List<Element> tables = new ArrayList<>();
        tables = htmlParsingUtil.extractTablesFromHTML(Jsoup.parse(sourceHtml), tables);

        TableType tableType = htmlParsingUtil.getTableType(tables.get(3));

        Assert.assertEquals(TableType.MONITORING, tableType);
    }

    @Test
    public void testValidTableRowsGetConvertedToTables() throws Exception {
        List<Element> tables = new ArrayList<>();
        tables = htmlParsingUtil.extractTablesFromHTML(Jsoup.parse(sourceHtml), tables);
        Element programmingStackRecord = tables.get(0).select("tr").first();
        Element buildStackRecord = tables.get(1).select("tr").first();
        Element infrastructureRecord = tables.get(2).select("tr").first();
        Element monitoringingRecord = tables.get(3).select("tr").first();

        Table programmingStack = htmlParsingUtil.convertHtmlRowToTableRecord(programmingStackRecord, TableType.PROGRAMMING_STACK);
        Table buildStack = htmlParsingUtil.convertHtmlRowToTableRecord(buildStackRecord, TableType.BUILD_STACK);
        Table infrastructure = htmlParsingUtil.convertHtmlRowToTableRecord(infrastructureRecord, TableType.INFRASTRUCTURE);
        Table monitoring = htmlParsingUtil.convertHtmlRowToTableRecord(monitoringingRecord, TableType.MONITORING);

        Assert.assertTrue(programmingStack.getClass().getName().equals("domain.ProgrammingStack"));
        Assert.assertTrue(buildStack.getClass().getName().equals("domain.BuildStack"));
        Assert.assertTrue(infrastructure.getClass().getName().equals("domain.Infrastructure"));
        Assert.assertTrue(monitoring.getClass().getName().equals("domain.Monitoring"));
    }
}
