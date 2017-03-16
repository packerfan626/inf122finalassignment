package Server;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Color;
import java.io.IOException;

public class ServerGUI extends JFrame
{
	private static int port = 9090;
	private static Server server;
	private JPanel contentPane = new JPanel();
	private static boolean created = false;
	
	public ServerGUI() {
		super("Game Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 242);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setVisible(true);
		contentPane.setLayout(null);
		
		
		JTextArea txtrServerOnline = new JTextArea();
		txtrServerOnline.setForeground(new Color(0, 153, 0));
		txtrServerOnline.setText("Server Online");
		txtrServerOnline.setBounds(164, 35, 108, 22);
		contentPane.add(txtrServerOnline);
		
		JTextArea taPort = new JTextArea();
		taPort.setLineWrap(true);
		taPort.setBounds(164, 79, 108, 22);
		contentPane.add(taPort);
		taPort.setText("Port use: " + port);	
		repaint();		
	}	

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub		
		while(!created)
		{
			try
			{
				ServerGUI run = new ServerGUI();
				server = new Server(port);
				server.waitForPlayer();		
			}
			catch (IOException IOE)
			{
				System.out.println("Server not created");
			}
			finally
			{
				if(server != null)
					server.kill();
			}
		}
	}
}
