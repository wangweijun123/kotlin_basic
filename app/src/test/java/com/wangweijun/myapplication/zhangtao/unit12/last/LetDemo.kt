package com.wangweijun.myapplication.zhangtao.unit12.last

import com.wangweijun.myapplication.unit7.User
import org.junit.Test

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/05/16 16:22
 * version: 1.0
 * desc   :
 */
class LetDemo {
    @Test
    fun test() {
        var user: User? = User("wangweijun")
        val r1 = user?.let {
            println(user === it) // true
            println("user.hashCode() == it.hashCode() ? ${user.hashCode() == it.hashCode()}") // true
            user = null
            println("user = $user")
            println("it = $it")
            it.name
        }
        println(r1)
    }


}