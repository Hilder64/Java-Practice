package com.nimych.mentoring.module1.task1_1_1;

import java.util.Comparator;
import java.util.List;

public interface Buffer<T> {
    void put(T t);

    T get();

    Object[] toObjectArray();

    T[] toArray();

    List<T> asList();

    void addAll(List<? extends T> toAdd);

    void sort(Comparator<? super T> comparator);

    boolean isEmpty();

    boolean isFull();
}
