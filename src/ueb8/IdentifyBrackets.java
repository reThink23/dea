package ueb8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class IdentifyBrackets {
	private static String[] readLines(String filePath) throws IOException, FileNotFoundException {
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
		if (args.length < 1)
			throw new Exception("Argument is missing");

		String[] lines = readLines(args[0]);
		if (lines.length == 0)
			throw new Exception("File " + args[0] + " doesn't contain anything to read");

		Stack<Bracket> stack = new Stack<Bracket>();
		for (int i = 0; i < lines.length; i++) {
			for (int j = 0; j < lines[i].length(); j++) {
				char c = lines[i].charAt(j);
				if (c == '(' || c == '[' || c == '{') {
					stack.push(new Bracket(i + 1, j + 1, c));
				} else if (c == ')' || c == ']' || c == '}') {
					if (stack.isEmpty()) {
						System.out.println("Error: " + c + " at line " + (i + 1) + " column " + (j + 1));
						return;
					} else {
						char top = stack.top().getChar();
						if (c == ')' && top != '(' || c == ']' && top != '[' || c == '}' && top != '{') {
							System.out.println("Error: " + c + " at line " + (i + 1) + " column " + (j + 1));
							return;
						} else {
							Tuple<Bracket,Bracket> bracketPair = new Tuple<Bracket,Bracket>(stack.top(), new Bracket(i+1, j+1, top));
							stack.pop();
							System.out.println("Matching brackets: " + "'" + bracketPair.getFirst().getChar() + "' at line " + bracketPair.getFirst().getLine() + ", column " + bracketPair.getFirst().getColumn() + " and " + "'" + bracketPair.getSecond().getChar() + "' at line " + bracketPair.getSecond().getLine() + ", column " + bracketPair.getSecond().getColumn());
						}
					}
				}
			}
		}

	}
}
