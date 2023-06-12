package ueb9;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HeapSort {

	public static void sort(int[] A) {
		createHeap(A);

		for (int last = A.length-1; last > 0; last--) {
			int tmp = A[last];
			A[last] = A[0];
			A[0] = tmp;
			heapify(A, last, 0);
		}
	}

	private static void heapify(int[] A, int k, int i) {
		if (i < 0 || i >= k) return;
		
		int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < k && A[l] > A[largest])
            largest = l;

        if (r < k && A[r] > A[largest])
            largest = r;

        if (largest != i) {
            int swap = A[i];
            A[i] = A[largest];
            A[largest] = swap;

            heapify(A, k, largest);
        }
	}

	private static void createHeap(int[] A) {
		int len = A.length;
		for (int i = len/2-1; i >= 0; i--) {
			heapify(A, len, i);
		}
	}

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

	public static void main(String[] args) throws Exception {
		if (args.length < 1) throw new Exception("Argument is missing");


		String[] lines = readLines(args[0]);
		if (lines.length == 0)
			throw new Exception("File " + args[0] + " doesn't contain anything to read");

		/* convert each line (string) to number */
		int[] A = new int[lines.length];

		for (int i = 0; i < lines.length; i++) {
			A[i] = Integer.parseInt(lines[i]);
		}

		// long start = System.currentTimeMillis();
		sort(A);
		// long end = System.currentTimeMillis();
		// long diff = end - start;
		// System.out.println("Time: " + diff);


		for (int a : A) {
			System.out.println(a);
		}
	}
	
}
