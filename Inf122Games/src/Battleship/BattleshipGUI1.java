package Battleship;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class BattleshipGUI1 extends JFrame implements ActionListener//, MouseListener
{
	private final String VERTICAL = "Vertical", HORIZONTAL = "Horizontal";
	private JPanel contentPane, opponentView, yourView;
	private static JButton[][] grid = new JButton[10][10];
	private static JButton[][] oppGrid = new JButton[10][10]; 
	Battleship bsGame;
	private JTextField txtYourBoard;
	private JTextField txtOpponentsBoard;
	private JButton bAircraftCarrier, 		
		bBattleship,
		bSubmarine,
		bCruiser, 
		bDestroyer,
		bQuit,
		bDeploy,
		bDirection;
	private Ship currentShip;
	private static int xCord;
	private static int yCord;
	private static int destroyEnemyShipCounter = 0;
	private String direction = VERTICAL;
	private boolean AC_Deployed, B_Deployed, S_Deployed, C_Deployed, D_Deployed;
	private JTextField tvShipDir;
	private static boolean wait = false;
	
	public BattleshipGUI1(Battleship bs)
	{
		super("Battleships");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 826, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		bsGame = bs;
		
		yourView = new JPanel();
		yourView.setBounds(338, 11, 288, 344);		
		contentPane.add(yourView);
		yourView.setLayout(new GridLayout(10, 10));
		
		opponentView = new JPanel();
		opponentView.setBounds(21, 11, 288, 344);
		contentPane.add(opponentView);
		opponentView.setLayout(new GridLayout(10, 10));
		
		bQuit = new JButton("Quit");
		bQuit.setBounds(666, 11, 134, 23);
		contentPane.add(bQuit);
		bQuit.addActionListener(this);
		
		bDeploy = new JButton("Deploy");
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
		bBattleship = new JButton("Battleship");
		bSubmarine = new JButton("Submarine");
		bCruiser = new JButton("Cruiser");
		bDestroyer = new JButton("Destroyer");
		panel_ships.add(bAircraftCarrier);
		panel_ships.add(bBattleship);		
		panel_ships.add(bSubmarine);
		panel_ships.add(bCruiser);
		panel_ships.add(bDestroyer);
		bAircraftCarrier.addActionListener(this);
		bBattleship.addActionListener(this);
		bSubmarine.addActionListener(this);
		bCruiser.addActionListener(this);
		bDestroyer.addActionListener(this);
		//ships button END		
		
		txtYourBoard = new JTextField();
		txtYourBoard.setText("Your board");
		txtYourBoard.setBounds(444, 366, 86, 20);
		contentPane.add(txtYourBoard);
		txtYourBoard.setColumns(10);
		
		txtOpponentsBoard = new JTextField();
		txtOpponentsBoard.setText("Opponents Board");
		txtOpponentsBoard.setColumns(10);
		txtOpponentsBoard.setBounds(108, 366, 102, 20);
		contentPane.add(txtOpponentsBoard);
		
		bDirection = new JButton(VERTICAL);
		bDirection.setBounds(666, 59, 134, 29);
		contentPane.add(bDirection);
		bDirection.addActionListener(this);
		
		tvShipDir = new JTextField();
		tvShipDir.setText("Ship Direction:");
		tvShipDir.setBounds(666, 39, 86, 20);
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
			
			//REPLACE WITH OPPONENT SHIPS
//			oppShips = enemeyShip.get_ships();
//			oppShips.get(0).set_xCoord(0);
//			oppShips.get(0).set_yCoord(0);
//			oppShips.get(0).set_direction(VERTICAL);
//			oppShips.get(0).setShipCoordinates();
//			
//			oppShips.get(1).set_xCoord(1);
//			oppShips.get(1).set_yCoord(0);
//			oppShips.get(1).set_direction(VERTICAL);
//			oppShips.get(1).setShipCoordinates();
//			
//			oppShips.get(2).set_xCoord(2);
//			oppShips.get(2).set_yCoord(0);
//			oppShips.get(2).set_direction(VERTICAL);
//			oppShips.get(2).setShipCoordinates();
//			
//			oppShips.get(3).set_xCoord(3);
//			oppShips.get(3).set_yCoord(0);
//			oppShips.get(3).set_direction(VERTICAL);
//			oppShips.get(3).setShipCoordinates();
//			
//			oppShips.get(4).set_xCoord(4);
//			oppShips.get(4).set_yCoord(0);
//			oppShips.get(4).set_direction(VERTICAL);
//			oppShips.get(4).setShipCoordinates();
			
//			set_oppBoard(oppShips);
		}
		//System.out.println(length);	//test 
	}

//	@Override
//	public void mouseClicked(MouseEvent e)
//	{
//		// TODO Auto-generated method stub
//		
//		
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e)
//	{
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e)
//	{
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mousePressed(MouseEvent e)
//	{
//		// TODO Auto-generated method stub
//		if(currentShip!=null)
//		{
//			for(int i = 0; i < 10; i++) //y
//			{
//				for(int j = 0; j < 10; j++) //x
//				{
//					if(grid[i][j] == e.getSource())
//					{
//						xCord = j;
//						yCord = i;
//						
//						boolean isOutOfBound = false;
//						//check if ship is within grid
//						if(direction.equalsIgnoreCase(VERTICAL))
//							if(yCord+currentShip.get_shipLength()>10) { 
//								isOutOfBound = true; 
//								}
//						if(direction.equalsIgnoreCase(HORIZONTAL))
//							if(xCord+currentShip.get_shipLength()>10) 
//						{ 
//							isOutOfBound = true; 						
//						}
//					
//
//						if(isOutOfBound)
//						{
//							JOptionPane.showMessageDialog(this, currentShip.get_type() + 
//									" can't be placed here, ship-overboard.", "Out-of-bound Error!", JOptionPane.ERROR_MESSAGE	);
//						}
//						else //need to debug more
//						{
//							if(checkOther(currentShip))
//							{
//								JOptionPane.showMessageDialog(this, currentShip.get_type() + 
//										" can't be placed here, another ship is here", "Error!, Ships-collide", JOptionPane.ERROR_MESSAGE	);
//							//	System.out.println("SHIP IN THE WAY");
//							}
//							else
//							{
//								//clear Ship coordinates
//								//METHOD TO CLEAR SHIP; needs to be implemented
//								clearBoard(currentShip);
//								
//								//place ship coordinates
//								setShipCord(currentShip, xCord, yCord, direction);
//								
//								//set board with ship coordinates
//								setBoard(currentShip);
//							}
//						}
//					}
//				}
//			}
//		}
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent e)
//	{
//		// TODO Auto-generated method stub
//		
//	}
	
	private boolean checkOther(Ship ship)
	{
		ArrayList<Ship> tempList = bsGame.get_ships();
		//tempList.remove(ship);
		int xAdd = ship.get_shipLength()+yCord;
		
		//if direction
		//boolean isVertical = true;
	
		//need ships orientation
		for(Ship s: tempList)
		{
			if(s!=ship)
			{
				if(direction.equalsIgnoreCase(VERTICAL))
				{
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
		
		//USE THIS WHEN FUNCTIONING
//		ship.setShipCoordinates(x, y, dir);
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
			AC_Deployed=true;		
		}
		
		if(currentShip.get_type().equalsIgnoreCase("Battleship"))
		{
			bBattleship.setBackground(Color.GREEN);
			B_Deployed=true;
		}
		if(currentShip.get_type().equalsIgnoreCase("Submarine"))
		{
			bSubmarine.setBackground(Color.GREEN);
			S_Deployed=true;
		}
		if(currentShip.get_type().equalsIgnoreCase("Cruiser"))
		{
			bCruiser.setBackground(Color.GREEN);
			C_Deployed=true;
		}
		if(currentShip.get_type().equalsIgnoreCase("Destroyer"))
		{
			bDestroyer.setBackground(Color.GREEN);
			D_Deployed=true;
		}
		
		if(!AC_Deployed || !B_Deployed || !S_Deployed || !C_Deployed || !D_Deployed)
			bDeploy.setEnabled(false);
		else
			bDeploy.setEnabled(true);
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
	
//	public void set_oppBoard(ArrayList<Ship> shipList)
//	{
//		oppShips = shipList;
//	}
	
	private boolean checkWin()
	{
		return destroyEnemyShipCounter == 5;
	}
	
	private boolean checkLost()
	{
		int count = 0;
		for(Ship s : bsGame.get_ships())
		{
			if(s.checkDestroy())
				count++;
		};
		return count == 5;
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
						xCord = j;
						yCord = i;
						
						shot(xCord, yCord);
//									if()
//									{
//										oppGrid[i][j].setBackground(Color.ORANGE);							
//									}

						oppGrid[i][j].setEnabled(false);
						if(wait)
						{
							if(checkWin())
							{
								int value = JOptionPane.showConfirmDialog(BattleshipGUI1.this, "All enemy ship sunk, you Win", "Play Again?", JOptionPane.YES_NO_OPTION);
								if(value == 0)
									reset();						
							}
							
							if(checkLost())
							{
								int value = JOptionPane.showConfirmDialog(BattleshipGUI1.this, "Enemy sunk all your ship, you lose", "Play Again?", JOptionPane.YES_NO_OPTION);
								if(value == 0)
									reset();
							}
							wait = false;
						}
					}
				}
			}			
		}
		
		private void shot(int x, int y)
		{
			bsGame.sendMove(x, y);
//			return false;
//			if(enemeyShip.get_hit(x, y))
//			{
//				Ship temp = enemeyShip.set_hit(x, y);
//				bsGame.sendMove(x, y);
//				if(temp != null)
//				{
//					if(temp.checkDestroy())
//					{
//						JOptionPane.showMessageDialog(BattleshipGUI1.this, temp.get_type() + " has been sunk");
//						destroyCounter++;
//						if(temp.get_type().equalsIgnoreCase("aircraft carrier"))
//							bAircraftCarrier.setBackground(Color.RED);
//						if(temp.get_type().equalsIgnoreCase("battleship"))
//							bBattleship.setBackground(Color.RED);
//						if(temp.get_type().equalsIgnoreCase("cruiser"))
//							bCruiser.setBackground(Color.RED);
//						if(temp.get_type().equalsIgnoreCase("submarine"))
//							bSubmarine.setBackground(Color.RED);
//						if(temp.get_type().equalsIgnoreCase("destroyer"))
//							bDestroyer.setBackground(Color.RED);
//					}
//					return true;
//				}
//				else
//				{
//					oppGrid[yCord][xCord].setBackground(Color.BLUE);	
//				}
//			}			
//			return false;
		}
		
		private void reset() 
		{	
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
			bsGame = new Battleship();		
//			enemeyShip = new Battleship();
			currentShip = null;
			destroyEnemyShipCounter = 0;
			AC_Deployed = false;
			B_Deployed = false;
			S_Deployed = false;
			C_Deployed = false;
			D_Deployed = false;
//			oppShips = new ArrayList<>();	
			
			bAircraftCarrier.setEnabled(true);
			bBattleship.setEnabled(true);
			bCruiser.setEnabled(true);
			bSubmarine.setEnabled(true);
			bDestroyer.setEnabled(true);
			bDirection.setEnabled(true);
			bDeploy.setVisible(true);
			bDirection.setVisible(true);
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
			destroyEnemyShipCounter++;
			
		wait = true;
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
	}
}





