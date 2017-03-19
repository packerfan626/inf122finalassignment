package Games;

import java.io.Serializable;

import javax.swing.JPanel;

import Server.Server.PlayerThread;

public abstract class Game extends JPanel implements Createable, Serializable 
{
	String player1;
	String player2;
	
	
	public void receiveUpdateBoard(boolean hasHit, int x, int y, String s, boolean des)
	{
		// TODO Auto-generated method stub
		
	}
	

	
	//some functions that each game implements
	
	
}
