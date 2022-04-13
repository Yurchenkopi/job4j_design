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

    @Override
    public boolean hasNext() {
        if (column == data[row].length) {
            if (theNextRowsIsEmpty()) {
                return false;
            }
            return row != data.length - 1;
        }
        return true;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (column == data[row].length) {
            do {
                row++;
            } while (data[row].length == 0);
            column = 0;
        }
        return data[row][column++];
    }

    public boolean theNextRowsIsEmpty() {
        boolean rsl = true;
        for (int i = row; i < data.length; i++) {
            if (data[i].length != 0) {
                rsl = false;
                break;
            }
        }
        return rsl;
    }
}