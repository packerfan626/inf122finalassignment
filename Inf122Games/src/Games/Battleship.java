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
	
	public void placeShip(Ship ship, int x, int y, String dir)
	{
		ship.set_xCoord(x);
		ship.set_yCoord(y);
		ship.set_direction(dir);
		ship.setShipCoordinates();
	}
	
	public String gameInto()
	{
		return "Battleship";
	}
	
	//setters
	public void set_hit(int x, int y) 
	{ 
		if(isValid[x][y])
		{
			//check all ships
			for(Ship s : ships)
			{
				if(s.checkShot(x, y))
					s.incementHits();
				else
					System.out.println("YOU SUCK");
			}			
			isValid[x][y] = false;
		}
	}
	
	//getters
	public boolean get_hit(int x, int y) { return isValid[x][y]; }
	public ArrayList<Ship> get_ships() { return ships;}
}
