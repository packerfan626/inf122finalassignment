package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Games.Game;

public class Server {
	
	//Set port number
	private int port = 4444;
	
	private ArrayList<PlayerThread> playerThread;
	static private Game currentGame;
	
	
	//Create server instance
	public Server(int port){
		this.port = port;
		playerThread = new ArrayList<>();
		currentGame = null;
	}
	
	public void startServer(){
		System.out.println("Starting Server...");
		try{
			ServerSocket serverSocket = new ServerSocket(port);
			
			while(true){
				Socket socket = serverSocket.accept();
				System.out.println("Accepted Client");
				
				PlayerThread player = new PlayerThread(socket);
				playerThread.add(player);
				player.start();
			}
			
		} catch(Exception e){
			System.out.println("Failed to connect " + e);
		}
	}
	
	public class PlayerThread extends Thread{

		//Server side communication
		Socket socket;
		ObjectInputStream in;
		ObjectOutputStream out;
		
		//Username and Opponent
		String username;
		PlayerThread opponent;
		
		public PlayerThread(Socket socket){
			this.socket = socket;
			
			try{
				//Sets up input and object streams
				in = new ObjectInputStream(socket.getInputStream());
				out = new ObjectOutputStream(socket.getOutputStream());
				
				//Reads in username as a String
				this.username = in.readObject().toString();

			}catch(Exception e){
				System.out.print("Failed: " + e);
				System.exit(1);
			}
		}
		
		public void setOpponent(PlayerThread opponent){
			this.opponent = opponent;
		}
		
		public void run(){
			//while(true){				
				try {
					
					//get game selection and make that game..."
					currentGame = new Game();
					System.out.println("Making a new game...");
					
					for(PlayerThread player: playerThread){
						//player.out.writeObject(message);
						player.out.writeObject(currentGame);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//}
		}
	}	
}
