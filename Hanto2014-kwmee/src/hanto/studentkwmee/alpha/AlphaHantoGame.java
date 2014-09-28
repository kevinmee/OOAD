package hanto.studentkwmee.alpha;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentkwmee.common.Board;

public class AlphaHantoGame implements HantoGame{

	private Board gameBoard;
	private HantoPlayerColor turn;
	
	public AlphaHantoGame(){
		gameBoard = new Board();
		turn = HantoPlayerColor.BLUE;
	}
	
	public void initialize(HantoPlayerColor startingPlayer){
		gameBoard.clearBoard();
		turn = startingPlayer;
	}
	
	public boolean isFirstLocation( HantoCoordinate startingLocation) throws HantoException{
		if ( isBlue( turn) && !(startingLocation.getX() == 0 && startingLocation.getY() == 0)){
			throw new HantoException( "Blue must play a butterfly at (0,0)");
		}
		return true;
	}
	
	public boolean isBlue(HantoPlayerColor color) throws HantoException{
		if(color != HantoPlayerColor.BLUE){
			throw new HantoException("Piece is not blue!");
		}
		return true;
	}
	
	
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		
		// Checks to see that the piece is not something other than butterfly
		if( pieceType != HantoPieceType.BUTTERFLY){
			throw new HantoException("The piece should be a butterfly!");
		}
		
		// Checks to see if it is blues turn and the location is (0,0)
		if( isFirstLocation(to)){
			changeTurn();
			return MoveResult.OK;
		}
		
		// Checks to see if there is a piece to attach to
		if( !gameBoard.isAdjacent(to)){
			throw new HantoException( "There is no piece next to that one!");
		}
		
		// Adds the piece to the board
		gameBoard.add(new Piece( pieceType, turn, to));
		
		// Change player turn
		changeTurn();
		
		return gameState();
		
	}

	private MoveResult gameState() {
		MoveResult result;
		if(gameBoard.size() < 2){
			result = MoveResult.OK;
		}
		else
			result = MoveResult.DRAW;
		
		return result;
	}

	private void changeTurn() {
		if ( turn == HantoPlayerColor.BLUE){
			turn = HantoPlayerColor.RED;
		}
		turn = HantoPlayerColor.BLUE;
		
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		HantoPiece p = (HantoPiece) gameBoard.getPiece(where);
		return p;
	}

	@Override
	public String getPrintableBoard() {
		// TODO Auto-generated method stub
		return null;
	}

}
