package model;

public class Game {
	private Player  player1,player2;
	private BoardGame board_game;
	private GameState gameState;
	
	
	public Game(Player player1, Player player2) {
		//super();
		
		this.player1 = player1;
		this.player2 = player2;
		
		
		this.board_game = new BoardGame(player1.getColor(),player2.getColor());
		//this.board_game.init();
		
	
	}
	
	public void setupBoard() {
		this.board_game.init();
	}
	
	
	public Boolean PlayerPlays(Player player,int x1,int y1,int x2,int y2) {
		// CHECK IF PIECE IS PLAYERS
		// CHECK IF POINTS IS EMPTY, VALID
		Boolean ret = false;
		
		if(board_game.getPoints()[x1][y1].getPiece() != null && board_game.getPoints()[x2][y2].getPiece() == null) {
			ret =  this.board_game.placePiece(board_game.getPoints()[x1][y1].getPiece(), board_game.getPoints()[x2][y2]);
		}
		//Piece piece,Point to_point
		
		return ret;
	}
	
	
	
	public void displayBoardGame() {
		//DISPLAYING BOARDGAME
		for (int i = 0; i < 10;i++) {
			for (int j = 0; j < 10;j++) {
				
				if(j%10 == 0) {
					System.out.println();
				}
				if(board_game.getPoints()[i][j].getPoint_type().toString() == "PORTAL") {
					System.out.print("     PORTAL");
				}
				else if(board_game.getPoints()[i][j].getPiece() != null) {
					System.out.print("     " + board_game.getPoints()[i][j].getPiece().getType());
					}
				else if(board_game.getPoints()[i][j].getPoint_type().toString() == "NOTHING") {
					System.out.print("     X    ");
				}
					else {
						System.out.print("     [" + i + " " + j + "]");
					}
					
			}
		}
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
	public BoardGame getBoard_game() {
		return board_game;
	}
	public void setBoard_game(BoardGame board_game) {
		this.board_game = board_game;
	}
	public GameState getGameState() {
		return gameState;
	}
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	
	

}
