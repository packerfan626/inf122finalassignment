import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class ServerTest
{
	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	private static BufferedReader bufferedReader;
	private static String inputLine;
	public static void main(String[] args)
	{
		// Wait for client to connect on 63400
		try
		{
			serverSocket = new ServerSocket(63400);
			clientSocket = serverSocket.accept();
			// Create a reader
			bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			// Get the client message
			while((inputLine = bufferedReader.readLine()) != null)
			System.out.println(inputLine);
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
}
