package com.wangweijun.myapplication.unit6
// data class 编译器自动为属性生成了get set 方法
data class User(var id: Int,
                var name: String,
                val playerType: PlayerViewType = PlayerViewType.BLUE)
