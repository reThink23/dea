package ueb10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class QuickSort {
	enum Strategy  { RANDOM, MEDIAN3, FIRST
	}

	public static void sort(int[] A, String strategy) {
		Strategy mode = Strategy.RANDOM;
		switch (strategy) {
			case "random":
				mode = Strategy.RANDOM;
				break;
			case "median3":
				mode = Strategy.MEDIAN3;
				break;
			case "first":
				mode = Strategy.FIRST;
				break;
		}
		qsort(A, 0, A.length-1, mode);
	}

	private static void qsort(int[] A, int l, int r, Strategy strat) {
		if (l >= r) return;
		int q = partition(A,l,r, strat);
		qsort(A, l, q-1, strat);
		qsort(A, q+1, r, strat);
	}

	private static int partition(int[] A, int l, int r, Strategy strat) {
		int p = new Random(r).nextInt(r-l+1)+l;
		switch (strat) {
			case FIRST:
				p = l;
				break;
			case RANDOM:
				p = new Random().nextInt(r-l+1)+l;
				break;
			case MEDIAN3:
				p = (new Random(p).nextInt(r-l+1)+l) + (new Random(p).nextInt(r-l+1)+l) + (new Random(p).nextInt(r-l+1)+l) / 3;
				break;
		}
		int x = A[p];
		swap(A, p, r);
		int i = l-1;
		for (int j = l; j < r; j++) {
			if (A[j] <= x) {
				i++;
				swap(A, i, j);
			}
		}
		swap(A, i+1, r);
		return i+1;
	}

	private static void swap(int[] A, int a, int b) {
		int tmp = a;
		A[a] = A[b];
		A[b] = tmp;
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
		if (args.length < 2) { 
			System.out.println("Usage: java QuickSort <filename> <strategy>"); 
			System.exit(1);
		}
		String strat = args[1];


		String[] lines = readLines(args[0]);
		if (lines.length == 0)
			throw new Exception("File " + args[0] + " doesn't contain anything to read");

		/* convert each line (string) to number */
		int[] A = new int[lines.length];

		for (int i = 0; i < lines.length; i++) {
			A[i] = Integer.parseInt(lines[i]);
		}

		// long start = System.currentTimeMillis();
		sort(A, strat);
		// long end = System.currentTimeMillis();
		// long diff = end - start;
		// System.out.println("Time: " + diff);


		for (int a : A) {
			System.out.println(a);
		}
	}

}
