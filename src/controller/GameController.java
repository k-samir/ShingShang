package controller;

import model.Game;
import model.GameState;
import model.Piece;
import model.Player;
import model.Point;

public class GameController {
	
	private Game game;
	private Player  player1,player2;
	private int currentPlayerTurn;
	
	public void createTheGame(Player player1,Player player2) {
		
		this.game = new Game(player1,player2);
		
		
	}
	
	public void startTheGame() {
		game.setupBoard();
		//YOU CAN CHANGE THE FIRST PLAYER TO START
		currentPlayerTurn = 1;
		
	}
	public int getCurrentPlayerTurn() {
		return currentPlayerTurn;
	}

	public void setCurrentPlayerTurn(int currentPlayerTurn) {
		this.currentPlayerTurn = currentPlayerTurn;
	}

	public Boolean isWinner() {
		return false;
	}
	public void annouceWinner() {}
	
	//public void Player1Move(Piece piece,Point to_point) {}
	//public void Player2Move(Piece piece,Point to_point) {}
	
	
	public void Player1Move(int x1,int y1,int x2,int y2) {
		System.out.println("Move : " + game.PlayerPlays(player1, x1, y1, x2, y2).toString());
	
	}
	
	public void Player2Move(int x1,int y1,int x2,int y2) {

		System.out.println("Move : " + game.PlayerPlays(player2, x1, y1, x2, y2).toString());
		}
	
	
	
	public void switchPlayer() {
		if(currentPlayerTurn == 1) {
			currentPlayerTurn = 2;
		}
		else if (currentPlayerTurn == 2) {
			currentPlayerTurn = 1;
		}
		
	}
	
	public void displayBoard() {
		game.displayBoardGame();
		System.out.println("");
	}
	
	

}
