package com.wangweijun.myapplication.zhangtao.unit05

class UserManager3 private constructor(name: String){

    companion object : BaseSingleton<String, UserManager3>() {

        override fun creator(param: String): UserManager3 = UserManager3(param)

    }
}