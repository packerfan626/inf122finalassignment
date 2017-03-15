package Client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket socket;
	
	private String server = "localhost";
	private String username;
	private int port = 4444;
	
	public Client(String server, int port, String username){
		this.server = server;
		this.port = port;
		this.username = username;
	}
	
	public boolean connect(){
		try{
			socket = new Socket("localhost", port);
		} catch(Exception e){
			System.out.println("Failed to connect");
			return false;
		}
		
		System.out.println("Client connected successfully");
		
		try{
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			
			out.writeObject(username);
			out.flush();
		} catch(Exception e){
			e.printStackTrace();
			System.out.print("Error connecting");
			return false;
		}
		return true;
		
		
	}
	
}
