/*
 * Test file provided
 */
package hanto;

import static org.junit.Assert.*;
import static hanto.common.HantoPieceType.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hanto.studentkwmee.HantoGameFactory;
import hanto.studentkwmee.alpha.AlphaHantoGame;
import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;

import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;

/**
 */
public class AlphaHantoMasterTest{

	/**
	 * 
	 * Internal class for these test cases.
	 * 
	 * @version Sep 13, 2014
	 */

	class TestHantoCoordinate implements HantoCoordinate{

		private final int x, y;

		/**
		 * Constructor for TestHantoCoordinate.
		 * @param x int
		 * @param y int
		 */
		public TestHantoCoordinate(int x, int y){

			this.x = x;
			this.y = y;
		}

		/*
		 * @see hanto.common.HantoCoordinate#getX()
		 */
		@Override
		public int getX(){
			return x;
		}

		/*
		 * @see hanto.common.HantoCoordinate#getY()
		 */
		@Override
		public int getY(){
			return y;
		}
	}

	private static HantoGameFactory factory;
	private HantoGame game;

	/**
	 * Method initializeClass.
	 */
	@BeforeClass
	public static void initializeClass(){
		
		factory = HantoGameFactory.getInstance();
	}

	/**
	 * Method setup.
	 */
	@Before
	public void setup(){

		game = factory.makeHantoGame(HantoGameID.ALPHA_HANTO);
	}

	/**
	 * Method getAnAlphaHantoGameFromTheFactory.
	 */
	@Test
	public void getAnAlphaHantoGameFromTheFactory(){

		assertTrue(game instanceof AlphaHantoGame);
	}

	/**
	 * Method blueMakesValidFirstMove.
	 * @throws HantoException
	 */
	@Test
	public void blueMakesValidFirstMove() throws HantoException{

		final MoveResult mr = game.makeMove(BUTTERFLY, null,
		new TestHantoCoordinate(0, 0));
		assertEquals(MoveResult.OK, mr);

	}

	/**
	 * Method afterFirstMoveBlueButterflyIsAt0_0.
	 * @throws HantoException
	 */
	@Test
	public void afterFirstMoveBlueButterflyIsAt0_0() throws HantoException{

		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
		final HantoPiece p = game.getPieceAt(new TestHantoCoordinate(0, 0));
		assertEquals(BUTTERFLY, p.getType());
		assertEquals(HantoPlayerColor.BLUE, p.getColor());

	}

	/**
	 * Method bluePlacesNonButterfly.
	 * @throws HantoException
	 */
	@Test(expected = HantoException.class)
	public void bluePlacesNonButterfly() throws HantoException{

		game.makeMove(SPARROW, null, new TestHantoCoordinate(0, 0));
	}

	/**
	 * Method redPlacesButterflyNextToBlueButterfly.
	 * @throws HantoException
	 */
	@Test
	public void redPlacesButterflyNextToBlueButterfly() throws HantoException{

		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 1));

		final HantoPiece p = game.getPieceAt(new TestHantoCoordinate(0, 1));

		assertEquals(BUTTERFLY, p.getType());
		assertEquals(HantoPlayerColor.RED, p.getColor());

	}

	/**
	 * Method blueAttemptsToPlaceButterflyAtWrongLocation.
	 * @throws HantoException
	 */
	@Test(expected = HantoException.class)
	public void blueAttemptsToPlaceButterflyAtWrongLocation()
	throws HantoException{

		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(-1, 1));
	}

	/**
	 * Method redMakesValidSecondMoveAndGameIsDrawn.
	 * @throws HantoException
	 */
	@Test
	public void redMakesValidSecondMoveAndGameIsDrawn() throws HantoException{

		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
		final MoveResult mr = game.makeMove(BUTTERFLY, null,
		new TestHantoCoordinate(-1, 1));

		assertEquals(MoveResult.DRAW, mr);
	}

	/**
	 * Method redPlacesButterflyNonAdjacentToBlueButterfly.
	 * @throws HantoException
	 */
	@Test(expected = HantoException.class)
	public void redPlacesButterflyNonAdjacentToBlueButterfly()
	throws HantoException{

		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 2));

	}

	/**
	 * Method attemptToMoveRatherThanPlace.
	 * @throws HantoException
	 */
	@Test(expected = HantoException.class)
	public void attemptToMoveRatherThanPlace() throws HantoException{

		game.makeMove(BUTTERFLY, new TestHantoCoordinate(0, 1),
		new TestHantoCoordinate(0, 0));

	}
}