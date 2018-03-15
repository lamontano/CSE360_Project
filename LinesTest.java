import static org.junit.Assert.*;

import org.junit.Test;

public class LinesTest {

	@Test
	public void testLines() {
		TextFormatter test = new TextFormatter();
		test.format("C:\\Users\\Owner\\Documents\\Final_UserStories_360.txt", "C:\\Users\\Owner\\Documents", "left");  							//Final_UserStories_360.txt
		Stats result = test.getStats();
		int lines = result.lines_processed();
		assertEquals(15, lines);
	}

}
