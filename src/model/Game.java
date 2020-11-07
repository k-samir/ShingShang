package model;

public class Game {
	private Player  player1,player2;
	private BoardGame board_game;
	private GameState gameState;
	
	
	public Game(Player player1, Player player2, BoardGame board_game) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.board_game = board_game;
	}
	
	public void setupBoard() {
		this.board_game.init();
	}
	
	public Boolean PlayerPlays(Piece piece,Point to_point) {
		return this.board_game.placePiece(piece, to_point);
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
