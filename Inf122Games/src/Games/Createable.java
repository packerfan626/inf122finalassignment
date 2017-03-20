package Games;

public interface Createable		//PLUG - IN THAT ALL GAMES MUST FOLLOW
{
	public void sendMove(int x, int y);		//sending the move to the server by calling Client.sendmessage()
	/*CALLING PROTOCOL :
	
		Client.sendMessage(String message);
		
		 message needs to be in this format: 
		 
		 "MOVE_X_Y" 
		 
		 
		 WHERE X AND Y REPRESENT THE POSITION ON BOARD THEY ARE MOVING TOO. 
	

	
	*/
	public void receiveMove(int x, int y);	//receive opponent move (and update your own board//check for win)
	/*
	 * 
	 * 
	 * every game needs to implement this in order to update their board with a move from the other player.
	 * use this function to also check for a win. 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	
}
