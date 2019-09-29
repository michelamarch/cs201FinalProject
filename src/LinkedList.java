/**
 *LinkedList holds the methods to create and interact with a linked list
 *@author Michela Marchini
 *@version 11/4/18
 */

public class LinkedList<T>{

	private LinkedListNode<T> head;

	/**
	 * Constructor creates an empty linked list.
	 */
	public LinkedList(){
	}

	/**
	 * getData gets data stored in head node of list.
	 * @return head's data
	 */
	public T getFirst(){
		if (head == null){
			return null;
		}
		else{
			return head.getData();
		}
	}

	/**
	 * getFirstNode gets the head node of the list.
	 * @return head
	 */
	public LinkedListNode<T> getFirstNode(){
		return head;
	}

	/**
	 * getLastNode gets the last node of the list.
	 * @return the last node
	 */
	public LinkedListNode<T> getLastNode(){
		if (head == null){
			return null;
		}
		LinkedListNode<T> currentNode = head;
		while (currentNode.getNext() != null){
			currentNode = currentNode.getNext();
		}
		return currentNode;
	}

	/**
	 * getLast gets data stored in the last node of list.
	 * @return the last node's data
	 */
	public T getLast(){
		if (getLastNode() == null){
			return null;
		}
		return getLastNode().getData();
	}


	/**
	 * insertFirst creates a new node with data and inserts it at the head of 
	 * the list
	 * @param data data to be put in new node
	 */
	public void insertFirst( T data ){
		LinkedListNode<T> toInsert = new LinkedListNode<T>();
		toInsert.setData(data);
		insertFirstNode(toInsert);
	}

	/**
	 * insertFirstNode inserts the node at the head of the list
	 * @param node to be inserted
	 */
	public void insertFirstNode( LinkedListNode<T> node ){
		node.setNext(head);
		head = node;
	}

	/**
	 * insertAfter creates a new node with data and inserts it after currentNode
	 * @param currentNode node already in LinkedList
	 * @param data to create new node
	 */
	public void insertAfter( LinkedListNode<T> currentNode, T data ){
		LinkedListNode<T> toInsert = new LinkedListNode<T>();
		toInsert.setData(data);
		insertNodeAfter(currentNode, toInsert);
	}

	/**
	 * insertNodeAfter inserts the node after currentNode
	 * @param currentNode node already in the LinkedList
	 * @param node to be inserted
	 */
	public void insertNodeAfter( LinkedListNode<T> currentNode,
			LinkedListNode<T> node ){
		node.setNext(currentNode.getNext());
		currentNode.setNext(node);
	}

	/**
	 * insertLast creates a new node with data and intserts it at the tail of 
	 * the list
	 * @param data to create the new node
	 */
	public void insertLast( T data ){
		LinkedListNode<T> node = new LinkedListNode<T>();
		node.setData(data);
		insertLastNode(node);
	}

	/**
	 * insertLastNode inserts the node at the tail of the list
	 * @param node to be inserted
	 */
	public void insertLastNode( LinkedListNode<T> node ){
		if (getLastNode() == null){
			insertFirstNode(node);
		}
		else{
			getLastNode().setNext(node);
		}
	}


	/**
	 * deleteFirst removes head node
	 */
	public void deleteFirst(){
		if (size() == 1){
			head = null;
		}
		else if (size() > 1){
			head = head.getNext();
		}
	}

	/**
	 * deleteLast removes tail node
	 */
	public void deleteLast(){
		if (size() == 1){
			head = null;
		}
		if (size() > 1){ //list is not empty
			LinkedListNode<T> currentNode = new LinkedListNode<T>();
			currentNode = head;
			//find second to last node
			while(currentNode.getNext() != getLastNode()){
				currentNode = currentNode.getNext();
			}
			currentNode.setNext(null);
		}
	}

	/**
	 * deleteNext removes node following currentNode
	 * If no node exists (i.e., currentNode is the tail), do nothing
	 * @param currentNode to delete the next of
	 */
	public void deleteNext( LinkedListNode<T> currentNode ){
		if (currentNode != null && currentNode.getNext() != null){
			currentNode.setNext(currentNode.getNext().getNext());
		}
	}

	/**
	 * Compute the size of this list.
	 */
	public int size(){
		if (head == null){
			return 0;
		}
		if (head == getLastNode()){
			return 1;
		}
		int count = 1; //last node is not in loop and there >= 2 elements
		LinkedListNode<T> currentNode = new LinkedListNode<T>();
		currentNode = head;
		while (currentNode != getLastNode()){
			count++;
			currentNode = currentNode.getNext();
		}
		return count;
	}

	/**
	 * Check if list is empty.
	 * @return true if list contains no items.
	 */
	public boolean isEmpty(){
		return (size() == 0);
	}

	/**
	 * Return a String representation of the list.
	 */
	public String toString(){
		String toRet = "";
		LinkedListNode<T> currentNode = head;
		while (currentNode != getLastNode()){
			toRet += currentNode + " --> ";
			currentNode = currentNode.getNext();
		}
		if (!isEmpty()){
			toRet += getLastNode();
		}
		return toRet;
	}


}
