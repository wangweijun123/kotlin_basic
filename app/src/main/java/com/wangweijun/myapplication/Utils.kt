/**
 * kotlin 是jvm上的一种语言
 * 在java能用文件名加kt来调用函数  UtilsKt.echo("wangweij");
 * 猜想 1 kotlin文件被编译成了public class,
 *
 * kotlin文件中顶级函数被编译成了pubulic static 类方法
 * 而 Kotlin class 中fun函数还是实例方法
 * 2 函数编译成了 fun echo(str: String)  -> public static xxx echo(String str) ;
 *
 * 疑问: kotlin 文件反编译变成了啥
 *
 * Utils.kt  --变成了 ->  class public final Lcom/wangweijun/myapplication/UtilsKt;
 * myname   --->  field private static final myname
 * 同时生成了方法: public static final getMyname()Ljava/lang/String;
 * fun echo(str: String)  ->  public static final echo(Ljava/lang/String;)V
 *
 */
package com.wangweijun.myapplication

//val name = "wang"  在编译期间 error, 因为在Test.kt也定义了变量 name
val myname = "wangduan"
fun echo(str: String) {
    println(str)
}

/**
 * 调用带有默认值的参数,被编译成 public static静态方法
 *  public static final void echo2(@NotNull String str)
 */
fun echo2(str: String = "xxxx") {
    println(str)
}

/**
 * 函数重载
 */
fun echo2(age: Int, str: String = "xxxx") {
    println(str)
}

/**
 * 如果函数体只有一句话 直接在函数声明后 = 函数体语句
 */
fun echo3(age: Int, str: String = "xxxx") = println(str)