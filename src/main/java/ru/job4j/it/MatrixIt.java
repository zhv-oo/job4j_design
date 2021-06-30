package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @SuppressWarnings("checkstyle:SimplifyBooleanExpression")
    @Override
    public boolean hasNext() {
        boolean chk = false;
        while (row < data.length) {
            if (column < data[row].length) {
                chk = true;
                break;
            } else {
                column = 0;
                row++;
            }
        }
        return chk;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}