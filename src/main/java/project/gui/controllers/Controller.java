package project.gui.controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

	private final static int OPEN_FILE_HISTORY_LENGHT = 3;

	private Stage window;

	private Backend manager;

	@FXML  private TableView<String> table;

	private Map<String,String> openFileHistory = new HashMap<>();




	/**
	 * Displays a dialog chooser to user 
	 * select the file that he want to open
	 */
	@FXML
	public void openFile(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("*(.xlsx) Excel", "*.xlsx"));

		File selectedFile = fc.showOpenDialog(window);

		try {
			loadList(manager.parseFileToMap(selectedFile));

			addRecentOpenFile(selectedFile.getAbsolutePath());
		}catch(Exception e) {
			showErrorDialog(e.getMessage());
		}
	}





	/**
	 * @param selectedFile file to be displayed as a List
	 */
	private void loadList( Map<Integer,List<Object>> map) {
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





	/**
	 * 
	 * @param event
	 */
	@FXML
	public void openRecent(ActionEvent event) {
	//	String fileName = ((MenuItem)(event.getSource())).getText();

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


	@FXML 
	public void getMetricsFromGUI() {
		
	}



	public void initialize(URL location, ResourceBundle resources) {
		manager = new Backend();
		loadList(null);
	}





	public static void show(String[] args) {
		launch(args);
	}

}