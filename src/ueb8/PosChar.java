package ueb8;

public class PosChar implements Comparable<PosChar> {
	
	private int line;
	private int column;
	private char character;


	public PosChar(int line, int column, char character) {
		this.line = line;
		this.column = column;
		this.character = character;
	}

	public int getLine() { return line; }
	public int getColumn() { return column; }
	public char getChar() { return character; }

	public char getMatchingCharacter() {
		switch (character) {
			case '(': return ')';
			case '[': return ']';
			case '{': return '}';
			case ')': return '(';
			case ']': return '[';
			case '}': return '{';
			default: return character;
			// default: throw new IllegalArgumentException("Can't get matching character for " + character);
		}
	}

	public int compareTo(PosChar other) {
		if (this.line < other.line) return -1;
		if (this.line > other.line) return 1;
		if (this.column < other.column) return -1;
		if (this.column > other.column) return 1;
		return 0;
	}

	public String toString() {
		return "'" + character + "' at line " + line + ", column " + column;
	}
}
