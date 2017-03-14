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

//create multiple clients to the servers
public class ClientThread implements Runnable
{
	private BufferedReader input;
	private PrintWriter output;
	private int clientNum;
	private Socket socket;
	private Game game;
	private ClientServerController CSC;
	
	public ClientThread(Socket client, Game game, int clientNum)
	{
		// TODO Auto-generated constructor stub
		this.game = game;
		this.clientNum = clientNum;
		this.socket = client;		
		CSC = new ClientServerController();
		
		//see unique clients
		System.out.println(client.toString());
		
		try 
		{
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), false);
			
			//THIS IS WHERE YOU ADD PLAYER TO THE GAME
		}
		catch (IOException e)
		{
			System.err.println("not set up for client");
		}
	}
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		try
		{
			for(int i = 1; i <= 10; ++i)
			{
				outputMessage("Test to client : " + Integer.toString(clientNum));
				System.out.println("Test to Client : " + Integer.toString(clientNum));
				Thread.sleep(5000);
			}
			
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	//sends a message to client
	protected void outputMessage(String message)
	{
		CSC.ServerToClientMessage(output, message);
	}
}
