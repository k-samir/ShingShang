package model;

import java.util.ArrayList;


/**
 * Game class : game methods, to manage various actions
 * 
 * @author Samir KAMAR
 *
 */
public class Game {
	private Player player1, player2;
	private BoardGame board_game;
	private GameState gameState;
	private Player winner = null;

	private ArrayList<Point> currentValidMoves = null;

	public Game(Player player1, Player player2) {
		// super();

		this.player1 = player1;
		this.player2 = player2;
		this.gameState = GameState.INGAME;

		this.board_game = new BoardGame(player1.getColor(), player2.getColor());
		// this.board_game.init();

	}

	public void setupBoard() {
		this.board_game.init();
	}

	public boolean shingShangSeq() {
		return this.board_game.getShingShangSeq();
	}

	public void setShingShangSeq(boolean bool) {
		this.board_game.setShingShangSeq(bool);
	}

	
	/**  update the validmoves of the current player and store it in currentvalidmoves arraylist*/
	
	public void updateValidMoves(Player player, int x1, int y1) {
		if(this.currentValidMoves != null) {
		this.currentValidMoves.clear();
		}
		this.setCurrentValidMoves(this.board_game.getValidMoves(board_game.getPoints()[x1][y1].getPiece()));

	}

	public ArrayList<Point> getCurrentValideMoves() {
		return currentValidMoves;
	}

	public void setCurrentValidMoves(ArrayList<Point> currentValideMoves) {
		this.currentValidMoves = currentValideMoves;
	}

	
	/**  Player move action : check if piece is players,check if destination point is empty adn usable */
	
	public boolean playerPlays(Player player, int x1, int y1, int x2, int y2) {
		
		boolean ret = false;

		// FREE DESTINATION AND EXISTING PIECE FROM POINT 1
		if (board_game.getPoints()[x2][y2].isUsable()) {
			if (board_game.getPoints()[x1][y1].isUsed()) {
				if (board_game.getPoints()[x2][y2].getPiece() == null) {
					
					if (board_game.getPoints()[x1][y1].getPiece().getColor() == player.getColor()) {
						

						// UPDATE ALL CURRENT MOVES AVAILABLE FOR PIECES IN currentValideMoves
						updateValidMoves(player, x1, y1);

						ret = this.board_game.placePiece(board_game.getPoints()[x1][y1].getPiece(),
								board_game.getPoints()[x2][y2]);
						

					} else {
						System.out.println("The Piece is not yours, try again");
					}
				} else {
					ret = false;
					System.out.println("There is a piece on the Destination Point, try again");
				}
			} else {
				ret = false;
				System.out.println("There is no Piece on the initial Point, try again");
			}
		} else {
			ret = false;
			System.out.println("The Destination Point is outside the board, try again");
		}
		
		if(ret) {
			//System.out.println();
			System.out.println("\n" + board_game.getPoints()[x2][y2].getPiece().getColor() + " : ["+x1 + ","+ y1 +"] to [" + x2 + "," +  y2+"]\n");
			
		}

		return ret;
	}

	public void checkGameOver() {

		// GAME OVER WHEN DRAGON IS ON PORTAL OF OPONENT
		// OR OPPONENT HAVE NO DRAGON
		Tuple<Boolean, String> game_over = this.board_game.onPortalCheck();
		Tuple<Boolean, String> game_over2 = this.board_game.noDragonCheck();

		
		if (game_over.getFirst()) {

			this.setGameState(GameState.ENDGAME);
			if (game_over.getSecond() == "1") {
				this.setWinner(this.player1);
			} else if (game_over.getSecond() == "2") {
				this.setWinner(this.player2);
			}
		}
		if (game_over2.getFirst()) {

			this.setGameState(GameState.ENDGAME);
			if (game_over2.getSecond() == "2") {
				this.setWinner(this.player2);
			} else if (game_over2.getSecond() == "1") {
				this.setWinner(this.player1);
			}
		}

	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
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

	public BoardGame getBoardGame() {
		return board_game;
	}

	public void setBoardGame(BoardGame board_game) {
		this.board_game = board_game;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

}
