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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

	@FXML
	private TextField locText;

	@FXML
	private TextField cycloText;

	@FXML
	private TextField atfdText;

	@FXML
	private TextField laaText;

	@FXML
	private RadioButton andButton;

	@FXML
	private RadioButton orButton;





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
			//loadList(manager.parseFileToMap(selectedFile));
			manager.parseFileToMap(selectedFile);
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
	 *  This method will retrieve the values from the LOC, CYCLO, ATFD and LAA fields, call
	 *  a function and give them as parameters.
	 *  In case, any field is blank, it will throw an error and expect the user to enter the value on all fields.
	 *
	 * @param event
	 */
	@FXML
	public void getMetricsFromGUI(ActionEvent event) {
		String erro = "Please enter the metrics in the following spaces: \n";
		if (locText.getText().isBlank() || cycloText.getText().isBlank() || atfdText.getText().isBlank()
				|| laaText.getText().isBlank()) {
			if(locText.getText().isBlank())
				erro += "LOC \n";
			if(cycloText.getText().isBlank())
				erro += "CYCLO \n";
			if(atfdText.getText().isBlank())
				erro += "ATFD \n";
			if(laaText.getText().isBlank())
				erro += "LAA \n";

			showErrorDialog(erro);
		}else {
			//manager.checkList();
			//loadList();

		}

	}
	
	
	
	
	

	/**
	 *
	 * This method will check if the user selected AND or OR radioButton
	 *  and call a function with the
	 *  In case, any field is blank, it will throw an error and expect the user to enter the value on all fields.
	 *
	 *
	 * @param event
	 */
	@FXML
	public void getAndOrFromGUI(ActionEvent event) {
		if(andButton.isSelected()) {
			System.out.println("AND");
		}else {
			System.out.println("OR");
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