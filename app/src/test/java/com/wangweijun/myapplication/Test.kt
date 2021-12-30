package com.wangweijun.myapplication

import com.wangweijun.myapplication.unit3.Animal2
import com.wangweijun.myapplication.unit3.Dog2
import com.wangweijun.myapplication.unit3.Zomm2
import com.wangweijun.myapplication.unit6.PlayerUI
import com.wangweijun.myapplication.unit6.PlayerViewType
import com.wangweijun.myapplication.unit7.arrayTest
import com.wangweijun.myapplication.unit7.functionScope
import com.wangweijun.myapplication.unit7.loop
import com.wangweijun.myapplication.unit7.myOperator
import org.junit.Test
import kotlin.reflect.KClass

/**
 * 在kotlin语言,变量与函数直接定义在文件中
 */

// val 定义的是一个不可变的变量, 但是不是常量
val age: Int = 18

// 这种是不可以为NULL的类型
 var name: String = "wangweijun"
// 这种是可以为NULL的类型, 这是两种不同的类型
var name2: String? = null

// 声明lambda闭包,闭包就是一对花括号{}
val echo2 = {}
val echo4 = {
    println("无参数的闭包")
}
// 带参数的闭包 需要 -> 把参数与函数体分开
// 我的理解 闭包(参数与函数体) 就是 一个函数(参数与函数体)
val echo3 = { name: String ->
    println("this name : $name")
}

/**
 * 声明一个带有参数为函数的函数, 叫高阶函数： 函数的参数是函数
 * 参数为函数: () -> Unit 表示无参, 返回值为Unit: 意思是没有返回值,(有一个隐藏的返回值)
 */
fun onlyif(isDebug: Boolean, bolck: () -> Unit) {
    if (isDebug) {
        bolck()
    }
}

@Test
fun main() {
//    printLen(name)
    // 把可以为NULL类型强制传入不可为NULL类型的函数,除非你确保可以为NULL类型的参数的值一定不为NULL,否则强制传入
    // 在运行期抛异常NullPointException
//    name2 = "xxx"
//    printLen(name2!!)
//    printLen(null) 编译前报错

//    callOtherKotlin()
//    testClass(JavaClass::class.java)
//    testClass(KotlinClass::class)
    // 在java定义变量名字与Kotlin中关键词冲突(名字一样)的时候, 使用``这个转义
//    println(JavaClass.`in`)
//    println(JavaClass.name)
//    callJavaStaticMethodAndNonStatic()

//    A.a.putNumber(111)

//    使用伴生对象()

//    echo2()
//    echo2("ddddddddddd")
//    Dog().printName(Dog())

//    val dog = Dog()
//    dog.printName(dog)

//    lambdaTest01()
//    lambdaTest02()
    // 调用闭包(如下两种都可以)
//    echo3.invoke("ddddxxxx")
//    echo3("1113222")
//    echo4.invoke()

    // 调用高阶函数
    /*onlyif(true) {
        println("这是高阶函数")
    }*/
    // 这样也是可以的
    /*onlyif(true, {
        println("这是高阶函数")
    })*/

    // 这是创建一个对象
    /*val runnable = Runnable {
        println(" run ...")
    }
    val function = runnable::run
    onlyif(true, function)*/

    // 编译出错
//    StringUtils.isEmp("xx")
//    val su = StringUtils()
//    println(su.isEmp("xx"))
//    println(StringUtils.isEmp2(""))

//    proxyTest()
//    beanTest()
//    musicPlayerTest()
//    loop()
//    arrayTest()
//    myOperator()

    functionScope()
}

fun musicPlayerTest() {
//    val user = com.wangweijun.myapplication.unit6.User(1, "dx")
    val user = com.wangweijun.myapplication.unit6.User(1, "dx", PlayerViewType.VIP)
    PlayerUI.get().showPlayer(user)
}

fun beanTest() {
    val user = User(100, "dx")
    println(user.id)
}

/**
 * 静态代理
 */
fun proxyTest() {
    Zomm2(Dog2()).bark()
}

/**
 * kotlin class 单例测试
 */
fun kotlinSingleTest() {
    // 私有构造函数SingleKotlin() build error
    // 只能通过伴生对象获取companion object
    SingleKotlin.get().test()
}

fun lambdaTest01() {
    val thread = Thread({ ->
        println("ddddddddd")
    })
    thread.start()
    Thread.sleep(100)
}

fun lambdaTest02() {
    // 1 如果lambda闭包没有参数,箭头可以省略,see lambdaTest02
    // 2 如果lambda是函数的最后一个参数,可以将{} 放到()外面
    // 3 如果函数只有lambda一个参数, 小阔号()可以省略
    /*val thread2 = Thread({
        println("22222222222")
    })
    val thread3 = Thread() {
        println("22222222222")
    }*/
    // 怎么简单怎么来
    val thread = Thread {
        println("22222222222")
    }
    thread.start()
    Thread.sleep(100)
}

/**
 * 自定义返回值类型, 主要是 ? 类型
 */
fun definedRetrueType() {
    val format = C.format("")
//    println(format.length) // 运行期空指针报错
    println(format?.length) // is ok 打印null,后面的语句继续执行
    println("##########")

    // f1 类型 String!
    val f1 = C.format("")
    println(f1?.length)
    // f2 不为NULL的String类型
    // val f2: String = C.format("") // 运行期异常NullPointException
// f3 可以为NULL的String类型
    val f3: String? = C.format("")
    println(f3?.length) // ? 其实就是加了一个if的判断
}

/**
 * kotlin中null测试
 */
fun nullTest() {
    val format = C.format("")
    println(format)
    if (format == null) {
        println("is null")
    } else {
        println("not null")
    }
}

fun 使用伴生对象() {
    // ka 对象 与 a 对象不是一个哦
    val ka = KotlinA()
    ka.putNumber(23232)
    KotlinA.a.putNumber(123)

    val empty = KotlinA.isEmpty("xxx")
    println("empty = $empty")
}

/**
 *
 */
fun callJavaStaticMethodAndNonStatic() {
    // 在kotlin中不用关键词new 新建对象
    val main = Main()
    val main2 = Main()
    println(main)
    println(main2)
    println(main2 == main)
    // 调用实例方法
    main.instanceMethod("this is instance method")
    // 调用静态方法
    Main.staticMethod("this is static method")
}

fun testClass(clz: Class<JavaClass>) {
    println(clz.simpleName)
}

fun testClass(clz: KClass<KotlinClass>) {
    println(clz.simpleName)
}

/**
 * 直接调用其他Kotlin文件的函数
 */
fun callOtherKotlin() {
    echo("dxx")
}

fun callOtherKotlin2() {
    echo2("ddddddddddd")
}

/**
 * 在字符串中打印变量需要 $
 */
fun printVarInStr() {
    print("my age = $age")
}

/*fun printVarInStr2() {
    val age = 18;
    // 不能用占位符 build error
    println("my age = %d", age)
}*/

/**
 * 赋值操作: 不为NULL类型可以为 可以为NULL类型赋值
 */
fun assign() {
    //    name = name2 // error 类型不匹配,但是可以强转,如下
//    name = name2!!
    name2 = name; // is ok
    print(name2)
}

/**
 * 参数 为 不为NULL 类型, 所以在函数内部就不需要去做判断
 */
fun printLen(str: String): String {
//    println("字符串为：$str")
    return str
}

// open 与 final 相反, kotlin的class默认背编译成final class
open class Animal
class Dog : Animal()

fun Animal.name() = "animal"
fun Dog.name() = "Dog"

fun Animal.printName(anima: Animal) {
    println(anima.name())
}
