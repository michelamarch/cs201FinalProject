/**
 * DefaultBinaryTreeNode class implements BinaryTreeNode and holds a node for a 
 * DefaultBinaryTree
 * @author Michela Marchini
 * @version 11/23/18
 */

public class DefaultBinaryTreeNode<T> implements BinaryTreeNode<T>{

	private T data;
	private BinaryTreeNode<T> leftChild;
	private BinaryTreeNode<T> rightChild;
	private BinaryTreeNode<T> parent;

	/**
	 * Get the data stored at this node.
	 * @return Object data.
	 */
	public T getData(){
		return data;
	}

	/**
	 * Set the data stored at this node.
	 */
	public void setData(T data){
		this.data = data;
	}

	/**
	 * Get the left child.
	 * @return BinaryTreeNode that is left child,
	 * or null if no child.
	 */
	public BinaryTreeNode<T> getLeftChild(){
		if (leftChild != null){
			return leftChild;
		}
		return null;
	}

	/**
	 * Get the right child.
	 * @return BinaryTreeNode that is right child,
	 * or null if no child.
	 */
	public BinaryTreeNode<T> getRightChild(){
		if (rightChild != null){
			return rightChild;
		}
		return null;
	}


	/**
	 * Get the parent node.
	 * @return BinaryTreeNode that is the parent,
	 * or null if no parent.
	 */
	public BinaryTreeNode<T> getParent(){
		if (parent != null){
			return parent;
		}
		return null;
	}


	/**
	 * Set the left child.
	 */
	public void setLeftChild( BinaryTreeNode<T> left ){
		this.leftChild = left;
	}

	/**
	 * Set the right child.
	 */
	public void setRightChild( BinaryTreeNode<T> right ){
		this.rightChild = right;
	}

	/**
	 * Set the parent.
	 */
	public void setParent( BinaryTreeNode<T> parent ){
		this.parent = parent;
	}

	/**
	 * Tests if this node is a leaf (has no children).
	 * @return true if leaf node.
	 */
	public boolean isLeaf(){
		return (leftChild == null && rightChild == null);
	}
}
    
