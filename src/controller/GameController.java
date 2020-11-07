package controller;

import model.Game;
import model.GameState;
import model.Piece;
import model.Player;
import model.Point;

public class GameController {
	
	private Game game;
	private Player  player1,player2;
	private Player currentPlayerTurn;
	
	public void createTheGame(Player player1,Player player2) {
		// MODIFY CONSTRUCTOR TO IMPLEMENT BOARDGAME
		//this.game = new Game(player1,player2);
		
	}
	
	public void startTheGame() {
		
	}
	
	public void annouceWinner() {}
	
	public void Player1Move(Piece piece,Point to_point) {}
	public void Player2Move(Piece piece,Point to_point) {}
	
	public void switchPlayer() {
		if(currentPlayerTurn == player1) {
			currentPlayerTurn = player2;
		}
		else if (currentPlayerTurn == player2) {
			currentPlayerTurn = player1;
		}
		else {}
	}
	
	

}
