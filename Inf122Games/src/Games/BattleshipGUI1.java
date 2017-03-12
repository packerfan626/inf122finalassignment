package Games;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class BattleshipGUI1 extends JFrame implements ActionListener, MouseListener
{
	private JPanel contentPane, opponentView, yourView;
	private final JButton[][] grid = new JButton[10][10];
	Battleship bsGame = new Battleship();
	private JTextField txtYourBoard;
	private JTextField txtOpponentsBoard;
	private JButton bAircraftCarrier, 		
		bBattleship,
		bSubmarine,
		bCruiser, 
		bDestroyer,
		bQuit,
		bDeploy;
	private Ship currentShip;
	private boolean click;
	private int xCord, yCord;
	
	public BattleshipGUI1()
	{
		super("Battleships");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 826, 432);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		bDeploy.setBounds(666, 305, 134, 23);
		contentPane.add(bDeploy);
		bDeploy.addActionListener(this);
	
		
		//ships buttons START
		JPanel panel_ships = new JPanel();
		panel_ships.setBounds(666, 62, 134, 232);
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
		
		buildBoard();
	}
	
	private void buildBoard() {
		for(int i = 0; i < 10; ++i)
		{
			for(int j = 0; j < 10; ++j)
			{
				JButton b = new JButton();
				grid[i][j] = new JButton();
				grid[i][j].addMouseListener(this);
				opponentView.add(b);
				yourView.add(grid[i][j]);
			}
		}
	}

	private void placeShip(Ship ship, int x, int y)
	{

		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		int length = 0;
		// TODO Auto-generated method stub
		if(e.getSource() == bAircraftCarrier)
		{
//			Ship carrier = bsGame.get_ships().get(0);
			currentShip = bsGame.get_ships().get(0);
			//System.out.println(carrier.Get_type());	//test 
//			final int len = carrier.get_shipLength();	
			
//			bAircraftCarrier.addMouseListener(this);

		}
		
		if(e.getSource() == bBattleship)
		{
//			Ship battleship =  bsGame.get_ships().get(1);
//			System.out.println(battleship.Get_type());	//test 
			currentShip = bsGame.get_ships().get(1);
//			bBattleship.addMouseListener(this);
		}
		
		if(e.getSource() == bSubmarine)
		{
//			Ship submarine =  bsGame.get_ships().get(2);
//			System.out.println(submarine.Get_type());	//test 
//			length = submarine.get_shipLength();
			
			currentShip = bsGame.get_ships().get(2);
//			bSubmarine.addMouseListener(this);
//			paintLengthOfShip(currentShip, "s", "vertical");	
		}
		
		if(e.getSource() == bCruiser)
		{
//			Ship cruiser =  bsGame.get_ships().get(3);
//			System.out.println(cruiser.Get_type());	//test 
//			length = cruiser.get_shipLength();
			
			currentShip = bsGame.get_ships().get(3);
//			bCruiser.addMouseListener(this);
//			paintLengthOfShip(currentShip, "cs", "vertical");	
			
		}	
		
		if(e.getSource() == bDestroyer)
		{
//			Ship destroy =  bsGame.get_ships().get(4);
//			System.out.println(destroy.Get_type());	//test 
//			length = destroy.get_shipLength();
			
			currentShip = bsGame.get_ships().get(4);
//			bDestroyer.addMouseListener(this);
//			paintLengthOfShip(currentShip, "d", "vertical");	
		}	
		
		if(e.getSource() == bQuit)
		{
			dispose();
		}
		
		if(e.getSource() == bDeploy)
		{
			//disable all ships button
			//show status of your ships' health
		}
		//System.out.println(length);	//test 
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e)
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
						//if(vertical)
							if(yCord+currentShip.get_shipLength()>10) { isOutOfBound = true; }
						//else
							if(xCord+currentShip.get_shipLength()>10) { isOutOfBound = true; }
					

						if(isOutOfBound)
						{
							JOptionPane.showMessageDialog(this, currentShip.Get_type() + 
									" can't be placed here, ship-overboard.", "Out-of-bound Error!", JOptionPane.ERROR_MESSAGE	);
						}
						else //need to debug more
						{
							if(checkOther(currentShip))
							{
								JOptionPane.showMessageDialog(this, currentShip.Get_type() + 
										" can't be placed here, another ship is here", "Error!, Ships-collide", JOptionPane.ERROR_MESSAGE	);
							//	System.out.println("SHIP IN THE WAY");
							}
							else
							{
								//clear Ship coordinates
								//METHOD TO CLEAR SHIP; needs to be implemented
								clearBoard(currentShip);
								
								//place ship coordinates
								setShipCord(currentShip, xCord, yCord, "vertical");
								
								//set board with ship coordinates
								setBoard(currentShip);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	private boolean checkOther(Ship ship)
	{
		ArrayList<Ship> tempList = bsGame.get_ships();
		//tempList.remove(ship);
		int xAdd = ship.get_shipLength()+yCord;
		
		//if direction
		boolean isVertical = true;
	
		//need ships orientation
		for(Ship s: tempList)
		{
			if(s!=ship)
			{
				if(isVertical)
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
		//NEED A METHOD TO TRACK IF SHIP IS SET IN SHIP CLASS
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
	}

//	private void paintLengthOfShip(Ship ship, String type, String o)
//	{
//		System.out.println("PLACE SHIP");
//		int len = ship.get_shipLength();
//		Ship c = ship;
//		String orientation = o; //get ship orientation NEED TO CHANGE CONSTRUCTOR
//			
//		//place ship on board
//		int x = 0, y=0;
//		click = false;
//		for(int i = 0; i < 10; ++i)
//		{
//			for(int j = 0; j < 10; ++j )
//			{		
//				
//				
//				grid[i][j].addActionListener(new ActionListener() {					
//					@Override
//					public void actionPerformed(ActionEvent e)
//					{
//						click = false;
//						// TODO Auto-generated method stub
//						for(int i = 0; i < 10;++i)
//						{
//							for(int j = 0; j < 10; ++j)
//							{
//								if(grid[i][j] == e.getSource())
//								{		
//									//clear ships
//									//NEED A METHOD THAT GETS THE COORDINATES OF THE SHIPS
//									if(currentShip.get_xCoord() != -1 ||currentShip.get_yCoord() != -1)	//SET xCoord and yCoord to -1
//									{
//										
//									}	
//									click=true;
//								}
//								if(click)
//									break;
//							}
//							if(click)
//								break;
//						}
//						
//						//vertical
//						for(int i = 0; i < 10-len+1; ++i) //y
//						{
//							for(int j = 0; j < 10; ++j) //x
//							{
//								if(grid[i][j] == e.getSource())
//								{
//									int t = 0;
//									while(t!=len)
//									{
//										grid[i+t][j].setText(type);
//										grid[i+t][j].setBackground(Color.green);
//										t++;
//									}									
//								}
//							}
//						}								
//					}
//				});
////				grid[i][j].addMouseListener(new MouseListener()
////				{			
////					@Override
////					public void mouseReleased(MouseEvent e)
////					{
////						// TODO Auto-generated method stub
////						
////					}
////					
////					@Override
////					public void mousePressed(MouseEvent e)
////					{
//						
//						// TODO Auto-generated method stub
////						for(int i = 0; i < 10; ++i) //y
////						{
////							for(int j = 0; j < 10; ++j) //x
////							{
////								if(grid[i][j] == e.getSource())
////								{
////									xCord = j;
////									yCord = i;
////								}
////								//clear ships
////								//NEED A METHOD THAT GETS THE COORDINATES OF THE SHIPS
////								if(currentShip.get_xCoord() != -1 ||currentShip.get_yCoord() != -1)	//SET xCoord and yCoord to -1
////								{
////									
////								}
////								if(grid[i][j].getText().toString().equalsIgnoreCase(type))
////								{
////									grid[i][j].setText("");
////									grid[i][j].setBackground(null);
////								}
////								
////								//check if other ships
////								
////								//place ships
////								//bsGame.placeShip(currentShip, yCord, xCord, orientation);
////								
////								//color grid
////								
////							}
////						}
//						
//						//Horizontal
////						for(int i = 0; i < 10; ++i) //y
////						{
////							for(int j = 0; j < 10-len+1; ++j) //x
////							{
////								if(grid[i][j] == e.getSource())
////								{
////									grid[i][j].setText(type);
////									grid[i][j+1].setText(type);
////									grid[i][j+2].setText(type);
////									grid[i][j+3].setText(type);
////									grid[i][j+4].setText(type);
////									grid[i][j].setBackground(Color.green);
////									grid[i][j+1].setBackground(Color.green);
////									grid[i][j+2].setBackground(Color.green);
////									grid[i][j+3].setBackground(Color.green);
////									grid[i][j+4].setBackground(Color.green);
////								}
////							}
////						}
//
////						//vertical
////						for(int i = 0; i < 10-len+1; ++i) //y
////						{
////							for(int j = 0; j < 10; ++j) //x
////							{
////								if(grid[i][j] == e.getSource())
////								{
////									grid[i][j].setText(type);
////									grid[i+1][j].setText(type);
////									grid[i+2][j].setText(type);
////									grid[i+3][j].setText(type);
////									grid[i+4][j].setText(type);
////									grid[i][j].setBackground(Color.green);
////									grid[i+1][j].setBackground(Color.green);
////									grid[i+2][j].setBackground(Color.green);
////									grid[i+3][j].setBackground(Color.green);
////									grid[i+4][j].setBackground(Color.green);
////								}
////							}
////						}
////					}
//					
////					@Override
////					public void mouseExited(MouseEvent e)
////					{
////						for(int i = 0; i < 10-subtractY; ++i) //y
////						{
////							for(int j = 0; j < 10-subtractX; ++j) //x
////							{
////								if(grid[i][j] == e.getSource())
////								{
////									grid[i][j].setBackground(null);
////									grid[i][j+1].setBackground(null);
////									grid[i][j+2].setBackground(null);
////									grid[i][j+3].setBackground(null);
////									grid[i][j+4].setBackground(null);
////								}
////							}
////						}
//					}
//					
////					@Override
////					public void mouseEntered(MouseEvent e)
////					{
////						for(int i = 0; i < 10-subtractY; ++i) //y
////						{
////							for(int j = 0; j < 10-subtractX; ++j) //x
////							{
////								if(grid[i][j] == e.getSource())
////								{
////									grid[i][j].setBackground(Color.green);
////									grid[i][j+1].setBackground(Color.green);
////									grid[i][j+2].setBackground(Color.green);
////									grid[i][j+3].setBackground(Color.green);
////									grid[i][j+4].setBackground(Color.green);
////								}
////							}
////						}
////					}
////					
////					@Override
////					public void mouseClicked(MouseEvent e)
////					{
////						// TODO Auto-generated method stub
////						click = true;
////					}
////				});
////	
////			}
//			if(click)
//				break;
//		}
//		
//	}
}


