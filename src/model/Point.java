package model;

public class Point {

	private Integer n_row;
	private Integer n_column;
	private PointType point_type;
	private Piece piece;

	public Point(Integer x, Integer y, String point_type) {
		//super();
		this.n_row = x;
		this.n_column = y;

		if (point_type.equals("Portal")) {
			this.point_type = PointType.PORTAL;

		} else if (point_type.equals("Standard")) {
			this.point_type = PointType.STANDARD;

		} else {
			this.point_type = PointType.NOTHING;
		}

		piece = null;
	}

	public Boolean isUsed() {
		return (piece != null);
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	public Integer getN_row() {
		return n_row;
	}

	public Integer getN_column() {
		return n_column;
	}

	public PointType getPoint_type() {
		return point_type;
	}
//
//	public void setPoint_type(String point_type) {
//		if (point_type.equals("Portal")) {
//			this.point_type = PointType.PORTAL;
//
//		} else if (point_type.equals("Standard")) {
//			this.point_type = PointType.STANDARD;
//
//		} else {
//			this.point_type = PointType.NOTHING;
//		}
//	}

}
