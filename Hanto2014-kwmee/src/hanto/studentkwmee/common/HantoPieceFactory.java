package hanto.studentkwmee.common;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;


public class HantoPieceFactory {
	private static final HantoPieceFactory INSTANCE = new HantoPieceFactory();

	/**
	 * default private constructor
	 */
	private HantoPieceFactory() {
	}

	/**
	 * @return the instance
	 */
	public static HantoPieceFactory getInstance() {
		return INSTANCE;
	}

	/**
	 * Factory method that returns the appropriately configured Hanto game.
	 *
	 * @param pieceId
	 *            The piece type desired.
	 * @param color
	 *            The color the piece belongs to
	 * @return the Piece instance
	 */
	public Piece makeHantoPiece(HantoPieceType pieceId,
			HantoPlayerColor color) {
		Piece piece = null;
		switch (pieceId) {
		case BUTTERFLY:
			piece = new Piece(HantoPieceType.BUTTERFLY, color);
			break;
		case CRAB:
			piece = new Piece(HantoPieceType.CRAB, color);
			break;
		case CRANE:
			break;
		case DOVE:
			break;
		case HORSE:
			break;
		case SPARROW:
			piece = new Piece(HantoPieceType.SPARROW, color);
			break;
		default:
			break;
		}
		return piece;
	}
}