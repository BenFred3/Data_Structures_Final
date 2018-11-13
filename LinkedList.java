/**
 * @author Benjamin Frederickson
 * This program creates array-based linked list that takes strings.
 */

// Exception handling for when linkedList is empty or full.
@SuppressWarnings("serial")
class linkedListEmptyException extends Exception 
{
	@Override
	public String getMessage()
	{
		return ("The linked list is empty.");
	}
}

@SuppressWarnings("serial")
class linkedListFullException extends Exception
{
	@Override
	public String getMessage()
	{
		return ("The linked list is full.");
	}
}

public class LinkedList
{
	// Initial values.
	private static final int SIZE = 50000;
	private String linkedArray[];
	private int size;
	
	// Default constructor.
	public LinkedList() 
	{
		linkedArray = (String[]) new String[SIZE];
		size = 0;
	}
	
	// Function to get the size of the linkedList.
	public int getSize()
	{	
		return size;
	}
	
	/*
	 * This function takes a index value and prints it out.
	 * @param index.
	 * @return linkedArray found with the search value.
	 * @throws linkedListEmptyException.
	 */
	public String search (int index) throws linkedListEmptyException
	{
		 // If the size is equal to zero throw a exception.
		if (getSize() == 0)
		{
			throw new linkedListEmptyException();
		}
		// Else return the value.
		else 
		{
			return linkedArray[index];
		}
	}
	
	/**
	 * This function adds a value to the linked list and increases the size.
	 * @param word.
	 * @throws linkedListFullException.
	 */
	public void insertion(String word) throws linkedListFullException
	{
		// If the array is full print this message.
		if (getSize() == SIZE) 
		{
			throw new linkedListFullException();
		}
		// Else increase the size and set the value.
		else 
		{
			linkedArray[getSize()] = word;
			size++;
		}
	}
	
	/**
	 * This function adds a value to the linked list at the chosen indexValue and increases the size.
	 * @param word.
	 * @param indexValue.
	 */
	public void insertionAtIndex (String word, int indexValue) throws linkedListFullException
	{
		if (getSize() == SIZE)
		{
			throw new linkedListFullException();
		}
		
		// If the linkedArray's indexvalue isn't null then do this.
		if (linkedArray[indexValue] != null) 
		{
			// Set two values for the for loop. One of the initial word in the request indexValue and one for the word next to it.
			String movingValue = linkedArray[indexValue];
			String tempValue = linkedArray[indexValue + 1];
			
			// Set the indexValues neighbor to the next index value and continue doing so until the end of the linked list.
			for (int i = 1; i < size + 1; i++)
			{
				int nextIndex = i + 1; // Get one more than the current i value each loop.
				linkedArray[indexValue + i] = movingValue; // Set the moving value to the indexValue plus the current loop number.
				
				// If both of the values are null exit out of the for loop. There is nothing more to do.
				if (movingValue == null && tempValue == null)
				{
					break; // Break out of the for loop.
				}
				movingValue = tempValue; // Set the movingValue to the current tempValue.
				tempValue = linkedArray[indexValue + nextIndex]; // Get the next tempValue.
			}
			// Set the word at the index value given and increase the size.
			linkedArray[indexValue] = word;
			size++;
		}
		// Else the index value in the linked list is empty, so just insert the word into it and increase the size.
		else
		{
			linkedArray[indexValue] = word;
			size++;
		}
	}
	
	/**
	 * This function deletes the string from the index value.
	 * @param index
	 * @return linkedArray deleted value.
	 * @throws linkedListEmptyException.
	 */
	public void delete(int index) throws linkedListEmptyException
	{
		// If the size is equal to zero throw an exception.
		if (getSize() == 0) 
		{
			throw new linkedListEmptyException();
		}
		// Else delete the word from the index value.
		else 
		{	
			// Copies the numbers into the position.
			for (int i = index; i < size - 1; i++) 
			{
				linkedArray[i] = linkedArray[i + 1];
			}
			// Set the last element to null and decrease the size.
			linkedArray[getSize() - 1] = null;
			size--;
		}
	}

	/**
	 * This function displays the entire array.
	 * @return the linked list's data.
	 * @throws linkedListEmptyException
	 */
	public String display() throws linkedListEmptyException
	{
		// Strings for the display.
		String arrayData = "";
		String tempData = "";
		
		// If the array is empty print this message.
		if (getSize() == 0) 
		{ 
			throw new linkedListEmptyException();
		}
		// Else display the array.
		else 
		{
			// For loop to get all the data from the linkedArray.
			for (int i = 0; i < size; i++)
			{
				tempData = (linkedArray[i] + " ");
				arrayData = arrayData + tempData;
			}
		}
		// Return the data.
		return arrayData;
	}
}