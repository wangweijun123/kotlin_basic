package com.wangweijun.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.wangweijun.myapplication.tip.RecycleViewActivity
import com.wangweijun.myapplication.tip.RecycleViewDiffUtilActivity
import com.wangweijun.myapplication.tip.RecycleViewMulitActivity
import com.wangweijun.myapplication.tip.TipTimeActivity
import com.yqritc.recyclerviewmultipleviewtypesadapter.sample.MulitTypeUseBaseActivity
import kotlinx.coroutines.*
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
//        Test() // build failed object声明的类是不能实例化的, 因为编译生成了私有构造, 生成了一个静态的实例
        Test.sayMessage("hi kotlin obj in kotlin")
        Test.jvmStaticTest("hi jvmStaticTest in kotlin ")
        testClass(JavaClass::class.java)
        testClass(KotlinClass::class)
        // 注意在kotlin中传java的class的写法 SecondActivity::class.java
        startActivity(Intent(applicationContext, SecondActivity::class.java))
    }

    fun jumpTipTime(view: View) {
        startActivity(Intent(applicationContext, TipTimeActivity::class.java))
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
        // {}里面会被编译成一个对象Function{num}, 函数的最后一个参数lambda的话,可以把{}写到()小括号之外
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

    // 被编译成了 lambda(lambda就是一对{}, {参数 -> 代码}) 被编译成了 一个对象
    // public final onlyif(Boolean, ZLkotlin/jvm/functions/Function0;)V
    // block lambda表达式, 参数为空, 返回Unit也为空
    // 如果使用inline 修饰高阶函数，减少临时对象的创建,

    // inline的作用： ####拆解方法的调用为语句的调用####
    // inline的作用： ####拆解方法的调用为语句的调用####
    // inline的作用： ####拆解方法的调用为语句的调用####
    // 也就是把函数调用使用inline修饰的函数的代码copy到了调用函数,反编译才能看出来哦
    // inline 一般使用在高阶函数(函数为参数的函数)
   inline fun onlyif(isDebug: Boolean, bolck: () -> Unit) {
        if (isDebug) {
            bolck()
        }
    }

    override fun onClick(v: View?) {
        println("实现接口的写法")
    }

    fun tesetGlobalScope(view: View) {
        println("tesetGlobalScope ...  " + Thread.currentThread().name + ", id="+Thread.currentThread().id)
        GlobalScope.launch(Dispatchers.Main) {
            // 这里(外层协程)实在主线程执行，第三句执行
            println("GlobalScope launch...  " + Thread.currentThread().name + ", id="+Thread.currentThread().id)
            // 里面的携程是在子线程操作 Dispatchers.Default 这里的参数很重要
            // 携程的异步调用实质是 callback
            // 使用携程异步编程方案
            val result = withContext(Dispatchers.Default) {
                // 这里实在子线程执行 withContext DefaultDispatcher-worker-1, id=111918
                println("withContext " + Thread.currentThread().name + ", id="+Thread.currentThread().id)
                delay(10_000)
                // lambda表达式的最后一行是返回值
                "xxxxxx"
            }
            // 当然这里也是在主线程执行,所以可以操作UI(等待内层携程执行完成才执行)
            println("result = $result")
            findViewById<TextView>(R.id.result).text = result
        }
        // 这里很快就执行完，在第二句执行
        println("tesetGlobalScope finished " + Thread.currentThread().name + ", id="+Thread.currentThread().id)
    }

    fun testWithContext() {
        // 必须在协程或suspend函数中调用,否则编译出错
        /*withContext(Dispatchers.IO) {

        }*/
    }

    fun startRecycleViewAct(view: android.view.View) {
        startActivity(Intent(applicationContext, RecycleViewActivity::class.java))
    }

    fun startRecycleViewDiffAct(view: android.view.View) {
        startActivity(Intent(applicationContext, RecycleViewDiffUtilActivity::class.java))
    }

    fun startRecycleViewMulitAct(view: android.view.View) {
        startActivity(Intent(applicationContext, RecycleViewMulitActivity::class.java))
    }
    fun startRecycleViewMulitAct2(view: android.view.View) {
        startActivity(Intent(applicationContext, MulitTypeUseBaseActivity::class.java))
    }


}