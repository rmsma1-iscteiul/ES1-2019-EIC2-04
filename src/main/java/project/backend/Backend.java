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
	private double LOC = 80;
	private double CYCLO = 10;
	private double ATFD = 4;
	private double LAA = 0.42;
	
	private int dci=0;
	private int dii=0;
	private int adci=0;
	private int adii=0;
	
	private List<DataContainer> fileListed;



	public void setLOC(double lOC) {
		LOC = lOC;
	}

	public void setCYCLO(double cYCLO) {
		CYCLO = cYCLO;
	}

	public void setATFD(double aTFD) {
		ATFD = aTFD;
	}

	public void setLAA(double lAA) {
		LAA = lAA;
	}
	
	
	public int getDci() {
		return dci;
	}

	public int getDii() {
		return dii;
	}

	public int getAdci() {
		return adci;
	}

	public int getAdii() {
		return adii;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	 * takes in a row from a excell file previously converted into a apache.poi sheet and creates a new instace of DataContainer from it
	 * @param row 
	 * @return
	 * @throws Exception this is trhown in case the excel file is in the wrong format
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
		boolean is_long_method = is_long_method(false,lOC, cYCLO);
		boolean iPlasma = (cells[9].charAt(0) == 'V');
		boolean pMD = (cells[10].charAt(0) == 'V');
		boolean is_feature_envy = is_feature_envy(false, aTFD, lAA);
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
				"TODO", 
				"TODO"); 
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
		calculateIndicators (bool);
		return fileListed;
	}

	
	
	
	
	/** 
	 * @param bool
	 * boolean that comes from GUI deciding AND/OR operator (true=OR/false=AND)
	 * @param locf
	 * number of lines of code from file
	 * @param cyclof
	 * cycle complexity from file
	 * @return true or false, depending on inputs
	 */
	public boolean is_long_method (boolean bool, double locf, double cyclof) {
		if (!bool) return locf > LOC && cyclof > CYCLO;
		else return locf > LOC || cyclof > CYCLO;
	}

	
	/**
	 * @param bool
	 * boolean that comes from GUI deciding AND/OR operator (true=OR/false=AND)
	 * @param atfdf
	 * access to methods from other classes from file
	 * @param laaf
	 * access to attributes from same class from file
	 * @return true or false, depending on inputs
	 */
	public boolean is_feature_envy (boolean bool, double atfdf, double laaf) {
		if (!bool) return atfdf > ATFD && laaf < LAA;
		else return atfdf > ATFD || laaf < LAA;
	}
	

	
	
	/**
	 * method that calculates all indicators
	 * for each DataContainer:
	 * -> overwrite is_long_method and is_feature_envy
	 * -> compare is_long_method and PMD, write type of indicator in StatusPMD 
	 * -> compare is_long_method and iPlasma, write type of indicator in StatusIPLASMA
	 * @param bool
	 * boolean that comes from GUI deciding AND/OR operator (true=OR/false=AND)
	 */
	public void calculateIndicators (boolean bool) {
		assert (fileListed != null);
		dci=0;
		dii=0;
		adci=0;
		adii=0;
		
		for (DataContainer dc: fileListed) {
			dc.setIs_long_method( is_long_method(bool, dc.getLoc(), dc.getCyclo()) );
			dc.setIs_feature_envy( is_feature_envy(bool, dc.getAtfd(), dc.getLaa()) );
			
			//PMD
			if(dc.getPmd() && dc.getIs_long_method()) {
				dc.setStatusPMD("DCI");
				dci++;
			}
		
			else if(dc.getPmd() && !dc.getIs_long_method()) {
				dc.setStatusPMD("DII");
				dii++;
			}
			
			else if(!dc.getPmd() && !dc.getIs_long_method()) {
				dc.setStatusPMD("ADCI");
				adci++;
			}
		
			else if(!dc.getPmd() && dc.getIs_long_method()) {
				dc.setStatusPMD("ADII");
				adii++;
			}
			
			//iPLASMA
			if(dc.getiPlasma() && dc.getIs_long_method()) {
				dc.setStatusIPLASMA("DCI");
				dci++;
			}
		
			else if(dc.getiPlasma() && !dc.getIs_long_method()) {
				dc.setStatusIPLASMA("DII");
				dii++;
			}
			
			else if(!dc.getiPlasma() && !dc.getIs_long_method()) {
				dc.setStatusIPLASMA("ADCI");
				adci++;
			}
		
			else if(!dc.getiPlasma() && dc.getIs_long_method()) {
				dc.setStatusIPLASMA("ADII");
				adii++;
			}

		}
	}
	

}

