import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import project.backend.containers.DataContainer;

class DataContainerTest {

	@Test
	void testDataContainer() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertNotNull(a);
	}

	@Test
	void testGetAttributes() {
		String [][] matrix = {
				{"Method ID"    ,"Integer"  , "methodID"   },
				
				{"Package"      ,"String"   , "packageName"},
				{"Class"        ,"String"   , "className"},
				{"Method"       ,"String"   , "method"},
				
				{"Loc"          ,"Integer"  , "loc"},
				{"Cyclo"        ,"Integer"  , "cyclo"},
				{"Atfd"         ,"Integer"  , "atfd"},
				   
				{"Laa"          ,"Double"   , "laa"},
				
				{"Long Method"  ,"Boolean"  , "isLongMethod"},
				
				{"Iplasma"      ,"Boolean"   , "iPlasma"},
				{"Pmd"          ,"Boolean"   , "pmd"},
				
				{"Feature Envy" ,"Boolean"  , "isFeatureEnvy"},
				
				{"QualityPMD"       ,"String"   , "statusPMD"},
				{"QualityIPLASMA"       ,"String"   , "statusIPLASMA"},
				
				{"MetricLM"      ,"Boolean"   , "metricLongMethod"},
				{"MetricFE"          ,"Boolean"   , "metricFeatureEnvy"},
				
				{"QualityMetricLM"       ,"String"   , "qualityMetricLongMethod"},
				{"QualityMetricFE"       ,"String"   , "qualityMetricFeatureEnvy"}
		};
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(matrix,a.getAttributes());
	}

	@Test
	void testMethodIDProperty() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(0,a.methodIDProperty().get());
	}

	@Test
	void testPackageNameProperty() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals("s",a.packageNameProperty().get());
	}

	@Test
	void testClassNameProperty() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals("s",a.classNameProperty().get());
	}

	@Test
	void testMethodProperty() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals("s",a.methodProperty().get());
	}

	@Test
	void testLocProperty() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(0,a.locProperty().get());
	}

	@Test
	void testCycloProperty() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(0,a.cycloProperty().get());
	}

	@Test
	void testAtfdProperty() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(0,a.atfdProperty().get());
	}

	@Test
	void testLaaProperty() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(0.0,a.laaProperty().get());
	}

	@Test
	void testIsLongMethodProperty() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(false,a.isLongMethodProperty().get());
	}

	@Test
	void testIPlasmaProperty() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(false,a.iPlasmaProperty().get());
	}

	@Test
	void testPmdProperty() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(false,a.pmdProperty().get());
	}

	@Test
	void testIsFeatureEnvyProperty() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(false,a.isFeatureEnvyProperty().get());
	}

	@Test
	void testStatusPMDProperty() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals("s",a.statusPMDProperty().get());
	}

	@Test
	void testStatusIPLASMAProperty() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals("s",a.statusIPLASMAProperty().get());
	}

	@Test
	void testMetricLongMethod() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(false,a.metricLongMethod().get());
	}

	@Test
	void testMetricFeatureEnvy() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(false,a.metricFeatureEnvy().get());
	}

	@Test
	void testQualityMetricLongMethod() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals("s",a.qualityMetricLongMethod().get());
	}

	@Test
	void testQualityMetricFeatureEnvy() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals("s",a.qualityMetricFeatureEnvy().get());
	}

	@Test
	void testGetMethodID() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(0,a.getMethodID());
	}

	@Test
	void testGetPackageName() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals("s",a.getPackageName());
	}

	@Test
	void testGetClassName() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals("s",a.getClassName());
	}

	@Test
	void testGetMethod() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals("s",a.getMethod());
	}

	@Test
	void testGetLoc() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(0,a.getLoc());
	}

	@Test
	void testGetCyclo() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(0,a.getCyclo());
	}

	@Test
	void testGetAtfd() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(0,a.getAtfd());
	}

	@Test
	void testGetLaa() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(0.0,a.getLaa());
	}

	@Test
	void testGetIs_long_method() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(false,a.getIs_long_method());
	}

	@Test
	void testGetiPlasma() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(false,a.getiPlasma());
	}

	@Test
	void testGetPmd() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(false,a.getPmd());
	}

	@Test
	void testGetIs_feature_envy() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(false,a.getIs_feature_envy());
	}

	@Test
	void testGetStatusPMD() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals("s",a.getStatusPMD());
	}

	@Test
	void testGetStatusIPLASMA() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals("s",a.getStatusIPLASMA());
	}

	@Test
	void testGetMetricLongMethod() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(false,a.getMetricLongMethod());
	}

	@Test
	void testGetMetricFeatureEnvy() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals(false,a.getMetricFeatureEnvy());
	}

	@Test
	void testGetQualityMetricLongMethod() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals("s",a.getQualityMetricLongMethod());
	}

	@Test
	void testGetQualityMetricFeatureEnvy() {
		DataContainer a = new DataContainer
				(0, "s", "s", "s", 0, 0, 0,
				0.0, false, false,false, false,
				"s","s", false, false,
				"s", "s");
		assertEquals("s",a.getQualityMetricFeatureEnvy());
	}

	@Test
	void testSetIs_long_method() {
		fail("Not yet implemented");
	}

	@Test
	void testSetIs_feature_envy() {
		fail("Not yet implemented");
	}

	@Test
	void testSetStatusPMD() {
		fail("Not yet implemented");
	}

	@Test
	void testSetStatusIPLASMA() {
		fail("Not yet implemented");
	}

	@Test
	void testSetMetric_longmethod() {
		fail("Not yet implemented");
	}

	@Test
	void testSetMetric_featureenvy() {
		fail("Not yet implemented");
	}

	@Test
	void testSetQualityLM() {
		fail("Not yet implemented");
	}

	@Test
	void testSetQualityFE() {
		fail("Not yet implemented");
	}

}
