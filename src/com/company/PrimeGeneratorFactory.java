package com.company;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;

public class PrimeGeneratorFactory implements GeneratorFactory {
    private final int max;
    private final BitSet bits = new BitSet();
    private Collection<Integer> collection;

    public PrimeGeneratorFactory(int max) {
        this.max = max;
        bits.clear();
        bits.set(2, max, true);
    }

    @Override
    public Iterator<Integer> Create() {
        if (collection == null) {
            generatePrimes();
        }
        return collection.iterator();
    }

    @Override
    public Iterator<Integer> CreateFrom(Integer from) {
        if (collection == null) {
            generatePrimes();
        }
        return collection.stream().dropWhile(x -> x <= from).iterator();
    }

    private void generatePrimes() {
        var mx = Math.round(Math.ceil(Math.sqrt(max)));
        for (int i = 2; i < mx; i ++) {
            if (bits.get(i)) {
                for (int j = i * i; j < max; j += i) {
                    bits.clear(j);
                }
            }
        }
        collection = new ArrayList<>();
        for (int i = 2; i < max; i ++) {
            if (bits.get(i)) {
                collection.add(i);
            }
        }
    }
}
