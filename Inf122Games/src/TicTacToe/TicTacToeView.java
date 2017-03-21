package TicTacToe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.*;

import Client.Client;
import Client.ClientGUI;
import Games.Game;

public class TicTacToeView extends Game implements ActionListener {
	static JButton buttons[] = new JButton[9];
	boolean gameOver = false;
	int moveCounter = 0;
	int moveX;
	int moveY;
	Client _client; // current client
	JFrame frame;
	private static String letter;
	private static boolean turn;
	private boolean quit = false;
	
	//JFrame frame = new JFrame("Tic Tac Toe");

	JPanel gamePanel = new JPanel();
	JPanel menuPanel = new JPanel();
	JPanel southPanel = new JPanel();
	JPanel topPanel = new JPanel();
	JPanel bottomPanel = new JPanel();

	JMenuBar menu = new JMenuBar();
	JMenuItem menuExit = new JMenuItem("Quit");

	public TicTacToeView(Client client) {

		_client = client;

		if (_client.isHost) {
			turn = true;
			letter = "X";
		} else {
			turn = false;
			letter = "O";
		}

		if (_client.isHost)
			frame = new JFrame("TTT - " + _client.username + "(Host)");
		else
			frame = new JFrame("TTT - " + _client.username);
		
		frame.setSize(600, 600);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		menuPanel.setBackground(new Color(70, 70, 70));
        southPanel.setBackground(new Color(80, 80, 80));
        
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        topPanel.setBackground(new Color(80, 80, 80));
        bottomPanel.setBackground(new Color(80, 80, 80));
        
		menu.add(menuExit);
		menuExit.setFont(new Font("Purisa", Font.BOLD, 18));
		
		
		menuExit.addActionListener(this);
		
		gamePanel.setLayout(new GridLayout(3, 3, 2, 2));
		gamePanel.setBorder(BorderFactory.createLineBorder(Color.darkGray, 3));
		for (int i = 0; i < 9; i++) {
			buttons[i] = new TicTacToeButton(this, i);
			gamePanel.add(buttons[i]);
		}
		
		menuPanel.add(menu);
		southPanel.setLayout(new BorderLayout());
		southPanel.add(gamePanel, BorderLayout.CENTER);

		frame.add(menuPanel, BorderLayout.NORTH);
		//frame.add(gamePanel, BorderLayout.CENTER);
		frame.add(southPanel, BorderLayout.CENTER);
		//frame.pack();
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent click){
		if(click.getSource() == menuExit)  
        {
            int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", 
            "Quit" ,JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
            {
            	this.sendQuit(true);
    			this.frame.dispose();	
                //System.exit(0);
            }
        }
		this.playerLeft();
	}
   

	public void printWinner() {
		// String winner = "";
		/*
		 * if (Driver.p1Wins) { winner = _client.username; } else if
		 * (Driver.p2Wins) { winner = "Player 2"; } else if (!Driver.hasWinner)
		 * { JOptionPane.showMessageDialog(null, "Game ended in a draw");
		 * System.exit(0); return; }
		 */
		if (Driver.buttonLetter.equalsIgnoreCase("x") && Driver.p1Wins) {
			if (_client.isHost)
				JOptionPane.showMessageDialog(frame, "Congrats! You have won the game!");
			else
				JOptionPane.showMessageDialog(frame, "Nice try! You lost the game.");
		} else {
			if (_client.isHost)
				JOptionPane.showMessageDialog(frame, "Nice try! You lost the game.");
			else
				JOptionPane.showMessageDialog(frame, "Congrats! You have won the game");
		}

		if (!playAgain()) {
			frame.dispose();
		} else {
			Driver.resetGame();
			this.clearBoard();
		}

		// JOptionPane.showMessageDialog(frame, winner + " has won the game!");
		// System.exit(0);
	}

	public void clearBoard() {
		for (int i = 0; i < 9; i++) {
			((TicTacToeButton) buttons[i]).setText(" ");
		}

	}

	public boolean playAgain() {
		int response = JOptionPane.showConfirmDialog(frame, "Want to play again?", "Tic Tac Toe",
				JOptionPane.YES_NO_OPTION);
		return response == JOptionPane.YES_OPTION;
	}

	@Override
	public void sendMove(int x, int y) {
		_client.sendMessage("MOVE_" + x + "_" + y);
	}

	@Override
	public void receiveMove(int x, int y) {
		System.out.println("received move" + x + " y: " + y);
		// TODO Auto-generated method stub
		int index = 0;

		switch (y) {
		case 0:
			if (x == 0) {
				index = 0;
			} else if (x == 1) {
				index = 1;
			} else if (x == 2) {
				index = 2;
			}
			break;
		case 1:
			if (x == 0) {
				index = 3;
			} else if (x == 1) {
				index = 4;
			} else if (x == 2) {
				index = 5;
			}
			break;

		case 2:
			if (x == 0) {
				index = 6;
			} else if (x == 1) {
				index = 7;
			} else if (x == 2) {
				index = 8;
			}
			break;

		}
		String oppLetter;
		if (letter.equalsIgnoreCase("x"))
			oppLetter = "O";
		else
			oppLetter = "X";
		ActionEvent actionevent = new ActionEvent(buttons[index], 0, null);
		if (buttons[index].getText() == " ") {
			((TicTacToeButton) buttons[index]).setText(oppLetter);
			Driver.buttonLetter = oppLetter;
			Driver.getInput(y, x);
			switchPlayer();
			Driver.checkForWin();
		}
		if (Driver.hasWinner || Driver.flagTotal == 9) { // If there is a
															// winner,
			// print the winner pop-up screen
			printWinner();
		}
		// for debugging
		System.out.println(Arrays.deepToString(Driver.board));
	};

	public void switchPlayer() {
		/*
		 * Driver.p1sTurn = !Driver.p1sTurn; Driver.p2sTurn = !Driver.p2sTurn;
		 */
		if (turn == true) {
			turn = false;
		} else {
			turn = true;
		}
	}

	public static void changeTurn(boolean tun) {
		turn = tun; /*
					 * Driver.p1sTurn = !Driver.p1sTurn; Driver.p2sTurn =
					 * !Driver.p2sTurn;
					 */
	}

	public static boolean get_turn() {
		return turn;
	}

	public static String get_piece() {
		return letter;
	}
	
	public void playerQuit(boolean out) {	this.quit = out; }
	
	public boolean get_hasQuit() {	return quit; }
	
	public void playerLeft()
	{
		if(this.get_hasQuit())
		{
			int input = JOptionPane.showOptionDialog(null, "Opponent has quit, You win!!", "Opponent Quit",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
			//JOptionPane.showMessageDialog(null, "Opponent has quit", "Opponent Quit", JOptionPane.INFORMATION_MESSAGE);
			if(input == JOptionPane.OK_OPTION || input == JOptionPane.CANCEL_OPTION)
			{
				setVisible(false);
				this.frame.dispose();	
			}
		}
	}

	@Override
	public void sendQuit(boolean out) {
		_client.sendMessage("QUITGAME_" + out);
		// TODO Auto-generated method stub
	}

	@Override
	public void receiveQuit(boolean out) {
		System.out.println("received move" );		
		System.out.println("hasQuitted " + out);
		playerQuit(out);
		// TODO Auto-generated method stub
	}
	
	
	
	/*public static void main(String[] args) throws IOException {
		TicTacToeView tttview = new TicTacToeView();
	}*/
}
