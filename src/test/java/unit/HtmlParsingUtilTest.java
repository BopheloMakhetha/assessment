package unit;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import util.HtmlParsingUtil;

import java.util.Iterator;
import java.util.List;
import static org.mockito.Mockito.*;

public class HtmlParsingUtilTest {

    @Test
    public void testThatTablesGetExtractedFromHtml(){
        Iterator<Element> elementIterator = mock(Iterator.class);
        Elements htmlElements = mock(Elements.class);
        List<Element> tableList = mock(List.class);
        Document sourceHtml = mock(Document.class);
        String tableElement = "table";
        Element table = mock(Element.class);

        when(sourceHtml.select(tableElement)).thenReturn(htmlElements);
        when(htmlElements.iterator()).thenReturn(elementIterator);
        when(elementIterator.hasNext()).thenReturn(true, true,false);
        when(elementIterator.next()).thenReturn(table);

        HtmlParsingUtil htmlParsingUtil = new HtmlParsingUtil();
        tableList = htmlParsingUtil.extractTableFromHTML(sourceHtml, tableList);

        verify(tableList, times(2)).add(table);
    }
}
