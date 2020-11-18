package controller;

import java.util.Scanner;

import model.Game;

import model.Player;


public class GameController {

	private Game game;
	private Player player1, player2;
	private int currentPlayerTurn;

	public void createTheGame(Player player1, Player player2) {

		this.game = new Game(player1, player2);
		this.player1 = player1;
		this.player2 = player2;

	}

	public void startTheGame() {
		game.setupBoard();
		// YOU CAN CHANGE THE FIRST PLAYER TO START
		currentPlayerTurn = 1;

		System.out.println("GAME STARTED ...");
		displayBoard();

	}

	public int getCurrentPlayerTurn() {
		return currentPlayerTurn;
	}

	public void setCurrentPlayerTurn(int currentPlayerTurn) {
		this.currentPlayerTurn = currentPlayerTurn;
	}

	public void annouceWinner() {
		if(game.getWinner() != null) {
		System.out.println(game.getWinner().getName() + " wins");
		}
	
	}

	// public void Player1Move(Piece piece,Point to_point) {}
	// public void Player2Move(Piece piece,Point to_point) {}

	public Boolean Player1Move(int x1, int y1, int x2, int y2) {

		Boolean ret = game.PlayerPlays(player1, x1, y1, x2, y2);

		return ret;
	}

	public Boolean Player2Move(int x1, int y1, int x2, int y2) {

		Boolean ret = game.PlayerPlays(player2, x1, y1, x2, y2);

		return ret;
	}

	public void switchPlayer() {
		if (currentPlayerTurn == 1) {
			currentPlayerTurn = 2;
		} else if (currentPlayerTurn == 2) {
			currentPlayerTurn = 1;
		}

	}

	public void displayBoard() {
		game.displayBoardGame();
		System.out.println("");
	}
	
	public Boolean WinnerExist() {
		//System.out.print(this.game.getWinner() != null);
		return this.game.getWinner() != null;
	}
		
	public void nextTurn(Scanner sc) {

		
			Boolean move = false;
			System.out.println("\n ---- Player " + this.getCurrentPlayerTurn() + "'s turn : ---- ");
			while (!move) {

				System.out.println("Choose option : 1 playe move , 2 pass turn, 3 see all possible move for a piece");
				System.out.println("Enter Your choice : ");
				int action = sc.nextInt();
				// MOVING
				if (action == 1) {
					System.out.println("From Which Point ? (input must be like (x y) )");
					int from_x = sc.nextInt();
					int from_y = sc.nextInt();
					System.out.println("To you which Point ? (input must be like (x y) )");
					int to_x = sc.nextInt();
					int to_y = sc.nextInt();

					if (this.getCurrentPlayerTurn() == 1) {
						move = Player1Move(from_x, from_y, to_x, to_y);
					} 
					if(this.getCurrentPlayerTurn() == 2) {
						move = Player2Move(from_x, from_y, to_x, to_y);
					}

				}
				// PASSING
				else if (action == 2) {
					move = true;
					game.setShingShangSeq(false);
				}
				
				else if(action == 3) {
					System.out.println("From Which Point ? (input must be like (x y) )");
					int from_x = sc.nextInt();
					int from_y = sc.nextInt();
					
					if (this.getCurrentPlayerTurn() == 1) {
						game.displayValidMoves(this.player1,from_x,from_y);
					} 
					if(this.getCurrentPlayerTurn() == 2) {
						game.displayValidMoves(this.player2,from_x,from_y);
					}
					
					
				}
				
				game.checkGameOver();

			}
			if (!game.ShingShangSeq()) {

				this.switchPlayer();
				
			}

			this.displayBoard();
		}
		
	}


