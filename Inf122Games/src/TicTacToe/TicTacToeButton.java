package TicTacToe;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TicTacToeButton extends JButton implements ActionListener{
	TicTacToeView TTTboard;
	Driver game;
	String letter; // x or o
	int index;
	public TicTacToeButton(TicTacToeView board, int buttonIndex) {	// creating blank board
		TTTboard = board;
		index = buttonIndex;
		letter=" ";
		setFont (new Font ("Arial", Font.BOLD, 150));
		setText(letter);
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//player1's turn and theres no X or O there yet, and the turns haven't exceeded 9.
		if(Driver.getFlagTotal()%2 == 0 && getText().equals(" ") && Driver.getFlagTotal() < 9){
			letter="X";
			Driver.askForInput(((TicTacToeButton)e.getSource()).getButtonIndex(), 1);
			TTTboard.incMoveCounter();
		} 
		//player2's turn and theres no X or O there yet, and the turns haven't exceeded 9.

		else if(Driver.getFlagTotal()%2 != 0 && getText().equals(" ") && Driver.getFlagTotal() < 9){
			letter="O";
			Driver.askForInput(((TicTacToeButton)e.getSource()).getButtonIndex(), 2);
			TTTboard.incMoveCounter();
		}
		setText(letter);
		Driver.updateBoard();
		Driver.checkForWin();
		if (Driver.hasWinner || Driver.getFlagTotal() == 9){
			System.out.println("Game over");
			TTTboard.printWinner();
		}
	}
	
	public int getButtonIndex(){
		//need to change this to x and y coordinate to send across the server.
		return this.index;
	}

}
