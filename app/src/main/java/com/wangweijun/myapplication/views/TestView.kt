package com.wangweijun.myapplication.views

import android.content.Context
import android.util.AttributeSet
import android.view.View

class TestView : View {
    constructor(context: Context) : super(context) {
        println("constructor")
    }
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
}