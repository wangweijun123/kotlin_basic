package com.wangweijun.myapplication.views

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * 如果一个类构造的时候有参数, 直接在class 后面加参数,这个构造叫主构造,
 * 如果还有其他的构造, 需要显示的声明 constructor,并且必须直接或者间间
 * 继承主构造或者父类的构造函数, 所以底下会出现 : 冒号
 * 如下是主构: 为无参 (类名后面的) 3个次级构造函数
 */
class TestView : View {
    constructor(context: Context) : super(context) {
        println("constructor")
    }
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}