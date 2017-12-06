package project_go;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class CustomControl extends Control
{
	private GoBoard board;

	public CustomControl(Label score1, Label score2)
	{
		setSkin(new CustomControlSkin(this));
		board = new GoBoard();
		
		getChildren().add(board);

		setOnMouseClicked(new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent event) 
			{
				board.placePiece(event.getX(), event.getY());
				board.addScore(score1, score2);
			}
		});
	}

	@Override
	public void resize(double width, double height)
	{
		super.resize(width, height);
		board.resize(width, height);
	}
}
