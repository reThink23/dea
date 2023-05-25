package ueb7;

public interface IList<T> {
	boolean empty(); // Ist die Liste leer?
	void clear(); // Loeschen aller Elemente.
	void append(T el); // Anfuegen eines Elementes ans Ende der Liste.
	void insert(T el, int pos); // Einfuegen eines Elementes an die gegebene Position.
	void delete(int pos); // Entfernt das Element an der gegebenen Position.
	void deleteLast(); // Entfernt das letzte Element der Liste.
	void reverse(); // Kehrt die Listenreihenfolge um.
	T get(int pos); // Gibt das Element mit Index pos zurück.
	int size(); // Gibt die Anzahl der gespeicherten Elemente zurück.
	String toString(); // Zur übersichtlichen Ausgabe der Liste, z.B. [22,54,3].
}

