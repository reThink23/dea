package ueb12;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class WordCount {
	public static String[] readLines(String filePath) throws IOException, FileNotFoundException {
		ArrayList<String> stringList = new ArrayList<String>();
		
		BufferedReader bf = new BufferedReader(new FileReader(filePath, Charset.defaultCharset()));
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

		String[] lines = readLines("C:/Users/joela/Github/dea/testfiles/ueb12/De_Odyssee.txt");

		SearchTree<String, Integer> tree = new SearchTree<>();

		// tree.insert(5, "05");
		// tree.insert(1, "01");
		// tree.insert(11, "11");
		// tree.insert(7, "07");
		// tree.insert(3, "03");
		// tree.insert(4, "04");
		// tree.insert(9, "09");
		// tree.insert(2, "02");
		// tree.insert(6, "06");
		// tree.insert(8, "08");
		// tree.insert(10, "10");

		// tree.remove("07");


		for (String line : lines) {
			String[] words = line.split("[.,:;!?*()/\\-\t ]+");

			for (String word : words) {
				if (word.equals("")) continue;
				Integer val = tree.isMember(word);
				boolean isInserted = tree.insert(1, word);
				if (!isInserted) {
					tree.search(word).data = val+1;
				}
			}
		}

		System.out.println("isMember: " + tree.isMember("Odysseus"));
		System.out.println("Minimum: " + tree.minimum());
		System.out.println("Maximum: " + tree.maximum());
		System.out.println("Size: " + tree.size());
		System.out.println("Depth: " + tree.depth());
		System.out.println(tree.toString());

	}

}
