package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import Games.Game;

public class ServerLogic
{
	private int portValue;
	static Game game = null;
	static ServerSocket serverSocket;
	
	public ServerLogic(int portValue) throws IOException
	{
		// TODO Auto-generated constructor stub
		this.portValue = portValue;
		serverSocket = new ServerSocket(portValue);
		System.out.println("Started server");
		//game logic
	}
	
	public void waitForPlayers() throws IOException
	{
		int clientNum = 1;
		
		while(true)
		{
			Socket client = serverSocket.accept();
			System.out.println("Client Arrived");
			System.out.println("Start thread for " + clientNum);
			ClientThread task = new ClientThread(client, game, clientNum);
			clientNum++;
			new Thread(task).start();
		}
	}
	
	public void kill()
	{
		try 
		{
			serverSocket.close();
		}
		catch (IOException e)
		{
			System.out.println("Cannot close Server socket");
		}
	}
}
