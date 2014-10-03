/*
 * Delta tests
 * 
 * Kevin Mee, WPI A-term 2014
 */
package delta;

import static hanto.common.HantoPieceType.BUTTERFLY;
import static hanto.common.HantoPieceType.SPARROW;
import static hanto.common.HantoPlayerColor.BLUE;
import static hanto.common.MoveResult.OK;
import static org.junit.Assert.assertEquals;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoTestGame;
import hanto.common.HantoTestGameFactory;
import hanto.common.MoveResult;
import hanto.studentkwmee.common.Coordinate;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for Delta hanto
 */
public class MyDeltaTest {

	
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
		testGame = factory.makeHantoTestGame(HantoGameID.DELTA_HANTO);
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
	 * Method tryingPieceThatIsntAllowed
	
	 * @throws HantoException */
	
	@Test
	public void tryingPieceThatIsntAllowed() throws HantoException{
		final MoveResult mr = game.makeMove(HantoPieceType.CRANE, null, new Coordinate(0, 0));
		assertEquals(OK, mr);
	}
	
	/**
	 * Method nullResign.
	 * @throws HantoException
	 */
	@Test
	public void nullResign() throws HantoException{
		final MoveResult mr = game.makeMove(null, null, null);
		assertEquals(MoveResult.RED_WINS, mr);
	}
	
	/**
	 * Method tryWalking.
	 * @throws HantoException
	 */
	@Test
	public void tryWalking() throws HantoException{
		game.makeMove(SPARROW, null, new Coordinate(0, 0));
		final MoveResult mr = game.makeMove(SPARROW, new Coordinate(0, 0), new Coordinate(0, 1));
		assertEquals(MoveResult.OK, mr);
	}
	
	
	/**
	 * Method printThatBoard.
	
	 * @throws HantoException */
	@Test
	public void printThatBoard() throws HantoException{
		game.makeMove(BUTTERFLY, null, new Coordinate(0, 0));
		game.getPrintableBoard();
	}
	
}