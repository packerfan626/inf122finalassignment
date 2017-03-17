package Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Games.Game;
import TicTacToe.TicTacToe;
import TicTacToe.TicTacToeView;

public class ClientGUI {
	static Client client;
	static String username;
	static ArrayList<String> availGames;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		availGames = new ArrayList<>();
		System.out.print("Enter username: ");
		username = scanner.nextLine();
		
		client = new Client("localhost", availGames, 4444, username);
		client.connect();
		client.sendMessage("REG_" + username);
		
		System.out.println("Select which game you would like to play: "
				+ "\n1. Tic Tac Toe"
				+ "\n2. Chess"
				+ "\n3. Othello"
				+ "\nSelection:");
		
		int selection = scanner.nextInt();
		
		switch(selection){
			case 1: 
				//Sets client as host for starting TicTacToe
				startGame("TicTacToe");
				break;
			case 2: 
				//Sets client as host for Battleship
				startGame("Battleship");
				break;
			case 3:
				startGame("Othello");
				break;
			default:
				System.out.println("Invalid Selection");
				break;
		}
	}
	
	public static void startGame(String game){
		//Sets client as host for selected game
		client.isHost = true;
		
		//Adds game to the list of games
		String str = game + "_" + username;
		availGames.add(str);
		
		client.sendMessage("NEWGAME_" + str);
	}
}
