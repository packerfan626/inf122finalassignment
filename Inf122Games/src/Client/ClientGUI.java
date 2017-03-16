package Client;

import java.awt.Color;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Games.Game;
import Server.Server;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientGUI extends JFrame
{
	private static int port = 9090;
	private JPanel contentPane = new JPanel();
	private JTextField tfUsername;
	private static String username;
	private static boolean created = false;
	private JTextField textField;
	private JButton bBattleship;
	private JTextPane textPane;
	private JTextField testTextBox;
	private JButton bTestButton;
	private String testString;
	
	public ClientGUI()
	{
		super("Client GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 242);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setVisible(true);
		contentPane.setLayout(null);
		
		
		JTextArea txtrServerOnline = new JTextArea();
		txtrServerOnline.setForeground(new Color(0, 0, 0));
		txtrServerOnline.setText("Enter Username");
		txtrServerOnline.setBounds(28, 23, 108, 22);
		contentPane.add(txtrServerOnline);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(177, 25, 86, 20);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = tfUsername.getText().toString();
				Client client = new Client("localhost", port, username);
				client.connect();
			}
		});
		btnNewButton.setBounds(311, 24, 89, 23);
		contentPane.add(btnNewButton);
			
		textField = new JTextField();
		textField.setBounds(176, 26, 130, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		btnNewButton.setBounds(329, 25, 89, 23);
		contentPane.add(btnNewButton);
		
		bBattleship = new JButton("Battleship");
		bBattleship.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ClientLogic.startBattleship();
			}
		});
		bBattleship.setBounds(312, 90, 89, 23);
		contentPane.add(bBattleship);
		
		textPane = new JTextPane();
		textPane.setBounds(23, 90, 232, 233);
		textPane.setEditable(false);
		contentPane.add(textPane);
		
		JTextArea txtrActiveGames = new JTextArea();
		txtrActiveGames.setText("Joinable games");
		txtrActiveGames.setBounds(71, 67, 130, 22);
		contentPane.add(txtrActiveGames);
		
		testTextBox = new JTextField();
		testTextBox.setBounds(276, 193, 131, 32);
		contentPane.add(testTextBox);
		testTextBox.setColumns(10);
		
		bTestButton = new JButton("ok");
		bTestButton.setBounds(277, 246, 124, 22);
		contentPane.add(bTestButton);
		
		bTestButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				testString = testTextBox.getText().toString();
			}
		} );
		
		repaint();		
	
		
		
		System.out.println("Enter Message: ");
		//send game selection
//				Game temp = new BattleShip();		
		
		Game gameSelection = new Game();
		client.sendMessage(gameSelection);
	}
	public static void main(String[] args) 
	{
		ClientGUI clientGUI = new ClientGUI();
	}
}
