package com.rb;

public class Main {
    public static void main(String[] args) {
        BloomFilter<Integer> bg = new BloomFilter<>(1_0000_0000, 0.01);
    }
}
