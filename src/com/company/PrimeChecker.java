package com.company;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PrimeChecker implements Checker {
    private final Set<Integer> primes = new HashSet<>();
    private final Iterator<Integer> provider;

    public PrimeChecker(Iterator<Integer> provider) {
        this.provider = provider;
    }

    @Override
    public boolean Check(Integer number) {
        if (primes.isEmpty()) {
            populateSet();
        }
        return primes.contains(number);
    }

    private void populateSet() {
        while (provider.hasNext()) {
            primes.add(provider.next());
        }
    }
}
