package util;

import domain.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

import static domain.TableType.*;

public class HtmlParsingUtil {

    public static final String TABLE_HEADING = "th";
    public static final String ROW = "tr";
    public static final String COLUMN = "td";

    public List<Element> extractTablesFromHTML(Document sourceHtml, List<Element> tableList) {

        for (Element table : sourceHtml.select("table")) {
            tableList.add(table);
        }
        return tableList;
    }

    public TableType getTableType(Element table) throws Exception {
        Elements headings = table.select(TABLE_HEADING);

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

        if(fields.size() > 0) {
            String column1 = extractTextFromElement(fields.get(0));
            String column2 = extractTextFromElement(fields.get(1));
            String column3 = extractTextFromElement(fields.get(2));
            switch (tableType) {
                case BUILD_STACK:
                    String column4 = extractTextFromElement(fields.get(3));
                    return new BuildStack(column1, column2, column3, column4);
                case INFRASTRUCTURE:
                    return new Infrastructure(column1, column2, column3);
                case MONITORING:
                    return new Monitoring(column1, column2, column3);
                case PROGRAMMING_STACK:
                    return new ProgrammingStack(column1, column2, column3);
            }
        }
        throw new Exception("UnSupported Table");
    }
    private boolean headingIsInHeadings(Elements elements, String heading, int index) {
        return elements.get(index).text().equals(heading);
    }

    private String extractTextFromElement(Element element){
        String text;
        try{
            text = element.text();
        }catch (Exception e){
            text = "";
        }
        return text;
    }
}

