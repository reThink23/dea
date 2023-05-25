package ueb7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListTest {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> list = null;
		
		System.out.println("Single linked list (s) or double linked list (d)?");
		String line = bf.readLine();
		
		char listType = line.charAt(0);
		if (listType != 's' && listType != 'd') {
			System.out.println("Unknown list type");
			return;
		} else if (listType == 's') {
			list = new SList<>();
		}
		// } else {
		// 	list = new DList<>();
		// }
		
		System.out.println("Enter command:");

		while (line != null) {
			line = bf.readLine();
			String[] split = line.split(" ");
			char firstChar = split[0].charAt(0);
			switch (firstChar) {
				case 'a':
					if (split.length < 1) {
						System.out.println("Missing argument");
						System.out.println("Usage: a <value>");
						break;
					} else {
						int value = Integer.parseInt(split[1]);
						list.append(value);
						System.out.println("Added " + value);
					}
					break;
				case 'i':
					if (split.length < 2) {
						System.out.println("Missing argument");
						System.out.println("Usage: i <value> <pos>");
						break;
					} else {
						int value = Integer.parseInt(split[1]);
						int pos = Integer.parseInt(split[2]);
						list.insert(value, pos);
						System.out.println("Inserted " + value + " at position " + pos);
					}
					break;
				case 'd':
					// delete
					if (split.length < 1) {
						System.out.println("Missing argument");
						System.out.println("Usage: d <pos>");
						break;
					} else {
						int pos = Integer.parseInt(split[1]);
						list.delete(pos);
						System.out.println("Deleted element at position " + pos);
					}
					break;
				case 's': System.out.println(list.size()); break;
				case 'c': list.clear(); break;
				case 'r': list.reverse(); break;
				case 'g':
					// get
					if (split.length < 1) {
						System.out.println("Missing argument");
						System.out.println("Usage: g <pos>");
						break;
					} else {
						try {
							int pos = Integer.parseInt(split[1]);
							System.out.println(list.get(pos));
						} catch (IndexOutOfBoundsException e) {
							System.out.println("<pos> out of bounds");
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
					System.out.println("Unknown command");
			}
		}
		bf.close();

	}
}
