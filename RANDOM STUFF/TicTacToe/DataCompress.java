package TicTacToe;
/*
 * Casey Waddell
 * 9/2/20
 * v0.0
 * This program is a way to compress data to unwrap later on when you need it
 */

public class DataCompress 
{
	int xcoordinate = -1;
	int ycoordinate = -1;
	public DataCompress(int a, int b)
	{
		xcoordinate = a;
		ycoordinate = b;
	}
	public int getX()
	{
		 return xcoordinate;
	}
	public int getY()
	{
		return ycoordinate;
	}
	public String toString()
	{
		String returnThis = "";
		returnThis += xcoordinate;
		returnThis += ", ";
		returnThis += ycoordinate;
		return returnThis;
	}
	
}
