package com.core.genericity;

/**
 * 泛型类
 * */
public class Pair<T> {
    private T min;
    private T max;

    public Pair(T min, T max) {
        this.min = min;
        this.max = max;
    }

    public T getMin() {
        return min;
    }

    public void setMin(T min) {
        this.min = min;
    }

    public T getMax() {
        return max;
    }

    public void setMax(T max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
