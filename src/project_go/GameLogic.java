package project_go;

public class GameLogic 
{
	public boolean suicideRule(int[][] board, Stone renders[][], int x, int y)
	{
		return true;
	}
	
	public boolean canCapture(int[][] board, Stone renders[][], int x, int y)
	{
		if (board[x - 1][y])
		return true;
	}
}
