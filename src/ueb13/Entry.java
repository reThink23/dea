package ueb13;

public class Entry<Key, Value> {
	public Key key;
	public Value value;
	public Entry<Key, Value> next;
	
	public Entry(Key k, Value d) {
		key = k;
		value = d;
		next = null;
	}
	
	public String toString() {
		return "("+ key + "," + value + ")";
	}
}
