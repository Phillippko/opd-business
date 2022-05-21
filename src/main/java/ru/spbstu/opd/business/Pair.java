package ru.spbstu.opd.business;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Pair<T, E> {
    private T first;
    private E second;

    public static <T,E> Pair<T, E> of(T key, E value) {
        return new Pair<>(key, value);
    }

    public E getSecond() {
        return this.second;
    }

    public T getFirst() {
        return this.first;
    }
}

