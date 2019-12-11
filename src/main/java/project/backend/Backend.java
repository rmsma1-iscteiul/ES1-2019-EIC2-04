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

	
	
	
	// check list with arguments from GUI
	/**
	 * calls method calculateIndicators(boolean)
	 * @param bool from GUI deciding AND/OR operator (true=OR/false=AND)
	 * @return list after calculating indicators
	 */
	public List<DataContainer> checkList (boolean bool) {
		assert (fileListed != null);
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
			else if(!and && locLessThan && cycloLessThan) {
				if (dc.getLoc() <= locGUI || dc.getCyclo() <= cycloGUI)
					dc.setMetric_longmethod(false);
			}
			
			else if(and && !locLessThan && !cycloLessThan) {
				if (dc.getLoc() > locGUI && dc.getCyclo() > cycloGUI)
					dc.setMetric_longmethod(false);
			}
			else if(!and && !locLessThan && !cycloLessThan) {
				if (dc.getLoc() > locGUI || dc.getCyclo() > cycloGUI)
					dc.setMetric_longmethod(false);
			}
			
			else if(and && locLessThan && !cycloLessThan) {
				if (dc.getLoc() <= locGUI && dc.getCyclo() > cycloGUI)
					dc.setMetric_longmethod(false);
			}
			else if(!and && locLessThan && !cycloLessThan) {
				if (dc.getLoc() <= locGUI || dc.getCyclo() > cycloGUI)
					dc.setMetric_longmethod(false);
			}
			
			else if(and && !locLessThan && cycloLessThan) {
				if (dc.getLoc() > locGUI && dc.getCyclo() <= cycloGUI)
					dc.setMetric_longmethod(false);
			}
			else if(!and && !locLessThan && cycloLessThan) {
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
			else if(!and && atfdLessThan && laaLessThan) {
				if (dc.getAtfd() <= atfdGUI || dc.getLaa() <= laaGUI)
					dc.setMetric_featureenvy(false);
			}
			
			else if(and && !atfdLessThan && !laaLessThan) {
				if (dc.getAtfd() > atfdGUI && dc.getLaa() > laaGUI)
					dc.setMetric_featureenvy(false);
			}
			else if(!and && !atfdLessThan && !laaLessThan) {
				if (dc.getAtfd() > atfdGUI || dc.getLaa() > laaGUI)
					dc.setMetric_featureenvy(false);
			}
			
			else if(and && atfdLessThan && !laaLessThan) {
				if (dc.getAtfd() <= atfdGUI && dc.getLaa() > laaGUI)
					dc.setMetric_featureenvy(false);
			}
			else if(!and && atfdLessThan && !laaLessThan) {
				if (dc.getAtfd() <= atfdGUI || dc.getLaa() > laaGUI)
					dc.setMetric_featureenvy(false);
			}
			
			else if(and && !atfdLessThan && laaLessThan) {
				if (dc.getAtfd() > atfdGUI && dc.getLaa() <= laaGUI)
					dc.setMetric_featureenvy(false);
			}
			else if(!and && !atfdLessThan && laaLessThan) {
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
		assert (fileListed != null);
		
		for (DataContainer dc: fileListed) {
			
			//PMD
			if(dc.getPmd() && dc.getIs_long_method()) {
				dc.setStatusPMD("DCI");
				pdci++;
			}
		
			else if(dc.getPmd() && !dc.getIs_long_method()) {
				dc.setStatusPMD("DII");
				pdii++;
			}
			
			else if(!dc.getPmd() && !dc.getIs_long_method()) {
				dc.setStatusPMD("ADCI");
				padci++;
			}
		
			else if(!dc.getPmd() && dc.getIs_long_method()) {
				dc.setStatusPMD("ADII");
				padii++;
			}
			
			//iPLASMA
			if(dc.getiPlasma() && dc.getIs_long_method()) {
				dc.setStatusIPLASMA("DCI");
				ipdci++;
			}
		
			else if(dc.getiPlasma() && !dc.getIs_long_method()) {
				dc.setStatusIPLASMA("DII");
				ipdii++;
			}
			
			else if(!dc.getiPlasma() && !dc.getIs_long_method()) {
				dc.setStatusIPLASMA("ADCI");
				ipadci++;
			}
		
			else if(!dc.getiPlasma() && dc.getIs_long_method()) {
				dc.setStatusIPLASMA("ADII");
				ipadii++;
			}

		}
		//debugging
		int a = pdci+pdii+padci+padii;
		int b = ipdci+ipdii+ipadci+ipadii;
		
		System.out.println("PMD");
		System.out.println(pdci+" / "+pdii+" / "+padci+" / "+padii);
		System.out.println("total: "+a);
		
		System.out.println("iPlasma");
		System.out.println(ipdci+" / "+ipdii+" / "+ipadci+" / "+ipadii);
		System.out.println("total: "+b);
		//
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
		
			else if(dc.getMetricLongMethod() && !dc.getIs_long_method()) {
				dc.setQualityLM("DII");
				mLMdii++;
			}
			
			else if(!dc.getMetricLongMethod() && !dc.getIs_long_method()) {
				dc.setQualityLM("ADCI");
				mLMadci++;
			}
		
			else if(!dc.getMetricLongMethod() && dc.getIs_long_method()) {
				dc.setQualityLM("ADII");
				mLMadii++;
			}
			
			//atfd and laa
			if(dc.getMetricFeatureEnvy() && dc.getIs_feature_envy()) {
				dc.setQualityFE("DCI");
				mFEdci++;
			}
		
			else if(dc.getMetricFeatureEnvy() && !dc.getIs_feature_envy()) {
				dc.setQualityFE("DII");
				mFEdii++;
			}
			
			else if(!dc.getMetricFeatureEnvy() && !dc.getIs_feature_envy()) {
				dc.setQualityFE("ADCI");
				mFEadci++;
			}
		
			else if(!dc.getMetricFeatureEnvy() && dc.getIs_feature_envy()) {
				dc.setQualityFE("ADII");
				mFEadii++;
			}

		}
		//debugging
		int a = mLMdci+mLMdii+mLMadci+mLMadii;
		int b = mFEdci+mFEdii+mFEadci+mFEadii;
		
		System.out.println("METRIC LM");
		System.out.println(mLMdci+" / "+mLMdii+" / "+mLMadci+" / "+mLMadii);
		System.out.println("total: "+a);
		
		System.out.println("METRIC FE");
		System.out.println(mFEdci+" / "+mFEdii+" / "+mFEadci+" / "+mFEadii);
		System.out.println("total: "+b);
		//
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
	 * @param laa the laa to set
	 */
	public void setLaa(double laa) {
		this.laa = laa;
	}

	/**
	 * @param atfd the atfd to set
	 */
	public void setAtfd(double atfd) {
		this.atfd = atfd;
	}

	/**
	 * @param cyclo the cyclo to set
	 */
	public void setCyclo(double cyclo) {
		this.cyclo = cyclo;
	}

	/**
	 * @param loc the loc to set
	 */
	public void setLoc(double loc) {
		this.loc = loc;
	}
}

