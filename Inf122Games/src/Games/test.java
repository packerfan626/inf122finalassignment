package Games;

import java.util.ArrayList;

public class test
{
	public static void main(String[] args)
	{
		Battleship B = new Battleship();
		ArrayList<Ship> ships = B.get_ships();
//		B.display();
		B.displayShips();
		
		Ship AC = ships.get(0);
		Ship BS = ships.get(1);
		Ship SB = ships.get(2);
		Ship CS = ships.get(3);
		Ship DR = ships.get(4);
		B.placeShip(AC, 0, 0, "horizontal");
		B.placeShip(BS, 3, 5, "vertical");
		B.placeShip(SB, 6, 2, "vertical");
		B.placeShip(CS, 7, 1, "horizontal");
		B.placeShip(DR, 5, 9, "horizontal");
		B.display();
		
		System.out.println("\nTEST set_Hit: ");
		B.set_move(0, 0);
		B.set_move(1, 0);
		B.set_move(2, 0);
		B.set_move(3, 0);
		B.set_move(4, 0);
		B.display();
		
		
	}
}
