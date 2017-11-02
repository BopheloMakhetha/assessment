package unit;

import domain.TableType;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;
import util.HtmlParsingUtil;

import java.util.Iterator;
import java.util.List;
import static org.mockito.Mockito.*;

public class HtmlParsingUtilTest {
    HtmlParsingUtil htmlParsingUtil = new HtmlParsingUtil();
    Element table = mock(Element.class);
    Elements headings = mock(Elements.class);
    private Element element = mock(Element.class);

    @Test
    public void testThatTablesGetExtractedFromHtml(){
        Iterator<Element> elementIterator = mock(Iterator.class);
        Elements htmlElements = mock(Elements.class);
        List<Element> tableList = mock(List.class);
        Document sourceHtml = mock(Document.class);
        String tableElement = "table";

        when(sourceHtml.select(tableElement)).thenReturn(htmlElements);
        when(htmlElements.iterator()).thenReturn(elementIterator);
        when(elementIterator.hasNext()).thenReturn(true, true,false);
        when(elementIterator.next()).thenReturn(table);

        tableList = htmlParsingUtil.extractTablesFromHTML(sourceHtml, tableList);

        verify(tableList, times(2)).add(table);
    }

    @Test(expected = Exception.class)
    public void testTableClassificationThrowsExceptionForUnsupportedTable() throws Exception {



        when(table.select(anyString())).thenReturn(headings);
        when(headings.size()).thenReturn(2);

        htmlParsingUtil.getTableType(table);
    }

    @Test(expected = Exception.class)
    public void testTableClassificationThrowsExceptionForUnrecognizedColumns()throws Exception {
        when(table.select(anyString())).thenReturn(headings);
        when(headings.size()).thenReturn(4);

        when(headings.get(anyInt())).thenReturn(element);
        when(element.text()).thenReturn("Test Heading");
        htmlParsingUtil.getTableType(table);
    }

    @Test
    public void testTableClassificationWorksProperlyForBuildStack()throws Exception {
        when(table.select(anyString())).thenReturn(headings);
        when(headings.size()).thenReturn(4);
        Element useElement = mock(Element.class);
        Element reasonElement = mock(Element.class);
        Element lifecycleElement = mock(Element.class);

        when(headings.get(0)).thenReturn(element);
        when(element.text()).thenReturn("Tech");
        when(headings.get(1)).thenReturn(useElement);
        when(useElement.text()).thenReturn("Use");
        when(headings.get(2)).thenReturn(reasonElement);
        when(reasonElement.text()).thenReturn("Reason");
        when(headings.get(3)).thenReturn(lifecycleElement);
        when(lifecycleElement.text()).thenReturn("Lifecycle");

        TableType tableType = htmlParsingUtil.getTableType(table);
        Assert.assertEquals(TableType.BUILD_STACK, tableType);
    }

    @Test
    public void testTableClassificationWorksProperlyForProgrammingStack()throws Exception {
        when(table.select(anyString())).thenReturn(headings);
        when(headings.size()).thenReturn(3);
        Element techElement = mock(Element.class);
        Element reasonElement = mock(Element.class);
        Element lifecycleElement = mock(Element.class);

        when(headings.get(0)).thenReturn(techElement);
        when(techElement.text()).thenReturn("Tech");
        when(headings.get(1)).thenReturn(reasonElement);
        when(reasonElement.text()).thenReturn("Reason");
        when(headings.get(2)).thenReturn(lifecycleElement);
        when(lifecycleElement.text()).thenReturn("Lifecycle");

        TableType tableType = htmlParsingUtil.getTableType(table);
        Assert.assertEquals(TableType.PROGRAMMING_STACK, tableType);
    }

    @Test
    public void testTableClassificationWorksProperlyForInfrastructure() throws Exception{
        when(table.select(anyString())).thenReturn(headings);
        when(headings.size()).thenReturn(3);
        Element infraElement = mock(Element.class);
        Element useElement = mock(Element.class);
        Element lifecycleElement = mock(Element.class);

        when(headings.get(0)).thenReturn(infraElement);
        when(infraElement.text()).thenReturn("Infrastructure");
        when(headings.get(1)).thenReturn(useElement);
        when(useElement.text()).thenReturn("Use");
        when(headings.get(2)).thenReturn(lifecycleElement);
        when(lifecycleElement.text()).thenReturn("");

        TableType tableType = htmlParsingUtil.getTableType(table);
        Assert.assertEquals(TableType.INFRASTRUCTURE, tableType);
    }

    @Test
    public void testTableClassificationWorksProperlyForMonitoring() throws Exception{
        when(table.select(anyString())).thenReturn(headings);
        when(headings.size()).thenReturn(3);
        Element techElement = mock(Element.class);
        Element useElement = mock(Element.class);
        Element reasonElement = mock(Element.class);

        when(headings.get(0)).thenReturn(techElement);
        when(techElement.text()).thenReturn("Tech");
        when(headings.get(1)).thenReturn(useElement);
        when(useElement.text()).thenReturn("Use");
        when(headings.get(2)).thenReturn(reasonElement);
        when(reasonElement.text()).thenReturn("Reason");

        TableType tableType = htmlParsingUtil.getTableType(table);
        Assert.assertEquals(TableType.MONITORING, tableType);
    }
}
