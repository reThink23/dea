package ueb7;

public class DListItem<T extends Comparable<T>> {
	public T data;
	public DListItem<T> next;
	public DListItem<T> prev;

	public DListItem(T data, DListItem<T> next, DListItem<T> prev) {
		this.data = data;
		this.next = next;
		this.prev = prev;
	}
}