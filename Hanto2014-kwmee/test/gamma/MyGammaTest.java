/*
 * My tests for Gamma Hanto
 * 
 * Kevin Mee. WPI A-term 2014
 */

package gamma;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPieceType.SPARROW;
import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.MoveResult.OK;
import static org.junit.Assert.assertEquals;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoTestGame;
import hanto.common.HantoTestGameFactory;
import hanto.common.MoveResult;
import hanto.studentkwmee.common.Coordinate;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 */
public class MyGammaTest {

	
	private static HantoTestGameFactory factory = HantoTestGameFactory.getInstance();
	private HantoGame game;
	private HantoTestGame testGame;
	
	/**
	 * Method initializeClass.
	 */
	@BeforeClass
	public static void initializeClass()
	{
		factory = HantoTestGameFactory.getInstance();
	}
	
	/**
	 * Method setup.
	 */
	@Before
	public void setup()
	{
		// By default, blue moves first.
		testGame = factory.makeHantoTestGame(HantoGameID.GAMMA_HANTO);
		game = testGame;
	}
	
	/**
	 * Method bluePlacesButterflyFirst.
	
	 * @throws HantoException */
	@Test
	public void bluePlacesButterflyFirst() throws HantoException
	{
		final MoveResult mr = game.makeMove(BUTTERFLY, null, new Coordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece piece = game.getPieceAt(new Coordinate(0, 0));
		assertEquals(BLUE, piece.getColor());
		assertEquals(BUTTERFLY, piece.getType());
	}
	
	/**
	 * Method redPlacesSparrowFirst.
	
	 * @throws HantoException */
	@Test
	public void redPlacesSparrowFirst() throws HantoException
	{
		final MoveResult mr = game.makeMove(SPARROW, null, new Coordinate(0, 0));
		assertEquals(OK, mr);
	}
	
	
	/**
	 * Method printThatBoard.
	 * @throws HantoException
	 */
	@Test
	public void printThatBoard() throws HantoException{
		game.makeMove(BUTTERFLY, null, new Coordinate(0, 0));
		game.getPrintableBoard();
	}
	
}
