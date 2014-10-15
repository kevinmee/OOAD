package hanto.studentkwmee.epsilon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;
import hanto.studentkwmee.common.AbstractGame;
import hanto.studentkwmee.common.Coordinate;
import hanto.studentkwmee.common.Piece;
import hanto.studentkwmee.common.PlayerPieceList;
import hanto.tournament.HantoMoveRecord;

public class EpsilonHantoGame extends AbstractGame {
	public EpsilonHantoGame(HantoPlayerColor color) {
		super(color);
		flightDistance = 4;
		startingLocation = new Coordinate(0, 0);
		pieceList = new PlayerPieceList(1, 6, 4, 2);
	}

	@Override
	protected void validateMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		if (!gameInProgress) {
			throw new HantoException("Game not playing");
		} else {
			if ((pieceType != HantoPieceType.BUTTERFLY)
					&& (turn >= 6)) {
				if(currentPlayer == HantoPlayerColor.BLUE && super.blueButterfly == null){
						throw new HantoException( "Player must place a butterfly by the fourth turn");
				}
				else if(currentPlayer == HantoPlayerColor.RED && super.redButterfly == null){
					throw new HantoException("Need to play a butterfly");
				}
			}
			switch (pieceType) {
			case CRAB:
			case HORSE:
			case SPARROW:
			case BUTTERFLY:
				if (from == null && to != null) {
					validatePiecePlacement(pieceType, to);
				} else {
					if (from != null && to != null && !board.isEmpty()) {
						if (getPieceAt(from).getType() != pieceType) {
							throw new HantoException("Piece type given does not match piece type that exists on the board.");
						}
						validPieceMovement(pieceType, from, to);
					} else {
						throw new HantoException(
								"Cannot move a piece if the board is empty");
					}
				}
				break;
			default:
				throw new HantoException("Can only use Butterflies, Sparrows, Crabs, and Horses.");
			}
			;
		}
	}

	private void validatePiecePlacement(HantoPieceType pieceType,
			HantoCoordinate to) throws HantoException,
			HantoException {
		if (!pieceList.hasPiece(currentPlayer, pieceType)) {
			throw new HantoException("Inventory is empty of this piece type.");
		}
		if (board.isEmpty()) {
			if (!isStartingLocation(to)) {
				throw new HantoException("Must place first piece at startingLocation.");
			}
		} else {
			if (turn > 1) {
				Coordinate hcTo = new Coordinate(to);
				boolean nextToOpposingPiece = false;
				for (HantoCoordinate adjacent : hcTo.getAdjacentCoordinates()) {
					if (getPieceAt(adjacent) != null
							&& getPieceAt(adjacent).getColor() != currentPlayer) {
						nextToOpposingPiece = true;
					}
				}
				if (nextToOpposingPiece) {
					throw new HantoException("Piece cannot be placed next adjacent to a piece of the opposing color.");
				}
			}
		}
	}

	@Override
	protected MoveResult determineMoveResult() {
		MoveResult result = MoveResult.OK;
		if (redButterfly != null && isSurrounded(redButterfly)) {
			result = MoveResult.BLUE_WINS;
			gameInProgress = false;
		} else if (blueButterfly != null && isSurrounded(blueButterfly)) {
			result = MoveResult.RED_WINS;
			gameInProgress = false;
		}
		if ((redButterfly != null && isSurrounded(redButterfly))
				&& (blueButterfly != null && isSurrounded(blueButterfly))) {
			result = MoveResult.DRAW;
			gameInProgress = false;
		}
		return result;
	}

	@Override
	protected MoveResult handleResignation() throws HantoException {
		if (!getAvailableMoves(currentPlayer).isEmpty()) {
			throw new HantoPrematureResignationException();
		}
		MoveResult result = MoveResult.OK;
		switch (currentPlayer) {
		case RED:
			result = MoveResult.BLUE_WINS;
			break;
		case BLUE:
			result = MoveResult.RED_WINS;
		}
		gameInProgress = false;
		return result;
	}

	/**
	 * 
	 * @param currentPlayer
	 * @return
	 */
	public List<HantoMoveRecord> getAvailableMoves(HantoPlayerColor me) {
		HantoPlayerColor enemy;
		if (me.equals(HantoPlayerColor.BLUE)) {
			enemy = HantoPlayerColor.RED;
		} else {
			enemy = HantoPlayerColor.BLUE;
		}
		List<HantoMoveRecord> moves = new ArrayList<HantoMoveRecord>();
		if (board.isEmpty() && !pieceList.isEmpty(me)) {
			moves.add(new HantoMoveRecord(HantoPieceType.BUTTERFLY, null, startingLocation));
			moves.add(new HantoMoveRecord(HantoPieceType.SPARROW, null, startingLocation));
			moves.add(new HantoMoveRecord(HantoPieceType.CRAB, null, startingLocation));
			moves.add(new HantoMoveRecord(HantoPieceType.HORSE, null, startingLocation));
		} else {
			if (!pieceList.isEmpty(me)) {
				if (turn <= 1) {
					for (HantoCoordinate adjacent : new Coordinate(
							startingLocation).getAdjacentCoordinates()) {
						moves.add(new HantoMoveRecord(HantoPieceType.BUTTERFLY,
								null, adjacent));
						moves.add(new HantoMoveRecord(HantoPieceType.SPARROW,
								null, adjacent));
						moves.add(new HantoMoveRecord(HantoPieceType.CRAB,
								null, adjacent));
						moves.add(new HantoMoveRecord(HantoPieceType.HORSE,
								null, adjacent));
					}
				} else {
					for (Entry<Coordinate, Piece> entry : board.entrySet()) {
						Coordinate hc = new Coordinate(entry.getKey());
						if (getPieceAt(hc).getColor().equals(me)) {
							for (HantoCoordinate adjacent : new Coordinate(hc).getAdjacentCoordinates()) {
								boolean nextToEnemy = false;
								if (getPieceAt(adjacent) == null) {
									for (HantoCoordinate adjacentToAdjacent : new Coordinate(
											adjacent).getAdjacentCoordinates()) {
										if (getPieceAt(adjacentToAdjacent) != null
												&& getPieceAt(
														adjacentToAdjacent)
														.getColor().equals(
																enemy)) {
											nextToEnemy = true;
											break;
										}
									}
									if (!nextToEnemy) {
										if ((turn >= 6)
												&& (!board
														.containsValue(pieceFactory
																.makeHantoPiece(
																		HantoPieceType.BUTTERFLY,
																		me)))) {
											if (pieceList.hasPiece(me,
													HantoPieceType.BUTTERFLY)) {
												moves.add(new HantoMoveRecord(
														HantoPieceType.BUTTERFLY,
														null, adjacent));
											}
										} else {
											if (pieceList.hasPiece(me,
													HantoPieceType.CRAB)) {
												moves.add(new HantoMoveRecord(
														HantoPieceType.CRAB,
														null, adjacent));
											}
											if (pieceList.hasPiece(me,
													HantoPieceType.SPARROW)) {
												moves.add(new HantoMoveRecord(
														HantoPieceType.SPARROW,
														null, adjacent));
											}
											if (pieceList.hasPiece(me,
													HantoPieceType.HORSE)) {
												moves.add(new HantoMoveRecord(
														HantoPieceType.HORSE,
														null, adjacent));
											}
										}
									}
								}
							}
						}
					}
				}
				if (!((turn >= 6) && (!board.containsValue(pieceFactory
						.makeHantoPiece(HantoPieceType.BUTTERFLY, me))))) {
					for (Entry<Coordinate, Piece> entry : board.entrySet()) {
						Coordinate hc = new Coordinate(
								entry.getKey());
						if (getPieceAt(hc) != null
								&& getPieceAt(hc).getColor().equals(me)) {
							// If it is YOUR PIECE
							HantoPieceType pieceType = getPieceAt(hc).getType();
							// IF MOVING IT WOULD MAKE THE BOARD DISCONTINUOUS
							Map<Coordinate, Piece> temp = new HashMap<Coordinate, Piece>(board);
							temp.remove(new Coordinate(hc));
							Coordinate adjCoord = null;
							for (HantoCoordinate adj : hc
									.getAdjacentCoordinates()) {
								if (getPieceAt(adj) != null) {
									adjCoord = new Coordinate(adj);
									break;
								}
							}
							boolean movingBreaksContinuity = true;
							if (adjCoord != null) {
								movingBreaksContinuity = !boardIsContinuous(
										temp, adjCoord);
							}
							if (movingBreaksContinuity) {
								break;
							}
							switch (pieceType) {
							case BUTTERFLY:
								// same rules as butterfly, no break
							case CRAB:
								for (HantoCoordinate adjacent : hc
										.getAdjacentCoordinates()) {
									if (getPieceAt(adjacent) == null) {
										try {
											validateWalkOneHex(pieceType, hc,
													adjacent);
											moves.add(new HantoMoveRecord(
													getPieceAt(hc).getType(),
													hc, adjacent));
										} catch (HantoException e) {
										}
									}
								}
								break;
							case SPARROW:
								break;
							case HORSE:
								// for each axis
								for (int axis = 0; axis < 6; axis++) {
									int i;
									switch (axis) {
									case 0:
										i = 1;
										while (true) {
											try {
												validJump(
														hc,
														new Coordinate(
																hc.getX(), hc
																		.getY()
																		+ i));
												moves.add(new HantoMoveRecord(
														HantoPieceType.HORSE,
														hc,
														new Coordinate(
																hc.getX(), hc
																		.getY()
																		+ i)));
											} catch (HantoException e) {
												if (getPieceAt(new Coordinate(
														hc.getX(), hc.getY()
																+ i)) == null) {
													break;
												}
												i++;
												continue;
											}
											break;
										}
										break;
									case 1:
										i = 1;
										while (true) {
											try {
												validJump(
														hc,
														new Coordinate(
																hc.getX(), hc
																		.getY()
																		- i));
												moves.add(new HantoMoveRecord(
														HantoPieceType.HORSE,
														hc,
														new Coordinate(
																hc.getX(), hc
																		.getY()
																		- i)));
											} catch (HantoException e) {
												if (getPieceAt(new Coordinate(
														hc.getX(), hc.getY()
																- i)) == null) {
													break;
												}
												i++;
												continue;
											}
											break;
										}
										break;
									case 2:
										i = 1;
										while (true) {
											try {
												validJump(
														hc,
														new Coordinate(
																hc.getX() + i,
																hc.getY()));
												moves.add(new HantoMoveRecord(
														HantoPieceType.HORSE,
														hc,
														new Coordinate(
																hc.getX() + i,
																hc.getY())));
											} catch (HantoException e) {
												if (getPieceAt(new Coordinate(
														hc.getX() + i,
														hc.getY())) == null) {
													break;
												}
												i++;
												continue;
											}
											break;
										}
										break;
									case 3:
										i = 1;
										while (true) {
											try {
												validJump(
														hc,
														new Coordinate(
																hc.getX() - i,
																hc.getY()));
												moves.add(new HantoMoveRecord(
														HantoPieceType.HORSE,
														hc,
														new Coordinate(
																hc.getX() - i,
																hc.getY())));
											} catch (HantoException e) {
												if (getPieceAt(new Coordinate(
														hc.getX() - i,
														hc.getY())) == null) {
													break;
												}
												i++;
												continue;
											}
											break;
										}
										break;
									case 4:
										i = 1;
										while (true) {
											try {
												validJump(
														hc,
														new Coordinate(
																hc.getX() + i,
																hc.getY() - i));
												moves.add(new HantoMoveRecord(
														HantoPieceType.HORSE,
														hc,
														new Coordinate(
																hc.getX() + i,
																hc.getY() - i)));
											} catch (HantoException e) {
												if (getPieceAt(new Coordinate(
														hc.getX() + i,
														hc.getY() - i)) == null) {
													break;
												}
												i++;
												continue;
											}
											break;
										}
										break;
									case 5:
										i = 1;
										while (true) {
											try {
												validJump(
														hc,
														new Coordinate(
																hc.getX() - i,
																hc.getY() + i));
												moves.add(new HantoMoveRecord(
														HantoPieceType.HORSE,
														hc,
														new Coordinate(
																hc.getX() - i,
																hc.getY() + i)));
											} catch (HantoException e) {
												if (getPieceAt(new Coordinate(
														hc.getX() - i,
														hc.getY() + i)) == null) {
													break;
												}
												i++;
												continue;
											}
											break;
										}
										break;
									}
								}
								break;
							default:
							}
						}
					}
				}
			}
		}
		return moves;
	}

	/**
	 * Determines if moving (not placing) the piece is valid.
	 * 
	 * @param pieceType
	 *            The piece type that is being moved
	 * @param from
	 *            Where the piece is moving from
	 * @param to
	 *            Where the piece is moving to
	 * 
	 * @throws InvalidSourceLocationException
	 * @throws HantoException
	 */
	void validPieceMovement(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		if (from == null || getPieceAt(from) == null) {
			throw new HantoException( "");
		} else {
			if (to == null || getPieceAt(to) != null) {
				throw new HantoException( "");
			}
		}
		switch (pieceType) {
		case BUTTERFLY:
		case CRAB:
			validateWalkOneHex(pieceType, from, to);
			break;
		case SPARROW:
			validFlight(pieceType, from, to);
			break;
		case HORSE:
			validJump(from, to);
		default:
			break;
		}
	}

}