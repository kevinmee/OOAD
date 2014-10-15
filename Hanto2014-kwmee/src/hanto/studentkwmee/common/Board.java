/*
 * This is the Board for Hanto
 * 
 * Kevin Mee, WPI A-term 2014
 */

package hanto.studentkwmee.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.studentkwmee.alpha.*;

/**
 */
public class Board {
	
	Map<Coordinate,Piece> boardLayout = new HashMap<Coordinate, Piece>();
	private List<Piece> board;
	
	public Board(){
		
	}
	
	public Board(Board tempBoard) {
		Map< Coordinate, Piece>tempHash = new HashMap<Coordinate, Piece>(boardLayout);
	}
	

	/**
	 * Method clearBoard.
	 */
	public void clearBoard(){
		boardLayout = new HashMap<Coordinate, Piece>();
		board = new ArrayList<Piece>();
	}

	/**
	 * Method isAdjacent.
	 * @param to HantoCoordinate
	
	 * @return boolean */
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
	 * Method getNeighbors.
	 * @param from HantoCoordinate
	 * @return List<Coordinate>
	 */
	public List<Coordinate> getNeighbors(HantoCoordinate from){
		List<Coordinate> list = new ArrayList<Coordinate>();
		
		list.add(new Coordinate(from.getX(), from.getY() + 1));
		list.add(new Coordinate(from.getX(), from.getY() - 1));
		list.add(new Coordinate(from.getX() + 1, from.getY()));
		list.add(new Coordinate(from.getX() - 1, from.getY()));
		list.add(new Coordinate(from.getX() - 1, from.getY() + 1));
		list.add(new Coordinate(from.getX() + 1, from.getY() - 1));
		
		
		return list;
	}
	


	/**
	 * Method add.
	 * @param to HantoCoordinate
	 * @param pieceType Piece
	 */
	public void add(HantoCoordinate to, Piece pieceType) {
		final Coordinate coord = new Coordinate(to.getX(), to.getY());
		boardLayout.put(coord, pieceType);
		
	}
	
	/**
	 * Method movePiece.
	 * @param from HantoCoordinate
	 * @param to HantoCoordinate
	
	 * @throws HantoException */
	public void movePiece( HantoCoordinate from, HantoCoordinate to) throws HantoException{
		if(boardLayout.containsKey(new Coordinate(to.getX(), to.getY()))){
			throw new HantoException("Can't play on top of another piece");
		}
		if( !boardLayout.containsKey(new Coordinate(from.getX(), from.getY()))){
			throw new HantoException("There is no piece to move");
		}
		
		final Piece oldLocation = boardLayout.get(new Coordinate(from));
		final Piece newLocation = new Piece(oldLocation.getType(), oldLocation.getColor(), to);
		
		boardLayout.remove(new Coordinate(from));
		
		add(newLocation.getCoord(), newLocation);
	}

	/**
	 * Method size.
	
	 * @return int */
	public int size() {
		return boardLayout.size();
	}


	/**
	 * Method getPiece.
	 * @param where HantoCoordinate
	
	 * @return Piece */
	public Piece getPiece(HantoCoordinate where) {
		return boardLayout.get(new Coordinate(where.getX(), where.getY()));
	}

	public Set<Entry<Coordinate, Piece>> entrySet() {
		return boardLayout.entrySet();
	}

	public void remove(Coordinate coordinate) {
		boardLayout.remove(coordinate);
		
	}



}
