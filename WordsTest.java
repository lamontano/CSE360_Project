import javax.swing.JPanel;
import static org.junit.Assert.*;
import org.junit.Test;

public class WordsTest {

	@Test
	public void testWords() {
		TextFormatter test = new TextFormatter();
		test.format("C:\\Users\\Owner\\Documents\\Final_UserStories_360.txt", "C:\\Users\\Owner\\Documents", "left");  							//Final_UserStories_360.txt
		Stats result = test.getStats();
		int wordCount = result.words_processed();
		assertEquals(237, wordCount);
	}
}
