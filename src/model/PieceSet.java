package model;

import java.util.ArrayList;

public class PieceSet {
	private ArrayList<Piece> pieces;
	private String color;

	public PieceSet(String color) {
		//super();
		pieces = new ArrayList<Piece>();
		this.color = color;

	}

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void addPiece(Piece piece) {
		pieces.add(piece);
	}
	
	



}
