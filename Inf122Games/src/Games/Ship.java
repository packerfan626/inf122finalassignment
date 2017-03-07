package Games;

public class Ship
{
	private String type,			//type of ship
		direction;					//direction of ship
	private int xCoord, yCoord, 	//tail of ship 
		x1Coord, y1Coord,			//head of ship
		shipLength,					//length of ship		
		hits;						//number of hits
	private boolean shipCoords [][];
	
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
		this.x1Coord = x1;
		this.y1Coord = y1;
		this.shipLength = len;
		this.direction = "horizontal";	
		if(x1 == xCoord)				//check direction of ship is vertical
			direction = "vertical";
		this.hits = 0;
	}

	public boolean checkShot(int x, int y) { return shipCoords[x][y]; }
	public boolean checkDestroy() { return hits == shipLength; }
	
	//setter functions
	public void set_xCoord(int x) {	this.xCoord = x; }
	public void set_yCoord(int y) { this.yCoord = y; }
	public void set_x1Coord(int x1) { this.x1Coord = x1; }
	public void set_y1Coord(int y1) { this.y1Coord = y1; }
	public void incementHits() { this.hits++; }
	public void set_direction() { 
		if(x1Coord == xCoord)
			this.direction = "vertical";
		else
			this.direction = "horitzontal";
	}
	public void setShipCoordinates()
	{		
		if(direction.equalsIgnoreCase("vertical"))
		{
			int inc = yCoord;
			for(int i = 0; i < shipLength; i++)
				this.shipCoords[xCoord][inc++] = true;		}	
		else
		{
			int inc = xCoord;
			for(int i = 0; i < shipLength; i++)
				this.shipCoords[inc++][yCoord] = true;
		}
	}
	
	//getter functions
	public int get_shipLength() { return shipLength; }
	public int get_xCoord() { return xCoord; }
	public int get_yCoord() { return yCoord; }	
	public int get_x1Coord() { return x1Coord; }
	public int get_y1Coord() { return y1Coord; }
	public int get_hits() { return hits; }
	public String Get_type() { return type; }	
}
