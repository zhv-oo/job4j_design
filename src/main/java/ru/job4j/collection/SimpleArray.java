package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private Object[] container;
    private final int defaultCap = 10;
    private int size = 0;
    private int modCount = 0;

    public SimpleArray() {
        this.container = new Object[defaultCap];
    }

    public SimpleArray(int size) {
        this.container = new Object[size];
    }

    public T get(int index) {
        return (T) container[Objects.checkIndex(index, size)];
    }

    public void add(T model) {
        if (size < container.length) {
            container[size++] = model;
            modCount++;
        } else {
            int oldCapacity = container.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            Object[] newContainer = new Object[newCapacity];
            System.arraycopy(container, 0, newContainer, 0, size);
            container = newContainer;
            container[size++] = model;
            modCount++;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int expectedModCount = modCount;
            private int pos = 0;
            @Override
            public boolean hasNext() {
                return pos < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[pos++];
            }
        };
    }
}