package project.gui.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
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
public class Controller extends Application implements Initializable {

	private static final String PROGRAM_NAME = "Code Analyzer";

	private final static int OPEN_FILE_HISTORY_LENGHT = 3;
	private String[] openFileHistory = new String[OPEN_FILE_HISTORY_LENGHT];

	private Stage window;

	private Backend manager;

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

	@FXML
	private AnchorPane dataTabPane;
	@FXML
	private TableView<DataContainer> table;
	@FXML
	private Menu openRecentMenu;

	@FXML
	private Label DCI;
	@FXML
	private Label DII;
	@FXML
	private Label ADCI;
	@FXML
	private Label ADII;

	@FXML
	private Text DCItext;
	@FXML
	private Text DIItext;
	@FXML
	private Text ADCItext;
	@FXML
	private Text ADIItext;

	@FXML
	private PieChart pieChart;
	@FXML
	private StackedBarChart<String, Number> barChart;
	@FXML
	private HBox hboxChart;

	private Boolean logicSelector = false; // AND = FALSE, OR = TRUE

	/**
	 * Displays a dialog chooser to user select the file that he want to open
	 */
	@FXML
	public void openFile(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("*(.xlsx) Excel", "*.xlsx"));

		File selectedFile = fc.showOpenDialog(window);

		try {
			loadList(manager.parseFileToMap(selectedFile));

			addRecentOpenFile(selectedFile.getAbsolutePath());

			// window.setTitle(PROGRAM_NAME+ " ( "+selectedFile.getName()+" )");
		} catch (Exception e) {
			e.printStackTrace();
			showErrorDialog(e.getMessage());
		}
	}

	/**
	 * @param rowList List to be load on TableView table
	 */
	private void loadList(List<DataContainer> rowList) {
		table.getItems().clear();
		table.getItems().addAll(FXCollections.observableList(rowList));
	}

	
	
	
	
	/**
	 *
	 * This method saves the file path in history
	 *
	 * @param absolutePath Path of recent open file
	 */
	// TODO
	public void addRecentOpenFile(String absolutePath) {
		if (!ArrayUtil.contains(openFileHistory, absolutePath)) {
			ArrayUtil.shiftRight(openFileHistory);
			openFileHistory[0] = absolutePath;
		} else {
			int index = ArrayUtil.getIndex(openFileHistory, absolutePath);
			String buffer = openFileHistory[index];
			openFileHistory[index] = null;
			ArrayUtil.shiftRight(openFileHistory);
			openFileHistory[0] = buffer;
		}

		openRecentMenu.getItems().clear();

		for (String path : openFileHistory) {
			if (path != null) {
				MenuItem item = new MenuItem(path.split("\\\\")[path.split("\\\\").length - 1]);
				openRecentMenu.getItems().add(item);
			}
		}
	}

	/**
	 *
	 * @param event
	 */
	@FXML
	public void openRecent(ActionEvent event) {
		// String fileName = ((MenuItem)(event.getSource())).getText();

	}

	/**
	 * This method will retrieve the values from the LOC, CYCLO, ATFD and LAA
	 * fields, call a function and give them as parameters. In case, any field is
	 * blank, it will assume a predefined value.
	 *
	 * @param event
	 */
	public void getMetrics() {
		if (locText.getText().matches("[0-9]*") || cycloText.getText().matches("[0-9]*") ||
				atfdText.getText().matches("[0-9]*") || laaText.getText().matches("[0-9]*")) {
			if (!locText.getText().isBlank()) {
				manager.setLOC(Double.parseDouble(locText.getText()));
			}
			if (!cycloText.getText().isBlank()) {
				manager.setCYCLO(Double.parseDouble(cycloText.getText()));
			}
			if (!atfdText.getText().isBlank()) {
				manager.setATFD(Double.parseDouble(atfdText.getText()));
			}
			if (!laaText.getText().isBlank()) {
				manager.setLAA(Double.parseDouble(laaText.getText()));
			}
		}else {
			showErrorDialog("Only numbers allowed");
		}

		// manager.checkList();
		// loadList(manager.checkList(logicSelector));
		getAndOr();

	}

	/**
	 *
	 * This method will check if the user selected AND or OR radioButton
	 *
	 * @param event
	 */
	public void getAndOr() {
		if (andButton.isSelected())
			logicSelector = false;
		if (orButton.isSelected())
			logicSelector = true;

	}

	/**
	 *
	 * This method will get the total values from DCI,DII,ADCI,ADII and show them to
	 * the user in the form of results
	 *
	 * @param totalDCI
	 * @param totalDII
	 * @param totalADCI
	 * @param totalADII
	 */
	public void setQualityIndicatorsTotals() {
		float total = manager.getPdci() + manager.getPdii() + manager.getPadci() + manager.getPadii();
		double dciP = 0;
		double diiP = 0;
		double adciP = 0;
		double adiiP = 0;

		DCI.setText(Integer.toString(manager.getPdci()));
		DII.setText(Integer.toString(manager.getPdii()));
		ADCI.setText(Integer.toString(manager.getPadci()));
		ADII.setText(Integer.toString(manager.getPadii()));
		
		dciP =(int) Math.round((((float)manager.getPdci() / total)) * 100.0);
		diiP =(int) Math.round((((float)manager.getPdii() / total)) * 100.0);
		adciP =(int) Math.round((((float)manager.getPadci() / total)) * 100.0);
		adiiP =(int) Math.round((((float)manager.getPadii() / total)) * 100.0);
		
		DCItext.setText(Double.toString(dciP) + "%");
		DIItext.setText(Double.toString(diiP) + "%");
		ADCItext.setText(Double.toString(adciP) + "%");
		ADIItext.setText(Double.toString(adiiP) + "%");

		configurePieChart();

		configureStackedBarChart();

	}

	private void configurePieChart() {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("DCI", manager.getPdci()), new PieChart.Data("DII", manager.getPdii()),
				new PieChart.Data("ADCI", manager.getPadci()), new PieChart.Data("ADII", manager.getPadii()));
		pieChart.setData(pieChartData);
		pieChart.setTitle("Quality Indicators");
		pieChart.setLegendSide(Side.LEFT);
	}

	private void configureStackedBarChart() {

		hboxChart.getChildren().remove(barChart);

		CategoryAxis xAxis = new CategoryAxis(); // String

		xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("DCI", "DII", "ADCI", "ADII")));

		xAxis.setLabel("Quality Indicators");

		NumberAxis yAxis = new NumberAxis(); // int
		yAxis.setLabel("Value in units");

		barChart = new StackedBarChart<>(xAxis, yAxis);

		XYChart.Series<String, Number> data = new XYChart.Series<>();
		data.getData().add(new XYChart.Data<>("DCI", manager.getPdci()));
		data.getData().add(new XYChart.Data<>("DII", manager.getPdii()));
		data.getData().add(new XYChart.Data<>("ADCI", manager.getPadci()));
		data.getData().add(new XYChart.Data<>("ADII", manager.getPadii()));

		barChart.getData().addAll(data);

		hboxChart.getChildren().add(barChart);

	}

	/**
	 * This method will be called when the Apply button is pressed
	 *
	 *
	 * @param event
	 */
	@FXML
	public void applyPressed(ActionEvent event) {
		getMetrics();
		manager.calculateIndicators(logicSelector);
		setQualityIndicatorsTotals();
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
	 * Displays a error dialog with @param error message
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("scene.fxml"));
			Parent node = loader.load();
			Scene scene = new Scene(node);
			Stage stage = new Stage();

			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
			showErrorDialog(e.getMessage());
		}
	}

	/**
	 * This method is called when Scenes.fxml is load
	 *
	 * This method instantiates manager and creates the Column table that will be
	 * added to TableView
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initialize(URL location, ResourceBundle resources) {
		manager = new Backend();

		TableColumn<DataContainer, ?> col = null;

		for (String[] data : DataContainer.getAttributes()) {

			if (data[1].equals("Integer")) { // Integer Column Type
				col = new TableColumn<DataContainer, Integer>(data[0]);
				col.setCellValueFactory(new PropertyValueFactory(data[2]));

			} else if (data[1].equals("Double")) { // Double Column Type
				col = new TableColumn<DataContainer, Double>(data[0]);
				col.setCellValueFactory(new PropertyValueFactory(data[2]));

			} else if (data[1].equals("String")) { // String Column Type
				col = new TableColumn<DataContainer, String>(data[0]);
				col.setCellValueFactory(new PropertyValueFactory(data[2]));

			} else if (data[1].equals("Boolean")) { // Boolean Column Type
				col = new TableColumn<DataContainer, Boolean>(data[0]);
				col.setCellValueFactory(new PropertyValueFactory(data[2]));
			}

			table.getColumns().add(col);
		}

		setBindings();
		locText.setPromptText("80");
		cycloText.setPromptText("10");
		atfdText.setPromptText("4");
		laaText.setPromptText("0.42");
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
