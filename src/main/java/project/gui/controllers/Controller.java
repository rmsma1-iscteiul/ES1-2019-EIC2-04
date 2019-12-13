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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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

	@FXML private Label labelDCI;
	@FXML private Label labelDII;
	@FXML private Label labelADCI;
	@FXML private Label labelADII;
	@FXML private Label newRuleLabel;

	@FXML private PieChart pieChartiPlasma;
	@FXML private PieChart pieChartPMD;
	@FXML private PieChart pieChartFE;
	@FXML private PieChart pieChartLM;

	@FXML private StackedBarChart<String, Number> barChartPMD;
	@FXML private StackedBarChart<String, Number> barChartiPlasma;
	@FXML private StackedBarChart<String, Number> barChartFE;
	@FXML private StackedBarChart<String, Number> barChartLM;

	@FXML private HBox hboxChartPMD;
	@FXML private HBox hboxChartiPlasma;
	@FXML private HBox hboxChartFE;
	@FXML private HBox hboxChartLM;


	//-----------------------------
	//LOC
	@FXML private TextField locText;
	@FXML private ChoiceBox<String> locBiggerThanSelector;

	//AND OR
	@FXML private RadioButton locCycloAndButton;
	@FXML private RadioButton locCycloOrButton;

	//CYCLO
	@FXML private TextField cycloText;
	@FXML private ChoiceBox<String> cycloBiggerThanSelector;

	//AFTD
	@FXML private TextField atfdText;
	@FXML private ChoiceBox<String> atfdBiggerThanSelector;


	//AND OR
	@FXML private RadioButton atfdLaaAndButton;
	@FXML private RadioButton atfdLaaOrButton;

	//LAA
	@FXML private TextField laaText;
	@FXML private ChoiceBox<String> laaBiggerThanSelector;
	//----------------------------

	@FXML private ListView<MetricsRule> metricList;


	private boolean isFileLoaded = false;



	/**
	 * Opens Java Doc in users browser
	 */
	public void openJavaDoc() {
		getHostServices().showDocument(System.getProperty("user.dir")+"\\doc\\allpackages-index.html");
	}






	/**
	 * Saves metric in a list
	 * @param event
	 */
	@FXML
	public void saveMetric(ActionEvent event) {
		if(validateInput()) {
			String name = getAndShowInputTextDialog("Please enter metric name:");

			for(MetricsRule mr : metricList.getItems()) {
				if(mr.getMetricName().equals(name)){
					showErrorDialog("There is Already a metric rule with that name\n Please choose another name");
					return;
				}
			}
			try {

				boolean atfdAndLaa = atfdLaaAndButton.isSelected();
				boolean atfdBiggerThan = atfdBiggerThanSelector.getSelectionModel().getSelectedIndex() == 0;
				boolean laaBiggerThan = laaBiggerThanSelector.getSelectionModel().getSelectedIndex() == 0;
				boolean locAndCyclo = locCycloAndButton.isSelected();
				boolean locBiggerThan = locBiggerThanSelector.getSelectionModel().getSelectedIndex() == 0;
				boolean cycloBiggerThan = cycloBiggerThanSelector.getSelectionModel().getSelectedIndex() == 0;
				int locGUI = Integer.parseInt(locText.getText());
				int cycloGUI = Integer.parseInt(cycloText.getText());
				int atfdGUI = Integer.parseInt(atfdText.getText());
				double laaGUI = Double.parseDouble(laaText.getText());

				metricList.getItems().add(manager.createRule(name, locGUI, locBiggerThan, locAndCyclo, cycloGUI, cycloBiggerThan, 
						atfdGUI, atfdBiggerThan, atfdAndLaa, laaGUI, laaBiggerThan));		//creates and add metric to list
			}catch (Exception e) {
				e.printStackTrace();
				showErrorDialog("Something went wrong:\n"+e.getMessage());
				return;
			}

			showInfoDialog("Metric " +name + " has been saved with success");
		}
	}









	/**
	 * If this return true it means inputs are valid otherwise they are invalid
	 */
	private boolean validateInput() {
		if(isFileLoaded) {
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
		}else {
			showErrorDialog("No file found! \nPlease load a file first");
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
			isFileLoaded = true;
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
	 */
	public void setQualityIndicatorsTotals() {
		MetricsRule mr = metricList.getSelectionModel().getSelectedItem();
		String metricName = "New Rule";
		if(mr!=null) {
			metricName  = mr.getMetricName();	
		}
		newRuleLabel.setText(metricName);


		labelDCI.setText("iPlasma: " + Integer.toString(manager.getIpdci()) + "\n" + "PMD: " + Integer.toString(manager.getPdci()) + "\n" + 
				metricName + "\n" + "Long Method: " + Integer.toString(manager.getmLMdci()) + "\n" + "Feature Envy: " + Integer.toString(manager.getmFEdci()));

		labelDII.setText("iPlasma: " + Integer.toString(manager.getIpdii()) + "\n" + "PMD: " + Integer.toString(manager.getPdii()) + "\n" + 
				metricName + "\n" + "Long Method: " + Integer.toString(manager.getmLMdii()) + "\n" + "Feature Envy: " + Integer.toString(manager.getmFEdii()));


		labelADCI.setText("iPlasma: " + Integer.toString(manager.getIpadci()) + "\n" + "PMD: " + Integer.toString(manager.getPadci()) + "\n" + 
				metricName + "\n" + "Long Method: " + Integer.toString(manager.getmLMadci()) + "\n" + "Feature Envy: " + Integer.toString(manager.getmFEadci()));

		labelADII.setText("iPlasma: " + Integer.toString(manager.getIpadii()) + "\n" + "PMD: " + Integer.toString(manager.getPadii()) + "\n" + 
				metricName + "\n" + "Long Method: " + Integer.toString(manager.getmLMadii()) + "\n" + "Feature Envy: " + Integer.toString(manager.getmFEadii()));

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
		pieChartLM.setData(pieChartDataLM);
		pieChartLM.setTitle("Long Method");
		pieChartLM.setLegendSide(Side.LEFT);
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
		pieChartFE.setData(pieChartDataFE);
		pieChartFE.setTitle("Feature Envy");
		pieChartFE.setLegendSide(Side.LEFT);
	}












	/**
	 * 
	 * This method will create the iPlasma and PMD bar charts
	 * 
	 */
	private void configureStackedBarChart() {
		pmdBarChart();
		iPlasmaBarChart();

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



	/**
	 * Sets up all the graphs
	 */
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

		try {
			metricList.getItems().addAll(FXCollections.observableArrayList(manager.loadRules()));
		} catch (IOException e) {
			e.printStackTrace();
			showErrorDialog("Something went wrong loading saved metric rules.");
		}

		metricList.getSelectionModel().selectedItemProperty().addListener((obs, old, newValue) -> showMetricDialog());
	}






	/**
	 * Show metric dialog with three buttons 
	 * Load -> loads metric
	 * Delete -> Deletes metric
	 * Cancel -> Cancels action
	 */
	private void showMetricDialog() {
		if(isFileLoaded) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Load metric");
			alert.setHeaderText("Do you want to load or delete "+ metricList.getSelectionModel().getSelectedItem().getMetricName()+ " metric?");
			alert.setContentText("Choose an option.");

			ButtonType loadButton = new ButtonType("Load");
			ButtonType deleteButton = new ButtonType("Delete");
			ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(loadButton, deleteButton, buttonTypeCancel);

			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == loadButton){					//process the dialog output
				loadMetric();

			} else if (result.get() == deleteButton) {

				try {
					manager.deleteRule(metricList.getSelectionModel().getSelectedItem());

					if(!metricList.getItems().remove(metricList.getSelectionModel().getSelectedItem())) {
						showErrorDialog("Something went wrong deleting metric");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					showErrorDialog("Something went wrong.\n"+e.getLocalizedMessage());
				}
				showInfoDialog("Rule deleted with Success");
			} 
		}else
			showErrorDialog("No file found! \nPlease load a file first");
	}








	/**
	 * This method displays a dialog with the given message
	 * @param message message to be displayed as info dialog
	 */
	private void showInfoDialog(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Info");
		alert.setHeaderText("Info");
		alert.setContentText(message);
		alert.showAndWait();
	}








	/**
	 * This method loads the selected metric
	 */
	private void loadMetric() {
		MetricsRule mr = metricList.getSelectionModel().getSelectedItem();

		boolean atfdAndLaa = mr.getAftdLaaAndOr();
		atfdLaaAndButton.setSelected(mr.getAftdLaaAndOr());
		atfdLaaOrButton.setSelected(!mr.getAftdLaaAndOr());

		boolean atfdLessThan = !mr.getAftdComparison();
		atfdBiggerThanSelector.getSelectionModel().select(mr.getAftdComparison() ? 0 : 1);

		boolean laaLessThan = !mr.getLaaComparison();
		laaBiggerThanSelector.getSelectionModel().select(mr.getLaaComparison() ? 0 : 1);

		int atfdGUI = mr.getAftdValue();
		atfdText.setText(mr.getAftdValue()+"");

		double laaGUI = mr.getLaaValue();
		laaText.setText(mr.getLaaValue()+"");

		manager.MetricFeatureEnvy(atfdAndLaa, atfdLessThan, laaLessThan, atfdGUI, laaGUI);

		//--------------

		boolean locAndCyclo = mr.getLocCycloAndOr();
		locCycloAndButton.setSelected(mr.getLocCycloAndOr());
		locCycloOrButton.setSelected(!mr.getLocCycloAndOr());

		boolean locLessThan = !mr.getLocComparison();
		locBiggerThanSelector.getSelectionModel().select(mr.getLocComparison() ? 0 : 1);

		boolean cycloLessThan = mr.getCycloComparison();
		cycloBiggerThanSelector.getSelectionModel().select(mr.getCycloComparison() ? 0 : 1);

		int locGUI = mr.getLocValue();
		locText.setText(mr.getLocValue()+"");

		int cycloGUI = mr.getCycloValue();
		cycloText.setText(mr.getCycloValue()+"");

		manager.MetricLongMethod(locAndCyclo, locLessThan, cycloLessThan, locGUI, cycloGUI);
		manager.calculateIndicatorsMetric();

		setQualityIndicatorsTotals();
		setUpGraphsFeLMG();
	}







	/**
	 * Calls the application start method that creates the platform thread
	 */
	public static void show(String[] args) {
		launch(args);
	}
}
