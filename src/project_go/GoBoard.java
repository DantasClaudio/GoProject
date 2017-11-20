package project_go;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
	private int player1_score;
	private int player2_score;
	private int[][] surrounding;

	private final int EMPTY = 0;
	private final int WHITE = 1;
	private final int BLACK = 2;
	
	public GoBoard()
	{
		background = new Rectangle();
		board = new int [7][7];
		renders = new Stone[7][7];
		horizontal = new Line[9];
		vertical = new Line[9];
		t_horizontal = new Translate[9];
		t_vertical = new Translate[9];

		for (int i = 0; i < 7; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				board[i][j] = EMPTY;
				renders[i][j] = null;
			}
		}
		
		current_player = BLACK;
		background.setFill(Color.ROSYBROWN);
		getChildren().addAll(background);

		for (int i = 0; i < 9; i++)
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
		cell_width = width / 9.0;
		cell_height = height / 9.0;
		background.setWidth(width);
		background.setHeight(height);
		
		for (int i = 0; i < 9; i++)
		{
			if (i % 2 == 0)
			{
				cnt += 1;
				t_horizontal[i].setY(cnt * cell_height);
				t_vertical[i].setX(cnt * cell_width);
			}
			else
			{
				cnt += 1;
				t_horizontal[i].setY(cnt * cell_height);
				t_vertical[i].setX(cnt * cell_width);							
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
}
