package model;
import java.lang.Math;
import java.util.ArrayList;

public class MoveValidator {

	public Boolean moveValid(Piece piece, Point point_to,Point[][] all_points) {
		// LOGIC
		
		
		//MONKEY
		return isInBigNet(piece,point_to,all_points);
		//all_points[x][y].equals(point_to);
		
	}
	
	public Boolean isInBigSquare(Piece piece,Point point_to,Point[][] all_points) {
		Boolean verification = false;
		int stop_X = piece.getPosition().getN_row() + 3;
		int stop_Y = piece.getPosition().getN_column() + 3;
		
		for(int x = piece.getPosition().getN_row() - 3;x<stop_X;x++) {
			for(int y = piece.getPosition().getN_column() - 3;y<stop_Y;y++) {
				try {
					
				
				if((!all_points[x][y].isUsed()) && all_points[x][y].isUsable()) {
					System.out.println(x + " " + y);
					//if(all_points[x][y].equals(point_to)) {
					//	verification = true;
					//}
				}
			}
			catch(java.lang.ArrayIndexOutOfBoundsException Exception) {}
				
				
			}
		}
		return verification ;
	}
	
	public Boolean isInBigNet(Piece piece,Point point_to,Point[][] all_points) {
		Boolean verification = false;
		
		int stop_X = piece.getPosition().getN_row() + 3;
		int stop_Y = piece.getPosition().getN_column() + 3;
		
		int n_row = piece.getPosition().getN_row();
		int n_col = piece.getPosition().getN_column();
		
		for(int x = piece.getPosition().getN_row() - 3;x<stop_X;x++) {
			for(int y = piece.getPosition().getN_column() - 3;y<stop_Y;y++) {
				try {
				if((Math.abs(x - n_row) < 3 )&& (y == n_col)) {
					System.out.println(x + " " + y);
					if(all_points[x][y].equals(point_to)) {
						verification = true;
					}
				}
				else if((Math.abs(y - n_col) < 3 ) && (x == n_row)) { 
					
					if(all_points[x][y].equals(point_to)) {
						verification = true;
					}
				}
				
				else if(( x == n_row + 1 && y == n_col + 1 ) || ( x == n_row + 2 && y == n_col + 2 ) ||
						( x == n_row - 1 && y == n_col + 1 ) || ( x == n_row - 2 && y == n_col + 2 ) ||
						( x == n_row + 1 && y == n_col - 1 ) || ( x == n_row + 2 && y == n_col - 2 ) ||
						( x == n_row - 1 && y == n_col - 1 ) || ( x == n_row - 2 && y == n_col - 2 ) ) {
					
					if(all_points[x][y].equals(point_to)) {
						verification = true;
					}
					
				}
					
					
				}
				catch(java.lang.ArrayIndexOutOfBoundsException Exception) {}
			}
		}
		
				
		return verification;
	}

	public ArrayList<Point> getValidMoves(Piece piece, ArrayList<Point> points) {
		// LOGIC
		return points;
	}
}
