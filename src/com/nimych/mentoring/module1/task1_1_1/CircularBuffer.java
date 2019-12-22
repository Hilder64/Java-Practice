package com.nimych.mentoring.module1.task1_1_1;

import java.util.Comparator;
import java.util.List;

public class CircularBuffer<T> implements Buffer<T> {

    private T[] array;

    private int head;

    private int tail;

    private int size;

    public CircularBuffer(int capacity) {
        if (capacity < 0) {
            throw new RuntimeException("Illegal capacity: " + capacity);
        } else {
            array = (T[]) new Object[capacity];
            head = -1;
            tail = -1;
            size = 0;
        }
    }

    @Override
    public void put(T t) {
        if (isFull()) {
            throw new RuntimeException("Unable to add value, Circular Buffer is full");
        } else {
            tail = (tail + 1) % array.length;
            if (head == -1) {
                head++;
            }
            array[tail] = t;
            size++;
        }
    }

    @Override
    public T get() {
        if (isEmpty()) {
            throw new RuntimeException("Unable to get value, Circular Buffer is empty");
        } else {
            T value = array[head];
            head = (head + 1) % array.length;
            size--;
            return value;
        }
    }

    @Override
    public Object[] toObjectArray() {
        return new Object[0];
    }

    @Override
    public T[] toArray() {
        return null;
    }

    @Override
    public List<T> asList() {
        return null;
    }

    @Override
    public void addAll(List<? extends T> toAdd) {

    }

    @Override
    public void sort(Comparator<? super T> comparator) {

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == array.length;
    }
}
