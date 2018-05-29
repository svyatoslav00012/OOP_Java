package container;

import javafx.util.Pair;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class MyContainer implements Container, Sortable,
		Iterable<String>, Serializable {

	private static final long serialVersionUID = 1L;

	private int size = 0;
	private String[] data = null;

	public MyContainer() {
		data = new String[0];
	}

	@Override
	public void add(String string) {
		if (size == data.length)
			realloc();
		data[size++] = string;
	}

	public String get(int index) {
		return index >= size ? null : data[index];
	}

	@Override
	public void clear() {
		size = 0;
		realloc();
	}

	@Override
	public boolean remove(String string) {
		for (int i = 0; i < size; ++i)
			if (data[i].equals(string)) {
				for (int j = i; j < size - 1; ++j)
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
		for (int i = 0; i < size; ++i)
			if (data[i].equals(string))
				return true;
		return false;
	}

	@Override
	public boolean containsAll(Container container) {
		String[] s = (String[]) container.toArray();
		for (String s1 : s)
			if (!contains(s1))
				return false;
		return true;
	}

	@Override
	public Iterator<String> iterator() {
		return new Itr<String>();
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Iterator<String> it = iterator(); it.hasNext(); )
			sb.append(it.next() + "\n");
		return sb.toString();
	}

	public void sort(){
		System.out.println("Hello from another thread");
		CountDownLatch c = new CountDownLatch(1);
		ExecutorService ex = Executors.newSingleThreadExecutor();
		Thread sortThread = new Thread() {
			@Override
			public void run() {
				synchronized (this) {
					Timer t = new Timer();
					Stack<Pair<Integer, Integer>> s = new Stack<>();
					String[] bufData = Arrays.copyOf(data, data.length);
					s.push(new Pair<>(0, size - 1));
					while (!s.empty()) {
						int b = s.peek().getKey();
						int e = s.pop().getValue();
						//System.out.println(b + " " + e);
						String tmp;
						int l = b;
						int r = e;
						String pivot = bufData[(l + r) / 2];
						while (l <= r) {
							while (bufData[l].compareTo(pivot) < 0) l++;
							while (bufData[r].compareTo(pivot) > 0) r--;
							if (l <= r) {
								tmp = bufData[l];
								bufData[l] = bufData[r];
								bufData[r] = tmp;
								l++;
								r--;
							}
							try {
								Thread.sleep(1);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
						if (b < r)
							s.push(new Pair<>(b, r));
						if (e > l)
							s.push(new Pair<>(l, e));
					}
					data = bufData;
					c.countDown();
				}
			}
		};
		try {
			ex.submit(sortThread).get(10000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		try {
			c.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void realloc() {
		data = Arrays.copyOf(data, size == 0 ? 1 : size * 2);
	}

	public void marshal(File f) {
		if (!f.exists()) {
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

	public void demarshal(File f) {
		if (!f.exists()) {
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

	public void read() {
		Scanner in = new Scanner(System.in);
		String s;
		while (!(s = in.nextLine()).equals("."))
			add(s);
	}

	public void randomlyFill(int size) {
		CountDownLatch c = new CountDownLatch(1);
		ExecutorService ex = Executors.newSingleThreadExecutor();
		MyContainer container = new MyContainer();
		Thread sortThread = new Thread() {
			@Override
			public void run() {
				Random r = new Random();
				Random lenR = new Random();
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < size; ++i) {
					int len = Math.abs(lenR.nextInt()) % 10 + 5;
					for (int j = 0; j < len; ++j)
						sb.append((char) (Math.abs(r.nextInt()) % 26 + (r.nextBoolean() ? 'a' : 'A')));
					container.add(sb.toString());
					sb.setLength(0);
				}
				c.countDown();
			}
		};
		try {
			ex.submit(sortThread).get(10000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
		try {
			c.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.size = container.size;
		this.data = container.data;
		//System.out.println(MyContainer.this.size);
	}

	class Itr<String> implements Iterator {

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
			if (last > 0 && last < size)
				MyContainer.this.remove(data[last]);
		}
	}
}
