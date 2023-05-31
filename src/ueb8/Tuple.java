package ueb8;

public class Tuple<T1, T2> {
	private T1 first;
	private T2 second;
	
	public Tuple(T1 x, T2 y) {
		this.first = x;
		this.second = y;
	}
	
	public T1 getFirst() { return first; }
	public T2 getSecond() { return second; }
	
	public String toString() {
		return "(" + first + ", " + second + ")";
	}
}
