package com.rb;

import java.util.Arrays;

public class Pirate {
    public static void main(String[] args) {
        // ◼ 在北美洲东南部，有一片神秘的海域，是海盗最活跃的加勒比海
        // 有一天，海盗们截获了一艘装满各种各样古董的货船，每一件古董都价值连城，一旦打碎就失去了它的价值
        // 海盗船的载重量为 W，每件古董的重量为 𝑤i，海盗们该如何把尽可能多数量的古董装上海盗船?
        // 比如 W 为 30，𝑤i 分别为 3、5、4、10、7、14、2、11
        int[] weights = {3, 5, 4, 10, 7, 14, 2, 11};
        Arrays.sort(weights);

        int capacity = 30, weight = 0, count = 0;
        for (int i = 0; i < weights.length; i++) {
            int newWeight = weight + weights[i];
            if (newWeight  <= capacity) {
                weight = newWeight;
                count++;
                System.out.println("选的古董重量" + weights[i]);
            }
        }
        System.out.println("一共选了" + count + "件古董");
    }
}
