package ueb7;

import java.util.ArrayList;

public class DList<T extends Comparable<T>> implements IList<T> {
	private DListItem<T> head;
	private DListItem<T> tail;

	public DList() {
		head = null;
	}

	public DListItem<T> searchElement(T key) {
		// returns first DListItem which contains key as its data
		// member if key is in the list, and returns null otherwise
		DListItem<T> current = head;
		while (current != null && current.data.compareTo(key) != 0)
			current = current.next;
		return current;
	}

	public boolean empty() {
		return head == null;
	};

	public void clear() {
		head = null;
		tail = null;
	}

	public void append(T el) {
		if (empty()) {
			head = new DListItem<T>(el, null, null);
			tail = head;
			return;
		}
		tail.next = new DListItem<T>(el, null, tail);
		tail = tail.next;
	}

	public void prepend(T el) {
		if (empty()) {
			head = new DListItem<T>(el, null, null);
			return;
		}
		head = new DListItem<T>(el, head, null);
	}

	public void deleteLast() {
		if (empty()) return;
		tail.prev.next = null;
		tail = tail.prev;
	}

	public void deleteFirst() {
		if (empty()) return;
		head = head.next;
	}

	public void insert(T el, int pos) {
		if (empty()) return;
		if (pos < 0) throw new IndexOutOfBoundsException("<pos> is negative");
		if (pos == 0) {
			prepend(el);
			return;
		}
		int count = 0;
		DListItem<T> crt = head;
		while (crt.next != null) {
			if (count == pos - 1) {
				crt.next = new DListItem<T>(el, crt.next, crt);
				return;
			}
			count++;
			crt = crt.next;
		}
		throw new IndexOutOfBoundsException("<pos> is greater than list size (" + count + ")");
	}

	public void delete(int pos) {
		if (empty()) return;
		if (pos < 0) throw new IndexOutOfBoundsException("<pos> is negative");
		if (pos == 0) {
			deleteFirst();
			return;
		}
		int count = 0;
		DListItem<T> crt = head;
		while (crt.next != null) {
			if (count == pos - 1) {
				crt.next = crt.next.next;
				return;
			}
			count++;
			crt = crt.next;
		}
		throw new IndexOutOfBoundsException("<pos> is greater than list size (" + count + ")");
	}

	public void reverse() {
		if (empty()) return;
		
		DListItem<T> crt = tail;
		while (crt != null) {
			DListItem<T> next = crt.next;
			crt.next = crt.prev;
			crt.prev = next;
			crt = crt.next;
		}
		crt = head;
		head = tail;
		tail = crt;
	}

	public T get(int pos) {
		if (empty()) return null;
		if (pos < 0) throw new IndexOutOfBoundsException("<pos> is negative");

		int count = 0;
		DListItem<T> crt = head;
		while (crt != null) {
			if (count == pos) return crt.data;
			count++;
			crt = crt.next;
		}
		throw new IndexOutOfBoundsException("<pos> is greater than list size (" + count + ")");
	}

	public int size() {
		if (empty()) return 0;
		int size = 0;
		DListItem<T> crt = head;
		while (crt != null) {
			size++;
			crt = crt.next;
		}
		return size;
	}

	public String toString() {
		ArrayList<T> arr = new ArrayList<T>();
		DListItem<T> crt = head;
		while (crt != null) {
			arr.add(crt.data);
			crt = crt.next;
		}
		return arr.toString();
	}
}
