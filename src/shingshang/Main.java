package shingshang;


import java.util.Scanner;

import controller.GameController;
import model.Player;
//import view.GameGUI;

public class Main {

	public static void main(String[] args) {
	
		
	//GameGUI gameGui = new GameGUI();
	GameController gameC = new GameController();	
		
	//CREATE PLAYERS
	Player player1 = new Player("SAMIR", "blue");
	Player player2 = new Player("SAM", "red");
	
	
	gameC.createTheGame(player1, player2);

	System.out.println("                                               \r\n"
			+ " _____ _   _             _____ _               \r\n"
			+ "|   __| |_|_|___ ___ ___|   __| |_ ___ ___ ___ \r\n"
			+ "|__   |   | |   | . |___|__   |   | .'|   | . |\r\n"
			+ "|_____|_|_|_|_|_|_  |   |_____|_|_|__,|_|_|_  |\r\n"
			+ "                |___|                     |___|");
	Scanner sc = new Scanner(System.in);

	 while (true) {
         System.out.println("Press any key to begin ...");
         
         sc.nextLine();

         break;
     }
	gameC.startTheGame();

				
	while(!gameC.WinnerExist()) {
		gameC.nextTurn(sc);
	}
	
	gameC.annouceWinner();
	System.out.println("GAME OVER..");

	}

}
