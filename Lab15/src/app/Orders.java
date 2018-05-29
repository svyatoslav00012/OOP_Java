package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Orders {

	private ObservableList<Order> orders;
	private File file = new File("orders.xml");

	public Orders() {
		this.orders = FXCollections.observableArrayList();
		if (file.exists())
			deserialize();
	}

//	public boolean addOrder(String[] params) {
//		if (!Order.checkCorrectInputFormat(params))
//			return false;
//		addOrder(new Order(params));
//		return true;
//	}

	public ObservableList<Order> getList() {
		return orders;
	}

//	public boolean addOrder(String s) {
//		return addOrder(s.split(" "));
//	}

	public void deleteOrder(int index) {
		orders.remove(index);
		serialize();
	}

	public void clear() {
		orders.clear();
		serialize();
	}

	public int size() {
		return orders.size();
	}

	public void save() {
		save(file);
	}

	public void save(File f) {
		try {
			FileOutputStream out =
					new FileOutputStream(f);
			XMLEncoder encoder = new XMLEncoder(out);
			encoder.writeObject(orders.stream().collect(Collectors.toList()));
			encoder.flush();
			encoder.close();
			System.out.println("Successfully saved");
		} catch (Exception e) {
			System.err.println("Error saving orders");
		}
	}

	public void read() {
		read(file);
	}

	public void read(File f) {
		try {
			FileInputStream in =
					new FileInputStream(f);
			XMLDecoder decoder = new XMLDecoder(in);
			try {
				orders = FXCollections
						.observableArrayList((List<Order>) decoder.readObject());
			} catch (Exception e) {
				System.err.println("empty file");
			}
			decoder.close();
			System.out.println("Successfuly read");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error reading orders");
		}
	}

	public void serialize() {
		try {
			ObjectOutputStream out =
					new ObjectOutputStream(
							new FileOutputStream(
									new File("orders.txt")
							)
					);
			out.writeObject(orders.stream().collect(Collectors.toList()));
			out.close();
			System.out.println("serialized");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deserialize() {
		try {
			ObjectInputStream in =
					new ObjectInputStream(
							new FileInputStream(
									new File("orders.txt")
							)
					);

			try {
				orders = FXCollections
						.observableArrayList((List<Order>) in.readObject());
			} catch (Exception e) {
				System.err.println("empty file");
			}
			System.out.println("deserialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("====== ORDERS ======\n");
		for (int i = 0; i < orders.size(); ++i)
			sb.append((i + 1) + ").\n" + orders.get(i) + "\n");
		sb.append("====================\n");
		return sb.toString();
	}

	public void addOrder(Order o) {
		orders.add(o);
		System.out.println(o);
		serialize();
	}

	public void deleteOrder(Order selectedItem) {
		orders.remove(selectedItem);
	}
}
