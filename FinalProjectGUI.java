/**
 * @author Benjamin Frederickson
 * This program creates several GUI's and implements them into the FinalProjectTester.
 * The functionality for the splash screen was found here: https://stackoverflow.com/questions/16134549/how-to-make-a-splash-screen-for-gui.
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;  
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class FinalProjectGUI
{
	JFrame frame2 = new JFrame("Welcome!"); // Create the base frame for the welcome message and name the window.
	JPanel mainPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Create the panel and set the layout to flow.
	private JLabel commandInfo, commandInfo2, promptMessage, warningMessage, blank; // Create all the labels needed for the welcome message.
	
	
	JFrame frame = new JFrame("Typed Info"); // Create the main frame for the program.
	JPanel mainPanel = new JPanel(new BorderLayout()); // Create the panel and set the layout to border.
	
	// Create two labels for information for the user and the current words typed in the linkedList.
	JLabel wordsTypedInfo = new JLabel ("Currently Typed Information: ", SwingConstants.CENTER);
	JLabel wordsTypedLabel = new JLabel("", SwingConstants.CENTER);
    
    String userInput = ""; // Create a string to be able to hold user input.
    
	// Default Constructor.
	public FinalProjectGUI()
	{
		// Create a splash screen for the user. 
     	JWindow window = new JWindow(); // Create a window to hold the label.
     	window.getContentPane().add( new JLabel("Welcome to the writing program!", SwingConstants.CENTER)); // Create the label.
     	window.setBounds(807, 261, 307, 200); // Set the window's boundaries.
     	window.setVisible(true); // Make the splash screen visible. 
     	try // Try and catch statement to make sure the sleep works correctly.
     	{
     	    Thread.sleep(5000); // Make the program wait until the splash is over.
     	} 
     	catch (InterruptedException e) 
     	{
     	    e.printStackTrace(); // If there is an exception catch it and print the stack trace.
     	}
     	window.setVisible(false); // Make the splash screen not visible. 
     	window.dispose(); // Remove the window since the splash is over.
     	
     	// Set all the labels for the welcome GUI.
     	commandInfo = new JLabel("The commands used in this program are: InsertWord, UndoWord, RedoWord, DeleteWord, EndProgram.");
		commandInfo2 = new JLabel("If you would like to see these commands again type: CommandsWord.");
		blank = new JLabel("_____________________________________________________________________________________________________________");
		promptMessage = new JLabel("To start the program please type a word in the window below.");
		warningMessage = new JLabel("(You cannot use commands that edit words until one word is typed)");
		
		
		// Add them to the panel.
		mainPanel2.add(commandInfo);
		mainPanel2.add(commandInfo2);
		mainPanel2.add(blank);
		mainPanel2.add(promptMessage);
		mainPanel2.add(warningMessage);
		frame2.getContentPane().add(mainPanel2); // Add them to the frame.
     	
		// Set the welcome GUI's frame boundaries and make it visible.
		frame2.setBounds(650, 93, 605, 175);
		frame2.setVisible(true);
     	
		// Set the default close for the main frame and set it's boundaries.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(807, 261, 307, 200);
		
		mainPanel.add(BorderLayout.NORTH, wordsTypedInfo); // Add the label to the panel.
		mainPanel.add(BorderLayout.CENTER, wordsTypedLabel); // Add the label to the panel.
		// Add the panel and make it visible.
		frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
	}
	
	
	/**
	 * This function takes a linkedlist and uses it's display function to get all it's strings. Then it set the strings to the wordsTypedLabel.
	 * @param wordsTyped
	 * @throws linkedListEmptyException
	 */
	public void setWordsTyped(LinkedList wordsTyped) throws linkedListEmptyException
	{
		// Set the text to nothing then use the display function to get the linkedlist's strings and set the label to that.
		wordsTypedLabel.setText(" ");
		wordsTypedLabel.setText("<html>" + wordsTyped.display() + "</html>");
	}
	
	/**
	 * This function updates the text depending on the size of the linked list. This allows the DeleteWord function to work without causing an error.
	 * @param wordsTyped
	 * @throws linkedListEmptyException
	 */
	public void updateWordsTyped(LinkedList wordsTyped) throws linkedListEmptyException
	{
		if (wordsTyped.getSize() == 0)
		{
			// Set the text to nothing since the linkedlist is empty.
			wordsTypedLabel.setText("");
		}
		else
		{
			// Set the text to nothing then use the display function to get the linkedlist's strings and set the label to that.
			wordsTypedLabel.setText("");
			wordsTypedLabel.setText("<html>" + wordsTyped.display() + "</html>");
		}
	}
}
