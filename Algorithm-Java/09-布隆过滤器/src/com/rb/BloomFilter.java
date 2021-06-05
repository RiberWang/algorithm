package com.rb;

public class BloomFilter <T> {
    /*
    * 二进制向量的长度（一共有多少个二进制位）
    * */
    private int bitSize;

    /*
    * 二进制向量 一个long是64位
    * */
    private  long[] bits;

    /*
    * 哈希函数的个数
    * */
    private int hashSize;

    /*
    * n 数据规模
    * p 误判率 取值范围[0, 1]
    * */
    public BloomFilter(int n, double p) {
        if (n <= 0 || p <= 0 || p >= 1) {
            throw new IllegalArgumentException("参数错误");
        }

        double ln2 = Math.log(2);
        // 求出二进制向量的长度
        bitSize = (int) (-(n * Math.log(p)) / (ln2 * ln2));

        // 求出哈希函数的个数
        hashSize = (int) (bitSize * ln2 / n);

        System.out.println("bitSize:" + bitSize);
        System.out.println("hashSize:" + hashSize);
    }


    /*
    * 添加元素
    * */
    public void put(T value) {

    }

    /*
    * 判断一个元素是否存在
    * */
    public boolean contains(T value) {
        return false;
    }
}
