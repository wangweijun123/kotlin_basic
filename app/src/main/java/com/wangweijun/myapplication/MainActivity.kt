package com.wangweijun.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlin.reflect.KClass

/**
 * AppCompatActivity() 表示调用父类的无参构造函数
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    init {
        println("init闭包在构造函数中的被调用")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.bt).setOnClickListener(this)
    }

    fun javaCallKotlin(view: View) {
        Test.sayMessage("hi kotlin obj in kotlin")
        Test.jvmStaticTest("hi jvmStaticTest in kotlin ")
        testClass(JavaClass::class.java)
        testClass(KotlinClass::class)
        // 注意在kotlin中传java的class的写法 SecondActivity::class.java
        startActivity(Intent(applicationContext, SecondActivity::class.java))
    }

    fun companionOBJ(view: View) {
        val ka = KotlinA()
        ka.putNumber(23232)

        KotlinA.a.putNumber(123)
        printLen(null)


    }

    fun extendsMethodAndVar(view: View) {
        val dog = Dog()
        // 结果system.out: animal
        // printName 是父类的方法, dog 会被强制转化成 animal对象
        // 扩展函数是静态的给类添加函数, 不具备多态效应
        dog.printName(dog)
    }

    fun gaoJie(view: View) {
        // {}里面会被编译成一个对象
        onlyif(true) {
            println("这是高阶函数")
        }
    }

    fun testClass(clz: Class<JavaClass>) {
        println(clz.simpleName)
    }

    fun testClass(clz: KClass<KotlinClass>) {
        println(clz.simpleName)
    }

    /**
     * 参数可以为NULL的String类型, 返回值也可以为NULL的String类型
     */
    fun printLen(str: String?): String? {
        return str
    }

    /**
     * 参数是不可以为NULL的String类型, 返回值也是不可以为NULL的String的类型
     */
    fun printLen2(str: String): String {
        return str
    }

    // 被编译成了 lambda 被编译成了 一个对象
    // public final onlyif(Boolean, ZLkotlin/jvm/functions/Function0;)V
    // inline 修饰高阶函数，减少临时对象的创建
   inline fun onlyif(isDebug: Boolean, bolck: () -> Unit) {
        if (isDebug) {
            bolck()
        }
    }

    override fun onClick(v: View?) {
        println("实现接口的写法")
    }


}