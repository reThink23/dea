package ueb7;

public class ListItem<T extends Comparable<T>> {
	public T data;
	public ListItem<T> next;

	public ListItem(T data, ListItem<T> next) {
		this.data = data;
		this.next = next;
	}
}
