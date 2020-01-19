package ru.otus.l03;

import java.util.*;

public class DIYArrayList<T> implements List<T> {
	private int size;
	private int listCapacity;
	private Object[] elements;
	private int modCount = 0;

	public static final String UNSUPORTEDMETHOD = "This method can't be used";


	public DIYArrayList(int listCapacity) {
		if (listCapacity > 0) {
			this.elements = new Object[listCapacity];
		} else if (listCapacity != 0) {
			throw new IllegalArgumentException("Invalid list size: " + listCapacity);
		}
		this.elements = new Object[0];
	}

	public DIYArrayList() {
		this.elements = new Object[0];
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException(UNSUPORTEDMETHOD);
	}

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException(UNSUPORTEDMETHOD);
	}

	@Override
	public Iterator<T> iterator() {
		throw new UnsupportedOperationException(UNSUPORTEDMETHOD);
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(this.elements, this.size);
	}

	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length < this.size) {
			return (T[]) Arrays.copyOf(this.elements, this.size, a.getClass());
		} else {
			System.arraycopy(this.elements, 0, a, 0, this.size);
			if (a.length > this.size) {
				a[this.size] = null;
			}
			return a;
		}
	}

	@Override
	public boolean add(T t) {
		throw new UnsupportedOperationException(UNSUPORTEDMETHOD);
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException(UNSUPORTEDMETHOD);
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		throw new UnsupportedOperationException(UNSUPORTEDMETHOD);
	}

	@Override
	public boolean addAll(Collection<? extends T> collection) {
		Object[] receivedCollection = collection.toArray();
		modCount++;
		int numNew = receivedCollection.length;
		if (numNew == 0) {
			return false;
		} else {
			int s = this.size;
			if (numNew > this.elements.length - s) {
				elements = Arrays.copyOf(this.elements, this.listCapacity + receivedCollection.length);
			}
			System.arraycopy(receivedCollection, 0, elements, s, numNew);
			this.size = s + numNew;
			return true;
		}
	}

	@Override
	public boolean addAll(int i, Collection<? extends T> collection) {
		throw new UnsupportedOperationException(UNSUPORTEDMETHOD);
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		throw new UnsupportedOperationException(UNSUPORTEDMETHOD);
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		throw new UnsupportedOperationException(UNSUPORTEDMETHOD);
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException(UNSUPORTEDMETHOD);
	}

	@Override
	public T get(int index) {
		return (T) this.elements[index];
	}

	@Override
	public T set(int i, T t) {
		throw new UnsupportedOperationException(UNSUPORTEDMETHOD);
	}

	@Override
	public void add(int i, T t) {
		throw new UnsupportedOperationException(UNSUPORTEDMETHOD);
	}

	@Override
	public T remove(int i) {
		throw new UnsupportedOperationException(UNSUPORTEDMETHOD);
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException(UNSUPORTEDMETHOD);
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException(UNSUPORTEDMETHOD);
	}

	@Override
	public ListIterator<T> listIterator() {
		return null;
	}

	@Override
	public ListIterator<T> listIterator(int i) {
		return null;
	}

	@Override
	public List<T> subList(int i, int i1) {
		throw new UnsupportedOperationException(UNSUPORTEDMETHOD);
	}


	@Override
	public void sort(Comparator<? super T> c) {
		Arrays.sort((T[]) this.elements, 0, this.size, c);
		++this.modCount;

	}
}
