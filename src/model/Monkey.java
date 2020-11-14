package model;

public class Monkey implements Piece {

	private String type;
	private Integer size;
	private Point position;
	private PieceState pieceState;
	private String color;

	public String getColor() {
		return color;
	}

	// USEFUL IF CAPTURED OR CHANGING GAME RULES IN THE FUTUR
	public void setColor(String color) {
		this.color = color;
	}

	public Monkey(Point position, String color) {
		//super();
		this.type = "Monkey";
		this.size = 1;
		this.position = position;
		this.pieceState = PieceState.ALIVE;
		this.color = color;

	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public Integer getSize() {
		return size;
	}

	@Override
	public void setSize(Integer size) {
		this.size = size;
	}

	@Override
	public PieceState getPieceState() {
		return pieceState;
	}

	@Override
	public void setPieceState(String pieceState) {
		if (pieceState.equals("Alive")) {
			this.pieceState = PieceState.ALIVE;

		} else if (pieceState.equals("Dead")) {
			this.pieceState = PieceState.DEAD;

		} else {
			this.pieceState = PieceState.NOTHING;
		}
	}

	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public void setPosition(Point position) {
		this.position = position;
	}

	@Override
	public Boolean isDead() {
		return this.pieceState == PieceState.DEAD;
	}

	@Override
	public Point[] valideMoves() {
		return null;
	}

}
