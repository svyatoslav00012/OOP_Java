package container;

import java.io.Serializable;

public class Node<T> implements Serializable{

	private static long serialVersionUID = 1L;
	//ссылка на следующий
	private Node next;
	//ссылка на предыдущий
	private Node prev;
	//собсно символ
	private T data;

	public Node() {
	}

	public Node(T data) {
		this.data = data;
	}

	public Node(T data, Node prev) {
		this.data = data;
		this.prev = prev;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public void remove(){
		if(getPrev() != null)getPrev().setNext(getNext());
		if(getNext() != null)getNext().setPrev(getPrev());
	}
}