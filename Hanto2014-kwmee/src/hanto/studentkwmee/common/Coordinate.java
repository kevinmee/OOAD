/*
 * Coordinate system for Hanto
 * 
 * Kevin Mee, WPI A-term 2014
 */

package hanto.studentkwmee.common;

import java.util.ArrayList;
import java.util.Collection;
import hanto.common.*;


/**
 */
public class Coordinate implements HantoCoordinate {
	int xCoord, yCoord;

	/**
	 * Constructor for Coordinate.
	 * @param x int
	 * @param y int
	 */
	public Coordinate(int x, int y) {
		xCoord = x;
		yCoord = y;
	}


	/**
	 * Constructor for Coordinate.
	 * @param coord HantoCoordinate
	 */
	public Coordinate(HantoCoordinate coord) {
		xCoord = coord.getX();
		yCoord = coord.getY();
	}

	@Override
	public int getX() {
		return xCoord;
	}

	@Override
	public int getY() {
		return yCoord;
	}

	@Override
	public String toString() {
		return "(" + xCoord + "," + yCoord + ")";
	}


	/**
	 * Method equals.
	 * @param coord HantoCoordinate
	
	 * @return boolean */
	public boolean equals(HantoCoordinate coord) {
		return (xCoord == coord.getX()) && (yCoord == coord.getY());
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
	 * Method isAdjacentTo.
	 * @param coord HantoCoordinate
	
	 * @return boolean */
	public boolean isAdjacentTo(HantoCoordinate coord) {
		return getDistanceTo(coord) == 1;
	}


	/**
	 * Method getDistanceTo.
	 * @param to HantoCoordinate
	
	 * @return int */
	public int getDistanceTo(HantoCoordinate to) {
		int distanceX = to.getX() - xCoord;
		int distanceY = to.getY() - yCoord;
		int distance;
		if (sameSign(distanceX, distanceY)) {
			distance = Math.abs(distanceX + distanceY);
		} else {
			distance = Math.max(Math.abs(distanceX), Math.abs(distanceY));
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
		result = prime * result + (xCoord ^ (xCoord >>> 32));
		result = prime * result + (yCoord ^ (yCoord >>> 32));
		return result;
	}

/**
 * 
 * @return neighbors
 */
	public Collection<HantoCoordinate> getNeighbors() {
		Collection<HantoCoordinate> neighbors = new ArrayList<HantoCoordinate>(
				6);
		neighbors.add(new Coordinate(xCoord, yCoord + 1));
		neighbors.add(new Coordinate(xCoord + 1, yCoord));
		neighbors.add(new Coordinate(xCoord + 1, yCoord - 1));
		neighbors.add(new Coordinate(xCoord, yCoord - 1));
		neighbors.add(new Coordinate(xCoord - 1, yCoord));
		neighbors.add(new Coordinate(xCoord - 1, yCoord + 1));
		
		return neighbors;
		
	}
}
