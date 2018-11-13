/**
 * @author Benjamin Frederickson
 * This program is my final project for data structures. It simulates a writing program with commands built in. It uses two data structures stack and linked list.
 */

import java.io.FileNotFoundException; // Checks if a file isn't found.
import java.io.PrintWriter; // Allows printing to a file.
import java.util.InputMismatchException; // Allows the inputMisMatch exception to work correctly.
import java.util.Scanner; // Allows user input.
import javax.swing.JOptionPane; // Allows a GUI.

// CURRENT TIME: 17:57 (hours:minutes)

/*
Sprint One - Along with writing the formalized proposal.

Sprint Two - Along with starting to write the user’s manual including screenshots.

Sprint Three - Along with finishing my formalized proposal and user’s manual if not already done.

Sprint Four - I would then write the lessons learned and conclusion/summary of my report.
 */

public class FinalProjectTester
{
	public static void main(String[] args) throws stackEmptyException, linkedListFullException, linkedListEmptyException, FileNotFoundException
	{
		// Values used in main.
		int userInputIndex = 0, userDeleteIndex = 0, undoWordIndex = 0, redoWordIndex = 0;
		String userInput = "", undoWord = "", redoWord = "", userInputInsert = "", deleteValue = "", lastCommand = "", undoTwiceCheck = "";
		Boolean endProgram = false, triggered = false, firstLoop = true, saveYesOrNo = false;
		Scanner userInputScan = new Scanner(System.in);
		
		// Data structures created.
		LinkedList wordsTyped = new LinkedList();
		Stack undo = new Stack();
		Stack redo = new Stack();
		
		// Set up the GUI. Use the GUI to welcome the user to the program, tell them some commands, and finally prompt them to begin typing.
		FinalProjectGUI gui = new FinalProjectGUI();
		gui.setSize(450,200);
		gui.setVisible(true);
		
		do // Do this.
		{
			triggered = false; // Reset the boolean that shows no commands have been entered.
			
			// Act like the loop is the first loop if the wordsType is zero.
			if (wordsTyped.getSize() == 0)
			{
				firstLoop = true; // Set the first loop to true again.
				System.out.println("- - - - - - - - - - - - - - - - - - - - - - -"); // Print this so output looks better.
			}
			// If it is the first loop then block commands and only accept words.
			if (firstLoop == true)
			{
				firstLoop = false; // Set the firstLoop to false.
				
				userInput = userInputScan.next(); // Get the user's next command or word typed.
				
				// If the users input equals a command during the first loop then tell the user to enter a word before using commands.
				if (userInput.equals("InsertWord") || userInput.equals("UndoWord") || userInput.equals("RedoWord") || userInput.equals("DeleteWord"))
				{
					System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
					JOptionPane.showMessageDialog(null, "Please enter in a word before using a command.", "Error Message", 0);
				}
				// COMMANDS COMMAND:
				else if (userInput.equals("CommandsWord") || userInput.equals("commandsword") || userInput.equals("Commandsword") || userInput.equals("commandsWord"))
				{
					System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
					JOptionPane.showMessageDialog(null, "Commands: InsertWord, UndoWord, RedoWord, DeleteWord, EndProgram.", "Commands", 0);
				}
				
				// Else if the user wants to end the program.
				else if (userInput.equals("EndProgram"))
				{
					// Set both of the booleans to true.
					triggered = true;
					endProgram = true;
				}
				// Else insert the users word.
				else 
				{
					// Insert the user's input and push the userInput on both stacks.
					wordsTyped.insertion(userInput);
					
					// If undo and redo are bigger than zero then pop the top value for the new one.
					if (undo.size() > 0)
					{
						undo.pop();
					}
					if (redo.size() > 0)
					{
						redo.pop();
					}
					
					// Push the word onto the redo and undo stacks.
					undo.push(userInput);
					redo.push(userInput);
				}
			}
			// If the size of the linkedList is greater than one, then print out what the user has typed.
			if (wordsTyped.getSize() > 0)
			{
				System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
				System.out.println(wordsTyped.display());
				System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
			}
			// If the user doesn't want the program to end ask for more user input.
			if (endProgram == false)
			{
				userInput = userInputScan.next(); // Get the user's next command or word typed.
			}
			// If the wordsTyped is equal to zero.
			if (wordsTyped.getSize() == 0)
			{
				// Then check if the userInput is equal to a command. If it is then tell the user to enter a words first, reset userInput and set triggered as true.
				if (userInput.equals("InsertWord") || userInput.equals("UndoWord") || userInput.equals("RedoWord") || userInput.equals("DeleteWord"))
				{
					triggered = true;
					userInput = "ENTERWORDFIRST";
					JOptionPane.showMessageDialog(null, "Please enter in a word before using a command.", "Error Message", 0);
				}
				// Else if the user wants to end the program.
				else if (userInput.equals("EndProgram"))
				{
					// Set both of the booleans to true.
					triggered = true;
					endProgram = true;
				}
			}
			// COMMANDS COMMAND:
			if (userInput.equals("CommandsWord") || userInput.equals("commandsword") || userInput.equals("Commandsword") || userInput.equals("commandsWord"))
			{
				JOptionPane.showMessageDialog(null, "Commands: InsertWord, UndoWord, RedoWord, DeleteWord, EndProgram.", "Commands", 0);
				triggered = true;
			}
			
			// INSERTWORD COMMAND:
			if (userInput.equals("InsertWord") || userInput.equals("insertword") || userInput.equals("Insertword") || userInput.equals("insertWord"))
			{
				int biggestSizePossible = (wordsTyped.getSize() + 1); // Set the biggestSizePossible to the size of the linkedList plus one.
				
				// Set triggered to true since a command has occurred, set the undoTwiceCheck to InsertWord, and reset the userInput.
				triggered = true;
				undoTwiceCheck = "InsertWord";
				userInputIndex = 0;
				
				// Do this until the user's output is correct.
				do
				{
					// Try this.
					try 
					{
						// Ask the user for index number.
						System.out.print("Enter a number");
						// If the wordsTyped size is equal to one then print out the only option, one.
						if (wordsTyped.getSize() == 1)
						{
							System.out.print(" (1 - 2): ");
						}
						// If the wordsTyped size is greater than one, then print out one through the biggestSizePossible.
						if (wordsTyped.getSize() > 1)
						{
							System.out.print(" (1 - " + biggestSizePossible + "): ");
						}
						userInputIndex = userInputScan.nextInt(); // Scan for the user's index.
						// If the user's index is bigger than the biggestSizePossible or is equal to 0 throw a exception.
						if (userInputIndex > biggestSizePossible || userInputIndex == 0)
						{
							throw new Exception();
						}
					}
					// If the user enters something other than a number catch the exception and ask them to enter a number.
					catch (InputMismatchException ex)
					{
						System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
						JOptionPane.showMessageDialog(null, "Please enter in a number..", "Error Message", 0);
					}
					// If the user enters something better than the biggestSizePossible or 0 catch the exception and ask them to enter a valid number.
					catch (Exception ex)
					{
						System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
						JOptionPane.showMessageDialog(null, "Please enter in a valid number..", "Error Message", 0);
					}
					userInputScan.nextLine(); // Clear out the scanner.
				} while(userInputIndex <= 0 || userInputIndex > biggestSizePossible);
				
				// Ask the user for what word they want to put into the linkedList.
				System.out.print("Type your word to insert into " + userInputIndex + ": ");
				userInputInsert = userInputScan.next(); // Get the nextLine as the userInputInsert.
				// Set the word and indexValue (minus one so it reflects an actual array) to the function insertionAtIndex.
				wordsTyped.insertionAtIndex(userInputInsert, userInputIndex - 1);
				
				lastCommand = "InsertWord"; // Set the last command to InsertWord.
				
				// If undo and redo are bigger than zero then pop the top value for the new one.
				if (undo.size() > 0)
				{
					undo.pop();
				}
				if (redo.size() > 0)
				{
					redo.pop();
				}
				
				// Push the word onto the redo and undo stacks.
				redo.push(userInputInsert);
				undo.push(userInputInsert);
			}
			// UNDO COMMAND:
			if (userInput.equals("UndoWord") || userInput.equals("undoword") || userInput.equals("Undoword") || userInput.equals("undoWord"))
			{
				// Set triggered to true since a command has occurred.
				triggered = true;
				
				if (undoTwiceCheck.equals("UndoWord"))
				{
					System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
					JOptionPane.showMessageDialog(null, "You cannot undo twice. Please enter a different command or a word..", "Error Message", 0);
				}
				// If the lastCommand was InsertWord or RedoInsertWord, delete the inserted word.
				else if (lastCommand.equals("InsertWord") || lastCommand.equals("RedoInsertWord"))
				{
					undoTwiceCheck = "UndoWord"; // Set the undoTwiceCheck to UndoWord.
					
					undoWord = undo.pop(); // Pop the top value off of undo.
					undoWordIndex = userInputIndex - 1; // Set the undoWordIndex to the one previously used.
					wordsTyped.delete(undoWordIndex);
				}
				// Else if the lastCommand was DeleteWord or RedoDeleteWord, insert the deleteWord back the same index.
				else if (lastCommand.equals("DeleteWord") || lastCommand.equals("RedoDeleteWord"))
				{
					undoTwiceCheck = "UndoWord"; // Set the undoTwiceCheck to UndoWord.
					lastCommand = "UndoDeleteWord";
					
					undoWord = undo.pop(); // Pop the top value off of undo.
					undoWordIndex = userDeleteIndex - 1; // Set the undoWordIndex to the one previously used.
					wordsTyped.insertionAtIndex(undoWord, undoWordIndex);
				}
				// Else delete the last typed word.
				else
				{
					undoTwiceCheck = "UndoWord"; // Set the undoTwiceCheck to UndoWord.
					
					undoWord = undo.pop(); // Pop the top value off of undo.
					
					// Get the size and delete the highest index value..
					undoWordIndex = wordsTyped.getSize();
					wordsTyped.delete(undoWordIndex);
				}
				// If undoTwiceCheck does equal undoWord then do nothing.
				if (undoTwiceCheck.equals("UndoWord"))
				{
					// Do nothing because undoWord was done twice.
				}
				// Else push the undoWord onto the redo stack.
				else
				{
					redo.push(undoWord); // Push the undoWord onto the redo stack.
				}
			}
			
			// REDO COMMAND:
			if (userInput.equals("RedoWord") || userInput.equals("redoword") || userInput.equals("Redoword") || userInput.equals("redoWord"))
			{
				// Set triggered to true since a command has occurred and set the undoTwiceCheck to RedoWord.
				triggered = true;
				undoTwiceCheck = "RedoWord";
				
				redoWord = redo.pop(); // Pop the top word of the redo stack.
				
				// If the lastCommand was InsertWord, insert another word at the same index.
				if (lastCommand.equals("InsertWord"))
				{
					redoWordIndex = userInputIndex - 1;
					wordsTyped.insertionAtIndex(redoWord, redoWordIndex);
					
					lastCommand = "RedoInsertWord"; // Set the last command to RedoInsertWord.
				}
				// If the lastCommand was UndoWord, insert another word at the same index.
				else if (lastCommand.equals("UndoWord"))
				{
					redoWordIndex = undoWordIndex;
					wordsTyped.insertionAtIndex(redoWord, redoWordIndex);
				}
				// If the lastCommand was DeleteWord, do nothing.
				else if (lastCommand.equals("DeleteWord"))
				{
					// This is intentionally left blank, if the user just deleted something the RedoWord shouldn't do anything.
				}
				// If the lastCommand was UndoDeleteWord, delete the word that was just inserted.
				else if (lastCommand.equals("UndoDeleteWord"))
				{
					redoWordIndex = undoWordIndex;
					wordsTyped.delete(undoWordIndex);
					lastCommand = "RedoDeleteWord";
				}
				// Else insert the redoWord.
				else
				{
					wordsTyped.insertion(redoWord);
					lastCommand = "RedoWord"; // Set last command to RedoWord.
				}
				
				// If undo and redo are bigger than zero then pop the top value for the new one.
				if (undo.size() > 0)
				{
					undo.pop();
				}
				if (redo.size() > 0)
				{
					redo.pop();
				}
				
				// Push the redoWord onto the redo and undo stacks.
				redo.push(redoWord);
				undo.push(redoWord);
			}
			
			// DELETE COMMAND:
			if (userInput.equals("DeleteWord") || userInput.equals("deleteword") || userInput.equals("Deleteword") || userInput.equals("deleteWord"))
			{
				int biggestSizePossible = (wordsTyped.getSize()); // Set the biggestSizePossible to the size of the linkedList.
				
				// Set triggered to true since a command has occurred, set the undoTwiceCheck to DeleteWord, and reset the userInput.
				triggered = true;
				undoTwiceCheck = "DeleteWord";
				userDeleteIndex = -1;
				
				// Do this until the user's output is correct.
				do 
				{
					// Try this.
					try
					{
						// Ask the user for an number to delete.
						System.out.print("Enter a number");
						// If the wordsTyped size is equal to one then print out the only option, one.
						if (wordsTyped.getSize() == 1)
						{
							System.out.print(" (1): ");
						}
						// If the wordsTyped size is greater than one, then print out one through the biggestSizePossible.
						if (wordsTyped.getSize() > 1)
						{
							System.out.print(" (1 - " + biggestSizePossible + "): ");
						}
						userDeleteIndex = userInputScan.nextInt(); // Scan for the user's index.
						// If the user's index is bigger than the biggestSizePossible or is equal to 0 throw a exception.
						if (userDeleteIndex > biggestSizePossible || userDeleteIndex == 0)
						{
							throw new Exception();
						}
					}	
					// If the user enters something other than a number catch the exception and ask them to enter a number.
					catch (InputMismatchException ex)
					{
						System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
						JOptionPane.showMessageDialog(null, "Please enter in a number..", "Error Message", 0);
					}
					// If the user enters something better than the biggestSizePossible or 0 catch the exception and ask them to enter a valid number.
					catch (Exception ex)
					{
						System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
						JOptionPane.showMessageDialog(null, "Please enter in a valid number..", "Error Message", 0);
					}
					userInputScan.nextLine(); // Clear the scanner.
				} while(userDeleteIndex <= 0 || userDeleteIndex > biggestSizePossible);
				// If undo and redo is bigger than zero then pop the top value for the new one.
				if (undo.size() > 0)
				{
					undo.pop();
				}
				if (redo.size() > 0)
				{
					redo.pop();
				}
				
				// Get the value to delete for the undo function.
				deleteValue = wordsTyped.search(userDeleteIndex - 1);
				undo.push(deleteValue);
				redo.push(deleteValue);
				
				wordsTyped.delete(userDeleteIndex - 1); // Go into the delete function.
				
				lastCommand = "DeleteWord"; // Set the last command to DeleteWord.
			}
			
			// If the user's input is endProgram exit the do-while loop.
			if (userInput.equals("EndProgram") || userInput.equals("endprogram") || userInput.equals("Endprogram") || userInput.equals("endProgram"))
			{
				// Set both of the booleans to true.
				triggered = true;
				endProgram = true;
			}
			// Else if any other commands haven't been entered insert the userInput.
			else if (triggered == false)
			{
				// If undo and redo are bigger than zero then pop the top value for the new one.
				if (undo.size() > 0)
				{
					undo.pop();
				}
				if (redo.size() > 0)
				{
					redo.pop();
				}
				undoTwiceCheck = "Default"; // Reset the undoTwiceCheck.
				lastCommand = "Default"; // Set the last command to default.
				
				// Insert the user's input and push the userInput on both stacks.
				wordsTyped.insertion(userInput);
				undo.push(userInput);
				redo.push(userInput);
			}
		} while (endProgram == false); // While endProgram is false.
		
		// Ask the user if they want to save what they have typed.
		String userInputGUI = JOptionPane.showInputDialog("Do you want to save the file you have created (Yes or No)?\n(This deletes any information already saved)");
		
		// Do-while loop to make sure the user inputs correct information.
		do 
		{
			// If the user puts yes then save it to a text file and tell them if it was successful.
			if (userInputGUI.equals("yes") || userInputGUI.equals("Yes"))
			{
				saveYesOrNo = true; // Set the boolean to true to get out of the do-while loop.
			
				// Try printing to the file.
				try (PrintWriter out = new PrintWriter("wordDocument.txt"))
				{
					out.println(wordsTyped.display());
				}
				// If successful print a space and send a MessageDialog message to the user.
				JOptionPane.showMessageDialog(null, "Save successful. The file was saved to: \"wordDocument.txt\"", "Success Message", 0);
			}
			else if (userInputGUI.equals("no") || userInputGUI.equals("No"))
			{
				saveYesOrNo = true; // Set the boolean to true to get out of the do-while loop.
				// Do nothing else.
			}
			else
			{
				userInputGUI = JOptionPane.showInputDialog("Do you want to save the file you have created (Yes or No)?");
			}
		} while(saveYesOrNo == false);
		
		// Tell the user thank you and that the program has ended.
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
		System.out.println("Thanks for using the writing program. Please come again!");
		
		userInputScan.close(); // Close the scanner.
	}
}
