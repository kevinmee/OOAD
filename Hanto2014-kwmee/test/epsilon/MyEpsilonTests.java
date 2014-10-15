package epsilon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentkwmee.common.Coordinate;
import hanto.studentkwmee.common.Piece;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hanto.common.HantoTestGame;
import hanto.common.HantoTestGameFactory;
import hanto.common.HantoTestGame.PieceLocationPair;

public class MyEpsilonTests {
	private HantoTestGame game;
	private static HantoTestGameFactory gameFactory;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gameFactory = HantoTestGameFactory.getInstance();
	}

	@Before
	public void setUp() throws Exception {
		game = gameFactory.makeHantoTestGame(HantoGameID.EPSILON_HANTO);
	}

	@Test
	public void factoryReturnsNotNullGame() {
		assertNotNull(game);
	}

	@Test
	public void testNothingOnTheBoard() {
		assertNull(game.getPieceAt(new Coordinate(0, 0)));
	}

	@Test
	public void testNoStringWhenNoPieceOnBoard() {
		assertTrue(game.getPrintableBoard().equals(""));
	}

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

	@Test(expected = HantoException.class)
	public void invalidFirstPlacement() throws HantoException {
		HantoPiece firstPiece = new Piece(HantoPieceType.BUTTERFLY,
				HantoPlayerColor.BLUE);
		game.makeMove(firstPiece.getType(), null, new Coordinate(1, 1));
	}

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


	@Test(expected = HantoException.class)
	public void invalidPiece() throws HantoException {
		game.makeMove(HantoPieceType.CRANE, null, new Coordinate(0, 0));
	}

	
	@Test(expected = HantoException.class)
	public void makeMoveAfterGameEnded() throws HantoException {
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

	@Test(expected = HantoException.class)
	public void butterflyNotInTime() throws HantoException {
		game.setTurnNumber(4);
		game.setPlayerMoving(HantoPlayerColor.BLUE);
		game.makeMove(HantoPieceType.HORSE, null, new Coordinate(0, -1));
	}

	@Test(expected = HantoException.class)
	public void notOnBoard() throws HantoException {
		game.makeMove(HantoPieceType.SPARROW, new Coordinate(0, 0),
				new Coordinate(0, -1));
	}

	@Test(expected = HantoException.class)
	public void makeInvalidMoveWhichCausesDiscontinuousBoard()
			throws HantoException {
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

	@Test(expected = HantoException.class)
	public void makeInvalidMovePlaceNextToOpposingColor() throws HantoException {
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

	@Test
	public void testValidFlight() {
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
		try {
			assertEquals("Sparrow can fly to another valid location.",
					MoveResult.OK, game.makeMove(HantoPieceType.SPARROW,
							new Coordinate(0, -1), new Coordinate(0, 3)));
		} catch (HantoException e) {
			fail("Unexpected exception:\t" + e.getMessage());
		}
	}

	@Test(expected = HantoException.class)
	public void testInvalidFlightTooFar() throws HantoException {
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

	@Test(expected = HantoException.class)
	public void testInvalidFlight() throws HantoException {
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

	@Test(expected = HantoException.class)
	public void testInvalidMovement() throws HantoException {
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

	@Test
	public void simulateValidBlueWinsGame1() {
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
		try {
			assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW,
					new Coordinate(-1, 0), new Coordinate(-1, 1)));
			assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW,
					null, new Coordinate(-1, 3)));
			assertEquals(MoveResult.BLUE_WINS, game.makeMove(
					HantoPieceType.SPARROW, new Coordinate(2, -1),
					new Coordinate(1, 0)));
		} catch (HantoException e) {
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	@Test
	public void simulateValidRedWinsGame1() throws HantoException {
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

	public void simulateValidDrawGame() {
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
		try {
			assertEquals(MoveResult.DRAW, game.makeMove(HantoPieceType.SPARROW,
					new Coordinate(2, -1), new Coordinate(1, 0)));
		} catch (HantoException e) {
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	@Test
	public void horseMakesValidJump() {
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
		try {
			assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.HORSE,
					new Coordinate(0, -1), new Coordinate(0, 2)));
		} catch (HantoException e) {
			fail("Unexpected exception: " + e.getMessage());
		}
	}

}