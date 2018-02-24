

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class TextFormatterPanel extends JPanel 
{		
	//Declaration of instance variables.
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
	//private TextFormatter T1;
	//Radio buttons
	private JRadioButton right, left;
	private static final int FRAME_WIDTH = 2400;
	private static final int FRAME_HEIGHT = 3600;
	private static final int  FIELD_WIDTH = 10;


	//----------------------------------------------------------------------------------------------------------
	//
	//Constructor
	//
	//----------------------------------------------------------------------------------------------------------
	public TextFormatterPanel () 
	{
		createComponents();
		createPanel();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		add(panel);
		setBackground(Color.orange);
		setPreferredSize(new Dimension(300, 500));

		


	}

	//This method adds components to the panel. 
	private void createPanel()
	{   
		
		panel = new JPanel();

		panel.setLayout(new GridLayout (17, 1));
		panel.setBackground(Color.orange);
		
		panel.add(title);
		
		panel.add(title1);
		
		panel.add(input);
		
		panel.add(inputField);
		
		panel.add(output);
		
		panel.add(outputField);

		panel.add(button);

		panel.add(left);
		panel.add(right);
		
		panel.add(resultLabel);
		
		panel.add(resultLabel1);
		
		panel.add(resultLabel2);
		
		panel.add(resultLabel3);
		
		panel.add(resultLabel4);
		
		
		
		panel.add(resultLabel6);
		
		panel.add(resultLabel7);
		
	}

	//----------------------------------------------------------------------------------------------------------
	//
	//Methods
	//
	//----------------------------------------------------------------------------------------------------------
    
	//This methods creates and initializes all components that will be placed in the panel.
	private void createComponents()
	{
		
		button = new JButton("Format File");
		right = new JRadioButton("Right Justified");
		left = new JRadioButton("Left Justified", true);
		right.setBackground(Color.orange);
		left.setBackground(Color.orange);
	
		ActionListener listener = new ButtonListener();
		button.addActionListener(listener);
		button.setBackground(Color.cyan);

		ActionListener listener1 = new radioButtonListener();
		right.addActionListener(listener1);
		left.addActionListener(listener1);
		

		title = new JLabel("Enter the file name: ");
		
		title1 = new JLabel("Then press " + "\"Format File\"");
		
		input = new JLabel("Input file");

		inputField = new JTextField(FIELD_WIDTH);
		
		output = new JLabel("Output file");


		outputField = new JTextField(FIELD_WIDTH);

		resultLabel = new JLabel("# Words processed: ");

		resultLabel1 = new JLabel("# Lines: ");

		resultLabel2 = new JLabel("# Blank lines removed: ");

		resultLabel3 = new JLabel("Average words per line: ");

		resultLabel4 = new JLabel("Average line length: ");

		

		resultLabel6 = new JLabel(" \n ");

		resultLabel7 = new JLabel("Group 5: Jian Kang, Luis Montano, Carlos Paz, Krishna Sandeep");

		resultLabel.setText("# Words processed: 0");
		resultLabel1.setText("# Lines: 0");
		resultLabel2.setText("# Blank lines removed: 0");
		resultLabel3.setText("Average words per line: 0");
		resultLabel4.setText("Average line lenght: 0");
		
		
		
	}
	
	//This method creates a listener, by asking the user for the input and setting it out accordingly. 
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed (ActionEvent event) 
		{
		
			int in;
			int out;
			
			String enterInput = inputField.getText();
			String getOutput = outputField.getText();


			
			

			if (inputField.getText().isEmpty() && outputField.getText().isEmpty())
			{
				resultLabel.setText("# Words processed: " + Integer.toString(0));
				resultLabel1.setText("# Lines: " + Integer.toString(0));
				resultLabel2.setText("# Blank lines removed: " + Integer.toString(0));
				resultLabel3.setText("Average words per line: "  + Integer.toString(0));
				resultLabel4.setText("Average line lenght: " + Integer.toString(0));
				button.setEnabled(false);
				
			}

			else
			{ 
				in = Integer.parseInt(enterInput);
				out = Integer.parseInt(getOutput);
				button.setEnabled(true);

                //Create object from class
				//t1 = new TextFormatter(in , out);
				
 
				
				/*
				resultLabel.setText("# Words processed " + Integer.toString());
				resultLabel1.setText("# Lines " + Integer.toString());
				resultLabel2.setText("# Blank lines removed " + Integer.toString());
				resultLabel3.setText("Average words per line " + Integer.toString());
				resultLabel4.setText("Average line lenght " + Integer.toString());
				*/

			}
			

		}

	}
	private class radioButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event1)
		{
			Object radioButton = event1.getSource();

			if(radioButton == right )
			{
				right = new JRadioButton("Right Justified", true);
				left = new JRadioButton("Left Justified", false);
			}
			else
			{
				right = new JRadioButton("Right Justified", false);
				left = new JRadioButton("Left Justified", true);
			}
		}
	}
}