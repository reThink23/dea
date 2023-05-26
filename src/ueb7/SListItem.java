package ueb7;

public class SListItem<T extends Comparable<T>> {
	public T data;
	public SListItem<T> next;

	public SListItem(T data, SListItem<T> next) {
		this.data = data;
		this.next = next;
	}
}
