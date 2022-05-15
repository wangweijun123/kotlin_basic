package com.wangweijun.myapplication.zhangtao.unit09.last

class Model4 {
    var data: String = "model 4"
        // 给set定义private,从类的外部就不能访问了
        // 原理就是去掉了set方法
        private set
}