package com.company;

public class TriplePrimeChecker implements Checker {
    private final GeneratorFactory primeGeneratorFactory;
    private final Checker primeChecker;

    public TriplePrimeChecker(
            GeneratorFactory primeGeneratorFactory,
            Checker primeChecker) {
        this.primeGeneratorFactory = primeGeneratorFactory;
        this.primeChecker = primeChecker;
    }

    @Override
    public boolean Check(Integer number) {
        var primeGenerator = primeGeneratorFactory.Create();
        while (primeGenerator.hasNext()) {
            if (primeChecker.Check(number)) {
                return false;
            }
            var firstPrime = primeGenerator.next();
            if (firstPrime > number) {
                return false;
            }
            if (number % firstPrime != 0) {
                continue;
            }

            var div = number / firstPrime;
            if (primeChecker.Check(div)) {
                return false;
            }
            var secondPrimeGenerator = primeGeneratorFactory.CreateFrom(firstPrime);
            while (secondPrimeGenerator.hasNext()) {
                var secondPrime = secondPrimeGenerator.next();
                if (secondPrime > div) {
                    break;
                }
                if (div % secondPrime != 0) {
                    continue;
                }

                var ret = div / secondPrime;
                if (primeChecker.Check(ret)) {
                    return true;
                } else {
                    break;
                }
            }
        }
        return false;
    }
}
