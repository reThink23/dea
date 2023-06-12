package ueb9;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HeapSort {

	private static boolean hasLeftChild( int i, int len ) { return (i <= len/2-1); }
	private static boolean hasRightChild( int i, int len ) {return (i <= (len+1)/2-2);}
	private static int left( int i, int len ) { if (hasLeftChild(i, len)) {return 2*i+1;} else throw new IndexOutOfBoundsException("i is out of bounds"); }
	private static int right( int i, int len ) { if (hasRightChild(i, len)) {return 2*i+2;} else throw new IndexOutOfBoundsException("i is out of bounds"); }
	private static int parent( int i ) { return i > 0 ? (i-1)/2 : -1; }
	
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
		int len = A.length;
		// if (!hasLeftChild(i, len) && !hasRightChild(i, len)) return;
		if (!hasLeftChild(i, len)) return;
		int child;
		if (hasLeftChild(i, len) && !hasRightChild(i, len)) { child = left(i,len); }
		if (hasRightChild(i, len) && A[left(i, len)] > A[right(i, len)]) { 
			child = left(i, len);
		} else {
			child = right(i, len); 
		}
		if (child != -1 && A[child] > A[i]) {
			int tmp = A[child];
			A[child] = A[i];
			A[i] = tmp;
			heapify(A, k, child);
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

		long start = System.currentTimeMillis();
		sort(A);
		long end = System.currentTimeMillis();
		long diff = end - start;
		System.out.println("Time: " + diff);
		// System.out.println("[" + A[0]+ ", ..., "+ A[A.length/2-1] +", ..., " + A[A.length-1]+"]");


		// for (int a : A) {
		// 	System.out.println(a);
		// }
	}
	
}
