package Games;

import Battleship.Battleship;
import Client.Client;
import Othello.OthelloGUI;
import TicTacToe.TicTacToeView;

public class GameFactory {

	public Game setGame(Client client, String string) {
		
		if(string.equals("TicTacToe"))
		{
			return new TicTacToeView(client);
		}						

		if(string.equals("Battleship"))
		{
			return new Battleship(client);
		}
		if(string.equals("Othello"))
		{
			return new OthelloGUI(client);
		}		
		else
			return null;
	}

	
	
}
