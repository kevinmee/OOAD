/*
 * This is the Board for Hanto
 * 
 * Kevin Mee, WPI A-term 2014
 */

package hanto.studentkwmee.common;

import java.util.HashMap;
import java.util.Map;

import hanto.common.HantoCoordinate;
import hanto.studentkwmee.alpha.*;

/**
 */
public class Board {

	Map<Coordinate,Piece> boardLayout = new HashMap<Coordinate, Piece>();
	
	/**
	 * Method clearBoard.
	 */
	public void clearBoard(){
		boardLayout = new HashMap<Coordinate, Piece>();
	}

	/**
	 * Method isAdjacent.
	 * @param to HantoCoordinate
	 * @return boolean
	 */
	public boolean isAdjacent(HantoCoordinate to) {
		boolean result = false;
		
		if( boardLayout.size() < 1){
			result = true;
		}
		
		switch(to.getX()){
		case -1:
			if (to.getY() == 1 || to.getY() == 0){
				result = true;
			}
			break;
		case 0:
			if ( to.getY() == 1 || to.getY() == -1){
				result = true;
			}
			break;
		case 1:
			if ( to.getY() == -1 || to.getY() == 0){
				result = true;
			}
			break;
		default:
			break;
		}
		return result;
	}

	/**
	 * Method add.
	 * @param to HantoCoordinate
	 * @param pieceType Piece
	 */
	public void add(HantoCoordinate to, Piece pieceType) {
		Coordinate coord = new Coordinate(to.getX(), to.getY());
		boardLayout.put(coord, pieceType);
		
	}

	/**
	 * Method size.
	 * @return int
	 */
	public int size() {
		return boardLayout.size();
	}


	/**
	 * Method getPiece.
	 * @param where HantoCoordinate
	 * @return Piece
	 */
	public Piece getPiece(HantoCoordinate where) {
		return boardLayout.get(new Coordinate(where.getX(), where.getY()));
	}


}
