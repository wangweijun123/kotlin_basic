package com.wangweijun.myapplication.zhangtao.unit12;

import org.junit.Test;

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/05/17 12:08
 * version: 1.0
 * desc   :
 */
public class Main3 {

    @Test
    public void  testPlatformType() {
        // java 调用 java方法, 标注不为空的参数，基本上无用，还是可以传null，编译器不报错
        String notNullString = NullJava.getNotNullString("Hey,");
        String notNullString2 = NullJava.getNotNullString(null);
    }


}
