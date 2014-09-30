package hanto.studentkwmee.gamma;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoTestGame.PieceLocationPair;
import hanto.common.MoveResult;
import hanto.studentkwmee.alpha.Piece;
import hanto.studentkwmee.common.Board;

public class GammaHantoGame implements HantoGame {

	private Board gameBoard;
	private HantoPlayerColor turnColor;
	private int turnNumber, maxTurn = 20;
	
	public GammaHantoGame(HantoPlayerColor color){
		gameBoard = new Board();
		turnNumber = 0;
		turnColor = color;
	}
	
	
	public boolean isFirstLocation( HantoCoordinate startingLocation){
		if ( startingLocation.getX() == 0 && startingLocation.getY() == 0){
			return true;
		}
		return false;
	}
	
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {

		Piece piece = new Piece( pieceType, turnColor);
		
		// Checks to see if it is blues turn and the location is (0,0)
		if( !isFirstLocation(to)){
			throw new HantoException( "First move must play at (0,0)");
		}
		if( (turnNumber / 2) > maxTurn && gameResult() == MoveResult.OK){
			return MoveResult.DRAW;
		}
		
		// Checks to see if there is a piece to attach to
		else if( !gameBoard.isAdjacent(to)){
			throw new HantoException( "There is no piece next to that one!");
		}
		
		// Adds the piece to the board
		gameBoard.add(to, piece);
				
		// Change player turn
		changeTurn();
		turnNumber ++;
				
		return gameResult();
		
	}
	
	private MoveResult gameResult() {
		MoveResult result;
		if(gameBoard.size() < 12){
			result = MoveResult.OK;
		}
		else
			result = MoveResult.DRAW;
		
		return result;
	}
	
	private void changeTurn() {
		if ( turnColor == HantoPlayerColor.BLUE){
			turnColor = HantoPlayerColor.RED;
		}
		else{
			turnColor = HantoPlayerColor.BLUE;
		}		
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		return gameBoard.getPiece(where);
	}

	@Override
	public String getPrintableBoard() {
		return gameBoard.toString();
	}


	public void setTurnNumber(int turnNum) {
		turnNumber = turnNum;
		
	}


	public void setPlayerTurn(HantoPlayerColor player) {
		turnColor = player;
		
	}


	public void initializeBoard(PieceLocationPair[] initialPieces) {
		for( int i = 0; i < initialPieces.length; i++ ){
			Piece p = new Piece(initialPieces[i].pieceType, initialPieces[i].player);
			gameBoard.add(initialPieces[i].location, p);
		}
	}



}
