package Battleship;

import java.util.ArrayList;

import javax.swing.JFrame;

import Client.Client;
import Games.Game;

public class Battleship extends Game
{
	final static private String AIRCRAFT_CARRIER = "Aircraft Carrier", //length 5
			BATTLESHIP = "Battleship", 	//length 4
			SUBMARINE = "Submarine", 	//length 3
			CRUISER = "Cruiser", 		//length 3
			DESTROYER = "Destroyer";	//length 2
	private static boolean isValid [][];
	private static int board[][];	
	private static ArrayList<Ship> ships = new ArrayList<Ship>();
	static Client client;
	private boolean turn;
	
	public Battleship()
	{
		makeBoard(10,10);
		ships.add(new Ship(AIRCRAFT_CARRIER, 5));
		ships.add(new Ship(BATTLESHIP, 4));
		ships.add(new Ship(SUBMARINE, 4));
		ships.add(new Ship(CRUISER, 3));
		ships.add(new Ship(DESTROYER, 2));
	}
	
	public Battleship(Client client)
	{
		makeBoard(10,10);
		ships.add(new Ship(AIRCRAFT_CARRIER, 5));
		ships.add(new Ship(BATTLESHIP, 4));
		ships.add(new Ship(SUBMARINE, 4));
		ships.add(new Ship(CRUISER, 3));
		ships.add(new Ship(DESTROYER, 2));
		
		this.client = client;
		BattleshipGUI1 frame = new BattleshipGUI1(this);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);	
		if(client.isHost)
			turn = true;
		else
			turn = false;
	}
	
	public void makeBoard(int x, int y)
	{
		board = new int[x][y];
		isValid = new boolean[x][y];
		
		for (int i = 0; i < x; i++){ //SET THE BOARD
			for (int j = 0; j<y; j++){
				board[i][j] = 0;
				isValid[i][j] = true;
			}
		}
	}
	
	public void placeShip(Ship ship, int x, int y, String dir)
	{
		ship.set_xCoord(x);
		ship.set_yCoord(y);
		ship.set_direction(dir);
		ship.setShipCoordinates();
		
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
	public Ship set_hit(int x, int y) 
	{ 
		if(isValid[x][y])
		{
			isValid[x][y] = false;
			//check all ships
			for(Ship s : ships)
			{
				if(s.checkShot(y, x))
				{
					s.incementHits();
					return s;
				}
			}				
		}
		return null;
	}
	
	public void set_move(int x, int y) 
	{ 
		boolean hitShip = false;
		if(isValid[x][y]) //returns TRUE if move is not yet made
		{
			String ship = "NONE";
			boolean shipDestroy = false;
			//check all ships
			for (Ship s : ships)
			{
				if(s.isValidHit(x, y)){
					s.makeHit(x,y);
					System.out.println(s.get_type() +" HAS BEE HIT");
					ship = s.get_type();
					
					if (s.checkDestroy())
					{
						System.out.println("You just sunk their " + s.get_type() + "!");
						shipDestroy = true;
					}
					System.out.println("UPDATE YOUR BOARD = x: " + x + " y: " + y);
					//UPDATE YOUR BOARD THAT THEY HIT
					BattleshipGUI1.updateYourBoard(true, y, x);
					//SEND MESSAGE TO UPDATE THEIR BOARD
					updateBoard(true, y,x, ship, shipDestroy);
					hitShip = true;
					break;
				}
				else
				{
					System.out.println("YOU SUCK");
				}
			}	
			board[x][y] = 1;
			isValid[x][y] = false;
			if(!hitShip)
			{
				System.out.println("UPDATE MISSED");
				//UPDATE YOUR BOARD THAT THEY MISSED
				BattleshipGUI1.updateYourBoard(false, y, x);
				//SEND MESSAGE TO UPDATE THEIR BOARD
				updateBoard(false, y, x, "no", false);
			}
		}
		else{ //isValid[x][y] returns FALSE, move has been made
			System.out.println("Already fired here.");
			//already fired here
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
	
	public void receiveUpdateBoard(boolean hasHit, int x, int y, String ship, boolean destroy)
	{
		System.out.println("UPDATEBOARD_"+ hasHit);
		//update opponents board here
		BattleshipGUI1.updateOppBoard(hasHit, x, y, ship, destroy);		
	}
	
	public void updateBoard(boolean hasHit, int x, int y, String ship, boolean destroy)
	{
		
		System.out.println("UPDATE BOARD = x: " + x + " y: " + y);
		client.sendMessage("UPDATEBOARD_"+ hasHit + "_" + x + "_" + y + "_" + ship + "_" + destroy);
	}
	
	@Override
	public void sendMove(int x, int y)
	{
		client.sendMessage("MOVE_" + x+ "_"+ y );
		System.out.println("X CORD: " + x + " Y CORD: " + y);
	}

	@Override
	public void receiveMove(int x, int y)
	{
		System.out.println("received move" );		
		System.out.println("X CORD: " + x + " Y CORD: " + y);

		set_move(x, y);
			
	}
	
	public void receiveDeployStatus(boolean deployed)
	{
		System.out.println("DEPLOYEDSTATUS_"+ deployed);
		//update opponents board here
		BattleshipGUI1.updateOppDeployed(deployed);		
	}
	
	public void sendDeployStatus(boolean deployed)
	{
		
		System.out.println("DEPLOYED = : " + deployed);
		client.sendMessage("DEPLOYED_"+ deployed);
	}
	
	public void switchPlayer(){
		if(turn = true){
			turn = false;
		}else{
			turn = true;
		}
		
	}
	
	public void reset()
	{
		isValid = new boolean[10][10];
		board = new int[10][10];	
		ships = new ArrayList<Ship>();
		ships.add(new Ship(AIRCRAFT_CARRIER, 5));
		ships.add(new Ship(BATTLESHIP, 4));
		ships.add(new Ship(SUBMARINE, 4));
		ships.add(new Ship(CRUISER, 3));
		ships.add(new Ship(DESTROYER, 2));
		makeBoard(10,10);
	}
	
	public void changeTurn(boolean turn) {	this.turn = turn; }
	public boolean get_turn() {	return turn; }
}