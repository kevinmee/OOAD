package hanto.studentkwmee.alpha;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

public class Piece implements HantoPiece{

	private HantoPieceType type;
	private HantoPlayerColor color;
	
	public Piece(HantoPieceType type, HantoPlayerColor color){
		this.type = type;
		this.color = color;
		
	}
		
	public HantoPieceType getType(){
		return type;
	}
	
	public HantoPlayerColor getColor(){
		return color;
	}
	

	
	
}
