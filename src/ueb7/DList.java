package ueb7;

public class DList<T extends Comparable<T>> extends List<T> {
	public boolean empty() {return false;}
	public void clear() {}
	public void append(T el) {}
	public void insert(T el, int pos) {}
	public void delete(int pos) {}
	public void deleteLast() {}
	public void reverse() {}
	public T get(int pos) {return null;}
	public int size() {return 0;}
	public String toString() {return null;}

}
