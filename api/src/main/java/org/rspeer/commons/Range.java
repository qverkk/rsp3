package org.rspeer.commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

/**
 * A numeric range, only the minimum and maximum values are provided.
 */
public class Range {

    private final int min;
    private final int max;

    private Range(int min, int max) {
        this.min = Math.min(min, max);
        this.max = Math.max(min, max);
    }

    public static Range ordered(int a, int b) {
        return a > b ? Range.of(b, a) : Range.of(a, b);
    }

    public static Range of(int min, int max) {
        return new Range(min, max);
    }

    public <T> List<T> map(IntFunction<T> mapper) {
        List<T> mapped = new ArrayList<>();
        for (int i = min; i < max; i++) {
            mapped.add(mapper.apply(i));
        }
        return mapped;
    }

    public int minimum() {
        return min;
    }

    public int maximum() {
        return max;
    }

    public boolean within(int value) {
        return value <= max && value >= min;
    }

    public int random() {
        return Random.nextInt(min, max);
    }

    public int[] fill() {
        int[] array = new int[max - min];
        Arrays.setAll(array, index -> min + index);
        return array;
    }
}
