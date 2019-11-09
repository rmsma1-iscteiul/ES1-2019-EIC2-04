package project.backend;
import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import project.backend.containers.DataContainer;
public class Backend {



	
	
	
	
	// parse file to ... (map?)
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
		return list;
	}
	
	
	
	
	private Sheet file_to_sheet(File file) throws EncryptedDocumentException, IOException {
		Workbook workbook;
		workbook = WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheetAt(0);
		return sheet;
	}

	
	
	
	
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
		boolean is_long_method = (cells[8].charAt(0) == 'V');//TODO change to is_long_method()
		String iPlasma = cells[9];
		String pMD = cells[10];
		boolean is_feature_envy = (cells[11].charAt(0) == 'V');//TODO change to is_feature_envy()
		DataContainer container = new DataContainer(methodID, packageName, className,method, lOC, cYCLO, aTFD, lAA, is_long_method, iPlasma, pMD, is_feature_envy, "TODO"); 
		return container;
	}

	
	
	
	
	// check map //TODO pull arguments from GUI (which arguments)
	public Map<Integer,ArrayList<Object>> checkList (Map<Integer,ArrayList<Object>> map) {
		//TODO map
		return map;
	}

	
	
	
	
	public boolean is_long_method (int LOC, int CYCLO, int locIN, int cycloIN) {
		return LOC > locIN && CYCLO > cycloIN;
	}

	
	
	
	
	public boolean is_feature_envy (int ATFD, int LAA, int atfdIN, int laaIN) {
		return ATFD > atfdIN && LAA < laaIN;
	}

	
	
	
	
	//não percebi
	public boolean iPlasma () {
		return true;
	}


	
	
	
	
	//não percebi
	public boolean PMD () {
		return true;
	}

	
	
	
	
	public void calculateIndicators (Map<Integer,ArrayList<Object>> map) {
//		for (int ID: map.keySet()) {
//				- DCI, quando o valor da coluna correspondente à ferramenta (PMI ou iPlasma) é TRUE e a coluna
//				is_long_method também é TRUE;
//				- DII, quando o valor da coluna correspondente à ferramenta (PMI ou iPlasma) é TRUE e a coluna
//				is_long_method é FALSE;
//				- ADCI, quando o valor da coluna correspondente à ferramenta (PMI ou iPlasma) é FALSE e a coluna
//				is_long_method também é FALSE;
//				- ADII, quando o valor da coluna correspondente à ferramenta (PMI ou iPlasma) é FALSE e a coluna
//				is_long_method é TRUE.
//
//		}
	}

}
