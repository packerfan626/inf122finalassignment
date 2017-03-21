package Client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Games.Game;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.Font;
import java.awt.Color;
//import TicTacToe.TicTacToe;
//import TicTacToe.TicTacToeView;

public class ClientGUI extends JFrame implements ActionListener
{
	static Client client;
	static String username;
	static ArrayList<String> availGames = new ArrayList<>();;
	private static int port = 4444;
	private JPanel contentPane = new JPanel();
	private JTextField tfUsername;
	private static JButton bBattleship, bTicTacToe, bOthello, btnTESTjoingame;
	
	private static int numGameCount = 0;
    private static JScrollPane JoinableGamesList;
    private static JPanel joinPanel = new JPanel();
    private static JButton joinGame[] = new JButton[10];
	
	public ClientGUI()
	{

		super("LOBBY");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 479, 361);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        setVisible(true);
        contentPane.setLayout(null);
        JTextArea txtrEnterUsername = new JTextArea();
        txtrEnterUsername.setLineWrap(true);
        txtrEnterUsername.setBackground(Color.WHITE);
        txtrEnterUsername.setFont(new Font("Book Antiqua", Font.PLAIN, 17));
        txtrEnterUsername.setText("Enter username:");
        txtrEnterUsername.setBounds(21, 21, 141, 22);
        txtrEnterUsername.setEditable(false);
        contentPane.add(txtrEnterUsername);
        tfUsername = new JTextField();
        tfUsername.setBounds(173, 21, 117, 22);
        contentPane.add(tfUsername);
        tfUsername.setColumns(10);
        JButton bUsernameOK = new JButton("Ok");
        bUsernameOK.setBounds(314, 18, 97, 28);
        contentPane.add(bUsernameOK);
        JButton bHostGame = new JButton("Create New Game");
        bHostGame.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFrame newGame = new JFrame();
                newGame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                newGame.setBounds(200, 200, 300, 300);

                JPanel gameContents = new JPanel();
                gameContents.setBorder(new EmptyBorder(5, 5, 5, 5));
                newGame.setContentPane(gameContents);
                newGame.setVisible(true);
                gameContents.setLayout(null);
                
                bBattleship = new JButton("Battleship");
                bBattleship.setBounds(50, 10, 141, 35);
                bBattleship.addActionListener(new ActionListener()
                {
                    
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        // TODO Auto-generated method stub
                        startGame("Battleship");
                    }
                });
                gameContents.add(bBattleship);
                bTicTacToe = new JButton("Tic-Tac-Toe");
                bTicTacToe.setBounds(50, 50, 141, 35);
                bTicTacToe.addActionListener(new ActionListener()
                {
                    
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        // TODO Auto-generated method stub
                        startGame("TicTacToe");
                     }
                });
                gameContents.add(bTicTacToe);
                bOthello = new JButton("Othello");
                bOthello.setBounds(50, 90, 141, 35);
                bOthello.addActionListener(new ActionListener()
                {
                    
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        // TODO Auto-generated method stub
                        startGame("Othello");
                    }
                });
                gameContents.add(bOthello);
                
                    
                    
            }
        });
        bHostGame.setBounds(173, 255, 141, 35);
        contentPane.add(bHostGame);
        
        JoinableGamesList = new JScrollPane();
        JoinableGamesList.setBounds(110, 75, 249, 167);
//        JoinableGamesList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JoinableGamesList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        joinPanel.setLayout(new GridLayout(10, 1));
        JoinableGamesList.setViewportView(joinPanel);
        contentPane.add(JoinableGamesList);
        btnTESTjoingame = new JButton("");
        btnTESTjoingame.setBounds(308, 59, 141, 35);
        contentPane.add(btnTESTjoingame);
        btnTESTjoingame.setVisible(false);
        btnTESTjoingame.addActionListener(this);
        bUsernameOK.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {            	
            	tfUsername.setEditable(false);
            	tfUsername.setVisible(false);
            	
            	bUsernameOK.setEnabled(false);
            	bUsernameOK.setVisible(false);
            	
                username = tfUsername.getText().toString();
                txtrEnterUsername.setText("Username: " + username);
         
                txtrEnterUsername.setLineWrap(true);
                System.out.println(username);
                client = new Client("localhost", availGames, port, username);
                client.connect();
                String message = "REG_" + username;
                System.out.println(message);
                client.sendMessage(message);
            }
        });
        repaint();
	}
	
	
	public static void main(String[] args) throws IOException {
		ClientGUI clientGUI = new ClientGUI();
	}
	
	public static void startGame(String game){
		//Sets client as host for selected game
		client.isHost = true;
		
		//Adds game to the list of games
		String str = game + "_" + username;
		availGames.add(str);
		
		client.sendMessage("NEWGAME_" + str);
	}

	public static void updateGames()
    {
        
//        String [] gameInfo = s.split("_");
        joinGame[numGameCount % 10] = new JButton();
        joinGame[numGameCount % 10].setText(availGames.get(availGames.size()-1));
        joinPanel.add(joinGame[numGameCount % 10]);
//        JoinableGamesList.add(joinGame[numGameCount % 10]);
        joinGame[numGameCount % 10].addActionListener(new ActionListener()
        {        
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // TODO Auto-generated method stub
                for(int i = 0; i < 10; ++i)
                {
                    if(e.getSource() == joinGame[i])
                    {
                        client.sendMessage("JOINGAME_" + availGames.get(availGames.size()-1));
                    }
                }
            }
        });
        numGameCount++;
        btnTESTjoingame.setVisible(false);
        btnTESTjoingame.setText(availGames.get(availGames.size()-1));
    }

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		// TODO Auto-generated method stub
		if(e.getSource() == bBattleship)
		{
			startGame("Battleship");
		}
		if(e.getSource() == bTicTacToe)
		{
			startGame("TicTacToe");
		}
		if(e.getSource() == bOthello)
		{
			startGame("Othello");
		}
	}		
}
