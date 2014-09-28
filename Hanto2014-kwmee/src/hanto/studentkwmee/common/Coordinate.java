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

	
}
