package com.rb;
import com.rb.file.Files;
import com.rb.printer.BinaryTreeInfo;
import com.rb.printer.BinaryTrees;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        test1();
    }

    static void test1() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 1
        };
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        };
        BinaryTrees.println(bst, BinaryTrees.PrintStyle.LEVEL_ORDER);

        System.out.println(bst.isComplete());

//        System.out.println(bst.height());
    }

}
