package container;

import java.io.*;
import java.util.*;

public class MyContainer implements Container, Sortable,
		Iterable<String>, Serializable{

	private static final long serialVersionUID = 1L;

	private int size = 0;
	private String[] data = null;

	public MyContainer(){
		data = new String[0];
	}

	@Override
	public void add(String string) {
		if(data.length <= size)
			realloc();
		data[size++] = string;
	}

	public String get(int index){
		return index >= size ? null : data[index];
	}

	@Override
	public void clear() {
		size = 0;
		realloc();
	}

	@Override
	public boolean remove(String string) {
		for(int i = 0; i < size; ++i)
			if(data[i].equals(string)){
				for(int j = i; j < size - 1; ++j)
					data[j] = data[j + 1];
				size--;
				return true;
			}
		return false;
	}

	@Override
	public Object[] toArray() {
		return data;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean contains(String string) {
		for(int i = 0; i < size; ++i)
			if(data[i].equals(string))
				return true;
		return false;
	}

	@Override
	public boolean containsAll(Container container) {
		String[] s = (String[]) container.toArray();
		for(String s1 : s)
			if(!contains(s1))
				return false;
		return true;
	}

	@Override
	public Iterator<String> iterator() {
		return new Itr<String>();
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(Iterator<String> it = iterator(); it.hasNext();)
			sb.append(it.next() + "\n");
		return sb.toString();
	}

	public void sort(){
		qsort(0, size - 1);
	}

	private void qsort(int b, int e){
		String tmp;
		int l = b;
		int r = e;
		int m = (l + r) / 2;
		while(l <= r){
			while(data[l].compareTo(data[m]) < 0)l++;
			while(data[r].compareTo(data[m]) > 0)r--;
			if(l <= r){
				tmp = data[l];
				data[l] = data[r];
				data[r] = tmp;
				l++;
				r--;
			}
		}
		if(b < r)
			qsort(b, r);
		if(e > l)
			qsort(l, e);
	}

	private void realloc(){
		data = Arrays.copyOf(data, size * 2);
	}

	public void marshal(File f){
		if(!f.exists()){
			System.out.println("No such file exists");
			return;
		}
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(new Date(System.currentTimeMillis()));
			out.writeObject(this);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Object successfully marshaled");
	}

	public void demarshal(File f){
		if(!f.exists()){
			System.out.println("No such file exists");
			return;
		}
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
			Date d = (Date) in.readObject();
			MyContainer c = (MyContainer) in.readObject();
			this.size = c.size;
			this.data = c.data;
			System.out.println("This object was marshalled on " + d);
			System.out.println("Now, demarshalled... ");
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void read(){
		Scanner in = new Scanner(System.in);
		String s;
		while(!(s = in.nextLine()).equals("."))
			add(s);
	}

	class Itr<String> implements Iterator{

		int cursor = 0;
		int last = -1;

		@Override
		public boolean hasNext() {
			return cursor < size;
		}

		@Override
		public Object next() throws NoSuchElementException {
			return cursor == size ? null : data[cursor++];
		}

		@Override
		public void remove() {
			if(last > 0 && last < size)
				MyContainer.this.remove(data[last]);
		}
	}
}
