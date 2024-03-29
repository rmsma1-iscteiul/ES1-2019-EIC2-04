import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.backend.Backend;
import project.backend.containers.DataContainer;
import project.backend.containers.MetricsRule;

class BackendTest {
	private static final String fileLocation = System.getProperty("user.dir")
			+ "/src/main/java/project/saves/saved_rules.txt";

	static Backend manager = new Backend();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		manager.setFileListed(new ArrayList<DataContainer>());
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testParseFileToMap() {
		File file = new File(System.getProperty("user.dir") + "/src/test/java/test_file.txt");
		assertNotNull(manager.parseFileToMap(file));
	}

	@Test
	void testCreateRule() {
		Path file = Paths.get(fileLocation);

		try {
			MetricsRule a = manager.createRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
			assertNotNull(a);
			List<String> lines = Files.readAllLines(file);
			boolean b = false;
			for (String string : lines) {
				if (string.equals(a.fileToString())) {
					b = true;
				}
			}
			assertEquals(true, b);
		} catch (IOException e) {
			fail("Threw IOException");
		}
	}

	@Test
	void testDeleteRule() {

		Path file = Paths.get(fileLocation);

		try {
			MetricsRule a = manager.createRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
			assumeNotNull(a);
			manager.deleteRule(a);
			List<String> lines = Files.readAllLines(file);
			boolean b = true;
			for (String string : lines) {
				if (string.equals(a.fileToString())) {
					b = false;
				}
			}
			assertEquals(false, b);
		} catch (IOException e) {
			fail("Threw IOException");
		}

	}

	@Test
	void testLoadRules() {
		try {
			assertNotNull(manager.loadRules());
		} catch (IOException e) {
			fail("Threw IOException");
		}
	}

	@Test
	void testCheckList() {
		DataContainer dc1 = new DataContainer(1, "test", "test", "test", 1, 1, 10, 8, true, true, true, true, "", "",
				true, true, "", "");
		manager.getFileListed().add(dc1);
		assertNotNull(manager.checkList());
	}

	@Test
	void testMetricLongMethod() {
<<<<<<< HEAD
		DataContainer dc1 = new DataContainer(1, "test", "test", "test", 10, 10, 1, 1, true, true, true, true, "", "",
				false, false, "", "");
=======
		manager.setFileListed( new ArrayList<DataContainer>() );
		DataContainer dc1 = new DataContainer(1,"test","test","test",10,10,1,1,true,true,true,true,"","",false,false,"","");
>>>>>>> branch 'Dev_branch' of https://github.com/rmsma1-iscteiul/ES1-2019-EIC2-04.git
		manager.getFileListed().add(dc1);
<<<<<<< HEAD

=======
		
		DataContainer dc2 = new DataContainer(1,"test","test","test",10,12,1,1,true,true,true,true,"","",false,false,"","");
		manager.getFileListed().add(dc2);
		
		DataContainer dc3 = new DataContainer(1,"test","test","test",12,12,1,1,true,true,true,true,"","",false,false,"","");
		manager.getFileListed().add(dc3);
		
		DataContainer dc4 = new DataContainer(1,"test","test","test",12,10,1,1,true,true,true,true,"","",false,false,"","");
		manager.getFileListed().add(dc4);
		
>>>>>>> branch 'Dev_branch' of https://github.com/rmsma1-iscteiul/ES1-2019-EIC2-04.git
		manager.MetricLongMethod(true, true, true, 11, 11);
		assertEquals(dc1.getMetricLongMethod(), false);
<<<<<<< HEAD

		manager.MetricLongMethod(false, true, true, 11, 9);
=======
		assertEquals(dc2.getMetricLongMethod(), true);
		assertEquals(dc3.getMetricLongMethod(), true);
		assertEquals(dc4.getMetricLongMethod(), true);
		
		manager.MetricLongMethod(false, true, true, 11, 11);
>>>>>>> branch 'Dev_branch' of https://github.com/rmsma1-iscteiul/ES1-2019-EIC2-04.git
		assertEquals(dc1.getMetricLongMethod(), false);
<<<<<<< HEAD

		manager.MetricLongMethod(true, false, false, 9, 9);
		assertEquals(dc1.getMetricLongMethod(), false);

		manager.MetricLongMethod(false, false, false, 9, 11);
		assertEquals(dc1.getMetricLongMethod(), false);

		manager.MetricLongMethod(true, true, false, 11, 9);
		assertEquals(dc1.getMetricLongMethod(), false);

		manager.MetricLongMethod(false, true, false, 11, 9);
=======
		assertEquals(dc2.getMetricLongMethod(), false);
		assertEquals(dc3.getMetricLongMethod(), true);
		assertEquals(dc4.getMetricLongMethod(), false);
		
		manager.MetricLongMethod(true, false, false, 11, 11);
		assertEquals(dc1.getMetricLongMethod(), true);
		assertEquals(dc2.getMetricLongMethod(), true);
		assertEquals(dc3.getMetricLongMethod(), false);
		assertEquals(dc4.getMetricLongMethod(), true);
		
		manager.MetricLongMethod(false, false, false, 11, 11);
		assertEquals(dc1.getMetricLongMethod(), true);
		assertEquals(dc2.getMetricLongMethod(), false);
		assertEquals(dc3.getMetricLongMethod(), false);
		assertEquals(dc4.getMetricLongMethod(), false);
		
		manager.MetricLongMethod(true, true, false, 11, 11);
		assertEquals(dc1.getMetricLongMethod(), true);
		assertEquals(dc2.getMetricLongMethod(), false);
		assertEquals(dc3.getMetricLongMethod(), true);
		assertEquals(dc4.getMetricLongMethod(), true);
		
		manager.MetricLongMethod(false, true, false, 11, 11);
>>>>>>> branch 'Dev_branch' of https://github.com/rmsma1-iscteiul/ES1-2019-EIC2-04.git
		assertEquals(dc1.getMetricLongMethod(), false);
<<<<<<< HEAD

		manager.MetricLongMethod(true, false, true, 9, 11);
		assertEquals(dc1.getMetricLongMethod(), false);

		manager.MetricLongMethod(false, false, true, 9, 11);
=======
		assertEquals(dc2.getMetricLongMethod(), false);
		assertEquals(dc3.getMetricLongMethod(), false);
		assertEquals(dc4.getMetricLongMethod(), true);
		
		manager.MetricLongMethod(true, false, true, 11, 11);
		assertEquals(dc1.getMetricLongMethod(), true);
		assertEquals(dc2.getMetricLongMethod(), true);
		assertEquals(dc3.getMetricLongMethod(), true);
		assertEquals(dc4.getMetricLongMethod(), false);
		
		manager.MetricLongMethod(false, false, true, 11, 11);
>>>>>>> branch 'Dev_branch' of https://github.com/rmsma1-iscteiul/ES1-2019-EIC2-04.git
		assertEquals(dc1.getMetricLongMethod(), false);
		assertEquals(dc2.getMetricLongMethod(), true);
		assertEquals(dc3.getMetricLongMethod(), false);
		assertEquals(dc4.getMetricLongMethod(), false);
		
		
	}

	@Test
	void testMetricFeatureEnvy() {
<<<<<<< HEAD
		DataContainer dc1 = new DataContainer(1, "test", "test", "test", 1, 1, 10, 10, true, true, true, true, "", "",
				false, false, "", "");
=======
		manager.setFileListed( new ArrayList<DataContainer>() );
		DataContainer dc1 = new DataContainer(1,"test","test","test",1,1,10,10,true,true,true,true,"","",false,false,"","");
>>>>>>> branch 'Dev_branch' of https://github.com/rmsma1-iscteiul/ES1-2019-EIC2-04.git
		manager.getFileListed().add(dc1);
<<<<<<< HEAD

=======
		
		DataContainer dc2 = new DataContainer(1,"test","test","test",1,1,10,12,true,true,true,true,"","",false,false,"","");
		manager.getFileListed().add(dc2);
		
		DataContainer dc3 = new DataContainer(1,"test","test","test",1,1,12,12,true,true,true,true,"","",false,false,"","");
		manager.getFileListed().add(dc3);
		
		DataContainer dc4 = new DataContainer(1,"test","test","test",1,1,12,10,true,true,true,true,"","",false,false,"","");
		manager.getFileListed().add(dc4);
		
>>>>>>> branch 'Dev_branch' of https://github.com/rmsma1-iscteiul/ES1-2019-EIC2-04.git
		manager.MetricFeatureEnvy(true, true, true, 11, 11);
		assertEquals(dc1.getMetricFeatureEnvy(), false);
<<<<<<< HEAD

		manager.MetricFeatureEnvy(false, true, true, 11, 9);
=======
		assertEquals(dc2.getMetricFeatureEnvy(), true);
		assertEquals(dc3.getMetricFeatureEnvy(), true);
		assertEquals(dc4.getMetricFeatureEnvy(), true);
		
		manager.MetricFeatureEnvy(false, true, true, 11, 11);
>>>>>>> branch 'Dev_branch' of https://github.com/rmsma1-iscteiul/ES1-2019-EIC2-04.git
		assertEquals(dc1.getMetricFeatureEnvy(), false);
<<<<<<< HEAD

		manager.MetricFeatureEnvy(true, false, false, 9, 9);
		assertEquals(dc1.getMetricFeatureEnvy(), false);

		manager.MetricFeatureEnvy(false, false, false, 9, 11);
		assertEquals(dc1.getMetricFeatureEnvy(), false);

		manager.MetricFeatureEnvy(true, true, false, 11, 9);
		assertEquals(dc1.getMetricFeatureEnvy(), false);

		manager.MetricFeatureEnvy(false, true, false, 11, 9);
=======
		assertEquals(dc2.getMetricFeatureEnvy(), false);
		assertEquals(dc3.getMetricFeatureEnvy(), true);
		assertEquals(dc4.getMetricFeatureEnvy(), false);
		
		manager.MetricFeatureEnvy(true, false, false, 11, 11);
		assertEquals(dc1.getMetricFeatureEnvy(), true);
		assertEquals(dc2.getMetricFeatureEnvy(), true);
		assertEquals(dc3.getMetricFeatureEnvy(), false);
		assertEquals(dc4.getMetricFeatureEnvy(), true);
		
		manager.MetricFeatureEnvy(false, false, false, 11, 11);
		assertEquals(dc1.getMetricFeatureEnvy(), true);
		assertEquals(dc2.getMetricFeatureEnvy(), false);
		assertEquals(dc3.getMetricFeatureEnvy(), false);
		assertEquals(dc4.getMetricFeatureEnvy(), false);
		
		manager.MetricFeatureEnvy(true, true, false, 11, 11);
		assertEquals(dc1.getMetricFeatureEnvy(), true);
		assertEquals(dc2.getMetricFeatureEnvy(), false);
		assertEquals(dc3.getMetricFeatureEnvy(), true);
		assertEquals(dc4.getMetricFeatureEnvy(), true);
		
		manager.MetricFeatureEnvy(false, true, false, 11, 11);
>>>>>>> branch 'Dev_branch' of https://github.com/rmsma1-iscteiul/ES1-2019-EIC2-04.git
		assertEquals(dc1.getMetricFeatureEnvy(), false);
<<<<<<< HEAD

		manager.MetricFeatureEnvy(true, false, true, 9, 11);
		assertEquals(dc1.getMetricFeatureEnvy(), false);

		manager.MetricFeatureEnvy(false, false, true, 9, 11);
=======
		assertEquals(dc2.getMetricFeatureEnvy(), false);
		assertEquals(dc3.getMetricFeatureEnvy(), false);
		assertEquals(dc4.getMetricFeatureEnvy(), true);
		
		manager.MetricFeatureEnvy(true, false, true, 11, 11);
		assertEquals(dc1.getMetricFeatureEnvy(), true);
		assertEquals(dc2.getMetricFeatureEnvy(), true);
		assertEquals(dc3.getMetricFeatureEnvy(), true);
		assertEquals(dc4.getMetricFeatureEnvy(), false);
		
		manager.MetricFeatureEnvy(false, false, true, 11, 11);
>>>>>>> branch 'Dev_branch' of https://github.com/rmsma1-iscteiul/ES1-2019-EIC2-04.git
		assertEquals(dc1.getMetricFeatureEnvy(), false);
		assertEquals(dc2.getMetricFeatureEnvy(), true);
		assertEquals(dc3.getMetricFeatureEnvy(), false);
		assertEquals(dc4.getMetricFeatureEnvy(), false);
	}

	@Test
	void testCalculateIndicators() {
		DataContainer dc1 = new DataContainer(1, "test", "test", "test", 1, 1, 10, 8, true, true, true, true, "", "",
				true, true, "", "");
		manager.getFileListed().add(dc1);
		DataContainer dc2 = new DataContainer(1, "test", "test", "test", 1, 1, 10, 8, false, true, true, true, "", "",
				true, true, "", "");
		manager.getFileListed().add(dc2);
		DataContainer dc3 = new DataContainer(1, "test", "test", "test", 1, 1, 10, 8, false, false, false, true, "", "",
				true, true, "", "");
		manager.getFileListed().add(dc3);
		DataContainer dc4 = new DataContainer(1, "test", "test", "test", 1, 1, 10, 8, true, false, false, true, "", "",
				true, true, "", "");
		manager.getFileListed().add(dc4);

		manager.calculateIndicators();

		assertEquals(dc1.getStatusPMD(), "DCI");
		assertEquals(dc1.getStatusIPLASMA(), "DCI");

		assertEquals(dc2.getStatusPMD(), "DII");
		assertEquals(dc2.getStatusIPLASMA(), "DII");

		assertEquals(dc3.getStatusPMD(), "ADCI");
		assertEquals(dc3.getStatusIPLASMA(), "ADCI");

		assertEquals(dc4.getStatusPMD(), "ADII");
		assertEquals(dc4.getStatusIPLASMA(), "ADII");
<<<<<<< HEAD

=======
		
		manager.setFileListed(null);
		manager.calculateIndicators();
		
>>>>>>> branch 'Dev_branch' of https://github.com/rmsma1-iscteiul/ES1-2019-EIC2-04.git
	}

	@Test
	void testCalculateIndicatorsMetric() {
		DataContainer dc1 = new DataContainer(1, "test", "test", "test", 1, 1, 10, 8, true, true, true, true, "", "",
				true, true, "", "");
		manager.getFileListed().add(dc1);
		DataContainer dc2 = new DataContainer(1, "test", "test", "test", 1, 1, 10, 8, false, true, true, false, "", "",
				true, true, "", "");
		manager.getFileListed().add(dc2);
		DataContainer dc3 = new DataContainer(1, "test", "test", "test", 1, 1, 10, 8, false, false, false, false, "",
				"", false, false, "", "");
		manager.getFileListed().add(dc3);
		DataContainer dc4 = new DataContainer(1, "test", "test", "test", 1, 1, 10, 8, true, false, false, true, "", "",
				false, false, "", "");
		manager.getFileListed().add(dc4);

		manager.calculateIndicatorsMetric();
		;

		assertEquals(dc1.getQualityMetricLongMethod(), "DCI");
		assertEquals(dc1.getQualityMetricFeatureEnvy(), "DCI");

		assertEquals(dc2.getQualityMetricLongMethod(), "DII");
		assertEquals(dc2.getQualityMetricFeatureEnvy(), "DII");

		assertEquals(dc3.getQualityMetricLongMethod(), "ADCI");
		assertEquals(dc3.getQualityMetricFeatureEnvy(), "ADCI");

		assertEquals(dc4.getQualityMetricLongMethod(), "ADII");
		assertEquals(dc4.getQualityMetricFeatureEnvy(), "ADII");
	}

	@Test
	void testGetWorkbook() {
		fail("Not yet implemented");
	}

	@Test
	void testSetWorkbook() {
		fail("Not yet implemented");
	}

	@Test
	void testGetLoc() {
		assertNotNull(manager.getLoc());
	}

	@Test
	void testGetCyclo() {
		assertNotNull(manager.getCyclo());
	}

	@Test
	void testGetAtfd() {
		assertNotNull(manager.getAtfd());
	}

	@Test
	void testGetLaa() {
		assertNotNull(manager.getLaa());
	}

	@Test
	void testGetmLMdci() {
		assertNotNull(manager.getmLMdci());
	}

	@Test
	void testGetmLMdii() {
		assertNotNull(manager.getmLMdii());
	}

	@Test
	void testGetmLMadci() {
		assertNotNull(manager.getmLMadci());
	}

	@Test
	void testGetmLMadii() {
		assertNotNull(manager.getmLMadii());
	}

	@Test
	void testGetmFEdci() {
		assertNotNull(manager.getmFEdci());
	}

	@Test
	void testGetmFEdii() {
		assertNotNull(manager.getmFEdii());
	}

	@Test
	void testGetmFEadci() {
		assertNotNull(manager.getmFEadci());
	}

	@Test
	void testGetmFEadii() {
		assertNotNull(manager.getmFEadii());
	}

	@Test
	void testSetLoc() {
		manager.setLoc(2);
		assertEquals(manager.getLoc(), 2);
	}

	@Test
	void testSetCyclo() {
		manager.setCyclo(2);
		assertEquals(manager.getCyclo(), 2);
	}

	@Test
	void testSetAtfd() {
		manager.setAtfd(2);
		assertEquals(manager.getAtfd(), 2);
	}

	@Test
	void testSetLaa() {
		manager.setLaa(2);
		assertEquals(manager.getLaa(), 2);
	}

	@Test
	void testSetPdci() {
		manager.setPdci(2);
		assertEquals(manager.getPdci(), 2);
	}

	@Test
	void testSetPdii() {
		manager.setPdii(2);
		assertEquals(manager.getPdii(), 2);
	}

	@Test
	void testSetPadci() {
		manager.setPadci(2);
		assertEquals(manager.getPadci(), 2);
	}

	@Test
	void testSetPadii() {
		manager.setPadii(2);
		assertEquals(manager.getPadii(), 2);
	}

	@Test
	void testSetIpdci() {
		manager.setIpdci(2);
		assertEquals(manager.getIpdci(), 2);
	}

	@Test
	void testSetIpdii() {
		manager.setIpdii(2);
		assertEquals(manager.getIpdii(), 2);
	}

	@Test
	void testSetIpadci() {
		manager.setIpadci(2);
		assertEquals(manager.getIpadci(), 2);
	}

	@Test
	void testSetIpadii() {
		manager.setIpadii(2);
		assertEquals(manager.getIpadii(), 2);
	}

	@Test
	void testSetmLMdci() {
		manager.setmLMdci(2);
		assertEquals(manager.getmLMdci(), 2);
	}

	@Test
	void testSetmLMdii() {
		manager.setmLMdii(2);
		assertEquals(manager.getmLMdii(), 2);
	}

	@Test
	void testSetmLMadci() {
		manager.setmLMadci(2);
		assertEquals(manager.getmLMadci(), 2);
	}

	@Test
	void testSetmLMadii() {
		manager.setmLMadii(2);
		assertEquals(manager.getmLMadii(), 2);
	}

	@Test
	void testSetmFEdci() {
		manager.setmFEdci(2);
		assertEquals(manager.getmFEdci(), 2);
	}

	@Test
	void testSetmFEdii() {
		manager.setmFEdii(2);
		assertEquals(manager.getmFEdii(), 2);
	}

	@Test
	void testSetmFEadci() {
		manager.setmFEadci(2);
		assertEquals(manager.getmFEadci(), 2);
	}

	@Test
	void testSetmFEadii() {
		manager.setmFEadii(2);
		assertEquals(manager.getmFEadii(), 2);
	}

	@Test
	void testSetLOC() {
		manager.setLOC(2);
		assertEquals(manager.getLoc(), 2);
	}

	@Test
	void testSetCYCLO() {
		manager.setCYCLO(2);
		assertEquals(manager.getCyclo(), 2);
	}

	@Test
	void testSetATFD() {
		manager.setATFD(2);
		assertEquals(manager.getAtfd(), 2);
	}

	@Test
	void testSetLAA() {
		manager.setLAA(2);
		assertEquals(manager.getLaa(), 2);
	}

	@Test
	void testGetPdci() {
		assertNotNull(manager.getPdci());
	}

	@Test
	void testGetPdii() {
		assertNotNull(manager.getPdii());
	}

	@Test
	void testGetPadci() {
		assertNotNull(manager.getPadci());
	}

	@Test
	void testGetPadii() {
		assertNotNull(manager.getPadii());
	}

	@Test
	void testGetIpdci() {
		assertNotNull(manager.getIpdci());
	}

	@Test
	void testGetIpdii() {
		assertNotNull(manager.getIpdii());
	}

	@Test
	void testGetIpadci() {
		assertNotNull(manager.getIpadci());
	}

	@Test
	void testGetIpadii() {
		assertNotNull(manager.getIpadii());
	}

	@Test
	void testGetFileListed() {
		assertNotNull(manager.getFileListed());
	}

	@Test
	void testSetFileListed() {
		manager.setFileListed(new ArrayList<DataContainer>());
		assertNotNull(manager.getFileListed());
	}

}
