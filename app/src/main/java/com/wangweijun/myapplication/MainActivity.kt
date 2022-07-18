package com.wangweijun.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.wangweijun.Library1Util
import com.wangweijun.myapplication.mycoroutines.last.getLoginInfo
import com.wangweijun.myapplication.mycoroutines.last.getLoginInfoNotDispatcher
import com.wangweijun.myapplication.mycoroutines.last.getLoginInfoNotSuspend
import com.wangweijun.myapplication.tip.RecycleViewActivity
import com.wangweijun.myapplication.tip.RecycleViewDiffUtilActivity
import com.wangweijun.myapplication.tip.RecycleViewMulitActivity
import com.wangweijun.myapplication.tip.TipTimeActivity
import com.wangweijun.myapplication.web.WebActivity
import com.yqritc.recyclerviewmultipleviewtypesadapter.sample.MulitTypeUseBaseActivity
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.reflect.KClass

/**
 * AppCompatActivity() 表示调用父类的无参构造函数
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    init {
        println("init闭包在构造函数中的被调用")
    }

    lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        findViewById<Button>(R.id.bt).setOnClickListener(this)
        findViewById<Button>(R.id.bt).setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                TODO("Not yet implemented")
            }
        })
        // lambda表达式 = { gotoPre() }这一块
        findViewById<Button>(R.id.bt).setOnClickListener {
            gotoPre()
        }
        // 与上面等价
        findViewById<Button>(R.id.bt).setOnClickListener { v: View ->
            gotoPre()
        }

        mainActivity = this
        setMultiColor()
        testRetunLamb()
        testlibray()
    }

    fun testlibray() {
        findViewById<View>(R.id.testlibray).setOnClickListener {
            Library1Util.test1()
        }
    }

    fun testRetunLamb() {
        findViewById<Button>(R.id.testlamba).setOnClickListener{
            val re =  try {
                1/0
            } catch (e: Exception) {
                Log.i("wwwww", "Exception")
                100
//                return@setOnClickListener // click 方法结束
            }

            Log.i("wwwww", "xxx = $re")
            return@setOnClickListener
            Log.i("wwwww", "xxx")
        }
    }

    fun gaojie() {
        findViewById<Button>(R.id.bt).setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                gotoPre()
            }
        })
        findViewById<Button>(R.id.bt).setOnClickListener(View.OnClickListener{ v: View? ->
            gotoPre()
            Log.i("ww", "xxx")
        })

        findViewById<Button>(R.id.bt).setOnClickListener({
            gotoPre()
        })

        findViewById<Button>(R.id.bt).setOnClickListener{
            gotoPre()
            Log.i("ww", "xxx")
            return@setOnClickListener
            Log.i("ww", "xxx")
        }


    }



    fun gotoPre() {}

    // single abstract method
    fun asmTest() {
        // 转换前：
//        public void setOnClickListener(OnClickListener l)

            // 转换后：
//        fun setOnClickListener(l: (View) -> Unit)
        // 实际上是这样：
//        fun setOnClickListener(l: ((View!) -> Unit)?)
    }

    private fun setMultiColor() {
//        val totalCount = 3
//        val totalPrice = 33.8
//        val totalCountStr = totalCount.toString() + ""
//        val totalPriceStr = String.format("%.2f", totalPrice)
//
//        val str = ("共 " + totalCountStr + "件商品，"
//                + "已付款￥" + totalPriceStr + "元")
//        val style = SpannableStringBuilder(str)
//        style.setSpan(
//            ForegroundColorSpan(Color.RED),
//            1,
//            totalCountStr + 1,
//            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//        )
//        style.setSpan(
//            ForegroundColorSpan(Color.RED),
//            totalCountStr + 9,
//            str.length - 1,
//            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
//        )

        val content = Html.fromHtml(getString(R.string.text_multi_color))
        findViewById<TextView>(R.id.appeal_tv).text = content
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


    fun startWebActivity(view: android.view.View) {
        val pdfUrl = "https://demo.codeseasy.com/downloads/CodesEasy.pdf"
        val intent = Intent(applicationContext, WebActivity::class.java)
        intent.putExtra("pdf_url", pdfUrl)
        startActivity(intent)
    }

    fun startJsonTest(view: android.view.View) {
        val requestParams = """ 
{
  "type": "onfido",
  "asyncReqCount": "10",
  "asyncReqSeconds": "3",
  "token": "xxxx",
  "enableNFC": "true",
  "face_type": "video",
  "documentType": "['ID_CARD', 'PASSPORT', 'DRIVERS_LICENSE']",
  "countryCode": "GB"
}
"""
        val jsonObject = JSONObject(requestParams)
        val type = jsonObject.optString("type")
        println("type = $type")
        val optBoolean = jsonObject.optBoolean("enableNFC")
        println("enableNFC = $optBoolean")
        val documentTypeJson = jsonObject.optString("documentType")

        val jsonArray = JSONArray(documentTypeJson)

        val length = jsonArray.length()
        println("lenght = ${length}")
        for (index in 0 until length) {
            println("index = ${index}, value ${jsonArray[index]}")
        }
    }

    fun startJsonTest2(view: android.view.View) {
        val requestParams = """ 
                                {
                                  "type": "onfido",
                                  "asyncReqCount": "10",
                                  "asyncReqSeconds": "3",
                                  "token": "xxxx",
                                  "enableNFC": true,
                                  "face_type": "video",
                                  "documentType": ['ID_CARD', 'PASSPORT', 'DRIVERS_LICENSE'],
                                  "countryCode": "GB"
                                }
                             """
        val jsonObject = JSONObject(requestParams)
        val type = jsonObject.optString("type")
        println("type = $type")
        val optBoolean = jsonObject.optBoolean("enableNFC")
        println("enableNFC = $optBoolean")
        val optJSONArray = jsonObject.optJSONArray("documentType")
        val length = optJSONArray.length()
        println("lenght = ${length}")
        for (index in 0 until length) {
            println("index = ${index}, value ${optJSONArray[index]}")
        }
    }


    fun jumpCoroutineActivity(view: android.view.View) {
        startActivity(Intent(applicationContext, CoroutineActivity::class.java))
    }

    /**
     * 在android中使用携程scrcope，你提供了LifecycleScope、ViewModelScope。如果是其他端，则可以自己创建CoroutineScope。
     * @param view View
     */
    fun testlifecycleScope(view: android.view.View) {
        Log.d("wangweijundx", "generateQrcodeAndDisplay id = ${Thread.currentThread().id}, name=${Thread.currentThread().name}")
        lifecycleScope.launch(Dispatchers.IO) {
            Log.d("wangweijundx","delay 5 s")
            delay(5000)
            Log.d("wangweijundx", "launch id = ${Thread.currentThread().id}, name=${Thread.currentThread().name}")
            withContext(Dispatchers.Main) {
//                ivShareQrcode?.setImageBitmap(qrBitmap)
                Log.d("wangweijundx", "withContext id = ${Thread.currentThread().id}, name=${Thread.currentThread().name}")
            }
        }
        // 最后一句运行在 launch block前面哦
        Log.d("wangweijundx", "testlifecycleScope finished")
    }

    /**
     *
     * @param view View
     */

    fun testlifecycleScope2(view: android.view.View) {
        Log.d("wangweijundx", "generateQrcodeAndDisplay id = ${Thread.currentThread().id}, name=${Thread.currentThread().name}")
        // 创建一个协程,注意block块是函数运行完之后运行的哦
        lifecycleScope.launch(Dispatchers.Main) {
            // 底下这句在main thread
            Log.d("wangweijundx", "launch id = ${Thread.currentThread().id}, name=${Thread.currentThread().name}")
            // 挂起函数，并不是挂起或者阻塞当前调用线程，
            // 每一次从主线程到 IO 线程，都是一次协程挂起
            // 每一次从 IO 线程到主线程，都是一次协程恢复。
            // 粗略的说， 等号=左边运行main thread，等号右边运行在io thread
            val msg = getLoginInfo()
            Log.d("wangweijundx", "withContext id = ${Thread.currentThread().id}, name=${Thread.currentThread().name}, msg = $msg")
        }
        // 最后一句运行在 launch block前面哦(不管block块代码指定在哪个线程)
        Log.d("wangweijundx", "testlifecycleScope2 finished")
    }

    fun testlifecycleScopeNotDispatcher(view: android.view.View) {
        Log.d("wangweijundx", "testlifecycleScopeNotDispatcher id = ${Thread.currentThread().id}, name=${Thread.currentThread().name}")
        // block 中的代码执行在 最后一行代码运行完之后, 虽然都是在同一个线程
        lifecycleScope.launch(Dispatchers.Main) {
            // 底下这句在main thread
            Log.d("wangweijundx", "launch id = ${Thread.currentThread().id}, name=${Thread.currentThread().name}")
            val msg = getLoginInfoNotDispatcher()
            Log.d("wangweijundx", "withContext id = ${Thread.currentThread().id}, name=${Thread.currentThread().name}, msg = $msg")
        }
        Log.d("wangweijundx", "after launch id = ${Thread.currentThread().id}, name=${Thread.currentThread().name}");
    }

    fun testlifecycleScopeNotSuspend(view: android.view.View) {
        Log.d("wangweijundx", "getLoginInfoNotSuspend id = ${Thread.currentThread().id}, name=${Thread.currentThread().name}")
        // 1 launch创建了一个协程
        // 2 指定 launch block 块运行的线程(所以这里要注意耗时操作)
        // 3 很重要, 函数运行完(也就是在最后一句代码前) 才会运行 launch block
        // 携程运行时机一定是在函数的最后一行
        lifecycleScope.launch(Dispatchers.Main) {
            // 底下这句在main thread
            Log.d("wangweijundx", "launch id = ${Thread.currentThread().id}, name=${Thread.currentThread().name}")
            val msg = getLoginInfoNotSuspend()
            Log.d("wangweijundx", "launch finished id = ${Thread.currentThread().id}, name=${Thread.currentThread().name}, msg = $msg")
        }
        Log.d("wangweijundx", "after launch id = ${Thread.currentThread().id}, name=${Thread.currentThread().name}");
    }

    fun testlifecycleScopeNotSuspendNotlaunch(view: android.view.View) {
        Log.d("wangweijundx", "testlifecycleScopeNotSuspendNotlaunch id = ${Thread.currentThread().id}, name=${Thread.currentThread().name}")
        val msg = getLoginInfoNotSuspend()
        Log.d("wangweijundx", "after launch id = ${Thread.currentThread().id}, name=${Thread.currentThread().name}");
    }

    fun testlifecycleScope3(view: android.view.View) {
        Log.d("wangweijundx", "testlifecycleScope3 click id = ${Thread.currentThread().id}, name=${Thread.currentThread().name}")
    }


    // 定义一个变量，不过这个变量代表着一个函数(函数替代接口)
    var mOnClickListener: ((View) -> Unit)? = null

    fun setOnclickListener(l: (View) -> Unit) {
        mOnClickListener = l
    }

    fun testLibrary() {
        Library1Util.test1()
    }

    fun testGif(view: View) {
        startActivity(Intent(applicationContext, GifActivity::class.java))
    }

    private fun xxxx() {
        runBlocking {

        }
    }
}