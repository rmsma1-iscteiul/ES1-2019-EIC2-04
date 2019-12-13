import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import project.backend.containers.MetricsRule;
import project.backend.IO_Manager;

class IO_ManagerTest {
	
	private static final String fileLocation = System.getProperty("user.dir")
			+"/src/main/java/project/saves/saved_rules.txt";
	
	@Test
	void testWriteRuleToFile() {
		Path file = Paths.get(fileLocation);
		try {
			MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);		
			IO_Manager.writeRuleToFile(a);
			List<String> lines = Files.readAllLines(file);
			boolean b = false;
			for (String string : lines) {
				if(string.equals(a.fileToString())) {
					b = true;
				}
			}
			assertEquals(true, b);
		} catch (IOException e) {
			fail("Threw IOException");
		}
	}

	@Test
	void testRemoveRuleFromFile() {
		Path file = Paths.get(fileLocation);
		try {
			MetricsRule a = new MetricsRule("name", 1, true, true, 1, true, 1, true, true, 1, true);		
			IO_Manager.writeRuleToFile(a);
			IO_Manager.removeRuleFromFile(a);
			List<String> lines = Files.readAllLines(file);
			boolean b = true;
			for (String string : lines) {
				if(string.equals(a.fileToString())) {
					b = false;
				}
			}
			assertEquals(false, b);
		} catch (IOException e) {
			fail("Threw IOException");
		}
	}

	@Test
	void testReadListFromFile() {
		try {
			assertNotNull(IO_Manager.readListFromFile());
		} catch (IOException e) {
			fail("Threw IOException");
		}
	}
	

}
