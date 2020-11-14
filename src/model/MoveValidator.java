package model;

import java.lang.Math;
import java.util.ArrayList;

public class MoveValidator {

	public Boolean moveValid(Piece piece, Point point_to, Point[][] all_points) {
		// LOGIC
		Boolean ret = false;

		// MONKEY
		if (piece.getType() == "Monkey") {
			ret = isInBigStar(piece, point_to, all_points);
		} else if (piece.getType() == "Lion") {
			ret = isInStar(piece, point_to, all_points);
		}

		return ret;

		// all_points[x][y].equals(point_to);

	}

	public Boolean isInBigSquare(Piece piece, Point point_to, Point[][] all_points) {
		Boolean verification = false;
		int stop_X = piece.getPosition().getN_row() + 3;
		int stop_Y = piece.getPosition().getN_column() + 3;

		for (int x = piece.getPosition().getN_row() - 3; x < stop_X; x++) {
			for (int y = piece.getPosition().getN_column() - 3; y < stop_Y; y++) {
				try {

					if ((!all_points[x][y].isUsed()) && all_points[x][y].isUsable()) {
						System.out.println(x + " " + y);
						// if(all_points[x][y].equals(point_to)) {
						// verification = true;
						// }
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException Exception) {
				}

			}
		}
		return verification;
	}

	public Boolean isInBigStar(Piece piece, Point point_to, Point[][] all_points) {
		Boolean verification = false;

		int stop_X = piece.getPosition().getN_row() + 3;
		int stop_Y = piece.getPosition().getN_column() + 3;

		int n_row = piece.getPosition().getN_row();
		int n_col = piece.getPosition().getN_column();

		for (int x = piece.getPosition().getN_row() - 3; x < stop_X; x++) {
			for (int y = piece.getPosition().getN_column() - 3; y < stop_Y; y++) {
				try {

					// VERTICAL CHECK
					if ((Math.abs(x - n_row) < 3) && (y == n_col)) {
						System.out.println(x + " " + y);
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

	public Boolean isInStar(Piece piece, Point point_to, Point[][] all_points) {
		Boolean verification = false;

		int stop_X = piece.getPosition().getN_row() + 2;
		int stop_Y = piece.getPosition().getN_column() + 2;

		int n_row = piece.getPosition().getN_row();
		int n_col = piece.getPosition().getN_column();

		for (int x = piece.getPosition().getN_row() - 2; x < stop_X; x++) {
			for (int y = piece.getPosition().getN_column() - 2; y < stop_Y; y++) {
				try {

					// VERTICAL CHECK
					if ((Math.abs(x - n_row) < 2) && (y == n_col)) {
						System.out.println(x + " " + y);
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

	public ArrayList<Point> getValidMoves(Piece piece, ArrayList<Point> points) {
		// LOGIC
		return points;
	}
}
