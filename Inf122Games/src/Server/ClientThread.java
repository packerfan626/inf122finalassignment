package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import Games.Battleship;
import Games.Game;
import Games.Player;

//create multiple clients to the servers
public class ClientThread extends PlayerGuide implements Runnable
{
	private BufferedReader input;
	private PrintWriter output;
	private int clientNum;
	private Socket socket;
	private Game game;
	private ClientServerController CSC;
	private String username;
	private ClientGUI clientGUI;
	private Player player;
	public ClientThread(Socket client, Game game, int clientNum, String username) throws Exception
	{
		// TODO Auto-generated constructor stub
		this.game = game;
		this.clientNum = clientNum;
		this.socket = client;		
		this.username = username;
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    String username1 = input.readLine();
		this.player = new Player(username1, client, clientNum);
		CSC = new ClientServerController();
		//ClientThread
		System.out.print("username : " + username);
		System.out.print("clientNum : " + clientNum);
		System.out.print("client : " + client);
		
		super.setCSC(CSC);
		super.setGame(game);
		//see unique clients
		System.out.println(client.toString());
		
		try 
		{
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), false);
			logInfo();
			//THIS IS WHERE YOU ADD PLAYER TO THE GAME
		}
		catch (IOException e)
		{
			System.err.println("not set up for client");
		}
	}
	
	public Player getPlayerProfile() { return player; }
	
	public void logInfo() throws Exception{
		//open buffered reader for reading data from client
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String username = input.readLine();
//        Socket clientWord = input.readLine();
        int port = input.read();
        
        System.out.println("SERVER SIDE: " + username);       
      
        //open printwriter for writing data to client
        output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        output.println("Welcome, " + username);
        output.flush();

	}
	
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
//		try
//		{
////			for(int i = 1; i <= 10; ++i)
////			{
////				outputMessage("Test to client : " + Integer.toString(clientNum));
////				System.out.println("Test to Client : " + Integer.toString(clientNum));
////				Thread.sleep(5000);
////			}
//			
//		}
//		catch (InterruptedException e)
//		{
//			e.printStackTrace();
//		}
	}
	
	//sends a message to client
	protected void outputMessage(String message)
	{
		CSC.ServerToClientMessage(output, message);
	}
}
