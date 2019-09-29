/**
 *LinkedListNode defines a node of a linked list
 *@author Michela Marchini
 *@version 10/25/2018
 */

public class LinkedListNode<T>{

	private T data;
	private LinkedListNode<T> next;

	/**
	 * setData sets the data stored at this node.
	 * @param data the value to set to the data of the node
	 */
	public void setData( T data ){
		this.data = data;
	}

	/**
	 * getData gets the data stored at this node.
	 * @return data
	 */
	public T getData(){
		return data;
	}

	/**
	 * setNext sets the next pointer to passed node.
	 * @param node the node to set to next
	 */
	public void setNext( LinkedListNode<T> node ){
		next = node;
	}

	/**
	 * getNext gets (pointer to) next node.
	 * @return next
	 */
	public LinkedListNode<T> getNext(){
		return next;
	}

	/**
	 * Overrides object toString
	 * @return a String representation of this node
	 */
	public String toString(){
		if (data == null){
			return null;
		}
		return (String)data;
	}    

}
