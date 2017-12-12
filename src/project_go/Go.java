package project_go;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Go extends Application
{
	private BorderPane test_ui = new BorderPane();
	private StackPane board_ui = new StackPane();
	private GridPane score_ui = new GridPane();
	private VBox score1_ui = new VBox();
	private VBox score2_ui = new VBox();
	private MenuBar menu_bar = new MenuBar();
	private Menu[] menu_lbl = new Menu[3];
	private MenuItem[] items = new MenuItem[9];
	
	Label[] items_name = new Label[3];
	Label player1_lbl = new Label("White Player");
	Label player2_lbl = new Label("Black Player");
	Label score1_lbl = new Label("0");
	Label score2_lbl = new Label("0");

	private CustomControl cc_custom;
	
	public void init()
	{
		items_name[0] = new Label("Game");
		items_name[1] = new Label("Help");
				
		for (int i = 0; i < 2; i++)
		{
			menu_lbl[i] = new Menu(items_name[i].getText());			
			menu_bar.getMenus().add(menu_lbl[i]);
		}
		
		items[0] = new MenuItem("Pass");
		items[1] = new MenuItem("Reset Board");
		items[2] = new MenuItem("Rules");
		
		items[2].setOnAction(new EventHandler<ActionEvent>() 
		{
		    public void handle(ActionEvent t) 
		    {
		    	 Label rulesLabel = new Label("A game of Go starts with an empty board.\n"
		    	 		+ "Each player has an effectively unlimited supply of pieces (called stones), one taking the black stones, the other taking white.\n"
		    	 		+ "The main object of the game is to use your stones to form territories by surrounding vacant areas of the board.\n"
		    	 		+ "Each one of your stones earns you one point");
		    	 

		    	 rulesLabel.setWrapText(true);
		    	 rulesLabel.setPadding(new Insets(10, 0, 0, 0));
		    	 
		    	 StackPane secondaryLayout = new StackPane();
		    	 secondaryLayout.getChildren().add(rulesLabel);
		    	 
		    	 Scene secondScene = new Scene(secondaryLayout, 300, 200);
		    	 
		    	 Stage newWindow = new Stage();
		    	 newWindow.setTitle("How to Play");
		    	 newWindow.setScene(secondScene);
		    	 
		    	 newWindow.show();			
		    }
		});
		menu_lbl[0].getItems().add(items[0]);
		menu_lbl[0].getItems().add(items[1]);
		menu_lbl[1].getItems().add(items[2]);

		score1_ui.setStyle("-fx-background-color: #FFFFFF;");
		score1_ui.setPrefWidth(200);
		score1_ui.setPrefHeight(Integer.MAX_VALUE / 2);
		score1_ui.getChildren().add(player1_lbl);
		score1_ui.getChildren().add(score1_lbl);
		score1_lbl.setStyle("-fx-font-size: 15 px");
		
		score2_ui.setStyle("-fx-background-color: #000000;");
		score2_ui.setPrefWidth(200);
		score2_ui.setPrefHeight(Integer.MAX_VALUE / 2);
		player2_lbl.setTextFill(Color.WHITE);
		score2_lbl.setTextFill(Color.WHITE);
		score2_ui.getChildren().add(player2_lbl);
		score2_lbl.setStyle("-fx-font-size: 30 px;-fx-underline: true;");
		score2_ui.getChildren().add(score2_lbl);
		cc_custom = new CustomControl(score1_lbl, score2_lbl, items);
		
		score_ui.addRow(0, score1_ui);
		score_ui.addRow(1, score2_ui);
		score_ui.getStylesheets().add("project_go/style.css");
		
		board_ui.getChildren().add(cc_custom);
		
		test_ui.setTop(menu_bar);
		test_ui.setLeft(score_ui);
		test_ui.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		test_ui.setCenter(board_ui);
	}
	
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("Go");
		primaryStage.setScene(new Scene(test_ui, 1000, 800));
		
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