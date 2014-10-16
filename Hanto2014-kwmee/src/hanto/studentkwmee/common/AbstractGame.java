/*
 * Abstract Game // $codepro.audit.disable codeInComments
 * 
 * Kevin Mee, WPI A-term
 */
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


/**
 */
public abstract class AbstractGame implements HantoGame {


	protected Map<Coordinate, Piece> board;
	protected int turn;
	protected HantoPlayerColor movesFirst, current;
	protected HantoPieceFactory pieceFactory = HantoPieceFactory.getInstance();
	protected PlayerPieceList pieceList;
	protected Coordinate blueButterfly, redButterfly, startingLocation;
	protected int maxTurns, flightDistance;
	protected boolean inProgress;


	/**
	 * Constructor for AbstractGame.
	 * @param color HantoPlayerColor
	 */
	protected AbstractGame(HantoPlayerColor color) {
		board = new HashMap<Coordinate, Piece>();
		turn = 0;
		movesFirst = color;
		inProgress = true;
	}


	public HantoPiece getPieceAt(HantoCoordinate where) {
		return board.get(new Coordinate(where));
	}


	public String getPrintableBoard() {
		StringBuilder output = new StringBuilder();
		for (Map.Entry<Coordinate, Piece> entry : board.entrySet()) {
			output.append(entry.getKey().toString());
			output.append("\t:\t");
			output.append(entry.getValue().toString());
		}
		return output.toString();
	}


	/**
	 * Method isStartingLocation.
	 * @param coord Coordinate
	 * @return boolean
	 */
	public boolean isStartingLocation(Coordinate coord) {
		return startingLocation.equals(coord);
	}


	/**
	 * Method adjacentPiece.
	 * @param coord Coordinate
	 * @return boolean
	 */
	public boolean adjacentPiece(final Coordinate coord) {
		boolean flag = false;
		for (HantoCoordinate entry : new Coordinate(coord)
		.getNeighbors()) {
			if (getPieceAt(entry) != null) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	@Override
	public MoveResult makeMove(final HantoPieceType pieceType,
	final HantoCoordinate from, final HantoCoordinate to)
	throws HantoException {
		whoseTurn();
		MoveResult result = MoveResult.OK;
		if (isResigning(from, to)) {
			result = handleResignation();
		} else {
			checkMoveStatus(pieceType, from, to);
			placePiece(pieceType, from, to);
			result = determineMoveResult();
			turn++;
		}
		return result;
	}

	/**
	 * Method isResigning.
	 * @param from HantoCoordinate
	 * @param to HantoCoordinate
	 * @return boolean
	 */
	protected boolean isResigning(HantoCoordinate from, HantoCoordinate to){
		boolean result = false;
		if( from == null && to == null){
			result = true;
		}
		return result;
	}
	
	/**
	 * Method handleResignation.
	 * @return MoveResult
	 * @throws HantoException
	 * @throws HantoPrematureResignationException
	 */
	protected abstract MoveResult handleResignation() throws HantoException, HantoPrematureResignationException;


	/**
	 * Method isSurrounded.
	 * @param coord HantoCoordinate
	 * @return boolean
	 */
	protected boolean isSurrounded(final HantoCoordinate coord) {
		boolean isSurrounded = true;
		for (HantoCoordinate entry : new Coordinate(coord)
		.getNeighbors()) {
			if (getPieceAt(entry) == null) {
				isSurrounded = false;
				break;
			}
		}
		return isSurrounded;
	}


	/**
	 * Method checkMoveStatus.
	 * @param pieceType HantoPieceType
	 * @param from HantoCoordinate
	 * @param to HantoCoordinate
	 * @throws HantoException
	 */
	protected abstract void checkMoveStatus(final HantoPieceType pieceType,
	final HantoCoordinate from, final HantoCoordinate to)
	throws HantoException;


	/**
	 * Method placePiece.
	 * @param pieceType HantoPieceType
	 * @param from HantoCoordinate
	 * @param to HantoCoordinate
	 */
	protected void placePiece(final HantoPieceType pieceType,
	final HantoCoordinate from, final HantoCoordinate to) {
		Piece piece = pieceFactory.makeHantoPiece(pieceType, current);
		if (pieceType == HantoPieceType.BUTTERFLY) {
			switch (current) {
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
		pieceList.usePiece(current, pieceType);
	}


	/**
	 * Method determineMoveResult.
	 * @return MoveResult
	 */
	protected abstract MoveResult determineMoveResult();


	/**
	 * Method whoseTurn.
	 */
	protected void whoseTurn() {
		if (turn % 2 == 0) {
			switch (movesFirst) {
			case BLUE:
				current = HantoPlayerColor.BLUE;
				break;
			case RED:
				current = HantoPlayerColor.RED;
				break;
			}
		} else {
			switch (movesFirst) {
			case BLUE:
				current = HantoPlayerColor.RED;
				break;
			case RED:
				current = HantoPlayerColor.BLUE;
				break;
			}
		}
	}


	/**
	 * Method isBoardContinuous.
	 * @param temp Map<Coordinate,Piece>
	 * @param first Coordinate
	 * @return boolean
	 */
	protected boolean isBoardContinuous(Map<Coordinate, Piece> temp,
	Coordinate first) {

		Set<Coordinate> visited = new HashSet<Coordinate>();
		List<Coordinate> outterEdge = new LinkedList<Coordinate>();
		outterEdge.add(new Coordinate(first));
		
		
		while (!outterEdge.isEmpty()) {
			Coordinate current = outterEdge.remove(0);
			if (visited.contains(current)) {
				continue;
			}
			visited.add(current);
			
			for (HantoCoordinate adjacent : current.getNeighbors()) {
				if (temp.get(adjacent) != null
				&& !visited.contains(new Coordinate(adjacent))) {
					outterEdge.add(new Coordinate(adjacent));
				}
			}
		}
		return visited.size() == temp.size();
	}

	/**
	 * Method validJump.
	 * @param from HantoCoordinate
	 * @param to HantoCoordinate
	 * @throws HantoException
	 */
	protected void validJump(HantoCoordinate from, HantoCoordinate to)
	throws HantoException {
		
		
		if (getPieceAt(to) != null) {
			throw new HantoException(
			"Can't jump onto another piece");
		}
		if (new Coordinate(from).isAdjacentTo(to)) {
			throw new HantoException("Can't jump next to your current spot");
		}
		
		boolean isOpen = false;
		int bigCoordX, smallCoordX, bigCoordY, smallCoordY;
		
		if (from.getX() < to.getX()) {
			bigCoordX = to.getX();
			smallCoordX = from.getX();
		} else {
			bigCoordX = from.getX();
			smallCoordX = to.getX();
		}
		

		if (from.getY() < to.getY()) {
			bigCoordY = to.getY();
			smallCoordY = from.getY();
		} 
		else {
			bigCoordY = from.getY();
			smallCoordY = to.getY();
		}
		
		
		// Straight jump along X-axis
		if (from.getX() == to.getX()) {
			for (int y = smallCoordY + 1; y < bigCoordY; y++) {
				Coordinate visiting = new Coordinate(
				from.getX(), y);
				if (getPieceAt(visiting) == null) {
					isOpen = true;
				}
			}
		} 
		// Straight jump along Y-axis
		else if (from.getY() == to.getY()) {
			for (int x = smallCoordX + 1; x < bigCoordX; x++) {
				Coordinate visiting = new Coordinate(x,
				from.getY());
				if (getPieceAt(visiting) == null) {
					isOpen = true;
				}
			}
		} 
		else if (from.getX() - to.getX() == (0 - (from.getY() - to.getY()))) {
			int y = bigCoordY - 1;
			for (int x = smallCoordX + 1; x < bigCoordX; x++) {
				Coordinate visiting = new Coordinate(x, y);
				if (getPieceAt(visiting) == null && !(visiting.equals(to))) {
					isOpen = true;
				}
				y--;
			}
		} 
		else {
			throw new HantoException("Can only jump in a straight line");
		}
		if (isOpen) {
			throw new HantoException("Can't jump over an open spot");
		}
	}
	
	
	
	/**
	 * Method validWalk.
	 * @param pieceType HantoPieceType
	 * @param from HantoCoordinate
	 * @param to HantoCoordinate
	 * @throws HantoException
	 */
	protected void validWalk(HantoPieceType pieceType,
	HantoCoordinate from, HantoCoordinate to) throws HantoException {
		
		if (!((Coordinate) from).isAdjacentTo(to)) {
			throw new HantoException("Can only walk one space");
		} 
		else {
			boolean flag = false;
			
			for (HantoCoordinate coord : ((Coordinate) from).getNeighbors()) {
				Coordinate next = new Coordinate(coord);
				
				if (next.isAdjacentTo(to) && getPieceAt(next) == null) {
					flag = true;
				}
			}
			
			
			Map<Coordinate, Piece> temp = new HashMap<Coordinate, Piece>(board);
			temp.remove(new Coordinate(from));
			temp.put(new Coordinate(to),
			pieceFactory.makeHantoPiece(pieceType, current));
			
			flag = isBoardContinuous(temp, new Coordinate(to));
			if (!flag) {
				throw new HantoException("Can't move from there");
			}
		}
	}

	/**
	 * Method validFlight.
	 * @param pieceType HantoPieceType
	 * @param from HantoCoordinate
	 * @param to HantoCoordinate
	 * @throws HantoException
	 */
	protected void validFlight(HantoPieceType pieceType, HantoCoordinate from,
	HantoCoordinate to) throws HantoException {
		
		if (to == null || from == null) {
			throw new HantoException("One of the spots is null");
		} 
		
		Map<Coordinate, Piece> temp = new HashMap<Coordinate, Piece>(board);
		temp.remove(new Coordinate(from));
		temp.put(new Coordinate(to), HantoPieceFactory.getInstance()
		.makeHantoPiece(pieceType, current));
		
		if (isBoardContinuous(temp, new Coordinate(to))) {
			inProgress = true;
		} 
		else {
			throw new HantoException("That would break continuity");
		}
		if (new Coordinate(from).getDistanceTo(to) > flightDistance) {
			throw new HantoException( "Can't fly that far");
		}
	}
	



}