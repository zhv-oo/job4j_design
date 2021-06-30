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
        if (Objects.checkIndex(index, pos) == index) {
            data[index] = model;
        }
    }
    public void remove(int index) {
        if (Objects.checkIndex(index, pos) == index) {
           System.arraycopy(data, index + 1, data, index, data.length - index - 1);
           pos--;
        }
    }

    public T get(int index) {
        T rsl = null;
        if (Objects.checkIndex(index, pos) == index) {
           rsl = (T) data[index];
        }
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
                T rsl = null;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (rsl == null) {
                    rsl = (T) data[position++];
                }
                return rsl;
            }
        };
    }
}