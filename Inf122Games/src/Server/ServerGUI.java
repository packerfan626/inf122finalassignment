package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class ServerGUI
{
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static int serverPort;
	private static boolean created = false;
	private static ServerLogic serverLogic;
	
	public ServerGUI() throws NumberFormatException, IOException
	{
		// TODO Auto-generated constructor stub
		System.out.println("Enter server port");
		serverPort = Integer.parseInt(br.readLine());
	}
	
	public static void main(String args[])
	{
		while(!created)
		{
			try
			{
				ServerGUI server = new ServerGUI();
				serverLogic = new ServerLogic(serverPort);
				serverLogic.waitForPlayers();
			}
			catch (IOException e)
			{
				System.out.println("Server not created - use different port - IO");
			}
			catch (NumberFormatException ne)
			{
				System.out.println("Enter incorrect server port");
			}
			finally
			{
				if (serverLogic != null)
				{
					serverLogic.kill();
				}
			}
		}
	}
	
}
