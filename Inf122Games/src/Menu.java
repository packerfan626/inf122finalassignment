

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Menu extends JFrame{

	private JFrame frame;

	
	//run the menuGUI
	public void run() {
	try 
	{
		Menu window = new Menu();
		window.frame.setVisible(true);
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
	}

	}
		
	

	/**
	 * Create the application.
	 */
	public Menu(){
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnOthello = new JButton("Othello");
		btnOthello.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CALL OTHELLO GUI
			}
		});
		btnOthello.setBounds(117, 174, 234, 58);
		frame.getContentPane().add(btnOthello);
		
		JButton btnBattleship = new JButton("Battleship");
		btnBattleship.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				//CALL BATTLESHIP GUI
		}
			
			
		});
		
		btnBattleship.setBounds(117, 258, 234, 58);
		frame.getContentPane().add(btnBattleship);
		
		JButton btnTictactoe = new JButton("Tic-Tac-Toe");
		btnTictactoe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//CALL TIC-TAC-TOE GUI
			}
		});
		btnTictactoe.setBounds(117, 345, 234, 58);
		frame.getContentPane().add(btnTictactoe);
		
		JLabel lblWelcome = new JLabel("Welcome!");
		lblWelcome.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 26));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(133, 55, 199, 36);
		frame.getContentPane().add(lblWelcome);
		
		JLabel lblSelectAGame = new JLabel("Select A Game");
		lblSelectAGame.setFont(new Font("Tekton Pro", Font.PLAIN, 17));
		lblSelectAGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectAGame.setBounds(174, 125, 121, 36);
		frame.getContentPane().add(lblSelectAGame);
		
	
	}
}
