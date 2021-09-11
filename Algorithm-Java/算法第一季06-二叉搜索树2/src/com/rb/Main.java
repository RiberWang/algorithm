package com.rb;
import com.rb.file.Files;
import com.rb.printer.BinaryTreeInfo;
import com.rb.printer.BinaryTrees;
import com.rb.tree.BST;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
//        test1();
//        test2();

        /**
         * Java中的匿名类 类似于iOS的Block JS的闭包（function）
         */
        BST<Person> bst1 = new BST<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return 0;
            }
        });
        bst1.add(new Person(12));
        bst1.add(new Person(15));

        BST<Person> bst2 = new BST<>(new PersonComparator());
        bst2.add(new Person(12));
        bst2.add(new Person(15));

        BST<Person> bst3 = new BST<>(new PersonComparator2());
        bst3.add(new Person(12));
        bst3.add(new Person(15));

//        test3();
//        test4();
//        test5();
        test7();

    }

    static  void test7() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        };
        BinaryTrees.println(bst, BinaryTrees.PrintStyle.LEVEL_ORDER);
//        bst.remove(1);
//        bst.remove(3);
        bst.remove(7);
        BinaryTrees.println(bst);
    }

    static void test6() {
        BinaryTrees.println(new BinaryTreeInfo() {
            @Override
            public Object root() {
                return "A";
            }

            @Override
            public Object left(Object node) {
                if (node.equals("A")) return "B";
                if (node.equals("B")) return "D";
                return null;
            }

            @Override
            public Object right(Object node) {
                if (node.equals("A")) return "C";
                if (node.equals("C")) return "E";
                return null;
            }

            @Override
            public Object string(Object node) {
                return node.toString() + "_";
            }
        });
    }

    // 前序、中序、后序、层序遍历
    static void test5() {
        Integer data[] = new Integer[]{
                7, 4, 2, 1, 3, 5, 9, 8, 11, 10, 12
        };
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        }

        BinaryTrees.println(bst);
//        bst.preorderTraversal();
//        bst.inorderTraversal();
//        bst.postorderTraversal();
//        bst.levelOrderTraversal();
        bst.preorderTraversalWithVisitor(new BST.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(element + " ");

                return element == 2 ? true : false;
            }
        });
        System.out.println();

        bst.inorderTraversalWithVisitor(new BST.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(element + " ");

                return element == 4 ? true : false;
            }
        });
        System.out.println();

        bst.postorderTraversalWithVisitor(new BST.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(element + " ");

                return element == 4 ? true : false;
            }
        });
        System.out.println();

        bst.levelOrder(new BST.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print(element + " ");

                return element == 9 ? true : false;
            }
        });
        System.out.println();
    }

    static void test4() {
        BST<Person> bst = new BST<>();
        bst.add(new Person(10, "Jack"));
        bst.add(new Person(12, "Rose"));
        bst.add(new Person(6, "Jim"));

        bst.add(new Person(10, "Michael"));

        BinaryTrees.println(bst);
    }

    static void test3() {
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < 40; i++) {
            bst.add((int)(Math.random() * 100));
        }
        BinaryTrees.println(bst);

        // 写入到文件
        String str = BinaryTrees.printString(bst);
        str += "\n";
        Files.writeToFile("/Users/riber/Downloads/1.txt", str);
    }

    static void test2() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 12, 1
        };
        BST<Person> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(new Person(data[i]));
        };
        BinaryTrees.println(bst);

        BST<Person> bst2 = new BST<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        for (int i = 0; i < data.length; i++) {
            bst2.add(new Person(data[i]));
        };
        BinaryTrees.println(bst2);
    }

    static void test1() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3, 1
        };
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < data.length; i++) {
            bst.add(data[i]);
        };
        BinaryTrees.println(bst, BinaryTrees.PrintStyle.LEVEL_ORDER);

        System.out.println(bst.isComplete());

//        System.out.println(bst.height());
    }

    private static class PersonComparator implements Comparator<Person> {
        @Override
        public int compare(Person e1, Person e2) {
            return e1.getAge() - e2.getAge();
        }
    }

    private static class PersonComparator2 implements Comparator<Person> {
        @Override
        public int compare(Person e1, Person e2) {
            return e2.getAge() - e1.getAge();
        }
    }}
