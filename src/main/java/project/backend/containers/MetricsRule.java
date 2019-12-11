package project.backend.containers;

import javafx.beans.property.*;
/**
 * 
 */
public class MetricsRule {
	
	private SimpleStringProperty metricName = new SimpleStringProperty();

	private SimpleIntegerProperty locValue = new SimpleIntegerProperty();
	private SimpleBooleanProperty locComparison = new SimpleBooleanProperty();
	
	private SimpleBooleanProperty locCycloAndOr = new SimpleBooleanProperty();
	
	private SimpleIntegerProperty cycloValue = new SimpleIntegerProperty();
	private SimpleBooleanProperty cycloComparison = new SimpleBooleanProperty();
	
	private SimpleIntegerProperty aftdValue = new SimpleIntegerProperty();
	private SimpleBooleanProperty aftdComparison = new SimpleBooleanProperty();
	
	private SimpleBooleanProperty aftdLaaAndOr = new SimpleBooleanProperty();
	
	private SimpleIntegerProperty laaValue = new SimpleIntegerProperty();
	private SimpleBooleanProperty laaComparison = new SimpleBooleanProperty();
	
	
	
	
	
	public MetricsRule(
			int locVal, boolean locComp,
			boolean locCyclo,
			int cycloVal, boolean cycloComp,
			int aftdVal, boolean aftdComp,
			boolean aftdLaa,
			int laaVal, boolean laaComp,
			String name) {
		
		locValue.set(locVal);
		locComparison.set(locComp);
		
		locCycloAndOr.set(locCyclo);
		
		cycloValue.set(cycloVal);
		cycloComparison.set(cycloComp);
		
		aftdValue.set(aftdVal);
		aftdComparison.set(aftdComp);
		
		aftdLaaAndOr.set(aftdLaa);
		
		laaValue.set(laaVal);
		laaComparison.set(laaComp);
		
		metricName.set(name);
	}
	
	
	
	
	
	public int getLocValue() {
		return locValue.get();
	}





	public void setLocValue(int locValue) {
		this.locValue.set(locValue);
	}





	public boolean getLocComparison() {
		return locComparison.get();
	}





	public void setLocComparison(boolean locComparison) {
		this.locComparison.set(locComparison);
	}





	public boolean getLocCycloAndOr() {
		return locCycloAndOr.get();
	}





	public void setLocCycloAndOr(boolean locCycloAndOr) {
		this.locCycloAndOr.set(locCycloAndOr);
	}





	public int getCycloValue() {
		return cycloValue.get();
	}





	public void setCycloValue(int cycloValue) {
		this.cycloValue.set(cycloValue);
	}





	public boolean getCycloComparison() {
		return cycloComparison.get();
	}





	public void setCycloComparison(boolean cycloComparison) {
		this.cycloComparison.set(cycloComparison);
	}





	public int getAftdValue() {
		return aftdValue.get();
	}





	public void setAftdValue(int aftdValue) {
		this.aftdValue.set(aftdValue);
	}





	public boolean getAftdComparison() {
		return aftdComparison.get();
	}





	public void setAftdComparison(Boolean aftdComparison) {
		this.aftdComparison.set(aftdComparison);
	}





	public boolean getAftdLaaAndOr() {
		return aftdLaaAndOr.get();
	}





	public void setAftdLaaAndOr(boolean aftdLaaAndOr) {
		this.aftdLaaAndOr.set(aftdLaaAndOr);
	}





	public int getLaaValue() {
		return laaValue.get();
	}





	public void setLaaValue(int laaValue) {
		this.laaValue.set(laaValue);
	}





	public boolean getLaaComparison() {
		return laaComparison.get();
	}





	public void setLaaComparison(boolean laaComparison) {
		this.laaComparison.set(laaComparison);
	}





	/**
	 * @return the metricName
	 */
	public String getMetricName() {
		return metricName.get();
	}





	/**
	 * @param metricName the metricName to set
	 */
	public void setMetricName(String metricName) {
		this.metricName.set(metricName);
	}
}	
