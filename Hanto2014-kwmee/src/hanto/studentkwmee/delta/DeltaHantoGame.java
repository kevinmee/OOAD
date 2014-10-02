/*
 * Delta version of Hanto Game
 * 
 * Kevin Mee, WPI A-term 2014
 */

package hanto.studentkwmee.delta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentkwmee.alpha.Piece;
import hanto.studentkwmee.common.Board;

/**
 */
public class DeltaHantoGame implements HantoGame{

	protected HantoPlayerColor turnColor;
	protected int turnNumber; 
	protected Board gameBoard;
	
	
	/**
	 * Constructor for DeltaHantoGame.
	 * @param color HantoPlayerColor
	 */
	public DeltaHantoGame(HantoPlayerColor color) {
		setGameBoard(new Board());
		turnNumber = 0;
		turnColor = color;
	}

	/**
	 * Method isFirstLocation.
	 * 
	 * @param startingLocation
	 *            HantoCoordinate
	
	 * @return boolean */
	public boolean isFirstLocation(HantoCoordinate startingLocation) {
		boolean result = false;
		if (gameBoard.size() == 0 && startingLocation.getX() == 0
		&& startingLocation.getY() == 0) {
			result = true;
		}
		return result;
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
	HantoCoordinate to) throws HantoException {

		final Piece piece = new Piece(pieceType, turnColor);

		// Checks to see if the first location is (0,0)
		if (turnNumber == 0 && !isFirstLocation(to)) {
			throw new HantoException("First move must play at (0,0)");
		}
		if( isResign(pieceType, from, to)){
			if(turnColor == HantoPlayerColor.BLUE){
				return MoveResult.RED_WINS;
			}
			else{
				return MoveResult.BLUE_WINS;
			}
		}

		// Checks to see if there is a piece to attach to
		else {
			if (!gameBoard.isAdjacent(to)) {
				throw new HantoException("There is no piece next to that one!");
			} else {
				if (from != null) {
					gameBoard.movePiece(from, to);
				} 
			}
		}

		// Adds the piece to the board
		gameBoard.add(to, piece);

		// Change player turn and increases the turn counter
		changeTurn();
		turnNumber++;

		return gameResult();

	}

	private static boolean isResign(HantoPieceType pieceType, HantoCoordinate from,
	HantoCoordinate to) {
		boolean result = false;
		if( pieceType == null && from == null && to == null){
			result = true;
		}
		return result;
	}

	private MoveResult gameResult() {
		MoveResult result;
		if (gameBoard.size() < 12) {
			result = MoveResult.OK;
		} else {
			result = MoveResult.DRAW;
		}

		return result;
	}

	private void changeTurn() {
		if (turnColor == HantoPlayerColor.BLUE) {
			turnColor = HantoPlayerColor.RED;
		} else {
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

	public Board getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(Board gameBoard) {
		this.gameBoard = gameBoard;
	}

	/**
	 * Method clearGameBoard.
	 */
	public void clearGameBoard() {
		gameBoard.clearBoard();
	}

}
	
