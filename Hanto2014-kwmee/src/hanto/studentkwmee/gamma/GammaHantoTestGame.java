package hanto.studentkwmee.gamma;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoTestGame;
import hanto.common.MoveResult;

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
		 super.initializeBoard(initialPieces);
	}

	@Override
	public void setTurnNumber(int turnNumber) {
		super.setTurnNumber(turnNumber);
		
	}

	@Override
	public void setPlayerMoving(HantoPlayerColor player) {
		super.setPlayerTurn(player);
		
	}
	
}
