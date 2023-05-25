package ueb4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MergeSort {
	public static int[] sort(int[] A) {
		mergeSort(A, 0, A.length-1);
		return A;
	}

	private static void mergeSort(int[] A, int p, int r) {
		if (p < r) {
			int q = (p + r)/2;
			mergeSort(A, p, q);
			mergeSort(A, q+1, r);
			merge(A, p, q, r);
		}
	}

	public static void merge(int[] A, int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;

		int[] L = new int[n1+1];
		int[] R = new int[n2+1];

		L[n1] = Integer.MAX_VALUE;
		for (int i = 0; i < n1; i++) { L[i] = A[p + i]; }
		
		R[n2] = Integer.MAX_VALUE;
		for (int j = 0; j < n2; j++) { R[j] = A[q + 1 + j]; }

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
/* 
	static void merge(int[] A, int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;
		
		int[] L = new int[n1+1];
		for (int i = 1; i <= n1; i++) { L[i] = A[p+i]; }
		L[n1] = Integer.MAX_VALUE;
		
		int[] R = new int[n2+1];
		for (int j = 0; j < n2; j++) { R[j] = A[q+j]; }
		R[n2] = Integer.MAX_VALUE;

		int i = 0, j = 0;
		for (int k = p; k <= r; k++) {
			if (L[i] <= R[j]) {
				A[k] = L[i];
				i++;
			} else {
				A[k] = R[j];
				j++;
			}
			
		}
	} */

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

		long start = System.currentTimeMillis();
		sort(A);
		long end = System.currentTimeMillis();
		long diff = end - start;
		System.out.println("Time: " + diff);


		// for (int a : A) {
		// 	System.out.println(a);
		// }
	}
}
