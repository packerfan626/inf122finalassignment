package TicTacToe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToeView{
	static JButton buttons[] = new JButton[9];
	boolean gameOver = false;
	int moveCounter = 0;
	// if this number is a even, then put a X. If it's odd,
	// then put an O

	public TicTacToeView() {
		JFrame frame = new JFrame ("Tic-Tac-Toe");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout (new GridLayout (3, 3));
		panel.setBorder (BorderFactory.createLineBorder (Color.gray, 3));
		panel.setBackground (Color.white);

		for(int i=0; i<9; i++){
			buttons[i] = new TicTacToeButton(this, i);
			panel.add(buttons[i]);			
		}

		frame.getContentPane().add (panel);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(500, 500);// set frame size
	}
	
	public void printWinner(){ // if the game ends let the user know who wins and give option to play again
		String winner = "";
		if (Driver.isP1Wins()){
			
			winner = "Player 1";
		} 
		else if(Driver.isP2Wins()){
			winner = "Player 2";
		}
		else if(!Driver.isThereAWinner()){
			JOptionPane.showMessageDialog(null, "Game ended in a draw");
			System.exit(0);
			return;
		}
		JOptionPane.showMessageDialog(null, winner + " has won the game!");
		System.exit(0);
};
		
	
	public int getMoveCounter(){
		return moveCounter;
	}
	
	public void incMoveCounter(){
		moveCounter++;
	}
}
