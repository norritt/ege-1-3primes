package com.company;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        int min, max;
        try {
            min = Integer.parseInt(args[0]);
            max = Integer.parseInt(args[1]);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            return;
        }

        Iterator<Integer> numbersEmitter = new RangeIterator(min, max);
        var primeFactory = new PrimeGeneratorFactory(max);
        var primeStart = Instant.now();
        var primes = primeFactory.Create();
        var primeTime = Duration.between(primeStart, Instant.now()).toMillis();
        Checker checker = new TriplePrimeChecker(
                primeFactory,
                new PrimeChecker(primes)
        );
        var counter = 0;
        var minGood = -1;
        var mainStart = Instant.now();
        while (numbersEmitter.hasNext()) {
            var number = numbersEmitter.next();
            if (checker.Check(number)) {
                counter ++;
                if (minGood == -1) {
                    minGood = number;
                }
            }
        }
        var mainTime = Duration.between(mainStart, Instant.now()).toMillis();
        System.out.println(MessageFormat.format("Minimum in range [{2}, {3}] is {0}, total count: {1}", minGood, counter, min, max));
        System.out.println(MessageFormat.format("Primes calculated in {0}ms, main algorithm in {1}ms", primeTime, mainTime));
    }
}
