package ueb7;

import java.util.ArrayList;

public class DList<T extends Comparable<T>> extends List<T> {
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
		DListItem<T> crt = head;
		while (crt != null) {
			if (crt.next == null) {
				crt.next = new DListItem<T>(el, null, crt);
				return;
			}
			crt = crt.next;
		}
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
		if (pos < 0) throw new IndexOutOfBoundsException();
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
		throw new IndexOutOfBoundsException();
	}

	public void delete(int pos) {
		if (empty()) return;
		if (pos < 0) throw new IndexOutOfBoundsException();
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
		throw new IndexOutOfBoundsException();
	}

	public void reverse() {
		if (empty()) return;

		if (empty())
			return;
		DListItem<T> crt = head;
		DListItem<T> prev = null;
		DListItem<T> next = null;
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
		if (pos < 0) throw new IndexOutOfBoundsException();

		int count = 0;
		DListItem<T> crt = head;
		while (crt != null) {
			if (count == pos)
				return crt.data;
			count++;
			crt = crt.next;
		}
		throw new IndexOutOfBoundsException();
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
