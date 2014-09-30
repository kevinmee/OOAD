package hanto.studentkwmee.gamma;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentkwmee.alpha.Piece;
import hanto.studentkwmee.common.Board;

public class GammaHantoGame implements HantoGame {

	private Board gameBoard;
	protected HantoPlayerColor turnColor;
	protected int turnNumber, maxTurn = 20;
	
	public GammaHantoGame(HantoPlayerColor color){
		setGameBoard(new Board());
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
		else if( !getGameBoard().isAdjacent(to)){
			throw new HantoException( "There is no piece next to that one!");
		}
		
		// Adds the piece to the board
		getGameBoard().add(to, piece);
				
		// Change player turn
		changeTurn();
		turnNumber ++;
				
		return gameResult();
		
	}
	
	private MoveResult gameResult() {
		MoveResult result;
		if(getGameBoard().size() < 12){
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
		return getGameBoard().getPiece(where);
	}

	@Override
	public String getPrintableBoard() {
		return getGameBoard().toString();
	}

	public Board getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(Board gameBoard) {
		this.gameBoard = gameBoard;
	}

	public void clearGameBoard(){
		gameBoard.clearBoard();
	}


}
