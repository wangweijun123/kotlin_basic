package com.wangweijun.myapplication.zhangtao.debug;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/06/07 11:49
 * version: 1.0
 * desc   :
 */
public class DebuTest {

    @Test
    public void testDebug() {
        boolean flag = true;
        if (flag) {
            System.out.println("if flag = " + flag);
        } else {
            System.out.println("else flag = " + flag);
        }
    }
    @Test
    public void testDebug2() {
        List<DebugUser> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(new DebugUser("name"+i, "girl"));
        }

        for (DebugUser user: list) {
            System.out.println(user);
        }
    }

    @Test
    public void testDebug3() {
        boolean add = add();
        System.out.println("add="+add);
    }

    public boolean add() {
        boolean add = false;
        System.out.println("addd");
        return add;
    }
}

class DebugUser {
    private String name;
    private String sex;

    public DebugUser(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "DebugUser{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
