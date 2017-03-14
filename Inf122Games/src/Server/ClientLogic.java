package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;



public class ClientLogic implements Runnable
{
	private PrintWriter output;
	private BufferedReader input;	
	private ClientGUI clientGUI;
	private Thread thread;
	private Socket server;
	private ClientServerController CSC;
	
	public ClientLogic(String name, int port, ClientGUI clientGUI) throws IOException
	{
		// TODO Auto-generated constructor stub
		server = new Socket(name, port);
		output = new PrintWriter(server.getOutputStream(), false);
		input = new BufferedReader(new InputStreamReader(server.getInputStream()));
		CSC = new ClientServerController();
		this.clientGUI = clientGUI;
		
		//start thread
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		try
		{
			waitforserver();
		}
		catch (SocketException se)
		{
			CSC.ClientToClientMessage(clientGUI, "Connection lost to server");
		}
		catch (IOException e)
		{
			CSC.ClientToClientMessage(clientGUI, "Connection lost to server");
		}
		catch (RuntimeException re1)
		{
			CSC.ClientToClientMessage(clientGUI, "Connection lost to server");
		}
	}

	private void waitforserver() throws IOException, SocketException, RuntimeException
	{
		// TODO Auto-generated method stub
		while(true)
		{
			String serverReply = input.readLine();
			CSC.ClientToClientMessage(clientGUI, serverReply);
		}
	}
	
	
}
