package Games;
import java.util.ArrayList;

public class Ship
{
	private String type,			//type of ship
		direction;					//direction of ship
	private int xCoord, yCoord, 	//tail of ship 
		shipLength,					//length of ship		
		hits;						//number of hits
	private ArrayList<Integer> x1Coord, y1Coord;
	private boolean isDestroyed;
	private boolean shipCoords [][]; //I DON'T THINK WE NEED THIS

	
	//constructors
	public Ship(String type, int len)
	{
		this.type = type;
		this.shipLength = len;
	}
	
	public Ship(String type, int xCoord, int yCoord, int x1, int y1, int len)
	{
		this.type = type;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
//		this.x1Coord = x1;
//		this.y1Coord = y1;
		this.shipLength = len;
		this.direction = "horizontal";	
		if(x1 == xCoord)				//check direction of ship is vertical
			direction = "vertical";
		this.hits = 0;
	}

	public boolean checkShot(int x, int y) { return shipCoords[x][y]; }
	public boolean checkDestroy() { return hits == shipLength; }
	public void clearCord()
	{
		for(int i = 0; i < 10;++i)
		{
			for(int j =0 ; j < 10; ++j)
				shipCoords[i][j]=false;
		}
	}
	//setter functions
	public void set_xCoord(int x) {	this.xCoord = x; }
	public void set_yCoord(int y) { this.yCoord = y; }

//	public void set_x1Coord(int x1) { this.x1Coord = x1; }
//	public void set_y1Coord(int y1) { this.y1Coord = y1; }
	public void incementHits() { this.hits++; }
	public void set_direction(String dir) { 
		this.direction = dir;
		if(dir.equalsIgnoreCase("vertical"))
		{
//			x1Coord = xCoord;
//			y1Coord = yCoord + shipLength-1;
		}
		else
		{
//			x1Coord = xCoord + shipLength-1;
//			y1Coord = yCoord;
		}
	}
	
  public void setShipCoordinates()
	{		
		if(direction.equalsIgnoreCase("vertical"))
		{
//			int inc = xCoord;
			for(int i = 0; i < shipLength; i++)
				this.shipCoords[yCoord+i][xCoord] = true;		}	
		else
		{
			int inc = xCoord;
			for(int i = 0; i < shipLength; i++)
				this.shipCoords[inc++][yCoord] = true;
		}
	}

	public void makeHit(int x, int y) { 
		this.hits++;
		int toRemove = 0;
		if (this.direction.equals("vertical")){
			for (int i = 0; i < y1Coord.size(); i++){
				if (y1Coord.get(i) == y){
					toRemove = i;
				}
			}
			y1Coord.remove(toRemove);
		}
		else{
			for (int i = 0; i < x1Coord.size(); i++){
				if (x1Coord.get(i) == x){
					toRemove = i;
				}
			}
			x1Coord.remove(toRemove);
		}
	}
  
	public void setShipCoordinates(int x, int y, String dir) throws ArrayIndexOutOfBoundsException
	{	
		if(dir.equalsIgnoreCase("vertical"))
		{
			this.direction = "vertical";
			this.xCoord = x;
			this.x1Coord = null;
			this.yCoord = y;
			this.y1Coord = new ArrayList<Integer>();
			
			for(int i = 0; i < shipLength; i++){
				this.y1Coord.add(y+i);
			}
		}
		else // direction is horizontal

		{
			this.direction = "horizontal";
			this.xCoord = x;
			this.x1Coord = new ArrayList<Integer>();
			this.yCoord = y;
			this.y1Coord = null;
			
			for(int i = 0; i < shipLength; i++){
				this.x1Coord.add(x+i);
			}
		}
	}
	
	//getter functions
	public int get_shipLength() { return shipLength; }
	public int get_xCoord() { return xCoord; }
	public int get_yCoord() { return yCoord; }
	public ArrayList<Integer> get_Placement() 
	{ 
		if (this.direction.equals("vertical")){
			return y1Coord;
		}
		else{
			return x1Coord;
		}
	}
	public int get_hits() { return hits; }
	public boolean isValid(int x, int y) { return true; }
	public String get_type() { return type; }	
	public boolean isValidHit(int x, int y)
	{
		if (this.direction.equals("vertical")){
			for (int i = 0; i < y1Coord.size(); i++){
				if (y1Coord.get(i) == y){
					if (xCoord == x){
						return true;
					}
				}
			}
			return false;
		}
		else {
			for (int i = 0; i < x1Coord.size(); i++){
				if (x1Coord.get(i) == x){
					if (yCoord == y){
						return true;
					}
				}
			}
			return false;
		}
	}
}
