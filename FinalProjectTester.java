/**
 * @author Benjamin Frederickson
 * This program is my final project for Data structures. It simulates a writing program with commands built in. It uses two data structures,
 * stack and linked list.
 */
import java.util.InputMismatchException;
import java.util.Scanner;

// CURRENT TIME: 10:13 (hours:minutes)

/*
Sprint One- I would first re-create a stack and linked list implementation that uses strings. 
I would have a main that would accept manual input. Making sure to include comments and java doc throughout. 
Along with writing the formalized proposal.

Sprint Two- I would then create the basic word program that would display a welcome message then give a prompt to type. 
As the user enters each word, the program would display above the current message type and accept any commands entered. 
Along with starting to write the user’s manual including screenshots.

Sprint Three- I would create my two methods for the word program called undo and redo. 
I would add user input validation to ensure no errors would occur. 
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
		System.out.println("Please begin typing at any time..");
		
		do // Do this.
		{
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
				
				// If the users input equals a command then 
				if (userInput.equals("InsertWord") || userInput.equals("UndoWord") || userInput.equals("RedoWord") || userInput.equals("DeleteWord"))
				{
					System.out.println("Please enter in a word before using a command.");
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
					undo.push(userInput);
					redo.push(userInput);
				}
			}
			triggered = false; // Reset the boolean that shows no commands have been entered.
			
			// If the size of the linkedList is greater than one, then print out what the user has typed.
			if (wordsTyped.getSize() > 0)
			{
				System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
				System.out.println(wordsTyped.display());
				System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
			}
			userInput = userInputScan.nextLine(); // Get the user's next command or word typed.
			
			// If the user types the command "InsertWord".
			if (userInput.equals("InsertWord")) // Need to get UNDO and REDO working with this command, otherwise works as intended. 
			{
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
						System.out.print("Enter an number: ");
						userInputIndex = userInputScan.nextInt();
					}
					// If the user enters something other than a number catch the exception and ask them to enter a number.
					catch (InputMismatchException ex)
					{
						System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
						System.out.println("Please enter a number..");
						System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
					}
					userInputScan.nextLine(); // Clear out the scanner.
				} while(userInputIndex <= 0);
				
				// Ask the user for what word they want to put into the linkedList.
				System.out.print("Type your string to insert into " + userInputIndex + ": ");
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
					System.out.println("You cannot Undo twice. Please enter a different command or a word..");
				}
				// If the lastCommand was InsertWord, delete the inserted word.
				else if (lastCommand.equals("InsertWord") || lastCommand.equals("RedoInsertWord"))
				{
					undoTwiceCheck = "UndoWord"; // Set the undoTwiceCheck to UndoWord.
					
					undoWord = undo.pop(); // Pop the top value off of undo.
					undoWordIndex = userInputIndex - 1; // Set the undoWordIndex to the one previously used.
					wordsTyped.delete(undoWordIndex);
				}
				// Else if the lastCommand was DeleteWord, insert the deleteWord back the same index.
				else if (lastCommand.equals("DeleteWord"))
				{
					undoTwiceCheck = "UndoWord"; // Set the undoTwiceCheck to UndoWord.
					
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
				redo.push(undoWord); // Push the undoWord onto the redo stack.
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
			
			if (userInput.equals("DeleteWord")) // Works correctly but need to fix UNDO.
			{
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
						System.out.print("Enter a number: ");
						userDeleteIndex = userInputScan.nextInt();
					}	
					// If the user enters something other than a number catch the exception and ask them to enter a number.
					catch (InputMismatchException ex)
					{
						System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
						System.out.println("Please enter a number..");
						System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
					}
					userInputScan.nextLine(); // Clear the scanner.
				} while(userDeleteIndex <= 0);
				// If undo is bigger than zero then pop the top value for the new one.
				if (undo.size() > 0)
				{
					undo.pop();
				}
				
				// Get the value to delete for the undo function.
				deleteValue = wordsTyped.search(userDeleteIndex - 1);
				undo.push(deleteValue);
				
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
