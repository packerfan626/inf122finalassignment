package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextPane;

public class ServerGUI extends JFrame
{
	private JPanel contentPane;
	private BufferedReader input;// = new BufferedReader(new InputStreamReader(System.in));
	private static int serverPort = 9090;
	private static boolean created = false;
	private static ServerLogic serverLogic;
	private JTextArea tvServerStatus;
	private JTextArea tvStatusPort;
	
	public ServerGUI() throws NumberFormatException, IOException
	{
		super("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setBounds(100, 100, 434, 373);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setVisible(true);
		contentPane.setLayout(null);
		
		tvServerStatus = new JTextArea();
		tvServerStatus.setForeground(new Color(0, 153, 0));
		tvServerStatus.setText("Server Status: Online");
		tvServerStatus.setBounds(114, 11, 190, 22);
		tvServerStatus.setEditable(false);
		contentPane.add(tvServerStatus);
		
		tvStatusPort = new JTextArea();
		tvStatusPort.setText("Port used: " + serverPort);
		tvStatusPort.setBounds(144, 57, 130, 22);
		contentPane.add(tvStatusPort);
		tvStatusPort.setEditable(false);
		
		JTextPane tpListOfUsers = new JTextPane();
		tpListOfUsers.setBounds(0, 96, 408, 206);
		contentPane.add(tpListOfUsers);
		
		repaint();
	}
	public static void main(String args[])
	{
		while(!created)
		{
			try
			{
				ServerGUI server = new ServerGUI();	
				serverLogic = new ServerLogic(serverPort);	
				serverLogic.waitForPlayers();
				
			}
			catch (IOException e)
			{
				System.out.println("Server not created - use different port - IO");
			}
			catch (NumberFormatException ne)
			{
				System.out.println("Enter incorrect server port");
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				if (serverLogic != null)
				{
					serverLogic.kill();
				}
			}
		}
	}
	public static void setUsername(String username)
	{
		System.out.println("SERVER LOGIC Username: " + username);
	}
	
}
