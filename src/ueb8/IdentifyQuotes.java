package ueb8;

import java.io.IOException;

public class IdentifyQuotes extends IdentifyBrackets {

	public static void main(String[] args) throws IllegalArgumentException, IOException {
		if (args.length < 1)
			throw new IllegalArgumentException("Argument is missing");

		String[] lines = readLines(args[0]);
		if (lines.length == 0)
			throw new IllegalArgumentException("File " + args[0] + " doesn't contain anything to read");

		Stack<PosChar> stack = new Stack<PosChar>();
		for (int i = 0; i < lines.length; i++) {
			for (int j = 0; j < lines[i].length(); j++) {
				char c = lines[i].charAt(j);
				if (c == '\'' || c == '"') {
					stack.push(new PosChar(i + 1, j + 1, c));
				} else if (c == '\'' || c == '"') {
					if (stack.isEmpty()) {
						System.out.println("Error: " + c + " at line " + (i + 1) + " column " + (j + 1));
						System.out.println("Error: Expected " + c + " at line " + (i + 1) + " column " + (j + 1));
						return;
					} else {
						char top = stack.top().getChar();
						if (c == '\'' && top != '\'' || c == '"' && top != '"') {
							System.out.println(
								"Error at line " + (i + 1) + " column " + (j + 1) + ": Reading `" + 
								c + "` but expecting `" + stack.top().getMatchingCharacter()  + "` because of " + stack.top()
							);
							return;
						} else {
							Tuple<PosChar,PosChar> bracketPair = new Tuple<PosChar,PosChar>(stack.top(), new PosChar(i+1, j+1, c));
							stack.pop();
							System.out.println("Matching brackets: " + bracketPair.getFirst() + " and " + bracketPair.getSecond());
						}
					}
				}
			}
		}

	}	
}
