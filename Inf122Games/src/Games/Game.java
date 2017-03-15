package Games;

import Server.ClientThread;
import Server.PlayerGuide;

public abstract class Game implements Createable
{
	Player player1;
	Player player2;
	
	public void makeBoard(int x, int y)
	{
		// TODO Auto-generated method stub
		
	}

	public String getGoal()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void addPlayer(PlayerGuide playerGuide, ClientThread thread)
	{
		// TODO Auto-generated method stub
		
	}

	public void die(Player player)
	{
		// TODO Auto-generated method stub
		
	}

	public Player getPlayerThreadMap(ClientThread thread)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void playerLeft(Player player)
	{
		// TODO Auto-generated method stub
		
	}

}
