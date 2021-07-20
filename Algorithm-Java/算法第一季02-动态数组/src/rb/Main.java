package rb;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(10);
        list.add(22);

        Assert.test(list.size() == 3);
        Assert.test(list.get(0) == 10);

//        ArrayList<Person> list = new ArrayList<>();
//        list.add(new Person(1, "PegPig"));
//        list.add(new Person(3, "Flower"));
//        list.add(new Person(29, "Riber"));
//        list.add(null);
//        System.out.println(list.indexOf(null));
//        list.clear();
//
//        // 提醒JVM进行垃圾回收
//        System.gc();
    }

    static void test() {
        // int -> Integer
        // 所有的类 最终都继承java.lang.Object

        // new是向堆空间申请内存
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person(1, "PegPig"));
        list.add(new Person(3, "Flower"));
        list.add(new Person(29, "Riber"));
        System.out.println(list);
        list.clear();
        list.add(new Person(30, "Red"));


        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(3);
        list2.add(29);
        System.out.println(list2);


//        list.add(99);
//        list.add(88);
//        list.add(11);
//        list.add(20);
//        list.remove(0);
//        list.add(list.size(), 100);
//        list.set(2, 0);
//        if (list.get(2) != 0) {
//            throw  new IllegalArgumentException("测试不通过!");
//        }
//        for (int i = 0; i < 10; i++) {
//            list.add(i);
//        }
    }
}
