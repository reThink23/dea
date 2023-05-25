package ueb7;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SList<T extends Comparable<T>> extends List<T> {
	private ListItem<T> head;

	public SList() {
		head = new ListItem<T>(null, null);
	}

	public ListItem<T> searchElement(T key) {
		// returns first ListItem which contains key as its data
		// member if key is in the list, and returns null otherwise
		ListItem<T> current = head;
		while (current != null && current.data.compareTo(key) != 0)
			current = current.next;
		return current;
	}

	public boolean empty() {return head.next == null;};
	
	public void clear() { head.next = null;}
	
	public void append(T el) {
		ListItem<T> crt = head;
		while (crt != null) {
			if (crt.next == null) {
				crt.next = new ListItem<T>(el, null);
				return;
			}
			crt = crt.next;
		}
		crt.next = new ListItem<T>(el, null);
	}
	
	public void deleteLast() {
		ListItem<T> crt = head;
		while (crt.next != null) {
			if (crt.next.next == null) { 
				crt.next = null; 
				return; 
			}
			crt = crt.next;
		}
	}
	
	public void insert(T el, int pos) {}
	
	public void delete(int pos) {
		if (pos < 0) throw new IndexOutOfBoundsException();
		int count = 0;
		ListItem<T> crt = head;
		while (crt.next != null) {
			if (count == pos) {
				crt.next = crt.next.next;
				return;
			}
			count++;
			crt = crt.next;
		}
		throw new IndexOutOfBoundsException();
	}

	public void reverse() {
		ListItem<T> crt = head;
		ListItem<T> prev = null;
		ListItem<T> next = null;
		while (crt.next != null) {
			next = crt.next;
			crt.next = prev;
			prev = crt;
			crt = next;
		}
		crt.next = prev;
		head = crt;
	}
	
	public T get(int pos) {
		if (pos < 0) throw new IndexOutOfBoundsException();
		int count = 0;
		ListItem<T> crt = head;
		while (crt.next != null) {
			if (count == pos) return crt.data;
			count++;
			crt = crt.next;
		}
		throw new IndexOutOfBoundsException();
	}
	
	public int size() {
		int size = 0;
		ListItem<T> crt = head;
		while (crt.next != null) {
			size++;
			crt = crt.next;
		}
		return size;
	}
	
	public String toString() {
		ArrayList<T> arr = new ArrayList<T>();
		ListItem<T> crt = head;
		while (crt.next != null) {
			arr.add(crt.data);
			crt = crt.next;
		}
		return arr.toString();
	}
}
