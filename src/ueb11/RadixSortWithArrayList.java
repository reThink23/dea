package ueb11;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RadixSortWithArrayList {
	private int[] A;
	@SuppressWarnings("unchecked")
	ArrayList<Integer>[] bucket = (ArrayList<Integer>[]) new ArrayList[2];
	
	public RadixSortWithArrayList(int[] A) {
		this.A = A;
	}

	public int[] sort() {
		// int[] queue = new int[A.length];
		// System.arraycopy(A, 0, queue, 0, A.length);
		// int k = maxLength(A);
		int k = 32;
		// for (int r = k; r >= 1; r--) {
		for (int r = 0; r < k; r++) {
			clearBuckets();
			while (A.length > 0) {
				int num = pop();
				int idx = getBitAt(num, r);
				bucket[idx].add(num);
			}	
			// A = flatten(bucket);
		} 
		return A;
		// int lenMax = maxLength(A);

		// int[][] buckets = new int[lenMax][10];
		// for (int i = 0; i < A.length; i++) {
		// 	int len = getIntLength(A[i]);
		// 	buckets[len] = add(buckets[len], A[i]);
		// }
		// A = flatten(buckets);


		// int[][] buckets = new int[2][0];
		// for (int i = 0; i < buckets.length; i++) {
		// 	for (int j = 0; j < buckets[i].length; j++) {
		// 		buckets[i][j] = j;
		// 	}
		// }
		// for (int i = 0; i < A.length; i++) {
		// 	int len = getIntLength(A[i]);
		// 	buckets[A[i]] = add(buckets[A[i]], i);
		// }
		// A = flatten(buckets);
	}

	
	private static int getIntLength(int a) {
		return (a == 0) ? 1 : (int)(Math.log10(a)+1);
	}

	private static int maxLength(int[] A) {
		int max = getIntLength(A[0]);
		for (int i = 1; i < A.length; i++) {
			int crtLen = getIntLength(A[i]);
			if (crtLen > max) {
				max = crtLen;
			}
		}
		return max;
	}



	private int pop() {
		if (A.length == 0) return -1;
		int[] arrB = new int[A.length-1];
		int first = A[0];
		System.arraycopy(A, 1, arrB, 0, A.length-1);
		A = arrB;
		return first;
	}

	private void clearBuckets() {
		for (int i = 0; i < bucket.length; i++) {
			bucket[i].clear();
		}
	}

	private static int[] concat(int[] arrA, int[] arrB) {
		int[] arrRes = new int[arrA.length+arrB.length];
		System.arraycopy(arrA, 0, arrRes, 0, arrA.length);
		System.arraycopy(arrB, 0, arrRes, arrA.length, arrB.length);
		return arrRes;
	}

	private static int[] flatten(int[][] arr) {
		int[] arrRes = new int[arr[0].length];
		System.arraycopy(arr[0], 0, arrRes, 0, arr[0].length);
		for (int i = 1; i < arr.length; i++) {
			arrRes = concat(arrRes, arr[i]);
		}
		return arrRes;
	}

	private static int getBitAt(int a, int i) {
		return (a >> i) & 1;
		// return (a << i) & 1;
	}
	
	public static void sort(int[] A) {
		RadixSortWithArrayList rSort = new RadixSortWithArrayList(A);
		int[] arr = rSort.sort();
		System.arraycopy(arr, 0, A, 0, arr.length);
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
		if (args.length < 1) { 
			System.out.println("Usage: java RadixSort <filename>"); 
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
		sort(A); //Fix: System.arraycopy in sort() | Problem: does sort, but the A in this line is not changed, because of reassigning A in void sort()
		long end = System.currentTimeMillis();
		long diff = end - start;
		System.out.println("Time: " + diff);


		// for (int a : A) {
		// 	System.out.println(a);
		// 	// System.out.print(a+" ");
		// }
	}
}
