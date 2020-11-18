package shingshang;


import controller.GameController;
import model.Player;
import view.GameGUI;

public class Main {

	public static void main(String[] args) {
	
	GameController gameC = new GameController();	
	GameGUI gameGui = new GameGUI(gameC);
	gameC.setView(gameGui);
		
	//CREATE PLAYERS, temp import Player
	Player player1 = new Player("SAMIR", "blue");
	Player player2 = new Player("SAM", "red");
	
	gameGui.createTheGame(player1, player2);	 
	gameGui.startTheGame();
	
	
	}

}
