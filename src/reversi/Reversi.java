package reversi;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Reversi extends Application
{
	private StackPane sp_main;
	private ReversiControl cc_custom;
	
	public void init() 
	{
		sp_main = new StackPane();
		cc_custom = new ReversiControl();
		sp_main.getChildren().add(cc_custom);
	}

	public void start(Stage primaryStage) 
	{
		primaryStage.setTitle("Reversi");
		primaryStage.setScene(new Scene(sp_main, 800, 800));

		primaryStage.show();
	}

	public void stop() 
	{
		
	}

	static public void main(String[] args)
	{
		launch(args);
	}
	
}