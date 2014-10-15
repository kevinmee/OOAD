/*
 * Coordinate system for Hanto
 * 
 * Kevin Mee, WPI A-term 2014
 */

package hanto.studentkwmee.common;

import java.util.ArrayList;
import java.util.Collection;
import hanto.common.*;


public class Coordinate implements HantoCoordinate {
	int x, y;

	/**
	 * Constructor for the implementation of HantoCoordinate
	 *
	 * @param x
	 *            The x value of the coordinate (using hex style)
	 * @param y
	 *            the y value of the coordinate (using hex style)
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Copy constructor
	 *
	 * @param c
	 */
	public Coordinate(HantoCoordinate c) {
		x = c.getX();
		y = c.getY();
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	/**
	 * @param c
	 *            the HantoCoordinate determine if equal
	 * @return true if the given coordinates match this coordinates
	 */
	public boolean equals(HantoCoordinate c) {
		return (x == c.getX()) && (y == c.getY());
	}

	@Override
	public boolean equals(Object o) {
		boolean equals = false;
		if (o instanceof HantoCoordinate) {
			equals = equals((HantoCoordinate) o);
		}
		return equals;
	}

	/**
	 * Determines if the given coordinate is adjacent to this coordinate
	 *
	 * @param c
	 *            the given coordinate
	 * @return true if the given coordinate is adjacent to this coordinate
	 */
	public boolean isAdjacentTo(HantoCoordinate c) {
		return getDistanceTo(c) == 1;
	}

	/**
	 *
	 * @param to
	 *            The Coordinate where we are calculating the distance to
	 * @return The number of tiles the two tiles are away from each other. If
	 *         they are adjacent, then it should return 1.
	 */
	public int getDistanceTo(HantoCoordinate to) {
		int dx = to.getX() - x;
		int dy = to.getY() - y;
		int distance;
		if (sameSign(dx, dy)) {
			distance = Math.abs(dx + dy);
		} else {
			distance = Math.max(Math.abs(dx), Math.abs(dy));
		}
		return distance;
	}

	private boolean sameSign(int x, int y) {
		return (x < 0 && y < 0) || (y >= 0 && x >= 0);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (x ^ (x >>> 32));
		result = prime * result + (y ^ (y >>> 32));
		return result;
	}

	/**
	 * @return A collection of coordinates adjacent to this coordinate
	 */
	public Collection<HantoCoordinate> getAdjacentCoordinates() {
		Collection<HantoCoordinate> coordinates = new ArrayList<HantoCoordinate>(
				6);
		coordinates.add(new Coordinate(x, y + 1));
		coordinates.add(new Coordinate(x + 1, y));
		coordinates.add(new Coordinate(x + 1, y - 1));
		coordinates.add(new Coordinate(x, y - 1));
		coordinates.add(new Coordinate(x - 1, y));
		coordinates.add(new Coordinate(x - 1, y + 1));
		return coordinates;
		
		
		
	}
}
