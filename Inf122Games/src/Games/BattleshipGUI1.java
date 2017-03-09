package Games;

import java.awt.BorderLayout;
import java.awt.Color;
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
			Ship carrier = bsGame.get_ships().get(0);
			currentShip = bsGame.get_ships().get(0);
			//System.out.println(carrier.Get_type());	//test 
			//length = carrier.get_shipLength();	
			

			click = false;
			paintLengthOfShip(currentShip, "c");
			
//			for(int i = 0; i < 10; ++i)
//			{
//				for(int j = 0; j < 10; ++j )
//				{
//					grid[i][j].addMouseListener(new MouseListener()
//					{			
//						@Override
//						public void mouseReleased(MouseEvent e)
//						{
//							// TODO Auto-generated method stub
//							
//						}
//						
//						@Override
//						public void mousePressed(MouseEvent e)
//						{
//							// TODO Auto-generated method stub
//							click = true;
//							for(int i = 0; i < 10; ++i) //y
//							{
//								for(int j = 0; j < 6; ++j) //x
//								{
//									if(grid[i][j] == e.getSource())
//									{
//										grid[i][j].setText("c");	
//										grid[i][j+1].setText("c");
//										grid[i][j+2].setText("c");
//										grid[i][j+3].setText("c");
//										grid[i][j+4].setText("c");
//									}
//								}
//							}
//						}
//						
//						@Override
//						public void mouseExited(MouseEvent e)
//						{
//							for(int i = 0; i < 10; ++i) //y
//							{
//								for(int j = 0; j < 6; ++j) //x
//								{
//									if(grid[i][j] == e.getSource())
//									{
//										grid[i][j].setBackground(null);
//										grid[i][j+1].setBackground(null);
//										grid[i][j+2].setBackground(null);
//										grid[i][j+3].setBackground(null);
//										grid[i][j+4].setBackground(null);
//									}
//								}
//							}
//						}
//						
//						@Override
//						public void mouseEntered(MouseEvent e)
//						{
//							for(int i = 0; i < 10; ++i) //y
//							{
//								for(int j = 0; j < 6; ++j) //x
//								{
//									if(grid[i][j] == e.getSource())
//									{
//										grid[i][j].setBackground(Color.green);
//										grid[i][j+1].setBackground(Color.green);
//										grid[i][j+2].setBackground(Color.green);
//										grid[i][j+3].setBackground(Color.green);
//										grid[i][j+4].setBackground(Color.green);
//									}
//								}
//							}
//						}
//						
//						@Override
//						public void mouseClicked(MouseEvent e)
//						{
//							// TODO Auto-generated method stub
//							
//						}
//					});
//					if(click)
//						break;
//				}
//			}
			
		}
		
		if(e.getSource() == bBattleship)
		{
			Ship battleship =  bsGame.get_ships().get(1);
			System.out.println(battleship.Get_type());	//test 
			length = battleship.get_shipLength();
		}
		
		if(e.getSource() == bSubmarine)
		{
			Ship submarine =  bsGame.get_ships().get(2);
			System.out.println(submarine.Get_type());	//test 
			length = submarine.get_shipLength();
		}
		
		if(e.getSource() == bCruiser)
		{
			Ship cruiser =  bsGame.get_ships().get(3);
			System.out.println(cruiser.Get_type());	//test 
			length = cruiser.get_shipLength();
			
		}	
		
		if(e.getSource() == bDestroyer)
		{
			Ship destroy =  bsGame.get_ships().get(4);
			System.out.println(destroy.Get_type());	//test 
			length = destroy.get_shipLength();
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
		for(int i = 0; i < 10; ++i) //y
		{
			for(int j = 0; j < 10; ++j) //x
			{
				if(grid[i][j] == e.getSource())
					System.out.println("x: " + j + "\ty: " + i);
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		if(e.getSource() == bAircraftCarrier)
		{
			for(int i = 0; i < 10; ++i) //y
			{
				for(int j = 0; j < 10; ++j) //x
				{
					if(grid[i][j] == e.getSource())
					{
						grid[i][j].setBackground(Color.BLUE);
						grid[i][j].setForeground(Color.BLUE);	
						grid[i][j].repaint();
					}
				}
			}
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		if(e.getSource() == bAircraftCarrier)
		{
			for(int i = 0; i < 10; ++i) //y
			{
				for(int j = 0; j < 10; ++j) //x
				{
					grid[i][j].setBackground(null);
					grid[i][j].setForeground(null);	
					grid[i][j].repaint();
				}
			}
		}
	
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	private void paintLengthOfShip(Ship ship, String type)
	{
		int len = ship.get_shipLength();
		String orientation = "vertical"; //get ship orientation NEED TO CHANGE CONSTRUCTOR
		
		//clear ship on board
		for(int i = 0; i < 10; ++i)
		{
			for(int j = 0; j < 10; ++j )
			{
				if(grid[i][j].getText().toString().equalsIgnoreCase(type))
					grid[i][j].setText("");
			}
		}
	
		//place ship on board
		int x = 0, y=0;;
		
		if(orientation.equalsIgnoreCase("vertical"))
			x = len;
		else
			y = len;
		
		final int subtractX = x-1;
		final int subtractY = y;
		
		for(int i = 0; i < 10; ++i)
		{
			for(int j = 0; j < 10; ++j )
			{
				grid[i][j].addMouseListener(new MouseListener()
				{			
					@Override
					public void mouseReleased(MouseEvent e)
					{
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mousePressed(MouseEvent e)
					{
						// TODO Auto-generated method stub
						click = true;
						for(int i = 0; i < 10; ++i) //y
						{
							for(int j = 0; j < 10-subtractX; ++j) //x
							{
								if(grid[i][j] == e.getSource())
								{
									grid[i][j].setText("c");	
									grid[i][j+1].setText("c");
									grid[i][j+2].setText("c");
									grid[i][j+3].setText("c");
									grid[i][j+4].setText("c");
								}
							}
						}
					}
					
					@Override
					public void mouseExited(MouseEvent e)
					{
						for(int i = 0; i < 10-subtractY; ++i) //y
						{
							for(int j = 0; j < 10-subtractX; ++j) //x
							{
								if(grid[i][j] == e.getSource())
								{
									grid[i][j].setBackground(null);
									grid[i][j+1].setBackground(null);
									grid[i][j+2].setBackground(null);
									grid[i][j+3].setBackground(null);
									grid[i][j+4].setBackground(null);
								}
							}
						}
					}
					
					@Override
					public void mouseEntered(MouseEvent e)
					{
						for(int i = 0; i < 10-subtractY; ++i) //y
						{
							for(int j = 0; j < 10-subtractX; ++j) //x
							{
								if(grid[i][j] == e.getSource())
								{
									grid[i][j].setBackground(Color.green);
									grid[i][j+1].setBackground(Color.green);
									grid[i][j+2].setBackground(Color.green);
									grid[i][j+3].setBackground(Color.green);
									grid[i][j+4].setBackground(Color.green);
								}
							}
						}
					}
					
					@Override
					public void mouseClicked(MouseEvent e)
					{
						// TODO Auto-generated method stub
						click = true;
					}
				});
				if(click)
					break;
			}
		}
	}
}


