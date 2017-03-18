package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Games.Game;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
//import TicTacToe.TicTacToe;
//import TicTacToe.TicTacToeView;

public class ClientGUI extends JFrame implements ActionListener
{
	static Client client;
	static String username;
	static ArrayList<String> availGames = new ArrayList<>();;
	private static int port = 4444;
	private JPanel contentPane = new JPanel();
	private JTextField tfUsername;
	private static JButton bBattleship, bTicTacToe, bOthello, btnTESTjoingame;
	public ClientGUI()
	{
		super("Game Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 361);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setVisible(true);
		contentPane.setLayout(null);
		
		JTextArea txtrEnterUsername = new JTextArea();
		txtrEnterUsername.setText("Enter username:");
		txtrEnterUsername.setBounds(21, 21, 129, 22);
		contentPane.add(txtrEnterUsername);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(173, 21, 117, 22);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);
		
		JButton bUsernameOK = new JButton("Ok");
		bUsernameOK.setBounds(314, 18, 97, 28);
		contentPane.add(bUsernameOK);
		
		btnTESTjoingame = new JButton("");
		btnTESTjoingame.setBounds(173, 85, 141, 35);
		btnTESTjoingame.addActionListener(this);
		contentPane.add(btnTESTjoingame);
		
		JButton bHostGame = new JButton("Host Game");
		bHostGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bHostGame.setBounds(0, 255, 141, 35);
		contentPane.add(bHostGame);
		
		bBattleship = new JButton("Battleship");
		bBattleship.setBounds(291, 184, 141, 35);
		bBattleship.addActionListener(this);
		contentPane.add(bBattleship);
		
		bTicTacToe = new JButton("Tic-Tac-Toe");
		bTicTacToe.setBounds(291, 220, 141, 35);
		bTicTacToe.addActionListener(this);
		contentPane.add(bTicTacToe);
		
		bOthello = new JButton("Othello");
		bOthello.setBounds(291, 255, 141, 35);
		bOthello.addActionListener(this);
		contentPane.add(bOthello);
		
		bUsernameOK.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				username = tfUsername.getText().toString();
				System.out.println(username);
				client = new Client("localhost", availGames, port, username);
				client.connect();
				String message = "REG_" + username;
				System.out.println(message);
				client.sendMessage(message);
				
			}
		});
		repaint();
	}
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		ClientGUI clientGUI = new ClientGUI();
		
//		Scanner scanner = new Scanner(System.in);
//		availGames = new ArrayList<>();
//		System.out.print("Enter username: ");
//		username = scanner.nextLine();
//		
//		
//		client.sendMessage("REG_" + username);
		
//		System.out.println("Select which game you would like to play: "
//				+ "\n1. Tic Tac Toe"
//				+ "\n2. Chess"
//				+ "\n3. Othello"
//				+ "\nSelection:");
		
//		int selection = scanner.nextInt();
		
//		switch(selection){
//			case 1: 
//				//Sets client as host for starting TicTacToe
//				startGame("TicTacToe");
//				break;
//			case 2: 
//				//Sets client as host for Battleship
//				startGame("Battleship");
//				break;
//			case 3:
//				startGame("Othello");
//				break;
//			default:
//				System.out.println("Invalid Selection");
//				break;
//		}
	}
	
	public static void startGame(String game){
		//Sets client as host for selected game
		client.isHost = true;
		
		//Adds game to the list of games
		String str = game + "_" + username;
		availGames.add(str);
		
		client.sendMessage("NEWGAME_" + str);
	}

	public static void updateGames()
	{
		
//		String [] gameInfo = s.split("_");
		btnTESTjoingame.setText(availGames.get(availGames.size()-1));
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		// TODO Auto-generated method stub
		if(e.getSource() == bBattleship)
		{
			startGame("Battleship");
		}
		if(e.getSource() == bTicTacToe)
		{
			startGame("TicTacToe");
		}
		if(e.getSource() == bOthello)
		{
			startGame("Othello");
		}
		if(e.getSource() == btnTESTjoingame)
		{
			//Display the list of games in the list
			String text = btnTESTjoingame.getText();
			String[] messages = text.split("");
			client.sendMessage("JOINGAME_" + text);
		}
	}		
}
