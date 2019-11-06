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
		
		//não percebi
		public boolean iPlasma () {
			return true;
		}
		
		//não percebi
		public boolean PMD () {
			return true;
		}
		
		public void calculateIndicators (Map<Integer,ArrayList<Object>> map) {
			for (int ID: map.keySet()) {
				//	- DCI, quando o valor da coluna correspondente à ferramenta (PMI ou iPlasma) é TRUE e a coluna
				//	is_long_method também é TRUE;
				//	- DII, quando o valor da coluna correspondente à ferramenta (PMI ou iPlasma) é TRUE e a coluna
				//	is_long_method é FALSE;
				//	- ADCI, quando o valor da coluna correspondente à ferramenta (PMI ou iPlasma) é FALSE e a coluna
				//	is_long_method também é FALSE;
				//	- ADII, quando o valor da coluna correspondente à ferramenta (PMI ou iPlasma) é FALSE e a coluna
				//	is_long_method é TRUE.

			}
		}

}
