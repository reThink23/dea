package ueb8;

import ueb7.SList;

public class Stack<T extends Comparable<T>> {
	private SList<T> stack;

	public Stack() { stack = new SList<T>(); }

	public void push(T t) { stack.prepend(t); }
	public void pop() { stack.deleteFirst();  }
	public T top() { return stack.get(0);  }
	public boolean isEmpty() { return stack.empty(); }
	public int size() { return stack.size(); }
	public String toString() { return stack.toString(); }
}

