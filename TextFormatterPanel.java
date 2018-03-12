import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TextFormatterPanel extends JPanel {
	// Declaration of instance variables.
	private JPanel panel;
	private JButton button;
	private JLabel title;
	private JLabel title1;
	private JLabel input;
	private JTextField inputField;
	private JLabel output;
	private JTextField outputField;
	private JLabel resultLabel;
	private JLabel resultLabel1;
	private JLabel resultLabel2;
	private JLabel resultLabel3;
	private JLabel resultLabel4;
	private JLabel resultLabel6;
	private JLabel resultLabel7;
	private TextFormatter formatter;
	// Radio buttons
	private JRadioButton right;
	private JRadioButton left;
	private JFileChooser fileChooser;
	private JButton inButton;
	private JButton outButton;
	private static final int FRAME_WIDTH = 2400;
	private static final int FRAME_HEIGHT = 3600;
	private static final int FIELD_WIDTH = 10;

	// ----------------------------------------------------------------------------------------------------------
	//
	// Constructor
	//
	// ----------------------------------------------------------------------------------------------------------
	public TextFormatterPanel() {
		formatter = new TextFormatter();
		createComponents();
		createPanel();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		add(panel);
		setBackground(Color.orange);
		setPreferredSize(new Dimension(500, 500));

	}

	// This method adds components to the panel.
	private void createPanel() {

		panel = new JPanel();

		panel.setLayout(new GridLayout(16, 2));
		panel.setBackground(Color.orange);

		panel.add(title);

		panel.add(title1);

		panel.add(input);
		JPanel inPanel = new JPanel();
		inPanel.setBackground(Color.orange);
		inPanel.setLayout(new GridLayout(1, 2));
		inPanel.add(inputField);
		inPanel.add(inButton);
		panel.add(inPanel);

		panel.add(output);
		JPanel outPanel = new JPanel();
		outPanel.setBackground(Color.orange);
		outPanel.setLayout(new GridLayout(1, 2));
		outPanel.add(outputField);
		outPanel.add(outButton);
		panel.add(outPanel);

		JPanel radioPanel = new JPanel(new GridLayout(1, 2));
		radioPanel.setBackground(Color.orange);
		radioPanel.add(left);
		radioPanel.add(right);

		panel.add(radioPanel);

		panel.add(button);

		panel.add(resultLabel);

		panel.add(resultLabel1);

		panel.add(resultLabel2);

		panel.add(resultLabel3);

		panel.add(resultLabel4);

		panel.add(resultLabel6);

		panel.add(resultLabel7);

	}

	// ----------------------------------------------------------------------------------------------------------
	//
	// Methods
	//
	// ----------------------------------------------------------------------------------------------------------

	// This methods creates and initializes all components that will be placed
	// in the panel.
	private void createComponents() {

		button = new JButton("Format File");
		right = new JRadioButton("Right Justified", false);
		left = new JRadioButton("Left Justified", true);

		ActionListener listener = new ButtonListener();
		button.addActionListener(listener);
		button.setBackground(Color.cyan);
		button.setEnabled(false);

		ActionListener listener1 = new radioButtonListener();
		right.addActionListener(listener1);
		left.addActionListener(listener1);

		title = new JLabel("Enter the file name: ");

		title1 = new JLabel("Then press " + "\"Format File\"");

		input = new JLabel("Input file");

		inputField = new JTextField(FIELD_WIDTH);
		inButton = new JButton("Browse Input file");
		inButton.addActionListener(new BrowseButtonListener());
		fileChooser = new JFileChooser();

		output = new JLabel("Output file");
		outButton = new JButton("Browse output directory");
		outButton.addActionListener(new BrowseButtonListener());

		outputField = new JTextField(FIELD_WIDTH);

		resultLabel = new JLabel("# Words processed: ");

		resultLabel1 = new JLabel("# Lines: ");

		resultLabel2 = new JLabel("# Blank lines removed: ");

		resultLabel3 = new JLabel("Average words per line: ");

		resultLabel4 = new JLabel("Average line length: ");

		resultLabel6 = new JLabel(" \n ");

		resultLabel7 = new JLabel("Group 5: Jian Kang, Luis Montano, Carlos Paz, Venkata Maganti");

		resultLabel.setText("# Words processed: 0");
		resultLabel1.setText("# Lines: 0");
		resultLabel2.setText("# Blank lines removed: 0");
		resultLabel3.setText("Average words per line: 0");
		resultLabel4.setText("Average line lenght: 0");

	}

	// This method creates a listener, by asking the user for the input and
	// setting it out accordingly.
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			String inputFilePath = inputField.getText();
			String outputFilePath = outputField.getText();

			if (inputField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(panel, "ERROR: Input file path not specified");
			}else if(outputField.getText().isEmpty()){
				JOptionPane.showMessageDialog(panel, "ERROR: Output file path not specified");
			}else {
				if (left.isSelected()) {
					formatter.format(inputFilePath, outputFilePath, panel, "left");
				} else {
					formatter.format(inputFilePath, outputFilePath, panel, "right");
				}
				Stats s = formatter.getStats();

				resultLabel.setText("# Words processed " + s.words_processed());
				resultLabel1.setText("# Lines " + s.lines_processed());
				resultLabel2.setText("# Blank lines removed " + s.empty_liens());
				resultLabel3.setText("Average words per line " + s.avg_words_per_line());
				resultLabel4.setText("Average line lenght " + s.avg_line_length());
			}

		}

	}

	private class BrowseButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			
			if (event.getSource() == inButton) {
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int val = fileChooser.showOpenDialog(panel);
				if(val==JFileChooser.APPROVE_OPTION){
					button.setEnabled(true);
					inputField.setText(fileChooser.getSelectedFile().getPath());
				}
			}else{
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int val = fileChooser.showOpenDialog(panel);
				if(val==JFileChooser.APPROVE_OPTION){
					outputField.setText(fileChooser.getSelectedFile().getPath());
				}
				
				
			}

		}

	}

	private class radioButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event1) {
			Object radioButton = event1.getSource();

			if (radioButton == right) {
				right.setSelected(true);
				left.setSelected(false);
			} else {
				right.setSelected(false);
				left.setSelected(true);
			}
		}
	}
}