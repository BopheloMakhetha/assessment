package integration;

import domain.ProgrammingStack;
import domain.Table;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(TableTest.class)
public class TableTest {


    @Test
    public void testConvertingToJSON() throws Exception{

        Table programmingStack = new ProgrammingStack("Groovy", "Developer friendly especially for scripting, XML and JSON", "");

        Assert.assertEquals("{\"tech\":\"Groovy\",\"reason\":\"Developer friendly especially for scripting, XML and JSON\",\"lifeCycle\":\"\"}", programmingStack.toJSON());
    }
}
