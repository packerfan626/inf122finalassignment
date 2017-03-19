package Battleship;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class BattleshipGUI1 extends JFrame implements ActionListener//, MouseListener
{
	private final String VERTICAL = "Vertical", HORIZONTAL = "Horizontal";
	private JPanel contentPane, opponentView, yourView;
	private static JButton[][] grid = new JButton[10][10];
	private static JButton[][] oppGrid = new JButton[10][10]; 
	static Battleship bsGame;
	private JTextArea txtYourBoard;
	private JTextArea txtOpponentsBoard;
	private static JButton bAircraftCarrier;
	private static JButton bBattleship;
	private static JButton bSubmarine;
	private static JButton bCruiser;
	private static JButton bDestroyer;
	private static JButton bQuit;
	private static JButton bDeploy;
	private static JButton bDirection;
	private static Ship currentShip;
	private static int xCord;
	private static int yCord;
	private static int destroyEnemyShipCounter = 0;
	private String direction = VERTICAL;
	private static boolean AC_Deployed, B_Deployed, S_Deployed, C_Deployed, D_Deployed;
	private static JTextArea tvShipDir;
	private static boolean waiting = true;
	
	public BattleshipGUI1(Battleship bs)
	{
		super("Battleships");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 826, 432);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		bsGame = bs;
		
		yourView = new JPanel();
		yourView.setBackground(UIManager.getColor("Button.background"));
		yourView.setBounds(338, 11, 288, 344);		
		contentPane.add(yourView);
		yourView.setLayout(new GridLayout(10, 10));
		
		opponentView = new JPanel();
		opponentView.setBackground(UIManager.getColor("Button.background"));
		opponentView.setBounds(21, 11, 288, 344);
		contentPane.add(opponentView);
		opponentView.setLayout(new GridLayout(10, 10));
		
		bQuit = new JButton("Quit");
		bQuit.setForeground(Color.RED);
		bQuit.setBackground(Color.DARK_GRAY);
		bQuit.setBounds(666, 11, 134, 23);
		contentPane.add(bQuit);
		bQuit.addActionListener(this);
		
		bDeploy = new JButton("Deploy");
		bDeploy.setForeground(Color.BLACK);
//		bDeploy.setBackground(new Color(51, 153, 0));
		bDeploy.setBounds(666, 342, 134, 29);
		contentPane.add(bDeploy);
		bDeploy.setEnabled(false);
		bDeploy.addActionListener(this);
	
		
		//ships buttons START
		JPanel panel_ships = new JPanel();
		panel_ships.setBounds(666, 99, 134, 232);
		contentPane.add(panel_ships);
		panel_ships.setLayout(new GridLayout(5, 0, 0, 0));		
		bAircraftCarrier = new JButton("Aircraft Carrier");		
		bAircraftCarrier.setBackground(Color.DARK_GRAY);
		bBattleship = new JButton("Battleship");
		bBattleship.setBackground(Color.DARK_GRAY);
		bSubmarine = new JButton("Submarine");
		bSubmarine.setBackground(Color.DARK_GRAY);
		bCruiser = new JButton("Cruiser");
		bCruiser.setBackground(Color.DARK_GRAY);
		bDestroyer = new JButton("Destroyer");
		bDestroyer.setBackground(Color.DARK_GRAY);
		panel_ships.add(bAircraftCarrier);
		panel_ships.add(bBattleship);		
		panel_ships.add(bSubmarine);
		panel_ships.add(bCruiser);
		panel_ships.add(bDestroyer);
		bAircraftCarrier.setForeground(Color.WHITE);
		bBattleship.setForeground(Color.WHITE);
		bSubmarine.setForeground(Color.WHITE);
		bCruiser.setForeground(Color.WHITE);
		bDestroyer.setForeground(Color.WHITE);
		bAircraftCarrier.addActionListener(this);
		bBattleship.addActionListener(this);
		bSubmarine.addActionListener(this);
		bCruiser.addActionListener(this);
		bDestroyer.addActionListener(this);
		//ships button END		
		
		txtYourBoard = new JTextArea();
		txtYourBoard.setEditable(false);
		txtYourBoard.setText("Your board");
		txtYourBoard.setBounds(444, 366, 86, 20);
		contentPane.add(txtYourBoard);
		txtYourBoard.setColumns(10);
		txtYourBoard.setOpaque(false);
		
		txtOpponentsBoard = new JTextArea();
		txtOpponentsBoard.setEditable(false);
		txtOpponentsBoard.setText("Opponents Board");
		txtOpponentsBoard.setColumns(10);
		txtOpponentsBoard.setBounds(108, 366, 102, 20);
		txtOpponentsBoard.setOpaque(false);
		contentPane.add(txtOpponentsBoard);
		
		bDirection = new JButton(VERTICAL);
		bDirection.setForeground(Color.ORANGE);
		bDirection.setBackground(Color.DARK_GRAY);
		bDirection.setBounds(666, 59, 134, 29);
		contentPane.add(bDirection);
		bDirection.addActionListener(this);
		
		tvShipDir = new JTextArea();
		tvShipDir.setText("Ship Direction:");
		tvShipDir.setBounds(666, 39, 86, 20);
		tvShipDir.setOpaque(false);
		contentPane.add(tvShipDir);
		tvShipDir.setColumns(10);
		
		buildBoard();
	}
	
	private void buildBoard() 
	{
		for(int i = 0; i < 10; ++i)
		{
			for(int j = 0; j < 10; ++j)
			{			
				//Your Grid layout
				grid[i][j] = new JButton();
//				grid[i][j].addMouseListener(this);
				grid[i][j].addActionListener(new boardAction());
				yourView.add(grid[i][j]);
				
				//opponent grid layout
				oppGrid[i][j] = new JButton();
				oppGrid[i][j].setEnabled(false);
				opponentView.add(oppGrid[i][j]);				
			}
		}
	}
	
	private class boardAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			if(currentShip!=null)
			{
				for(int i = 0; i < 10; i++) //y
				{
					for(int j = 0; j < 10; j++) //x
					{
						if(grid[i][j] == e.getSource())
						{
							xCord = j;
							yCord = i;
							
							boolean isOutOfBound = false;
							//check if ship is within grid
							if(direction.equalsIgnoreCase(VERTICAL))
								if(yCord+currentShip.get_shipLength()>10) { 
									isOutOfBound = true; 
									}
							if(direction.equalsIgnoreCase(HORIZONTAL))
								if(xCord+currentShip.get_shipLength()>10) 
							{ 
								isOutOfBound = true; 						
							}
						
	
							if(isOutOfBound)
							{
								JOptionPane.showMessageDialog(BattleshipGUI1.this, currentShip.get_type() + 
										" can't be placed here, ship-overboard.", "Out-of-bound Error!", JOptionPane.ERROR_MESSAGE	);
							}
							else //need to debug more
							{
								if(checkOther(currentShip))
								{
									JOptionPane.showMessageDialog(BattleshipGUI1.this, currentShip.get_type() + 
											" can't be placed here, another ship is here", "Error!, Ships-collide", JOptionPane.ERROR_MESSAGE	);
								//	System.out.println("SHIP IN THE WAY");
								}
								else
								{
									//clear Ship coordinates
									//METHOD TO CLEAR SHIP; needs to be implemented
									clearBoard(currentShip);
									
									//place ship coordinates
									setShipCord(currentShip, xCord, yCord, direction);
									
									//set board with ship coordinates
									setBoard(currentShip);
								}
							}
						}
					}
				}
			}	
		}					
	}	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		int length = 0;
		// TODO Auto-generated method stub
		
		//change direction of ship
		if(e.getSource() == bDirection)
		{
			if(direction.equalsIgnoreCase(VERTICAL))
			{
				direction = HORIZONTAL;
				bDirection.setText(HORIZONTAL);
			}
			else
			{
				direction = VERTICAL;
				bDirection.setText(VERTICAL);
			}			
		}
			
		//ship placement buttons
		if(e.getSource() == bAircraftCarrier)		
			currentShip = bsGame.get_ships().get(0);		
		if(e.getSource() == bBattleship)
			currentShip = bsGame.get_ships().get(1);		
		if(e.getSource() == bSubmarine)
			currentShip = bsGame.get_ships().get(2);		
		if(e.getSource() == bCruiser)
			currentShip = bsGame.get_ships().get(3);		
		if(e.getSource() == bDestroyer)
			currentShip = bsGame.get_ships().get(4);
		
		if(e.getSource() == bQuit)
			dispose();		
		if(e.getSource() == bDeploy)
		{
			//disable all ships button
			disableShipPlacementButtons();
			bsGame.sendDeployStatus(false);
//			
//			JFrame wait = new JFrame("Waiting to start game");
//			JPanel waitPanel = new JPanel();
//			
//			final JOptionPane optionPane = new JOptionPane("Waiting for other player to deploy ships", 
//					JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
//			final JDialog dialog = new JDialog();
//			dialog.setTitle("Waiting to start game");
//			dialog.setModal(true);
//			dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			
			JFrame waitFrame = new JFrame("Waiting to start game");
	        waitFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
	        waitFrame.setBounds(200, 200, 300, 300);
	
	        JPanel waitPanel = new JPanel();
	        waitPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	        waitFrame.setContentPane(waitPanel);
	        waitFrame.setVisible(false);
	        waitPanel.setLayout(null);
	        JButton taWait = new JButton("Waiting for player to deploy ships");
	        taWait.setBounds(50, 10, 141, 35);
            waitPanel.add(taWait);
			if(waiting)
			{
				waitFrame.setVisible(true);
//				dialog.setContentPane(optionPane);
//				dialog.pack();
//				dialog.setVisible(true);
//				wait.setVisible(true);
//				wait.setBounds(100, 100, 449, 176);
//				waitPanel.setBackground(Color.WHITE);
//				waitPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//				wait.setContentPane(waitPanel);
//				waitPanel.setLayout(null);
//				
//				JTextArea content = new JTextArea();
//				content.setBounds(77, 50, 283, 30);
//				content.setFont(new Font("Book Antiqua", Font.PLAIN, 17));
//			    content.setEditable(false);		    
//				content.setText("Waiting for player to deploy ships");
//				waitPanel.add(content);      	 
//				wait.repaint();
			}
			
			while(waiting != false)
			{
				try
				{
					Thread.sleep(200);
				}
				catch (InterruptedException IE)
				{ 
					
				}
			}
			
			if(!waiting)
			{
				waitFrame.dispose();
//				dialog.setModal(false);
//				dialog.setVisible(false);
//				dialog.dispose();
//				wait.dispose();
				if(bsGame.get_turn())
				{
					JOptionPane.showMessageDialog(null, 
							"Your move first", "Battle Started", JOptionPane.INFORMATION_MESSAGE	);
				}
				else
				{
					JOptionPane.showMessageDialog(null, 
							"Waiting for player to make move", "Battle Started", JOptionPane.INFORMATION_MESSAGE	);
				}
			}		
		}
		//System.out.println(length);	//test 
	}

	private boolean checkOther(Ship ship)
	{
		ArrayList<Ship> tempList = bsGame.get_ships();
		//tempList.remove(ship);
		
		
		//if direction
		//boolean isVertical = true;
	
		//need ships orientation
		for(Ship s: tempList)
		{
			if(s!=ship)
			{
				if(direction.equalsIgnoreCase(VERTICAL))
				{
					int xAdd = ship.get_shipLength()+yCord;
					for(int y = yCord; y < xAdd; y++)
					{
						if(s.checkShot(y, xCord))
						{
							return true;
						}
					}
				}
				else
				{
					int xAdd = ship.get_shipLength()+xCord;
					for(int x = xCord; x < xAdd ; x++)
					{
						if(s.checkShot(yCord, x))
						{
							return true;
						}
					}				
				}
			}
		}
		return false;
	}
	private void clearBoard(Ship ship)
	{
		for(int i = 0; i < 10;++i)
		{
			for(int j =0 ; j < 10; ++j)
			{
				if(ship.checkShot(i, j))
				{
					grid[i][j].setBackground(null);
				}
			}
		}
		ship.clearCord();
		
	}
	private void setShipCord(Ship ship, int x, int y, String dir)
	{
		ship.set_xCoord(x);
		ship.set_yCoord(y);
		ship.set_direction(dir);
		ship.setShipCoordinates();
	}
	
	private void setBoard(Ship ship)
	{
		for(int i = 0; i < 10;++i)	//y
		{
			for(int j =0 ; j < 10; ++j)	//x
			{
				if(ship.checkShot(i, j))
				{
					grid[i][j].setBackground(Color.GREEN);
				}
			}
		}
		checkIfAllShipsDeployed();		
	}
	
	private void checkIfAllShipsDeployed() 
	{
		if(currentShip.get_type().equalsIgnoreCase("Aircraft Carrier"))
		{
			bAircraftCarrier.setBackground(Color.GREEN);
			bAircraftCarrier.setForeground(Color.BLACK);
			AC_Deployed=true;		
		}
		
		if(currentShip.get_type().equalsIgnoreCase("Battleship"))
		{
			bBattleship.setBackground(Color.GREEN);
			bBattleship.setForeground(Color.BLACK);
			B_Deployed=true;
		}
		if(currentShip.get_type().equalsIgnoreCase("Submarine"))
		{
			bSubmarine.setBackground(Color.GREEN);
			bSubmarine.setForeground(Color.BLACK);
			S_Deployed=true;
		}
		if(currentShip.get_type().equalsIgnoreCase("Cruiser"))
		{
			bCruiser.setBackground(Color.GREEN);
			bCruiser.setForeground(Color.BLACK);
			C_Deployed=true;
		}
		if(currentShip.get_type().equalsIgnoreCase("Destroyer"))
		{
			bDestroyer.setBackground(Color.GREEN);
			bDestroyer.setForeground(Color.BLACK);
			D_Deployed=true;
		}
		
		if(!AC_Deployed || !B_Deployed || !S_Deployed || !C_Deployed || !D_Deployed)
			bDeploy.setEnabled(false);
		else
		{
			bDeploy.setBackground(new Color(51, 153, 0));
			bDeploy.setEnabled(true);
			bAircraftCarrier.setForeground(Color.BLACK);
			bBattleship.setForeground(Color.BLACK);
			bSubmarine.setForeground(Color.BLACK);
			bCruiser.setForeground(Color.BLACK);
			bDestroyer.setForeground(Color.BLACK);
		}
	}
	
	private void disableShipPlacementButtons()
	{
		bAircraftCarrier.setEnabled(false);
		bBattleship.setEnabled(false);
		bCruiser.setEnabled(false);
		bSubmarine.setEnabled(false);
		bDestroyer.setEnabled(false);
		bDirection.setEnabled(false);
		bDeploy.setVisible(false);
		bDirection.setVisible(false);
		tvShipDir.setVisible(false);
		
		bAircraftCarrier.setEnabled(false);
		bBattleship.setEnabled(false);
		bCruiser.setEnabled(false);
		bSubmarine.setEnabled(false);
		bDestroyer.setEnabled(false);
		bDirection.setEnabled(false);
		
		for(int i = 0; i < 10; ++i)
		{
			for(int j = 0; j < 10; ++j)
			{
				grid[i][j].setEnabled(false);
				oppGrid[i][j].setEnabled(true);
				oppGrid[i][j].addActionListener(new myActionListener());
			}
		}
		
	}
	
	private class myActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			
			for(int i = 0; i < 10; i++) //y
			{
				for(int j = 0; j < 10; j++) //x
				{
					if(oppGrid[i][j] == e.getSource())
					{
						if(bsGame.get_turn())
						{
							xCord = j;
							yCord = i;
							
							bsGame.sendMove(xCord,yCord);
							oppGrid[i][j].setEnabled(false);
							bsGame.changeTurn(false);;
						}
						else
						{
							JOptionPane.showMessageDialog(null, 
									"Not your turn.", "Error! Not your turn", JOptionPane.ERROR_MESSAGE	);
						}
					}
				}
			}
			
		}				
	}
	
	private static boolean checkWin()
	{
		return destroyEnemyShipCounter == 5;
	}
	
	private static boolean checkLost()
	{
		int count = 0;
		for(Ship s : bsGame.get_ships())
		{
			if(s.checkDestroy())
				count++;
		};
		return count == 5;
	}
	
	private static void reset() 
	{	
		bsGame.reset();	
		for(int i = 0; i < 10; ++i)
		{
			for(int j = 0; j < 10; ++j)
			{
				grid[i][j].setBackground(null);
				grid[i][j].setEnabled(true);
				
				oppGrid[i][j].setBackground(null);
				oppGrid[i][j].setEnabled(false);
			}
		}
		bAircraftCarrier.setBackground(null);
		bBattleship.setBackground(null);
		bSubmarine.setBackground(null);
		bDestroyer.setBackground(null);
		bCruiser.setBackground(null);
		currentShip = null;
		destroyEnemyShipCounter = 0;
		AC_Deployed = false;
		B_Deployed = false;
		S_Deployed = false;
		C_Deployed = false;
		D_Deployed = false;	
		
		bAircraftCarrier.setEnabled(true);
		bBattleship.setEnabled(true);
		bCruiser.setEnabled(true);
		bSubmarine.setEnabled(true);
		bDestroyer.setEnabled(true);
		bDirection.setEnabled(true);
		bDeploy.setVisible(true);
		tvShipDir.setVisible(true);
		bDirection.setVisible(true);
	}		
	
	public static void updateOppBoard(boolean hasHit, int x, int y, String ship, boolean destroy)
	{
		if(hasHit)
		{
			oppGrid[x][y].setBackground(Color.ORANGE);
		}
		else
		{
			oppGrid[x][y].setBackground(Color.BLUE);
		}
		
		if(destroy)
		{
			destroyEnemyShipCounter++;
			JOptionPane.showMessageDialog(null, ship + " has been sunk");

			if(ship.equalsIgnoreCase("aircraft carrier"))
				bAircraftCarrier.setBackground(Color.RED);
			if(ship.equalsIgnoreCase("battleship"))
				bBattleship.setBackground(Color.RED);
			if(ship.equalsIgnoreCase("cruiser"))
				bCruiser.setBackground(Color.RED);
			if(ship.equalsIgnoreCase("submarine"))
				bSubmarine.setBackground(Color.RED);
			if(ship.equalsIgnoreCase("destroyer"))
				bDestroyer.setBackground(Color.RED);
			
			if(checkWin())
			{
				int value = JOptionPane.showConfirmDialog(null, "All enemy ship sunk, you Win", "Play Again?", JOptionPane.YES_NO_OPTION);
				if(value == 0)
					reset();						
			}
		}
			
		
	}
	
	public static void updateYourBoard(boolean hasHit, int x, int y)
	{
		if(hasHit)
		{
			grid[x][y].setBackground(Color.ORANGE);
		}
		else
		{
			grid[x][y].setBackground(Color.BLUE);
		}
		
		if(checkLost())
		{
			int value = JOptionPane.showConfirmDialog(null, "Enemy sunk all your ship, you lose", "Play Again?", JOptionPane.YES_NO_OPTION);
			if(value == 0)
				reset();
		}
		bsGame.changeTurn(true);
	}

	public static void updateOppDeployed(boolean deployed)
	{
		// TODO Auto-generated method stub
		waiting = deployed;		
	}

}






