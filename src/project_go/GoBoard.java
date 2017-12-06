package project_go;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.transform.Translate;
import javafx.scene.shape.*;

public class GoBoard extends Pane
{
	private int[][] board;
	private Stone[][] renders;
	private Rectangle background;
	private Line[] horizontal;
	private Line[] vertical;
	private double cell_width, cell_height;
	
	private Translate[] t_horizontal;
	private Translate[] t_vertical;
	private int current_player;
	private int opposing;
	private int player1_score = 0;
	private int player2_score = 0;
	private int[][] surrounding;

	private final int EMPTY = 0;
	private final int WHITE = 1;
	private final int BLACK = 2;

	private GameLogic logic = new GameLogic();
	
	Image img = new Image("file:background.jpg");
	
	public GoBoard()
	{
		background = new Rectangle();
		board = new int[7][7];
		renders = new Stone[7][7];
		horizontal = new Line[7];
		vertical = new Line[7];
		t_horizontal = new Translate[7];
		t_vertical = new Translate[7];
		background.setFill(new ImagePattern(img));

		for (int i = 0; i < 7; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				board[i][j] = EMPTY;
				renders[i][j] = null;
			}
		}
		
		current_player = BLACK;
		getChildren().addAll(background);

		for (int i = 0; i < 7; i++)
		{
			horizontal[i] = new Line();
			vertical[i] = new Line();

			horizontal[i].setStartX(0);
			horizontal[i].setStartY(0);
			horizontal[i].setEndY(0);
			
			vertical[i].setStartX(0);
			vertical[i].setStartY(0);
			vertical[i].setEndX(0);

			t_horizontal[i] = new Translate(0, 0);
			horizontal[i].getTransforms().add(t_horizontal[i]);
			t_vertical[i] = new Translate(0, 0);
			vertical[i].getTransforms().add(t_vertical[i]);
			this.getChildren().addAll(horizontal[i], vertical[i]);
		}	
	}

	@Override
	public void resize(double width, double height)
	{
		int cnt = 0;
		super.resize(width, height);
		cell_width = width / 7.0;
		cell_height = height / 7.0;
		background.setWidth(width);
		background.setHeight(height);
		
		for (int i = 0; i < 7; i++)
		{
			if (i % 2 == 0)
			{
				cnt += 1;
				t_horizontal[i].setY((cnt - .5) * cell_height);
				t_vertical[i].setX((cnt -.5) * cell_width);
			}
			else
			{
				cnt += 1;
				t_horizontal[i].setY((cnt - .5) * cell_height);
				t_vertical[i].setX((cnt - .5) * cell_width);							
			}
			
			horizontal[i].setEndX(width);
			vertical[i].setEndY(height);
		}
		
		for (int i = 0; i < 7; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				if (board[i][j] != EMPTY)
				{
					renders[i][j].resize(cell_width, cell_height);
					renders[i][j].relocate(i * cell_width, j * cell_height);
				}
			}
		}
	}

	public void canCapture(int x, int y)
	{
		if (board[x - 1][y] != current_player && board[x - 1][y] != -1)
		{
			if (board[x - 1][y - 1] == current_player && board[x - 2][y] == current_player)
				System.out.println("Can Capture");
		}

		if (board[x + 1][y] != current_player && board[x + 1][y] != -1)
		{
			if (board[x + 1][y - 1] == current_player && board[x + 2][y] == current_player && board[x + 1][y + 1] == current_player)
				System.out.println("Can Capture");
		}

		if (board[x][y - 1] != current_player && board[x][y - 1] != -1)
		{
			if (board[x - 1][y - 1] == current_player && board[x][y - 2] == current_player && board[x - 1][y - 1] == current_player)
				System.out.println("Can Capture");
		}

		if (board[x][y + 1] != current_player && board[x][y + 1] != -1)
		{
			if (board[x - 1][y + 1] == current_player && board[x][y + 2] == current_player && board[x + 1][y + 1] == current_player)
				System.out.println("Can Capture");		
		}
	}

	public void addScore(Label score1, Label score2)
	{
		if (current_player == WHITE)
		{
			player1_score++;
			score1.setText("" + player1_score);
		}
		else
		{
			player2_score++;
			score2.setText("" + player2_score);
		}
	}
	
	public void placePiece(final double x, final double y)
	{		
		int indexX = (int) (x / cell_width);
		int indexY = (int) (y / cell_width);

		if (board[indexX][indexY] == EMPTY && current_player == BLACK)
		{
			board[indexX][indexY] = BLACK;
			renders[indexX][indexY] = new Stone(BLACK);
			renders[indexX][indexY].resize(cell_width, cell_height);
			renders[indexX][indexY].relocate(indexX * cell_width, indexY * cell_height);
			this.getChildren().add(renders[indexX][indexY]);
			canCapture(indexX, indexY);
			current_player = WHITE;
		}
		
		else if (board[indexX][indexY] == EMPTY && current_player == WHITE)
		{
			board[indexX][indexY] = WHITE;
			renders[indexX][indexY] = new Stone(WHITE);
			renders[indexX][indexY].resize(cell_width, cell_height);
			renders[indexX][indexY].relocate(indexX * cell_width, indexY * cell_height);
			this.getChildren().add(renders[indexX][indexY]);
			canCapture(indexX, indexY);
			current_player = BLACK;			
		}
	}
}
