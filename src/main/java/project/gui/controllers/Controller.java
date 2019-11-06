package project.gui.controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import project.backend.Backend;

/**
 * 
 * This class controls application GUI , receives and process input from user
 * 
 * @author RuiMenoita
 */
public class Controller extends Application implements Initializable{

	private final static int HISTORY_LENGHT = 3;
	
	private Stage window;
	
	private Backend manager;
	
	
	/**
	 * Displays a dialog chooser to user 
	 * select the file that he want to open
	 */
    @FXML
    public void openFile(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("*(.xlsx) Excel", "*.xlsx"));
    	
    	File selectedFile = fc.showOpenDialog(window);
    	
    	loadList(selectedFile);
    }
    
    
    
    
    
    /**
     * 
     * @param selectedFile file to be displayed as a List
     */
    private void loadList(File selectedFile) {
		manager = new Backend();
		
		
		
		
		
	}





	/**
     * 
     * This method saves the file path in history
     * 
     * @param absolutePath Path of recent open file
     */
    private void addRecentOpenFile(String absolutePath) {
		// TODO Auto-generated method stub
		
	}

    
    
    
    
    
    @FXML
	public void openRecent(ActionEvent event) {
		//TODO
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
	
	
	
	
	/**
	 * Displays a error dialog with  @param error message
	 */
	private void showErrorDialog(String error) {
		Platform.runLater(() -> {
			Alert dialog = new Alert(AlertType.ERROR);
			dialog.setTitle("Error");
			dialog.setContentText(error);
			dialog.initOwner(window);
			dialog.show();
		});
	}
	
	
	
	
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	public static void show(String[] args) {
		launch(args);
	}
	
}