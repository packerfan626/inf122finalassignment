package Server;

public enum CompassDirection
{
	NORTH('N'), EAST('E'), SOUTH('S'), WEST('W');
	
	private char text;
	
	CompassDirection(char text)
	{
		this.text = text;
	}
	
	@Override
	public String toString()
	{
		return String.valueOf(this.text);
	}
	
	public static CompassDirection fromString(String string)
	{
		if(string == null)
			throw new NullPointerException();
		
		if(string.length() != 1)
			throw new IllegalArgumentException("invalid argument");
		
		return fromChar(string.charAt(0));
	}
	
	public static CompassDirection fromChar(char ch)
	{
		for(final CompassDirection direction: CompassDirection.values())
		{
			if(ch == direction.text)
			{
				return direction;
			}
		}
		throw new IllegalArgumentException("invalid direction");
	}
}
