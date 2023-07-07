package ueb13;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import ueb12.SearchTree;

public class HashMap<Key, Value> {
	private Entry<Key, Value>[] hashTable;
	private int m = 997;

	public HashMap() {
		hashTable = new Entry[m];
	}

	private int hash(Key key) {
		return key.hashCode() % m;
	}

	// private Entry<Key, Value> search(int idx, Key key) {
	// 	Entry<Key, Value> tmp = hashTable[idx];
	// 	while (tmp != null) {
	// 		if (tmp.key.equals(key)) {
	// 			return tmp;
	// 		}
	// 		tmp = tmp.next;
	// 	}
	// 	return null;
	// }

	public void put(Key key, Value value) { 
		int idx = hash(key);
		Entry<Key, Value> e = new Entry<Key, Value>(key, value);

		if (hashTable[idx] == null) {
			hashTable[idx] = e;
		} else {
			Entry<Key, Value> tmp = hashTable[idx];
			while (tmp.next != null) {
				tmp = tmp.next;
			}
			tmp.next = e;
		}
	}
	
	public boolean contains(Key key) {
		int idx = hash(key);
		Entry<Key, Value> tmp = hashTable[idx];
		while (tmp != null) {
			if (tmp.key.equals(key)) return true;
			tmp = tmp.next;
		}
		return false;
	}
	
	public Value get(Key key) {
		int idx = hash(key);
		Entry<Key, Value> tmp = hashTable[idx];
		while (tmp != null) {
			if (tmp.key.equals(key)) return tmp.value;
			tmp = tmp.next;
		}
		return null;

	}

	public void delete(Key key) {
		int idx = hash(key);
		Entry<Key, Value> tmp = hashTable[idx];
		if (tmp == null) {
			return;
		} else if (tmp.key.equals(key)) {
			hashTable[idx] = tmp.next;
		} else {
			while (tmp.next != null) {
				if (tmp.next.key.equals(key)) {
					tmp.next = tmp.next.next;
					return;
				}
				tmp = tmp.next;
			}
		}
	}

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
		// String[] lines = readLines("C:/Github/dea/testfiles/ueb12/De_Odyssee.txt");
		
		HashMap<String, Integer> occurences = new HashMap<>();


		for (String line : lines) {
			String[] words = line.split("[.,:;!?*()/\\-\t ]+");

			for (String word : words) {
				if (word.equals("")) continue;
				Integer val = occurences.get(word);
				if (val != null) {
					// occurences.get(word).value += 1;
				} else {
					occurences.put(word, 1);
				}
			}
		}
		

	}
}

