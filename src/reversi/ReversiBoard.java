package reversi;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

// TO-DO : placeAndReverse();

public class ReversiBoard extends Pane
{
	private int[][] board;
	private ReversiPiece[][] renders;
	private Rectangle background;
	private Line[] horizontal;
	private Line[] vertical;
	private double cell_width, cell_height;

	private Translate[] horizontal_t;
	private Translate[] vertical_t;
	private int current_player;
	private int opposing;
	private boolean in_play;
	private int player1_score;
	private int player2_score;
	private int[][] surrounding;
	private boolean[][] can_reverse;

	private static final int[] x_offset = {-1, -1, -1,  0,  0,  1,  1,  1};

	private static final int[] y_offset = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	private final int EMPTY = 0;
	private final int WHITE = 1;
	private final int BLACK = 2;
	
	public ReversiBoard()
	{
		board = new int[8][8];
		renders = new ReversiPiece[8][8];
		horizontal = new Line[8];
		vertical = new Line[8];
		horizontal_t = new Translate[8];
		vertical_t = new Translate[8];
		surrounding = new int[3][3];
		can_reverse = new boolean[3][3];

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				can_reverse[i][j] = false;
		
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if ((i == 3 && j == 3) || (i == 4 && j == 4))
				{
					board[i][j] = WHITE;
					renders[i][j] = new ReversiPiece(WHITE);
				}	
				else if ((i == 3 && j == 4) || (i == 4 && j == 3))
				{
					board[i][j] = BLACK;
					renders[i][j] = new ReversiPiece(BLACK);
				}
				else
				{
					board[i][j] = EMPTY;
					renders[i][j] = null;
				}
			}
		}

		current_player = BLACK;
		background = new Rectangle();
		background.setFill(Color.TURQUOISE);
		getChildren().addAll(background);
		
		for (int i = 0; i < 8; i++)
		{
			horizontal[i] = new Line();
			vertical[i] = new Line();

			horizontal[i].setStartX(0);
			horizontal[i].setStartY(0);
			horizontal[i].setEndY(0);
			
			vertical[i].setStartX(0);
			vertical[i].setStartY(0);
			vertical[i].setEndX(0);

			horizontal_t[i] = new Translate(0, 0);
			horizontal[i].getTransforms().add(horizontal_t[i]);
			vertical_t[i] = new Translate(0, 0);
			vertical[i].getTransforms().add(vertical_t[i]);
			this.getChildren().addAll(horizontal[i], vertical[i]);
		}
		
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (board[i][j] != EMPTY)
				{
					renders[i][j].resize(cell_width, cell_height);
					renders[i][j].relocate(i * cell_width, j * cell_height);
					getChildren().add(renders[i][j]);
				}
			}
		}
	}
	
	@Override
	public void resize(double width, double height)
	{
		int cnt = 0;
		super.resize(width, height);
		cell_width = width / 8.0;
		cell_height = height / 8.0;
		background.setWidth(width);
		background.setHeight(height);
		
		for (int i = 0; i < 8; i++)
		{
			if (i % 2 == 0)
			{
				cnt += 1;
				horizontal_t[i].setY(cnt * cell_height);
				vertical_t[i].setX(cnt * cell_width);
			}
			else
			{
				cnt += 1;
				horizontal_t[i].setY(cnt * cell_height);
				vertical_t[i].setX(cnt * cell_width);							
			}
			
			horizontal[i].setEndX(width);
			vertical[i].setEndY(height);
		}
		
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (board[i][j] != EMPTY)
				{
					renders[i][j].resize(cell_width, cell_height);
					renders[i][j].relocate(i * cell_width, j * cell_height);
				}
			}
		}
	}
	
	public void resetGame()
	{
		
	}

	public int getPiece(int x, int y)
	{
		if (x > 7 || y > 7 || x < 0 || y < 0)
			return -1;
		return board[x][y];
	}
	
	public void determineSurrounding(int x, int y)
	{
		int dx = -1;
		int dy = -1;
		
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				surrounding[i][j] = getPiece(x + dx, y + dy);
				dx++;
			}
			dx = -1;
			dy++;
		}
	}
	
	public boolean adjacentOpposingPiece()
	{
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (surrounding[i][j] != current_player && surrounding[i][j] != 0 && surrounding[i][j] != -1)
					return true;

		return false;
	}

	public boolean determineReverse(int x, int y)
	{	
		int opposing = (current_player == BLACK) ? WHITE : BLACK;
		boolean validMove = false;
		
		for (int i = 0; i < 8; i++)
		{
			boolean hasOpponent = false;
			int row = x + x_offset[i];
			int col = y + y_offset[i];

			while (row >= 0 && row < 8 && col >= 0 && col < 8)
			{
				if (getPiece(row, col) == opposing)
					hasOpponent = true;
				else if (getPiece(row, col) == current_player && hasOpponent)
				{
					can_reverse[y_offset[i] + 1][x_offset[i] + 1] = true;
					validMove = true;
					break;
				}
				else
					break;

				row += x_offset[i];
				col += y_offset[i];
			}
			if (validMove)
				break;
		}
		return validMove;
	}

	public void placeAndReverse(int x, int y)
	{

	}
	
	public void placePiece(final double x, final double y)
	{
		int indexX = (int) (x / cell_width);
		int indexY = (int) (y / cell_width);

		if(renders[indexX][indexY] != null)
			return;
					
		determineSurrounding(indexX, indexY);
		
		if (!adjacentOpposingPiece())
			return;
		
		if (!determineReverse(indexX, indexY))
			return;
		
		if (board[indexX][indexY] == EMPTY && current_player == BLACK)
		{
			board[indexX][indexY] = BLACK;
			renders[indexX][indexY] = new ReversiPiece(BLACK);
			renders[indexX][indexY].resize(cell_width, cell_height);
			renders[indexX][indexY].relocate(indexX * cell_width, indexY * cell_height);
			this.getChildren().add(renders[indexX][indexY]);
			current_player = WHITE;
		}
		
		else if (board[indexX][indexY] == EMPTY && current_player == WHITE)
		{
			board[indexX][indexY] = WHITE;
			renders[indexX][indexY] = new ReversiPiece(WHITE);
			renders[indexX][indexY].resize(cell_width, cell_height);
			renders[indexX][indexY].relocate(indexX * cell_width, indexY * cell_height);
			this.getChildren().add(renders[indexX][indexY]);
			current_player = BLACK;			
		}
		
		System.out.println("placed at: " + indexX + ", " + indexY);
		System.out.println("White: " + player1_score + " Black: " + player2_score);
	}
}
