package container;

import java.util.Iterator;

public interface Container{

	//повертає вміст контейнера у вигляді рядка
	String toString();

	//додає вказаний елемент до кінця контейнеру
	void add(String string);

	//видаляє всі елементи з контейнеру
	void clear();

	//видаляє перший випадок вказаного елемента з контейнера
	boolean remove(String string);

	//повертає масив, що містить всі елементи у контейнері
	Object[] toArray();

	//повертає кількість елементів у контейнері
	int size();

	// повертає true, якщо контейнер містить вказаний елемент
	boolean contains(String string);

	// повертає true, якщо контейнер
	// містить всі елементи з зазначеного у параметрах;
	boolean containsAll(Container container);

	//повертає ітератор відповідно до
	public Iterator<String> iterator();
}
