/*
 * Casey Waddell
 * 4/22/20
 * v1.0
 * This program will play a game of tic tac toe between two users
 */

import java.util.*;

public class TicTacToe 
{
	//create a TicTacToe object, which will allow us to 'return' two ints after one method (so long as we access them in another method
	private int xcoordinate = -1, ycoordinate = -1;
	public TicTacToe(int a, int b)
	{
		xcoordinate = a;
		ycoordinate = b;
	}
	//two accessor methods to retrieve the int data stored in a TicTacToe object
	public int getX()
	{
		return xcoordinate;
	}
	public int getY()
	{
		return ycoordinate;
	}
	
	//---------METHODS------------
	//print will display the gameboard in a legible format
	//@Param char[][] the game board
	//@Return a very long string, the visual representation
	public static String print(char[][] a)
	{
		String output = "";
		for(int row = 0; row < a.length; row++)
		{
			output += row;
			for(int column = 0; column < a[0].length; column++)
			{
				output += "[" + a[row][column] + "] ";
			}
			output += "\n";
		}
		output += "  0   1   2\n";
		return output;
	}
	
	//convert changes a two digit input int into a TicTacToe which stores each digit
	//@Param 2 digit input representing coordinate
	//@Return TicTacToe object which 'returns' two ints now separated
	public static TicTacToe convert(int input)
	{
		int length = String.valueOf(input).length();
		if(length == 2)
		{
			int x = 0, y = 0;
			x = input % 10;
			y = (input - x) / 10;
			TicTacToe output = new TicTacToe(x, y);
			return output;
		}
		else
		{
			TicTacToe error = new TicTacToe(-1, -1);
			return error;
		}
	}
	
	//edit will edit the gameboard to reflect the players turn
	//@Param TicTacToe object representing coordinate, int representing playerturn to decide if x or o, and the gameboard, a char array
	//@Return boolean TRUE indicating if there was an error while doing the players turn
	public static boolean edit(TicTacToe input, int turn, char[][] gameBoard)
	{
		char letter = ' ';
		if((turn % 2) == 0) letter = 'X'; //logic to decide if the turn is X or O's
		else letter = 'O';
		int x = input.getX(); //decompresses the TicTacToe object to access each digit
		int y = input.getY();
		if((y - 1) > 2 || (x - 1) > 2) return true; //sees if the coordinate is out of bounds
		if(gameBoard[x - 1][y - 1] != ' ') return true; //if it's not an empty space, it won't place
		else
		{
			gameBoard[x - 1][y - 1] = letter;	//we subtract one from x and y because we added 11 earlier to made 0,0 work on the coordinate plane
			return false;
		}
	}
	
	//checkWin goes through each pattern that indicates a victory, and sees if that condition has been met
	//@Param the gameBoard to check
	//@Return boolean saying true if someone won
	public static boolean checkWin(char[][] a)
	{
		//vertical check
		if(a[0][0] != ' ' && a[0][0] == a[0][1] && a[0][0] == a[0][2]) return true;
		if(a[1][0] != ' ' && a[1][0] == a[1][1] && a[1][0] == a[1][2]) return true;
		if(a[2][0] != ' ' && a[2][0] == a[2][1] && a[2][0] == a[2][2]) return true;
		//horizontal check
		if(a[0][0] != ' ' && a[0][0] == a[1][0] && a[0][0] == a[2][0]) return true;
		if(a[0][1] != ' ' && a[0][1] == a[1][1] && a[0][1] == a[2][1]) return true;
		if(a[0][2] != ' ' && a[0][2] == a[1][2] && a[0][2] == a[2][2]) return true;
		//diagonal check
		if(a[0][0] != ' ' && a[0][0] == a[1][1] && a[0][0] == a[2][2]) return true;
		if(a[2][0] != ' ' && a[2][0] == a[1][1] && a[2][0] == a[0][2]) return true;
		else return false;
	}		
	
	//play is essentially the ultimate method that uses each other method. Play the game by typing 'play()' into main
	public static void play()
	{
		//initialize needed variables
		Scanner scan = new Scanner(System.in);
		boolean win = false, checkError = false, shouldIBreak = false;
		int turn = 1, playerTurnCalc = 2, playerTurn = 1;
		
		//construct gameboard
		char[][] gameBoard = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
		
		//create while loop to run through to check each turn if anyone has won
		while(win == false)
		{
			if(shouldIBreak == true)
			{
				break;
			}
			checkError = false;
			while(checkError == false)
			{
				//print the display for the users to see
				System.out.println("---TURN " + turn + "---\n" + print(gameBoard) + "------------\n");
				//calculation method to determine which player's turn it is (kind of overly complicated, but I didn't know how else)
				if(playerTurnCalc % 2 == 0) playerTurn = 1;
				else playerTurn = 2;
				System.out.println("Player " + playerTurn + "'s turn.");
				if(turn == 1) System.out.println("Indicate your move by entering the space as a coordinate from the top left of the board being 0,0 and the bottom right being 2, 2\nFor example, space (1, 2) would be represented as '12'");
				int input = (scan.nextInt());
				if((input - 9) % 10 == 0 || input > 99 || input < 0) //checks if the input ends in 9, which shouldn't happen anyways, but if user entered it before it would crash also sees if the user entered a negative
				{
					System.out.println("\nYou entered a problematic coordinate. Either the space was occupied, out of bounds, or something else. Try again.\n");
					break; //if checkError becomes true, the code will reloop so the user can try again
				}
				input = input + 11; //we add an eleven here at the end so we don't get errors when dividing 0s for the coordinate calculations
				TicTacToe coordinateDecision = convert(input); //creates a TicTacToe object to store the two coordinates
				checkError = edit(coordinateDecision, playerTurnCalc, gameBoard); //this will edit the gameboard given everything we know
				if(checkError == true)
				{
					System.out.println("\nYou entered a problematic coordinate. Either the space was occupied, out of bounds, or something else. Try again.\n");
					break; //if checkError becomes true, the code will reloop so the user can try again
				}
				//end of round calculations
				shouldIBreak = checkWin(gameBoard); //checks for victory
				if(shouldIBreak == true)
				{
					break;
				}
				playerTurnCalc++;
				turn++;
				if(turn > 9) //if the game reaches turn 9, the game board is full, which tells us there's a stalemate
				{
					System.out.println("-GAME OVER-\n" + print(gameBoard) + "----------\n");
					System.out.println("STALEMATE");
					scan.close(); //closes scanner just so I don't get that stupid error message
					System.exit(0);
				}
			}
		}
		System.out.println("-GAME OVER-\n" + print(gameBoard) + "----------\n");
		System.out.println("Player " + playerTurn + " wins!");
		scan.close(); //what I said above
		System.exit(0);
	}
	
	public static void main(String[] args)
	{
		play();
	}
}

