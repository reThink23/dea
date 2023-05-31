package ueb8;

public class Bracket implements Comparable<Bracket> {
	
	private int line;
	private int column;
	private Type type;

	public static enum Type { ROUND_OPEN, SQUARE_OPEN, CURLY_OPEN, ROUND_CLOSE, SQUARE_CLOSE, CURLY_CLOSE}

	public Bracket(int line, int column, Type type) {
		this.line = line;
		this.column = column;
		this.type = type;
	}

	public Bracket(int line, int column, char bracket) {
		this.line = line;
		this.column = column;
		switch (bracket) {
			case '(': this.type = Type.ROUND_OPEN; break;
			case '[': this.type = Type.SQUARE_OPEN; break;
			case '{': this.type = Type.CURLY_OPEN; break;
			case ')': this.type = Type.ROUND_CLOSE; break;
			case ']': this.type = Type.SQUARE_CLOSE; break;
			case '}': this.type = Type.CURLY_CLOSE; break;
			default: throw new IllegalArgumentException("Unknown bracket type: " + type);
		}
	}

	public int getLine() { return line; }
	public int getColumn() { return column; }
	public Type getType() { return type; }
	public char getChar() {
		switch (type) {
			case ROUND_OPEN: return '(';
			case SQUARE_OPEN: return '[';
			case CURLY_OPEN: return '{';
			case ROUND_CLOSE: return ')';
			case SQUARE_CLOSE: return ']';
			case CURLY_CLOSE: return '}';
			default: throw new IllegalArgumentException("Unknown bracket type: " + type);
		}
	}

	public int compareTo(Bracket other) {
		if (this.line < other.line) return -1;
		if (this.line > other.line) return 1;
		if (this.column < other.column) return -1;
		if (this.column > other.column) return 1;
		return 0;
	}

	public String toString() {
		return "Bracket(" + line + ", " + column + ", " + type + ")";
	}
}
