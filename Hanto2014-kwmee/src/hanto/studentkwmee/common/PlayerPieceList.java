/*
 * Player piece collection
 * 
 * Kevin Mee, WPI A-term 2014
 */

package hanto.studentkwmee.common;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;

/**
 * @author tcarmstrong klbrann
 */
public class PlayerPieceList {
	private int numButterfliesBlue;
	private int numCrabsBlue;
	private int numHorsesBlue;
	private int numSparrowsBlue;
	private int numButterfliesRed;
	private int numCrabsRed;
	private int numHorsesRed;
	private int numSparrowsRed;

	/**
	 * Creates a HantoPieceInventory which stores the number of each of type of
	 * piece available to both the Red player and Blue player in a HantoGame.
	 * 
	 * @param butterflies
	 *            - number of butterflies each player may use
	 * @param crabs
	 *            - number of crabs each player may use
	 * @param horses
	 *            - number of horses each player may use
	 * @param sparrows
	 *            - number of sparrows each player may use
	 */
	public PlayerPieceList(int butterflies, int crabs, int horses,
			int sparrows) {
		numButterfliesBlue = butterflies;
		numCrabsBlue = crabs;
		numHorsesBlue = horses;
		numSparrowsBlue = sparrows;
		numButterfliesRed = butterflies;
		numCrabsRed = crabs;
		numHorsesRed = horses;
		numSparrowsRed = sparrows;
	}

	/**
	 * Removes one of the given player's piece's of the given piece type from
	 * the inventory.
	 * 
	 * @param pieceType
	 *            - type of piece player is using
	 */
	public void usePiece(HantoPlayerColor playerColor, HantoPieceType pieceType) {
		switch (pieceType) {
		case BUTTERFLY:
			if (playerColor == HantoPlayerColor.BLUE)
				numButterfliesBlue--;
			else
				numButterfliesRed--;
			break;
		case CRAB:
			if (playerColor == HantoPlayerColor.BLUE)
				numCrabsBlue--;
			else
				numCrabsRed--;
			break;
		case HORSE:
			if (playerColor == HantoPlayerColor.BLUE)
				numHorsesBlue--;
			else
				numHorsesRed--;
			break;
		case SPARROW:
			if (playerColor == HantoPlayerColor.BLUE)
				numSparrowsBlue--;
			else
				numSparrowsRed--;
		default:
			break;
		}
	}

	/**
	 * Determines if the given player has the given piece type available to him
	 * or her to be used.
	 * 
	 * @param playerColor
	 *            - color of Player whose inventory to check
	 * @param pieceType
	 *            - type of piece to check in inventory
	 * @return true if a piece of the given type is available
	 */
	public boolean hasPiece(HantoPlayerColor playerColor,
			HantoPieceType pieceType) {
		boolean hasPiece = false;
		switch (pieceType) {
		case BUTTERFLY:
			if (playerColor.equals(HantoPlayerColor.BLUE))
				hasPiece = numButterfliesBlue > 0;
			else
				hasPiece = numButterfliesRed > 0;
			break;
		case CRAB:
			if (playerColor.equals(HantoPlayerColor.BLUE))
				hasPiece = numCrabsBlue > 0;
			else
				hasPiece = numCrabsRed > 0;
			break;
		case HORSE:
			if (playerColor.equals(HantoPlayerColor.BLUE))
				hasPiece = numHorsesBlue > 0;
			else
				hasPiece = numHorsesRed > 0;
			break;
		case SPARROW:
			if (playerColor.equals(HantoPlayerColor.BLUE))
				hasPiece = numSparrowsBlue > 0;
			else
				hasPiece = numSparrowsRed > 0;
		default:
			break;
		}
		return hasPiece;
	}

	public boolean isEmpty(HantoPlayerColor playerColor) {
		boolean isEmpty = false;
		switch (playerColor) {
		case RED:
			isEmpty = (numButterfliesRed == 0) && (numCrabsRed == 0)
					&& (numHorsesRed == 0) && (numSparrowsRed == 0);
			break;
		case BLUE:
			isEmpty = (numButterfliesBlue == 0) && (numCrabsBlue == 0)
					&& (numHorsesBlue == 0) && (numSparrowsBlue == 0);
		default:
			break;
		}
		return isEmpty;
	}
}
