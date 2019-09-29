/**
 * @author Michela Marchini
 * @version 12/9/18
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Dimension;

/**
 * An UnrestrictedGameMaker lets the user play the 20 questions guessing game 
 * and answer the yes or no questions presented, if the game cannot guess the 
 * artist they are thinking of, they can add it
 */
public class UnrestrictedGameMaker extends GameMaker implements ActionListener
{

	/**
	 * UnrestrictedGameMaker calls the constructor for GameMaker()
	 */
	public UnrestrictedGameMaker(){
		//super constructor will call createPopup from the GameMaker class and then the makeInstructions
		//method from unrestricted
		super();
	}

	/**
	 * makeInstructions ovewrites the GameMaker makeInstructions and returns
	 * the instructions for the unrestricted 20 questions game
	 * @return String of instructions
	 */
	@Override
	protected String makeInstructions(){
		return "Welcome to Musical 20 Questions (unrestricted version!). " +
				"Think of your favorite artist and click ok to continue.";
	}

	/**
	 * createEndPopupLost makes a JOptionPane with to signal that the computer
	 * did not correctly guess the artist and allows the user to input the 
	 * proper artist and question to be entered into the tree
	 */
	@Override
	protected void createEndPopupLost(){

		// make popup color mint green
		UIManager UI=new UIManager();
		UI.put("OptionPane.background", background);
		UI.put("Panel.background", background);

		Image image;
		Image scaledImage;
		ImageIcon icon;

		String toDisplay = "You stumped me! Tell me who you were thinking " +
				"about so I can guess next time.";

		// try to make the icon then instantiate the popup
		try{
			image = ImageIO.read(getClass().getResource("/img/questions.jpeg"));
			scaledImage = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			icon = new ImageIcon(scaledImage);
			// activate popup
			JOptionPane dialog = new JOptionPane(toDisplay);
			String artist = dialog.showInputDialog("What artist were you thinking about?");
			String question = dialog.showInputDialog("What question would have led you to this artist?");
			String[] ansOptions = {"yes", "no"};
			Object ans = dialog.showInputDialog(null, "What is the answer to" +
					"that question?", "Input",
					JOptionPane.INFORMATION_MESSAGE,
					null, ansOptions,
					ansOptions[0]);
			Question toInsertQuestion = new Question(question);
			Question artistQuestion = new Question("Was your artist " + artist +
					"?", artist);
			BinaryTreeNode<Question> questionNode =
					new DefaultBinaryTreeNode<Question>();
			BinaryTreeNode<Question> artistNode =
					new DefaultBinaryTreeNode<Question>();
			questionNode.setData(toInsertQuestion);
			artistNode.setData(artistQuestion);
			BinaryTreeNode<Question> parentNode = currentNode.getParent();
			if(currentNode.equals(parentNode.getLeftChild())){
				parentNode.setLeftChild(questionNode);
				questionNode.setParent(parentNode);
			}
			else{
				parentNode.setRightChild(questionNode);
				questionNode.setParent(parentNode);
			}
			if(ans.equals("yes")){
				questionNode.setLeftChild(artistNode);
				artistNode.setParent(questionNode);
				questionNode.setRightChild(currentNode);
				currentNode.setParent(questionNode);
			}
			else{
				questionNode.setRightChild(artistNode);
				artistNode.setParent(questionNode);
				questionNode.setLeftChild(currentNode);
				currentNode.setParent(questionNode);
			}


		}
		catch (Exception e){
			System.out.println("exception: " + e.getMessage());
		}
	}    

}
