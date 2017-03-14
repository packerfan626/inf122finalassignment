package Server;

import Games.Game;
import Games.PlayerListener;

public abstract class PlayerGuide implements PlayerListener
{
	private Game game;
	private ClientServerController childCSC;

	//handled by client thread
	public abstract void run();
	abstract protected void outputMessage(String message);
	
	@Override
	public void makeMove(int x, int y)
	{
		//pass x,y to server to display on other clientGUI
		outputMessage("MAKE_MOVE");
	}


	@Override
	public void startTurn()
	{
		// TODO Auto-generated method stub
		outputMessage("START_TURN");
	}


	@Override
	public void change()
	{
		// TODO Auto-generated method stub
		outputMessage("CHANGE");
	}


	@Override
	public void endTurn()
	{
		// TODO Auto-generated method stub
		outputMessage("END_TURN");
	}


	@Override
	public void win()
	{
		// TODO Auto-generated method stub
		outputMessage("WIN");
	}


	@Override
	public void lose()
	{
		// TODO Auto-generated method stub
		outputMessage("LOSE");
	}	
	
	public void addGame(Game game, ClientThread thread)
	{
		childCSC.addGame(this, game, thread);
		outputMessage("GOAL " + this.game.getGoal());
	}
	
	public void userConnectionGone(ClientThread thread)
	{
		//kill player in game
		childCSC.die(game, thread);
		//remove from turn list
		childCSC.playerLeft(game,thread);
	}
	
	public void processCommand(String commandString, ClientThread thread)
	{
		commandString = commandString.toUpperCase();
		
		final String commandStringSplit[] = commandString.split(" ", 2);
		final String command = commandStringSplit[0];
		final String arg = ((commandStringSplit.length == 2) ? commandStringSplit[1]: null);
		try
		{
			processCommandAndArgument(command, arg, thread);
		}
		catch (final CommandException e)
		{
			outputMessage("FAIL " + e.getMessage());
		}
	}
	
	private void processCommandAndArgument(String command, String arg, ClientThread thread) throws CommandException
	{
		// TODO Auto-generated method stub
		
		
	}
	public void setCSC(ClientServerController csc)
	{
		childCSC = csc;
	}
	
	public void setGame(Game game)
	{
		this.game = game;
	}
}
