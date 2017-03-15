package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.print.attribute.standard.OutputDeviceAssigned;

public class Player extends Thread{

	//Server side communication
	Socket socket;
	ObjectInputStream in;
	ObjectOutputStream out;
	
	//Username and Opponent
	String username;
	Player opponent;
	
	public Player(Socket socket){
		this.socket = socket;
		
		try{
			//Sets up input and object streams
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			
			//Reads in username as a String
			this.username = in.readObject().toString();
			System.out.println(this.username);

		}catch(Exception e){
			System.out.print("Failed: " + e);
			System.exit(1);
		}
	}
	
	public void setOpponent(Player opponent){
		this.opponent = opponent;
	}
	
	public void run(){
		System.out.println("Here");
	}
}
