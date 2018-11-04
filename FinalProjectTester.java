/**
 * @author Benjamin Frederickson
 * This program is my final project for data structures. It simulates a writing program with commands built in. It uses two data structures,
 * stack and linked list.
 */

import java.util.InputMismatchException; // Allows the inputMisMatch exception to work correctly.
import java.util.Scanner; // Allows user input.

// CURRENT TIME: 13:37 (hours:minutes)

/*
Sprint One- JAVA DOC, Along with writing the formalized proposal.

Sprint Two- Along with starting to write the user’s manual including screenshots.

Sprint Three- I would add user input validation to ensure no errors would occur. 
Along with finishing my formalized proposal and user’s manual if not already done.

Sprint Four - I would polish my program making sure I hadn't left out any features I had planned to and fix any errors that the program still had. 
I would then write the lessons learned and conclusion/summary of my report.
 */

public class FinalProjectTester
{
	public static void main(String[] args) throws stackEmptyException, linkedListFullException, linkedListEmptyException
	{
		// Values used in main.
		int userInputIndex = 0, userDeleteIndex = 0, undoWordIndex = 0, redoWordIndex = 0;
		String userInput = "", undoWord = "", redoWord = "", userInputInsert = "", deleteValue = "", lastCommand = "", undoTwiceCheck = "";
		Boolean endProgram = false, triggered = false, firstLoop = true;
		Scanner userInputScan = new Scanner(System.in);
		
		// Data structures created.
		LinkedList wordsTyped = new LinkedList();
		Stack undo = new Stack();
		Stack redo = new Stack();
		
		// Welcome the user to the program and prompt them to begin typing.
		System.out.println("Welcome to the writing program!\nKeywords: InsertWord, UndoWord, RedoWord, DeleteWord, EndProgram.");
		System.out.println("Please begin typing a word at any time..");
		
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
				
				userInput = userInputScan.nextLine(); // Get the user's next command or word typed.
				
				// If the users input equals a command during the first loop then tell the user to enter a word before using commands.
				if (userInput.equals("InsertWord") || userInput.equals("UndoWord") || userInput.equals("RedoWord") || userInput.equals("DeleteWord"))
				{
					System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
					System.out.println("Please enter in a word before using a command.");
					System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
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
				userInput = userInputScan.nextLine(); // Get the user's next command or word typed.
			}
			// If the wordsTyped is equal to zero.
			if (wordsTyped.getSize() == 0)
			{
				// Then check if the userInput is equal to a command. If it is then tell the user to enter a words first, reset userInput and set triggered 
				// as true.
				if (userInput.equals("InsertWord") || userInput.equals("UndoWord") || userInput.equals("RedoWord") || userInput.equals("DeleteWord"))
				{
					triggered = true;
					userInput = "ENTERWORDFIRST";
					System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
					System.out.println("Please enter in a word before using a command.");
				}
				// Else if the user wants to end the program.
				else if (userInput.equals("EndProgram"))
				{
					// Set both of the booleans to true.
					triggered = true;
					endProgram = true;
				}
			}
			
			// If the user types the command "InsertWord".
			if (userInput.equals("InsertWord"))
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
						System.out.println("Please enter a number..");
						System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
					}
					// If the user enters something better than the biggestSizePossible or 0 catch the exception and ask them to enter a valid number.
					catch (Exception ex)
					{
						System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
						System.out.println("Please enter a valid number..");
						System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
					}
					userInputScan.nextLine(); // Clear out the scanner.
				} while(userInputIndex <= 0 || userInputIndex > biggestSizePossible);
				
				// Ask the user for what word they want to put into the linkedList.
				System.out.print("Type your word to insert into " + userInputIndex + ": ");
				userInputInsert = userInputScan.nextLine(); // Get the nextLine as the userInputInsert.
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
			
			if (userInput.equals("UndoWord"))
			{
				// Set triggered to true since a command has occurred.
				triggered = true;
				
				if (undoTwiceCheck.equals("UndoWord"))
				{
					System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
					System.out.println("You cannot undo twice. Please enter a different command or a word..");
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
			
			if (userInput.equals("RedoWord"))
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
			
			if (userInput.equals("DeleteWord"))
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
						System.out.println("Please enter a number..");
						System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
					}
					// If the user enters something better than the biggestSizePossible or 0 catch the exception and ask them to enter a valid number.
					catch (Exception ex)
					{
						System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
						System.out.println("Please enter a valid number..");
						System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
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
			if (userInput.equals("EndProgram"))
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
		
		// Tell the user thank you and that the program is ending. (MAYBE ADD SAVE TO FILE HERE?)
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
		System.out.println("Thanks for using the writing program. Please come again!");
		
		userInputScan.close(); // Close the scanner.
	}
}
