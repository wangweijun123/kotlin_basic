package com.wangweijun.myapplication.zhangtao.unit10;

import org.junit.Test;

import java.util.ArrayList;

public class TestFaxing {
     class Animal {
         public void eat() {
             System.out.println("Animal eat");
        }
    }

    class Cat extends Animal {
        public void eat() {
            System.out.println("Cat eat");
        }
    }

    class Dog extends Animal {
        public void eat() {
            System.out.println("dog eat");
        }
    }

    @Test
    public void testFanxing() {
        ArrayList<Animal> animals = new ArrayList();
        animals.add(new Dog());
        animals.add(new Cat());
        for (int i = 0; i <animals.size(); i++) {
            animals.get(i).eat();
        }
    }

    @Test
    public void testFanxing2() {
         // 需要父类集合，传入子类集合,编译失败
        // ArrayList<Animal> 与 ArrayList<Cat>认为是两个类型
//        ArrayList<Cat> animals = new ArrayList();
        ArrayList<Animal> animals = new ArrayList();
        animals.add(new Cat());
        foo(animals);
    }

    // 需要父类集合，传入子类集合
    public void foo(ArrayList<Animal> list) {
        // 出错，Cat集合不能存Dog对象
        list.add(new Dog());
        // 通过
//        Animal animal = list.get(0);// 取出的Cat对象
        for (int i = 0; i <list.size(); i++) {
            list.get(i).eat();
        }
    }

    @Test
    public void testFanxing3() {
        // 需要子类集合，传入父类集合,编译失败
        ArrayList<Animal> animals = new ArrayList();
        animals.add(new Dog());
//        foo2(animals);
    }
    // 需要子类集合，传入父类集合
    public void foo2(ArrayList<Cat> list) {
        // 出错，Cat集合不能存Dog对象
        list.add(new Cat());
        // 通过
//        Animal animal = list.get(0);// 取出的Cat对象
        for (int i = 0; i <list.size(); i++) {
            list.get(i).eat();
        }
    }
}
