import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class FinalProjectGUI extends JFrame
{
	// Create a label for the frame.
	private JLabel welcomeMessage, commandInfo, commandInfo2, promptMessage, warningMessage, blank, blank2;
	
	// Default Constructor.
	public FinalProjectGUI()
	{
		super("Welcome!"); // Set the title bar to "Welcome!".
		setLayout(new FlowLayout(FlowLayout.CENTER)); // Set the layout to a flow layout.
		
		// Add the text to each label and add them all to the frame.
		welcomeMessage = new JLabel("Welcome to the writing program!");
		blank = new JLabel("------------------------------------------------------------------------------------------------------");
		commandInfo = new JLabel("Commands: InsertWord, UndoWord, RedoWord, DeleteWord, EndProgram.");
		commandInfo2 = new JLabel("If you would like to see these commands again type: CommandsWord.");
		blank2 = new JLabel("------------------------------------------------------------------------------------------------------");
		promptMessage = new JLabel("To start the program please type a word in the console below.");
		warningMessage = new JLabel("(You cannot use commands that edit words until one word is typed)");
		add(welcomeMessage);
		add(blank);
		add(commandInfo);
		add(commandInfo2);
		add(blank2);
		add(promptMessage);
		add(warningMessage);
	}
}
