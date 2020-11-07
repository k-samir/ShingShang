package model;

import java.util.ArrayList;

public class BoardGame {

	private final int ROW = 10;
	private final int COLUMN = 10;

	private ArrayList<PieceSet> pieces;
	private Point[][] points;

	private String colorJ1;
	private String colorJ2;

	public BoardGame(String colorJ1, String colorJ2) {
		super();
		this.colorJ1 = colorJ1;
		this.colorJ2 = colorJ2;

		pieces = new ArrayList<PieceSet>();
		points = new Point[ROW][COLUMN];

		pieces.add(new PieceSet(colorJ1));
		pieces.add(new PieceSet(colorJ2));

		// MODIFIEER POUR APPEL DANS GAME
		init();

	}

	public void init() {

		for (int i = 0; i < this.ROW;) {
			for (int j = 0; j < this.COLUMN;) {
				if ((i != 4 && j != 1) && (i != 5 && j != 1) && (i != 4 && j != 8) && (i != 5 && j != 8)) {
					this.getPoints()[i][j] = new Point(i, j, "Standard");
				} else {
					this.getPoints()[i][j] = new Point(i, j, "Portal");
				}
			}
		}

		this.addDragon1(1, 0);
		this.addDragon1(8, 0);
		this.addLion1(2, 0);
		this.addLion1(1, 1);
		this.addLion1(8, 1);
		this.addLion1(7, 0);
		this.addMonkey1(3, 0);
		this.addMonkey1(6, 0);
		this.addMonkey1(2, 1);
		this.addMonkey1(7, 1);
		this.addMonkey1(1, 2);
		this.addMonkey1(8, 2);

		this.addDragon2(1, 9);
		this.addDragon2(8, 9);
		this.addLion2(1, 8);
		this.addLion2(2, 9);
		this.addLion2(7, 9);
		this.addLion2(8, 8);
		this.addMonkey2(1, 7);
		this.addMonkey2(2, 8);
		this.addMonkey2(3, 9);
		this.addMonkey2(6, 9);
		this.addMonkey2(7, 8);
		this.addMonkey2(8, 7);

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

	public ArrayList<PieceSet> getPieces() {
		return pieces;
	}

	public void setPieces(ArrayList<PieceSet> pieces) {
		this.pieces = pieces;
	}

	public Point[][] getPoints() {
		return points;
	}

	public void setPoints(Point[][] points) {
		this.points = points;
	}

	public void movePiece(Point point1, Point point2) {
		this.getPoints()[point2.getN_row()][point2.getN_column()].setPiece(point1.getPiece());
		point1.setPiece(null);
	}

	public Boolean placePiece(Piece piece, Point point) {
		
		// CHECK IF MOVE OK
		removePiece(piece.getPosition());
		this.getPoints()[point.getN_row()][point.getN_column()].setPiece(piece);
		return true;
	}

	public void removePiece(Point point) {
		point.setPiece(null);
	}

}
