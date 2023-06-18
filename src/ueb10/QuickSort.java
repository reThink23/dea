package ueb10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class QuickSort {
	enum Strategy { RANDOM, MEDIAN3, FIRST }

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
		int q = partition(A,l,r, getPivotIndex(A, l, r, strat));
		qsort(A, l, q-1, strat);
		qsort(A, q+1, r, strat);
	}

	private static int partition(int[] A, int l, int r, int p) {
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

	private static int getPivotIndex(int[] A, int l, int r, Strategy strat) {
		int p = random(l, r, null);
		switch (strat) {
			case FIRST:
				p = l;
				break;
			case RANDOM:
				p = random(l, r, null);
				break;
			case MEDIAN3:
				p = (random(l, r, p) + random(l, r, p) + random(l, r, p)) / 3;
				break;
			default:
				p = random(l, r, null);
				break;
		}
		return p;
	}

	private static void swap(int[] A, int a, int b) {
		int tmp = A[a];
		A[a] = A[b];
		A[b] = tmp;
	}

	private static int random(int min, int max, Integer seed) {
		if (min > max) throw new IllegalArgumentException("min must be smaller than max");
		if (min == max) return min;
		if (seed != null)
			return new Random(seed).nextInt(max-min+1)+min;
		else
			return new Random().nextInt(max-min+1)+min;
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
		if (!strat.equals("random") && !strat.equals("median3") && !strat.equals("first")) {
			System.out.println("Strategy must be one of: random, median3, first");
			System.exit(1);
		}

		String[] lines = readLines(args[0]);
		if (lines.length == 0)
			throw new Exception("File " + args[0] + " doesn't contain anything to read");

		/* convert each line (string) to number */
		int[] A = new int[lines.length];

		for (int i = 0; i < lines.length; i++) {
			A[i] = Integer.parseInt(lines[i]);
		}

		long start = System.currentTimeMillis();
		sort(A, strat);
		long end = System.currentTimeMillis();
		long diff = end - start;
		System.out.println("Time: " + diff);


		// for (int a : A) {
		// 	// System.out.println(a);
		// 	System.out.print(a+" ");
		// }
	}

}
