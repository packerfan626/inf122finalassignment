package Games;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;

public class BattleshipGUI1 extends JFrame
{

	private JPanel contentPane;
	private final JButton[][] grid = new JButton[10][10];

	/**
	 * Create the frame.
	 */
	public BattleshipGUI1()
	{
		super("Battleships");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel yourView = new JPanel();
		yourView.setBounds(10, 11, 288, 344);		
		contentPane.add(yourView);
		yourView.setLayout(new GridLayout(10, 10));
		

		JPanel opponentView = new JPanel();
		opponentView.setBounds(330, 11, 302, 344);
		contentPane.add(opponentView);
		opponentView.setLayout(new GridLayout(10, 10));
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setBounds(655, 11, 89, 23);
		contentPane.add(btnQuit);
		
		JButton btnPlay = new JButton("Set ships");
		btnPlay.setBounds(655, 305, 89, 23);
		contentPane.add(btnPlay);
		
		JButton btnReady = new JButton("Ready");
		btnReady.setBounds(655, 339, 89, 23);
		contentPane.add(btnReady);
		
		JPanel panel_ships = new JPanel();
		panel_ships.setBounds(655, 45, 89, 232);
		contentPane.add(panel_ships);
		panel_ships.setLayout(new GridLayout(5, 0, 0, 0));
		
		JButton btnNewButton = new JButton("Aircraft Carrier");
		panel_ships.add(btnNewButton);
		
		JButton btnBattleship = new JButton("Battleship");
		panel_ships.add(btnBattleship);
		
		JButton btnNewButton_1 = new JButton("Submarine");
		panel_ships.add(btnNewButton_1);
		
		JButton btnCruiser = new JButton("Cruiser");
		panel_ships.add(btnCruiser);
		
		JButton btnDestroyer = new JButton("Destroyer");
		panel_ships.add(btnDestroyer);
		
		for(int i = 0; i < 10; ++i)
		{
			for(int j = 0; j < 10; ++j)
			{
				JButton b = new JButton();
				JButton c = new JButton();
				opponentView.add(b);
				yourView.add(c);
			}
		}		
	}
	
	private void buildBoard() {
		for(int i = 0; i < 10; ++i)
		{
			for(int j = 0; j < 10; ++j)
			{
				JButton b = new JButton();
				grid[i][j] = b;
				//boardLayout.add(grid[i][j]);
			}
		}
	}
}
