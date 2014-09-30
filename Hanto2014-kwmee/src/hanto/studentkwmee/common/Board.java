package hanto.studentkwmee.common;

import java.util.HashMap;

import hanto.common.HantoCoordinate;
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
		return boardLayout.get(new Coordinate(where.getX(), where.getY()));
	}


}
