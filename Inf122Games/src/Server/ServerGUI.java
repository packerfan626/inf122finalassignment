package Server;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;

public class ServerGUI extends JFrame
{
	private static int port = 4444;
	private static Server server;
	
	public ServerGUI() {
		super("Game Server");
		getContentPane().setLayout(null);
		
		JTextArea txtrServerOnline = new JTextArea();
		txtrServerOnline.setForeground(new Color(0, 153, 0));
		txtrServerOnline.setText("Server Online");
		txtrServerOnline.setBounds(164, 35, 108, 22);
		getContentPane().add(txtrServerOnline);
		
		JTextArea taPort = new JTextArea();
		taPort.setBounds(185, 79, 73, 22);
		getContentPane().add(taPort);
		taPort.setText("Port use: " + port);
	}	

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		server = new Server(port);
		server.startServer();
	}
}
