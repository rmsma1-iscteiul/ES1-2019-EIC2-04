package project.backend;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Backend {
	
	
	
	// parse file to ... (map?)
		public Map<Integer,List<Object>> parseFileToMap (File file) {
			Map<Integer,List<Object>> map = new HashMap<Integer, List<Object>>();
			//TODO file --> map
			return map;
		}
		
		// check map //TODO pull arguments from GUI (which arguments)
		public Map<Integer,ArrayList<Object>> checkList (Map<Integer,ArrayList<Object>> map) {
			
			return map;
		}
		
		
		
		public boolean is_long_method (int LOC, int CYCLO, int locIN, int cycloIN) {
			return LOC > locIN && CYCLO > cycloIN;
			
		}
		
		public boolean is_feature_envy (int ATFD, int LAA, int atfdIN, int laaIN) {
			return ATFD > atfdIN && LAA < laaIN;
		}
		
		//n�o percebi
		public boolean iPlasma () {
			return true;
		}
		
		//n�o percebi
		public boolean PMD () {
			return true;
		}
		
		public void calculateIndicators (Map<Integer,ArrayList<Object>> map) {
			for (int ID: map.keySet()) {
				//	- DCI, quando o valor da coluna correspondente � ferramenta (PMI ou iPlasma) � TRUE e a coluna
				//	is_long_method tamb�m � TRUE;
				//	- DII, quando o valor da coluna correspondente � ferramenta (PMI ou iPlasma) � TRUE e a coluna
				//	is_long_method � FALSE;
				//	- ADCI, quando o valor da coluna correspondente � ferramenta (PMI ou iPlasma) � FALSE e a coluna
				//	is_long_method tamb�m � FALSE;
				//	- ADII, quando o valor da coluna correspondente � ferramenta (PMI ou iPlasma) � FALSE e a coluna
				//	is_long_method � TRUE.

			}
		}

}
