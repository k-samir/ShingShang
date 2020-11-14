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
		
		// FREE DESTINATION  AND EXISTING PIECE FROM POINT 1
		if(board_game.getPoints()[x2][y2].isUsable()) {
			if(board_game.getPoints()[x1][y1].isUsed()){
				if( board_game.getPoints()[x2][y2].getPiece() == null ){
					if(board_game.getPoints()[x1][y1].getPiece().getColor() == player.getColor()) {
						ret =  this.board_game.placePiece(board_game.getPoints()[x1][y1].getPiece(), board_game.getPoints()[x2][y2]);
					}
					else {
						System.out.println("The Piece is not yours, try again");
					}	
				}
				else {
					ret = false;
					System.out.println("There is a piece on the Destination Point, try again");
				}
			}
			else{
				ret = false;
				System.out.println("There is no Piece on the initial Point, try again");
			}
		}
		else {
			ret = false;
			System.out.println("The Destination Point is outside the board, try again");
		}
		
		//Piece piece,Point to_point
		
		return ret;
	}
	
	
	
	public void displayBoardGame() {
		//DISPLAYING BOARDGAME
		System.out.println("       (#0)   (#1)   (#2)   (#3)   (#4)   (#5)   (#6)   (#7)   (#8)   (#9) ");
		System.out.print("--------------------------------------------------------------------------");
		
		for (int i = 0; i < 10;i++) {
			for (int j = 0; j < 10;j++) {
				
				if(j%10 == 0) {
					System.out.println();
					System.out.println("     |");
					System.out.print("(#"+i+") |");
					
				}
				
				if(board_game.getPoints()[i][j].getPoint_type().toString() == "PORTAL") {
					System.out.print(" [ P ] ");
				}
				else if(board_game.getPoints()[i][j].getPiece() != null) {
					if(board_game.getPoints()[i][j].getPiece().getType() == "Monkey") {
						System.out.print(" [ M ] ");
					}
					if(board_game.getPoints()[i][j].getPiece().getType() == "Lion") {
						System.out.print(" [ L ] ");
					}
					if(board_game.getPoints()[i][j].getPiece().getType() == "Dragon"){
						System.out.print(" [ D ] ");
					}
					
				}
				else if(board_game.getPoints()[i][j].getPoint_type().toString() == "NOTHING") {
					System.out.print(" [   ] ");
				}
					else {
						System.out.print(" [" + i + " " + j + "] ");
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
