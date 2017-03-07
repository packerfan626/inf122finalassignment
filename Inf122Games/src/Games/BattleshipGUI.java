package Games;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class BattleshipGUI extends JFrame
{
	JFrame mainFrame = new JFrame("Battleship Game");
	Battleship board = new Battleship();
	
	private final JButton[][] grid = new JButton[10][10];
	private Container player1;
	private final JPanel boardLayout;
	
	public BattleshipGUI() { 	
		boardLayout = new JPanel(new GridLayout(10,10));
//		boardLayout.setLayout(new GridBagLayout());
		boardLayout.setPreferredSize(new Dimension(400, 400));
		//boardLayout.setBorder(BorderFactory.createLineBorder(new Color(32, 156, 185)));
		
		buildBoard();
		
		player1.add(boardLayout, BorderLayout.CENTER);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//mainFrame.setPreferredSize(new Dimension(500, 400));
		//mainFrame.setResizable(false);
		//mainFrame.add(boardLayout, BorderLayout.CENTER);
		mainFrame.add(player1);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	private void buildBoard() {
		for(int i = 0; i < 10; ++i)
		{
			for(int j = 0; j < 10; ++j)
			{
				JButton b = new JButton();
				grid[i][j] = b;
				boardLayout.add(grid[i][j]);
			}
		}
	}
	
	
}
