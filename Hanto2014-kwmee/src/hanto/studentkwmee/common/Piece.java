/*
 * Piece class for Hanto Game
 * 
 * Kevin Mee, WPI A-term 2014
 */

package hanto.studentkwmee.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 */
public class Piece implements HantoPiece{

	private HantoPieceType type;
	private HantoPlayerColor color;
	private HantoCoordinate coord;
	
	/**
	 * Constructor for Piece.
	 * @param type HantoPieceType
	 * @param color HantoPlayerColor
	 * @param coord HantoCoordinate
	 */
	public Piece(HantoPieceType type, HantoPlayerColor color, HantoCoordinate coord){
		this.type = type;
		this.color = color;
		this.coord = coord;
		
	}
		
	/**
	 * Constructor for Piece.
	
	
	 * @param type HantoPieceType
	 * @param color HantoPlayerColor
	 */
	public Piece(HantoPieceType type, HantoPlayerColor color) {
		this.type = type;
		this.color = color;
	}

	public HantoPieceType getType(){
		return type;
	}
	
	public HantoPlayerColor getColor(){
		return color;
	}

	public HantoCoordinate getCoord() {
		return coord;
	}


	

	
	
}
