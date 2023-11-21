package com.wangweijun.myapplication.zhangtao.unit12

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/03/20 6:18 下午
 * version: 1.0
 * desc   :
 */

class JavaConvertExample {
    // 可空类型
    private var name: String? = null

    private var mybean: MyBean? = null
    fun init() {
        name = "duanxia"
        mybean = MyBean()
    }

    fun setNameNull() {
        name = null
    }

    // kotlin 函数参数是不可变得val，只是没有显示声明val，
    fun setMyBeanNull2(mybean: MyBean) {
//        mybean = MyBean() // build error
    }

    fun setMyBeanNull() {
        mybean = null
    }


    class MyBean {
        var name: String? = null
    }


    fun test() {
        if (name != null) {
            // 几百行代码
            setNameNull()
            //几百行代码
            val count = name!!.length // 死翘翘
        }
    }

    fun test2() {
        // 就是用这种方式，避免空指针
        name?.let {
            setNameNull()
            println("call foo() --> $name")
            println("first use let $it")

            println("second use let ${it.length}")
        }
    }

    // 避免直接使用成员变量，以参数形式传入，因为参数是不可以变化,  这里就不需要加 断言 !!
    fun test3(name: String?) {
        if (name != null) {
            val cont = name.length
        }

        name?.let {
            val cont = name.length
        }
    }

    fun test4() {
        val tempName = name
        if (tempName != null) {
            // 几百行代码
            setNameNull()
            //几百行代码
            val count = tempName.length
            println("count = $count, tempName = $tempName, name = $name")
        }

        val tempMybean = mybean
        if (tempMybean != null) {
            println("tempMybean = $tempMybean, mybean = $mybean, ")
            setMyBeanNull()
            println(" setMyBeanNull after tempMybean = $tempMybean, mybean = $mybean, ")
        }
    }
}