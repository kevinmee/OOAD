package hanto.studentkwmee.alpha;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

public class Piece implements HantoPiece{

	private HantoPieceType type;
	private HantoPlayerColor color;
	private HantoCoordinate coord;
	
	public Piece(HantoPieceType type, HantoPlayerColor color, HantoCoordinate coord){
		this.type = type;
		this.color = color;
		this.coord = coord;
		
	}
		
	public Piece(HantoPieceType pieceType, HantoPlayerColor turnColor) {
		this.type = type;
		this.color = color;
	}

	public HantoPieceType getType(){
		return type;
	}
	
	public HantoPlayerColor getColor(){
		return color;
	}

	public HantoCoordinate getCord() {
		return coord;
	}
	

	
	
}
