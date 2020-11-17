package model;

public interface Piece {

	public String getType();

	public void setType(String type);

	public Integer getSize();

	public Boolean isDead();

	public Point[] valideMoves();

	public void setSize(Integer size);

	public PieceState getPieceState();

	public void setPieceState(String pieceState);

	public Point getPosition();

	public void setPosition(Point position);
	
	public String getColor();
	
	public void killPiece();

}
