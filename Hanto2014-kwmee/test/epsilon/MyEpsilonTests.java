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
	private static HantoTestGameFactory theFactory;
	private HantoCoordinate startingLocation;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		theFactory = HantoTestGameFactory.getInstance();
	}

	@Before
	public void setUp() throws Exception {
		game = theFactory.makeHantoTestGame(HantoGameID.EPSILON_HANTO);
		startingLocation = new Coordinate(0, 0);
	}

	@Test
	public void factoryReturnsNotNullGame() {
		assertNotNull(game);
	}

	@Test
	public void testNothingOnTheBoard() {
		assertNull(game.getPieceAt(startingLocation));
	}

	@Test
	public void testNoStringWhenNoPieceOnBoard() {
		assertTrue(game.getPrintableBoard().equals(""));
	}

	@Test
	public void makeFirstMove1() {
		try {
			Piece firstPiece = new Piece(HantoPieceType.SPARROW, HantoPlayerColor.BLUE);
			assertEquals(
					"Placing a sparrow at startingLocation as first move should return OK",
					MoveResult.OK,
					game.makeMove(firstPiece.getType(), null, startingLocation));
			assertEquals(firstPiece.getColor(), game.getPieceAt(startingLocation).getColor());
			assertEquals(firstPiece.getType(), game.getPieceAt(startingLocation).getType());
		} catch (HantoException e) {
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	@Test
	public void makeFirstMove2() {
		try {
			game = theFactory.makeHantoTestGame(HantoGameID.GAMMA_HANTO,
					HantoPlayerColor.RED);
			HantoPiece firstPiece = new Piece(HantoPieceType.SPARROW, HantoPlayerColor.RED);
			assertEquals(
					"Placing a sparrow at startingLocation as first move should return OK",
					MoveResult.OK,
					game.makeMove(firstPiece.getType(), null, startingLocation));
			assertEquals("The piece at startingLocation should be a red piece",
					firstPiece.getColor(), game.getPieceAt(startingLocation).getColor());
			assertEquals("The piece at startingLocation should be a sparrow.",
					firstPiece.getType(), game.getPieceAt(startingLocation).getType());
		} catch (HantoException e) {
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	@Test
	public void makeFirstMove3() {
		try {
			HantoPiece firstPiece = new Piece(HantoPieceType.BUTTERFLY, HantoPlayerColor.BLUE);
			assertEquals(
					"Placing a butterfly at startingLocation as first move should return OK",
					MoveResult.OK,
					game.makeMove(firstPiece.getType(), null, startingLocation));
			assertEquals("The piece at startingLocation should be a blue piece",
					firstPiece.getColor(), game.getPieceAt(startingLocation).getColor());
			assertEquals("The piece at startingLocation should be a butterfly.",
					firstPiece.getType(), game.getPieceAt(startingLocation).getType());
		} catch (HantoException e) {
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	@Test
	public void makeFirstMove4() {
		try {
			HantoPiece firstPiece = new Piece(HantoPieceType.CRAB, HantoPlayerColor.BLUE);
			assertEquals(
					"Placing a butterfly at startingLocation as first move should return OK",
					MoveResult.OK,
					game.makeMove(firstPiece.getType(), null, startingLocation));
			assertEquals("The piece at startingLocation should be a blue piece",
					firstPiece.getColor(), game.getPieceAt(startingLocation).getColor());
			assertEquals("The piece at startingLocation should be a crab.",
					firstPiece.getType(), game.getPieceAt(startingLocation).getType());
		} catch (HantoException e) {
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	@Test(expected = HantoException.class)
	public void makeInvalidFirstMove1() throws HantoException {
		HantoPiece firstPiece = new Piece(HantoPieceType.BUTTERFLY, HantoPlayerColor.BLUE);
		HantoCoordinate notstartingLocation = new Coordinate(1, 1);
		game.makeMove(firstPiece.getType(), null, notstartingLocation);
	}

	@Test
	public void makeValidWalkingMoveButterfly() {
		try {
			PieceLocationPair[] toPlace = {
					new PieceLocationPair(HantoPlayerColor.BLUE,
							HantoPieceType.BUTTERFLY, startingLocation),
					new PieceLocationPair(HantoPlayerColor.RED,
							HantoPieceType.BUTTERFLY, new Coordinate(
									0, 1)), };
			game.initializeBoard(toPlace);
			game.setTurnNumber(2);
			game.setPlayerMoving(HantoPlayerColor.BLUE);
			assertEquals("Moving a butterfly should return OK.", MoveResult.OK,
					game.makeMove(HantoPieceType.BUTTERFLY, startingLocation,
							new Coordinate(1, 0)));
			assertEquals("The piece at (1,0) should be a blue piece",
					HantoPlayerColor.BLUE,
					game.getPieceAt(new Coordinate(1, 0))
							.getColor());
			assertEquals("The piece at (1,0) should be a butterfly.",
					HantoPieceType.BUTTERFLY,
					game.getPieceAt(new Coordinate(1, 0)).getType());
		} catch (HantoException e) {
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	@Test
	public void makeValidWalkingMoveCrab() {
		try {
			PieceLocationPair[] toPlace = {
					new PieceLocationPair(HantoPlayerColor.BLUE,
							HantoPieceType.CRAB, startingLocation),
					new PieceLocationPair(HantoPlayerColor.RED,
							HantoPieceType.BUTTERFLY, new Coordinate(
									0, 1)), };
			game.initializeBoard(toPlace);
			game.setTurnNumber(2);
			game.setPlayerMoving(HantoPlayerColor.BLUE);
			assertEquals("Moving a crab should return OK.", MoveResult.OK,
					game.makeMove(HantoPieceType.CRAB, startingLocation,
							new Coordinate(1, 0)));
			assertEquals("The piece at (1,0) should be a blue piece",
					HantoPlayerColor.BLUE,
					game.getPieceAt(new Coordinate(1, 0))
							.getColor());
			assertEquals("The piece at (1,0) should be a crab.",
					HantoPieceType.CRAB,
					game.getPieceAt(new Coordinate(1, 0)).getType());
		} catch (HantoException e) {
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	@Test(expected = HantoException.class)
	public void makeInvalidWalkingMoveMoreThanOneHexButterfly()
			throws HantoException {
		PieceLocationPair[] toPlace = {
				new PieceLocationPair(HantoPlayerColor.BLUE,
						HantoPieceType.BUTTERFLY, startingLocation),
				new PieceLocationPair(HantoPlayerColor.RED,
						HantoPieceType.BUTTERFLY, new Coordinate(0, 1)), };
		game.initializeBoard(toPlace);
		game.setTurnNumber(2);
		game.setPlayerMoving(HantoPlayerColor.BLUE);
		game.makeMove(HantoPieceType.BUTTERFLY, startingLocation,
				new Coordinate(1, 1));
	}

	@Test(expected = HantoException.class)
	public void makeInvalidWalkingMoveMoreThanOneHexCrab()
			throws HantoException {
		PieceLocationPair[] toPlace = {
				new PieceLocationPair(HantoPlayerColor.BLUE,
						HantoPieceType.CRAB, startingLocation),
				new PieceLocationPair(HantoPlayerColor.RED,
						HantoPieceType.CRAB, new Coordinate(0, 1)), };
		game.initializeBoard(toPlace);
		game.setTurnNumber(2);
		game.setPlayerMoving(HantoPlayerColor.BLUE);
		game.makeMove(HantoPieceType.CRAB, startingLocation, new Coordinate(1,
				1));
	}

	@Test(expected = HantoException.class)
	public void makeInvalidMoveFromNullWithInvalidPieceType()
			throws HantoException {
		HantoCoordinate startingLocation = new Coordinate(0, 0);
		game.makeMove(HantoPieceType.DOVE, null, startingLocation);
	}

	@Test(expected = HantoException.class)
	public void makeMoveAfterGameEnded() throws HantoException {
		PieceLocationPair[] toPlace = {
				new PieceLocationPair(HantoPlayerColor.BLUE,
						HantoPieceType.CRAB, startingLocation),
				new PieceLocationPair(HantoPlayerColor.RED,
						HantoPieceType.BUTTERFLY, new Coordinate(0, 1)), };
		game.initializeBoard(toPlace);
		game.setTurnNumber(2);
		game.setPlayerMoving(HantoPlayerColor.BLUE);
		game.makeMove(HantoPieceType.CRAB, null, null);
		game.makeMove(HantoPieceType.SPARROW, null, new Coordinate(
				1, 1));
	}

	@Test(expected = HantoException.class)
	public void blueButterflyNotPlacedByFourthTurn() throws HantoException {
		PieceLocationPair[] toPlace = {};
		game.initializeBoard(toPlace);
		game.setTurnNumber(4);
		game.setPlayerMoving(HantoPlayerColor.RED);
		game.makeMove(HantoPieceType.SPARROW, null, new Coordinate(
				0, -1));
	}

	@Test(expected = HantoException.class)
	public void movePieceNotOnBoardYet() throws HantoException {
		game.makeMove(HantoPieceType.SPARROW, startingLocation, new Coordinate(
				0, -1));
	}

	@Test(expected = HantoException.class)
	public void makeInvalidMoveWhichCausesDiscontinuousBoard()
			throws HantoException {
		PieceLocationPair[] toPlace = {
				new PieceLocationPair(HantoPlayerColor.BLUE,
						HantoPieceType.BUTTERFLY, startingLocation),
				new PieceLocationPair(HantoPlayerColor.BLUE,
						HantoPieceType.SPARROW, new Coordinate(0, -1)),
				new PieceLocationPair(HantoPlayerColor.RED,
						HantoPieceType.BUTTERFLY, new Coordinate(0, 1)),
				new PieceLocationPair(HantoPlayerColor.RED,
						HantoPieceType.SPARROW, new Coordinate(0, 2)) };
		game.initializeBoard(toPlace);
		game.setTurnNumber(2);
		game.setPlayerMoving(HantoPlayerColor.RED);
		game.makeMove(HantoPieceType.BUTTERFLY,
				new Coordinate(0, 1), new Coordinate(1, 0));
	}

	@Test(expected = HantoException.class)
	public void makeInvalidMovePlaceNextToOpposingColor() throws HantoException {
		PieceLocationPair[] toPlace = {
				new PieceLocationPair(HantoPlayerColor.BLUE,
						HantoPieceType.BUTTERFLY, startingLocation),
				new PieceLocationPair(HantoPlayerColor.BLUE,
						HantoPieceType.SPARROW, new Coordinate(1, -1)),
				new PieceLocationPair(HantoPlayerColor.RED,
						HantoPieceType.BUTTERFLY, new Coordinate(0, 1)),
				new PieceLocationPair(HantoPlayerColor.RED,
						HantoPieceType.SPARROW, new Coordinate(-1, 2)) };
		game.initializeBoard(toPlace);
		game.setTurnNumber(3);
		game.setPlayerMoving(HantoPlayerColor.BLUE);
		game.makeMove(HantoPieceType.SPARROW, null, new Coordinate(
				-2, 2));
	}

	@Test
	public void testValidFlight() {
		PieceLocationPair[] toPlace = {
				new PieceLocationPair(HantoPlayerColor.BLUE,
						HantoPieceType.BUTTERFLY, startingLocation),
				new PieceLocationPair(HantoPlayerColor.BLUE,
						HantoPieceType.SPARROW, new Coordinate(0, -1)),
				new PieceLocationPair(HantoPlayerColor.RED,
						HantoPieceType.BUTTERFLY, new Coordinate(0, 1)),
				new PieceLocationPair(HantoPlayerColor.RED,
						HantoPieceType.BUTTERFLY, new Coordinate(0, 2)) };
		game.initializeBoard(toPlace);
		game.setTurnNumber(3);
		game.setPlayerMoving(HantoPlayerColor.BLUE);
		try {
			assertEquals("Sparrow can fly to another valid location.",
					MoveResult.OK, game.makeMove(HantoPieceType.SPARROW,
							new Coordinate(0, -1),
							new Coordinate(0, 3)));
		} catch (HantoException e) {
			fail("Unexpected exception:\t" + e.getMessage());
		}
	}

	@Test(expected = HantoException.class)
	public void testInvalidFlightTooFar() throws HantoException {
		PieceLocationPair[] toPlace = {
				new PieceLocationPair(HantoPlayerColor.BLUE,
						HantoPieceType.BUTTERFLY, startingLocation),
				new PieceLocationPair(HantoPlayerColor.BLUE,
						HantoPieceType.SPARROW, new Coordinate(0, -1)),
				new PieceLocationPair(HantoPlayerColor.RED,
						HantoPieceType.BUTTERFLY, new Coordinate(0, 1)),
				new PieceLocationPair(HantoPlayerColor.RED,
						HantoPieceType.BUTTERFLY, new Coordinate(0, 2)),
				new PieceLocationPair(HantoPlayerColor.BLUE,
						HantoPieceType.SPARROW, new Coordinate(0, -2)) };
		game.initializeBoard(toPlace);
		game.setTurnNumber(3);
		game.setPlayerMoving(HantoPlayerColor.BLUE);
		game.makeMove(HantoPieceType.SPARROW,
				new Coordinate(0, -2), new Coordinate(0, 3));
	}

	@Test(expected = HantoException.class)
	public void testInvalidFlight() throws HantoException {
		PieceLocationPair[] toPlace = {
				new PieceLocationPair(HantoPlayerColor.BLUE,
						HantoPieceType.BUTTERFLY, startingLocation),
				new PieceLocationPair(HantoPlayerColor.BLUE,
						HantoPieceType.SPARROW, new Coordinate(0, -1)),
				new PieceLocationPair(HantoPlayerColor.RED,
						HantoPieceType.BUTTERFLY, new Coordinate(0, 1)),
				new PieceLocationPair(HantoPlayerColor.RED,
						HantoPieceType.BUTTERFLY, new Coordinate(0, 2)) };
		game.initializeBoard(toPlace);
		game.setTurnNumber(3);
		game.setPlayerMoving(HantoPlayerColor.BLUE);
		game.makeMove(HantoPieceType.SPARROW,
				new Coordinate(0, -1), new Coordinate(0, 8));
	}

	@Test(expected = HantoException.class)
	public void testInvalidMovement() throws HantoException {
		PieceLocationPair[] toPlace = {
				new PieceLocationPair(HantoPlayerColor.BLUE,
						HantoPieceType.BUTTERFLY, startingLocation),
				new PieceLocationPair(HantoPlayerColor.RED,
						HantoPieceType.BUTTERFLY, new Coordinate(0, 1)) };
		game.initializeBoard(toPlace);
		game.setTurnNumber(2);
		game.setPlayerMoving(HantoPlayerColor.BLUE);
		game.makeMove(HantoPieceType.SPARROW, startingLocation, new Coordinate(
				1, 0));
	}

	@Test
	public void simulateValidBlueWinsGame1() {
		PieceLocationPair[] toPlace = {
				new PieceLocationPair(HantoPlayerColor.BLUE,
						HantoPieceType.BUTTERFLY, startingLocation),
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
		game.initializeBoard(toPlace);
		game.setTurnNumber(5);
		game.setPlayerMoving(HantoPlayerColor.BLUE);
		try {
			assertEquals(MoveResult.OK, game.makeMove(
					HantoPieceType.SPARROW, new Coordinate(-1, 0),
					new Coordinate(-1, 1)));
			assertEquals(MoveResult.OK, game.makeMove(
					HantoPieceType.SPARROW, null,
					new Coordinate(-1, 3)));
			assertEquals(MoveResult.BLUE_WINS, game.makeMove(
					HantoPieceType.SPARROW, new Coordinate(2, -1),
					new Coordinate(1, 0)));
		} catch (HantoException e) {
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	@Test
	public void simulateValidRedWinsGame1() {
		game = theFactory.makeHantoTestGame(HantoGameID.EPSILON_HANTO,
				HantoPlayerColor.RED);
		PieceLocationPair[] toPlace = {
				new PieceLocationPair(HantoPlayerColor.RED,
						HantoPieceType.BUTTERFLY, startingLocation),
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
		game.initializeBoard(toPlace);
		game.setTurnNumber(5);
		game.setPlayerMoving(HantoPlayerColor.RED);
		try {
			assertEquals(MoveResult.OK, game.makeMove(
					HantoPieceType.SPARROW, new Coordinate(-1, 0),
					new Coordinate(-1, 1)));
			assertEquals(MoveResult.OK, game.makeMove(
					HantoPieceType.SPARROW, null,
					new Coordinate(-1, 3)));
			assertEquals(MoveResult.RED_WINS, game.makeMove(
					HantoPieceType.SPARROW, new Coordinate(2, -1),
					new Coordinate(1, 0)));
		} catch (HantoException e) {
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	public void simulateValidDrawGame() {
		PieceLocationPair[] toPlace = {
				new PieceLocationPair(HantoPlayerColor.BLUE,
						HantoPieceType.BUTTERFLY, startingLocation),
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
		game.initializeBoard(toPlace);
		game.setTurnNumber(6);
		game.setPlayerMoving(HantoPlayerColor.RED);
		try {
			assertEquals(MoveResult.DRAW, game.makeMove(
					HantoPieceType.SPARROW, new Coordinate(2, -1),
					new Coordinate(1, 0)));
		} catch (HantoException e) {
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	@Test(expected = HantoException.class)
	public void invalidNoCrabsLeft() throws HantoException {
		PieceLocationPair[] toPlace = {
				new PieceLocationPair(HantoPlayerColor.BLUE,
						HantoPieceType.BUTTERFLY, startingLocation),
				new PieceLocationPair(HantoPlayerColor.BLUE,
						HantoPieceType.CRAB, new Coordinate(1, -1)),
				new PieceLocationPair(HantoPlayerColor.BLUE,
						HantoPieceType.CRAB, new Coordinate(0, -1)),
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
		game.makeMove(HantoPieceType.CRAB, null, new Coordinate(0,
				-2));
	}
}