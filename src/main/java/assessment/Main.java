package assessment;

import domain.WebFile;
import util.WebFileUtil;

public class Main {

    public static void main(String[] args) {
        try {
            WebFile webFile = new WebFile("https://github.com/egis/handbook/blob/master/Tech-Stack.md");
            WebFileUtil webFileUtil = new WebFileUtil();
            webFileUtil.populateWebfile(webFile);
            System.out.print(webFile.getFileContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}