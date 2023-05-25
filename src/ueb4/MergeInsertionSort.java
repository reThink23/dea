package ueb4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MergeInsertionSort {
	public static int[] sort(int[] A, int k) {
		mergeInsertionSort(A, 0, A.length-1, k);
		return A;
	}

	private static void mergeInsertionSort(int[] A, int p, int r, int k) {
		if ((r-p) >= k) {
			int q = (p + r) / 2;
			mergeInsertionSort(A, p, q, k);
			mergeInsertionSort(A, q + 1, r, k);
			merge(A, p, q, r);
		} else {
			insertionSort(A, p, r);
		}
	}

	public static void merge(int[] A, int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;

		int[] L = new int[n1 + 1];
		int[] R = new int[n2 + 1];

		L[n1] = Integer.MAX_VALUE;
		for (int i = 0; i < n1; i++) {
			L[i] = A[p + i];
		}

		R[n2] = Integer.MAX_VALUE;
		for (int j = 0; j < n2; j++) {
			R[j] = A[q + 1 + j];
		}

		int i = 0;
		int j = 0;

		for (int k = p; k <= r; k++) {
			if (j >= n2 || (i < n1 && L[i] <= R[j])) {
				A[k] = L[i];
				i++;
			} else {
				A[k] = R[j];
				j++;
			}
		}
	}

	private static void insertionSort(int[] A, int p, int r) {
		for (int j = p+1; j <= r; j++) {
			
			int key = A[j];

			int i = j - 1;
			while (i >= p && A[i] > key) {
				A[i + 1] = A[i];
				i--;
			}
			A[i + 1] = key;
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
		if (args.length < 2) throw new Exception("Missing Argument(s)");

		
		String[] lines = readLines(args[0]);
		if (lines.length == 0)
		throw new Exception("File " + args[0] + " doesn't contain anything to read");
		
		/* convert each line (string) to number */
		int[] A = new int[lines.length];
		
		for (int i = 0; i < lines.length; i++) {
			A[i] = Integer.parseInt(lines[i]);
		}
		
		boolean measure = true;
		int kStart = 1;
		int kEnd = 1000;
		int loops = 3;
		if (!measure) {
			int k = Integer.parseInt(args[1]);
			long start = System.currentTimeMillis();
			sort(A, k);
			long end = System.currentTimeMillis();
			long diff = end - start;
			System.out.println("Time: " + diff);
			
			for (int a : A) {
				System.out.println(a);
			}
		} else {
			int [] avgDiffs = new int[kEnd-kStart+1+1];
			for (int k = kStart; k <= kEnd; k++) {
				long avgDiff = 0;
				for (int i = 0; i < loops; i++) {
					long start = System.currentTimeMillis();
					sort(A, k);
					long end = System.currentTimeMillis();
					long diff = end - start;
					avgDiff += diff;
				}
				avgDiff /= loops;
				System.out.println("Time (for k="+k+"): " + avgDiff);
				avgDiffs[k] = (int) avgDiff;
			}
			
			int min = avgDiffs[0];
			int minIdx = 0;
			for (int i = 1; i < avgDiffs.length; i++) {
				if (i > 1 && avgDiffs[i] > avgDiffs[i-1]) {
					min = avgDiffs[i];
					minIdx = i;
				}
			}
			System.out.println("Min: " + min + "\nMinIdx: " + minIdx);
		}

	}
}
