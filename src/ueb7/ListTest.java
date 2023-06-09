package ueb7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class ListTest {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		IList<Integer> list = null;
		
		System.out.println("Single linked list (s) or double linked list (d)?");
		String line = bf.readLine();
		
		char listType = line.charAt(0);
		if (listType != 's' && listType != 'd') {
			System.out.println("Unknown list type");
			// return;
		} else if (listType == 's') {
			list = new SList<>();
		} else {
			list = new DList<>();
		}

		for (int i = 0; i < 6; i++) {
			int r = new Random().nextInt(100);
			list.append(r);
		}
		
		System.out.println("Enter command:");

		while (line != null) {
			line = bf.readLine();
			String[] split = line.trim().split(" ");
			char firstChar = split[0].charAt(0);
			switch (firstChar) {
				case 'a':
					if (split.length < 2) {
						System.out.println("Missing argument");
						System.out.println("Usage: a <value> [<value> ...]");
						break;
					} else {
						boolean isAdded = false;
						for (int i = 1; i < split.length; i++) {
							try {
								int value = Integer.parseInt(split[i]);
								list.append(value);
								isAdded = true;
							} catch (NumberFormatException e) {
								System.out.println("<value"+ i +"> is not a number");
							}
						}
						if (isAdded) System.out.println("-> " + list);
					}
					break;
				case 'i':
					if (split.length < 3) {
						System.out.println("Missing argument");
						System.out.println("Usage: i <value> <pos>");
						break;
					} else {
						try {
							int value = Integer.parseInt(split[1]);
							int pos = Integer.parseInt(split[2]);
							list.insert(value, pos);
							System.out.println("-> " + list);
						} catch (IndexOutOfBoundsException e) {
							System.out.println("<pos> out of bounds");
						} catch (NumberFormatException e) {
							System.out.println("<value> or <pos> is not a number");
						}
					}
					break;
				case 'd':
					// delete
					if (split.length < 2) {
						System.out.println("Missing argument");
						System.out.println("Usage: d <pos>");
						break;
					} else {
						try {
							int pos = Integer.parseInt(split[1]);
							list.delete(pos);
							// System.out.println("Deleted element at position " + pos);
							System.out.println("-> " + list);
						} catch (IndexOutOfBoundsException e) {
							System.out.println("<pos> out of bounds");
						} catch (NumberFormatException e) {
							System.out.println("<pos> is not a number");
						}
					}
					break;
				case 's': System.out.println(list.size()); break;
				case 'c': list.clear(); System.out.println("-> " + list); break;
				case 'r': list.reverse(); System.out.println("-> " + list); break;
				case 'g':
					// get
					if (split.length < 2) {
						System.out.println("Missing argument");
						System.out.println("Usage: g <pos>");
						break;
					} else {
						try {
							int pos = Integer.parseInt(split[1]);
							System.out.println(list.get(pos));
						} catch (IndexOutOfBoundsException e) {
							System.out.println("<pos> out of bounds");
						} catch (NumberFormatException e) {
							System.out.println("<pos> is not a number");
						}
					}
					break;
				case 'p':
					// print
					System.out.println(list.toString());
					break;
				case 'e':
					// exit
					return;
				default:
					System.out.println("Unknown command " + split[0]);
			}
		}
		bf.close();

	}
}
