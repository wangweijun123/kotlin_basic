package com.wangweijun.myapplication.unit6

val TITLE = "VIP 标题"
val MSG = "VIP 消息"

// 写在class上的构造叫主构造函数(两个参数的构造函数),还有次级构造函数
class VIPPlayer(var title: String?, var msg: String?) : PlayerView {
    init {
        println("init ...")
    }

    constructor() : this(TITLE, MSG) {
        println("调用无参构造")
    }

//    constructor(msg: String?) : this(TITLE, msg)
   // 还可以写: 加大括号
    constructor(msg: String?) : this(TITLE, msg) {
        println("调用you参构造")
    }

    override fun showView() {
        println("showView... $title  $msg")
    }

    override fun getPlayButton() {
        println("getPlayButton... $title  $msg")
    }

}