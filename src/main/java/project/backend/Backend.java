package project.backend;
import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import project.backend.containers.DataContainer;
import project.backend.containers.MetricsRule;
import project.backend.IO_Manager;
 
public class Backend {
	private Workbook workbook;
	private double loc = 80;
	private double cyclo = 10;
	private double atfd = 4;
	private double laa = 0.42;
	
	//pmd
	private int pdci=0;
	private int pdii=0;
	private int padci=0;
	private int padii=0;
	
	//iplasma
	private int ipdci=0;
	private int ipdii=0;
	private int ipadci=0;
	private int ipadii=0;
	
	//metric loc and cyclo
	private int mLMdci=0;
	private int mLMdii=0;
	private int mLMadci=0;
	private int mLMadii=0;
	
	//metric atfd and laa
	private int mFEdci=0;
	private int mFEdii=0;
	private int mFEadci=0;
	private int mFEadii=0;
	
	private List<DataContainer> fileListed;

	
	
	
	
	/**
	 * parse file to List of DataContainers
	 * @param file file ot be parsed
	 * @return  list of DataContainer s
	 * @throws IOException
	 */
	public List<DataContainer> parseFileToMap (File file) {

		List<DataContainer> list = new ArrayList<DataContainer>();

		Sheet fileSheet;
		try {
			fileSheet = file_to_sheet(file);

			DataContainer tempContainer = null;
			Iterator<Row> rowIterator = fileSheet.rowIterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				tempContainer = row_to_container(row);

				list.add(tempContainer);
			}
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
			throw new InvalidParameterException("file might be encripted or not reachable");
		}catch (Exception e) {
			e.printStackTrace();
			throw new InvalidParameterException("file is in the wrong format");
		}
		fileListed = list;
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		calculateIndicators();
		return list;
	}
	
	
	
	
	
	/**
	 * takes in a excel file and converts it into a apache.poi sheet
	 * @param file to be converted
	 * @return apache.poi sheet 
	 * @throws EncryptedDocumentException the file is incripted
	 * @throws IOException
	 */
	private Sheet file_to_sheet(File file) throws EncryptedDocumentException, IOException {
		workbook = WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheetAt(0);
		return sheet;
	}

	
	
	
	
	/**
	 * takes in a row from a excel file previously converted into a apache.poi sheet and creates a new instace of DataContainer from it
	 * @param row 
	 * @return
	 * @throws Exception this is thrown in case the excel file is in the wrong format
 	 */
	private DataContainer row_to_container(Row row) throws Exception {
		DataFormatter dataFormatter = new DataFormatter();
		String[] cells = new String[12];

		for(int i = 0; i != 12; i++) {
			Cell cell = row.getCell(i);
			String cellValue = dataFormatter.formatCellValue(cell);
			cells[i]=cellValue;
//			System.out.print(cellValue + "\t" + (i== 11 ? "\n" : ""));
		}
		

		int methodID = Integer.parseInt(cells[0]);
		String packageName = cells[1];
		String className = cells[2];
		String method = cells[3];
		int lOC = Integer.parseInt(cells[4]);
		int cYCLO = Integer.parseInt(cells[5]);
		int aTFD = Integer.parseInt(cells[6]);
		double lAA = Double.parseDouble(cells[7]);
		boolean is_long_method = (cells[8].charAt(0) == 'T');
		boolean iPlasma = (cells[9].charAt(0) == 'T');
		boolean pMD = (cells[10].charAt(0) == 'T');
		boolean is_feature_envy = (cells[11].charAt(0) == 'T');
		DataContainer container = new DataContainer(
				methodID, 
				packageName, 
				className,
				method, 
				lOC, 
				cYCLO, 
				aTFD, 
				lAA, 
				is_long_method, 
				iPlasma, 
				pMD, 
				is_feature_envy, 
				"-", 
				"-",
				false, 
				false,
				"-", 
				"-"
				); 
		return container;
	}
	
	
	
	
	
	
	//Create and save user defined rules
	
	
	
	/**
	 * Creates a metric rule and saves it in the save file
	 * @param name 
	 * The rule name
	 * @param locVal 
	 * The rule's LOC Value
	 * @param locComp 
	 * The rule's LOC comparison method: true for greater than, false for lesser than
	 * @param locCyclo
	 * The rule's LOC and CYCLO logic operation type: true for And, false for OR
	 * @param cycloVal
	 * The rule's CYCLO Value
	 * @param cycloComp
	 * The rule's CYCLO comparison method: true for greater than, false for lesser than
	 * @param aftdVal
	 * The rule's AFTD Value
	 * @param aftdComp
	 * The rule's AFTD comparison method: true for greater than, false for lesser than
	 * @param aftdLaa
	 * The rule's AFTD and LAA logic operation type: true for And, false for OR
	 * @param laaVal
	 * The rule's LAA Value
	 * @param laaComp
	 * The rule's LAA comparison method: true for greater than, false for lesser than
	 * @return
	 * Returns the created rule After saving it to the save file
	 * @throws IOException
	 * Throws this exception case the are errors writing the save file(EX: file is missing);
	 */
	public MetricsRule createRule(
			String name,
			int locVal, boolean locComp,
			boolean locCyclo,
			int cycloVal, boolean cycloComp,
			int aftdVal, boolean aftdComp,
			boolean aftdLaa,
			double laaVal, boolean laaComp) throws IOException 
	{
		MetricsRule newRule = new MetricsRule
			(
					name,
					locVal,  
					locComp,
					locCyclo,
					cycloVal,  
					cycloComp,
					aftdVal,  
					aftdComp,
					aftdLaa,
					laaVal,  
					laaComp			 
			 );
		IO_Manager.writeRuleToFile(newRule);
		return newRule;
	}
	
	
	
	
	
	
	/**
	 * Deletes the given rule from the save file
	 * @param ruleToDel 
	 * The Metric rule object to remove from the save file
	 * @throws IOException 
	 * Throws this exception case the are errors rewriting the save file(EX: file is missing);
	 */
	public void deleteRule(MetricsRule ruleToDel) throws IOException {
		IO_Manager.removeRuleFromFile(ruleToDel);
	}
	
	
	
	
	
	
	/**
	 * Loads the list of Rules from the save file
	 * @return 
	 * Returns the saved list of metric rules
	 * @throws IOException 
	 * Throws this exception case the are errors reading the save file(EX: file is missing);
	 */
	public List<MetricsRule> loadRules() throws IOException{
		return IO_Manager.readListFromFile();
	}
	
	
	
	
	
	
	
	
	// check list with arguments from GUI
	/**
	 * calls method calculateIndicators(boolean)
	 * @param bool from GUI deciding AND/OR operator (true=OR/false=AND)
	 * @return list after calculating indicators
	 */
	public List<DataContainer> checkList () {
		calculateIndicators ();
		return fileListed;
	}
	
	/**
	 * sets column MetricLongMethod true or false, depending on inputs in GUI and comparing those inputs with values from file
	 * @param and 
	 * boolean that comes from GUI deciding AND/OR operator (true=AND/false=OR) between loc and cyclo
	 * @param locLessThan
	 * boolean: if true then use ( locFile <= locGUI ) for comparison; else ( locFile > locGUI )
	 * @param cycloLessThan
	 * boolean: if true then use ( cycloFile <= cycloGUI ) for comparison; else ( cycloFile > cycloGUI )
	 * @param locGUI
	 * loc value input from GUI
	 * @param cycloGUI
	 * cyclo value input from GUI
	 */
	public void MetricLongMethod (boolean and, boolean locLessThan, boolean cycloLessThan, int locGUI, int cycloGUI) {
		for (DataContainer dc: fileListed) {
			dc.setMetric_longmethod(true);
			
			if(and && locLessThan && cycloLessThan) {
				if (dc.getLoc() <= locGUI && dc.getCyclo() <= cycloGUI)
					dc.setMetric_longmethod(false);
			}
			if(!and && locLessThan && cycloLessThan) {
				if (dc.getLoc() <= locGUI || dc.getCyclo() <= cycloGUI)
					dc.setMetric_longmethod(false);
			}
			
			if(and && !locLessThan && !cycloLessThan) {
				if (dc.getLoc() > locGUI && dc.getCyclo() > cycloGUI)
					dc.setMetric_longmethod(false);
			}
			if(!and && !locLessThan && !cycloLessThan) {
				if (dc.getLoc() > locGUI || dc.getCyclo() > cycloGUI)
					dc.setMetric_longmethod(false);
			}
			
			if(and && locLessThan && !cycloLessThan) {
				if (dc.getLoc() <= locGUI && dc.getCyclo() > cycloGUI)
					dc.setMetric_longmethod(false);
			}
			if(!and && locLessThan && !cycloLessThan) {
				if (dc.getLoc() <= locGUI || dc.getCyclo() > cycloGUI)
					dc.setMetric_longmethod(false);
			}
			
			if(and && !locLessThan && cycloLessThan) {
				if (dc.getLoc() > locGUI && dc.getCyclo() <= cycloGUI)
					dc.setMetric_longmethod(false);
			}
			if(!and && !locLessThan && cycloLessThan) {
				if (dc.getLoc() > locGUI || dc.getCyclo() <= cycloGUI)
					dc.setMetric_longmethod(false);
			}
		}
	}
	
	
	
	
	
	
	
	/**
	 *  sets column MetricFeatureEnvy true or false, depending on inputs in GUI and comparing those inputs with values from file
	 * @param and
	 * boolean that comes from GUI deciding AND/OR operator (true=AND/false=OR) between atfd and laa
	 * @param atfdLessThan
	 * boolean: if true then use ( atfdFile <= atfdGUI ) for comparison; else ( atfdFile > atfdGUI )
	 * @param laaLessThan
	 * boolean: if true then use ( laaFile <= laaGUI ) for comparison; else ( laaFile > laaGUI )
	 * @param atfdGUI
	 * atfd value input from GUI
	 * @param laaGUI
	 * laa value input from GUI
	 */
	public void MetricFeatureEnvy (boolean and, boolean atfdLessThan, boolean laaLessThan, int atfdGUI, double laaGUI) {
		for (DataContainer dc: fileListed) {
			dc.setMetric_featureenvy(true);
			
			if(and && atfdLessThan && laaLessThan) {
				if (dc.getAtfd() <= atfdGUI && dc.getLaa() <= laaGUI)
					dc.setMetric_featureenvy(false);
			}
			if(!and && atfdLessThan && laaLessThan) {
				if (dc.getAtfd() <= atfdGUI || dc.getLaa() <= laaGUI)
					dc.setMetric_featureenvy(false);
			}
			
			if(and && !atfdLessThan && !laaLessThan) {
				if (dc.getAtfd() > atfdGUI && dc.getLaa() > laaGUI)
					dc.setMetric_featureenvy(false);
			}
			if(!and && !atfdLessThan && !laaLessThan) {
				if (dc.getAtfd() > atfdGUI || dc.getLaa() > laaGUI)
					dc.setMetric_featureenvy(false);
			}
			
			if(and && atfdLessThan && !laaLessThan) {
				if (dc.getAtfd() <= atfdGUI && dc.getLaa() > laaGUI)
					dc.setMetric_featureenvy(false);
			}
			if(!and && atfdLessThan && !laaLessThan) {
				if (dc.getAtfd() <= atfdGUI || dc.getLaa() > laaGUI)
					dc.setMetric_featureenvy(false);
			}
			
			if(and && !atfdLessThan && laaLessThan) {
				if (dc.getAtfd() > atfdGUI && dc.getLaa() <= laaGUI)
					dc.setMetric_featureenvy(false);
			}
			if(!and && !atfdLessThan && laaLessThan) {
				if (dc.getAtfd() > atfdGUI || dc.getLaa() <= laaGUI)
					dc.setMetric_featureenvy(false);
			}
		}
	}
	
	
	
	
	
	
	/**
	 * method that calculates all indicators
	 * for each DataContainer:
	 * -> compare is_long_method and PMD, write type of indicator in qualityPMD 
	 * -> compare is_long_method and iPlasma, write type of indicator in qualityIPLASMA
	 * gives total for all indicators for iPlasma and PMD
	 */
	public void calculateIndicators () {
		if (fileListed != null) {

			//reset values
			pdci=0;
			pdii=0;
			padci=0;
			padii=0;

			ipdci=0;
			ipdii=0;
			ipadci=0;
			ipadii=0;

			for (DataContainer dc: fileListed) {

				//PMD
				if(dc.getPmd() && dc.getIs_long_method()) {
					dc.setStatusPMD("DCI");
					pdci++;
				}

				if(dc.getPmd() && !dc.getIs_long_method()) {
					dc.setStatusPMD("DII");
					pdii++;
				}

				if(!dc.getPmd() && !dc.getIs_long_method()) {
					dc.setStatusPMD("ADCI");
					padci++;
				}

				if(!dc.getPmd() && dc.getIs_long_method()) {
					dc.setStatusPMD("ADII");
					padii++;
				}

				//iPLASMA
				if(dc.getiPlasma() && dc.getIs_long_method()) {
					dc.setStatusIPLASMA("DCI");
					ipdci++;
				}

				if(dc.getiPlasma() && !dc.getIs_long_method()) {
					dc.setStatusIPLASMA("DII");
					ipdii++;
				}

				if(!dc.getiPlasma() && !dc.getIs_long_method()) {
					dc.setStatusIPLASMA("ADCI");
					ipadci++;
				}

				if(!dc.getiPlasma() && dc.getIs_long_method()) {
					dc.setStatusIPLASMA("ADII");
					ipadii++;
				}

			}
		}
	}
	
	
	
	
	
	
	/**
	 * method that calculates all indicators
	 * for each DataContainer:
	 * -> compare is_long_method and MetricLongMethod, write type of indicator in qualityMetricLongMethod
	 * -> compare is_feature_envy and MetricFeatureEnvy, write type of indicator in qualityMetricFeatureEnvy
	 * gives total for all indicators for MetricLongMethod and MetricFeatureEnvy
	 */
	public void calculateIndicatorsMetric () {
		mLMdci=0;
		mLMdii=0;
		mLMadci=0;
		mLMadii=0;
		
		mFEdci=0;
		mFEdii=0;
		mFEadci=0;
		mFEadii=0;
		
		for (DataContainer dc: fileListed) {
			
			//loc and cyclo
			if(dc.getMetricLongMethod() && dc.getIs_long_method()) {
				dc.setQualityLM("DCI");
				mLMdci++;
			}
		
			if(dc.getMetricLongMethod() && !dc.getIs_long_method()) {
				dc.setQualityLM("DII");
				mLMdii++;
			}
			
			if(!dc.getMetricLongMethod() && !dc.getIs_long_method()) {
				dc.setQualityLM("ADCI");
				mLMadci++;
			}
		
			if(!dc.getMetricLongMethod() && dc.getIs_long_method()) {
				dc.setQualityLM("ADII");
				mLMadii++;
			}
			
			//atfd and laa
			if(dc.getMetricFeatureEnvy() && dc.getIs_feature_envy()) {
				dc.setQualityFE("DCI");
				mFEdci++;
			}
		
			if(dc.getMetricFeatureEnvy() && !dc.getIs_feature_envy()) {
				dc.setQualityFE("DII");
				mFEdii++;
			}
			
			if(!dc.getMetricFeatureEnvy() && !dc.getIs_feature_envy()) {
				dc.setQualityFE("ADCI");
				mFEadci++;
			}
		
			if(!dc.getMetricFeatureEnvy() && dc.getIs_feature_envy()) {
				dc.setQualityFE("ADII");
				mFEadii++;
			}

		}
	}

	
	
	
	
	
	/**
	 * @return the workbook
	 */
	public Workbook getWorkbook() {
		return workbook;
	}

	/**
	 * @return the loc
	 */
	public double getLoc() {
		return loc;
	}

	/**
	 * @return the cyclo
	 */
	public double getCyclo() {
		return cyclo;
	}

	/**
	 * @return the atfd
	 */
	public double getAtfd() {
		return atfd;
	}

	/**
	 * @return the laa
	 */
	public double getLaa() {
		return laa;
	}

	/**
	 * @return the mLMdci
	 */
	public int getmLMdci() {
		return mLMdci;
	}

	/**
	 * @return the mLMdii
	 */
	public int getmLMdii() {
		return mLMdii;
	}

	/**
	 * @return the mLMadci
	 */
	public int getmLMadci() {
		return mLMadci;
	}

	/**
	 * @return the mLMadii
	 */
	public int getmLMadii() {
		return mLMadii;
	}

	/**
	 * @return the mFEdci
	 */
	public int getmFEdci() {
		return mFEdci;
	}

	/**
	 * @return the mFEdii
	 */
	public int getmFEdii() {
		return mFEdii;
	}

	/**
	 * @return the mFEadci
	 */
	public int getmFEadci() {
		return mFEadci;
	}

	/**
	 * @return the mFEadii
	 */
	public int getmFEadii() {
		return mFEadii;
	}

	/**
	 * @param workbook the workbook to set
	 */
	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}

	/**
	 * @param loc the loc to set
	 */
	public void setLoc(double loc) {
		this.loc = loc;
	}

	/**
	 * @param cyclo the cyclo to set
	 */
	public void setCyclo(double cyclo) {
		this.cyclo = cyclo;
	}

	/**
	 * @param atfd the atfd to set
	 */
	public void setAtfd(double atfd) {
		this.atfd = atfd;
	}

	/**
	 * @param laa the laa to set
	 */
	public void setLaa(double laa) {
		this.laa = laa;
	}

	/**
	 * @param pdci the pdci to set
	 */
	public void setPdci(int pdci) {
		this.pdci = pdci;
	}

	/**
	 * @param pdii the pdii to set
	 */
	public void setPdii(int pdii) {
		this.pdii = pdii;
	}

	/**
	 * @param padci the padci to set
	 */
	public void setPadci(int padci) {
		this.padci = padci;
	}

	/**
	 * @param padii the padii to set
	 */
	public void setPadii(int padii) {
		this.padii = padii;
	}

	/**
	 * @param ipdci the ipdci to set
	 */
	public void setIpdci(int ipdci) {
		this.ipdci = ipdci;
	}

	/**
	 * @param ipdii the ipdii to set
	 */
	public void setIpdii(int ipdii) {
		this.ipdii = ipdii;
	}

	/**
	 * @param ipadci the ipadci to set
	 */
	public void setIpadci(int ipadci) {
		this.ipadci = ipadci;
	}

	/**
	 * @param ipadii the ipadii to set
	 */
	public void setIpadii(int ipadii) {
		this.ipadii = ipadii;
	}

	/**
	 * @param mLMdci the mLMdci to set
	 */
	public void setmLMdci(int mLMdci) {
		this.mLMdci = mLMdci;
	}

	/**
	 * @param mLMdii the mLMdii to set
	 */
	public void setmLMdii(int mLMdii) {
		this.mLMdii = mLMdii;
	}

	/**
	 * @param mLMadci the mLMadci to set
	 */
	public void setmLMadci(int mLMadci) {
		this.mLMadci = mLMadci;
	}

	/**
	 * @param mLMadii the mLMadii to set
	 */
	public void setmLMadii(int mLMadii) {
		this.mLMadii = mLMadii;
	}

	/**
	 * @param mFEdci the mFEdci to set
	 */
	public void setmFEdci(int mFEdci) {
		this.mFEdci = mFEdci;
	}

	/**
	 * @param mFEdii the mFEdii to set
	 */
	public void setmFEdii(int mFEdii) {
		this.mFEdii = mFEdii;
	}

	/**
	 * @param mFEadci the mFEadci to set
	 */
	public void setmFEadci(int mFEadci) {
		this.mFEadci = mFEadci;
	}

	/**
	 * @param mFEadii the mFEadii to set
	 */
	public void setmFEadii(int mFEadii) {
		this.mFEadii = mFEadii;
	}
	



	public void setLOC(double lOC) {
		loc = lOC;
	}

	public void setCYCLO(double cYCLO) {
		cyclo = cYCLO;
	}

	public void setATFD(double aTFD) {
		atfd = aTFD;
	}

	public void setLAA(double lAA) {
		laa = lAA;
	}
	
	public int getPdci() {
		return pdci;
	}

	public int getPdii() {
		return pdii;
	}

	public int getPadci() {
		return padci;
	}

	public int getPadii() {
		return padii;
	}

	public int getIpdci() {
		return ipdci;
	}

	public int getIpdii() {
		return ipdii;
	}

	public int getIpadci() {
		return ipadci;
	}

	public int getIpadii() {
		return ipadii;
	}

	
	public List<DataContainer> getFileListed() {
		return fileListed;
	}
	public void setFileListed(List<DataContainer> filelisted) {
		fileListed = filelisted;
	}
}

