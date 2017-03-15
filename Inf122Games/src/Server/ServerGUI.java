package Server;

public class ServerGUI{
	private static Server server;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		server = new Server(4444);
		server.startServer();
	}

}
