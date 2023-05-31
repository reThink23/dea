package ueb8;

public class Bracket implements Comparable<Bracket> {
	
	private int line;
	private int column;
	private char bracket;
	private Type type;

	public static enum Type { ROUND_OPEN, SQUARE_OPEN, CURLY_OPEN, ROUND_CLOSE, SQUARE_CLOSE, CURLY_CLOSE}

	public Bracket(int line, int column, Type type) {
		this.line = line;
		this.column = column;
		this.type = type;
		switch (type) {
			case ROUND_OPEN: this.bracket = '('; break;
			case SQUARE_OPEN: this.bracket = '['; break;
			case CURLY_OPEN: this.bracket = '{'; break;
			case ROUND_CLOSE: this.bracket = ')'; break;
			case SQUARE_CLOSE: this.bracket = ']'; break;
			case CURLY_CLOSE: this.bracket = '}'; break;
			default: throw new IllegalArgumentException("Unknown bracket type: " + type);
		}
	}

	public Bracket(int line, int column, char bracket) {
		this.line = line;
		this.column = column;
		this.bracket = bracket;
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
	public char getBracket() { return bracket; }
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

	public char getMatchingBracket() {
		switch (bracket) {
			case '(': return ')';
			case '[': return ']';
			case '{': return '}';
			case ')': return '(';
			case ']': return '[';
			case '}': return '{';
			default: throw new IllegalArgumentException("Unknown bracket type: " + bracket);
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
		return "'" + bracket + "' at line " + line + ", column " + column;
	}
}
