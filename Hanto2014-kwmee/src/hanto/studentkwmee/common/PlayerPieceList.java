/*
 * Player piece collection
 * 
 * Kevin Mee, WPI A-term 2014
 */

package hanto.studentkwmee.common;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;


/**
 */
public class PlayerPieceList {
	private int blueButterfly, blueCrabs, blueHorses, blueSparrows, redButterfly, redCrabs, redHorses, redSparrows;

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
		blueButterfly = butterflies;
		blueCrabs = crabs;
		blueHorses = horses;
		blueSparrows = sparrows;
		redButterfly = butterflies;
		redCrabs = crabs;
		redHorses = horses;
		redSparrows = sparrows;
	}


	/**
	 * Method usePiece.
	 * @param playerColor HantoPlayerColor
	 * @param pieceType HantoPieceType
	 */
	public void usePiece(HantoPlayerColor playerColor, HantoPieceType pieceType) {
		
		switch(playerColor){
		case BLUE:
			switch(pieceType){
			case BUTTERFLY:
				blueButterfly--;
				break;
			case CRAB:
				blueCrabs--;
				break;
			case HORSE:
				blueHorses--;
				break;
			case SPARROW:
				blueSparrows--;
				break;
			default:
				break;
			}
			break;
		case RED:
			switch(pieceType){
			case BUTTERFLY:
				redButterfly--;
				break;
			case CRAB:
				redCrabs--;
				break;
			case HORSE:
				redHorses--;
				break;
			case SPARROW:
				redSparrows--;
				break;
			default:
				break;
			}
			break;
		}
		
	}

	/**
	 * Method isEmpty.
	 * @param playerColor HantoPlayerColor
	 * @return boolean
	 */
	public boolean isEmpty(HantoPlayerColor playerColor) {
		boolean isEmpty = false;
		switch (playerColor) {
		case RED:
			isEmpty = (redButterfly == 0) && (redCrabs == 0)
					&& (redHorses == 0) && (redSparrows == 0);
			break;
		case BLUE:
			isEmpty = (blueButterfly == 0) && (blueCrabs == 0)
					&& (blueHorses == 0) && (blueSparrows == 0);
		default:
			break;
		}
		return isEmpty;
	}

	/**
	 * Method hasPiece.
	 * @param playerColor HantoPlayerColor
	 * @param pieceType HantoPieceType
	 * @return boolean
	 */
	public boolean hasPiece(HantoPlayerColor playerColor,
			HantoPieceType pieceType) {
		boolean hasPiece = false;
		switch (pieceType) {
		case BUTTERFLY:
			if (playerColor == HantoPlayerColor.BLUE){
				hasPiece = blueButterfly > 0;
			}
			else{
				hasPiece = redButterfly > 0;
			}
			break;
		case CRAB:
			if (playerColor == HantoPlayerColor.BLUE){
				hasPiece = blueCrabs > 0;
			}
			else{
				hasPiece = redCrabs > 0;
			}
			break;
		case HORSE:
			if (playerColor == HantoPlayerColor.BLUE){
				hasPiece = blueHorses > 0;
			}
			else{
				hasPiece = redHorses > 0;
			}
			break;
		case SPARROW:
			if (playerColor == HantoPlayerColor.BLUE){
				hasPiece = blueSparrows > 0;
			}
			else{
				hasPiece = redSparrows > 0;
			}
		default:
			break;
		}
		return hasPiece;
	}

}
