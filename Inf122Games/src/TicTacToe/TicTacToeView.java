package TicTacToe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.*;

import Client.Client;
import Games.Game;

public class TicTacToeView extends Game{
	static JButton buttons[] = new JButton[9];
	boolean gameOver = false;
	int moveCounter = 0;
	int moveX;
	int moveY;
	Client _client;		//current client
	JFrame frame;
	private static String letter;
	private static boolean turn;
	
	public TicTacToeView(Client client) {
		
		_client = client;
		
		if(_client.isHost)
		{
			turn = true;
			letter = "X";
		}
		else
		{
			turn = false;
			letter = "O";
		}		
		
		if (_client.isHost)
			frame = new JFrame("TTT - " + _client.username + "(Host)");
		else
			frame = new JFrame("TTT - " + _client.username);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 3));
		panel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 3));

		for (int i = 0; i < 9; i++) {
			buttons[i] = new TicTacToeButton(this, i);
			panel.add(buttons[i]);
		}

		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(600, 600);
	}

	public void printWinner() {
		//String winner = "";
		/*if (Driver.p1Wins) {
			winner = _client.username;
		} else if (Driver.p2Wins) {
			winner = "Player 2";
		} else if (!Driver.hasWinner) {
			JOptionPane.showMessageDialog(null, "Game ended in a draw");
			System.exit(0);
			return;
		}*/
		if (Driver.buttonLetter.equalsIgnoreCase("x") && Driver.p1Wins){
			if(_client.isHost)
				JOptionPane.showMessageDialog(frame, "You have won the game--Congrats!");
			else
				JOptionPane.showMessageDialog(frame,  "You lost the game!");
		} else {
			if(_client.isHost)
				JOptionPane.showMessageDialog(frame, "You lost the game!");
			else
				JOptionPane.showMessageDialog(frame,  "You have won the game--Congrats!");
		}
		if (!playAgain()){
			System.exit(0);
		} else {
			Driver.resetGame();
			this.clearBoard();
		}
		
			//JOptionPane.showMessageDialog(frame, winner + " has won the game!");
		//System.exit(0);
	}
	
	public void clearBoard(){
		for (int i = 0; i < 9; i++) {
			((TicTacToeButton) buttons[i]).setText(" ");
		}
		
	}
	
	public boolean playAgain() {
        int response = JOptionPane.showConfirmDialog(frame,
            "Want to play again?", "Tic Tac Toe",
            JOptionPane.YES_NO_OPTION);
        return response == JOptionPane.YES_OPTION;
    }

	@Override
	public void sendMove(int x, int y) {
		_client.sendMessage("MOVE_" +x+ "_"+y );
	}

	@Override
	public void receiveMove(int x, int y) {
		System.out.println("received move" + x + " y: "+ y);
		// TODO Auto-generated method stub
		int index = 0;
		
		switch(y)
		{
		case 0: 
			if (x == 0)
			{
				index = 0;
			} 
			else if (x == 1)
			{
				index = 1;
			}
			else if (x == 2)
			{
				index = 2;
			}
		break;
		case 1:
			if (x == 0)
			{
				index = 3;
			} 
			else if (x == 1)
			{
				index = 4;
			}
			else if (x == 2)
			{
				index = 5;
			}
		break;
		
		case 2:
			if (x == 0)
			{
				index = 6;
			} 
			else if (x == 1)
			{
				index = 7;
			}
			else if (x == 2)
			{
				index = 8;
			}
		break;
			
		}
		String oppLetter;
		if(letter.equalsIgnoreCase("x"))
			oppLetter = "O";
		else
			oppLetter = "X";
		ActionEvent actionevent = new ActionEvent(buttons[index],0,null);
		if (buttons[index].getText() == " "){
			((TicTacToeButton) buttons[index]).setText(oppLetter);
			Driver.buttonLetter = oppLetter;
			Driver.getInput(y, x);
			switchPlayer();
			Driver.checkForWin();
		}
		if (Driver.hasWinner || Driver.flagTotal == 9) { //If there is a winner,
			 //print the winner pop-up screen
				printWinner(); 
		}
		//for debugging
		System.out.println(Arrays.deepToString(Driver.board));
	};
	
	public void switchPlayer(){
		/*Driver.p1sTurn = !Driver.p1sTurn;
		Driver.p2sTurn = !Driver.p2sTurn;*/
		if(turn == true){
			turn = false;
		}else{
			turn = true;
		}	
	}
	
	public static void changeTurn(boolean tun) { 
		turn = tun; /*Driver.p1sTurn = !Driver.p1sTurn;
		Driver.p2sTurn = !Driver.p2sTurn;*/
	}
	public static boolean get_turn() {	return turn; }
	public static String get_piece() {	return letter; }

	@Override
	public void sendQuit(boolean out)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveQuit(boolean out)
	{
		// TODO Auto-generated method stub
		
	}
}
