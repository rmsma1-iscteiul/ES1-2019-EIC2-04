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
	
	private SimpleStringProperty iPlasma = new SimpleStringProperty();
	private SimpleStringProperty pmd = new SimpleStringProperty();
	
	private SimpleBooleanProperty is_feature_envy = new SimpleBooleanProperty();

	private SimpleStringProperty statusPMD = new SimpleStringProperty();
	private SimpleStringProperty statusIPLASMA = new SimpleStringProperty();

	
	
	
	public DataContainer(int methodID, String packageName, String className, String method, int lOC, int cYCLO, int aTFD,
			double lAA, boolean is_long_method, String iPlasma,String pMD, boolean is_feature_envy, String statuspmd, String statusiplasma) {

		this.methodID = new SimpleIntegerProperty(methodID);
		this.packageName = new SimpleStringProperty(packageName);
		this.className = new SimpleStringProperty(className);
		this.method = new SimpleStringProperty(method);
		this.loc = new SimpleIntegerProperty(lOC);
		this.cyclo = new SimpleIntegerProperty(cYCLO);
		this.atfd = new SimpleIntegerProperty(aTFD);
		this.laa = new SimpleDoubleProperty(lAA);
		this.is_long_method = new SimpleBooleanProperty(is_long_method);
		this.iPlasma = new SimpleStringProperty(iPlasma);
		this.pmd = new SimpleStringProperty(pMD);
		this.is_feature_envy = new SimpleBooleanProperty(is_feature_envy);
		this.statusPMD = new SimpleStringProperty(statuspmd);
		this.statusIPLASMA = new SimpleStringProperty(statusiplasma);
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
				{"Method ID"    , "Integer" , "methodID"   },
				
				{"Package"      ,"String"   , "packageName"},
				{"Class"        ,"String"   , "className"},
				{"Method"       ,"String"   , "method"},
				
				{"Loc"          ,"Integer"  , "loc"},
				{"Cyclo"        ,"Integer"  , "cyclo"},
				{"Atfd"         ,"Integer"  , "atfd"},
				   
				{"Laa"          ,"Double"   , "laa"},
				
				{"Long Method"  ,"Boolean"  , "isLongMethod"},
				
				{"Iplasma"      ,"String"   , "iPlasma"},
				{"Pmd"          ,"String"   , "pmd"},
				
				{"Envy Feature" ,"Boolean"  , "isFeatureEnvy"},
				
				{"StatusPMD"       ,"String"   , "statusPMD"},
				{"StatusIPLASMA"       ,"String"   , "statusIPLASMA"}};
		
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
	public SimpleStringProperty iPlasmaProperty()  {
		return iPlasma;
	}
	
	
	
	

	/**
	 * @return the packageName property
	 */
	public SimpleStringProperty pmdProperty()  {
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
		return statusPMD;
	}
	
	
	
	
	
	/**
	 * @return the packageName property
	 */
	public SimpleStringProperty statusIPLASMAProperty()  {
		return statusIPLASMA;
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


	public String getiPlasma() {
		return iPlasma.get();
	}


	public String getPmd() {
		return pmd.get();
	}


	public boolean getIs_feature_envy() {
		return is_feature_envy.get();
	}


	public String getStatusPMD() {
		return statusPMD.get();
	}
	
	
	public String getStatusIPLASMA() {
		return statusIPLASMA.get();
	}
	
}
