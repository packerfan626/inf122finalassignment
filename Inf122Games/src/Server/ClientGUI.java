package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientGUI
{
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private String serverIP;
	private int portValue;
	private ClientLogic clientLogic;
	
	public ClientGUI()
	{
		// TODO Auto-generated constructor stub
		try
		{
			System.out.println("Enter servername or IP");
			serverIP = br.readLine();
			System.out.println("Please Enter the port number:");
			portValue = Integer.parseInt(br.readLine());
			clientLogic = new ClientLogic(serverIP, portValue, this);
		}
		catch (IOException e)
		{
			System.out.println("buffer reader doesn't exist");
		}
		catch (NumberFormatException ne)
		{
			System.out.println("port can be only numbers");
		}
	}
	
	private void TakeInputAndAct() throws IOException
	{
		// TODO Auto-generated method stub
		while(true)
		{
			System.out.println("COMMAND: ");
			String inputMessage = br.readLine();
			switch (inputMessage)
			{
			case "N":
				System.out.println("N");
				break;
			case "W":
				System.out.println("W");
				break;
			case "E":
				System.out.println("E");
				break;
			case "S":
				System.out.println("S");
				break;
			case "PICKUP":
				System.out.println("PICKUP");
				break;
			case "EXIT":
				System.out.println("EXIT");
				System.exit(0);
				break;
			}
		}
	}
	
	public static void main(String args[])
	{
		ClientGUI client = new ClientGUI();
		try
		{
			client.TakeInputAndAct();
		}
		catch (IOException e)
		{
			System.out.println("Buffer reader doesn't exist");
		}
	}
	public void OutputSent(String reply)
	{
		System.out.println(reply);
	}
}
