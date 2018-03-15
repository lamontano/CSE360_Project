import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.*;

import java.io.File;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TextFormatterPanel extends JPanel {
	// Declarations of instance variables.
	private JPanel panel;

    private JLabel instruction;
    private JLabel instruction_content;

    private JFileChooser input_file_chooser;
    private JLabel input;
    private JTextField input_field;
	private JButton input_btn;
    private JFileChooser output_file_chooser;
    private JLabel output;
    private JTextField output_field;
    private JButton output_btn;

    private JRadioButton right;
    private JRadioButton left;

    private JButton format_file_btn;

    private TextFormatter formatter;
	private JLabel num_of_words_processed;
	private JLabel num_of_lines;
	private JLabel num_of_blank_lines;
	private JLabel avg_words_per_line;
	private JLabel avg_line_length;

	private JLabel padding;
	private JLabel group_members;

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


    // ----------------------------------------------------------------------------------------------------------
    //
    // Methods
    //
    // ----------------------------------------------------------------------------------------------------------
    /*
    create panel and add components
     */
	private void createPanel() {
		panel = new JPanel();

		panel.setLayout(new GridLayout(16, 2));
		panel.setBackground(Color.orange);

		// set instruction
		panel.add(instruction);
		panel.add(instruction_content);

		// input
		panel.add(input);
		JPanel inPanel = new JPanel();
		inPanel.setBackground(Color.orange);
		inPanel.setLayout(new GridLayout(1, 2));
		inPanel.add(input_field);
		inPanel.add(input_btn);
		panel.add(inPanel);

		// output
		panel.add(output);
		JPanel outPanel = new JPanel();
		outPanel.setBackground(Color.orange);
		outPanel.setLayout(new GridLayout(1, 2));
		outPanel.add(output_field);
		outPanel.add(output_btn);
		panel.add(outPanel);

		// radio button
		JPanel radioPanel = new JPanel(new GridLayout(1, 2));
		radioPanel.setBackground(Color.orange);
		radioPanel.add(left);
		radioPanel.add(right);
		panel.add(radioPanel);

		// format file button
		panel.add(format_file_btn);

		// statistics
		panel.add(num_of_words_processed);
		panel.add(num_of_lines);
		panel.add(num_of_blank_lines);
		panel.add(avg_words_per_line);
		panel.add(avg_line_length);

		// group info
		panel.add(padding);
		panel.add(group_members);
	}


    /*
    create and initialize all components
     */
	private void createComponents() {
	    // button and its listeners
        format_file_btn = new JButton("Format File");
		ActionListener btn_listener = new ButtonListener();
        format_file_btn.addActionListener(btn_listener);
        format_file_btn.setBackground(Color.cyan);
        format_file_btn.setEnabled(false);

        // radio buttons and their listeners
        right = new JRadioButton("Right Justified", false);
        left = new JRadioButton("Left Justified", true);
		ActionListener radio_btn_listener = new radioButtonListener();
		right.addActionListener(radio_btn_listener);
		left.addActionListener(radio_btn_listener);

		// instruction
        instruction = new JLabel("INSTRUCTION");
        instruction_content = new JLabel("Choose input and output files. Then press " + "\"Format File\"");

        // txt filter
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");

        // input path
		input = new JLabel("Input file");
        input_field = new JTextField(FIELD_WIDTH);
        input_btn = new JButton("Browse Input file");
        input_btn.addActionListener(new BrowseButtonListener());
        input_file_chooser = new JFileChooser();
        input_file_chooser.setFileFilter(filter);

		// output path
		output = new JLabel("Output file");
        output_btn = new JButton("Browse output directory");
        output_btn.addActionListener(new BrowseButtonListener());
        output_field = new JTextField(FIELD_WIDTH);
        output_file_chooser = new JFileChooser();
        output_file_chooser.setFileFilter(filter);

		// statistics
		num_of_words_processed = new JLabel("# Words processed: ");
		num_of_lines = new JLabel("# Lines: ");
        num_of_blank_lines = new JLabel("# Blank lines removed: ");
        avg_words_per_line = new JLabel("Average words per line: ");
		avg_line_length = new JLabel("Average line length: ");

		// group info
		padding = new JLabel(" \n ");
        group_members = new JLabel("Group 5: Jian Kang, Luis Montano, Carlos Paz, Venkata Maganti");
	}


    /*
    format file button listener
     */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String input_file_path = input_field.getText();
			String output_file_path = output_field.getText();

			if (input_field.getText().isEmpty()) {
				JOptionPane.showMessageDialog(panel, "ERROR: Input file path not specified.");
			} else if(output_field.getText().isEmpty()){
				JOptionPane.showMessageDialog(panel, "ERROR: Output file path not specified.");
			} else {
			    if (input_field.getText().equals(output_field.getText())) {
                    JOptionPane.showMessageDialog(panel, "ERROR: Cannot overwrite the input file.");
                    return;
                }

				if (left.isSelected()) {
					formatter.format(input_file_path, output_file_path, panel, "left");
				} else {
					formatter.format(input_file_path, output_file_path, panel, "right");
				}

				Stats s = formatter.getStats();
				num_of_words_processed.setText("# Words processed: " + s.words_processed());
				num_of_lines.setText("# Lines: " + s.lines_processed());
                num_of_blank_lines.setText("# Blank lines removed: " + s.empty_liens());
				avg_words_per_line.setText("Average words per line: " + s.avg_words_per_line());
				avg_line_length.setText("Average line length: " + s.avg_line_length());
			}
		}
	}


	/*
	Browse button listener
	 */
	private class BrowseButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == input_btn) {
                input_file_chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int val = input_file_chooser.showOpenDialog(panel);
				if(val == JFileChooser.APPROVE_OPTION){
                    format_file_btn.setEnabled(true);
                    input_field.setText(input_file_chooser.getSelectedFile().getPath());
				}
			}else{
                output_file_chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                output_file_chooser.setSelectedFile(new File(".txt"));
                int val = output_file_chooser.showSaveDialog(panel);
				if (val == JFileChooser.APPROVE_OPTION) {
				    String file_name = output_file_chooser.getSelectedFile().getName();
                    String extension = file_name.substring(file_name.length()-4);
                    if (containsIllegals(file_name)) {
                        JOptionPane.showMessageDialog(panel, "ERROR: File name contains one of the illegal characters: \n"
                                + "~, #, @, *, {, }, <, >, |, \", /, \\, ^\n"
                                + "Please change to a different name.");
                        return;
                    }
				    if (file_name.length() < 4) {
                        JOptionPane.showMessageDialog(panel, "ERROR: Output must be a .txt file.");
                        return;
                    }
				    if (file_name.equals(".txt")) {
                        JOptionPane.showMessageDialog(panel, "ERROR: Please name the output file.");
                        return;
                    }
				    if (!extension.equals(".txt")) {
                        JOptionPane.showMessageDialog(panel, "ERROR: Output must be a .txt file.");
                        return;
                    }

                    output_field.setText(output_file_chooser.getSelectedFile().getPath());
				}
			}
		}
	}


	/*
	Radio button listener
	 */
	private class radioButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Object radioButton = event.getSource();

			if (radioButton == right) {
				right.setSelected(true);
				left.setSelected(false);
			} else {
				right.setSelected(false);
				left.setSelected(true);
			}
		}
	}

	/*
	method to check if contains illegal characters
	 */
    public boolean containsIllegals(String toExamine) {
        Pattern pattern = Pattern.compile("[~#@*+%{}<>|\"\\_^]");
        Matcher matcher = pattern.matcher(toExamine);
        return matcher.find();
    }
}