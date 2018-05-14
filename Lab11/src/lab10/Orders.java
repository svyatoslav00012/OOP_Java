package lab10;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Orders {

	private ArrayList<Order> orders;
	private File file;

	public Orders() {
		this.orders = new ArrayList<>();
	}

	public boolean addOrder(String[] params){
		if (!Order.checkCorrectInputFormat(params))
			return false;
		orders.add(new Order(params));
		return true;
	}

	public boolean addOrder(String s) {
		return addOrder(s.split(" "));
	}

	public void deleteOrder(int index) {
		orders.remove(index);
	}

	public void clear(){
		orders.clear();
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
			encoder.writeObject(orders);
			encoder.flush();
			encoder.close();
		} catch (Exception e) {
			System.err.println("Error saving orders");
		}
		System.out.println("Successfully saved");
	}

	public void read() {
		read(file);
	}

	public void read(File f) {
		try {
			FileInputStream in =
					new FileInputStream(f);
			XMLDecoder decoder = new XMLDecoder(in);
			orders = (ArrayList<Order>) decoder.readObject();
			decoder.close();
		} catch (Exception e) {
			System.err.println("Error saving orders");
		}
		System.out.println("Successfuly read");
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("====== ORDERS ======\n");
		for (int i = 0; i < orders.size(); ++i)
			sb.append((i + 1) + ").\n" + orders.get(i) + "\n");
		sb.append("====================\n");
		return sb.toString();
	}
}
