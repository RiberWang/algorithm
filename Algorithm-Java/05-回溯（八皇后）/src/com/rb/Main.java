package com.rb;

public class Main {
    public static void main(String[] args) {
        new Main().placeEightQueens(4);
    }
    /*
    * 数组索引是行号，数组元素是列号 cols[row] = col
    * */
    int[] cols;

    // 一共有多少种摆法
    int ways;
    void placeEightQueens(int n) {
        if (n < 0) return;
        cols = new int[n];
        place(0);

        System.out.println(n + "皇后一共有" + ways + "种摆法");
    }

    /*
    * 从第 row 行开始摆放皇后
    * */
    void place(int row) {
        if (row == cols.length) {
            ways++;
            show();
            return;
        }
        for (int col = 0; col < cols.length; col++) {
            if (isValid(row, col)) {
                // 在第row行 第row列摆放皇后
                cols[row] = col;

                // 回溯
                place(row + 1);
            }
        }
    }

    /*
    * 判断第row行 第col列是否可以摆放皇后
    * */
    boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            // 第col列已经有皇后
            if (cols[i] == col) return false;
            // 第i行的皇后跟第row行第col列格子处在同一斜线上
            if (row - i == Math.abs(col - cols[i])) return false;
        }
        return true;
    }

    void show() {
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (cols[row] == col) {
                    System.out.print("1 ");
                }
                else {
                    System.out.print("0 ");
                }
            }
            System.out.print("\n");
        }
        System.out.println("-----------");
    }
}
