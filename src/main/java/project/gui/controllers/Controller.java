package project.gui.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.Menu;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import project.backend.Backend;
import project.backend.containers.DataContainer;
import project.utils.ArrayUtil;

/**
 * 
 * This class controls application GUI , receives and process input from user
 * 
 * @author RuiMenoita
 */
public class Controller extends Application implements Initializable{

	private static final String PROGRAM_NAME = "Code Analyzer";
	
	private final static int OPEN_FILE_HISTORY_LENGHT = 3;
	private String[] openFileHistory = new String[OPEN_FILE_HISTORY_LENGHT];

	private Stage window;

	private Backend manager;

	@FXML private TextField locText;
	@FXML private TextField cycloText;
	@FXML private TextField atfdText;
	@FXML private TextField laaText;
	
	@FXML private RadioButton andButton;
	@FXML private RadioButton orButton;

	@FXML private AnchorPane dataTabPane;
	@FXML private TableView<DataContainer> table;
	@FXML private Menu openRecentMenu;



	
	
	
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
			
//			window.setTitle(PROGRAM_NAME+ " ( "+selectedFile.getName()+" )"); 
		}catch(Exception e) {
			e.printStackTrace();
			showErrorDialog(e.getMessage());
		}
	}





	/**
	 * @param rowList List to be load on TableView table
	 */
	private void loadList( List<DataContainer> rowList) {
		table.getItems().clear();
		table.getItems().addAll(FXCollections.observableList(rowList));
	}





	/**
	 * 
	 * This method saves the file path in history
	 * 
	 * @param absolutePath Path of recent open file
	 */
	private void addRecentOpenFile(String absolutePath) {
		if(!ArrayUtil.contains(openFileHistory, absolutePath)) {
			ArrayUtil.shiftRight(openFileHistory);
			openFileHistory[0] = absolutePath;
		}
			
		openRecentMenu.getItems().clear();
		
		for (String path : openFileHistory) {
			String name = path.split("/")[path.split("/").length];
			for (String anotherPath : openFileHistory) {
				if(!path.equals(anotherPath))
					name = getNoCommonPastDir(path,anotherPath);
					//TODO
			}
		}
		

	}





	private String getNoCommonPastDir(String path, String anotherPath) {
		//TODO
		return null;
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
	public void start(Stage stage) throws Exception {
		this.window = stage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("scene.fxml")); 
		Parent node = loader.load();
		Scene scene = new Scene(node);


		window.setScene(scene);
		window.setTitle(PROGRAM_NAME);
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



	
	
	/**
	 * Opens a new project Window 
	 */
	@FXML
	public void openNewWindow(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("dataVisualizerScene.fxml")); 
			Parent node = loader.load();
			Scene scene = new Scene(node);
			Stage stage = new Stage();

			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * This method is called when Scenes.fxml is load
	 * 
	 * This method instantiates manager and creates the Column table that will 
	 * be added to TableView
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initialize(URL location, ResourceBundle resources) {
		manager = new Backend();


		TableColumn<DataContainer,?> col = null;

		for (String[] data : DataContainer.getAttributes()) {

			if(data[1].equals("Integer")) {									//Integer Column Type
				col = new TableColumn<DataContainer, Integer>(data[0]);
				col.setCellValueFactory(new PropertyValueFactory(data[2]));

			}else if(data[1].equals("Double")) {							//Double Column Type
				col = new TableColumn<DataContainer, Double> (data[0]);
				col.setCellValueFactory(new PropertyValueFactory(data[2]));

			}else if(data[1].equals("String")) {							//String Column Type
				col = new TableColumn<DataContainer, String>(data[0]);
				col.setCellValueFactory(new PropertyValueFactory(data[2]));

			}else if(data[1].equals("Boolean")) {							//Boolean Column Type
				col = new TableColumn<DataContainer, Boolean>(data[0]);
				col.setCellValueFactory(new PropertyValueFactory(data[2]));
			}

			table.getColumns().add(col);
		}
		
		setBindings();
	}


	
	
	
	
	/**
	 * This makes table size response when Data tab is resized
	 */
	private void setBindings() {
		dataTabPane.heightProperty().addListener((obs, old, newValue) -> table.setPrefHeight(newValue.doubleValue()));
		dataTabPane.widthProperty().addListener((obs, old, newValue) -> table.setPrefWidth(newValue.doubleValue()));
	}


	
	

	/**
	 * Calls the application start method that creates the platform thread
	 */
	public static void show(String[] args) {
		launch(args);
	}

}