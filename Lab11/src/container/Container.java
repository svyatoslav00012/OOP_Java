package container;

import java.util.Iterator;

public interface Container<T extends Object>{

	//повертає вміст контейнера у вигляді рядка
	String toString();

	//додає вказаний елемент до кінця контейнеру
	void add(T t);

	//видаляє всі елементи з контейнеру
	void clear();

	//видаляє перший випадок вказаного елемента з контейнера
	boolean remove(T t);

	//повертає масив, що містить всі елементи у контейнері
	Object[] toArray();

	//повертає кількість елементів у контейнері
	int size();

	// повертає true, якщо контейнер містить вказаний елемент
	boolean contains(T t);

	// повертає true, якщо контейнер
	// містить всі елементи з зазначеного у параметрах;
	boolean containsAll(Container<T> t);

	//повертає ітератор відповідно до
	public Iterator<T> iterator();
}
