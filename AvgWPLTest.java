import static org.junit.Assert.*;

import org.junit.Test;

public class AvgWPLTest {

	@Test
	public void testWPL() {
		TextFormatter test = new TextFormatter();
		test.format("C:\\Users\\Owner\\Documents\\Final_UserStories_360.txt", "C:\\Users\\Owner\\Documents", "left");  							//Final_UserStories_360.txt
		Stats result = test.getStats();
		double avgWPL = result.avg_words_per_line();
		assertEquals(237, avgWPL);
	}

}
