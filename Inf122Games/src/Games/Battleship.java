package Games;

import java.util.ArrayList;

public class Battleship extends Game
{
	final private String AIRCRAFT_CARRIER = "Aircraft Carrier", //length 5
			BATTLESHIP = "Battleship", 	//length 4
			SUBMARINE = "Submarine", 	//length 3
			CRUISER = "Cruiser", 		//length 3
			DESTROYER = "Destroyer";	//length 2
	private boolean isValid [][];
	private int board[][];	
	private ArrayList<Ship> ships = new ArrayList<Ship>();
	
	public Battleship()
	{
		makeBoard(10,10);
		ships.add(new Ship(AIRCRAFT_CARRIER, 5));
		ships.add(new Ship(BATTLESHIP, 4));
		ships.add(new Ship(SUBMARINE, 4));
		ships.add(new Ship(CRUISER, 3));
		ships.add(new Ship(DESTROYER, 2));
	}
	
	public void makeBoard(int x, int y)
	{
		// TODO Auto-generated method stub
		board = new int [x][y];		
	}
	
	public void placeShip(Ship ship)
	{
		//set (x, y) head coordinates
		//set (x1, y1) tail coordinates
	}
	
	public String gameInto()
	{
		return "Battleship";
	}
	
	//setters
	
	
	//getters
	public ArrayList<Ship> get_ships() { return ships;}
}
