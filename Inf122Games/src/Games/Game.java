package Games;

import java.io.Serializable;

import Server.Server.PlayerThread;

public class Game implements Createable, Serializable 
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
		System.out.println("In getgoal");
		return null;
	}

	public void die(Player player)
	{
		// TODO Auto-generated method stub
	}

	public Player getPlayerThreadMap(PlayerThread thread)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void playerLeft(Player player)
	{
		// TODO Auto-generated method stub	
	}
}
