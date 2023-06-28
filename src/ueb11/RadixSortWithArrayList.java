package ueb11;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class RadixSortWithArrayList {
	private int[] A;
	private ArrayList<Integer> arr;
	@SuppressWarnings("unchecked")
	ArrayList<Integer>[] bucket = (ArrayList<Integer>[]) new ArrayList[2];
	
	{
		bucket[0] = new ArrayList<Integer>();
		bucket[1] = new ArrayList<Integer>();
	}
	
	public RadixSortWithArrayList(int[] A) {
		this.arr  = Arrays.stream( A ).boxed().collect( Collectors.toCollection(ArrayList::new) );
		this.A = A;
	}

	public int[] sort() {
		int k = 32;
		for (int r = 0; r < k; r++) {
			clearBuckets();
			while (arr.size() > 0) {
				int num = arr.get(0);
				arr.remove(0);
				int idx = getBitAt(num, r);
				bucket[idx].add(num);
			}
			arr.clear();
			arr.addAll(bucket[0]);
			arr.addAll(bucket[1]);
		} 
		return A;
	}


	private void clearBuckets() {
		for (int i = 0; i < bucket.length; i++) {
			bucket[i].clear();
		}
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
			System.out.println("Usage: java RadixSortWithArrayList <filename>"); 
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
