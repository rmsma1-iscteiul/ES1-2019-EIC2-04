package project.gui.controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * 
 * This class controls application GUI , receives and process input from user
 * 
 * @author RuiMenoita
 */
public class Controller extends Application implements Initializable{

	private Stage window;
	private List<String> historico  = new ArrayList<String>();
	
	
	/**
	 * Method called when user clicks on open button inside the file menu
	 *  
	 */
    @FXML
    public void openFile(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("excel", "*.xlsx"));
    	File selectedFile = fc.showOpenDialog(window);
    	System.out.println(selectedFile.getAbsolutePath());
    	historico.add(selectedFile.getAbsolutePath());
    }
    
    public void openRecent(ActionEvent event) {
    	//FileChooser fc = new FileChooser();
    	for(String n : historico) {
    		System.out.println(n);
    	}
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
		this.window = window;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("scene.fxml")); 
		Parent node = loader.load();
		Scene scene = new Scene(node);
		

		window.setScene(scene);
		window.show();
	}
	

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void show(String[] args) {
		launch(args);
	}
	
}