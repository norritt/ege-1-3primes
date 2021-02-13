package com.company;

import java.util.Iterator;

public class RangeIterator implements Iterator<Integer> {
    private final int minVal;
    private final int maxVal;
    private int current = 0;

    public RangeIterator(int min, int max) {
        current = min - 1;
        minVal = min;
        maxVal = max;
    }

    @Override
    public boolean hasNext() {
        return current < maxVal;
    }

    @Override
    public Integer next() {
        return ++current;
    }
}
