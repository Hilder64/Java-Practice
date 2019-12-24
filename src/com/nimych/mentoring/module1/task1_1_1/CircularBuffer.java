package com.nimych.mentoring.module1.task1_1_1;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("unchecked")
public class CircularBuffer<T> implements Buffer<T> {

    private T[] array;

    private int tail;

    private int head;

    private int size;

    private Class<T> type;

    public CircularBuffer(int capacity, Class<T> type) {
        if (capacity < 0) {
            throw new RuntimeException("Illegal capacity: " + capacity);
        } else {
            array = (T[]) new Object[capacity];
            tail = 0;
            head = 0;
            size = 0;
            this.type = type;
        }
    }

    @Override
    public void put(T t) {
        if (isFull()) {
            throw new RuntimeException("Unable to add value, Circular Buffer is full");
        } else {
            array[head] = t;
            head = (head + 1) % array.length;
            size++;
        }
    }

    @Override
    public T get() {
        if (isEmpty()) {
            throw new RuntimeException("Unable to get value, Circular Buffer is empty");
        } else {
            T value = array[tail];
            array[tail] = null;
            tail = (tail + 1) % array.length;
            size--;
            return value;
        }
    }

    @Override
    public Object[] toObjectArray() {
        Object[] objectArray = new Object[size];
        for (int i = 0; i < objectArray.length; i++) {
            objectArray[i] = array[getIndex(i)];
        }
        return objectArray;
    }

    @Override
    public T[] toArray() {
        T[] copy = (T[]) Array.newInstance(type, size);
        System.arraycopy(toObjectArray(), 0, copy, 0, size);
        return copy;
    }

    @Override
    public List<T> asList() {
        return Arrays.asList(toArray());
    }

    @Override
    public void addAll(List<? extends T> toAdd) {
        int listSize = toAdd.size();
        if (listSize > (array.length - size)) {
            throw new RuntimeException("There is no enough space to add this list");
        } else {
            for (T value : toAdd) {
                put(value);
            }
            size += listSize;
        }
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        for (int j = 1; j < size; j++) {
            T key = array[getIndex(j)];
            int i = (array.length + j - 1) % array.length;
            while ((i > -1) && (comparator.compare(array[getIndex(i)], key) < 0)) {
                array[getIndex(i + 1)] = array[getIndex(i)];
                i--;
            }
            array[getIndex(i + 1)] = key;
        }
    }

    private int getIndex(int increment) {
        return (tail + increment) % array.length;
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
