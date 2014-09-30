package hanto.studentkwmee.gamma;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoTestGame;
import hanto.common.MoveResult;
import hanto.studentkwmee.alpha.Piece;

public class GammaHantoTestGame extends GammaHantoGame implements HantoTestGame{

	public GammaHantoTestGame(HantoPlayerColor movesFirst){
		super(movesFirst); 
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		return super.makeMove(pieceType, from, to);
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		return super.getPieceAt(where);
	}

	@Override
	public String getPrintableBoard() {
		return super.getPrintableBoard();
	}

	@Override
	public void initializeBoard(PieceLocationPair[] initialPieces) {
		clearGameBoard();
		for( int i = 0; i < initialPieces.length; i++ ){
			Piece p = new Piece(initialPieces[i].pieceType, initialPieces[i].player);
			getGameBoard().add(initialPieces[i].location, p);
		}
	}

	@Override
	public void setTurnNumber(int turnNumber) {
		super.turnNumber = turnNumber;
	}

	@Override
	public void setPlayerMoving(HantoPlayerColor player) {
		super.turnColor = player;
		
	}
	
}
