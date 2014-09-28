package hanto.studentkwmee.common;

import java.util.ArrayList;

import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.studentkwmee.alpha.*;

public class Board {

	ArrayList<Piece> boardLayout;
	
	public void clearBoard(){
		boardLayout = new ArrayList<Piece>();
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

	public void add(Piece piece) {
		boardLayout.add(piece);
		
	}

	public int size() {
		return boardLayout.size();
	}

	public Piece getPiece(HantoCoordinate where) {
		for (Piece p : boardLayout){
			if( new Coordinate(where).equals(p.getCoord())){
				return p;
			}
		}
		return null;
	}
	
}
