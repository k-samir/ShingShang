package shingshang;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.util.Scanner;

import controller.GameController;
import model.Player;
import view.GameGUI;

public class Main {

	public static void main(String[] args) {
	
		
	GameGUI gameGui = new GameGUI();
	GameController gameC = new GameController();	
		
	//CREATE PLAYERS
	Player player1 = new Player("SAMIR", "blue");
	Player player2 = new Player("SAM", "red");
	
	
	gameC.createTheGame(player1, player2);

	gameC.startTheGame();
	System.out.println("GAME STARTED ...");
	gameC.displayBoard();
	
	//System.out.println("current loop ...");
	Scanner sc = new Scanner(System.in);
			
	while(!gameC.isWinner()) {
		
		System.out.println("\n ---- Player "+ gameC.getCurrentPlayerTurn() +  "'s turn : ---- ");
		System.out.println("Choose option : 1 playe move , 2 pass turn, 3 see all possible move for a piece");
		System.out.println("Enter Your choice : ");
		int text = sc.nextInt(); 
		if(text == 1) {
			System.out.println("From Which Point ? (input must be like (x y) )");
			int from_x = sc.nextInt(); 
			int from_y = sc.nextInt(); 
			System.out.println("To you which Point ? (input must be like (x y) )");
			int to_x = sc.nextInt(); 
			int to_y = sc.nextInt(); 
			
			if(gameC.getCurrentPlayerTurn() == 1) {
				gameC.Player1Move(from_x,from_y,to_x,to_y);
			}
			else{
				gameC.Player2Move(from_x,from_y,to_x,to_y);
		}
				
		}
		
		gameC.switchPlayer();	
		gameC.displayBoard();
	}
	
	gameC.annouceWinner();
	System.out.println("GAME OVER..");

	}

}
