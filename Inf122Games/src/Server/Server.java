package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Games.Game;
import Games.Player;

public class Server {
	
	//Set port number
	private int port = 4444;

	//ArrayList of PlayerThreads
	private ArrayList<PlayerThread> playerThread;
	
	boolean isActive = false;
	
	//Create server instance
	public Server(int port){
		this.port = port;
		playerThread = new ArrayList<>();
	}
	
	public void startServer(){
		System.out.println("Starting Server...");
		isActive = true;
		try{
			ServerSocket serverSocket = new ServerSocket(port);

			while(isActive){
				//Accept new clients to server
				Socket socket = serverSocket.accept();
				
				if(!isActive){
					break;
				}
				
				//Create a new player thread and add player to ArrayList and start run()
				PlayerThread player = new PlayerThread(socket);
				playerThread.add(player);
				player.start();
			}
			try{
				serverSocket.close();
				for(PlayerThread player: playerThread){
					try{
						player.in.close();
						player.out.close();
						player.socket.close();
					}catch (Exception e){
						e.printStackTrace();
						System.exit(1);
					}
				}
			} catch (Exception e){
				System.err.println("Failed to close server");
			}
		} catch(Exception e){
			System.out.println("Failed to connect server");
		}
	}
	
	
	public void stopServer(){
		isActive = false;
        try {
            new Socket("localhost", port);
        }catch (IOException e){
            //end
        }
	}
	
	public class PlayerThread extends Thread{

		//Server side communication
		Socket socket;
		ObjectInputStream in;
		ObjectOutputStream out;
		
		//Username and Opponent
		String username;
		String opponent;
		
		public PlayerThread(Socket socket){
			this.socket = socket;
			
			try{
				//Sets up input and object streams
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());
			}catch(Exception e){
				//Output error messages and exits the Stream setup
				System.out.print("Failed to set streams for client");
				e.printStackTrace();
				System.exit(1);
			}
		}
		
		//Set opponent for the user; this is important to distinguish who the user is connected to
		public void setOpponent(String opponent){
			this.opponent = opponent;
		}
		
		public void removeConnection(String username){
			for (PlayerThread players : playerThread){
				if(players.username.equals(username)){
					playerThread.remove(players);
				}
			}
		}
		
		public void run(){
			boolean isActive = true;
			while(isActive){			
				try {
					//Read in message and set protocol string based on message
					String message = (String)in.readObject();
					System.out.println("Message that server reads: " + message);
					String []strings = message.split("_");
						
					//Checks if it is a new user and sets their username and outputs to the lobby of the new user joined
					if(strings[0].equals("REG")){
						this.username = strings[1];
						
						//Outputs who has joined the server
						ServerGUI.updateServer(this.username + " has joined the server");
						for(PlayerThread player: playerThread){
							player.out.writeObject(this.username + " has joined the lobby");
						}
					}
					
					//Joins the game of the desired host
					if (strings[0].equals("JOINGAME")){
						for(PlayerThread player: playerThread){	//joined order
							if(player.username.equals(strings[2])){
								//Setting each player as opponents
								System.out.println("this.username:" + this.username);
								player.opponent = this.username;
								this.opponent = strings[2];
								
								ServerGUI.updateServer("Player '" + this.username + "' has joined '" + 
										this.opponent + "'s' game!");
								
								player.out.writeObject(message);
								this.out.writeObject(message);
							}
						}
					}
					
					//Makes move in game and displays it to the user
					else if (strings[0].equals("MOVE")){
						for(PlayerThread player: playerThread){
							if(player.opponent.equals(username)){
								//Strings[1] = x; Strings[2] = y
								player.out.writeObject(message);
							}
						}
					}
					
					//Listens for the NEWGAME; will output to all users that there is a new game available.
					else if (strings[0].equals("NEWGAME")){
						ServerGUI.updateServer("New game '" + strings[1] +"' created by user '" + strings[2]);
						for(PlayerThread player: playerThread){
							player.out.writeObject(message);
						}
					}
					
					//UPDATE BOARD FOR BATTLESHIP
					else if (strings[0].equals("UPDATEBOARD")){
						ServerGUI.updateServer(message);
						for(PlayerThread player: playerThread){
							if(player.opponent.equals(username)){
								//Strings[1] = x; Strings[2] = y
								player.out.writeObject(message);
							}
						}
					}					
					
					//DEPLOY BOARD FOR BATTLESHIP
					else if (strings[0].equals("DEPLOYED")){
						ServerGUI.updateServer(message);
						for(PlayerThread player: playerThread){
							if(player.opponent.equals(username)){
								//Strings[1] = x; Strings[2] = y
								player.out.writeObject(message);
							}
						}
					}					
					//QUITGAME
					else if (strings[0].equals("QUITGAME")){
						ServerGUI.updateServer(message);
						for(PlayerThread player: playerThread){
							if(player.opponent.equals(username)){
								player.out.writeObject(message);
								
								this.opponent = null;
								player.opponent = null;
							}
						}
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Failed to run thread for client: ");
					e.printStackTrace();
					break;
				}
			}
			
			//Terminates the client

			ServerGUI.updateServer(username + " has left the server lobby!");
			removeConnection(username);
			try {
				in.close();
				out.close();
				socket.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}	
}
