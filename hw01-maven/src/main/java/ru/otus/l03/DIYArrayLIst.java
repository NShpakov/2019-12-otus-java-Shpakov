package ru.otus.l03;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class DIYArrayLIst<T> implements List<T> {
	protected int modCount = 0;
	private Object[] elementData;
	private int size;
	private static final Object[] EMPTY_ELEMENTDATA = new Object[0];
	private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = new Object[0];

	public DIYArrayLIst() {
		this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
	}


	public DIYArrayLIst(int initialCapacity) {
		if (initialCapacity > 0) {
			this.elementData = new Object[initialCapacity];
		} else {
			if (initialCapacity != 0) {
				throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
			}

			this.elementData = EMPTY_ELEMENTDATA;
		}

	}

	public DIYArrayLIst(Collection<? extends T> c) {
		this.elementData = c.toArray();
		if ((this.size = this.elementData.length) != 0) {
			if (this.elementData.getClass() != Object[].class) {
				this.elementData = Arrays.copyOf(this.elementData, this.size, Object[].class);
			}
		} else {
			this.elementData = EMPTY_ELEMENTDATA;
		}

	}

	T elementData(int index) {
		return (T) this.elementData[index];
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException("isEmpty");
	}

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException("contains");
	}

	@Override
	public Iterator<T> iterator() {
		throw new UnsupportedOperationException("iterator3");
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException("toArray1");
	}

	@Override
	public <T1> T1[] toArray(T1[] t1s) {
		throw new UnsupportedOperationException("toArray");
	}

	@Override
	public boolean add(T t) {
		++this.modCount;
		this.add(t, this.elementData, this.size);
		return true;
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException("remove");
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		throw new UnsupportedOperationException("containsAll");
	}

	@Override
	public boolean addAll(Collection<? extends T> collection) {
		throw new UnsupportedOperationException("addAll1");
	}

	@Override
	public boolean addAll(int i, Collection<? extends T> collection) {
		throw new UnsupportedOperationException("addAll");
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		throw new UnsupportedOperationException("removeAll");
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		throw new UnsupportedOperationException("retainAll");
	}

	@Override
	public void replaceAll(UnaryOperator<T> operator) {
		throw new UnsupportedOperationException("replaceAll");
	}

	@Override
	public void sort(Comparator<? super T> c) {
		int expectedModCount = this.modCount;
		T[] t = (T[]) this.elementData;
		Arrays.sort(t, 0, this.size, c);
		if (this.modCount != expectedModCount) {
			throw new ConcurrentModificationException();
		} else {
			++this.modCount;
		}
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException("clear");
	}

	@Override
	public T get(int i) {
		throw new UnsupportedOperationException("get");
	}

	@Override
	public T set(int i, T t) {
		Objects.checkIndex(i, this.size);
		T oldValue = this.elementData(i);
		this.elementData[i] = t;
		return oldValue;
	}

	@Override
	public void add(int index, T element) {
		//this.rangeCheckForAdd(index);
		++this.modCount;
		int s;
		Object[] elementData;
		if ((s = this.size) == (elementData = this.elementData).length) {
			elementData = this.grow();
		}

		System.arraycopy(elementData, index, elementData, index + 1, s - index);
		elementData[index] = element;
		this.grow() ;
	}

	private Object[] grow() {
		return this.grow(this.size + 1);
	}

	private Object[] grow(int minCapacity) {
		elementData = Arrays.copyOf(this.elementData, this.newCapacity(minCapacity));
		return this.elementData;
	}

	private int newCapacity(int minCapacity) {
		int oldCapacity = this.elementData.length;
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		if (newCapacity - minCapacity <= 0) {
			if (this.elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
				return Math.max(10, minCapacity);
			} else if (minCapacity < 0) {
				throw new OutOfMemoryError();
			} else {
				return minCapacity;
			}
		} else {
			return newCapacity - 2147483639 <= 0 ? newCapacity : hugeCapacity(minCapacity);
		}
	}

	private void add(T t, Object[] elementData, int s) {
		if (s == elementData.length) {
			elementData = this.grow();
		}

		elementData[s] = t;
		this.size = s + 1;
	}

	private static int hugeCapacity(int minCapacity) {
		if (minCapacity < 0) {
			throw new OutOfMemoryError();
		} else {
			return minCapacity > 2147483639 ? 2147483647 : 2147483639;
		}
	}


	@Override
	public T remove(int i) {
		throw new UnsupportedOperationException("remove");
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException("indexOf");
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException("lastIndexOf");
	}

	@Override
	public ListIterator<T> listIterator() {
		return this.listIterator(0);
	}

	@Override
	public ListIterator<T> listIterator(int i) {
		return new DIYArrayLIst<T>.ListItr(i);
	}

	@Override
	public List<T> subList(int i, int i1) {
		throw new UnsupportedOperationException("subList");
	}

	@Override
	public Spliterator<T> spliterator() {
		throw new UnsupportedOperationException("spliterator");
	}

	@Override
	public String toString() {
		return "DIYArrayLIst2{elementData= " + Arrays.toString(elementData) + " ," + "size= " + size;
	}

	private class ListItr extends DIYArrayLIst<T>.Itr implements ListIterator<T> {
		ListItr(int index) {
			super();
			this.cursor = index;
		}

		public boolean hasPrevious() {
			return this.cursor != 0;
		}

		public int nextIndex() {
			return this.cursor;
		}

		public int previousIndex() {
			return this.cursor - 1;
		}

		public T previous() {
			this.checkForComodification();
			int i = this.cursor - 1;
			if (i < 0) {
				throw new NoSuchElementException();
			} else {
				Object[] elementData = DIYArrayLIst.this.elementData;
				if (i >= elementData.length) {
					throw new ConcurrentModificationException();
				} else {
					this.cursor = i;
					return (T) elementData[this.lastRet = i];
				}
			}
		}

		public void set(T t) {
			if (this.lastRet < 0) {
				throw new IllegalStateException();
			} else {
				this.checkForComodification();

				try {
					DIYArrayLIst.this.set(this.lastRet, t);
				} catch (IndexOutOfBoundsException var3) {
					throw new ConcurrentModificationException();
				}
			}
		}

		public void add(T t) {
			this.checkForComodification();

			try {
				int i = this.cursor;
				DIYArrayLIst.this.add(i, t);
				this.cursor = i + 1;
				this.lastRet = -1;
				this.expectedModCount = DIYArrayLIst.this.modCount;
			} catch (IndexOutOfBoundsException var3) {
				throw new ConcurrentModificationException();
			}
		}
	}

	private class Itr implements Iterator<T> {
		int cursor;
		int lastRet = -1;
		int expectedModCount;

		Itr() {
			this.expectedModCount = DIYArrayLIst.this.modCount;
		}

		public boolean hasNext() {
			return this.cursor != DIYArrayLIst.this.size;
		}

		public T next() {
			this.checkForComodification();
			int i = this.cursor;
			if (i >= DIYArrayLIst.this.size) {
				throw new NoSuchElementException();
			} else {
				Object[] elementData = DIYArrayLIst.this.elementData;
				if (i >= elementData.length) {
					throw new ConcurrentModificationException();
				} else {
					this.cursor = i + 1;
					return (T) elementData[this.lastRet = i];
				}
			}
		}

		public void remove() {
			if (this.lastRet < 0) {
				throw new IllegalStateException();
			} else {
				this.checkForComodification();

				try {
					DIYArrayLIst.this.remove(this.lastRet);
					this.cursor = this.lastRet;
					this.lastRet = -1;
					this.expectedModCount = DIYArrayLIst.this.modCount;
				} catch (IndexOutOfBoundsException var2) {
					throw new ConcurrentModificationException();
				}
			}
		}

		public void forEachRemaining(Consumer<? super T> action) {
			Objects.requireNonNull(action);
			int size = DIYArrayLIst.this.size;
			int i = this.cursor;
			if (i < size) {
				Object[] es = DIYArrayLIst.this.elementData;
				if (i >= es.length) {
					throw new ConcurrentModificationException();
				}

				while (i < size && DIYArrayLIst.this.modCount == this.expectedModCount) {
					action.accept(DIYArrayLIst.elementAt(es, i));
					++i;
				}

				this.cursor = i;
				this.lastRet = i - 1;
				this.checkForComodification();
			}

		}

		final void checkForComodification() {
			if (DIYArrayLIst.this.modCount != this.expectedModCount) {
				throw new ConcurrentModificationException();
			}
		}
	}

	static <T> T elementAt(Object[] es, int index) {
		return (T) es[index];
	}

}
