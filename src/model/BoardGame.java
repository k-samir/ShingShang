package model;

import java.util.ArrayList;

/**
 * BoardGame class : represent the board of the game and various methods
 * 
 * @author Samir KAMAR
 *
 */


public class BoardGame {

	private ArrayList<PieceSet> pieces;
	private Point[][] points;

	private String colorJ1;
	private String colorJ2;

	private MoveValidator moveValidator;

	private boolean shingShangSeq = false;
	private boolean shingShangSeqAlly = false;
	
	private Piece shingShangPiece = null;
	private Piece shingShangPieceAlly = null;

	public BoardGame(String colorJ1, String colorJ2) {
		// super();

		this.colorJ1 = colorJ1;
		this.colorJ2 = colorJ2;

		pieces = new ArrayList<PieceSet>();
		points = new Point[10][10];

		pieces.add(new PieceSet(colorJ1));
		pieces.add(new PieceSet(colorJ2));

		moveValidator = new MoveValidator();


	}

	/**  This method, init the boardgame, create all the point (cells) of the board, and add all the piece
	 * in their specific position
	 * */
	public void init() {

		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {

				if (j % 10 == 0) {
					//System.out.println("\n");
				}

				if ((i == 1 && (j == 4 || j == 5) || (i == 8 && (j == 4 || j == 5)))) {
					this.getPoints()[i][j] = new Point(i, j, "Portal");
				}

				else if (j == 0 || j == 9) {
					this.getPoints()[i][j] = new Point(i, j, "Nothing");
				} else {
					this.getPoints()[i][j] = new Point(i, j, "Standard");

				}

			}
		}


		this.getPoints()[4][0] = new Point(4, 0, "Standard");
		this.getPoints()[5][0] = new Point(5, 0, "Standard");

		this.getPoints()[4][9] = new Point(4, 9, "Standard");
		this.getPoints()[5][9] = new Point(5, 9, "Standard");

		this.addDragon1(0, 1);
		this.addDragon1(0, 8);
		this.addLion1(0, 2);
		this.addLion1(1, 1);
		this.addLion1(1, 8);
		this.addLion1(0, 7);
		this.addMonkey1(0, 3);
		this.addMonkey1(0, 6);
		this.addMonkey1(1, 2);
		this.addMonkey1(1, 7);
		this.addMonkey1(2, 1);
		this.addMonkey1(2, 8);

		this.addDragon2(9, 1);
		this.addDragon2(9, 8);
		this.addLion2(8, 1);
		this.addLion2(9, 2);
		this.addLion2(9, 7);
		this.addLion2(8, 8);
		this.addMonkey2(7, 1);
		this.addMonkey2(8, 2);
		this.addMonkey2(9, 3);
		this.addMonkey2(9, 6);
		this.addMonkey2(8, 7);
		this.addMonkey2(7, 8);

		// TESTING PIECES
		//this.addDragon1(4, 6);
		//this.addMonkey1(7, 4);

		//this.addDragon2(4, 5);
		//this.addMonkey2(4, 6);

	}

////////////////////////////////////////////////////////////////////////////
	public void addLion1(int x, int y) {
		this.getPoints()[x][y].setPiece(new Lion(this.getPoints()[x][y], colorJ1));
		this.getPieces().get(0).addPiece(this.getPoints()[x][y].getPiece());
	}

	public void addMonkey1(int x, int y) {
		this.getPoints()[x][y].setPiece(new Monkey(this.getPoints()[x][y], colorJ1));
		this.getPieces().get(0).addPiece(this.getPoints()[x][y].getPiece());
	}

	public void addDragon1(int x, int y) {
		this.getPoints()[x][y].setPiece(new Dragon(this.getPoints()[x][y], colorJ1));
		this.getPieces().get(0).addPiece(this.getPoints()[x][y].getPiece());
	}

////////////////////////////////////////////////////////////////////////////
	public void addLion2(int x, int y) {
		this.getPoints()[x][y].setPiece(new Lion(this.getPoints()[x][y], colorJ2));
		this.getPieces().get(1).addPiece(this.getPoints()[x][y].getPiece());
	}

	public void addMonkey2(int x, int y) {
		this.getPoints()[x][y].setPiece(new Monkey(this.getPoints()[x][y], colorJ2));
		this.getPieces().get(1).addPiece(this.getPoints()[x][y].getPiece());
	}

	public void addDragon2(int x, int y) {
		this.getPoints()[x][y].setPiece(new Dragon(this.getPoints()[x][y], colorJ2));
		this.getPieces().get(1).addPiece(this.getPoints()[x][y].getPiece());
	}

////////////////////////////////////////////////////////////////////////////
	public String getColorJ1() {
		return colorJ1;
	}

	public void setColorJ1(String colorJ1) {
		this.colorJ1 = colorJ1;
	}

	public String getColorJ2() {
		return colorJ2;
	}

	public void setColorJ2(String colorJ2) {
		this.colorJ2 = colorJ2;
	}

	/**  Get all the pieces on the board */
	public ArrayList<PieceSet> getPieces() {
		return pieces;
	}

	public void setPieces(ArrayList<PieceSet> pieces) {
		this.pieces = pieces;
	}

	/**  Get all the points of the board */
	public Point[][] getPoints() {
		return points;
	}

	public void setPoints(Point[][] points) {
		this.points = points;
	}

	private void movePiece(Piece piece, Point to_point) {
		removePiece(piece.getPosition());
		piece.setPosition(to_point);
		this.getPoints()[to_point.getN_row()][to_point.getN_column()].setPiece(piece);
	}

	private void removePiece(Point point) {
		point.setPiece(null);
	}

	/** this method eats a piece from a point, piece is the eater and point is the position of 
	 * the dead piece 
	 *  */
	private void eatPiece(Piece piece, Point point) {

		System.out.println(piece.getType() + " of Player " + piece.getColor() + " jump over : "
				+ point.getPiece().getType() + " of Player " + point.getPiece().getColor() + " and catches him.");

		point.getPiece().killPiece();
		removePiece(point);

	}

	/**  Method to check if the play is ok , check if the piece is not the current shingshang piece,
	 * and check if there was/is a shingshang sequence
	 * */
	public boolean placePiece(Piece piece, Point point) {

		boolean ret = false;

		if (piece.equals(shingShangPiece)) {
			System.out.println("Choose another Piece, you already did a ShingShang sequence with this piece.");
		} 
			
		else {

			Tuple<Boolean, String> moveData = moveValidator.moveValid(piece, point, getPoints());

			// CHECK IF MOVE OK -- getFirst return the Boolean of moveValidator ( move is
			// valid or not )
			if (moveData.getFirst()) {
				//If shingshangAlly true check if shingshangpiece is piece played OR continue if no shingshangseqally
				if ((piece.equals(shingShangPieceAlly) &&  shingShangSeqAlly ) || (!shingShangSeqAlly)){			
				// Reset the current ShingShangPiece & ShingShangPieceAlly
				setShingShangPiece(null);
				setShingShangPieceAlly(null);

				if (moveData.getSecond() == "EnnemyJump") {
					System.out.println("SHING-SHANG you have 1 extra turn with another piece !");
					this.shingShangSeq = true;
					setShingShangPiece(piece);

					eatPiece(piece, getNeighbourPoint(piece, point));
					movePiece(piece, point);

				} else if (moveData.getSecond() == "AllyJump") {
					System.out.println("SHING-SHANG you have 1 extra turn with this piece !");
					shingShangSeqAlly = true;			
					setShingShangPieceAlly(piece);
					movePiece(piece, point);
				}

				else {
					this.shingShangSeq = false;
					this.shingShangSeqAlly = false;
					movePiece(piece, point);
				}

				ret = true;
			}
			else {
				ret = false;
				System.out.println("The Move is not allowed, try again");
			}

		}
		}
		return ret;
	}


	public Piece getShingShangPiece() {
		return shingShangPiece;
	}

	public void setShingShangPiece(Piece shingShangPiece) {
		this.shingShangPiece = shingShangPiece;
	}
	
	public Piece getShingShangPieceAlly() {
		return shingShangPieceAlly;
	}

	public void setShingShangPieceAlly(Piece shingShangPieceAlly) {
		this.shingShangPieceAlly = shingShangPieceAlly;
	}

	/**  get the neighbour of one point, with the point and his destination */
	private Point getNeighbourPoint(Piece piece, Point point_to) {

		Point[][] all_points = getPoints();
		Point ret = null;

		int x = piece.getPosition().getN_row();
		int y = piece.getPosition().getN_column();

		// CROSS

		try {
			// CHECK IF DESTINATION IS IN BIG SQUARE
			// UP
			if (all_points[x - 2][y].equals(point_to)) {

				// Check if the piece has a neighbour between itself and the destination
				if (all_points[x - 1][y].isUsed()) {
					ret = all_points[x - 1][y];
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
		}

		try {
			// CHECK IF DESTINATION IS IN BIG SQUARE
			// DOWN
			if (all_points[x + 2][y].equals(point_to)) {

				// Check if the piece has a neighbour between itself and the destination
				if (all_points[x + 1][y].isUsed()) {
					// System.out.println("POSITION POINT A MANGER" + x+1 + "," + y);
					ret = all_points[x + 1][y];
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
		}

		try {
			// CHECK IF DESTINATION IS IN BIG SQUARE
			// LEFT
			if (all_points[x][y - 2].equals(point_to)) {

				// Check if the piece has a neighbour between itself and the destination
				if (all_points[x][y - 1].isUsed()) {
					ret = all_points[x][y - 1];
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
		}

		try {
			// CHECK IF DESTINATION IS IN BIG SQUARE
			// RIGHT
			if (all_points[x][y + 2].equals(point_to)) {

				// Check if the piece has a neighbour between itself and the destination
				if (all_points[x][y + 1].isUsed()) {
					ret = all_points[x][y + 1];
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
		}

// DIAGONALS
		try {
			// CHECK IF DESTINATION IS IN BIG SQUARE
			// TOP LEFT CORNER
			if (all_points[x - 2][y - 2].equals(point_to)) {

				// Check if the piece has a neighbour between itself and the destination
				if (all_points[x - 1][y - 1].isUsed()) {
					ret = all_points[x - 1][y - 1];
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
		}

		try {
			// BOTTOM LEFT CORNER
			if (all_points[x + 2][y - 2].equals(point_to)) {

				// Check if the piece has a neighbour between itself and the destination
				if (all_points[x + 1][y - 1].isUsed()) {
					ret = all_points[x + 1][y - 1];
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
		}

		try {
			// TOP RIGHT CORNER
			if (all_points[x - 2][y + 2].equals(point_to)) {

				// Check if the piece has a neighbour between itself and the destination
				if (all_points[x - 1][y + 1].isUsed()) {
					ret = all_points[x - 1][y + 1];
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
		}

		try {
			// BOTTOM RIGHT CORNER
			if (all_points[x + 2][y + 2].equals(point_to)) {
				
				// Check if the piece has a neighbour between itself and the destination
				if (all_points[x + 1][y + 1].isUsed()) {

					ret = all_points[x + 1][y + 1];

				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
		}

		return ret;

	}

	/**  If ShingShangseq is true, we are in a sequence of shingshang, ( extra turn)*/
	public boolean getShingShangSeq() {
		return shingShangSeq;
	}

	public void setShingShangSeq(Boolean bool) {
		this.shingShangSeq = bool;
	}
	
	/**  If ShingShangseqAlly is true, we are in a sequence of shingshang, ( extra turn)*/
	public boolean getShingShangSeqAlly() {
		return shingShangSeqAlly;
	}

	public void setShingShangSeqAlly(Boolean bool) {
		this.shingShangSeqAlly = bool;
	}

	/** Check if an opponent dragon is on a portal */
	public Tuple<Boolean, String> onPortalCheck() {

		Tuple<Boolean, String> ret = new Tuple<Boolean, String>(false, null);

		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				if (this.getPoints()[x][y].getPiece() != null) {
					if ((this.getPoints()[x][y].getPiece().getType() == "Dragon")
							&& ((x == 1 || x == 8) && (y == 5 || y == 4))) {
						if (x == 1) {

							if (getPoints()[x][y].getPiece().getColor() == colorJ2) {
								ret.setFirst(true);
								ret.setSecond("2");
								System.out.println("+--------------------------------------+\r\n"
										+ "|   Player 2 is on opponent Portal !   |\r\n"
										+ "+--------------------------------------+\r\n"
										);
							}
						}
						if (x == 8) {

							if (getPoints()[x][y].getPiece().getColor() == colorJ1) {
								ret.setFirst(true);
								ret.setSecond("1");
								System.out.println("+--------------------------------------+\r\n"
										+ "|   Player 1 is on opponent Portal !   |\r\n"
										+ "+--------------------------------------+\r\n"
										);
							}
						}
					}
				}

			}
		}

		return ret;
	}

	/** Check if there is dragons left */
	public Tuple<Boolean, String> noDragonCheck() {

		// Check if there is no Dragon left on one side to end the game
		Tuple<Boolean, String> ret = new Tuple<Boolean, String>(false, null);
		Integer player1Dragons = 0;
		Integer player2Dragons = 0;
		Integer sizeArr = 0;

		if (pieces.get(0).getPieces().size() == pieces.get(1).getPieces().size()) {
			sizeArr = pieces.get(0).getPieces().size();
		} else {
			System.out.println("Error size of pieces arrays");
		}

		for (int i = 0; i < sizeArr; i++) {
			if ((pieces.get(0).getPieces().get(i).getType() == "Dragon")
					&& (pieces.get(0).getPieces().get(i).getPieceState() == PieceState.ALIVE)) {

				player1Dragons++;
			}
			if ((pieces.get(1).getPieces().get(i).getType() == "Dragon")
					&& (pieces.get(1).getPieces().get(i).getPieceState() == PieceState.ALIVE)) {

				player2Dragons++;

			}
		}

		if (player1Dragons == 0) {
			ret.setFirst(true);
			ret.setSecond("2");
			System.out.println("+--------------------------------------+\r\n"
					+ "|   Player 1 has no Dragon Left !   |\r\n"
					+ "+--------------------------------------+\r\n"
					);
		} else if (player2Dragons == 0) {
			ret.setFirst(true);
			ret.setSecond("1");
			System.out.println("+--------------------------------------+\r\n"
					+ "|   Player 2 has no Dragon Left !   |\r\n"
					+ "+--------------------------------------+\r\n"
					);
		} else {
		}

		return ret;
	}
	
	/**  Get all the valid moves of one point */
	public ArrayList<Point> getValidMoves(Piece piece){
		ArrayList<Point> emptyArray = new ArrayList<Point>();
		
		if (piece.equals(shingShangPiece)) {
			return emptyArray;
		}
		else if ((piece.equals(shingShangPieceAlly) &&  shingShangSeqAlly ) || (!shingShangSeqAlly)){
			return moveValidator.getValidMoves(piece, getPoints());
		}
		else {	
			return emptyArray;
		}
	}

}
