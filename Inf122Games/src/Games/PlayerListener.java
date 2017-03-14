package Games;

public interface PlayerListener
{
//	public void sendMessage(String message);		//don't need
//	public void sendChatMessage(String message);	//don't need
	public void startTurn();
	public void change();
	public void endTurn();
	public void win();
	public void lose();
	public void makeMove(int x, int y);
//	public void hpChange(int value);				//don't need
//	public void treasureChagne(int value);			//don't need	
}
