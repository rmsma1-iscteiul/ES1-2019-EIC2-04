package project.backend.store;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DataContainer {
	
	//wich
	private SimpleIntegerProperty methodID = new SimpleIntegerProperty();

	private SimpleStringProperty packageName = new SimpleStringProperty();
	private SimpleStringProperty className = new SimpleStringProperty();
	private SimpleStringProperty method = new SimpleStringProperty();
	
	private SimpleIntegerProperty LOC = new SimpleIntegerProperty();
	private SimpleIntegerProperty CYCLO = new SimpleIntegerProperty();
	private SimpleIntegerProperty ATFD = new SimpleIntegerProperty();
	private SimpleDoubleProperty LAA = new SimpleDoubleProperty();
	
	private SimpleBooleanProperty is_long_method = new SimpleBooleanProperty();
	
	private SimpleStringProperty iPlasma = new SimpleStringProperty();
	private SimpleStringProperty PMD = new SimpleStringProperty();
	
	private SimpleBooleanProperty is_feature_envy = new SimpleBooleanProperty();


	
	
	public DataContainer(int methodID, String packageName, String className, String method, int lOC, int cYCLO, int aTFD,
			double lAA, boolean is_long_method, String iPlasma,String pMD, boolean is_feature_envy) {

		this.methodID.set(methodID);
		this.packageName.set(packageName);
		this.className.set(className);
		this.method.set(method);
		this.LOC.set(lOC);
		this.CYCLO.set(cYCLO);
		this.ATFD.set(aTFD);
		this.LAA.set(lAA);
		this.is_long_method.set(is_long_method);
		this.iPlasma.set(iPlasma);
		this.PMD.set(pMD);
		this.is_feature_envy.set(is_feature_envy);
	}
	
	
	
	
	

	/**
	 * @return the methodID
	 */
	public SimpleIntegerProperty getMethodID() {
		return methodID;
	}

	/**
	 * @return the packageName
	 */
	public SimpleStringProperty getPackageName() {
		return packageName;
	}

	/**
	 * @return the className
	 */
	public SimpleStringProperty getClassName() {
		return className;
	}

	/**
	 * @return the lOC
	 */
	public SimpleIntegerProperty getLOC() {
		return LOC;
	}

	/**
	 * @return the cYCLO
	 */
	public SimpleIntegerProperty getCYCLO() {
		return CYCLO;
	}

	/**
	 * @return the aTFD
	 */
	public SimpleIntegerProperty getATFD() {
		return ATFD;
	}

	/**
	 * @return the lAA
	 */
	public SimpleDoubleProperty getLAA() {
		return LAA;
	}

	/**
	 * @return the is_long_method
	 */
	public SimpleBooleanProperty getIs_long_method() {
		return is_long_method;
	}

	/**
	 * @return the iPlasma
	 */
	public SimpleStringProperty getiPlasma() {
		return iPlasma;
	}

	/**
	 * @return the pMD
	 */
	public SimpleStringProperty getPMD() {
		return PMD;
	}

	/**
	 * @return the is_feature_envy
	 */
	public SimpleBooleanProperty getIs_feature_envy() {
		return is_feature_envy;
	}

	/**
	 * @param methodID the methodID to set
	 */
	public void setMethodID(SimpleIntegerProperty methodID) {
		this.methodID = methodID;
	}

	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(SimpleStringProperty packageName) {
		this.packageName = packageName;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(SimpleStringProperty className) {
		this.className = className;
	}

	/**
	 * @param lOC the lOC to set
	 */
	public void setLOC(SimpleIntegerProperty lOC) {
		LOC = lOC;
	}

	/**
	 * @param cYCLO the cYCLO to set
	 */
	public void setCYCLO(SimpleIntegerProperty cYCLO) {
		CYCLO = cYCLO;
	}

	/**
	 * @param aTFD the aTFD to set
	 */
	public void setATFD(SimpleIntegerProperty aTFD) {
		ATFD = aTFD;
	}

	/**
	 * @param lAA the lAA to set
	 */
	public void setLAA(SimpleDoubleProperty lAA) {
		LAA = lAA;
	}

	/**
	 * @param is_long_method the is_long_method to set
	 */
	public void setIs_long_method(SimpleBooleanProperty is_long_method) {
		this.is_long_method = is_long_method;
	}

	/**
	 * @param iPlasma the iPlasma to set
	 */
	public void setiPlasma(SimpleStringProperty iPlasma) {
		this.iPlasma = iPlasma;
	}

	/**
	 * @param pMD the pMD to set
	 */
	public void setPMD(SimpleStringProperty pMD) {
		PMD = pMD;
	}

	/**
	 * @param is_feature_envy the is_feature_envy to set
	 */
	public void setIs_feature_envy(SimpleBooleanProperty is_feature_envy) {
		this.is_feature_envy = is_feature_envy;
	}
}
