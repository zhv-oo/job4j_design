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
        for (int i = row; i < data.length; i++) {
            if (data[i].length != 0) {
                chk = true;
                break;
            }
        }
        return chk;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        boolean chk = false;
        while (!chk) {
            if (column == data[row].length) {
                row++;
                column = 0;
            } else if (data[row][column] != 0) {
                chk = true;
            }
        }
        return data[row][column++];
    }
}