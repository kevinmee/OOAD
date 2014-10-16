/*
 * HantoPieceFactory for Hanto Game
 * 
 * Kevin Mee, WPI A-term
 */
package hanto.studentkwmee.common;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;


/**
 */
public class HantoPieceFactory {
	private static final HantoPieceFactory INSTANCE = new HantoPieceFactory();


	private HantoPieceFactory() {
		
	}


	public static HantoPieceFactory getInstance() {
		return INSTANCE;
	}


	/**
	 * Method makeHantoPiece.
	 * @param pieceType HantoPieceType
	 * @param color HantoPlayerColor
	 * @return Piece
	 */
	public Piece makeHantoPiece(HantoPieceType pieceType,
			HantoPlayerColor color) {
		Piece piece = null;
		switch (pieceType) {
		case BUTTERFLY:
			piece = new Piece(pieceType, color);
			break;
		case CRAB:
			piece = new Piece(pieceType, color);
			break;
		case HORSE:
			piece = new Piece(pieceType, color);
			break;
		case SPARROW:
			piece = new Piece(pieceType, color);
			break;
		case CRANE:
			break;
		case DOVE:
			break;
		default:
			break;
		}
		return piece;
	}
}