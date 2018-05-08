package task8_Novosad;

public interface SimpleInterface<E> extends Iterable<E> {
	void add(E e);
    void clear();
    boolean remove(E e);
    int size();
    boolean containsAll(SimpleArray<E> container);
    String toString();
	boolean contains(E e);
    
}
