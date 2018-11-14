# Data_Structures_Final
This is where I will be posting my progress as I work on my Data Structures final.
Currently we are at 20 hours and 10 minutes.

Here is my final project proposed sprints: 

Sprint One- I would first re-create a stack and linked list implementation that uses strings. 
I would have a main that would accept manual input. Making sure to include comments and java doc throughout. 
Along with writing the formalized proposal.

Sprint Two- I would then create the basic word program that would display a welcome message then give a prompt to type. 
As the user enters each word, the program would display above the current message type and accept any commands entered. 
Along with starting to write the user’s manual including screenshots.

Sprint Three- I would create my two methods for the word program called undo and redo. 
I would add user input validation to ensure no errors would occur. 
Along with finishing my formalized proposal and user’s manual if not already done.

Sprint Four - I would polish my program making sure I hadn't left out any features I had planned to and fix any errors that the program
still had. I would then write the lessons learned and conclusion/summary of my report.

Here is the rest of my proposal:

The real-world problem I would like to solve is a writing program. In the program (like Microsoft word), a user can type anything they want. At the end of the program the currently written strings would be written to a file. Anything they type will be added to a linked list as a string. After the user types they will be asked where to add it (what index number to add it too). They will be able to remove any element with the command "DeleteWord", which will prompt them for an index number to remove. They will also be able to add an element anywhere with the command "InsertWord", will prompt them for an index number to add too. Otherwise anything typed will be added to the end of the linkedList.  Also, every word they type is added to a stack called undo and a stack called redo. If they type the command "UndoWord", it will undo. If they do the command "RedoWord", it will redo. So, if I type the word "Ben" it will be put into the stack undo and redo. Both first values will read "Ben" if I hit the redo command line, it will type Ben again. If I hit the undo command line, it will remove Ben, while removing the previous ben from the redo and adding another. If I then hit redo, it would add Ben back to the linked list and back to the undo stack. This process happens each time the user enters a new input. The user can also delete elements from any place in the linkedlist which will then go to the undo, but not the redo stack. 

It would look like this:

-------------------------------------------------------
Welcome to the writing program! 
Keywords: InsertWord, UndoWord, RedoWord, DeleteWord.

Go ahead and type one string at a time:

-------------------------------------------------------
-User types- : Hello

Ben Is Is Testing
Hello
-User types- : World!

Hello World!
-User types- : UndoWord

Hello
-User types- : RedoWord


Hello World!
-User types- : DeleteWord
Enter an index number (Current index values: 1-2): -User types- : 1

World!
-User types- : InsertWord
Enter an index number (Current index values: 1-2): -User types- : 1 
Enter your word: -User types- Hello

Hello World!
-User types- : RedoWord

Hello Hello World!
-User types- : DeleteWord
Enter an index number (Current index values: 1-3): -User types- : 2

Hello World!
-User types- : Hi!

Hello World! Hi!
-User types- : EndProgram


Your document has successfully been written to file!
Exiting now...

-------------------------------------------------------



This real-world problem incorporates two data structures, linked list and stack.

My four sprints would be (throughout the whole project I would be logging the hours spent):
SEE ABOVE
