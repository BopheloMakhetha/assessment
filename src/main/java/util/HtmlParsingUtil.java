package util;

import domain.TableType;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class HtmlParsingUtil {

    private static final String HEADING = "th";

    public List<Element> extractTablesFromHTML(Document sourceHtml, List<Element> tableList) {

        for (Element table : sourceHtml.select("table")) {
            tableList.add(table);
        }
        return tableList;
    }

    public TableType getTableType(Element table) throws Exception {
        Elements headings = table.select(HEADING);

        switch (headings.size()) {
            case 3:
                if (headingIsInHeadings(headings, "Tech", 0)) {
                    if (headingIsInHeadings(headings, "Reason", 1)
                            && headingIsInHeadings(headings, "Lifecycle", 2)) {
                        return TableType.PROGRAMMING_STACK;
                    } else if (headingIsInHeadings(headings, "Use", 1)
                            && headingIsInHeadings(headings, "Reason", 2)) {
                        return TableType.MONITORING;
                    }
                } else if (headingIsInHeadings(headings, "Infrastructure", 0)
                        && headingIsInHeadings(headings, "Use", 1)
                        && headingIsInHeadings(headings, "", 2)) {
                    return TableType.INFRASTRUCTURE;
                }

            case 4:
                if (headingIsInHeadings(headings, "Tech", 0)
                        && headingIsInHeadings(headings, "Use", 1)
                        && headingIsInHeadings(headings, "Reason", 2)
                        && headingIsInHeadings(headings, "Lifecycle", 3))
                    return TableType.BUILD_STACK;
                break;
        }
        throw new Exception("Table format not supported");
    }

    private boolean headingIsInHeadings(Elements elements, String heading, int index) {
        return elements.get(index).text().equals(heading);
    }
}

