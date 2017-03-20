package Server;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.JButton;

public class ServerGUI extends JFrame
{
	private static Server server;
	private JPanel contentPane = new JPanel();
	private static JTextPane tpListOfUsers;
	private static Document doc;
	private static int port = 4444;
	private static JTextArea txtrServerOnline;
	private static JTextArea taPort;
	private static boolean created = false;
	
	
	private JScrollPane scrollPane;
	
	
	public ServerGUI()
	{
		super("Game Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 408);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setVisible(true);
		contentPane.setLayout(null);
		
		
		txtrServerOnline = new JTextArea();
		txtrServerOnline.setForeground(Color.RED);
		txtrServerOnline.setBounds(21, 31, 108, 22);
		txtrServerOnline.setText("Server offline");
		txtrServerOnline.setEditable(false);
		contentPane.add(txtrServerOnline);
		
		taPort = new JTextArea();
		taPort.setLineWrap(true);
		taPort.setEditable(false);
		taPort.setBounds(141, 31, 108, 22);
		contentPane.add(taPort);
			
		JButton bStartServer = new JButton("Start Server");
		bStartServer.setBounds(270, 22, 141, 35);
		contentPane.add(bStartServer);
		scrollPane = new JScrollPane();
        scrollPane.setBounds(21, 91, 390, 225);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(scrollPane);    
        
        tpListOfUsers = new JTextPane();
        scrollPane.setViewportView(tpListOfUsers);
        tpListOfUsers.setEditable(false);
        doc = tpListOfUsers.getStyledDocument();
		
		bStartServer.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource() == bStartServer)
				{
					// TODO Auto-generated method stub
					txtrServerOnline.setText("Server Online");
					txtrServerOnline.setForeground(new Color(0, 153, 0));
					taPort.setText("Port use: " + port);
					tpListOfUsers.setText("Server starting \n");
					repaint();		
//					server = new Server(port);
				}
				
			}
		});
		repaint();		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ServerGUI seb = new ServerGUI();
		while(!created)
		{
			try
			{
				ServerGUI run = new ServerGUI();
				server = new Server(port);
				server.startServer();		
			}
			catch (Exception IOE)
			{
				System.out.println("Server not created");
			}
			finally
			{
//				if(server != null)
//					server.kill();
			}
		}
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
}
