package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import Games.Game;
import Games.Player;

public class ServerLogic
{
	private int portValue;
	static Game game = null;
	static ServerSocket serverSocket;
	private String username;
	private Socket client;
	private	ArrayList<Player> playerList = new ArrayList<>();
	
	public ServerLogic(int portValue) throws IOException
	{
		// TODO Auto-generated constructor stub
		this.portValue = portValue;
		serverSocket = new ServerSocket(portValue);
		System.out.println("Started server");
		//game logic
	}
	
	public void waitForPlayers() throws Exception
	{
		int clientNum = 1;
		
		while(true)
		{
			client = serverSocket.accept();
			System.out.println("Client Arrived");
			System.out.println("Start thread for " + clientNum);
			ClientThread task = new ClientThread(client, game, clientNum, username);
			playerList.add(task.getPlayerProfile());
			ServerGUI.setUsername(task.getPlayerProfile().getUsername());
			clientNum++;
			new Thread(task).start();
		}
	}
	
	public ArrayList<Player> getListOfPlayers() { return playerList; } 
	
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
