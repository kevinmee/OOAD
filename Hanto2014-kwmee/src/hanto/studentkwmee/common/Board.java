package hanto.studentkwmee.common;

import java.util.HashMap;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPieceType;
import hanto.studentkwmee.alpha.*;

public class Board {

	HashMap<Coordinate, Piece> boardLayout = new HashMap<Coordinate, Piece>();
	
	public void clearBoard(){
		boardLayout = new HashMap<Coordinate, Piece>();
	}

	public boolean isAdjacent(HantoCoordinate to) {
		if( boardLayout.size() < 1){
			return true;
		}
		
		switch(to.getX()){
		case -1:
			if (to.getY() == 1 || to.getY() == 0){
				return true;
			}
			break;
		case 0:
			if ( to.getY() == 1 || to.getY() == -1){
				return true;
			}
			break;
		case 1:
			if ( to.getY() == -1 || to.getY() == 0){
				return true;
			}
			break;
		default:
			break;
		}
		return false;
	}

	public void add(HantoCoordinate to, Piece pieceType) {
		Coordinate coord = new Coordinate(to.getX(), to.getY());
		boardLayout.put(coord, pieceType);
		
	}

	public int size() {
		return boardLayout.size();
	}


	public Piece getPiece(HantoCoordinate where) {
		if(boardLayout.containsKey(new Coordinate(where.getX(), where.getY()))){
			return boardLayout.get(where);
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((boardLayout == null) ? 0 : boardLayout.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (boardLayout == null) {
			if (other.boardLayout != null)
				return false;
		} else if (!boardLayout.equals(other.boardLayout))
			return false;
		return true;
	}
	
	

}
