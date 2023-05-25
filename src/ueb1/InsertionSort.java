package ueb1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InsertionSort {
	public static void sort(int[] A) {
		for (int j = 1; j < A.length; j++) {
			int key = A[j];
			
			int i = j - 1;
			while (i >= 0 && A[i] > key) {
				A[i+1] = A[i];
				i--;
			}
			A[i+1] = key;
		}
	}
	
	/* function to read lines from given file(path) and return lines as array of strings  */
	private static String[] readLines(String filePath) throws IOException, FileNotFoundException {
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
	
	public static void main(String[] args) throws Exception {
		if (args.length < 1) throw new Exception("Argument is missing");
		
		String[] lines = readLines(args[0]);
		if (lines.length == 0) throw new Exception("File "+ args[0] +" doesn't contain anything to read");
		
		/* convert each line (string) to number */
		int[] A = new int[lines.length];
		
		for (int i = 0; i < lines.length; i++) {
			A[i] = Integer.parseInt(lines[i]);
		}
		
		long start = System.currentTimeMillis();
		InsertionSort.sort(A);
		long end = System.currentTimeMillis();
		long diff = end - start;
		System.out.println("Time: " + diff);
		
		// for (int a: A) {			
		// 	System.out.println(a);
		// }
	}
}
