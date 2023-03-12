import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class DataFlowWebAppTest {
	
	static DataFlowWebApp app;
	
	@BeforeClass
    public static void setUp() throws InterruptedException {
		app = new DataFlowWebApp();
		app.inputAndSubmitData("1 2\n2 3\n2 4\n3 5\n4 5\n5 6\n5 7\n7 8\n8 5\n7 9", "1", "5", "i 4 8", "i 5,6 5,7 7,9 7,8 8");
    }
	
    @Test
    public void testGetDUpaths() throws InterruptedException{
        ArrayList<String> expectedDUpaths = new ArrayList<String>(Arrays.asList("4,5,7", "4,5,6", "4,5,7,9", "4,5,7,8", "8,5,7", "8,5,6", "8,5,7,8", "8,5,7,9"));
		ArrayList<String> actualDUpaths = app.getDUpaths();
		// assertTrue(actualEdges.containsAll(expectedDUpaths) && actualDUpaths.size() == expectedDUpaths.size());
    }
	
	 @AfterClass
	 public static void tearDown() throws InterruptedException {
		app.shutDown();
	}

}