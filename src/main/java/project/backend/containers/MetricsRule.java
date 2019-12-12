package project.backend.containers;

import java.awt.List;

import javax.swing.text.StyledEditorKit.BoldAction;

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
	public MetricsRule(String rule) {
		
		String[] atributes = rule.split("/");
		
		int locVal = Integer.parseInt(atributes[1]); 
		boolean locComp = Boolean.parseBoolean(atributes[2]);
		boolean locCyclo= Boolean.parseBoolean(atributes[3]);
		int cycloVal = Integer.parseInt(atributes[4]); 
		boolean cycloComp = Boolean.parseBoolean(atributes[5]);
		int aftdVal = Integer.parseInt(atributes[6]); 
		boolean aftdComp = Boolean.parseBoolean(atributes[7]);
		boolean aftdLaa = Boolean.parseBoolean(atributes[8]);
		int laaVal = Integer.parseInt(atributes[9]); 
		boolean laaComp = Boolean.parseBoolean(atributes[10]);
		String name = atributes[0];
		
		
		
		
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
	
	@Override
	public String toString() {
		String rule = "";
		rule += this.metricName.get();
		
		rule += "/"+this.locValue.get();
		rule += "/"+this.locComparison.get();
		
		rule += "/"+this.locCycloAndOr.get();
		
		rule += "/"+this.cycloValue.get();
		rule += "/"+this.cycloComparison.get();
		
		rule += "/"+this.aftdValue.get();
		rule += "/"+this.aftdComparison.get();
		
		rule += "/"+this.aftdLaaAndOr.get();
		
		rule += "/"+this.laaValue.get();
		rule += "/"+this.laaComparison.get();
		
		return rule;
	}
}	
