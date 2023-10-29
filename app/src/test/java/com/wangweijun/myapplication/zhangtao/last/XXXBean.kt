package com.wangweijun.myapplication.zhangtao.last

// name 与 age 都是 bean的属性
class XXXBean(val name: String, var age: Int) {
    // 这也是 bean的属性
    var height = 100

    // 清注意，翻译java代码后这里还是一个方法
    val isAdult: Boolean
        get() {
            // do something else
            return age >= 18
        }

    // 这里变成了属性
    val isAdult2 = age >= 18
}