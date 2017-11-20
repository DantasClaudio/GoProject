package project_go;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;

public class CustomControl extends Control
{
	private GoBoard board;

	public CustomControl()
	{
		setSkin(new CustomControlSkin(this));
		board = new GoBoard();
		
		getChildren().add(board);

		setOnMouseClicked(new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent event) 
			{
	//			board.placePiece(event.getX(), event.getY());
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
