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
			}
		});
		btnNewButton.setBounds(311, 24, 89, 23);
		contentPane.add(btnNewButton);
			
		repaint();		
	
		Client client = new Client("localhost", port, username);
		client.connect();
		
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
