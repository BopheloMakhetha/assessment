package assessment;

import domain.TableType;
import domain.WebFile;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import util.HtmlParsingUtil;
import util.WebFileUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private final static Logger LOG = Logger.getLogger(Main.class.getName());
    private static HtmlParsingUtil htmlParsingUtil = new HtmlParsingUtil();

    public static void main(String[] args) throws Exception {
        String url = "https://github.com/egis/handbook/blob/master/Tech-Stack.md";
        try {
            //If this were a spring project, I would autowire webFile, webFileUtil and HtmlParsingUtil. Didn't use spring cause it seemed like an overkill for this solution
            WebFile webFile = new WebFile(new URL(url));
            WebFileUtil webFileUtil = new WebFileUtil();;
            webFileUtil.populateWebfile(webFile);

            List<Element> tables = new ArrayList<>();
            tables = htmlParsingUtil.extractTablesFromHTML(Jsoup.parse(webFile.getFileContent()), tables);

            printTableAsJson(tables);
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage());
        }
    }

    public static void printTableAsJson(List<Element> tables) throws Exception {
        int tableTracker = tables.size();
        for(Element table: tables){
            tableTracker--;
            TableType tableType = htmlParsingUtil.getTableType(table);
            System.out.println("{");
            System.out.println("\""+tableType.toString()+"\": [");
            int rowTracker = table.select(htmlParsingUtil.ROW).size();
            for(Element row : table.select(htmlParsingUtil.ROW)) {
                rowTracker--;
                try {
                    System.out.print(htmlParsingUtil.convertHtmlRowToTableRecord(row, tableType).toJSON());

                    //to ensure we don't display a comma after the last element of the current list
                    if(rowTracker > 0) {
                        System.out.println(",");
                    }else{
                        System.out.println("\n]");
                    }
                }catch(Exception e){
                    continue;
                }
            }
            System.out.print("}");

            //To ensure we don't display a comma after the last list
            if(tableTracker > 0)
                System.out.println(",");

        }
    }
}