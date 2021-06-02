package com.rb;

public class Queens3 {
    public static void main(String[] args) {
        System.out.println(Byte.SIZE);
        System.out.println(Short.SIZE);
        // 位运算
        int n = 125; // 01111101
        for (int i = 0; i < 8; i++) {
            int result = n & (1 << i);
            System.out.println(i + ":" + (result != 0));
        }
        int col = 2;
        int v = 1 << col; // 左移col位
        System.out.println(Integer.toBinaryString(n));

        new Queens3().placeEightQueens(); // 只针对八皇后优化 位运算压缩空间复杂度
    }

    /*
     * 打印使用 数组索引是行号 数组元素是列号
     * */
    int[] queens;

    /*
    * 标记某一列是否摆放皇后
    * */
    byte cols;

    /*
    * 标记着某一对角线（斜线）是否有皇后（左上角->右下角）
    * */
    short leftTop;

    /*
    * 标记着某一对角线是否有皇后（右上角->左下角）
    * */
    short rightTop;

    // 一共有多少种摆法
    int ways;
    void placeEightQueens() {
        queens = new int[8];

        place(0);

        System.out.println("8皇后一共有" + ways + "种摆法");
    }

    /*
    * 从第 row 行开始摆放皇后
    * */
    void place(int row) {
        if (row == 8) {
            ways++;
            show();
            return;
        }
        for (int col = 0; col < 8; col++) {
            int cv = 1 << col;
            if ((cols & cv) != 0) continue;
            int lv = 1 << (row - col + 8 - 1);
            int rv = 1 << (row + col);
            if ((leftTop & lv) != 0)  continue;
            if ((rightTop & rv) != 0) continue;

            // 可以摆放皇后
            queens[row] = col;
            cols |= cv;
            leftTop |= lv;
            rightTop |= rv;

            // 回溯
            place(row + 1);

            /*
            * ~取反 然后&
            * */
            cols &= ~cv;
            leftTop &= ~lv;
            rightTop &= ~rv;
        }
    }

    void show() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (queens[row] == col) {
                    System.out.print("1 ");
                }
                else {
                    System.out.print("0 ");
                }
            }
            System.out.print("\n");
        }
        System.out.println("----------------------------------------------");
    }
}
