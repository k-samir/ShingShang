package view;

import java.util.Scanner;

import controller.GameController;
import model.Piece;
import model.PieceState;
import model.Player;

/**
 * GameGUI class : graphical user interface displays the board to the user
 * @author Samir KAMAR
 *
 */
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

		boolean move = false;
		System.out.println("\n ---- Player " + gameC.getCurrentPlayerTurn() + "'s turn : ---- ");
		
		while (!move) {

			System.out.println("Choose option : 1 playe move , 2 pass turn, 3 see all possible move for a piece, 4 to quit");
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
				gameC.getGame().setShingShangSeqAlly(false);
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
					displayValidMoves(gameC.getPlayer1(),from_x,from_y);
				} 
				if(gameC.getCurrentPlayerTurn() == 2) {
					displayValidMoves(gameC.getPlayer2(),from_x,from_y);
				}
				
				
			}
			else if(action == 4) {
				System.exit(1);
			}
			
			gameC.getGame().checkGameOver();

		}
		// Check if there is any type of shingshang ( jump ally or eat)
		if (!gameC.getGame().shingShangSeq() && !gameC.getGame().shingShangSeqAlly()) {

			gameC.switchPlayer();
			
		}

		displayBoardGame();
	}
	
	
	public void displayValidMoves(Player player, int x1, int y1) {

		System.out.println("\n");
		if (gameC.getGame().getBoardGame().getPoints()[x1][y1].getPiece() != null) {
			if (gameC.getGame().getBoardGame().getShingShangPiece() != gameC.getGame().getBoardGame().getPoints()[x1][y1].getPiece()) {
				if (gameC.getGame().getBoardGame().getPoints()[x1][y1].getPiece().getColor() == player.getColor()) {

					gameC.getGame().updateValidMoves(player, x1, y1);

					System.out.println("+--------------------------------------+\r\n"
							+ "|  Possible move for current piece :   |\r\n"
							+ "+--------------------------------------+\r\n");

					for (int i = 0; i < gameC.getGame().getCurrentValideMoves().size(); i++) {
						System.out.println("Destination possible : [" + gameC.getGame().getCurrentValideMoves().get(i).getN_row() + " "
								+ gameC.getGame().getCurrentValideMoves().get(i).getN_column() + "]");

					}

				} else {
					System.out.println("The Piece is not yours, try again");
				}
			}
			else {
				System.out.println("The Piece was used in last Shing Shang, try another piece");
			}
		}
		else {
			System.out.println("There is no Piece on the initial Point, try again");
		}

		System.out.println("\n");

	}
	
	
	public void startTheGame() {
		
		Scanner sc = new Scanner(System.in);

		 while (true) {
	         System.out.println("Press enter key to begin ...");
	         
	         sc.nextLine();

	         break;
	     }

		int choice = 1;
		while (true) {
	         System.out.println("Do you want to play against a bot ? ( 1 :  no, 2 : yes");
	         
	         choice = sc.nextInt();

	         break;
	     }
		
		 gameC.startTheGame();
		
		if(choice == 1) {
		
		while(!gameC.winnerExist()) {
			nextTurn(sc);
			}
		}
		
		else if(choice == 2) {
			while(!gameC.winnerExist()) {
				nextTurnBot(sc);
				}
			
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
					if (gameC.getGame().getBoardGame().getPoints()[i][j].getPiece().getType() == "Monkey" &&
							gameC.getGame().getBoardGame().getPoints()[i][j].getPiece().getPieceState() == PieceState.ALIVE	) {
						System.out.print(" [ M ] ");
					}
					if (gameC.getGame().getBoardGame().getPoints()[i][j].getPiece().getType() == "Lion" &&
							gameC.getGame().getBoardGame().getPoints()[i][j].getPiece().getPieceState() == PieceState.ALIVE	) {
						System.out.print(" [ L ] ");
					}
					if (gameC.getGame().getBoardGame().getPoints()[i][j].getPiece().getType() == "Dragon" &&
							gameC.getGame().getBoardGame().getPoints()[i][j].getPiece().getPieceState() == PieceState.ALIVE	) {
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
	
	
	private void nextTurnBot(Scanner sc) {

		boolean move = false;
		System.out.println("\n ---- Player " + gameC.getCurrentPlayerTurn() + "'s turn : ---- ");
		
		if(gameC.getCurrentPlayerTurn() == 1) {
		while (!move) {

			System.out.println("Choose option : 1 playe move , 2 pass turn, 3 see all possible move for a piece, 4 to quit");
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
				gameC.getGame().setShingShangSeqAlly(false);
				
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
					displayValidMoves(gameC.getPlayer1(),from_x,from_y);
				} 
				if(gameC.getCurrentPlayerTurn() == 2) {
					displayValidMoves(gameC.getPlayer2(),from_x,from_y);
				}
				
				
			}
			else if(action == 4) {
				System.exit(1);
			}
			
			gameC.getGame().checkGameOver();

			}
		}
		else {
			
			
			
			int validMoves = 0;
			Piece randomPiece = null;
			int x_randomPiece = -1;
			int y_randomPiece = -1;
			int randomMove = -1;
			
			while(validMoves == 0) {
				int randomIndexPiece = (int)(Math.random() * 12);
				
				randomPiece = gameC.getGame().getBoardGame().getPieces().get(1).getPieces().get(randomIndexPiece);
				
				x_randomPiece = randomPiece.getPosition().getN_row();
				y_randomPiece = randomPiece.getPosition().getN_column();
				
				gameC.getGame().updateValidMoves(gameC.getPlayer2(), x_randomPiece, y_randomPiece);
				
				if(gameC.getGame().getCurrentValideMoves().size() > 0) {
					validMoves = 1;
				}
				
			
			}
			
			randomMove = (int)(Math.random() * gameC.getGame().getCurrentValideMoves().size());
			move = gameC.player2Move(x_randomPiece, y_randomPiece, 
					gameC.getGame().getCurrentValideMoves().get(randomMove).getN_row(),
					gameC.getGame().getCurrentValideMoves().get(randomMove).getN_column());
			
				gameC.getGame().checkGameOver();
			
			}
			
		
		if (!gameC.getGame().shingShangSeq() || !gameC.getGame().shingShangSeqAlly()) {

			gameC.switchPlayer();
			
		}

		displayBoardGame();
	}
	
	
}
