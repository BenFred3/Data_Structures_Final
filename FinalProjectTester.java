/**
 * @author Benjamin Frederickson
 * This program is my final project for data structures. It simulates a writing program with commands built in. It uses two data structures stack and linked list.
 */

import java.io.FileNotFoundException; // Checks if a file isn't found.
import java.io.PrintWriter; // Allows printing to a file.
import java.util.InputMismatchException; // Allows the inputMisMatch exception to work correctly.
import javax.swing.JOptionPane; // Allows mini GUI pop-ups.

// CURRENT TIME: 34:16 (hours:minutes)

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
		// These integers are used to keep track of index when given by the user.
		int userInputIndex = 0, userDeleteIndex = 0, undoWordIndex = 0, redoWordIndex = 0;
		String userInput = ""; // This holds the userInput when given.
		// These strings hold the word the user has entered/wants to use.
		String undoWord = "", redoWord = "", userInputInsert = "", deleteValue = "";
		// These strings hold a value for the commands used so it can be referenced in different parts of the program.
		String lastCommand = "", undoTwiceCheck = "";		
		// These strings hold the message that will be sent to the user when doing the insert or delete command.
		String insertCommand = "", deleteCommand = "";
		// These booleans are used in if statements to check if different things are true or false.
		Boolean endProgram = false, triggered = false, firstLoop = true, saveYesOrNo = false;
		
		// Here the data-structures are created to be used throughout the program (One linkedlist for the words, undo for the undo command, and redo for the redo command).
		LinkedList wordsTyped = new LinkedList();
		Stack undo = new Stack();
		Stack redo = new Stack();
		
		// Set up the GUI.
		// The GUI is used to welcome the user, get user input, fix errors, and enter commands.
		FinalProjectGUI finalProjectGUI = new FinalProjectGUI();
		
		do // Do this.
		{
			triggered = false; // Reset the boolean that shows no commands have been entered.
			
			// Act like the loop is the first loop if the wordsType is zero.
			if (wordsTyped.getSize() == 0)
			{
				firstLoop = true; // Set the first loop to true again.
			}
			
			// If it is the first loop then block commands and only accept words.
			if (firstLoop == true)
			{
				firstLoop = false; // Set the firstLoop to false.
				
				userInput = JOptionPane.showInputDialog(null, "Please enter a word:", "Type here."); // Get the user's next command or word typed.
				
				// If the user presses the cancel button end the program.
				if (userInput == null)
				{
					userInput = "EndProgram";
				}
				
				// If the users input equals a command during the first loop then tell the user to enter a word before using commands.
				if (userInput.equals("InsertWord") || userInput.equals("UndoWord") || userInput.equals("RedoWord") || userInput.equals("DeleteWord"))
				{
					JOptionPane.showMessageDialog(null, "Please enter in a word before using a command.", "Error Message", 0);
				}
				// COMMANDS COMMAND (for first loop):
				else if (userInput.equals("CommandsWord") || userInput.equals("commandsword") || userInput.equals("Commandsword") || userInput.equals("commandsWord"))
				{
					JOptionPane.showMessageDialog(null, "Commands: InsertWord, UndoWord, RedoWord, DeleteWord, EndProgram.", "Commands", 0);
				}
				// Else if the user wants to end the program.
				else if (userInput.equals("EndProgram"))
				{
					// Set both of the booleans to true to allow the program to end.
					triggered = true;
					endProgram = true;
				}
				// Else insert the users word.
				else 
				{
					// If the user's string has white space do this.
					if (userInput.contains(" "))
					{
						// Create a String array with all the words.
						String[] splited = userInput.split("\\s+");
						
						// Insert them one at a time.
						for (int i = 0; i < splited.length; i++)
						{
							// Insert each one, one at a time.
							wordsTyped.insertion(splited[i]);
							
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
							redo.push(splited[i]);
							undo.push(splited[i]);
						}
					}
					else
					{
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
			}
			
			finalProjectGUI.frame2.setVisible(false); // Set the info frame to non visible.
			
			// If the size of the linkedList is greater than one, then print out what the user has typed.
			if (wordsTyped.getSize() > 0)
			{
				finalProjectGUI.setWordsTyped(wordsTyped);
			}
			
			// If the user doesn't want the program to end ask for more user input.
			if (endProgram == false)
			{
				userInput = JOptionPane.showInputDialog("Please enter a word or a command:"); // Get the user's next command or word typed.
			}
			
			// If the user presses the cancel button end the program.
			if (userInput == null)
			{
				userInput = "EndProgram";
			}
			
			// If the wordsTyped is equal to zero.
			if (wordsTyped.getSize() == 0)
			{
				// Then check if the userInput is equal to a command. If it is then tell the user to enter a word first, reset userInput and set triggered as true.
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
				// Show a message showing the commands and set the boolean triggered to true.
				JOptionPane.showMessageDialog(null, "Commands: InsertWord, UndoWord, RedoWord, DeleteWord, EndProgram.", "Commands", JOptionPane.INFORMATION_MESSAGE);
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
						insertCommand = ("Enter a number");
						
						// If the wordsTyped size is equal to one then print out one through two.
						if (wordsTyped.getSize() == 1)
						{
							insertCommand = JOptionPane.showInputDialog("Please enter a number (1 - 2):");
						}
						
						// If the wordsTyped size is greater than one, then print out one through the biggestSizePossible.
						if (wordsTyped.getSize() > 1)
						{
							insertCommand = JOptionPane.showInputDialog("Please enter a number (1 - " + biggestSizePossible + "): ");
						}
						userInputIndex = Integer.parseInt(insertCommand); // Take the user input and make it a integer with parse int.
						
						// If the user's index is bigger than the biggestSizePossible or is equal to zero throw a exception.
						if (userInputIndex > biggestSizePossible || userInputIndex == 0)
						{
							throw new Exception();
						}
					}
					// If the user enters something other than a number catch the exception and ask them to enter a number.
					catch (InputMismatchException ex)
					{
						JOptionPane.showMessageDialog(null, "Please enter in a number..", "Error Message", 0);
					}
					// If the user enters something better than the biggestSizePossible or zero catch the exception and ask them to enter a valid number.
					catch (Exception ex)
					{
						JOptionPane.showMessageDialog(null, "Please enter in a valid number..", "Error Message", 0);
					}
				} while(userInputIndex <= 0 || userInputIndex > biggestSizePossible); // While the user's input isn't correct.
				
				// Ask the user for what word they want to put into the linkedList.
				userInputInsert = JOptionPane.showInputDialog("Type your word to insert into " + userInputIndex + ":");
				
				if (userInputInsert.contains(" "))
				{
					// Create a String array with all the words.
					String[] splited = userInputInsert.split("\\s+");
					
					// insert them one at a time.
					for (int i = 0; i < splited.length; i++)
					{
						// Set the word and indexValue (minus one so it reflects an actual array) to the function insertionAtIndex.
						wordsTyped.insertionAtIndex(splited[i], userInputIndex - 1);
						
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
						redo.push(splited[i]);
						undo.push(splited[i]);
						
						// If i plus one is equal to splited.length then dont increase the index.
						if ((i + 1) != splited.length)
						{
							userInputIndex++; // Increase the current index so the next string is inserted correctly.
						}
					}
				}
				// Else do this.
				else
				{
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
				
				finalProjectGUI.updateWordsTyped(wordsTyped); // Update the words typed.
			}
			
			// UNDOWORD COMMAND:
			if (userInput.equals("UndoWord") || userInput.equals("undoword") || userInput.equals("Undoword") || userInput.equals("undoWord"))
			{
				// Set triggered to true since a command has occurred.
				triggered = true;
				
				// If the user attempts to undo twice send an error message and tell them to enter a different command or word.
				if (undoTwiceCheck.equals("UndoWord"))
				{
					JOptionPane.showMessageDialog(null, "You cannot undo twice. Please enter a different command or a word..", "Error Message", 0);
				}
				// If the lastCommand was InsertWord or RedoInsertWord, delete the inserted word.
				else if (lastCommand.equals("InsertWord") || lastCommand.equals("RedoInsertWord"))
				{
					undoTwiceCheck = "UndoWord"; // Set the undoTwiceCheck to UndoWord.
					
					undoWord = undo.pop(); // Pop the top value off of undo.
					undoWordIndex = userInputIndex - 1; // Set the undoWordIndex to the one previously used.
					wordsTyped.delete(undoWordIndex); // Delete the word.
				}
				// Else if the lastCommand was DeleteWord or RedoDeleteWord, insert the deleteWord back the same index.
				else if (lastCommand.equals("DeleteWord") || lastCommand.equals("RedoDeleteWord"))
				{
					undoTwiceCheck = "UndoWord"; // Set the undoTwiceCheck to UndoWord.
					lastCommand = "UndoDeleteWord";
					
					undoWord = undo.pop(); // Pop the top value off of undo.
					undoWordIndex = userDeleteIndex - 1; // Set the undoWordIndex to the one previously used.
					wordsTyped.insertionAtIndex(undoWord, undoWordIndex); // Insert the word.
				}
				// Else delete the last typed word.
				else
				{
					undoTwiceCheck = "UndoWord"; // Set the undoTwiceCheck to UndoWord.
					undoWord = undo.pop(); // Pop the top value off of undo.
					
					// Get the size and delete the highest index value.
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
				finalProjectGUI.updateWordsTyped(wordsTyped); // Update the words typed.
			}
			
			// REDOWORD COMMAND:
			if (userInput.equals("RedoWord") || userInput.equals("redoword") || userInput.equals("Redoword") || userInput.equals("redoWord"))
			{
				// Set triggered to true since a command has occurred and set the undoTwiceCheck to RedoWord.
				triggered = true;
				undoTwiceCheck = "RedoWord";
				
				redoWord = redo.pop(); // Pop the top word of the redo stack.
				
				// If the lastCommand was InsertWord, insert another word at the same index.
				if (lastCommand.equals("InsertWord") || lastCommand.equals("RedoInsertWord"))
				{
					redoWordIndex = userInputIndex - 1; // Set the redo index.
					wordsTyped.insertionAtIndex(redoWord, redoWordIndex); // Insert the word.
					
					lastCommand = "RedoInsertWord"; // Set the last command to RedoInsertWord.
				}
				// If the lastCommand was UndoWord, insert another word at the same index.
				else if (lastCommand.equals("UndoWord"))
				{
					redoWordIndex = undoWordIndex; // Set the redo index.
					wordsTyped.insertionAtIndex(redoWord, redoWordIndex); // Insert the word.
				}
				// If the lastCommand was DeleteWord, do nothing.
				else if (lastCommand.equals("DeleteWord"))
				{
					// This is intentionally left blank, if the user just deleted something the RedoWord shouldn't do anything.
				}
				// If the lastCommand was UndoDeleteWord, delete the word that was just inserted.
				else if (lastCommand.equals("UndoDeleteWord"))
				{
					redoWordIndex = undoWordIndex; // Set the index.
					wordsTyped.delete(undoWordIndex); // Delete the word.
					lastCommand = "RedoDeleteWord"; // Set last command to RedoDeleteWord.
				}
				// Else insert the redoWord.
				else
				{
					wordsTyped.insertion(redoWord); // Insert the word.
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
				
				finalProjectGUI.updateWordsTyped(wordsTyped); // Update the words typed.
			}
			
			// DELETEWORD COMMAND:
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
						// Ask the user for index number.
						deleteCommand = ("Enter a number");
						// If the wordsTyped size is equal to one then print out the only option, one.
						if (wordsTyped.getSize() == 1)
						{
							deleteCommand = JOptionPane.showInputDialog("Please enter a number (1):");
						}
						// If the wordsTyped size is greater than one, then print out one through the biggestSizePossible.
						if (wordsTyped.getSize() > 1)
						{
							deleteCommand = JOptionPane.showInputDialog("Please enter a number (1 - " + biggestSizePossible + "): ");
						}
						userDeleteIndex = Integer.parseInt(deleteCommand); // Take the user input and make it a integer with parse int.
						
						// If the user's index is bigger than the biggestSizePossible or is equal to zero throw a exception.
						if (userDeleteIndex > biggestSizePossible || userDeleteIndex == 0)
						{
							throw new Exception();
						}
					}	
					// If the user enters something other than a number catch the exception and ask them to enter a number.
					catch (InputMismatchException ex)
					{
						JOptionPane.showMessageDialog(null, "Please enter in a number..", "Error Message", 0);
					}
					// If the user enters something better than the biggestSizePossible or zero catch the exception and ask them to enter a valid number.
					catch (Exception ex)
					{
						JOptionPane.showMessageDialog(null, "Please enter in a valid number..", "Error Message", 0);
					}
				} while(userDeleteIndex <= 0 || userDeleteIndex > biggestSizePossible); // While the user's input isn't correct.
				
				// If undo and redo is bigger than zero then pop the top value for the new one.
				if (undo.size() > 0)
				{
					undo.pop();
				}
				if (redo.size() > 0)
				{
					redo.pop();
				}
				
				// Delete the word at the user's given index and push the userInput on both stacks.
				deleteValue = wordsTyped.search(userDeleteIndex - 1);
				undo.push(deleteValue);
				redo.push(deleteValue);
				
				wordsTyped.delete(userDeleteIndex - 1); // Go into the delete function.
				
				lastCommand = "DeleteWord"; // Set the last command to DeleteWord.
				
				finalProjectGUI.updateWordsTyped(wordsTyped); // Update the words typed.
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
				finalProjectGUI.frame2.setVisible(false); // Set the info frame to non visible. 
				
				// If the user's string has white space do this.
				if (userInput.contains(" "))
				{
					// Create a String array with all the words.
					String[] splited = userInput.split("\\s+");
					
					// Insert them one at a time.
					for (int i = 0; i < splited.length; i++)
					{
						// Insert each one, one at a time.
						wordsTyped.insertion(splited[i]);
						
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
						redo.push(splited[i]);
						undo.push(splited[i]);
					}
				}
				else
				{
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
				
				undoTwiceCheck = "Default"; // Reset the undoTwiceCheck.
				lastCommand = "Default"; // Set the last command to default.
			}
		} while (endProgram == false); // While endProgram is false.
		
		if (wordsTyped.getSize() > 0)
		{
			// Ask the user if they want to save what they have typed.
			String userInputGUI = JOptionPane.showInputDialog("Do you want to save what you have written (Yes or No)?\n(This deletes any information already saved)");
		
			// If the user presses the cancel button allow the program to end without a null exception.
			if (userInputGUI == null)
			{
				userInputGUI = "no";
			}
		
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
					JOptionPane.showMessageDialog(null, "Save successful. The file was saved to: \"wordDocument.txt\"", "Success Message", JOptionPane.INFORMATION_MESSAGE);
				}
				else if (userInputGUI.equals("no") || userInputGUI.equals("No"))
				{
					saveYesOrNo = true; // Set the boolean to true to get out of the do-while loop.
					// Do nothing else.
				}
				else
				{
					userInputGUI = JOptionPane.showInputDialog("Incorrect Input.. Please try again:\nDo you want to save what you have written (Yes or No)?");
				}
			} while(saveYesOrNo == false);
		}
		
		// Tell the user thank you and that the program has ended.
		JOptionPane.showMessageDialog(null, "Thanks for using the writing program. Please come again!", "End Message", JOptionPane.INFORMATION_MESSAGE);
		
		// Hide the GUI if it is open cause the program is ending.
		finalProjectGUI.frame.setVisible(false); 
		finalProjectGUI.frame2.setVisible(false);
	}
}