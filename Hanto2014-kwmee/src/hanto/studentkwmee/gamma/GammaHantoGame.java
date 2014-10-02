/*
 * Gamme Hanto Game implementation
 * 
 * Kevin Mee, WPI A-term 2014
 */

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

/**
 */
public class GammaHantoGame implements HantoGame {

	private Board gameBoard;
	protected HantoPlayerColor turnColor;
	protected int turnNumber, maxTurn = 40;

	/**
	 * Constructor for GammaHantoGame.
	 * 
	 * @param color
	 *            HantoPlayerColor
	 */
	public GammaHantoGame(HantoPlayerColor color) {
		setGameBoard(new Board());
		turnNumber = 0;
		turnColor = color;
	}

	/**
	 * Method isFirstLocation.
	 * 
	 * @param startingLocation
	 *            HantoCoordinate
	 * @return boolean
	 */
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
		if ((turnNumber * 2) >= maxTurn) {
			return MoveResult.DRAW;
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
