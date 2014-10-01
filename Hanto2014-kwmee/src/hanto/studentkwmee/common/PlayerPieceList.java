/*
 * Player piece collection
 * 
 * Kevin Mee, WPI A-term 2014
 */

package hanto.studentkwmee.common;


import java.util.List;

import hanto.common.HantoPlayerColor;
import hanto.studentkwmee.alpha.Piece;

/**
 */
public class PlayerPieceList{

	private Piece[] pieces;
	private List<Piece> blueCollection;
	private List<Piece> redCollection;
	
	
	/**
	 * Constructor for PlayerPieceList.
	 * @param color HantoPlayerColor
	 * @param pieceList Piece[]
	 */
	public PlayerPieceList(HantoPlayerColor color, Piece[] pieceList){
		pieces = pieceList;
		addToCollection();
	}
	
	/**
	 * Method getRemainingPieces.
	 * @param color HantoPlayerColor
	 * @return List<Piece>
	 */
	public List<Piece> getRemainingPieces(HantoPlayerColor color){
		List<Piece> collection = blueCollection;
		if( color == HantoPlayerColor.RED){
			collection = redCollection;
		}
		else{
			collection = blueCollection;
		}
		return collection;
	}
	
	/**
	 * Method removePiece.
	 * @param piece Piece
	 */
	public void removePiece(Piece piece){
		if(piece.getColor() == HantoPlayerColor.RED){
			redCollection.remove(piece);
		}
		else{
			blueCollection.remove(piece);
		}
	}
	

	/**
	 * Method addToCollection.
	 */
	public void addToCollection(){
		for( int i = 0; i < pieces.length; i++){
			blueCollection.add(pieces[i]);
			redCollection.add(pieces[i]);
		}
	}
	
}
