package project_go;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CustomControl extends Control
{
	private GoBoard board;
	private int turnPass = 0;

	public CustomControl(Label score1, Label score2, MenuItem[] items)
	{
		setSkin(new CustomControlSkin(this));
		board = new GoBoard();
	
		getChildren().add(board);

		items[0].setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				board.passTurn(score1, score2);
				turnPass++;
				if (turnPass == 2)
				{
					board.resetBoard(score1, score2);
					Label endLabel = new Label("Both players passed their turn. Game Over!");

					endLabel.setWrapText(true);
					endLabel.setPadding(new Insets(10, 0, 0, 0));
					
					StackPane secondaryLayout = new StackPane();
					secondaryLayout.getChildren().add(endLabel);
					
					Scene secondScene = new Scene(secondaryLayout, 300, 200);
					
					Stage newWindow = new Stage();
					newWindow.setTitle("How to Play");
					newWindow.setScene(secondScene);
				    	 
					newWindow.show();			
				}
			}
		});
		
		items[1].setOnAction(new EventHandler<ActionEvent>() 
		{
			public void handle(ActionEvent event) 
			{
				board.resetBoard(score1, score2);				
			}
		});
		
		setOnMouseClicked(new EventHandler<MouseEvent>() 
		{
			@Override
			public void handle(MouseEvent event) 
			{
				board.placePiece(event.getX(), event.getY(), score1, score2);
				turnPass = 0;
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
