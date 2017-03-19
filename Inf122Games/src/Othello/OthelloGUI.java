package Othello;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Client.Client;
import Games.Game;

public class OthelloGUI extends Game{
	
    private javax.swing.Timer timer;
    
    private Othello game;
    Client _client;		//current client
    
    //This enables the mouse click for the screen. Deactivates/activates depending on the opponents turn
    boolean enabled = false;
	
	public OthelloGUI(Client client){
		super();
		_client = client;
		game = new Othello();
		
		//if host; then activate the mouse click
		if(_client.isHost)
			enabled = true;
		
		JFrame frame = new JFrame("Othello Game -- " + _client.username);
		frame.setContentPane(this);
		frame.setSize(530,528);
		frame.setLocation(700,100);
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setVisible(true);
		
		game.initializeGame(_client);

        setBackground(Color.GREEN);
        
        timer = new javax.swing.Timer(0, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Insert player move here, tba
                repaint();
            }
        });
        
        addMouseListener( new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
            	//Depending on whose turn it is allow for moves
            	if(enabled){
                // Find out which square was clicked
	                int x = evt.getX();
	                int y = evt.getY();
	                int screenWidth = getWidth();
	                int screenHeight = getHeight();
	                int column = (x*game.COLUMNS)/screenWidth;
	                int row = (y*game.ROWS)/screenHeight;
	
	                if(!game.isValid(row, column, game.currentPlayer)){
	                	System.out.println("Invalid Move!");
	                }else{
	                	game.makeMove(row, column, game.currentPlayer);
	            		sendMove(row, column);
	                	game.board[row][column] = game.currentPlayer.getPlayerColor();
	                	repaint();
	                	game.switchPlayer();
	                }
	            }
            	else{
            		System.out.println("Disabled");
            	}
            }
        });
	}
    
	 public void paintComponent(Graphics g) {
	      
	       super.paintComponent(g);
	       
	       int width = getWidth();
	       int height = getHeight();
	       
	       
	       int widthOffset = width/game.COLUMNS;
	       int heightOffset = height/game.ROWS;   
	       
	    // Draw the lines on the board
	       g.setColor(Color.BLACK);
	       for (int i=1; i<= game.COLUMNS; i++) {        
	           g.drawLine(i*widthOffset, 0, i*widthOffset, height);
	           g.drawLine(0, i*heightOffset, width, i*heightOffset);
	       }
	       
	       for (int i=0; i < game.ROWS; i++) {        
	           for (int j=0; j < game.COLUMNS; j++) {
	        	   
	               if (game.board[i][j] == Piece.BLACK) { 
	                   g.setColor(Color.BLACK);
	                   g.fillOval((j*widthOffset)+5,((i)*heightOffset)+5,50,50); 
	               }
	               else if (game.board[i][j] == Piece.WHITE) {  
	                   g.setColor(Color.WHITE);
	                   g.fillOval(j*widthOffset+5,(i*heightOffset)+5,50,50);

	               }
	           }
	       }
	 }

	@Override
	public void sendMove(int x, int y) {
		// TODO Auto-generated method stub
		_client.sendMessage("MOVE_" + x + "_"+ y);
		
		//change enabled to false to deactivate screen when move it sent and wait for other turn
		enabled = false;
	}

	@Override
	public void receiveMove(int x, int y) {
		// TODO Auto-generated method stub
		
		//change enabled to true to activate the screen when a move is received and deactivates other user screen
		enabled = true;
		game.makeMove(x, y, game.oppositePlayer);
		game.board[x][y] = game.oppositePlayer.getPlayerColor();
		repaint();
		game.switchPlayer();
	}

}