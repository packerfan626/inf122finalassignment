package Client;

import java.util.Scanner;

public class ClientGUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter username: ");
		String username = scanner.nextLine();
		
		Client client = new Client("localhost", 4444, username);
		client.connect();
		
		System.out.println("Enter Message: ");
		String message = scanner.nextLine();
		client.sendMessage(message);
	}
}
