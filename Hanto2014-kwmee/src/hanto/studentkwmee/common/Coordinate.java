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
		int hash = 0;
		hash += Integer.valueOf(xCoord).hashCode();
		hash += Integer.valueOf(yCoord).hashCode();
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		
		boolean isEqual = false;
		if( this == obj){
			isEqual = true;
		}
		else if( obj instanceof Coordinate){
			final Coordinate otherCoord = (Coordinate) obj; 
			if( xCoord == otherCoord.getX() && yCoord == otherCoord.getY()){
				isEqual = true;
			}
		}
		return isEqual;
	}

	public String toString(){
		return "(" + xCoord + ", " + yCoord + ")";
	}
	
}
