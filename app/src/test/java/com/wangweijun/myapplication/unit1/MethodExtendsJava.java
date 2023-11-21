package com.wangweijun.myapplication.unit1;

import static java.lang.System.out;

import org.junit.Test;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import kotlin.io.FilesKt;
import kotlin.text.Charsets;

public class MethodExtendsJava {
    @Test
    public void test() {
        File file = new File("src/1.txt");
        // 需要把本身传进去
        String s = FilesKt.readText(file, Charsets.UTF_8);
        out.println(s);
    }

    class A { }
    class B extends A { }
    class C extends B { }
    @Test
    public void testFaxnin() {
        List<A> list1 = new ArrayList<A>();	// work 泛型不变
        list1.add(new A());     // work
        list1.add(new B());     // work
        A a = list1.get(1);		// work
        // List<A> 和 List<B> 不存在型变关系
//        List<A> list2 = new ArrayList<B>();	// 编译错误，泛型不变，也就不支持协变（类似多态）

    }

    @Test
    public void testFaxnin2() {
        // 集合只读、不可写
        List<? extends A> list1 = new ArrayList<B>();	// 协变——父类引用指向子类
//        list1.add(new B()); // build error
//        list1.add(new A()); // build error


//        list1.add(new Object());  // 错误，容器不可写，不能放入任何值（null 除外）
        A a = list1.get(1);	// work 可读，且有泛型

    }

    @Test
    public void testFaxnin3() {
        List<? super B> list = new ArrayList<A>();	// 逆变——子类引用指向父类
//        list.add(new A());	  // 编译错误，集合中放入的元素类型只能为 B 及 B 子类型
        list.add(new B());    // work
        list.add(new C());    // work
        Object object = list.get(0);// work 可读，但无类型都是 Object

    }

}
