package com.wangweijun.myapplication

/**
 * Kotlin 单例的最NB的写法
 *
 * object 与 companion object都是生产了内部类,查看字节码
 *
 * SingleKotlin  编译成了 SingleKotlin.class
 * 项目中kotlin文件被编译的class文件在debug目录下
 * app/build/tmp/kotlin-classes/debug/包名/类名
 * 被编译的class文件junit测试在debugUnitTest 目录下
 * app\build\tmp\kotlin-classes\debugUnitTest\com\wangweijun\myapplication\unit9
 * 直接将xxx.class 拖入 jadex-gui中
 *
 * 0 SingleKotlin -> SingleKotlin.class
 * 1 私有构造函数 private constructor()
 * 2 定义object Holder
 * 3 定义 companion object
 */
class SingleKotlin private constructor(){
    // public final void test()
    // 还是实例方法, 如果是在kotlin文件中定义的顶级函数是生产了静态的public static
    fun test() {
        println("test SingleKotlin ")
    }

    /**
     * 伴生对象作用：
     * 1 Companion内部类
     * 2 Companion的静态实例
     * 3 伴生对象中函数还是实例函数(除非加@JVMStatic)
     * companion object被编译成了 内部类 static class Companion,
     * 同时在外部类中定了一个实例
     *
     * public static final Companion Companion = new Companion(null);
     * public static final class Companion {
        @NotNull
        public final SingleKotlin get() { SingleKotlin instance = SingleKotlin.Holder.INSTANCE.getInstance();
        String str = "get instance = " + instance + ' '; int i = 0; System.out.println(str);
        return instance;
        }
        }
     * 一定是写在一个类的内部
     */
    companion object {
        fun get(): SingleKotlin {
            val instance = Holder.instance
            println("get instance = $instance ")
            return instance
        }
    }

    /**
     * object Holder 被编译成了静态内部类
     * public static final class Holder
        {

        @NotNull
        public static final Holder INSTANCE = new Holder();

        @NotNull
        private static final SingleKotlin instance = new SingleKotlin(null);

        @NotNull
        public final SingleKotlin getInstance() { return instance; }

        }
     */
    /**
     * object Holder 被编译成了静态内部类class
     *
     */
    object Holder {
        // private static final SingleKotlin instance = new SingleKotlin(null);
        // 私有的静态的变量
        val instance = SingleKotlin()
    }


}