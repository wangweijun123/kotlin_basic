package com.wangweijun.myapplication.zhangtao.unit05.last

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/10 4:28 下午
 * version: 1.0
 * desc   : PPPPPerson2 拥有静态内部类的一个实例
 */
class PPPPPerson2 {
    // companion object 与 object 区别： companion object外部调用更加方便而已
    // InnerSingleton 这个名字取不取名是一样的
    companion object InnerSingleton {
        fun foo() {} // 注意这里还是实例方法

        /**
         * 伴生对象：加载外部类的同时，生成来内部类的对象，因为内部类是 static的变量
         * 1 注意这里还是实例方法
         * 2 但是会在外部类中生成同名的静态方法 public static final void foo2()
         * 3 外部类会生成内部类的对象，
         * public static final PPPPPerson2.InnerSingleton InnerSingleton
         *  = new PPPPPerson2.InnerSingleton((DefaultConstructorMarker)null);
         * 通过这个对象来调用内部类的方法
         */
        @JvmStatic
        fun foo2() {}
    }
}