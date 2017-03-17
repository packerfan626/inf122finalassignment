package Client;

import java.awt.Color;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import Games.Game;
import Server.Server;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientGUI extends JFrame implements ActionListener
{
	private static int port = 9090;
	private JPanel contentPane = new JPanel();
	private JTextField tfUsername;
	private static String username;
	private static boolean created = false;
	private JButton bBattleship;
	private JTextPane textPane;
	private JTextField testTextBox;
	private JButton bTestButton;
	private String testString;
	private static Client client;
	private static Document doc; 
	private JButton bTicTacToe;
	private JButton bOthello;
	private String gameSelection;
	private static Game game;
	
	public ClientGUI() 
	{
		super("Client GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 357);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setVisible(true);
		contentPane.setLayout(null);
		
		
		JTextArea txtrServerOnline = new JTextArea();
		txtrServerOnline.setForeground(new Color(0, 0, 0));
		txtrServerOnline.setText("Enter Username");
		txtrServerOnline.setBounds(28, 23, 108, 22);
		txtrServerOnline.setEditable(false);
		contentPane.add(txtrServerOnline);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(177, 25, 86, 20);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);
		
		JButton btnNewButton = new JButton("ok");		
		btnNewButton.setBounds(311, 24, 89, 23);
		contentPane.add(btnNewButton);
		btnNewButton.setBounds(329, 25, 89, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfUsername.setEditable(false);
				btnNewButton.setVisible(false);
				username = tfUsername.getText().toString();
				client = new Client("localhost", port, username);
				client.connect();
			}
		});
		
		bBattleship = new JButton("Battleship");
		bBattleship.addActionListener(this);
		bBattleship.setBounds(312, 90, 89, 23);
		contentPane.add(bBattleship);
		
		textPane = new JTextPane();
		textPane.setBounds(23, 90, 232, 233);
		textPane.setEditable(false);
		contentPane.add(textPane);
		doc = textPane.getStyledDocument();
		
		JTextArea txtrActiveGames = new JTextArea();
		txtrActiveGames.setText("Joinable games");
		txtrActiveGames.setBounds(71, 67, 130, 22);
		contentPane.add(txtrActiveGames);
		
		testTextBox = new JTextField();
		testTextBox.setBounds(276, 222, 131, 32);
		contentPane.add(testTextBox);
		testTextBox.setColumns(10);
		
		bTestButton = new JButton("ok");
		bTestButton.setBounds(276, 264, 124, 22);
		contentPane.add(bTestButton);
		
		bTicTacToe = new JButton("Tic-Tac-Toe");
		bTicTacToe.addActionListener(this);
		bTicTacToe.setBounds(276, 121, 141, 35);
		contentPane.add(bTicTacToe);
		
		bOthello = new JButton("Othello");
		bOthello.addActionListener(this);
		bOthello.setBounds(276, 166, 141, 35);
		contentPane.add(bOthello);
		
		bTestButton.addActionListener(this);
		
		repaint();				
		
//		System.out.println("Enter Message: ");
		//send game selection
//				Game temp = new BattleShip();		
		
		Game gameSelection = new Game();
//		client.sendMessage(gameSelection);
	
	}
	public static void main(String[] args) 
	{
		ClientGUI clientGUI = new ClientGUI();
	}
	
	public static void updateServer(String s)
	{
		try
		{
			doc.insertString(doc.getLength(), s + "\n", null);
		} catch (BadLocationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Game factory(String gameSelect)
	{
		if(gameSelect.equalsIgnoreCase("Battleship"))
			return game = new Game();	//battleship class
		else if(gameSelect.equalsIgnoreCase("TicTacToe"))
			return game = new Game();	//tictactoe class
		else if(gameSelect.equalsIgnoreCase("Othello"))
			return game = new Game();	//Othello
		else
			return null;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if(bBattleship == e.getSource())
			gameSelection = "battleship";
		if(bTicTacToe == e.getSource())
			gameSelection = "TicTacToe";
		if(bOthello == e.getSource())
			gameSelection = "Othello";
		if(gameSelection != null)
		{
			System.out.println("GAME SELECTED: " + gameSelection);
			game  = factory(gameSelection);
			gameSelection = null;
		}
	}
}
