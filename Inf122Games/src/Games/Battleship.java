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
		board = new int[x][y];
		isValid = new boolean[x][y];
		
		for (int i = 0; i < x; i++){ //SET THE BOARD
			for (int j = 0; j<y; j++){
				board[i][j] = 0;
			}
		}
		
		for (int i = 0; i < x; i++){ //SET THE VALID MOVES
			for (int j = 0; j<y; j++){
				isValid[i][j] = true;
			}
		}
	}
	
	public void placeShip(Ship ship, int x, int y, String dir)
	{
		try{
			ship.setShipCoordinates(x, y, dir);
			ArrayList<Integer> placeArray = ship.get_Placement();
			
			if (dir.equals("vertical")){
				for (int i = 0; i < placeArray.size(); i++){
					board[x][placeArray.get(i)] = ship.get_shipLength(); //ARBITRARY
				}
			}
			else if (dir.equals("horizontal")){
				for (int i = 0; i < placeArray.size(); i++){
					board[placeArray.get(i)][y] = ship.get_shipLength(); //ARBITRARY
				}
			}
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("~~ERROR: SHIP OUT OF BOUNDS~~");
		}
				
				
	}
	
	public String gameInto()
	{
		return "Battleship";
	}
	
	//setters
	public void set_move(int x, int y) 
	{ 
		if(isValid[x][y])
		{
			System.out.println(isValid[x][y]);
			//check all ships
			for (Ship s : ships)
			{
				System.out.println("checking if " + s.get_type());
				if(s.isValidHit(x, y)){
					s.makeHit(x,y);
					if (s.checkDestroy())
					{
						System.out.println("You just sunk their " + s.get_type() + "!");
					}
					break;
				}
				else
					System.out.println("YOU SUCK");
			}	
			board[x][y] = 0;
			isValid[x][y] = false;
		}
	}
	
	//getters
	public boolean get_hit(int x, int y) { return isValid[x][y]; }
	public ArrayList<Ship> get_ships() { return ships;}
	
	
	
	//TEST METHODS
	public void display()
	{
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j< 10; j++){
				System.out.print(board[j][i] + " ");
			}
			System.out.println();
		}
	}
	public void displayShips()
	{
		for (int i = 0; i < ships.size(); i++){
			System.out.print(ships.get(i).get_type() + " - ");
			System.out.println(ships.get(i).get_shipLength());
		}
		
	}
}