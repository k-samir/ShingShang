package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.GameController;
import model.PieceState;
import model.Player;
import view.GameGUI;

public class ShingShangTest {

	private static Player player1, player2;
	private static GameController gameC;
	private static GameGUI gameGui;

	public void init() {

		// CREATE PLAYERS, temp import Player
		player1 = new Player("test1", "blue");
		player2 = new Player("test2", "red");

		gameC = new GameController();
		gameGui = new GameGUI(gameC);

		gameC.setView(gameGui);
		gameC.createTheGame(player1, player2);
		gameC.startTheGame();

		
	}

	@Test
	public void initTest() {

		// CREATE PLAYERS, temp import Player
		player1 = new Player("test1", "blue");
		player2 = new Player("test2", "red");

		gameC = new GameController();
		gameGui = new GameGUI(gameC);

		gameC.setView(gameGui);
		gameC.createTheGame(player1, player2);
		gameC.startTheGame();

	}

	@Test
	public void currentUser1() {
		init();
		assertEquals(player1, gameC.getPlayer1());
	}

	@Test
	public void currentUser2() {
		init();
		gameC.switchPlayer();
		assertEquals(player2, gameC.getPlayer2());
	}

	@Test
	public void player1Move() {
		init();
		// DRAGON 01 to 23
		assertEquals(true, gameC.getGame().getBoardGame().getPoints()[0][1].isUsed());
		gameC.player1Move(0, 1, 2, 3);
		assertEquals(false, gameC.getGame().getBoardGame().getPoints()[0][1].isUsed());

	}

	@Test
	public void player2Move() {
		init();
		// DRAGON 91 to 73
		assertEquals(true, gameC.getGame().getBoardGame().getPoints()[9][1].isUsed());
		gameC.player2Move(9, 1, 7, 3);
		assertEquals(false, gameC.getGame().getBoardGame().getPoints()[9][1].isUsed());

	}

	@Test
	public void SeqShingShang() {
		init();
			
		// DRAGON 01 to 23
		// Check if there was a shingshang sequence before the move
		assertEquals(false, gameC.getGame().getBoardGame().getShingShangSeq());
		assertEquals(null,gameC.getGame().getBoardGame().getShingShangPiece());
		
		gameC.player1Move(0, 1, 2, 3);

		// Check if there was a shingshang sequence after the move
		assertEquals(true, gameC.getGame().getBoardGame().getShingShangSeq());

	}
	
	@Test void PieceShingShang() {

		// PLAYER 1 eat Dragon of player 2 and get a extra move ( shingshang )
			init();
			
			// ADDING TESTING PIECES
			gameC.getGame().getBoardGame().addDragon1(4, 6);
			gameC.getGame().getBoardGame().addDragon2(4, 5);
			
			// INDEX OF PIECE WHO WILL GET EATEN
			int index = gameC.getGame().getBoardGame().getPieces().get(0).getPieces()
					.indexOf(gameC.getGame().getBoardGame().getPoints()[4][6].getPiece());

	
			// Check if there was a shingshang sequence and a shingshang piece before the move
			assertEquals(false, gameC.getGame().getBoardGame().getShingShangSeq());
			assertEquals(null,gameC.getGame().getBoardGame().getShingShangPiece());
			
			gameC.player1Move(4, 6, 4, 4);

			// Check if there was a shingshang sequence  a shingshang piece after the move
			assertEquals(true, gameC.getGame().getBoardGame().getShingShangSeq());
			assertEquals(gameC.getGame().getBoardGame().getPieces().get(0).getPieces().get(index)
					,gameC.getGame().getBoardGame().getShingShangPiece());

		}
		
	@Test
	public void checkNoDragonWin() {
		init();
		// ADDING TESTING PIECES
		gameC.getGame().getBoardGame().addDragon1(4, 6);
		gameC.getGame().getBoardGame().addDragon2(4, 5);

		// KILLING DRAGONS
		gameC.getGame().getBoardGame().getPoints()[0][1].getPiece().setPieceState("Dead");
		gameC.getGame().getBoardGame().getPoints()[0][8].getPiece().setPieceState("Dead");
		gameC.getGame().getBoardGame().getPoints()[9][1].getPiece().setPieceState("Dead");
		gameC.getGame().getBoardGame().getPoints()[9][8].getPiece().setPieceState("Dead");

		gameC.getGame().getBoardGame().getPoints()[0][1].setPiece(null);
		gameC.getGame().getBoardGame().getPoints()[0][8].setPiece(null);
		gameC.getGame().getBoardGame().getPoints()[9][1].setPiece(null);
		gameC.getGame().getBoardGame().getPoints()[9][8].setPiece(null);

		// INDEX OF PIECE WHO WILL GET EATEN
		int index = gameC.getGame().getBoardGame().getPieces().get(1).getPieces()
				.indexOf(gameC.getGame().getBoardGame().getPoints()[4][5].getPiece());

		// STATE OF PIECE BEFORE MOVE
		assertEquals(PieceState.ALIVE,
				gameC.getGame().getBoardGame().getPieces().get(1).getPieces().get(index).getPieceState());

		// WINNER BEFORE MOVE
		assertEquals(null, gameC.getGame().getWinner());

		assertEquals(true, gameC.getGame().getBoardGame().getPoints()[4][5].isUsed());
		gameC.player1Move(4, 6, 4, 4);
		assertEquals(false, gameC.getGame().getBoardGame().getPoints()[4][5].isUsed());

		// STATE OF PIECE AFTER MOVE
		assertEquals(PieceState.DEAD,
				gameC.getGame().getBoardGame().getPieces().get(1).getPieces().get(index).getPieceState());

		// WINNER AFTER MOVE
		gameC.getGame().checkGameOver();
		assertEquals(player1, gameC.getGame().getWinner());
	}

	@Test
	public void checkPortalWin() {
		init();
		// ADDING TESTING PIECES
		gameC.getGame().getBoardGame().addDragon1(6, 4);
		gameC.getGame().getBoardGame().addMonkey2(7, 4);
		gameC.getGame().getBoardGame().addDragon1(4, 6);
		gameC.getGame().getBoardGame().addMonkey2(4, 5);

		// WINNER BEFORE MOVE
		assertEquals(null, gameC.getGame().getWinner());

		gameC.player1Move(6, 4, 8, 4);

		// WINNER AFTER MOVE
		gameC.getGame().checkGameOver();
		assertEquals(player1, gameC.getGame().getWinner());
	}

	// DRAGON EATS DRAGON HERE : CASE 1
	@Test
	public void checkEat() {
		init();
		// ADDING TESTING PIECES
		gameC.getGame().getBoardGame().addDragon1(4, 6);

		gameC.getGame().getBoardGame().addDragon2(4, 5);

		// INDEX OF PIECE WHO WILL GET EATEN
		int index = gameC.getGame().getBoardGame().getPieces().get(1).getPieces()
				.indexOf(gameC.getGame().getBoardGame().getPoints()[4][5].getPiece());

		// STATE OF PIECE BEFORE MOVE
		assertEquals(PieceState.ALIVE,
				gameC.getGame().getBoardGame().getPieces().get(1).getPieces().get(index).getPieceState());

		assertEquals(true, gameC.getGame().getBoardGame().getPoints()[4][5].isUsed());
		gameC.player1Move(4, 6, 4, 4);
		assertEquals(false, gameC.getGame().getBoardGame().getPoints()[4][5].isUsed());

		// STATE OF PIECE AFTER MOVE
		assertEquals(PieceState.DEAD,
				gameC.getGame().getBoardGame().getPieces().get(1).getPieces().get(index).getPieceState());

	}

	// Check Dragon eat Lion : CASE 2
	@Test
	public void checkDLEat() {
		init();
		// ADDING TESTING PIECES
		gameC.getGame().getBoardGame().addDragon1(4, 6);

		gameC.getGame().getBoardGame().addLion2(4, 5);

		// INDEX OF PIECE WHO WILL GET EATEN
		int index = gameC.getGame().getBoardGame().getPieces().get(1).getPieces()
				.indexOf(gameC.getGame().getBoardGame().getPoints()[4][5].getPiece());

		// STATE OF PIECE BEFORE MOVE
		assertEquals(PieceState.ALIVE,
				gameC.getGame().getBoardGame().getPieces().get(1).getPieces().get(index).getPieceState());

		assertEquals(true, gameC.getGame().getBoardGame().getPoints()[4][5].isUsed());
		gameC.player1Move(4, 6, 4, 4);
		assertEquals(false, gameC.getGame().getBoardGame().getPoints()[4][5].isUsed());

		// STATE OF PIECE AFTER MOVE
		assertEquals(PieceState.DEAD,
				gameC.getGame().getBoardGame().getPieces().get(1).getPieces().get(index).getPieceState());

	}

	// Check Dragon eat Monkey : CASE 3
	@Test
	public void checkDMEat() {
		init();
		// ADDING TESTING PIECES
		gameC.getGame().getBoardGame().addDragon1(4, 6);

		gameC.getGame().getBoardGame().addMonkey2(4, 5);

		// INDEX OF PIECE WHO WILL GET EATEN
		int index = gameC.getGame().getBoardGame().getPieces().get(1).getPieces()
				.indexOf(gameC.getGame().getBoardGame().getPoints()[4][5].getPiece());

		// STATE OF PIECE BEFORE MOVE
		assertEquals(PieceState.ALIVE,
				gameC.getGame().getBoardGame().getPieces().get(1).getPieces().get(index).getPieceState());

		assertEquals(true, gameC.getGame().getBoardGame().getPoints()[4][5].isUsed());
		gameC.player1Move(4, 6, 4, 4);
		assertEquals(false, gameC.getGame().getBoardGame().getPoints()[4][5].isUsed());

		// STATE OF PIECE AFTER MOVE
		assertEquals(PieceState.DEAD,
				gameC.getGame().getBoardGame().getPieces().get(1).getPieces().get(index).getPieceState());

	}

	// Check Lion eat Monkey : CASE 4
	@Test
	public void checkLMEat() {
		init();
		// ADDING TESTING PIECES
		gameC.getGame().getBoardGame().addLion1(4, 6);

		gameC.getGame().getBoardGame().addMonkey2(4, 5);

		// INDEX OF PIECE WHO WILL GET EATEN
		int index = gameC.getGame().getBoardGame().getPieces().get(1).getPieces()
				.indexOf(gameC.getGame().getBoardGame().getPoints()[4][5].getPiece());

		// STATE OF PIECE BEFORE MOVE
		assertEquals(PieceState.ALIVE,
				gameC.getGame().getBoardGame().getPieces().get(1).getPieces().get(index).getPieceState());

		assertEquals(true, gameC.getGame().getBoardGame().getPoints()[4][5].isUsed());
		gameC.player1Move(4, 6, 4, 4);
		assertEquals(false, gameC.getGame().getBoardGame().getPoints()[4][5].isUsed());

		// STATE OF PIECE AFTER MOVE
		assertEquals(PieceState.DEAD,
				gameC.getGame().getBoardGame().getPieces().get(1).getPieces().get(index).getPieceState());

	}

	// Check Monkey eat Monkey : CASE 5
	@Test
	public void checkMMEat() {
		init();
		// ADDING TESTING PIECES
		gameC.getGame().getBoardGame().addMonkey1(4, 6);

		gameC.getGame().getBoardGame().addMonkey2(4, 5);

		// INDEX OF PIECE WHO WILL GET EATEN
		int index = gameC.getGame().getBoardGame().getPieces().get(1).getPieces()
				.indexOf(gameC.getGame().getBoardGame().getPoints()[4][5].getPiece());

		// STATE OF PIECE BEFORE MOVE
		assertEquals(PieceState.ALIVE,
				gameC.getGame().getBoardGame().getPieces().get(1).getPieces().get(index).getPieceState());

		assertEquals(true, gameC.getGame().getBoardGame().getPoints()[4][5].isUsed());
		gameC.player1Move(4, 6, 4, 4);
		assertEquals(false, gameC.getGame().getBoardGame().getPoints()[4][5].isUsed());

		// STATE OF PIECE AFTER MOVE
		assertEquals(PieceState.DEAD,
				gameC.getGame().getBoardGame().getPieces().get(1).getPieces().get(index).getPieceState());

	}

	// Check Monkey eat Monkey : CASE 6
	@Test
	public void checkLLEat() {
		init();
		// ADDING TESTING PIECES
		gameC.getGame().getBoardGame().addLion1(4, 6);

		gameC.getGame().getBoardGame().addLion2(4, 5);

		// INDEX OF PIECE WHO WILL GET EATEN
		int index = gameC.getGame().getBoardGame().getPieces().get(1).getPieces()
				.indexOf(gameC.getGame().getBoardGame().getPoints()[4][5].getPiece());

		// STATE OF PIECE BEFORE MOVE
		assertEquals(PieceState.ALIVE,
				gameC.getGame().getBoardGame().getPieces().get(1).getPieces().get(index).getPieceState());

		assertEquals(true, gameC.getGame().getBoardGame().getPoints()[4][5].isUsed());
		gameC.player1Move(4, 6, 4, 4);
		assertEquals(false, gameC.getGame().getBoardGame().getPoints()[4][5].isUsed());

		// STATE OF PIECE AFTER MOVE
		assertEquals(PieceState.DEAD,
				gameC.getGame().getBoardGame().getPieces().get(1).getPieces().get(index).getPieceState());

	}

}
