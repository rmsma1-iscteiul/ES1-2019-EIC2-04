package project.backend;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import project.backend.containers.MetricsRule;;
public class IO_Manager {
	private static final String fileLocation = System.getProperty("user.dir")
			+"/src/main/java/project/saves/saved_rules.txt";
	
	
	public static void writeRuleToFile(MetricsRule rule) throws IOException {
		Path file = Paths.get(fileLocation);
		List<String> lines = Files.readAllLines(file);
		lines.add(rule.toString());
		Files.write(file, lines);
	}
	
	
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
