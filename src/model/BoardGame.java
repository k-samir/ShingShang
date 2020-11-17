package model;

import java.util.ArrayList;

public class BoardGame {

	private final int ROW = 10;
	private final int COLUMN = 10;

	private ArrayList<PieceSet> pieces;
	private Point[][] points;

	private String colorJ1;
	private String colorJ2;
	
	private MoveValidator moveValidator;

	public BoardGame(String colorJ1, String colorJ2) {
		//super();
		
		this.colorJ1 = colorJ1;
		this.colorJ2 = colorJ2;

		pieces = new ArrayList<PieceSet>();
		points = new Point[10][10];

		pieces.add(new PieceSet(colorJ1));
		pieces.add(new PieceSet(colorJ2));
		
		moveValidator = new MoveValidator();

		
		// MODIFIEER POUR APPEL DANS GAME
		//this.init();

	}

	public void init() {
		
		System.out.println("INIT...");

		for (int i = 0; i < 10;i++) {
			for (int j = 0; j < 10;j++) {
				
				if(j%10 == 0) {
					System.out.println("\n");
				}
				
			
				if((i == 1 &&  ( j==4 || j==5) || (i == 8 &&  ( j==4 || j==5)))){
					this.getPoints()[i][j] = new Point(i, j, "Portal");
				}
				
				else if (j==0 || j==9){
					this.getPoints()[i][j] = new Point(i, j, "Nothing");
				}
				else {
					this.getPoints()[i][j] = new Point(i, j, "Standard");
					
				}
				
		
				
			}
		}
		
		// ADDING POINT O,4 -  0,5 - 4,9 -- 5,9
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

	public void movePiece(Piece piece, Point to_point) {
		removePiece(piece.getPosition());
		piece.setPosition(to_point);
		this.getPoints()[to_point.getN_row()][to_point.getN_column()].setPiece(piece);
	}
	
	public void eatPiece(Piece piece, Point point) {
		
	}

	public Boolean placePiece(Piece piece, Point point) {
		
		Boolean ret = false;
		Tuple<Boolean,String> moveData = moveValidator.moveValid(piece, point,getPoints());
		
		// CHECK IF MOVE OK
		if(moveData.getFirst()) {
			
			System.out.println(moveData.getSecond());
			if(moveData.getSecond() == "Jump") {
				movePiece(piece,point);
			}
			else {
				movePiece(piece,point);
			}
			
			
			
			ret = true;
		}
		else {
			ret = false;
			System.out.println("The Move is not allowed, try again");
		}
		
		return ret;
		
	}

	public void removePiece(Point point) {
		point.setPiece(null);
	}
	

}
