package Client;

public class ClientGUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client client = new Client("localhost", 4444, "cpoon");
		client.connect();
	}

}
