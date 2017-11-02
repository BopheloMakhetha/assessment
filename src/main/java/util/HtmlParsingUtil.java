package util;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

public class HtmlParsingUtil {

    public List<Element> extractTableFromHTML(Document sourceHtml, List<Element> tableList){

        for (Element table : sourceHtml.select("table")) {
            tableList.add(table);
        }
        return tableList;
    }
}

