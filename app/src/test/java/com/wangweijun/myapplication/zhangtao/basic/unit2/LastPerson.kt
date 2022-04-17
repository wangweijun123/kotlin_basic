package com.wangweijun.myapplication.zhangtao.basic.unit2

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/04/10 9:10 上午
 * version: 1.0
 * desc   : val 定义的变量只有get方法(public), 但是var有public get , public set
 *    如果变量加上private ，不会生成get ,set
 */
class LastPerson(/*private */val name: String, /*private */var age: Int)