package controller;


import model.Game;

import model.Player;
import view.GameGUI;

/**
 * GameController class that make the link between the view and models class
 *
 * @author Samir KAMAR
 *
 */


public class GameController {

	private Game game;
	private Player player1, player2;
	private int currentPlayerTurn;
	private GameGUI view;

	public void createTheGame(Player player1, Player player2) {

		this.game = new Game(player1, player2);
		this.player1 = player1;
		this.player2 = player2;

	}

	public void setView(GameGUI view) {
		this.view = view;
	}


	/** Method start the game, setup the board, set the current player and displays the board */
	public void startTheGame() {
		game.setupBoard();
		// YOU CAN CHANGE THE FIRST PLAYER TO START
		currentPlayerTurn = 1;

		System.out.println("GAME STARTED ...");
		view.displayBoardGame();

	}

	public int getCurrentPlayerTurn() {
		return currentPlayerTurn;
	}

	public void setCurrentPlayerTurn(int currentPlayerTurn) {
		this.currentPlayerTurn = currentPlayerTurn;
	}

	public Boolean player1Move(int x1, int y1, int x2, int y2) {

		Boolean ret = game.playerPlays(player1, x1, y1, x2, y2);

		return ret;
	}

	public Boolean player2Move(int x1, int y1, int x2, int y2) {

		Boolean ret = game.playerPlays(player2, x1, y1, x2, y2);

		return ret;
	}

	/** Switch players turn */
	public void switchPlayer() {
		if (currentPlayerTurn == 1) {
			currentPlayerTurn = 2;
		} else if (currentPlayerTurn == 2) {
			currentPlayerTurn = 1;
		}

	}


	/**  Check if there is a winner */
	public Boolean winnerExist() {
		//System.out.print(this.game.getWinner() != null);
		return this.game.getWinner() != null;
	}
		
	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
		
	}


