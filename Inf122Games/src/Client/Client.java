package Client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import Games.Game;
import Games.GameFactory;
import TicTacToe.TicTacToeView;

public class Client {
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket socket;
	
	private String server = "localhost";
	private String username;
	private int port = 4444;
	boolean isHost = false;
	private ArrayList<String> availGames;
	
	//Use to start the game
	private GameFactory gameFactory;
	
	private Game clientGame;
	
	
	public Client(String server, ArrayList<String> availGames ,int port, String username){
		this.server = server;
		this.port = port;
		this.username = username;
		this.availGames = availGames;
	}
	
	public boolean connect(){
		//Establish socket connection
		try{
			socket = new Socket("localhost", port);
		} catch(Exception e){
			System.out.println("Failed to connect to the desired server");
			e.printStackTrace();
			return false;
		}
		
		//Output successful connection to server
		System.out.println("You have connected to the server successfully");
		
		//Setup the output and input streams for the socket/server.
		try{
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch(Exception e){
			System.out.print("Error setting up input streams");
			e.printStackTrace();
			return false;
		}
		
		//Start listening for messages from the server
		//and return true
		new serverListener().start();
		return true;
	}
	
	class serverListener extends Thread {
		public void run(){
			while(true){
				try{
					String message = (String)in.readObject();
					String []strings = message.split("_");
					
					//Listens for a new Game
					if(strings[0].equals("NEWGAME")){
						//String game contains the name of the game if the User Hosted it
						String game = strings[1] + "_" + username;
						String str = strings[1] + "_" + strings[2];
						
						System.out.println(message);
						
						//Checks if game = str; if it does not then add the game to the list; prevents user from viewing their own game
						availGames.add(str);
						ClientGUI.updateGames();
						System.out.println("New game added; waiting for user to join");
					}
					
					//Listens for if they Joined the Game Successfully and will start the game
					else if(strings[0].equals("JOINGAME")){
						//String of the game that is to be played
						String game = strings[1];
						//use "game" to set up what game is to be started
						
						if(strings[1].equals("TicTacToe")){
							clientGame = new TicTacToeView(Client.this);
						}
						
					}
					
					//Listens for the move from the server (aka other user)
					else if(strings[0].equals("MOVE")){
						//parse the move and update my board
						//token1 = x and token 2 = y (or something)
						
						//game receives a move
						//clientGame.receiveMove(int, int);
						
					}
					
					
					
					
					
					
				} catch (Exception e){
					System.out.println("Failed to Listen to server object");
					e.printStackTrace();
				}
			}
		}
	}
	
	//Outputs message to the user
	public void sendMessage(String message){
		try{
			out.writeObject(message);
			out.flush();
		}catch (Exception e){
			System.out.println("Failed to send message to the server");
			e.printStackTrace();
		}
	}
	
}
