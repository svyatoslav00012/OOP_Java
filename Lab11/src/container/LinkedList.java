package container;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

public class LinkedList<T> implements Container<T>, Iterable<T>, Serializable, SerializableContainer {

	private static long serialVersionUID = 1L;
	private Node first;

	public LinkedList() {

	}

	//Добавление элемента
	@Override
	public void add(T t) {
		if (first == null) {
			first = new Node(t, null);
		} else {
			Node last = getLast();
			last.setNext(new Node<>(t, last));
		}
	}

	@Override
	public int size() {
		return first == null ? 0 : countSize(first, 1);
	}

	@Override
	public boolean contains(T t) {
		Node n = first;
		while (n != null) {
			if (n.getData().equals(t))
				return true;
			n = n.getNext();
		}
		return false;
	}

	@Override
	public boolean containsAll(Container t) {
		for (Object obj1 : this) {
			boolean cont = false;
			for (Object obj2 : (LinkedList) t)
				if (obj1.equals(obj2)) {
					cont = true;
					break;
				}
			if (!cont)
				return false;
		}
		return false;
	}


	@Override
	public void clear() {
		first = null;
	}

	@Override
	public boolean remove(Object o) {
		Node n = first;
		while (n != null) {
			if (n.getData().equals(o)) {
				if (n == first)
					first = first.getNext();
				else n.remove();
				return true;
			}
			n = n.getNext();
		}
		return false;
	}

	@Override
	public Object[] toArray() {
		Node n = first;
		Object[] array = new Object[0];
		while (n != null) {
			array = Arrays.copyOf(array, array.length + 1);
			array[array.length - 1] = n;
			n = n.getNext();
		}
		return array;
	}


	@Override
	public Iterator<T> iterator() {
		return new It<T>();
	}


	//Получаем элемент (точнее сразу его содержимое) по индексу
	public T get(int index) {
		Node g = get(first, index);
		return g == null ? null : (T) g.getData();
	}

	//удаляем элемент по индексу
	public boolean delete(int index) {
		if (index >= size() || index < 0)
			return false;
		Node d = get(first, index);
		System.out.println("DELETE " + (d.getPrev() == null ? "NULL" : d.getPrev().getData()));
		System.out.println("DELETE " + (d.getNext() == null ? "NULL" : d.getNext().getData()));
		if (d == first)
			first = first.getNext();
		else d.remove();
		if (index == 0)
			first = first.getNext();
		return true;
	}

	//размер массивв


	private int countSize(Node n, int size) {
		return n.getNext() == null ? size : countSize(n.getNext(), size + 1);
	}

	public Node get(Node node, int index) {
		return index == 0 || node == null ? node : get(node.getNext(), index - 1);
	}

	public Node getLast() {
		return first == null ? null : getLast(first);
	}

	private Node getLast(Node node) {
		return node.getNext() == null ? node : getLast(node.getNext());
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Node n = first; n != null; n = n.getNext())
			sb.append(n.getData() + " ");
		return sb.toString();
	}

	@Override
	public void marshal() {
		try {
			FileOutputStream out =
					new FileOutputStream(new File("out.xml"));
			XMLEncoder encoder = new XMLEncoder(out);
			encoder.writeObject(first);
			encoder.flush();
			encoder.close();
			System.out.println("Marshalled");
		} catch (Exception e) {
			System.err.println("Error marshalling");
		}
	}

	@Override
	public void demarshal() {
		try {
			FileInputStream in =
					new FileInputStream(new File("out.xml"));
			XMLDecoder decoder = new XMLDecoder(in);
			first = (Node) decoder.readObject();
			decoder.close();
		} catch (Exception e) {
			System.err.println("Error demarshalling");
		}
		System.out.println("Successfuly demarshalled");
	}

	@Override
	public void serialize() {
		try {
			ObjectOutputStream out =
					new ObjectOutputStream(
							new FileOutputStream(
									new File("out.txt")
							)
					);
			out.writeObject(this);
			out.close();
			System.out.println("serialized");
		} catch (IOException e) {
			System.out.println("serializing error");
		}
	}

	@Override
	public void deserialize() {
		try {
			ObjectInputStream in =
					new ObjectInputStream(
							new FileInputStream(
									new File("out.txt")
							)
					);
			first = ((LinkedList) in.readObject()).first;
			System.out.println("deserialized");
		} catch (Exception e) {
			System.out.println("deserializing error");
		}
	}

	class It<T> implements Iterator<T> {

		Node<T> n = first;

		@Override
		public boolean hasNext() {
			return n.getNext() != null;
		}

		@Override
		public T next() {
			return (n = n.getNext() == null ? n : n.getNext()).getData();
		}

		@Override
		public void remove() {
			if (n == first)
				first = first.getNext();
			else n.remove();
		}
	}
}

