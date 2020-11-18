package view;

import java.util.Scanner;

import controller.GameController;
import model.Player;

public class GameGUI {
	
	private GameController gameC;
	
	public GameGUI(GameController gameC) {
		this.gameC = gameC;
	}
	
	private void annouceWinner() {
		if(gameC.getGame().getWinner() != null) {
		System.out.println("\n" + gameC.getGame().getWinner().getName() + " wins");
		}
	
	}
	public void createTheGame(Player player1, Player player2) {
		System.out.println("                                               \r\n"
			+ " _____ _   _             _____ _               \r\n"
			+ "|   __| |_|_|___ ___ ___|   __| |_ ___ ___ ___ \r\n"
			+ "|__   |   | |   | . |___|__   |   | .'|   | . |\r\n"
			+ "|_____|_|_|_|_|_|_  |   |_____|_|_|__,|_|_|_  |\r\n"
			+ "                |___|                     |___|");
	
		gameC.createTheGame(player1, player2);
	}

	
	private void nextTurn(Scanner sc) {

		Boolean move = false;
		System.out.println("\n ---- Player " + gameC.getCurrentPlayerTurn() + "'s turn : ---- ");
		
		while (!move) {

			System.out.println("Choose option : 1 playe move , 2 pass turn, 3 see all possible move for a piece");
			System.out.println("Enter Your choice : ");
			int action = sc.nextInt();
			// MOVING
			if (action == 1) {
				System.out.println("From Which Point ? (input must be like (x y) )");
				
				int from_x = sc.nextInt();
				int from_y = sc.nextInt();
				
				while(from_x > 9|| from_y > 9) {
					System.out.println("x or y is too big , try again ( x & y must be lower than 10 )");
					from_x = sc.nextInt();
					from_y = sc.nextInt();
				}
				System.out.println("To you which Point ? (input must be like (x y) )");
				int to_x = sc.nextInt();
				int to_y = sc.nextInt();
				
				while(to_x > 9|| to_y > 9) {
					System.out.println("x or y is too big , try again ( x & y must be lower than 10 )");
					to_x = sc.nextInt();
					to_y = sc.nextInt();
				}

				if (gameC.getCurrentPlayerTurn() == 1) {
					move = gameC.player1Move(from_x, from_y, to_x, to_y);
				} 
				if(gameC.getCurrentPlayerTurn() == 2) {
					move = gameC.player2Move(from_x, from_y, to_x, to_y);
				}

			}
			// PASSING
			else if (action == 2) {
				move = true;
				gameC.getGame().setShingShangSeq(false);
			}
			
			else if(action == 3) {
				System.out.println("From Which Point ? (input must be like (x y) )");
				int from_x = sc.nextInt();
				int from_y = sc.nextInt();
				
				while(from_x > 9|| from_y > 9) {
					System.out.println("x or y is too big , try again ( x & y must be lower than 10 )");
					from_x = sc.nextInt();
					from_y = sc.nextInt();
				}
				
				if (gameC.getCurrentPlayerTurn() == 1) {
					gameC.getGame().displayValidMoves(gameC.getPlayer1(),from_x,from_y);
				} 
				if(gameC.getCurrentPlayerTurn() == 2) {
					gameC.getGame().displayValidMoves(gameC.getPlayer2(),from_x,from_y);
				}
				
				
			}
			
			gameC.getGame().checkGameOver();

		}
		if (!gameC.getGame().shingShangSeq()) {

			gameC.switchPlayer();
			
		}

		displayBoardGame();
	}
	
	
	public void startTheGame() {
		
		Scanner sc = new Scanner(System.in);

		 while (true) {
	         System.out.println("Press any key to begin ...");
	         
	         sc.nextLine();

	         break;
	     }
		 
		gameC.startTheGame();
		
		while(!gameC.winnerExist()) {
			nextTurn(sc);
		}
		
		annouceWinner();
		System.out.println("GAME OVER..");

		
	}
	
	public void displayBoardGame() {
		// DISPLAYING BOARDGAME

		System.out.println("       (#0)   (#1)   (#2)   (#3)   (#4)   (#5)   (#6)   (#7)   (#8)   (#9) ");
		System.out.print("--------------------------------------------------------------------------");

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {

				if (j % 10 == 0) {
					System.out.println();
					System.out.println("     |");
					System.out.print("(#" + i + ") |");

				}

				if (gameC.getGame().getBoardGame().getPoints()[i][j].getPointType().toString() == "PORTAL"
						&& gameC.getGame().getBoardGame().getPoints()[i][j].getPiece() == null) {
					System.out.print(" [ P ] ");

				} else if ((gameC.getGame().getBoardGame().getPoints()[i][j].getPiece() != null)
						&& (!gameC.getGame().getBoardGame().getPoints()[i][j].getPiece().isDead())) {
					if (gameC.getGame().getBoardGame().getPoints()[i][j].getPiece().getType() == "Monkey") {
						System.out.print(" [ M ] ");
					}
					if (gameC.getGame().getBoardGame().getPoints()[i][j].getPiece().getType() == "Lion") {
						System.out.print(" [ L ] ");
					}
					if (gameC.getGame().getBoardGame().getPoints()[i][j].getPiece().getType() == "Dragon") {
						System.out.print(" [ D ] ");
					}

				} else if (gameC.getGame().getBoardGame().getPoints()[i][j].getPointType().toString() == "NOTHING") {
					System.out.print(" [   ] ");
				} else {
					System.out.print(" [" + i + " " + j + "] ");
				}

			}
		}
	}
	
}
