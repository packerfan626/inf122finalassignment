package Client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Games.Game;
import Server.ServerGUI;

public class Client {
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket socket;
	private Game board;
	
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
		
		System.out.println("Client connected successfully!!");
	
		try{
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());

			out.writeObject(username);
			out.flush();
		} catch(Exception e){
			e.printStackTrace();
			System.out.print("Error connecting");
			return false;
		}
		
		new serverListener().start();
		
		return true;
	}
	
	class serverListener extends Thread {
		public void run(){
			while(true){
				try{
//					Game temp = (Game)in.readObject();
//					System.out.println(temp.getGoal());
					String temp = (String) in .readObject();
					ClientGUI.updateServer(temp);
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public void sendMessage(String message){
		try{
			out.writeObject(message);
			out.flush();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
//	public void sendMessage(Game board){
//		try{
//			out.writeObject(board);
//			out.flush();
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//	}
	
}
