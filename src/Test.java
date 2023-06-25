import java.io.File;

import ueb9.HeapSort;
// import ueb10.QuickSort;
import ueb11.RadixSort;

public class Test {

	public static String padRight(String s, int n) {
     return String.format("%-" + n + "s", s);  
	}

	public static String padLeft(String s, int n) {
		return String.format("%" + n + "s", s);  
	}


	public static void main(String[] args) {

		// get all files in directory
		File folder = new File("C:/Github/dea/testfiles/desc/");
		File[] listOfFiles = folder.listFiles();
		// read each file in directory and sort it
		for (File file : listOfFiles) {
			if (!file.isFile()) continue;
			
			String fileName = file.getName();
			if (!fileName.endsWith(".txt")) continue;

			try {
				String[] lines = RadixSort.readLines(file.getAbsolutePath());
				int[] arr = new int[lines.length];
				for (int i = 0; i < lines.length; i++) {
					arr[i] = Integer.parseInt(lines[i]);
				}

				// run each test instance three times and take the average
				int runs = 3;
				int sum = 0;
				for (int i = 0; i < runs; i++) {
					long start = System.currentTimeMillis();
					HeapSort.sort(arr);
					long end = System.currentTimeMillis();
					long time = end - start;
					sum += time;
					// System.out.println("Time: " + time + "ms");
				}
				System.out.println(padLeft(fileName.split("\\.")[0] + ": ", 12) + (sum / runs));

			} catch (Exception e) {
				System.out.println("Error while reading file: " + fileName);
				e.printStackTrace();
			}
		}
		

	}
}
