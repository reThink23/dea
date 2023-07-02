package ueb12;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WordCount {
	public static String[] readLines(String filePath) throws IOException, FileNotFoundException {
		ArrayList<String> stringList = new ArrayList<String>();
		
		BufferedReader bf = new BufferedReader(new FileReader(filePath));
		String line = bf.readLine();

		while (line != null) {
			stringList.add(line);
			line = bf.readLine();
		}

		bf.close();

		String[] lines = stringList.toArray(new String[0]);
		return lines;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String[] lines = readLines("/testfiles/ueb12/De_Odyssee.txt");

		SearchTree<String, Integer> tree = new SearchTree<>();

		for (String line : lines) {
			String[] words = line.split("[.,:;!? ]+");

			for (String word : words) {
				Integer val = tree.isMember(line);
				tree.insert((val != null) ? val : 1, word);
			}
		}
	}

}
