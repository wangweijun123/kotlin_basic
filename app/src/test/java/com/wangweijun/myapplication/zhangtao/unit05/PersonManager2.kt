package com.wangweijun.myapplication.zhangtao.unit05

class PersonManager2 private constructor(name: String){

    companion object : BaseSingleton<String, PersonManager2>() {

        override fun creator(param: String): PersonManager2 = PersonManager2(param)

    }
}