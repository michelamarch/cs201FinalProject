/**
 * DefaultBinaryTree class implements BinaryTree and holds a binary tree
 * @author Michela Marchini
 * @version 11/23/18
 */


public class DefaultBinaryTree<T> implements BinaryTree<T>{

	private BinaryTreeNode<T> root;

	/**
	 * Get the root node for this tree.
	 * 
	 * @return root or null if tree is empty.
	 */
	public BinaryTreeNode<T> getRoot(){
		if (root != null){
			return root;
		}
		return null;
	}

	/**
	 * Set the root node for this tree.
	 */
	public void setRoot(BinaryTreeNode<T> root){
		this.root = root;
	}

	/**
	 * Test if the tree is empty.
	 * 
	 * @return true if tree has no data.
	 */
	public boolean isEmpty(){
		return (root == null);
	}

	/**
	 * Get the data of this tree using inorder traversal.
	 * 
	 * @return inorder List.
	 */
	public LinkedList<T> getAllLeaves(){
		LinkedList<T> traversal = new LinkedList<T>();
		getLeaves(root, traversal);
		return traversal;
	}

	/**
	 * inorderTraversal recursively goes through the tree to add the elememts to a
	 * list inorder
	 * @param node you are on currently
	 * @param traversal current inorder LinkedList
	 */
	private void getLeaves(BinaryTreeNode<T> node, LinkedList<T> traversal){
		if (node == null){
			return;
		}

		getLeaves(node.getLeftChild(), traversal);
		if (node.isLeaf()){
			traversal.insertFirst(node.getData());
		}
		getLeaves(node.getRightChild(), traversal);
	}

	/**
	 * Get the data of this tree using inorder traversal.
	 * 
	 * @return inorder List.
	 */
	public LinkedList<T> inorderTraversal(){
		LinkedList<T> traversal = new LinkedList<T>();
		inorderTraversal(root, traversal);
		return traversal;
	}

	/**
	 * inorderTraversal recursively goes through the tree to add the elememts to a
	 * list inorder
	 * @param node you are on currently
	 * @param traversal current inorder LinkedList
	 */
	private void inorderTraversal(BinaryTreeNode<T> node, LinkedList<T> traversal){
		if (node == null){
			return;
		}

		inorderTraversal(node.getLeftChild(), traversal);
		traversal.insertFirst(node.getData());
		inorderTraversal(node.getRightChild(), traversal);
	}


	/**
	 * Get the data of this tree using preorder traversal.
	 * 
	 * @return preorder List.
	 */
	public LinkedList<T> preorderTraversal(){
		LinkedList<T> traversal = new LinkedList<T>();
		preorderTraversal(root, traversal);
		return traversal;
	}

	/**
	 * preorderTraversal recursively goes through the tree to add the elememts to a
	 * list preorder
	 * @param node you are on currently
	 * @param traversal current preorder LinkedList
	 */
	private void preorderTraversal(BinaryTreeNode<T> node, LinkedList<T> traversal){
		//because the node is first, once you visit a node, you add it
		traversal.insertLast(node.getData());
		//traverse to the leftmost of a given node
		if (node.getLeftChild() != null){
			preorderTraversal(node.getLeftChild(), traversal);
		}
		//if there is no left then you try to go down the right
		else if (node.getLeftChild() == null && node.getRightChild() != null){
			preorderTraversal(node.getRightChild(), traversal);
		}
		//recursively going back up the tree
		else{
			//if its a left child, go back up until there is a right branch to
			//traverse, that isn't the one you just went down
			if (node.equals(node.getParent().getLeftChild())){
				BinaryTreeNode<T> newNode = node;
				while (newNode.getRightChild() == null 
						|| newNode.equals(node.getParent().getParent())){
					newNode = newNode.getParent();
				}
				preorderTraversal(newNode.getRightChild(), traversal);
			}
			//if it is a right child here, it is the farthest right on the left side,
			//in which case, you go back to the root and do the right branch or the
			//complete farthest right, in which case you stop
			else{
				if (inLeftBranch(node)){
					preorderTraversal(root.getRightChild(), traversal);
				}
			}
		}
	}

	/**
	 * Get the data of this tree using postorder traversal.
	 * 
	 * @return postorder List.
	 */
	public LinkedList<T> postorderTraversal(){
		LinkedList<T> traversal = new LinkedList<T>();
		postorderTraversal(root, traversal);
		return traversal;
	}

	/**
	 * postorderTraversal recursively goes through the tree to add the elememts to a
	 * list postorder
	 * @param node you are on currently
	 * @param traversal current postorder LinkedList
	 */
	private void postorderTraversal(BinaryTreeNode<T> node, LinkedList<T> traversal){
		if (node.getLeftChild() == null){
			//add any node with no left child
			traversal.insertLast(node.getData());
			//if the node is a left child of a parent that has a right child, then
			//traverse the parent's right branch
			if (node.getParent().getRightChild() != null
					&& !node.equals(node.getParent().getRightChild())){
				postorderTraversal(node.getParent().getRightChild(), traversal);
			}
			//if the node has a right child, traverse it's right branch
			else if (node.getRightChild() != null){
				postorderTraversal(node.getRightChild(), traversal);
			}
			//if here, the left and the right sides have been traversed, so add the
			//parent
			else{
				traversal.insertLast(node.getParent().getData());
				//if the node is not the farthest right node, and its parent is not
				//the farthest right node, then recursively traverse back up to find
				//a node with a right branch, else you have traversed all the way
				//through, so add the root
				if (inLeftBranch(node) && inLeftBranch(node.getParent())){
					while (node.getParent() != null
							//&& node.getParent().getRightChild() != null
							){
						node = node.getParent();
					}
					postorderTraversal(node.getRightChild(), traversal);
				}
				else{
					traversal.insertLast(root.getData());
				}
			}
		}
		//traverse to the bottom of the left branch of a node
		else{
			postorderTraversal(node.getLeftChild(), traversal);
		}
	}

	/**
	 * inLeftBranch returns whether or not a node is ever in the left branch of
	 * another
	 * @param node to be checked
	 * @return true if the node is in a left branch, false otherwise
	 */
	private boolean inLeftBranch(BinaryTreeNode<T> node){
		//if a node is on the left side of the tree, it keeps going, else this is
		//the final node in the tree, so do nothing else
		boolean inLeftBranch = false;
		while(node.getParent() != null){
			//if the node is a left child
			if(node.equals(node.getParent().getLeftChild())){
				inLeftBranch = true;
			}
			node = node.getParent();
		}
		return inLeftBranch;
	}

}
