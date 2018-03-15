import static org.junit.Assert.*;

import org.junit.Test;

public class BlankLinesTest {

	@Test
	public void testBL() {
		TextFormatter test = new TextFormatter();
		test.format("C:\\Users\\Owner\\Documents\\Final_UserStories_360.txt", "C:\\Users\\Owner\\Documents", "left");  							//Final_UserStories_360.txt
		Stats result = test.getStats();
		int blankLines = result.empty_lines();
		assertEquals(7, blankLines);
	}

}
