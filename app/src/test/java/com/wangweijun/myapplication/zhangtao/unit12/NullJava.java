package com.wangweijun.myapplication.zhangtao.unit12;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/03/20 5:44 下午
 * version: 1.0
 * desc   :
 */

// Java 代码

public class NullJava {
    public static String getMsg(String s) {
        return s + "Kotlin";
    }

    /**
     * Nullable 参数可空类型，返回值也可空类型
     * @param s
     * @return
     */
    @Nullable
    public static String getNullableString(@Nullable String s) {
        return s + "Kotlin";
    }

    /**
     * NotNull 参数不为空类型，返回值也是不为空类型
     *
     * @param s
     * @return
     */
    @NotNull
    public static String getNotNullString(@NotNull String s) {
        return "Hello World.";
    }


    @Test
    public void testNotnxx() {
        // java 调用java，就算参数定位为notnull，也可以传null
        // 但是如果是kotlin调用java，就会报错，build error
        String s = getNotNullString(null);
        System.out.println(s);
    }
}
