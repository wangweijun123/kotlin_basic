package com.wangweijun.myapplication.zhangtao.unit12;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

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
     * @param s
     * @return
     */
    @NotNull
    public static String getNotNullString(@NotNull String s) {
        return "Hello World.";
    }
}
