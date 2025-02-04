package model;

import java.lang.Math;
import java.util.ArrayList;


/**
 * MoveValidator class : the class validate all the move of the player 
 * before moving
 * @author Samir KAMAR
 *
 */
public class MoveValidator {

	private String jumpType = "";
	
	public Tuple<Boolean, String> moveValid(Piece piece, Point point_to, Point[][] all_points) {

		

		Tuple<Boolean, String> ret = new Tuple<Boolean, String>(false, "");

		// MOVEMENT LOGIC
		// MONKEY
		if (piece.getType() == "Monkey") {
			if (hasNeighbour(piece, point_to, all_points)) {
				ret.setFirst(canJump(piece, point_to, all_points));
				ret.setSecond(jumpType);
				jumpType = "";
			}

			else {
				ret.setFirst(isInBigStar(piece, point_to, all_points));
				ret.setSecond("Direct");
			}
		} else if (piece.getType() == "Lion") {
			if (hasNeighbour(piece, point_to, all_points)) {
				ret.setFirst(canJump(piece, point_to, all_points));
				ret.setSecond(jumpType);
				jumpType = "";
			} else {
				ret.setFirst(isInStar(piece, point_to, all_points));
				
				ret.setSecond("Direct");
			}
		} else if (piece.getType() == "Dragon") {
			
			ret.setFirst(canJump(piece, point_to, all_points));
			ret.setSecond(jumpType);
			jumpType = "";

		} else {
		}

		return ret;

	}
	

	
	private boolean hasNeighbour(Piece piece, Point point_to, Point[][] all_points) {
		boolean ret = false;

		int x = piece.getPosition().getN_row();
		int y = piece.getPosition().getN_column();
//CROSS
		
		try {
			// CHECK IF DESTINATION IS IN BIG SQUARE
			// TOP LEFT CORNER
			if (all_points[x - 2][y].equals(point_to)) {

				// Check if the piece has a neighbour between itself and the destination
				if (all_points[x - 1][y].isUsed()) {
					ret = true;
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
		}
		
		try {
			// CHECK IF DESTINATION IS IN BIG SQUARE
			// TOP LEFT CORNER
			if (all_points[x + 2][y].equals(point_to)) {

				// Check if the piece has a neighbour between itself and the destination
				if (all_points[x + 1][y].isUsed()) {
					ret = true;
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
		}
		
		try {
			// CHECK IF DESTINATION IS IN BIG SQUARE
			// TOP LEFT CORNER
			if (all_points[x][y -2].equals(point_to)) {

				// Check if the piece has a neighbour between itself and the destination
				if (all_points[x][y-1].isUsed()) {
					ret = true;
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
		}
		

		try {
			// CHECK IF DESTINATION IS IN BIG SQUARE
			// TOP LEFT CORNER
			if (all_points[x][y+2].equals(point_to)) {

				// Check if the piece has a neighbour between itself and the destination
				if (all_points[x][y+1].isUsed()) {
					ret = true;
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
					ret = true;
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
		}

		try {
			// BOTTOM LEFT CORNER
			if (all_points[x + 2][y - 2].equals(point_to)) {

				// Check if the piece has a neighbour between itself and the destination
				if (all_points[x + 1][y - 1].isUsed()) {
					ret = true;
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
		}

		try {
			// TOP RIGHT CORNER
			if (all_points[x - 2][y + 2].equals(point_to)) {

				// Check if the piece has a neighbour between itself and the destination
				if (all_points[x - 1][y + 1].isUsed()) {
					ret = true;
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
		}

		try {
			// BOTTOM RIGHT CORNER
			if (all_points[x + 2][y + 2].equals(point_to)) {
				
				// Check if the piece has a neighbour between itself and the destination
				if (all_points[x + 1][y + 1].isUsed()) {

					ret = true;

				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
		}

		return ret;

	}

	/**  Get the neigboursize  to know if i can eat him or not */
	private Integer getNeighbourSize(Piece piece,Integer x, Integer y, Point[][] all_points) {
		if(all_points[x][y].getPiece().getColor() == piece.getColor()) {
			jumpType = "AllyJump";
		}
		else {
			jumpType = "EnnemyJump";
		}
		
		return all_points[x][y].getPiece().getSize();
	}

	private boolean canJump(Piece piece, Point point_to, Point[][] all_points) {

		boolean canJump = false;

		int x = piece.getPosition().getN_row();
		int y = piece.getPosition().getN_column();

		
			// CHECK IF DESTINATION IS IN BIG SQUARE
		
			//UP
			try {
			if (all_points[x - 2][y].equals(point_to)) {

				// Check if the piece has a neighbour between itself and the destination
				if (all_points[x - 1][y].isUsed()) {
					// Check if piece canjump over smaller piece or equal size
					if (getNeighbourSize(piece,x - 1, y, all_points) <= piece.getSize()) {
						
						canJump = true;
					}

				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
		}
			
			//DOWN
			try {
				if (all_points[x  + 2][y].equals(point_to)) {

					// Check if the piece has a neighbour between itself and the destination
					if (all_points[x + 1][y].isUsed()) {
						// Check if piece canjump over smaller piece or equal size
						if (getNeighbourSize(piece,x + 1, y, all_points) <= piece.getSize()) {
							
							canJump = true;
						}

					}
				}
			} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
			}
			
			
			// RIGHT
			try {
				if (all_points[x][y + 2].equals(point_to)) {

					// Check if the piece has a neighbour between itself and the destination
					if (all_points[x][y + 1].isUsed()) {
						// Check if piece canjump over smaller piece or equal size
						if (getNeighbourSize(piece,x, y+1, all_points) <= piece.getSize()) {
							
							canJump = true;
						}

					}
				}
			} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
			}
			
			// LEFT
			try {
				if (all_points[x][y - 2].equals(point_to)) {

					// Check if the piece has a neighbour between itself and the destination
					if (all_points[x][y - 1].isUsed()) {
						// Check if piece canjump over smaller piece or equal size
						if (getNeighbourSize(piece,x, y-1, all_points) <= piece.getSize()) {
							
							canJump = true;
						}

					}
				}
			} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
			}
			
			
		try {
			
			// TOP LEFT CORNER
			if (all_points[x - 2][y - 2].equals(point_to)) {

				// Check if the piece has a neighbour between itself and the destination
				if (all_points[x - 1][y - 1].isUsed()) {
					// Check if piece canjump over smaller piece or equal size
					if (getNeighbourSize(piece,x - 1, y - 1, all_points) <= piece.getSize()) {
						
						canJump = true;
					}

				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
		}

		try {
			// BOTTOM LEFT CORNER
			if (all_points[x + 2][y - 2].equals(point_to)) {

				// Check if the piece has a neighbour between itself and the destination
				if (all_points[x + 1][y - 1].isUsed()) {

					// Check if piece canjump over smaller piece or equal size
					if (getNeighbourSize(piece,x + 1, y - 1, all_points) <= piece.getSize()) {
						canJump = true;
					}
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
		}

		try {
			// TOP RIGHT CORNER
			if (all_points[x - 2][y + 2].equals(point_to)) {

				// Check if the piece has a neighbour between itself and the destination
				if (all_points[x - 1][y + 1].isUsed()) {

					// Check if piece canjump over smaller piece or equal size
					if (getNeighbourSize(piece,x - 1, y + 1, all_points) <= piece.getSize()) {
						canJump = true;
					}
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
		}

		try {
			// BOTTOM RIGHT CORNER
			if (all_points[x + 2][y + 2].equals(point_to)) {
				
				// Check if the piece has a neighbour between itself and the destination
				if (all_points[x + 1][y + 1].isUsed()) {
				
					// Check if piece canjump over smaller piece or equal size
					if (getNeighbourSize(piece,x + 1, y + 1, all_points) <= piece.getSize()) {
						canJump = true;
					}
				}
			}
		} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
		}

		return canJump;

	}



	/** Check in all the vertical, horizontal and diagonal point ( 1 radius point away and 2 radius points away )  */
	private boolean isInBigStar(Piece piece, Point point_to, Point[][] all_points) {
		boolean verification = false;

		int stop_X = piece.getPosition().getN_row() + 3;
		int stop_Y = piece.getPosition().getN_column() + 3;

		int n_row = piece.getPosition().getN_row();
		int n_col = piece.getPosition().getN_column();

		for (int x = piece.getPosition().getN_row() - 3; x < stop_X; x++) {
			for (int y = piece.getPosition().getN_column() - 3; y < stop_Y; y++) {
				try {

					// VERTICAL CHECK
					if ((Math.abs(x - n_row) < 3) && (y == n_col)) {
						//System.out.println(x + " " + y);
						if (all_points[x][y].equals(point_to)) {
							verification = true;
						}
					}
					// HORIZONTAL CHECK
					else if ((Math.abs(y - n_col) < 3) && (x == n_row)) {

						if (all_points[x][y].equals(point_to)) {
							verification = true;
						}
					}

					// DIAGONAL CHECK
					else if ((x == n_row + 1 && y == n_col + 1) || (x == n_row + 2 && y == n_col + 2)
							|| (x == n_row - 1 && y == n_col + 1) || (x == n_row - 2 && y == n_col + 2)
							|| (x == n_row + 1 && y == n_col - 1) || (x == n_row + 2 && y == n_col - 2)
							|| (x == n_row - 1 && y == n_col - 1) || (x == n_row - 2 && y == n_col - 2)) {

						if (all_points[x][y].equals(point_to)) {
							verification = true;
						}

					}

				} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
				}
			}
		}

		return verification;
	}

	/** Check in all the vertical, horizontal and diagonal point ( only 1 radius point away )  */
	private boolean isInStar(Piece piece, Point point_to, Point[][] all_points) {
		boolean verification = false;

		int stop_X = piece.getPosition().getN_row() + 2;
		int stop_Y = piece.getPosition().getN_column() + 2;

		int n_row = piece.getPosition().getN_row();
		int n_col = piece.getPosition().getN_column();

		for (int x = piece.getPosition().getN_row() - 2; x < stop_X; x++) {
			for (int y = piece.getPosition().getN_column() - 2; y < stop_Y; y++) {
				try {

					// VERTICAL CHECK
					if ((Math.abs(x - n_row) < 2) && (y == n_col)) {
						//System.out.println(x + " " + y);
						if (all_points[x][y].equals(point_to)) {
							verification = true;
						}
					}
					// HORIZONTAL CHECK
					else if ((Math.abs(y - n_col) < 2) && (x == n_row)) {

						if (all_points[x][y].equals(point_to)) {
							verification = true;
						}
					}

					// DIAGONAL CHECK
					else if ((x == n_row + 1 && y == n_col + 1) || (x == n_row - 1 && y == n_col + 1)
							|| (x == n_row - 2 && y == n_col + 2) || (x == n_row + 1 && y == n_col - 1)
							|| (x == n_row - 1 && y == n_col - 1)) {

						if (all_points[x][y].equals(point_to)) {
							verification = true;
						}

					}

				} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
				}
			}
		}

		return verification;
	}

	/** checking in all the points there is a valid move and adding it to the arraylist  */
	public ArrayList<Point> getValidMoves(Piece piece,  Point[][] all_points) {
		ArrayList<Point> all_valid_moves = new ArrayList<Point>();
		
		for(var i = 0; i < all_points.length; i++){
		    for(var j = 0; j < all_points[i].length; j++){
		    	if(moveValid(piece, all_points[i][j], all_points).getFirst()) {
		    		if((!all_points[i][j].isUsed()) && (all_points[i][j].isUsable())) {
		    			all_valid_moves.add(all_points[i][j]);
		    			
		    		}
		    	}
		    }
		}
				
		return all_valid_moves;
	}
}
