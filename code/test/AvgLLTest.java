import static org.junit.Assert.*;

import org.junit.Test;

public class AvgLLTest {

	@Test
	public void testLL() {
		TextFormatter test = new TextFormatter();
		test.format("C:\\Users\\Owner\\Documents\\Final_UserStories_360.txt", "C:\\Users\\Owner\\Documents", "left");  							//Final_UserStories_360.txt
		Stats result = test.getStats();
		double avgLL = result.avg_line_length();
		assertEquals(237, avgLL);
	}

}
