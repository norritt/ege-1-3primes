package com.company;

import java.util.Iterator;

public interface GeneratorFactory {
    Iterator<Integer> Create();

    Iterator<Integer> CreateFrom(Integer from);
}
