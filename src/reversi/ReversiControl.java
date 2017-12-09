package reversi;
import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;

public class ReversiControl extends Control
{
	private ReversiBoard board;
	
	public ReversiControl()
	{
		setSkin(new ReversiControlSkin(this));
		board = new ReversiBoard();
		
		getChildren().add(board);

		setOnMouseClicked(new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent event) 
			{
				board.placePiece(event.getX(), event.getY());
				
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
