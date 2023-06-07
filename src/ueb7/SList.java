package ueb7;

import java.util.ArrayList;

public class SList<T extends Comparable<T>> implements IList<T> {
	private SListItem<T> head;
	private int size;

	public SList() {
		head = null;
		size = 0;
	}

	public SListItem<T> searchElement(T key) {
		// returns first SListItem which contains key as its data
		// member if key is in the list, and returns null otherwise
		SListItem<T> current = head;
		while (current != null && current.data.compareTo(key) != 0)
			current = current.next;
		return current;
	}

	public boolean empty() {return head == null;};
	
	public void clear() { head = null; size = 0;}
	
	public void append(T el) {
		if (empty()) {head = new SListItem<T>(el, null); return;}
		SListItem<T> crt = head;
		while (crt != null) {
			if (crt.next == null) {
				crt.next = new SListItem<T>(el, null);
				size++;
				return;
			}
			crt = crt.next;
		}
	}

	public void prepend(T el) {
		if (empty()) {head = new SListItem<T>(el, null); return;}
		head = new SListItem<T>(el, head);
		size++;
	}
	
	public void deleteLast() {
		if (empty()) return;
		SListItem<T> crt = head;
		while (crt.next != null) {
			if (crt.next.next == null) { 
				crt.next = null; 
				return; 
			}
			crt = crt.next;
		}
		size--;
	}

	public void deleteFirst() {
		if (empty()) return;
		head = head.next;
		size--;
	}
	
	public void insert(T el, int pos) {
		if (empty()) return;
		if (pos < 0) throw new IndexOutOfBoundsException("pos is negative");
		if (pos == 0) {
			prepend(el);
			size++;
			return;
		}
		int count = 0;
		SListItem<T> crt = head;
		while (crt.next != null) {
			if (count == pos-1) {
				crt.next = new SListItem<T>(el, crt.next);
				size++;
				return;
			}
			count++;
			crt = crt.next;
		}
		throw new IndexOutOfBoundsException("pos is greater than list size (" + count + ")");
	}
	
	public void delete(int pos) {
		if (empty()) return;
		if (pos < 0) throw new IndexOutOfBoundsException("pos is negative");
		if (pos == 0) {
			deleteFirst();
			return;
		}
		int count = 0;
		SListItem<T> crt = head;
		while (crt.next != null) {
			if (count == pos-1) {
				crt.next = crt.next.next;
				size--;
				return;
			}
			count++;
			crt = crt.next;
		}
		throw new IndexOutOfBoundsException("pos is greater than list size (" + count + ")");
	}

	public void reverse() {
		if (empty()) return;
		SListItem<T> crt = head;
		SListItem<T> prev = null;
		SListItem<T> next = null;
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
		if (empty()) return null;
		if (pos < 0) throw new IndexOutOfBoundsException("pos is negative");
		int count = 0;
		SListItem<T> crt = head;
		while (crt != null) {
			if (count == pos) return crt.data;
			count++;
			crt = crt.next;
		}
		throw new IndexOutOfBoundsException("pos is greater than list size (" + count + ")");
	}
	
	public int size() {
		// if (empty()) return 0;
		// int size = 0;
		// SListItem<T> crt = head;
		// while (crt != null) {
		// 	size++;
		// 	crt = crt.next;
		// }
		return size;
	}
	
	public String toString() {
		ArrayList<T> arr = new ArrayList<T>();
		SListItem<T> crt = head;
		while (crt != null) {
			arr.add(crt.data);
			crt = crt.next;
		}
		return arr.toString();
	}
}
