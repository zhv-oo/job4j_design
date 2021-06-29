package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator {
    private final int[] numbers;
    private int pos = 0;

    public EvenIt(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        return this.getPos() != -1;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.pos = this.getPos();
        return numbers[pos++];
    }

    private int getPos() {
        int rsl = -1;
        for (int i = pos; i < this.numbers.length; i++) {
            if (this.numbers[i] % 2 == 0) {
                rsl = i;
                break;
            }
        }
        return  rsl;
    }
}
