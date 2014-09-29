package hanto.studentkwmee.alpha;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentkwmee.common.Board;
import hanto.studentkwmee.alpha.Piece;
import hanto.studentkwmee.common.Coordinate;

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
	
	public boolean isFirstLocation( HantoCoordinate startingLocation){
		if ( startingLocation.getX() == 0 && startingLocation.getY() == 0){
			return true;
		}
		return false;
	}
	
	public boolean isBlue(HantoPlayerColor color){
		if(color == HantoPlayerColor.BLUE){
			return true;
		}
		return false;
	}
	
	
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		Piece piece = new Piece( pieceType, turn);
		
		// Checks to see that the piece is not something other than butterfly
		if( pieceType != HantoPieceType.BUTTERFLY){
			throw new HantoException("The piece should be a butterfly!");
		}
		
		// Checks to see if it is blues turn and the location is (0,0)
		if( isBlue(turn) && !isFirstLocation(to)){
			throw new HantoException( "First move must play a blue butterfly at (0,0)");
		}
		
		if(from != null){
			throw new HantoException("You can't move a piece");
		}
		
		// Checks to see if there is a piece to attach to
		else if( !gameBoard.isAdjacent(to)){
			throw new HantoException( "There is no piece next to that one!");
		}
		
		// Adds the piece to the board
		gameBoard.add(to, piece);
		
		// Change player turn
		changeTurn();
		
		return gameResult();
		
	}

	private MoveResult gameResult() {
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
		else{
			turn = HantoPlayerColor.BLUE;
		}		
	}

	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		HantoPiece p =  gameBoard.getPiece(where);
		return p;
	}

	@Override
	public String getPrintableBoard() {
		return gameBoard.toString();
	}

}
