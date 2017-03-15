package Games;
import java.net.Socket;

public class Player {
	
	String username;
	Socket socket;
	int clientNum;
	
	public Player(String username, Socket socket, int client)
	{
		this.username = username;
		this.socket = socket;
		this.clientNum = client;
	}
	public void makeMove(int x, int y)
	{
		//pass x,y to server to display on other clientGUI
		
	}
	
	
	
	//getters
	public String getUsername() { return username; }
	public Socket getScoket() { return socket; }
	public int getClientNum() { return clientNum; }
	
	
}

