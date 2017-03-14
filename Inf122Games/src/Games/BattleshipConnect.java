package Games;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class BattleshipConnect implements Runnable
{
	private String ip = "localhost";
	private int port = 22222;
	private Scanner scanner = new Scanner(System.in);
	private final int WIDTH = 506;
	private final int HEIGHT = 527;
	private Thread thread;

	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	
	private ServerSocket serverSocket;
	
	private boolean yourTurn = false;
	private boolean circle = true;
	private boolean playerWon = false;
	private boolean opponentWon = false;
	private boolean tieGame = false;
	private boolean accepted = false;
	private boolean unableToCommunicateWithOpponent = false;
	
	
	private int errors = 0;
	
	
	public void run() {
		while (true) {
			tick();
//			painter.repaint();

			if (!circle && !accepted) {
				runner();
			}

		}
	}

	public void runner(){
		Socket socket = null;
		try {
			socket = serverSocket.accept();
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			accepted = true;
			System.out.println("CLIENT HAS REQUESTED TO JOIN, AND WE HAVE ACCEPTED");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean connect() {
		try {
			socket = new Socket(ip, port);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			accepted = true;
		} catch (IOException e) {
			System.out.println("Unable to connect to the address: " + ip + ":" + port + " | Starting a server");
			return false;
		}
		System.out.println("Successfully connected to the server.");
		return true;
	}
	
	private void tick() {
		if (errors >= 10){
			unableToCommunicateWithOpponent = true;
		}

		if (!yourTurn && !unableToCommunicateWithOpponent) {
//			try {
//				int space = dis.readInt();
//				if (circle) spots[space] = "X";
//				else spots[space] = "O";
//				checkForEnemyWin();
//				checkForTie();
//				yourTurn = true;
//			} catch (IOException e) {
//				e.printStackTrace();
//				errors++;
//			}
		}
	}
}

