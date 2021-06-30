package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private int pos = 0;
    private Object[] data;

    public SimpleArray(int size) {
        this.data = new Object[size];
    }

    public void add(T model) {
        data[pos++] = model;
    }

    public void set(int index, T model) {
            data[Objects.checkIndex(index, pos)] = model;
    }
    public void remove(int index) {
        int itemPos = Objects.checkIndex(index, pos);
           System.arraycopy(data, itemPos + 1, data, itemPos, data.length - itemPos - 1);
           pos--;
    }

    public T get(int index) {
        T rsl = null;
           rsl = (T) data[Objects.checkIndex(index, pos)];
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            final private int chk = pos;
            private int position = 0;

            @Override
            public boolean hasNext() {
                return position < chk;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) data[position++];
            }
        };
    }
}