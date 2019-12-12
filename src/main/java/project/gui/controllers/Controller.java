package project.gui.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import project.backend.Backend;
import project.backend.containers.DataContainer;
import project.backend.containers.MetricsRule;

/**
 *
 * This class controls application GUI , receives and process input from user
 *
 * @author RuiMenoita and FranciscoCardoso
 */
public class Controller extends Application implements Initializable {

	private static final String PROGRAM_NAME = "Code Analyzer";

	private Stage window;
	private Backend manager;

	@FXML private AnchorPane dataTabPane;
	@FXML private TableView<DataContainer> table;
	@FXML private Menu openRecentMenu;

	@FXML private Text DCItext;
	@FXML private Text DIItext;
	@FXML private Text ADCItext;
	@FXML private Text ADIItext;

	@FXML private PieChart pieChartiPlasma;
	@FXML private PieChart pieChartPMD;
	@FXML private PieChart pieChartNewRule;

	@FXML private StackedBarChart<String, Number> barChartPMD;
	@FXML private StackedBarChart<String, Number> barChartiPlasma;
	@FXML private StackedBarChart<String, Number> barChartFE;
	@FXML private StackedBarChart<String, Number> barChartLM;

	@FXML private HBox hboxChartPMD;
	@FXML private HBox hboxChartiPlasma;
	@FXML private HBox hboxChartFE;
	@FXML private HBox hboxChartLM;
	@FXML private HBox hboxChartLE;
	@FXML private HBox hboxChartFM;


	//-----------------------------
	//LOC
	@FXML private TextField locText;
	@FXML private ChoiceBox<String> locBiggerThanSelector;

	//AND OR
	@FXML private RadioButton locCycloAndButton;

	//CYCLO
	@FXML private TextField cycloText;
	@FXML private ChoiceBox<String> cycloBiggerThanSelector;

	//AFTD
	@FXML private TextField atfdText;
	@FXML private ChoiceBox<String> atfdBiggerThanSelector;

	//AND OR
	@FXML private RadioButton atfdLaaAndButton;

	//LAA
	@FXML private TextField laaText;
	@FXML private ChoiceBox<String> laaBiggerThanSelector;
	//----------------------------

	@FXML private ListView<MetricsRule> metricList;





	/**
	 * Saves metric in a list
	 * @param event
	 */
	@FXML
	public void saveMetric(ActionEvent event) {
		if(validateInput()) {
			String name = getAndShowInputTextDialog("Please enter metric name:");
			System.out.println(name);
			//call backend to create metricRule
			//add metric to list
			//TODO Rui Menoita
		}
	}









	/**
	 * If this return true it means inputs are valid otherwise they are invalid
	 */
	private boolean validateInput() {
		if (!locText.getText().matches("[0-9]{1,}") &&  locText.getText().isBlank()){
			showErrorDialog("Loc must be a number and not empty");
			return false;
		}
		if (!cycloText.getText().matches("[0-9]{1,}") &&  cycloText.getText().isBlank()){
			showErrorDialog("Cyclo must be a number and not empty");
			return false;
		}
		if (!atfdText.getText().matches("[0-9]{1,}") &&  atfdText.getText().isBlank()){
			showErrorDialog("Atfd must be a number and not empty");
			return false;
		}
		if (!laaText.getText().isBlank()){
			try {
				if(Double.parseDouble(laaText.getText())<0 || Double.parseDouble(laaText.getText())>1) {
					showErrorDialog("Laa must be a number bigger than 0 and less then 1 ");
					return false;
				}
			}catch(NumberFormatException e ) {
				showErrorDialog("Laa must be a number");
			}
		}else {
			showErrorDialog("Laa must be a number and not empty");
			return false;
		}
		return true;
	}









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

			//Calculates everything without the need to press the apply button
			//manager.calculateIndicators();
			setQualityIndicatorsTotals();

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









	/**
	 * Creates and adds to the UI a Pie Chart for the iPlasma, PMD and (if selected) the new Rule
	 */
	private void configurePieChart() {
		iPlasmaPieChart();
		pmdPieChart();		
	}









	/**
	 * Creates pie chart for iPlasma
	 */
	private void iPlasmaPieChart() {
		//create iPlasma PieChart
		ObservableList<PieChart.Data> pieChartDataiPlasma = FXCollections.observableArrayList(
				new PieChart.Data("DCI", manager.getIpdci()), new PieChart.Data("DII", manager.getIpdii()),
				new PieChart.Data("ADCI", manager.getIpadci()), new PieChart.Data("ADII", manager.getIpadii()));
		pieChartiPlasma.setData(pieChartDataiPlasma);
		pieChartiPlasma.setTitle("iPlasma");
		pieChartiPlasma.setLegendSide(Side.LEFT);
	}









	/**
	 * Creates pie chart for PMD
	 */
	private void pmdPieChart() {
		//create PMD PieChart
		ObservableList<PieChart.Data> pieChartDataPMD = FXCollections.observableArrayList(
				new PieChart.Data("DCI", manager.getPdci()), new PieChart.Data("DII", manager.getPdii()),
				new PieChart.Data("ADCI", manager.getPadci()), new PieChart.Data("ADII", manager.getPadii()));
		//pieChartDataPMD.
		pieChartPMD.setData(pieChartDataPMD);
		pieChartPMD.setTitle("PMD");
		pieChartPMD.setLegendSide(Side.LEFT);
	}






	/**
	 * Creates pie chart for PMD
	 */
	private void lmPieChart() {
		//create PMD PieChart
		ObservableList<PieChart.Data> pieChartDataLM = FXCollections.observableArrayList(
				new PieChart.Data("DCI", manager.getmLMdci()), new PieChart.Data("DII", manager.getmLMdii()),
				new PieChart.Data("ADCI", manager.getmLMadci()), new PieChart.Data("ADII", manager.getmLMadii()));
		//pieChartDataPMD.
		pieChartPMD.setData(pieChartDataLM);
		pieChartPMD.setTitle("Long Method");
		pieChartPMD.setLegendSide(Side.LEFT);
	}
	
	
	
	
	
	
	
	
	/**
	 * Creates pie chart for PMD
	 */
	private void fePieChart() {
		//create PMD PieChart
		ObservableList<PieChart.Data> pieChartDataFE = FXCollections.observableArrayList(
				new PieChart.Data("DCI", manager.getmFEdci()), new PieChart.Data("DII", manager.getmFEdii()),
				new PieChart.Data("ADCI", manager.getmFEadci()), new PieChart.Data("ADII", manager.getmFEadii()));
		//pieChartDataPMD.
		pieChartPMD.setData(pieChartDataFE);
		pieChartPMD.setTitle("Feature Envy");
		pieChartPMD.setLegendSide(Side.LEFT);
	}












	/**
	 * 
	 * This method will create the iPlasma and PMD bar charts
	 * 
	 */
	private void configureStackedBarChart() {
		pmdBarChart();
		iPlasmaBarChart();
		//		if(){
		//			newRuleBarChart();
		//		}

	}









	/**
	 * Creates PMD bar chart
	 */
	@SuppressWarnings("unchecked")
	private void pmdBarChart() {

		//creating/setting up PMD bar chart
		hboxChartPMD.getChildren().remove(barChartPMD);

		CategoryAxis xAxisPMD = new CategoryAxis(); // String

		xAxisPMD.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("DCI", "DII", "ADCI", "ADII")));

		NumberAxis yAxisPMD = new NumberAxis(); // int

		barChartPMD = new StackedBarChart<>(xAxisPMD, yAxisPMD);

		//creates PMD data
		XYChart.Series<String, Number> dataPMD = new XYChart.Series<>();
		dataPMD.getData().add(new XYChart.Data<>("DCI", manager.getPdci()));
		dataPMD.getData().add(new XYChart.Data<>("DII", manager.getPdii()));
		dataPMD.getData().add(new XYChart.Data<>("ADCI", manager.getPadci()));
		dataPMD.getData().add(new XYChart.Data<>("ADII", manager.getPadii()));

		//sets the data in the StackedBarChart
		barChartPMD.getData().addAll(dataPMD);

		//draws it on the hbox
		hboxChartPMD.getChildren().add(barChartPMD);


	}









	/**
	 * Creates iPlasma bar chart
	 */
	@SuppressWarnings("unchecked")
	private void iPlasmaBarChart() {
		//creating/setting up iPlasma bar chart
		hboxChartiPlasma.getChildren().remove(barChartiPlasma);

		CategoryAxis xAxisiPlasma = new CategoryAxis(); // String

		xAxisiPlasma.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("DCI", "DII", "ADCI", "ADII")));

		NumberAxis yAxisiPlasma = new NumberAxis(); // int

		barChartiPlasma = new StackedBarChart<>(xAxisiPlasma, yAxisiPlasma);


		//creates iPlasma data
		XYChart.Series<String, Number> dataiPlasma = new XYChart.Series<>();
		dataiPlasma.getData().add(new XYChart.Data<>("DCI", manager.getIpdci()));
		dataiPlasma.getData().add(new XYChart.Data<>("DII", manager.getIpdii()));
		dataiPlasma.getData().add(new XYChart.Data<>("ADCI", manager.getIpadci()));
		dataiPlasma.getData().add(new XYChart.Data<>("ADII", manager.getIpadii()));

		//sets the data in the StackedBarChart
		barChartiPlasma.getData().addAll(dataiPlasma);

		//draws it on the hbox
		hboxChartiPlasma.getChildren().add(barChartiPlasma);
	}









	/**
	 * Creates bar chart for the new rule selected 
	 */
	@SuppressWarnings("unchecked")
	private void newRuleFEBarChart() {
		//creating/setting up Feature Envy bar chart
		hboxChartFE.getChildren().remove(barChartFE);

		CategoryAxis xAxisNewRule = new CategoryAxis(); // String

		xAxisNewRule.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("DCI", "DII", "ADCI", "ADII")));

		NumberAxis yAxisNewRule = new NumberAxis(); // int
		barChartFE = new StackedBarChart<>(xAxisNewRule, yAxisNewRule);

		//creates Feature Envy data
		XYChart.Series<String, Number> dataFE = new XYChart.Series<>();
		dataFE.getData().add(new XYChart.Data<>("DCI", manager.getmFEdci()));
		dataFE.getData().add(new XYChart.Data<>("DII", manager.getmFEdci()));
		dataFE.getData().add(new XYChart.Data<>("ADCI", manager.getmFEadci()));
		dataFE.getData().add(new XYChart.Data<>("ADII", manager.getmFEadii()));

		//sets the data in the StackedBarChart
		barChartFE.getData().addAll(dataFE);

		//draws it on the hbox
		hboxChartFE.getChildren().add(barChartFE);
	}

	
	
	
	
	
	/**
	 * Creates bar chart for the Long Method
	 */
	@SuppressWarnings("unchecked")
	private void newRuleLMBarChart() {
		//creating/setting up Long Method bar chart
		hboxChartLM.getChildren().remove(barChartLM);

		CategoryAxis xAxisNewRule = new CategoryAxis(); // String

		xAxisNewRule.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("DCI", "DII", "ADCI", "ADII")));

		NumberAxis yAxisNewRule = new NumberAxis(); // int
		barChartLM = new StackedBarChart<>(xAxisNewRule, yAxisNewRule);

		//creates Long Method data
		XYChart.Series<String, Number> dataLM = new XYChart.Series<>();
		dataLM.getData().add(new XYChart.Data<>("DCI", manager.getmLMdci()));
		dataLM.getData().add(new XYChart.Data<>("DII", manager.getmLMdci()));
		dataLM.getData().add(new XYChart.Data<>("ADCI", manager.getmLMadci()));
		dataLM.getData().add(new XYChart.Data<>("ADII", manager.getmLMadii()));

		//sets the data in the StackedBarChart
		barChartLM.getData().addAll(dataLM);

		//draws it on the hbox
		hboxChartLM.getChildren().add(barChartLM);
	}

	
	
	
	private void setUpGraphsFeLMG() {
		fePieChart();
		lmPieChart();
		newRuleFEBarChart();
		newRuleLMBarChart();
	}







	/**
	 * This method will be called when the Apply button is pressed
	 * @param event
	 */
	@FXML
	public void applyPressed(ActionEvent event) {
		if(validateInput()) {
				boolean atfdAndLaa = atfdLaaAndButton.isSelected();
				boolean atfdLessThan = atfdBiggerThanSelector.getSelectionModel().getSelectedIndex() != 0;
				boolean laaLessThan = laaBiggerThanSelector.getSelectionModel().getSelectedIndex() != 0;
				int atfdGUI = Integer.parseInt(atfdText.getText());
				double laaGUI = Double.parseDouble(laaText.getText());
				
				manager.MetricFeatureEnvy(atfdAndLaa, atfdLessThan, laaLessThan, atfdGUI, laaGUI);
				
				boolean locAndCyclo = locCycloAndButton.isSelected();
				boolean locLessThan = locBiggerThanSelector.getSelectionModel().getSelectedIndex() != 0;
				boolean cycloLessThan = cycloBiggerThanSelector.getSelectionModel().getSelectedIndex() != 0;
				int locGUI = Integer.parseInt(locText.getText());
				int cycloGUI = Integer.parseInt(cycloText.getText());
				
				manager.MetricLongMethod(locAndCyclo, locLessThan, cycloLessThan, locGUI, cycloGUI);
				manager.calculateIndicatorsMetric();
				
				setQualityIndicatorsTotals();
				setUpGraphsFeLMG();
				
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
	 * Displays an dialog that waits for user input
	 * @param message message to be displayed next to text field
	 * @return return the user input or null
	 */
	private String getAndShowInputTextDialog(String message) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setHeaderText(null);
		dialog.setGraphic(null);
		dialog.setContentText(message);

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent())
			return result.get();
		return null;
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

		initListeners();
		locText.setPromptText(manager.getLoc()+"");
		cycloText.setPromptText(manager.getCyclo()+"");
		atfdText.setPromptText(manager.getAtfd()+"");
		laaText.setPromptText(manager.getLaa()+"");
	}









	/**
	 * This makes table size responsive when Data tab is resized
	 * and creates Metric list listener
	 */
	private void initListeners() {
		dataTabPane.heightProperty().addListener((obs, old, newValue) -> table.setPrefHeight(newValue.doubleValue()));
		dataTabPane.widthProperty().addListener((obs, old, newValue) -> table.setPrefWidth(newValue.doubleValue()));


		metricList.getSelectionModel().selectedItemProperty().addListener((obs, old, newValue) ->{
			//load metric TODO Rui Menoita
		
		});
	}









	/**
	 * Calls the application start method that creates the platform thread
	 */
	public static void show(String[] args) {
		launch(args);
	}
}
