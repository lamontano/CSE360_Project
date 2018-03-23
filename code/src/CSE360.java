import javax.swing.JFrame;


public class CSE360 {
	public static void main (String[] args) {
		JFrame frame = new JFrame ("TextFormatter");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		TextFormatterPanel panel = new TextFormatterPanel();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}