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

	Scanner sc = new Scanner(System.in);
			
	while(!gameC.isWinner()) {
		gameC.nextTurn(sc);
	}
	
	gameC.annouceWinner();
	System.out.println("GAME OVER..");

	}

}
