/**
 * @author Benjamin Frederickson
 * This program creates array-based linked list that takes strings.
 */


@SuppressWarnings("serial")
class linkedListEmptyException extends Exception 
{
	@Override
	public String getMessage()
	{
		return ("List is empty");
	}
}

@SuppressWarnings("serial")
class linkedListFullException extends Exception
{
	@Override
	public String getMessage()
	{
		return ("List is full");
	}
}

public class LinkedList
{
	// Initial values.
	private static final int SIZE = 10;
	private String linkedArray[];
	private int size;
	
	public LinkedList() // Constructor to make the LinkedList.
	{
		linkedArray = (String[]) new String[SIZE];
		size = 0;
	}
	
	public int getSize()
	{
		return size;
	}
	
	/**
	 * This function takes a index value and prints it out.
	 * @param index.
	 * @return linkedArray founded with the search value.
	 * @throws linkedListEmptyException.
	 */
	public String search (int index) throws linkedListEmptyException
	{
		if (getSize() == 0) // If the size is equal to zero return null.
		{
			throw new linkedListEmptyException();
		}
		else // Returns the value.
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
		if (getSize() == SIZE) // If the array is full print this message.
		{
			throw new linkedListFullException();
		}
		else // Else increase the size and set the value.
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
	public void insertionAtIndex (String word, int indexValue)
	{
		// If the index value in the linked list is empty just insert the word into it.
		if (linkedArray[indexValue].equals(null))
		{
			linkedArray[indexValue] = word;
			size++;
		}
		else 
		{
			// TO DO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			String movingValue = linkedArray[indexValue];
			String tempValue = linkedArray[indexValue + 1];
			
			// Set the indexValues neighbor to the next index value and continue doing so until the end of the linked list.
			for (int i = indexValue; i < size; i++)
			{
				linkedArray[indexValue + i] = movingValue;
				movingValue = tempValue;
				tempValue = linkedArray[indexValue + i];
			}
			// Set the new value at the index value and increase the size.
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
		if (getSize() == 0) // If the size is equal to zero print this message.
		{
			throw new linkedListEmptyException();
		}
		else // Else delete the can from the index value.
		{	
			for (int i = index + 1; i < size - 1; i++) // Copies the numbers into the position.
			{
				// TO DO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			}
			// Set the last element to null and decrease the size.
			linkedArray[getSize() - 1] = null;
			size--;
		}
	}
	
	/**
	 * This function deletes the first value in the linked list.
	 * @return linkedArray deleted element.
	 * @throws linkedListEmptyException.
	 */
	public String deletion() throws linkedListEmptyException
	{
		if (getSize() == 0) // If the array is empty print this message.
		{
			throw new linkedListEmptyException();
		}
		else // Else decrease the size and return the element deleted.
		{
			for (int i = 0; i < size - 1; i++) // Copies the numbers into the position.
			{
				linkedArray[i] = linkedArray[i + 1];
			}
			// Set the last element to null and decrease the size.
			linkedArray[getSize() - 1] = null;
			size--;
			
			return linkedArray[size];
		}
	}

	/**
	 * This function displays the entire array.
	 * @return the linked list's data.
	 * @throws linkedListEmptyException
	 */
	public String display() throws linkedListEmptyException
	{
		String arrayData = "";
		String tempData = "";
		if (getSize() == 0) // If the array is empty print this message.
		{ 
			throw new linkedListEmptyException();
		}
		else // Else display the array.
		{
			for (int i = 0; i < size; i++)
			{
				tempData = (linkedArray[i] + " ");
				arrayData = arrayData + tempData;
			}
		}
		return arrayData;
	}
}
