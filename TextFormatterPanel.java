

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

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
	private Change c1;
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

		panel.setLayout(new GridLayout (15,1));
		panel.setBackground(Color.orange);
		
		panel.add(title);
		
		panel.add(title1);
		
		panel.add(input);
		
		panel.add(inputField);
		
		panel.add(output);
		
		panel.add(outputField);

		panel.add(button);
		
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

		ActionListener listener = new ButtonListener();
		button.addActionListener(listener);
		button.setBackground(Color.cyan);
		

		title = new JLabel("Enter the file name or path to file");
		
		title1 = new JLabel("Then press " + "\"Format File\"");
		
		input = new JLabel("Input file");

		inputField = new JTextField(FIELD_WIDTH);
		
		output = new JLabel("Output file");


		outputField = new JTextField(FIELD_WIDTH);

		resultLabel = new JLabel("Number of words processed ");

		resultLabel1 = new JLabel("Number of lines ");

		resultLabel2 = new JLabel("Number of blank lines removed ");

		resultLabel3 = new JLabel("Average words per line ");

		resultLabel4 = new JLabel("Average line length ");

		

		resultLabel6 = new JLabel(" \n ");

		resultLabel7 = new JLabel("Group 5: Jian Kang, Luis Montano, Carlos Paz");

		resultLabel.setText("Numbers of words processed 0");
		resultLabel1.setText("Number of lines 0");
		resultLabel2.setText("Number of blank lines removed 0");
		resultLabel3.setText("Average words per line 0");
		resultLabel4.setText("Average line lenght 0");
		
		
		
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
				resultLabel.setText("Number of words processed " + Integer.toString(0));
				resultLabel1.setText("Number of lines " + Integer.toString(0));
				resultLabel2.setText("Number of blank lines removed " + Integer.toString(0));
				resultLabel3.setText("Average words per line "  + Integer.toString(0));
				resultLabel4.setText("Average line lenght " + Integer.toString(0));
				
			}

			else
			{ 
				in = Integer.parseInt(enterInput);
				out = Integer.parseInt(getOutput);

                //Create object from class
				//t1 = new TextFormatter(in , out);
				
 
				
				/*
				resultLabel.setText("Number of words processed " + Integer.toString());
				resultLabel1.setText("Number of lines " + Integer.toString());
				resultLabel2.setText("Number of blank lines removed " + Integer.toString());
				resultLabel3.setText("Average words per line " + Integer.toString());
				resultLabel4.setText("Average line lenght " + Integer.toString());
				*/

			}
			

		}

	}
}