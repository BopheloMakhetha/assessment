package util;

import domain.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

import static domain.TableType.*;

public class HtmlParsingUtil {

    private static final String COLUMN = "th";
    private static final String ROW = "tr";

    public List<Element> extractTablesFromHTML(Document sourceHtml, List<Element> tableList) {

        for (Element table : sourceHtml.select("table")) {
            tableList.add(table);
        }
        return tableList;
    }

    public TableType getTableType(Element table) throws Exception {
        Elements headings = table.select(COLUMN);

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
                    return BUILD_STACK;
                break;
        }
        throw new Exception("Table format not supported");
    }


    public Table convertHtmlRowToTableRecord(Element row, TableType tableType) throws  Exception{
        Elements fields = row.select(COLUMN);
        String column1 = fields.get(0).text();
        String column2 = fields.get(1).text();
        String column3 = fields.get(2).text();
        switch (tableType){
            case BUILD_STACK:
                String column4 = fields.get(3).text();
                return new BuildStack(column1, column2, column3, column4);
            case INFRASTRUCTURE:
                return new Infrastructure(column1, column2, column3);
            case MONITORING:
                return new Monitoring(column1, column2, column3);
            case PROGRAMMING_STACK:
                return new ProgrammingStack(column1, column2, column3);
        }
        throw new Exception("UnSupported Table");
    }
    private boolean headingIsInHeadings(Elements elements, String heading, int index) {
        return elements.get(index).text().equals(heading);
    }
}

