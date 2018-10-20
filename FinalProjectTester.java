/**
 * @author Benjamin Frederickson
 * This program is my final project for Data structures. It simulates a writing program with commands built in. It uses two data structures,
 * stack and linked list.
 */
import java.util.Scanner;

// CURRENT TIME: 4:01 (hours:minutes)

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
		int userInputIndex = 0, userDeleteIndex = 0;
		String userInput = "", undoWord = "", redoWord = "", userInputInsert = "", deleteValue = "";
		Boolean endProgram = false, triggered = false;
		Scanner userInputScan = new Scanner(System.in);
		
		LinkedList wordsTyped = new LinkedList();
		Stack undo = new Stack();
		Stack redo = new Stack();
		
		// Welcome the user to the program and prompt them to begin typing
		System.out.println("Welcome to the writing program!\nKeywords: InsertWord, UndoWord, RedoWord, DeleteWord, EndProgram.");
		System.out.println("Please begin typing at any time..");
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
		
		do 
		{
			// Reset the boolean that shows no commands have been entered.
			triggered = false;
			
			// After the first word typed display the words typed.
			if (wordsTyped.getSize() > 0)
			{
				System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
				System.out.println(wordsTyped.display());
				System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
			}
			userInput = userInputScan.nextLine();

			if (userInput.equals("InsertWord")) // TO DO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			{
				triggered = true;
				
				System.out.print("Enter an index number: ");
				userInputIndex = userInputScan.nextInt();
				System.out.print("Type your string to insert into " + userInputIndex + ": ");
				userInputScan.nextLine();
				userInputInsert = userInputScan.nextLine();
				wordsTyped.insertionAtIndex(userInputInsert, userInputIndex - 1);
				
				redo.push(userInputInsert);
				undo.push(userInputInsert);
			}
			
			if (userInput.equals("UndoWord")) // TO DO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			{
				triggered = true;
				
				undoWord = undo.pop();
				
				for (int i = 0; i < wordsTyped.getSize(); i++)
				{
					if (undoWord.equals(wordsTyped.search(i)))
					{
						wordsTyped.delete(i);
					}
				}
				redo.push(undoWord);
			}
			
			if (userInput.equals("RedoWord")) // TO DO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			{
				triggered = true;
				
				redoWord = redo.pop();
				wordsTyped.insertion(redoWord);
				
				redo.push(redoWord);
				undo.push(redoWord);
			}
			
			if (userInput.equals("DeleteWord")) // TO DO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			{
				triggered = true;
				
				System.out.print("Enter an index number: ");
				userDeleteIndex = userInputScan.nextInt();

				deleteValue = wordsTyped.search(userDeleteIndex);
				undo.push(deleteValue);
				
				wordsTyped.delete(userDeleteIndex);
			}
			
			// If the user's input is endProgram exit the do-while loop.
			if (userInput.equals("EndProgram"))
			{
				triggered = true;
				
				endProgram = true;
			}
			else if (triggered == false)
			{
				wordsTyped.insertion(userInput);
				undo.push(userInput);
				redo.push(userInput);
			}
		} while (endProgram == false);
		
		System.out.println(undo.printStack());
		System.out.println("Stack Size: " + undo.size());
		System.out.println("Returned: " + undo.pop());
		
		userInputScan.close();
	}
}
