package com.wangweijun.myapplication.unit1;

import static java.lang.System.out;

import org.junit.Test;

import java.io.File;
import java.nio.charset.Charset;

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
}
