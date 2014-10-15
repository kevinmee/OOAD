package hanto.studentkwmee.common;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;

public abstract class AbstractGame implements HantoGame {

	// Every hanto game will have a HashMap to represent the Board
	protected Map<Coordinate, Piece> board;
	protected Coordinate startingLocation;
	protected int turn;
	protected HantoPlayerColor movesFirst;
	protected HantoPlayerColor currentPlayer;
	protected HantoPieceFactory pieceFactory = HantoPieceFactory.getInstance();
	protected PlayerPieceList pieceList;
	protected Coordinate blueButterfly;
	protected Coordinate redButterfly;
	protected int maxTurns;
	protected boolean gameInProgress;
	protected int flightDistance;

	/**
	 * @param color
	 *            The color of the player who moves first
	 */
	protected AbstractGame(HantoPlayerColor color) {
		board = new HashMap<Coordinate, Piece>();
		turn = 0;
		movesFirst = color;
		gameInProgress = true;
	}

	/*
	 * (non-Javadoc) <!-- // $codepro.audit.disable codeInComments -->
	 * 
	 * @see hanto.common.HantoGame#getPieceAt(hanto.common.HantoCoordinate)
	 */
	public HantoPiece getPieceAt(HantoCoordinate where) {
		return board.get(new Coordinate(where));
	}

	/*
	 * (non-Javadoc) // $codepro.audit.disable codeInComments
	 * 
	 * @see hanto.common.HantoGame#getPrintableBoard()
	 */
	public String getPrintableBoard() {
		StringBuilder output = new StringBuilder();
		for (Map.Entry<Coordinate, Piece> entry : board.entrySet()) {
			output.append(entry.getKey().toString());
			output.append("\t:\t");
			output.append(entry.getValue().toString());
			output.append('\n');
		}
		return output.toString();
	}

	/**
	 * @param c
	 *            The coordinate to check if at the 'home' position
	 * @return returns true if the tested coordinate is 'home' : (0,0)
	 */
	public boolean isStartingLocation(HantoCoordinate c) {
		return startingLocation.equals(c);
	}

	/**
	 * Determines if there is a piece that is adjacent to the given coordinate
	 * 
	 * @param hc
	 *            The coordinate to check adjacency
	 * @return true if there are pieces touching the given coordinate.
	 */
	public boolean hasAdjacentPiece(final HantoCoordinate hc) {
		boolean foundAdjacentPiece = false;
		for (HantoCoordinate entry : new Coordinate(hc)
				.getAdjacentCoordinates()) {
			if (getPieceAt(entry) != null) {
				foundAdjacentPiece = true;
				break;
			}
		}
		return foundAdjacentPiece;
	}

	@Override
	public MoveResult makeMove(final HantoPieceType pieceType,
			final HantoCoordinate from, final HantoCoordinate to)
			throws HantoException {
		determineColor();
		MoveResult result = MoveResult.OK;
		if (to == null && from == null) {
				result = handleResignation();
		} else {
			validateMove(pieceType, from, to);
			placePiece(pieceType, from, to);
			result = determineMoveResult();
			turn++;
		}
		return result;
	}

	protected abstract MoveResult handleResignation() throws HantoException, HantoPrematureResignationException;

	/**
	 * Determines if the coordinate is surrounded by pieces
	 * 
	 * @param hc
	 *            the coordinate to test if there are adjacent pieces
	 * @return True if location is surrounded
	 */
	protected boolean isSurrounded(final HantoCoordinate hc) {
		boolean isSurrounded = true;
		for (HantoCoordinate entry : new Coordinate(hc)
				.getAdjacentCoordinates()) {
			if (getPieceAt(entry) == null) {
				isSurrounded = false;
				break;
			}
		}
		return isSurrounded;
	}

	/**
	 * Determines if the given move is valid
	 * 
	 * @param pieceType
	 *            The Type of Piece to be operated on the board
	 * @param from
	 *            The source destination when moving a piece, null if placing a
	 *            piece
	 * @param to
	 *            The target location to the piece is moving to
	 * @throws HantoException
	 *             Throws exceptions if an invalid move
	 */
	protected abstract void validateMove(final HantoPieceType pieceType,
			final HantoCoordinate from, final HantoCoordinate to)
			throws HantoException;

	/**
	 * Places the piece on the board
	 * 
	 * @param pieceType
	 *            Piecetype to put on the board
	 * @param from
	 *            The location the piece is moving from. Null if a placement
	 * @param to
	 *            The location to place the piece on the board
	 */
	protected void placePiece(final HantoPieceType pieceType,
			final HantoCoordinate from, final HantoCoordinate to) {
		Piece piece = pieceFactory.makeHantoPiece(pieceType, currentPlayer);
		if (pieceType == HantoPieceType.BUTTERFLY) {
			switch (currentPlayer) {
			case BLUE:
				blueButterfly = new Coordinate(to);
				break;
			case RED:
				redButterfly = new Coordinate(to);
				break;
			}
		}
		board.put(new Coordinate(to), piece);
		if (from != null) {
			board.remove(new Coordinate(from));
		}
	}

	/**
	 * @return The result state of the move
	 */
	protected abstract MoveResult determineMoveResult();

	/**
	 * Determines the current player's color.
	 * 
	 */
	protected void determineColor() {
		if (turn % 2 == 0) {
			switch (movesFirst) {
			case BLUE:
				currentPlayer = HantoPlayerColor.BLUE;
				break;
			case RED:
				currentPlayer = HantoPlayerColor.RED;
				break;
			}
		} else {
			switch (movesFirst) {
			case BLUE:
				currentPlayer = HantoPlayerColor.RED;
				break;
			case RED:
				currentPlayer = HantoPlayerColor.BLUE;
				break;
			}
		}
	}

	/**
	 * Determines if the given board is continuous
	 * 
	 * @param temp
	 *            A Map representing the board.
	 * @param first
	 *            A location to start its iteration. Usually give the piece
	 *            that's being moved.
	 * @return boolean if all of the pieces are in a single 'blob'
	 */
	protected boolean boardIsContinuous(Map<Coordinate, Piece> temp,
			Coordinate first) {

		Set<Coordinate> visited = new HashSet<Coordinate>();
		List<Coordinate> fringe = new LinkedList<Coordinate>();
		fringe.add(new Coordinate(first));
		while (!fringe.isEmpty()) {
			Coordinate current = fringe.remove(0);
			if (visited.contains(current)) {
				continue;
			}
			visited.add(current);
			for (HantoCoordinate adjacent : current.getAdjacentCoordinates()) {
				if (temp.get(adjacent) != null
						&& !visited.contains(new Coordinate(adjacent))) {
					fringe.add(new Coordinate(adjacent));
				}
			}
		}
		return visited.size() == temp.size();
	}

	/**
	 * @param pieceType
	 *            The Piece type that is walking
	 * @param from
	 *            Where the piece is walking from
	 * @param to
	 *            Where the piece is walking to
	 * @throws InvalidTargetLocationException
	 */
	protected void validateWalkOneHex(HantoPieceType pieceType,
			HantoCoordinate from, HantoCoordinate to) throws HantoException {
		Coordinate hcFrom = new Coordinate(from);
		if (!hcFrom.isAdjacentTo(to)) {
			throw new HantoException("Piece can only move one hex.");
		} else {
			boolean canSlide = false;
			for (HantoCoordinate c : hcFrom.getAdjacentCoordinates()) {
				Coordinate next = new Coordinate(c);
				if (next.isAdjacentTo(to) && getPieceAt(next) == null) {
					canSlide = true;
				}
			}
			Map<Coordinate, Piece> temp = new HashMap<Coordinate, Piece>(board);
			temp.remove(new Coordinate(from));
			temp.put(new Coordinate(to),
					pieceFactory.makeHantoPiece(pieceType, currentPlayer));
			canSlide = canSlide && boardIsContinuous(temp, new Coordinate(to));
			if (!canSlide) {
				throw new HantoException("Cannot move from to that location");
			}
		}
	}

	protected void validFlight(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {

		if (to == null) {
			throw new HantoException("Can't move piece to null");
		} else {
			if (from == null) {
				throw new HantoException("Cant move piece from a null");
			}
		}
		
		Map<Coordinate, Piece> temp = new HashMap<Coordinate, Piece>(board);
		temp.remove(new Coordinate(from));
		temp.put(new Coordinate(to), HantoPieceFactory.getInstance()
				.makeHantoPiece(pieceType, currentPlayer));
		
		if (boardIsContinuous(temp, new Coordinate(to))) {
		} 
		else {
			throw new HantoException("Can't move that piece.");
		}
		if (new Coordinate(from).getDistanceTo(to) > flightDistance) {
			throw new HantoException( "This piece cannot fly more than the max flight");
		}
	}
	
//	
//	/**
//	 * Determines if moving from 'from' position to 'to' position follows valid
//	 * jumping behavior
//	 * 
//	 * @param from
//	 *            - Where the piece is moving from
//	 * @param to
//	 *            - Where the piece is moving to
//	 * @throws HantoException
//	 */
//	protected void validJump(final HantoPieceType pieceType, final HantoCoordinate from, final HantoCoordinate to)
//			throws HantoException {
//		if(from.getX() == to.getX() || from.getY() == to.getY()){
//			board.put(new Coordinate(to), new Piece(pieceType, currentPlayer));
//			board.remove(from);
//		}
//		else{
//			if( Math.abs(to.getX() - from.getX()) == Math.abs(to.getY() - from.getY())){
//				Map<Coordinate, Piece> tempBoard = new HashMap<Coordinate, Piece>();
//				tempBoard.putAll(board);
//				
//				tempBoard.put(new Coordinate(to), new Piece(pieceType, currentPlayer));
//				tempBoard.remove(from);
//				
//				if(!boardIsContinuous(tempBoard, new Coordinate(to))){
//					throw new HantoException("Board is no longer continuous");
//				}
//				else{
//					board.putAll(tempBoard);;
//				}
//			}
//		}
//	}
	/**
	 * Determines if moving from 'from' position to 'to' position follows valid
	 * jumping behavior
	 * 
	 * @param from
	 *            - Where the piece is moving from
	 * @param to
	 *            - Where the piece is moving to
	 * @throws HantoException
	 */
	protected void validJump(HantoCoordinate from, HantoCoordinate to)
			throws HantoException {
		if (getPieceAt(to) != null) {
			throw new HantoException(
					"Piece already exists there!");
		}
		if (new Coordinate(from).isAdjacentTo(to)) {
			throw new HantoException("Can't jump to adjacent tile.");
		}
		boolean gap = false;
		int largerX;
		int smallerX;
		if (from.getX() < to.getX()) {
			largerX = to.getX();
			smallerX = from.getX();
		} else {
			largerX = from.getX();
			smallerX = to.getX();
		}
		int largerY;
		int smallerY;
		if (from.getY() < to.getY()) {
			largerY = to.getY();
			smallerY = from.getY();
		} 
		else {
			largerY = from.getY();
			smallerY = to.getY();
		}
		if (from.getX() == to.getX()) {
			// 'vertical' straight line
			for (int y = smallerY + 1; y < largerY; y++) {
				Coordinate hcVisiting = new Coordinate(
						from.getX(), y);
				if (getPieceAt(hcVisiting) == null) {
					gap = true;
				}
			}
		} 
		else if (from.getY() == to.getY()) {
			// 'positive diagonal' straight line
			for (int x = smallerX + 1; x < largerX; x++) {
				Coordinate hcVisiting = new Coordinate(x,
						from.getY());
				if (getPieceAt(hcVisiting) == null) {
					gap = true;
				}
			}
		} 
		else if (from.getX() - to.getX() == (0 - (from.getY() - to.getY()))) {
			// 'negative diagonal' straight line
			int y = largerY - 1;
			for (int x = smallerX + 1; x < largerX; x++) {
				Coordinate hcVisiting = new Coordinate(x, y);
				if (getPieceAt(hcVisiting) == null && !(hcVisiting.equals(to))) {
					gap = true;
				}
				y--;
			}
		} 
		else {
			throw new HantoException("Pieces may only jump in a straight line.");
		}
		if (gap == true) {
			throw new HantoException("Pieces may not jump over empty spaces.");
		}
	}
}