package Server;

import java.io.PrintWriter;

import Games.Game;
import Games.Player;

public class ClientServerController
{
	
	public ClientServerController () {	}
	
	public void ServerToClientMessage(PrintWriter output, String message)
	{
		// TODO Auto-generated method stub
		output.print("BOB");
		System.out.println("BOB");
		output.print(message);
		output.flush();
	}

	public void ClientToClientMessage(ClientGUI clientGUI, String serverReply)
	{
		// TODO Auto-generated method stub
		clientGUI.OutputSent(serverReply);
	}
	
	public void addGame(PlayerGuide playerGuide, Game game, ClientThread thread)
	{
		// TODO Auto-generated method stub
		game.addPlayer(playerGuide, thread);
	}
	
	public void die(Game game, ClientThread thread)
	{
		// TODO Auto-generated method stub
//		Player player = game.getPlayerThreadMap(thread);
//		game.die(player);
	}

	public void playerLeft(Game game, ClientThread thread)
	{
		// TODO Auto-generated method stub
//		Player player = game.getPlayerThreadMap(thread);
//		game.playerLeft(player);
	}

	

}
