package project.gui.controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * This class controls application GUI , receives and process input from user
 * 
 * @author RuiMenoita
 */
public class Controller extends Application{

	
	
	
	/**
	 * Method called when user clicks on open button inside the file menu
	 *  
	 */
    @FXML
    public void openFile(ActionEvent event) {
    	System.out.println("YEAH ! IM A BADASS PROGRAMMER!");
    }
	
	
	/**
	 * This method starts the engine and the platform Thread
	 * 
	 * The Platform Thread is here the User interface will be executed
	 * all the GUI actions must be executed in this Thread (like swing)
	 * 
	 * You can use inside controllers the Method Plataform.runLater(Runnable r ); when 
	 * you need to execute an GUI action that is not called by Platform Thread
	 */
	@Override
	public void start(Stage window) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("scene.fxml")); 
		Parent node = loader.load();
		Scene scene = new Scene(node);

		window.setScene(scene);
		window.show();
	}
	
	
	
	
	public static void show(String[] args) {
		launch(args);
	}
	
}
