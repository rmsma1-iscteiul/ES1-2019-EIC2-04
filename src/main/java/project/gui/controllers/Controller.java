package project.gui.controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * 
 * This class controls application GUI , receives and process input from user
 * 
 * @author RuiMenoita
 */
public class Controller extends Application implements Initializable {

	private Stage window;
	private List<String> historico = new ArrayList<String>();

	@FXML
	private TextField locText;

	@FXML
	private TextField cycloText;

	@FXML
	private TextField atfdText;

	@FXML
	private TextField laaText;

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
	
	
	
	

	@FXML
	public void openRecent(ActionEvent event) {
		// FileChooser fc = new FileChooser();
		for (String n : historico) {
			System.out.println(n);
		}
	}
	
	
	

	
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
	 * This method starts the engine and the platform Thread
	 * 
	 * The Platform Thread is here the User interface will be executed all the GUI
	 * actions must be executed in this Thread (like swing)
	 * 
	 * You can use inside controllers the Method Plataform.runLater(Runnable r );
	 * when you need to execute an GUI action that is not called by Platform Thread
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
	
	



	public static void show(String[] args) {
		launch(args);
	}

}