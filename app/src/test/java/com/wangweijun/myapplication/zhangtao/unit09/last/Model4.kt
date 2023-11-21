package com.wangweijun.myapplication.zhangtao.unit09.last

class Model4 {
    var data: String = "model 4"
        // 给set定义private, 不能在外部修改,当然外部可以访问
        // 原理就是去掉了set方法
        private set
}