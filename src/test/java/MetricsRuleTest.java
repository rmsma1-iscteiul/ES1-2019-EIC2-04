import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import project.backend.containers.MetricsRule;
class MetricsRuleTest {


	
	


	
	@Test
	void testMetricsRuleConstructor1() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		assertNotNull(a);
	}

	@Test
	void testMetricsRuleConstuructor2() {
		MetricsRule b = new MetricsRule("name/1/true/true/1/true/1/true/true/1/true");
		assertNotNull(b);
	}

	@Test
	void testGetLocValue() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		assertEquals(1, a.getLocValue());
	}

	@Test
	void testSetLocValue() {		
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		a.setLocValue(2);
		assertEquals(2, a.getLocValue());
	}

	@Test
	void testGetLocComparison() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		assertEquals(true, a.getLocComparison());
	}

	@Test
	void testSetLocComparison() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		a.setLocComparison(false);
		assertEquals(false, a.getLocComparison());
	}

	@Test
	void testGetLocCycloAndOr() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		assertEquals(true, a.getLocCycloAndOr());
	}

	@Test
	void testSetLocCycloAndOr() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		a.setLocCycloAndOr(false);
		assertEquals(false, a.getLocCycloAndOr());
	}

	@Test
	void testGetCycloValue() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		assertEquals(1, a.getCycloValue());
	}

	@Test
	void testSetCycloValue() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		a.setCycloValue(2);
		assertEquals(2, a.getCycloValue());
	}

	@Test
	void testGetCycloComparison() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		assertEquals(true, a.getCycloComparison());
	}

	@Test
	void testSetCycloComparison() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		a.setCycloComparison(false);
		assertEquals(false, a.getCycloComparison());
	}

	@Test
	void testGetAftdValue() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		assertEquals(1, a.getAftdValue());
	}

	@Test
	void testSetAftdValue() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		a.setAftdValue(2);
		assertEquals(2, a.getAftdValue());
	}

	@Test
	void testGetAftdComparison() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		assertEquals(true, a.getAftdComparison());
	}

	@Test
	void testSetAftdComparison() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		a.setAftdComparison(false);
		assertEquals(false, a.getAftdComparison());
	}

	@Test
	void testGetAftdLaaAndOr() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		assertEquals(true, a.getAftdLaaAndOr());
	}

	@Test
	void testSetAftdLaaAndOr() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		a.setAftdLaaAndOr(false);
		assertEquals(false, a.getAftdLaaAndOr());
	}

	@Test
	void testGetLaaValue() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		assertEquals(1, a.getLaaValue());
	}

	@Test
	void testSetLaaValue() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		a.setLaaValue(2);
		assertEquals(2, a.getLaaValue());
	}

	@Test
	void testGetLaaComparison() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		assertEquals(true, a.getLaaComparison());
	}

	@Test
	void testSetLaaComparison() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		a.setLaaComparison(false);
		assertEquals(false, a.getLaaComparison());
	}

	@Test
	void testGetMetricName() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		assertEquals("name", a.getMetricName());
	}

	@Test
	void testSetMetricName() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		a.setMetricName("mane");
		assertEquals("mane", a.getMetricName());
	}

	@Test
	void testToString() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		assertEquals("name",a.toString());
	}

	@Test
	void testFileToString() {
		MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);
		assertEquals("name/1/true/true/1/true/1/true/true/1/true",a.fileToString());
	}

}
