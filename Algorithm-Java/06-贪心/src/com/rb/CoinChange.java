package com.rb;

import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        coinChange(new Integer[] {25, 10, 5, 1}, 41);

        // ◼ 贪心策略并不一定能得到全局最优解
        coinChange(new Integer[] {25, 20, 5, 1}, 41);
    }

    static  void coinChange(Integer[] faces, int money) {
        // ◼假设有 25 分、20 分、5 分、1 分的硬币，现要找给客户 41 分的零钱，如何办到硬币个数最少?
        if (faces.length <= 0) return;
        // 零钱兑换
        Arrays.sort(faces, (Integer f1, Integer f2) -> f2 - f1);
        int coins = 0, i = 0;

        while (i < faces.length) {
            if (money < faces[i]) {
                i++;
                continue;
            }

            System.out.println("选择的硬币是：" + faces[i]);
            money -= faces[i];
            coins++;
        }

        System.out.println("一共找了" + coins + "枚硬币");
    }

    static void coinChange1() {
        // ◼假设有 25 分、10 分、5 分、1 分的硬币，现要找给客户 41 分的零钱，如何办到硬币个数最少?
        int[] faces = {50, 25, 10, 5, 1};
        Arrays.sort(faces);
        int money = 67, coins = 0;

        for (int i = faces.length - 1; i >= 0; i--) {
            if (money < faces[i]) {
                continue;
            }

            System.out.println("选择的硬币是：" + faces[i]);
            money -= faces[i];
            coins++;
            i = faces.length;
        }

        System.out.println("一共找了" + coins + "枚硬币");
    }

}
