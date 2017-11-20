package project_go;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Go extends Application
{
	private StackPane sp_main;
	private CustomControl cc_custom;	
	
	public void init()
	{
		sp_main = new StackPane();
		cc_custom = new CustomControl();
		sp_main.getChildren().add(cc_custom);
		
	}
	
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("Go");
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
