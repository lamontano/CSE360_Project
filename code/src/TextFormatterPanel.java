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
    private JLabel instruction_padding;

    private JFileChooser input_file_chooser;
    private JLabel input;
    private JTextField input_field;
	private JButton input_btn;
    private JFileChooser output_file_chooser;
    private JLabel output;
    private JTextField output_field;
    private JLabel line;
    private JTextField user_line_length;    //phase 2 update
    private JButton output_btn;

    private JRadioButton right;
    private JRadioButton left;
    private JRadioButton full;     //phase 2 update

    //Radio buttons for single or double spacing
    private JRadioButton single_space;
    private JRadioButton double_space;

    private JButton format_file_btn;

    private TextFormatter formatter;
	private JLabel num_of_words_processed;
	private JLabel num_of_lines;
	private JLabel num_of_blank_lines;
	private JLabel num_of_spaces_added;
	private JLabel avg_words_per_line;
	private JLabel avg_line_length;
	private JLabel justify_label;
	private JLabel space_label;

	private JLabel padding;
	private JLabel group_members;

	private static final int FRAME_WIDTH = 1600;
	private static final int FRAME_HEIGHT = 2400;
	private static final int FIELD_WIDTH = 10;
	private static final int LINE_WIDTH = 5;

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
		setPreferredSize(new Dimension(500, 600));
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

		panel.setLayout(new GridLayout(25, 3));
		panel.setBackground(Color.orange);

		// set instruction
		panel.add(instruction);
		panel.add(instruction_content);
		panel.add(instruction_padding);

		// input
		panel.add(input);
		JPanel inPanel = new JPanel();
		inPanel.setBackground(Color.orange);
		inPanel.setLayout(new GridLayout(1, 3));
		inPanel.add(input_field);
		inPanel.add(input_btn);
		panel.add(inPanel);

		// output
		panel.add(output);
		JPanel outPanel = new JPanel();
		outPanel.setBackground(Color.orange);
		outPanel.setLayout(new GridLayout(1, 3));
		outPanel.add(output_field);
		outPanel.add(output_btn);
		panel.add(outPanel);

		// line length
		panel.add(line);
		JPanel linePanel = new JPanel();
		linePanel.setBackground(Color.orange);
		linePanel.setLayout(new GridLayout(1, 3));
		linePanel.add(user_line_length);
		panel.add(linePanel);

		// radio button
		panel.add(justify_label);
		JPanel radioPanel = new JPanel(new GridLayout(1, 3));
		radioPanel.setBackground(Color.orange);
		radioPanel.add(left);
		radioPanel.add(right);
		radioPanel.add(full);
		panel.add(radioPanel);


		// spacing radio button
		panel.add(space_label);
		JPanel radioPanel1 = new JPanel(new GridLayout (1, 2));
		radioPanel1.setBackground(Color.orange);
		radioPanel1.add(single_space);
		radioPanel1.add(double_space);
		panel.add(radioPanel1);

		// format file button
		panel.add(format_file_btn);

		// statistics
		panel.add(num_of_words_processed);
		panel.add(num_of_lines);
		panel.add(num_of_blank_lines);
		panel.add(num_of_spaces_added);
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
        justify_label = new JLabel("Type of justification");
        right = new JRadioButton("Right Justified", false);
        left = new JRadioButton("Left Justified", true);
        full = new JRadioButton("Full Justified", false);
		ActionListener radio_btn_justify_listener = new radioButtonListener();
		right.setBackground(Color.orange);
		left.setBackground(Color.orange);
		full.setBackground(Color.orange);
		right.addActionListener(radio_btn_justify_listener);
		left.addActionListener(radio_btn_justify_listener);
		full.addActionListener(radio_btn_justify_listener);

		//single and double space radio buttons
		space_label = new JLabel("Spacing");
		single_space = new JRadioButton("Single Space", true);
		double_space = new JRadioButton("Double Space", false);
		ActionListener radio_btn_space_listener = new radioButtonListener1();
		single_space.setBackground(Color.orange);
		double_space.setBackground(Color.orange);
		single_space.addActionListener(radio_btn_space_listener);
		double_space.addActionListener(radio_btn_space_listener);

		// instruction
        instruction = new JLabel("INSTRUCTIONS");
        instruction_content = new JLabel("Choose from the following options. Then press " + "\"Format File\"");
        instruction_padding = new JLabel("");

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

        /* Need to work on this */
        // user defined line length
        line = new JLabel("Line length");
        user_line_length = new JTextField(LINE_WIDTH);


		// statistics
		num_of_words_processed = new JLabel("# Words processed: ");
		num_of_lines = new JLabel("# Lines: ");
        num_of_blank_lines = new JLabel("# Blank lines removed: ");
        num_of_spaces_added = new JLabel("# Spaces added: ");
        avg_words_per_line = new JLabel("Average words per line: ");
		avg_line_length = new JLabel("Average line length: ");

		// group info
		padding = new JLabel(" \n ");
        group_members = new JLabel("Group 5: Jian Kang, Venkata Maganti, Luis Montano, Carlos Paz");
	}


    /*
    format file button listener
     */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
		    int line_length = 0;
			String input_file_path = input_field.getText();
			String output_file_path = output_field.getText();
			if (user_line_length.getText().length() == 0) {
                JOptionPane.showMessageDialog(panel, "ERROR: Line length not specified.");
            } else {
                try {
                    line_length = Integer.parseInt(user_line_length.getText());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(panel, "ERROR: Line length must be an integer.");
                }
            }

			if (input_field.getText().isEmpty()) {
				JOptionPane.showMessageDialog(panel, "ERROR: Input file path not specified.");
			} else if(output_field.getText().isEmpty()){
				JOptionPane.showMessageDialog(panel, "ERROR: Output file path not specified.");
			} else {
			    if (input_field.getText().equals(output_field.getText())) {
                    JOptionPane.showMessageDialog(panel, "ERROR: Cannot overwrite the input file.");
                    return;
                }

                String direction = "left";
			    String spacing = "single";

			    // set justification
			    if (left.isSelected()) {
			    	direction = "left";
				}
				if (right.isSelected()) {
			    	direction = "right";
				}
				if (full.isSelected()) {
			    	direction = "full";
				}

				// set spacing
				if (single_space.isSelected()) {
			    	spacing = "single";
				}
				if (double_space.isSelected()) {
			    	spacing = "double";
				}

				formatter.format(panel, line_length, input_file_path, output_file_path, direction, spacing);

//				if (left.isSelected()) {
//					formatter.format(line_length, input_file_path, output_file_path, panel, "left");
//				} else if (right.isSelected()) {
//					formatter.format(line_length, input_file_path, output_file_path, panel, "right");
//				} else {
//			    	formatter.format(line_length, input_file_path, output_file_path, panel, "full");
//				}

				Stats s = formatter.getStats();
				num_of_words_processed.setText("# Words processed: " + s.words_processed());
				num_of_lines.setText("# Lines: " + s.lines_processed());
                num_of_blank_lines.setText("# Blank lines removed: " + s.empty_liens());
                num_of_spaces_added.setText("# Spaces added: " + s.spaces_added());
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
					String file_name = input_file_chooser.getSelectedFile().getName();
					String extension = file_name.substring(file_name.length()-4);
					if (!extension.equals(".txt")) {
						JOptionPane.showMessageDialog(panel, "ERROR: Please choose a .txt file.");
						return;
					}
                    format_file_btn.setEnabled(true);
                    input_field.setText(input_file_chooser.getSelectedFile().getPath());
				}
			} else {
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

			// set justification
			if (radioButton == right) {
				right.setSelected(true);
				left.setSelected(false);
				full.setSelected(false);
			}
			if (radioButton == left) {
				right.setSelected(false);
				left.setSelected(true);
				full.setSelected(false);
			}
			if (radioButton == full){
				right.setSelected(false);
				left.setSelected(false);
				full.setSelected(true);
			}
		}
	}


	/*
	Radio button listener
	 */
	private class radioButtonListener1 implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Object radioButton = event.getSource();

			if (radioButton == single_space) {
				single_space.setSelected(true);
				double_space.setSelected(false);

			}
			if (radioButton == double_space){
				single_space.setSelected(false);
				double_space.setSelected(true);

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