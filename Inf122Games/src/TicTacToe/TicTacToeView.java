package TicTacToe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	public TicTacToeView(Client client) {
		
		_client = client;
		frame = new JFrame("Tic-Tac-Toe - PLAYER"+_client.username);
		
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		String winner = "";
		if (Driver.p1Wins) {
			winner = "Player 1";
		} else if (Driver.p2Wins) {
			winner = "Player 2";
		} else if (!Driver.hasWinner) {
			JOptionPane.showMessageDialog(null, "Game ended in a draw");
			System.exit(0);
			return;
		}
		JOptionPane.showMessageDialog(frame, winner + " has won the game!");
		System.exit(0);
	}

	@Override
	public void sendMove(int x, int y) {
		_client.sendMessage("MOVE_" +x+ "_"+y );
	}

	@Override
	public void receiveMove(int x, int y) {
		System.out.println("received move" );
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
			
			
		ActionEvent actionevent = new ActionEvent(buttons[index],0,null);
		((TicTacToeButton) buttons[index]).actionPerformed(actionevent);
		

	};

}
