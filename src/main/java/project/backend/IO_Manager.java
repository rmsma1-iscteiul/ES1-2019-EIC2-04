package project.backend;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import project.backend.containers.MetricsRule;;
public class IO_Manager {
	//the locations of the file in the project
	private static final String fileLocation = System.getProperty("user.dir")
			+"/src/main/java/project/saves/saved_rules.txt";
	
	/**
	 * Saves a MetricRule to the save file
	 * @param rule
	 * Rule to be saved in file
	 * @throws IOException
	 * Throws this exception case the are errors writing the save file(EX: file is missing);
	 */
	public static void writeRuleToFile(MetricsRule rule) throws IOException {
		Path file = Paths.get(fileLocation);
		List<String> lines = Files.readAllLines(file);
		lines.add(rule.toString());
		Files.write(file, lines);
	}
	
	/**
	 * Removes the given metric rule from the save file
	 * @param rule
	 * The Rule to remove from the file
	 * @throws IOException
	 * Throws this exception case the are errors rewriting the save file(EX: file is missing);
	 */
	public static void removeRuleFromFile(MetricsRule rule) throws IOException {
		Path file = Paths.get(fileLocation);
		List<String> lines = Files.readAllLines(file);
		String ruleToDel = rule.toString();
		String temp = null;
		for (String string : lines) {
			if(string.contentEquals(ruleToDel)) {
				temp = string;
			}
		}
		lines.remove(temp);
		Files.write(file, lines);
	}
	
	/**
	 * Reads all the Metric rules in the save file
	 * @return
	 * Returns The list Of metric rules present in the save file
	 * @throws IOException
	 * Throws this exception case the are errors reading the save file(EX: file is missing);
	 */
	public static List<MetricsRule> readListFromFile() throws IOException{
		List<MetricsRule> savedRules = new ArrayList<MetricsRule>();
		Path file = Paths.get(fileLocation);
		List<String> lines = Files.readAllLines(file);
		for (String string : lines) {
			savedRules.add(new MetricsRule(string));
		}
		return savedRules;
	}
}
