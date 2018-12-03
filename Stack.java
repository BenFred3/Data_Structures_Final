/**
 * @author Benjamin Frederickson
 * This program creates a stack using an array.
 */

// Exception handling if the stack is empty.
@SuppressWarnings("serial")
class stackEmptyException extends Exception 
{
	@Override
	public String getMessage()
	{
		return ("Stack is empty.");
	}
}

public class Stack
{
	// Initial values.
	private int top = -1;
	private String[] stackArray = null;
	private final int MAXSIZE = 3;
	
	// Default constructor.
	public Stack() 
	{
		stackArray = new String[MAXSIZE];
	}
	
	/**
	 * This function pushes a item on top of the stack.
	 * @param item.
	 */
	public void push(String item)
	{
		// Increase top then set the item to the array with top as the index.
		top = top + 1;
		stackArray[top] = item;
	}
	
	/**
	 * This function removes a item from the top of the stack.
	 * @return the item removed from the stack.
	 * @throws stackEmptyException.
	 */
	public String pop() throws stackEmptyException 
	{
		// If the stack is empty throw exception.
		if (stackArray.length <= 0)
		{
			throw new stackEmptyException();
		}
		// Else return the popped value.
		else 
		{
			String result = stackArray[top]; // Set the top value to result.
			stackArray[top] = null; // Set the top to null.
			top--; // Decrease top.
			
			return result; // Return the result.
		}
	}
	
	/**
	 * This function returns true or false depending on if the stack is empty or not.
	 * @return false or true.
	 */
	public boolean isEmpty() 
	{
		// If the top isn't equal to -1 then return false.
		if (top != -1)
		{
			return false;
		}
		// Else return true.
		else 
		{
			return true;
		}
	}
	
	/**
	 * This function returns true or false depending on if the stack is full or not.
	 * @return false or true.
	 */
	public boolean isFull() 
	{
		// If the top is equal to the MAXSIZE return true.
		if (top == MAXSIZE)
		{
			return true;
		}
		// Else return false.
		else
		{
			return false;
		}
	}
	
	/**
	 * This function returns the size of the stack.
	 * @return size of the stack.
	 */
	public int size() 
	{
		// Return the top plus one.
		return top + 1;
	}
}
