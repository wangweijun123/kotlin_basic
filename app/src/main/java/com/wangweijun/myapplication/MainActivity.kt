package com.wangweijun.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

}