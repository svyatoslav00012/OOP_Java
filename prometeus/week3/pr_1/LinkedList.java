package main;

import javax.management.Notification;
import java.util.ArrayList;

public class LinkedList {

	static class Node {
		private Node next;
		private Node prev;
		private Integer data;

		public Node() {
		}

		public Node(Integer data) {
			this.data = data;
		}

		public Node(Integer data, Node prev){
			this.data = data;
			this.prev = prev;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Integer getData() {
			return data;
		}

		public void setData(Integer data) {
			this.data = data;
		}

		public Node getPrev() {
			return prev;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}
	}

	private Node first;

	public LinkedList() {
	}

	public void add(Integer data) {
		if (first == null) {
			first = new Node(data, null);
		} else {
			Node last = getLast();
			last.setNext(new Node(data, last));
		}
	}

	public Integer get(int index) {
		Node g = get(first, index);
		return g == null ? null : g.getData();
	}

	public boolean delete(int index) {
		if (index >= size() || index < 0)
			return false;
		Node d = get(first, index);
		System.out.println("DELETE " +(d.getPrev() == null ? "NULL" : d.getPrev().getData()));
		System.out.println("DELETE " +(d.getNext() == null ? "NULL" : d.getNext().getData()));
		if(d.getPrev() != null)
			d.getPrev().setNext(d.getNext());
		if(d.getNext() != null)
			d.getNext().setPrev(d.getPrev());
		if(index == 0)
			first = first.getNext();
		return true;
	}

	public int size() {
		return first == null ? 0 : countSize(first, 1);
	}

	public int countSize(Node n, int size) {
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

	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(Node n = first; n != null; n = n.getNext())
			sb.append(n.getData() + " ");
		return sb.toString();
	}
}

