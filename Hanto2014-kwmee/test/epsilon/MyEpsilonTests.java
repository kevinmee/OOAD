/*
 * My Epsilon tests for Hanto
 * 
 * Kevin Mee, WPI A-term
 */
package epsilon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentkwmee.common.Board;
import hanto.studentkwmee.common.Coordinate;
import hanto.studentkwmee.common.HantoPieceFactory;
import hanto.studentkwmee.common.Piece;
import hanto.studentkwmee.common.PlayerPieceList;
import hanto.studentkwmee.epsilon.EpsilonHantoGame;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hanto.common.HantoTestGame;
import hanto.common.HantoTestGameFactory;
import hanto.common.HantoTestGame.PieceLocationPair;

/**
 */
public class MyEpsilonTests {
	private HantoTestGame game;
	private static HantoTestGameFactory gameFactory = HantoTestGameFactory.getInstance();

	/**
	 * Method setUpBeforeClass.
	 *
	 */
	@BeforeClass
	public static void startUpBeforeClass() {
		gameFactory = HantoTestGameFactory.getInstance();
	}

	/**
	 * Method setUp.
	 * 
	 */
	@Before
	public void startUp() {
		game = gameFactory.makeHantoTestGame(HantoGameID.EPSILON_HANTO);
	}

	/**
	 * Method factoryReturnsNotNullGame.
	 */
	@Test
	public void factoryReturnsNotNullGame() {
		assertNotNull(game);
	}

	/**
	 * Method testNothingOnTheBoard.
	 */
	@Test
	public void testNothingOnTheBoard() {
		assertNull(game.getPieceAt(new Coordinate(0, 0)));
	}

	/**
	 * Method testNoStringWhenNoPieceOnBoard.
	 */
	@Test
	public void testNoStringWhenNoPieceOnBoard() {
		assertTrue(game.getPrintableBoard().equals(""));
	}

	/**
	 * Method validFirstPlacement.
	 * @throws HantoException
	 */
	@Test
	public void validFirstPlacement() throws HantoException {

		Piece firstPiece = new Piece(HantoPieceType.HORSE,
		HantoPlayerColor.BLUE);
		assertEquals(MoveResult.OK,
		game.makeMove(firstPiece.getType(), null, new Coordinate(0, 0)));
		assertEquals(firstPiece.getColor(),
		game.getPieceAt(new Coordinate(0, 0)).getColor());
		assertEquals(firstPiece.getType(), game
		.getPieceAt(new Coordinate(0, 0)).getType());

	}

	/**
	 * Method anotherValidFirstPlacement.
	 * @throws HantoException
	 */
	@Test
	public void anotherValidFirstPlacement() throws HantoException {

		HantoPiece firstPiece = new Piece(HantoPieceType.BUTTERFLY,
		HantoPlayerColor.BLUE);
		assertEquals(MoveResult.OK,
		game.makeMove(firstPiece.getType(), null, new Coordinate(0, 0)));
		assertEquals(firstPiece.getColor(),
		game.getPieceAt(new Coordinate(0, 0)).getColor());
		assertEquals(firstPiece.getType(), game
		.getPieceAt(new Coordinate(0, 0)).getType());
	}

	/**
	 * Method pieceTest.
	 * 
	 */
	@Test
	public void pieceTest() {
		Piece piece = new Piece(HantoPieceType.BUTTERFLY, HantoPlayerColor.BLUE);
		assertEquals(HantoPlayerColor.BLUE, piece.getColor());
		assertEquals(HantoPieceType.BUTTERFLY, piece.getType());
		piece = new Piece(HantoPieceType.BUTTERFLY, HantoPlayerColor.BLUE,
		new Coordinate(0, 0));
		assertEquals(new Coordinate(0, 0), piece.getCoord());
	}

	/**
	 * Method abstractClassTest.
	 * @throws HantoException
	 */
	@Test
	public void abstractClassTest() throws HantoException {
		PieceLocationPair[] pieceList = {
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 0)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.HORSE, new Coordinate(0, -1)),
			new PieceLocationPair(HantoPlayerColor.RED,
		HantoPieceType.BUTTERFLY, new Coordinate(0, 1)), };
		game.initializeBoard(pieceList);
		game.setTurnNumber(3);
		game.setPlayerMoving(HantoPlayerColor.BLUE);

		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.HORSE,
		new Coordinate(0, -1), new Coordinate(0, 2)));
	}

	/**
	 * Method abstractClassTest2.
	 * 
	 */
	@Test
	public void abstractClassTest2() {
		PieceLocationPair[] pieceList = {
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 0)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.HORSE, new Coordinate(0, -1)),
			new PieceLocationPair(HantoPlayerColor.RED,
		HantoPieceType.BUTTERFLY, new Coordinate(0, 1)), };
		game.initializeBoard(pieceList);
		game.setTurnNumber(3);
		game.setPlayerMoving(HantoPlayerColor.BLUE);
		EpsilonHantoGame test = new EpsilonHantoGame(HantoPlayerColor.BLUE);
		assertEquals(false, test.adjacentPiece(new Coordinate(0, 0)));
		test.getPrintableBoard();
	}

	/**
	 * Method factoryTest.
	 */
	@Test
	public void factoryTest() {
		HantoPieceFactory.getInstance().makeHantoPiece(
		HantoPieceType.BUTTERFLY, HantoPlayerColor.BLUE);
		HantoPieceFactory.getInstance().makeHantoPiece(
		HantoPieceType.BUTTERFLY, HantoPlayerColor.RED);
		HantoPieceFactory.getInstance().makeHantoPiece(HantoPieceType.CRAB,
		HantoPlayerColor.BLUE);
		HantoPieceFactory.getInstance().makeHantoPiece(HantoPieceType.CRAB,
		HantoPlayerColor.RED);
		HantoPieceFactory.getInstance().makeHantoPiece(HantoPieceType.SPARROW,
		HantoPlayerColor.BLUE);
		HantoPieceFactory.getInstance().makeHantoPiece(HantoPieceType.SPARROW,
		HantoPlayerColor.RED);
		HantoPieceFactory.getInstance().makeHantoPiece(HantoPieceType.HORSE,
		HantoPlayerColor.BLUE);
		HantoPieceFactory.getInstance().makeHantoPiece(HantoPieceType.HORSE,
		HantoPlayerColor.RED);
		HantoPieceFactory.getInstance().makeHantoPiece(HantoPieceType.CRANE,
		HantoPlayerColor.BLUE);
		HantoPieceFactory.getInstance().makeHantoPiece(HantoPieceType.CRANE,
		HantoPlayerColor.RED);
		HantoPieceFactory.getInstance().makeHantoPiece(HantoPieceType.DOVE,
		HantoPlayerColor.BLUE);
		HantoPieceFactory.getInstance().makeHantoPiece(HantoPieceType.DOVE,
		HantoPlayerColor.RED);
	}

	/**
	 * Method validJump.
	 * @throws HantoException
	 */
	@Test
	public void validJump() throws HantoException {
		PieceLocationPair[] toPlace = {
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.CRAB, new Coordinate(0, 0)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.HORSE, new Coordinate(0, -1)),
			new PieceLocationPair(HantoPlayerColor.RED,
		HantoPieceType.CRAB, new Coordinate(0, 1)), };
		game.initializeBoard(toPlace);
		game.setTurnNumber(3);
		game.setPlayerMoving(HantoPlayerColor.BLUE);

		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.HORSE,
		new Coordinate(0, -1), new Coordinate(0, 2)));

	}

	/**
	 * Method validJump2.
	 * @throws HantoException
	 */
	@Test
	public void validJump2() throws HantoException {
		PieceLocationPair[] toPlace = {
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.CRAB, new Coordinate(0, 0)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.BUTTERFLY, new Coordinate(1, 0)),
			new PieceLocationPair(HantoPlayerColor.RED,
		HantoPieceType.HORSE, new Coordinate(-1, 0)), };
		game.initializeBoard(toPlace);
		game.setTurnNumber(3);
		game.setPlayerMoving(HantoPlayerColor.RED);

		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.HORSE,
		new Coordinate(-1, 0), new Coordinate(2, 0)));

	}

	/**
	 * Method validJump3.
	 * @throws HantoException
	 */
	@Test
	public void validJump3() throws HantoException {
		PieceLocationPair[] toPlace = {
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 0)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.HORSE, new Coordinate(1, -1)),
			new PieceLocationPair(HantoPlayerColor.RED,
		HantoPieceType.BUTTERFLY, new Coordinate(0, 1)), };
		game.initializeBoard(toPlace);
		game.setTurnNumber(3);
		game.setPlayerMoving(HantoPlayerColor.BLUE);

		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.HORSE,
		new Coordinate(1, -1), new Coordinate(-1, 1)));

	}

	/**
	 * Method invalidJump.
	 * @throws HantoException
	 */
	@Test(expected = HantoException.class)
	public void invalidJump() throws HantoException {
		PieceLocationPair[] toPlace = {
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 0)),
		    new PieceLocationPair(HantoPlayerColor.BLUE,
		    HantoPieceType.HORSE, new Coordinate(1, -1)),
		    new PieceLocationPair(HantoPlayerColor.BLUE,
		    HantoPieceType.HORSE, new Coordinate(2, -2)),
		    new PieceLocationPair(HantoPlayerColor.BLUE,
		    HantoPieceType.SPARROW, new Coordinate(0, -1)),
		    new PieceLocationPair(HantoPlayerColor.BLUE,
		    HantoPieceType.CRAB, new Coordinate(-1, 0)),
		    new PieceLocationPair(HantoPlayerColor.BLUE,
		    HantoPieceType.CRAB, new Coordinate(2, -1)),
		    new PieceLocationPair(HantoPlayerColor.BLUE,
		    HantoPieceType.CRAB, new Coordinate(-2, 0)),
		    new PieceLocationPair(HantoPlayerColor.BLUE,
		HantoPieceType.CRAB, new Coordinate(-1, -1)) };
		game.initializeBoard(toPlace);
		game.setTurnNumber(8);
		game.setPlayerMoving(HantoPlayerColor.BLUE);
		game.makeMove(HantoPieceType.HORSE, new Coordinate(2, -2),
		new Coordinate(0, 1));
	}

	/**
	 * Method pieceList.
	 * 
	 */
	@Test
	public void pieceList() {
		PlayerPieceList list = new PlayerPieceList(1, 1, 1, 1);
		assertEquals(false, list.isEmpty(HantoPlayerColor.BLUE));
		assertEquals(false, list.isEmpty(HantoPlayerColor.RED));
		list.usePiece(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY);
		assertEquals(false,
		list.hasPiece(HantoPlayerColor.BLUE, HantoPieceType.BUTTERFLY));
		assertEquals(true,
		list.hasPiece(HantoPlayerColor.BLUE, HantoPieceType.SPARROW));
		assertEquals(true,
		list.hasPiece(HantoPlayerColor.BLUE, HantoPieceType.CRAB));
		assertEquals(true,
		list.hasPiece(HantoPlayerColor.BLUE, HantoPieceType.HORSE));
		assertEquals(true,
		list.hasPiece(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY));
		assertEquals(true,
		list.hasPiece(HantoPlayerColor.RED, HantoPieceType.HORSE));
		assertEquals(true,
		list.hasPiece(HantoPlayerColor.BLUE, HantoPieceType.CRAB));
		list.usePiece(HantoPlayerColor.BLUE, HantoPieceType.HORSE);
		list.usePiece(HantoPlayerColor.BLUE, HantoPieceType.SPARROW);
		list.usePiece(HantoPlayerColor.BLUE, HantoPieceType.CRAB);
		assertEquals(false,
		list.hasPiece(HantoPlayerColor.BLUE, HantoPieceType.SPARROW));
		assertEquals(false,
		list.hasPiece(HantoPlayerColor.BLUE, HantoPieceType.CRAB));
		assertEquals(false,
		list.hasPiece(HantoPlayerColor.BLUE, HantoPieceType.HORSE));
		assertEquals(true, list.isEmpty(HantoPlayerColor.BLUE));
		list.usePiece(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY);
		list.usePiece(HantoPlayerColor.RED, HantoPieceType.HORSE);
		list.usePiece(HantoPlayerColor.RED, HantoPieceType.SPARROW);
		list.usePiece(HantoPlayerColor.RED, HantoPieceType.CRAB);
		assertEquals(false,
		list.hasPiece(HantoPlayerColor.RED, HantoPieceType.BUTTERFLY));
		assertEquals(false,
		list.hasPiece(HantoPlayerColor.RED, HantoPieceType.SPARROW));
		assertEquals(false,
		list.hasPiece(HantoPlayerColor.RED, HantoPieceType.CRAB));
		assertEquals(false,
		list.hasPiece(HantoPlayerColor.RED, HantoPieceType.HORSE));
		assertEquals(true, list.isEmpty(HantoPlayerColor.RED));
	}

	/**
	 * Method invalidJump2.
	 * @throws HantoException
	 */
	@Test(expected = HantoException.class)
	public void invalidJump2() throws HantoException {
		PieceLocationPair[] toPlace = {
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 0)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.HORSE, new Coordinate(1, -1)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.HORSE, new Coordinate(2, -2)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.SPARROW, new Coordinate(0, -1)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.CRAB, new Coordinate(-1, 0)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.CRAB, new Coordinate(2, -1)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.CRAB, new Coordinate(-2, 0)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
		HantoPieceType.CRAB, new Coordinate(-1, -1)) };
		game.initializeBoard(toPlace);
		game.setTurnNumber(8);
		game.setPlayerMoving(HantoPlayerColor.BLUE);
		game.makeMove(HantoPieceType.HORSE, new Coordinate(2, -2),
		new Coordinate(2, 1));
	}

	/**
	 * Method invalidFirstPlacement.
	 * @throws HantoException
	 */
	@Test(expected = HantoException.class)
	public void invalidFirstPlacement() throws HantoException {
		HantoPiece firstPiece = new Piece(HantoPieceType.BUTTERFLY,
		HantoPlayerColor.BLUE);
		game.makeMove(firstPiece.getType(), null, new Coordinate(1, 1));
	}

	/**
	 * Method butterflyWalk.
	 * @throws HantoException
	 */
	@Test
	public void butterflyWalk() throws HantoException {

		PieceLocationPair[] pieceList = {
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 0)),
			new PieceLocationPair(HantoPlayerColor.RED,
		HantoPieceType.BUTTERFLY, new Coordinate(0, 1)), };
		game.initializeBoard(pieceList);
		game.setTurnNumber(2);
		game.setPlayerMoving(HantoPlayerColor.BLUE);
		assertEquals("Moving a butterfly should return OK.", MoveResult.OK,
		game.makeMove(HantoPieceType.BUTTERFLY, new Coordinate(0, 0),
		new Coordinate(-1, 1)));
		assertEquals(HantoPlayerColor.BLUE,
		game.getPieceAt(new Coordinate(-1, 1)).getColor());
		assertEquals(HantoPieceType.BUTTERFLY,
		game.getPieceAt(new Coordinate(-1, 1)).getType());

	}

	/**
	 * Method invalidWalk.
	 * @throws HantoException
	 */
	@Test(expected = HantoException.class)
	public void invalidWalk() throws HantoException {
		PieceLocationPair[] pieceList = {
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.CRAB, new Coordinate(0, 0)),
			new PieceLocationPair(HantoPlayerColor.RED,
		HantoPieceType.BUTTERFLY, new Coordinate(0, -1)), };
		game.initializeBoard(pieceList);
		game.setTurnNumber(2);
		game.setPlayerMoving(HantoPlayerColor.BLUE);
		game.makeMove(HantoPieceType.CRAB, new Coordinate(0, 0),
		new Coordinate(1, 1));
	}

	/**
	 * Method invalidPiece.
	 * @throws HantoException
	 */
	@Test(expected = HantoException.class)
	public void invalidPiece() throws HantoException {
		game.makeMove(HantoPieceType.CRANE, null, new Coordinate(0, 0));
	}

	/**
	 * Method moveAfterEnded.
	 * @throws HantoException
	 */
	@Test(expected = HantoException.class)
	public void moveAfterEnded() throws HantoException {
		PieceLocationPair[] pieceList = {
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.CRAB, new Coordinate(0, 0)),
			new PieceLocationPair(HantoPlayerColor.RED,
		HantoPieceType.BUTTERFLY, new Coordinate(0, 1)), };
		game.initializeBoard(pieceList);
		game.setTurnNumber(2);
		game.setPlayerMoving(HantoPlayerColor.BLUE);
		game.makeMove(HantoPieceType.CRAB, null, null);
		game.makeMove(HantoPieceType.SPARROW, null, new Coordinate(1, 1));
	}

	/**
	 * Method butterflyNotInTime.
	 * @throws HantoException
	 */
	@Test(expected = HantoException.class)
	public void butterflyNotInTime() throws HantoException {
		game.setTurnNumber(4);
		game.setPlayerMoving(HantoPlayerColor.BLUE);
		game.makeMove(HantoPieceType.HORSE, null, new Coordinate(0, -1));
	}

	/**
	 * Method notOnBoard.
	 * @throws HantoException
	 */
	@Test(expected = HantoException.class)
	public void notOnBoard() throws HantoException {
		game.makeMove(HantoPieceType.SPARROW, new Coordinate(0, 0),
		new Coordinate(0, -1));
	}

	/**
	 * Method discontinuousBoard.
	 * @throws HantoException
	 */
	@Test(expected = HantoException.class)
	public void discontinuousBoard() throws HantoException {
		PieceLocationPair[] pieceList = {
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 0)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.SPARROW, new Coordinate(0, -1)),
			new PieceLocationPair(HantoPlayerColor.RED,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 1)),
			new PieceLocationPair(HantoPlayerColor.RED,
		HantoPieceType.SPARROW, new Coordinate(0, 2)) };
		game.initializeBoard(pieceList);
		game.setTurnNumber(2);
		game.setPlayerMoving(HantoPlayerColor.RED);
		game.makeMove(HantoPieceType.BUTTERFLY, new Coordinate(0, 1),
		new Coordinate(1, 0));
	}

	/**
	 * Method nextToOpposingColor.
	 * @throws HantoException
	 */
	@Test(expected = HantoException.class)
	public void nextToOpposingColor() throws HantoException {
		PieceLocationPair[] pieceList = {
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 0)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.SPARROW, new Coordinate(1, -1)),
			new PieceLocationPair(HantoPlayerColor.RED,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 1)),
			new PieceLocationPair(HantoPlayerColor.RED,
		HantoPieceType.SPARROW, new Coordinate(-1, 2)) };
		game.initializeBoard(pieceList);
		game.setTurnNumber(3);
		game.setPlayerMoving(HantoPlayerColor.BLUE);
		game.makeMove(HantoPieceType.SPARROW, null, new Coordinate(-2, 2));
	}

	/**
	 * Method board.
	 * 
	 */
	@Test
	public void board() {
		Board board = new Board();
		board.add(new Coordinate(0, 0), new Piece(HantoPieceType.BUTTERFLY,
		HantoPlayerColor.BLUE));
		assertEquals(null, board.getPiece(new Coordinate(0, 0)));
		board.clearBoard();
		assertEquals(null, board.getPiece(new Coordinate(0, 0)));
		assertEquals(0, board.size());
		assertEquals(false, board.isAdjacent(new Coordinate(0, 0)));
		board.movePiece(new Coordinate(0, 0), new Coordinate(0, 1));
		assertEquals(null, board.getPiece(new Coordinate(0, 1)));
	}

	/**
	 * Method validFlight.
	 * @throws HantoException
	 */
	@Test
	public void validFlight() throws HantoException {
		PieceLocationPair[] pieceList = {
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 0)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.SPARROW, new Coordinate(0, -1)),
			new PieceLocationPair(HantoPlayerColor.RED,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 1)),
			new PieceLocationPair(HantoPlayerColor.RED,
		HantoPieceType.BUTTERFLY, new Coordinate(0, 2)) };
		game.initializeBoard(pieceList);
		game.setTurnNumber(3);
		game.setPlayerMoving(HantoPlayerColor.BLUE);

		assertEquals("Sparrow can fly to another valid location.",
		MoveResult.OK, game.makeMove(HantoPieceType.SPARROW,
		new Coordinate(0, -1), new Coordinate(0, 3)));

	}

	/**
	 * Method invalidFlight.
	 * @throws HantoException
	 */
	@Test(expected = HantoException.class)
	public void invalidFlight() throws HantoException {
		PieceLocationPair[] pieceList = {
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 0)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.SPARROW, new Coordinate(0, -1)),
			new PieceLocationPair(HantoPlayerColor.RED,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 1)),
			new PieceLocationPair(HantoPlayerColor.RED,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 2)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
		HantoPieceType.SPARROW, new Coordinate(0, -2)) };
		game.initializeBoard(pieceList);
		game.setTurnNumber(3);
		game.setPlayerMoving(HantoPlayerColor.BLUE);
		game.makeMove(HantoPieceType.SPARROW, new Coordinate(0, -2),
		new Coordinate(0, 3));
	}

	/**
	 * Method invalidFlight2.
	 * @throws HantoException
	 */
	@Test(expected = HantoException.class)
	public void invalidFlight2() throws HantoException {
		PieceLocationPair[] pieceList = {
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 0)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.SPARROW, new Coordinate(0, -1)),
			new PieceLocationPair(HantoPlayerColor.RED,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 1)),
			new PieceLocationPair(HantoPlayerColor.RED,
		HantoPieceType.BUTTERFLY, new Coordinate(0, 2)) };
		game.initializeBoard(pieceList);
		game.setTurnNumber(3);
		game.setPlayerMoving(HantoPlayerColor.BLUE);
		game.makeMove(HantoPieceType.SPARROW, new Coordinate(0, -1),
		new Coordinate(0, 8));
	}

	/**
	 * Method invalidMovement.
	 * @throws HantoException
	 */
	@Test(expected = HantoException.class)
	public void invalidMovement() throws HantoException {
		PieceLocationPair[] pieceList = {
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 0)),
			new PieceLocationPair(HantoPlayerColor.RED,
		HantoPieceType.BUTTERFLY, new Coordinate(0, 1)) };
		game.initializeBoard(pieceList);
		game.setTurnNumber(2);
		game.setPlayerMoving(HantoPlayerColor.BLUE);
		game.makeMove(HantoPieceType.SPARROW, new Coordinate(0, 0),
		new Coordinate(1, 0));
	}

	/**
	 * Method blueWins.
	 * @throws HantoException
	 */
	@Test
	public void blueWins() throws HantoException {
		PieceLocationPair[] pieceList = {
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 0)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.SPARROW, new Coordinate(1, -1)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.SPARROW, new Coordinate(-1, 0)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.SPARROW, new Coordinate(2, -1)),
			new PieceLocationPair(HantoPlayerColor.RED,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 1)),
			new PieceLocationPair(HantoPlayerColor.RED,
			HantoPieceType.SPARROW, new Coordinate(-1, 2)),
			new PieceLocationPair(HantoPlayerColor.RED,
			HantoPieceType.SPARROW, new Coordinate(0, 2)),
			new PieceLocationPair(HantoPlayerColor.RED,
		HantoPieceType.SPARROW, new Coordinate(1, 1)) };
		game.initializeBoard(pieceList);
		game.setTurnNumber(5);
		game.setPlayerMoving(HantoPlayerColor.BLUE);

		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW,
		new Coordinate(-1, 0), new Coordinate(-1, 1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null,
		new Coordinate(-1, 3)));
		assertEquals(MoveResult.BLUE_WINS, game.makeMove(
		HantoPieceType.SPARROW, new Coordinate(2, -1), new Coordinate(1, 0)));

	}

	/**
	 * Method redWins.
	 * @throws HantoException
	 */
	@Test
	public void redWins() throws HantoException {
		game = gameFactory.makeHantoTestGame(HantoGameID.EPSILON_HANTO,
		HantoPlayerColor.RED);
		PieceLocationPair[] pieceList = {
			new PieceLocationPair(HantoPlayerColor.RED,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 0)),
			new PieceLocationPair(HantoPlayerColor.RED,
			HantoPieceType.SPARROW, new Coordinate(1, -1)),
			new PieceLocationPair(HantoPlayerColor.RED,
			HantoPieceType.SPARROW, new Coordinate(-1, 0)),
			new PieceLocationPair(HantoPlayerColor.RED,
			HantoPieceType.SPARROW, new Coordinate(2, -1)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 1)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.SPARROW, new Coordinate(-1, 2)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.SPARROW, new Coordinate(0, 2)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
		HantoPieceType.SPARROW, new Coordinate(1, 1)) };
		game.initializeBoard(pieceList);
		game.setTurnNumber(5);
		game.setPlayerMoving(HantoPlayerColor.RED);

		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW,
		new Coordinate(-1, 0), new Coordinate(-1, 1)));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, null,
		new Coordinate(-1, 3)));
		assertEquals(MoveResult.RED_WINS, game.makeMove(HantoPieceType.SPARROW,
		new Coordinate(2, -1), new Coordinate(1, 0)));

	}

	/**
	 * Method coordinateTest.
	 */
	@Test
	public void coordinateTest() {
		Coordinate coord = new Coordinate(0, 0);
		assertEquals("(0,0)", coord.toString());
	}

	/**
	 * Method draw.
	 * @throws HantoException
	 */
	public void draw() throws HantoException {
		PieceLocationPair[] pieceList = {
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 0)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.SPARROW, new Coordinate(1, -1)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.SPARROW, new Coordinate(0, -1)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.SPARROW, new Coordinate(-1, 0)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.SPARROW, new Coordinate(2, -1)),
			new PieceLocationPair(HantoPlayerColor.RED,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 1)),
			new PieceLocationPair(HantoPlayerColor.RED,
			HantoPieceType.SPARROW, new Coordinate(-1, 2)),
			new PieceLocationPair(HantoPlayerColor.RED,
			HantoPieceType.SPARROW, new Coordinate(0, 2)),
			new PieceLocationPair(HantoPlayerColor.RED,
			HantoPieceType.SPARROW, new Coordinate(1, 1)),
			new PieceLocationPair(HantoPlayerColor.RED,
		HantoPieceType.SPARROW, new Coordinate(-1, 1)) };
		game.initializeBoard(pieceList);
		game.setTurnNumber(6);
		game.setPlayerMoving(HantoPlayerColor.RED);

		assertEquals(MoveResult.DRAW, game.makeMove(HantoPieceType.SPARROW,
		new Coordinate(2, -1), new Coordinate(1, 0)));

	}

	/**
	 * Method validJump4.
	 * @throws HantoException
	 */
	@Test
	public void validJump4() throws HantoException {
		PieceLocationPair[] pieceList = {
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.BUTTERFLY, new Coordinate(0, 0)),
			new PieceLocationPair(HantoPlayerColor.BLUE,
			HantoPieceType.HORSE, new Coordinate(0, -1)),
			new PieceLocationPair(HantoPlayerColor.RED,
		HantoPieceType.BUTTERFLY, new Coordinate(0, 1)), };
		game.initializeBoard(pieceList);
		game.setTurnNumber(3);
		game.setPlayerMoving(HantoPlayerColor.BLUE);

		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.HORSE,
		new Coordinate(0, -1), new Coordinate(0, 2)));

	}

}