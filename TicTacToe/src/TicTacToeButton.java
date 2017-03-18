import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TicTacToeButton extends JButton implements ActionListener {
	TicTacToeView TTTboard;
	Driver game;
	String letter; // X or O
	int index; // location(0-8) of this button on the board
	// pass in the current TTT board and the index of the button being created.
	// each button is initialized to contain an empty string " "
	int x; //x-coordinate of button
	int y; //y-coordinate of button
	public TicTacToeButton(TicTacToeView board, int buttonIndex) {
		TTTboard = board;
		letter = " ";
		setFont(new Font("Arial", Font.BOLD, 150));
		setText(letter);
		addActionListener(this);
		index = buttonIndex;
		switch (index){
		case 0:
			x = y = 0;
			break;
		case 1:
			x = 1;
			y = 0;
			break;
		case 2:
			x = 2;
			y = 0;
			break;
		case 3:
			x = 0;
			y = 1;
			break;
		case 4:
			x = y = 1;
			break;
		case 5:
			x = 2;
			y = 1;
			break;
		case 6:
			x = 0;
			y = 2;
			break;
		case 7:
			x = 1;
			y = 2;
			break;
		case 8:
			x = y = 2;
			break;
		}
	}

	// Even Driver.flagtotal means it's player 1's turn.
	// Odd Driver.flagtotal means it's player 2's turn.
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean validClick = false;
		int y,x;
		System.out.println("Current cell: " + getText()); 
		if (Driver.flagTotal % 2 == 0 && getText().equals(" ") && Driver.flagTotal < 9) {
			Driver.p1sTurn = true;
			Driver.p2sTurn = false;
			letter = "X";
			y = ((TicTacToeButton) e.getSource()).getButtonRow();
			x = ((TicTacToeButton) e.getSource()).getButtonCol();
			Driver.getInput(y, x); //makes the move given the y(row) and x(col) coordinates.
			validClick = true;
		} else if (Driver.flagTotal % 2 != 0 && getText().equals(" ") && Driver.flagTotal < 9) {
			letter = "O";
			Driver.p1sTurn = false;
			Driver.p2sTurn = true;
			y = ((TicTacToeButton) e.getSource()).getButtonRow();
			x = ((TicTacToeButton) e.getSource()).getButtonCol();
			Driver.getInput(y, x); //makes the move given the y(row) and x(col) coordinates.
			validClick = true;
		}
		if (validClick){ 
			setText(letter); //Change the current button to reflect the X or O move
			Driver.checkForWin();
		}
		if (Driver.hasWinner || Driver.flagTotal == 9) { //If there is a winner,
														 //print the winner pop-up screen
			TTTboard.printWinner(); 
		}

	}
	
	public int getButtonRow(){
		return this.y;
	}
	
	public int getButtonCol(){
		return this.x;
	}
	
	// get index of current button
	public int getButtonIndex() {
		return this.index;
	}

	public String getLetter() {
		return this.letter;
	}

}
