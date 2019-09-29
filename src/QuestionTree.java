/**
 * QuestionTree holds the tree created by the XMLReader
 * @author Michela Marchini
 * @version 12/4/18
 */

public class QuestionTree{

    private DefaultBinaryTree<Question> tree;

    /**
     * constructor for QuestionTree that sets the tree instance variable to the tree constructed in 
     * the XMLReader
     */
    public QuestionTree(){
        XMLReader reader = new XMLReader();
	tree = reader.parseXML();
  
    }

    /**
     * getTree returns the tree instance variable
     * @return tree
     */
    public BinaryTree<Question> getTree(){
	return tree;
    }

    /**
     * getArtists returns an array of all the leaves in the tree (all artists)
     * @return String[] of all artists in the tree
     */
    public String[] getArtists(){
	LinkedList<Question> inOrder = tree.getAllLeaves();
	String[] artists = new String[inOrder.size()];
        for (int i = 0; i < artists.length; i++){
	    artists[i] = inOrder.getFirst().getArtist();
	    inOrder.deleteFirst();
	}
	return artists;
    }
    

}
