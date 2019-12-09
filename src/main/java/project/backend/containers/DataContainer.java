package project.backend.containers;


import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DataContainer {
	
	private SimpleIntegerProperty methodID = new SimpleIntegerProperty();

	private SimpleStringProperty packageName = new SimpleStringProperty();
	private SimpleStringProperty className = new SimpleStringProperty();
	private SimpleStringProperty method = new SimpleStringProperty();
	
	private SimpleIntegerProperty loc = new SimpleIntegerProperty();
	private SimpleIntegerProperty cyclo = new SimpleIntegerProperty();
	private SimpleIntegerProperty atfd = new SimpleIntegerProperty();
	private SimpleDoubleProperty laa = new SimpleDoubleProperty();
	
	private SimpleBooleanProperty is_long_method = new SimpleBooleanProperty();
	
	private SimpleBooleanProperty iPlasma = new SimpleBooleanProperty();
	private SimpleBooleanProperty pmd = new SimpleBooleanProperty();
	
	private SimpleBooleanProperty is_feature_envy = new SimpleBooleanProperty();

	private SimpleStringProperty quality_PMD = new SimpleStringProperty();
	private SimpleStringProperty quality_IPLASMA = new SimpleStringProperty();
	
	private SimpleBooleanProperty metric_longmethod = new SimpleBooleanProperty();
	private SimpleBooleanProperty metric_featureenvy = new SimpleBooleanProperty();
	
	private SimpleStringProperty quality_metric_longmethod = new SimpleStringProperty();
	private SimpleStringProperty quality_metric_featureenvy = new SimpleStringProperty();

	
	
	
	public DataContainer(int methodID, String packageName, String className, String method, int lOC, int cYCLO, int aTFD,
			double lAA, boolean is_long_method, boolean iPlasma,boolean pMD, boolean is_feature_envy,
			String statuspmd, String statusiplasma, boolean metric_longmethod, boolean metric_featureenvy,
			String quality_metric_longmethod, String quality_metric_featureenvy) {

		this.methodID = new SimpleIntegerProperty(methodID);
		this.packageName = new SimpleStringProperty(packageName);
		this.className = new SimpleStringProperty(className);
		this.method = new SimpleStringProperty(method);
		this.loc = new SimpleIntegerProperty(lOC);
		this.cyclo = new SimpleIntegerProperty(cYCLO);
		this.atfd = new SimpleIntegerProperty(aTFD);
		this.laa = new SimpleDoubleProperty(lAA);
		this.is_long_method = new SimpleBooleanProperty(is_long_method);
		this.iPlasma = new SimpleBooleanProperty(iPlasma);
		this.pmd = new SimpleBooleanProperty(pMD);
		this.is_feature_envy = new SimpleBooleanProperty(is_feature_envy);
		this.quality_PMD = new SimpleStringProperty(statuspmd);
		this.quality_IPLASMA = new SimpleStringProperty(statusiplasma);
		this.metric_longmethod = new SimpleBooleanProperty(metric_longmethod);
		this.metric_featureenvy = new SimpleBooleanProperty(metric_featureenvy);
		this.quality_metric_longmethod = new SimpleStringProperty(quality_metric_longmethod);
		this.quality_metric_featureenvy = new SimpleStringProperty(quality_metric_featureenvy);
	}
	
	
	/**
	 * returns a matrix with all the attributes of this class (represents the Columns of the table shown on GUI)
	 * 
	 * The second array has 3 values
	 *  
	 * the first one is the column title
	 * the second one is the class type
	 * the third one is the property name
	 * 
	 * | Title | Type | Name |
	 */
	public static String[][] getAttributes(){
		
		String [][] matrix = {
				{"Method ID"    ,"Integer"  , "methodID"   },
				
				{"Package"      ,"String"   , "packageName"},
				{"Class"        ,"String"   , "className"},
				{"Method"       ,"String"   , "method"},
				
				{"Loc"          ,"Integer"  , "loc"},
				{"Cyclo"        ,"Integer"  , "cyclo"},
				{"Atfd"         ,"Integer"  , "atfd"},
				   
				{"Laa"          ,"Double"   , "laa"},
				
				{"Long Method"  ,"Boolean"  , "isLongMethod"},
				
				{"Iplasma"      ,"Boolean"   , "iPlasma"},
				{"Pmd"          ,"Boolean"   , "pmd"},
				
				{"Feature Envy" ,"Boolean"  , "isFeatureEnvy"},
				
				{"QualityPMD"       ,"String"   , "statusPMD"},
				{"QualityIPLASMA"       ,"String"   , "statusIPLASMA"},
				
				{"MetricLM"      ,"Boolean"   , "metricLongMethod"},
				{"MetricFE"          ,"Boolean"   , "metricFeatureEnvy"},
				
				{"QualityMetricLM"       ,"String"   , "qualityMetricLongMethod"},
				{"QualityMetricFE"       ,"String"   , "qualityMetricFeatureEnvy"}
		};
		
		return matrix;
	}


	
	
	
	
	
	/**
	 * @return the methodID property
	 */
	public SimpleIntegerProperty methodIDProperty()  {
		return methodID;
	}
	
	
	
	

	/**
	 * @return the className property
	 */
	public SimpleStringProperty packageNameProperty()  {
		return packageName;
	}
	
	
	
	

	/**
	 * @return the className property
	 */
	public SimpleStringProperty classNameProperty()  {
		return className;
	}
	
	
	
	

	/**
	 * @return the packageName property
	 */
	public SimpleStringProperty methodProperty()  {
		return method;
	}
	
	
	
	

	/**
	 * @return the packageName property
	 */
	public SimpleIntegerProperty locProperty()  {
		return loc;
	}
	
	
	
	

	/**
	 * @return the packageName property
	 */
	public SimpleIntegerProperty cycloProperty()  {
		return cyclo;
	}
	
	
	
	

	/**
	 * @return the packageName property
	 */
	public SimpleIntegerProperty atfdProperty()  {
		return atfd;
	}
	
	
	
	

	/**
	 * @return the packageName property
	 */
	public SimpleDoubleProperty laaProperty()  {
		return  laa;
	}
	
	
	
	

	/**
	 * @return the packageName property
	 */
	public SimpleBooleanProperty isLongMethodProperty()  {
		return is_long_method;
	}
	
	
	
	

	/**
	 * @return the packageName property
	 */
	public SimpleBooleanProperty iPlasmaProperty()  {
		return iPlasma;
	}
	
	
	
	

	/**
	 * @return the packageName property
	 */
	public SimpleBooleanProperty pmdProperty()  {
		return pmd;
	}
	
	
	
	

	/**
	 * @return the packageName property
	 */
	public SimpleBooleanProperty isFeatureEnvyProperty()  {
		return is_feature_envy;
	}
	
	
	
	

	/**
	 * @return the packageName property
	 */
	public SimpleStringProperty statusPMDProperty()  {
		return quality_PMD;
	}
	
	
	
	
	
	/**
	 * @return the packageName property
	 */
	public SimpleStringProperty statusIPLASMAProperty()  {
		return quality_IPLASMA;
	}
	
	
	
	
	/**
	 * @return the packageName property
	 */
	public SimpleBooleanProperty metricLongMethod()  {
		return metric_longmethod;
	}
	
	
	
	

	/**
	 * @return the packageName property
	 */
	public SimpleBooleanProperty metricFeatureEnvy()  {
		return metric_featureenvy;
	}
	
	
	
	

	/**
	 * @return the packageName property
	 */
	public SimpleStringProperty qualityMetricLongMethod()  {
		return quality_metric_longmethod;
	}
	
	
	
	
	
	/**
	 * @return the packageName property
	 */
	public SimpleStringProperty qualityMetricFeatureEnvy()  {
		return quality_metric_featureenvy;
	}


	
	
	//getters ------------------------------------------
	
	
	public int getMethodID() {
		return methodID.get();
	}


	public String getPackageName() {
		return packageName.get();
	}


	public String getClassName() {
		return className.get();
	}


	public String getMethod() {
		return method.get();
	}


	public int getLoc() {
		return loc.get();
	}


	public int getCyclo() {
		return cyclo.get();
	}


	public int getAtfd() {
		return atfd.get();
	}


	public double getLaa() {
		return laa.get();
	}


	public boolean getIs_long_method() {
		return is_long_method.get();
	}


	public boolean getiPlasma() {
		return iPlasma.get();
	}


	public boolean getPmd() {
		return pmd.get();
	}


	public boolean getIs_feature_envy() {
		return is_feature_envy.get();
	}


	public String getStatusPMD() {
		return quality_PMD.get();
	}
	
	
	public String getStatusIPLASMA() {
		return quality_IPLASMA.get();
	}
	
	
	public boolean getMetricLongMethod() {
		return metric_longmethod.get();
	}


	public boolean getMetricFeatureEnvy() {
		return metric_featureenvy.get();
	}


	public String getQualityMetricLongMethod() {
		return quality_metric_longmethod.get();
	}
	
	
	public String getQualityMetricFeatureEnvy() {
		return quality_metric_featureenvy.get();
	}
	
	//setters -------------
	
	public void setIs_long_method(boolean bool) {
		is_long_method.set(bool);
	}
	
	public void setIs_feature_envy(boolean bool) {
		is_feature_envy.set(bool);
	}
	
	public void setStatusPMD(String str) {
		quality_PMD.set(str);
	}
	
	public void setStatusIPLASMA(String str) {
		quality_IPLASMA.set(str);
	}
	
	public void setMetric_longmethod(boolean bool) {
		metric_longmethod.set(bool);
	}
	
	public void setMetric_featureenvy(boolean bool) {
		metric_featureenvy.set(bool);
	}
	
	public void setQualityLM(String str) {
		quality_metric_longmethod.set(str);
	}
	
	public void setQualityFE(String str) {
		quality_metric_featureenvy.set(str);
	}
}
