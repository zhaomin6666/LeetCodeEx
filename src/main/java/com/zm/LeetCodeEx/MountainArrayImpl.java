package com.zm.LeetCodeEx;

import java.util.Arrays;

public class MountainArrayImpl implements MountainArray {
    private int[] arr;
    private int opGetTimes = 0;

    public MountainArrayImpl(int[] input) {
        arr = Arrays.copyOf(input, input.length);
    }

    @Override
    public int get(int index) {
        opGetTimes++;
        return arr[index];
    }

    @Override
    public int length() {
        return arr.length;
    }

    @Override
    public int getOpGetTimes() {
        return opGetTimes;
    }
}
