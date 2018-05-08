package task8_Novosad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Date;

public class SimpleArray<E> implements SimpleInterface <E>, Serializable{

	
	private static final long serialVersionUID = 1L;
	private static long ID = 0;
	
	static {
		
	}
	
	private long id;
	private E[] values;
	private int size = 0;
	
	public long getId() {
		return id;
	}
	
	SimpleArray(){
		values = (E[]) new Object[size];
		id = ID++;
	}
	
	private void reInit() throws ClassCastException{
		if(size * 2 < values.length || size == values.length) {
			E[] temp=values;
			values = (E[]) new Object[(size == 0) ? 1 : size * 2];
			System.arraycopy(temp, 0, values, 0, size);
		}
	}
	
	public void add(E e) {	
		try {
			reInit();
			values[size++]=e;
		}
		catch(ClassCastException ex) {
			ex.printStackTrace();
		}
	}
	
	public E get(int index) {
		return values[index];
	}
	
	public int size() {
		return size;
	}
	
	public Iterator<E> iterator() {
	    return new SimpleIterator<>();
	}

	public static void main(String[] args) {
	   SimpleArray <String> strings = new SimpleArray<> ();
	   
       strings.add("a");
       strings.add("c");
       strings.add("b");
       
       strings.sort(new Comparator<String>(){
    	   
    	   @Override
    	   public int compare(String one, String two) {
    		   return one.compareTo(two);
    	   }
    	   
       });
       
       strings.serialize();
       
       SimpleArray<String> s2 = new SimpleArray<>();
       s2.deserialize(new File(strings.getId() + ".arr"));
       
       Iterator<String> it = s2.iterator();
       while(it.hasNext())
    	   System.out.println(it.next());
	}
	@Override
	public void clear() {
		for (int i = 0; i < values.length; i++) {
	            values[i] = null;
		}
		size = 0;
		reInit();
	}
	
	public int indexOf(String s) {
		for(int i = 0; i < size; ++i)
			if(values[i].equals(s))
				return i;
		return -1;
	}
	
	public void sort(Comparator<E> c) {
		sort(0, size - 1, c);
	}
	
	public void sort(int l, int r, Comparator<E> c) {
		E tmp;
		int left = l, right = r;
		int m = (left + right) / 2;
		while(left <= right) {
			while(c.compare(values[left], values[m]) < 0)
				left++;
			while(c.compare(values[right], values[m]) > 0)
				right--;
			if(left <= right) {
				tmp = values[left];
				values[left] = values[right];
				values[right] = tmp;
				left++;
				right--;
			}
		}
		if(l < right)
			sort(l, right, c);
		if(r > left)
			sort(left, r, c);
	}
	
	@Override
	public boolean remove(E e) {
		for(int i = 0; i <values.length;i++) {
			if(e.equals(values[i]))
				try {
						System.arraycopy(values, i+1, values, i, values.length - (i + 1));
						reInit();
						size--;
						return true;
					}
					catch(ClassCastException ex) {
						ex.printStackTrace();
					}
		}
		return false;
	}
	
	@Override
	public boolean contains(E e) {
		for(E i : values){
	        if(e.equals(i))
	            return true;
	    }

		return false;
	}
	
	@Override
	public boolean containsAll(SimpleArray<E> container) {
		for(E s : container)
			if(!contains(s))
				return false;
		return true;
	}
	
	void serialize() {
		try {
			File f = new File(id+".arr");
			if(!f.exists())
				f.createNewFile();
			
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
			Date date = new Date(System.currentTimeMillis());
			out.writeObject(date);
			out.writeObject(this);
			out.close();
		} catch (Exception e) {
			System.out.println("Error serrializing object");
		}
	}
	
	void deserialize(File f) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
			Date date = new Date(System.currentTimeMillis());
			Date d = (Date)in.readObject();
			System.out.println("Conteiner has been serialized at " + date);
			SimpleArray<E> s = (SimpleArray<E>)in.readObject();
			this.id = s.id;
			this.values = s.values;
			this.size = s.size;
			in.close();
		} catch (Exception e) {
			System.out.println("Error deserrializing object");
		}		
	}
	
	class SimpleIterator<F> implements Iterator<F> {
		
		private int index=0;
		
		public boolean hasNext(){
			return index < size;
		}
		
		public F next(){
			return (F) values[index++];
		}
		
		public void remove() {
			SimpleArray.this.remove(values[index]);
		}
		
	}

}
