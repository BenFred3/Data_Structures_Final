/**
 * @author Benjamin Frederickson
 * This program creates a stack using an array.
 */
@SuppressWarnings("serial")
class stackEmptyException extends Exception 
{
	@Override
	public String getMessage()
	{
		return ("Stack is empty");
	}
}

public class Stack
{
	private int top = -1;
	private String[] stackArray = null;
	private final int MAXSIZE = 2000000;
	
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
		if (top == MAXSIZE - 1)
		{
			
		}
		else 
		{
			top = top + 1;
			stackArray[top] = item;
		}
	}
	
	/**
	 * This function removes a item from the top of the stack.
	 * @return the item removed from the stack.
	 * @throws stackEmptyException.
	 */
	public String pop() throws stackEmptyException 
	{
		if (stackArray.length <= 0)
		{
			throw new stackEmptyException();
		}
		else 
		{
			String result = stackArray[top];
			stackArray[top] = null;
			top--;
			return result;
		}
	}
	
	/**
	 * This function returns true or false depending on if the stack is empty or not.
	 * @return false or true.
	 */
	public boolean isEmpty() 
	{
		if (top != -1)
		{
			return false;
		}
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
		if (top == MAXSIZE)
		{
			return true;
		}
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
		return top + 1;
	}
	
	/**
	 * This function prints the entire stack.
	 * @throws stackEmptyException.
	 */
	public String printStack() throws stackEmptyException 
	{
		String printOne, printTwo = "", printThree, currentStack;
		if (top != -1)
		{
			printOne = "Stack: ";
			for (int i = 0; i < (top + 1); i++)
			{
				currentStack = (stackArray[i] + " ");
				printTwo = printTwo + currentStack;
			}
			printThree = "\n";
			
			return printOne + printThree + printTwo + printThree;
		}
		else 
		{
			throw new stackEmptyException();
		}
	}
	
	/**
	 * This function prints the stack's top's data.
	 * @throws stackEmptyException.
	 */
	public String top() throws stackEmptyException 
	{
		if (top < 0)
		{
			throw new stackEmptyException();
		}
		else 
		{
			return ("Front element: " + stackArray[top]);
		}
	}
}
