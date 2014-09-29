package hanto.studentkwmee.common;

import hanto.common.HantoCoordinate;

public class Coordinate implements HantoCoordinate{

	int xCoord, yCoord;
	
	public Coordinate( int x, int y){
		xCoord = x;
		yCoord = y;
	}
	
	public Coordinate( HantoCoordinate hantoCoord){
		xCoord = hantoCoord.getX();
		yCoord = hantoCoord.getY();
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xCoord;
		result = prime * result + yCoord;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (xCoord != other.xCoord)
			return false;
		if (yCoord != other.yCoord)
			return false;
		return true;
	}

	
}
