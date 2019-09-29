//import the following libraries
import javax.xml.parsers.*;
import org.xml.sax.SAXException;  
import org.w3c.dom.*;
import java.io.*;

/**
 * XMLReader contains methods to read in and process the artists.xml file
 * @author Michela
 * @verson 12/4/18
 *
 */

public class XMLReader {

	private BinaryTreeNode<Question> currentNode = new DefaultBinaryTreeNode<Question>();

	/**
	 * This method creates new DefaultBinaryTree<Question> and calls the
	 * parseNode method on it and the root of the XML file. 
	 * @param document
	 * @return a BinaryTree<Question> of all the contents of the XML file
	 */
	private DefaultBinaryTree<Question> parseFile( Document document )
	{
		DefaultBinaryTree<Question> tree = new DefaultBinaryTree<Question>();
		Node docRoot =  document.getDocumentElement();
		parseNode(docRoot, tree);
		return tree;

	}

	/**
	 * This method recursively parses each question tag. 
	 * @param n-- which is a starting node
	 * @param tree-- the BinaryTree so far
	 */
	private void parseNode(Node n, DefaultBinaryTree<Question> tree){
		//only look at element nodes
		if( n.getNodeType() == Node.ELEMENT_NODE){ 
			Element currentElt = (Element)n;
			NodeList myNode = currentElt.getChildNodes();
			for(int i=0; i < myNode.getLength(); i++){
				//if it is a question node add it to the tree
				if( myNode.item(i).getNodeName().equals("question") ){
					if(myNode.item(i).getNodeType() == Node.ELEMENT_NODE){
						Element elt = (Element)myNode.item(i);
						Question question = new Question(elt.getAttribute("txt"));
						BinaryTreeNode<Question> questionNode= new DefaultBinaryTreeNode<Question>();
						questionNode.setData(question);
						//if the tree is empty, make this question the root
						if(tree.getRoot() == null){
							tree.setRoot(questionNode);
							currentNode = questionNode;
						}
						//if it is aquired by saying yes to the previous question, put it on the left
						else if(elt.getAttribute("ans").equals("yes")){
							currentNode.setLeftChild(questionNode);
							questionNode.setParent(currentNode);
							currentNode = questionNode;
						}
						//if it is aquired by saying no to the previous question, put it on the right
						else{
							currentNode.setRightChild(questionNode);
							questionNode.setParent(currentNode);
							currentNode = questionNode;
						}

					}
				}
				//if it is an artist, add it to the tree
				if( myNode.item(i).getNodeName().equals("artist") ){
					if(myNode.item(i).getNodeType() == Node.ELEMENT_NODE){
						Element elt = (Element)myNode.item(i);
						String artist = elt.getTextContent();
						String question = "Was your artist" + artist + "?";
						Question artistQuestion = new Question(question, artist);
						BinaryTreeNode<Question> questionNode= new DefaultBinaryTreeNode<Question>();
						questionNode.setData(artistQuestion);
						//if it is aquired by saying yes to the previous question, put it on the left
						if(elt.getAttribute("ans").equals("yes")){
							currentNode.setLeftChild(questionNode);
							questionNode.setParent(currentNode);
							//loop back to the node the next line of xml will be the child of
							while(currentNode.getRightChild() != null){
								currentNode = currentNode.getParent();
							}
						}
						//if it is aquired by saying no to the previous question, put it on the right
						else{
							currentNode.setRightChild(questionNode);
							questionNode.setParent(currentNode);
							//loop back to the node the next line of xml will be the child of
							while(currentNode.getRightChild() != null && currentNode != tree.getRoot()){
								currentNode = currentNode.getParent();
							}
						}

					}
				}
				parseNode(myNode.item(i), tree);
			}
		}
	}

	/**
	 * This method takes an XML file, creates a document and calls parsing methods. 
	 * @return the binary tree of questions generated from parseFile
	 */
	public DefaultBinaryTree<Question> parseXML()
	{
		//Setup XML Document
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			File xmlFile = new File( "artists.xml" );
			Document document = builder.parse( xmlFile );

			return parseFile(document);
		} catch (ParserConfigurationException pce) {
			//what to do if this exception happens
			System.out.println("ParserConfigurationException found");
			return new DefaultBinaryTree<Question>();
		} catch (SAXException saxe) {
			//what to do if this exception happens
			System.out.println("SAXException found");
			return new DefaultBinaryTree<Question>();
		} catch (IOException ioe) {
			//what to do if this exception happens
			System.out.println("IOException found");
			return new DefaultBinaryTree<Question>();
		}

	}
}

