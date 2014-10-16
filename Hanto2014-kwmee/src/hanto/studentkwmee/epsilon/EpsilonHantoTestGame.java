/*
 * Epsilon Hanto Test game
 * 
 * Kevin Mee, WPI A-term
 */

package hanto.studentkwmee.epsilon;

import hanto.common.HantoPlayerColor;
import hanto.studentkwmee.common.Coordinate;
import hanto.studentkwmee.common.HantoPieceFactory;
import hanto.studentkwmee.common.Piece;

import hanto.common.HantoTestGame;

/**
 * 
 */
public class EpsilonHantoTestGame extends EpsilonHantoGame implements
		HantoTestGame {
	
	
	public EpsilonHantoTestGame(HantoPlayerColor color) {
		super(color);
	}

	@Override
	public void initializeBoard(PieceLocationPair[] initialPieces) {
		
		for (PieceLocationPair p : initialPieces) {
			Piece toPlace = HantoPieceFactory.getInstance()
					.makeHantoPiece(p.pieceType, p.player);
			
			switch (p.pieceType) {
			case BUTTERFLY:
				switch (p.player) {
				case BLUE:
					blueButterfly = new Coordinate(p.location);
					break;
				case RED:
					redButterfly = new Coordinate(p.location);
					break;
				}
				break;
			default:
				break;
			}
			super.board.put(new Coordinate(p.location), toPlace);
		}
	}

	@Override
	public void setTurnNumber(int turnNumber) {
		super.turn = (turnNumber - 1) * 2;
	}

	@Override
	public void setPlayerMoving(HantoPlayerColor player) {
		if (player != movesFirst) {
			if (turn % 2 != 1) {
				turn++;
			}
		}
	}
}