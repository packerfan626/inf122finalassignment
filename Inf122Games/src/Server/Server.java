package Server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	//Set port number
	private int port = 4444;
	
	//Create server instance
	public Server(int port){
		this.port = port;
	}
	
	public void startServer(){
		System.out.println("Starting Server...");
		
		try{
			ServerSocket serverSocket = new ServerSocket(port);
			
			while(true){
				Socket socket = serverSocket.accept();
				
				Player player = new Player(socket);
				player.start();
				
			}
			
		} catch(Exception e){
			System.out.println("Failed to connect " + e);
		}
	}
	
}
