package com.wangweijun.myapplication.di

import org.junit.Test

class DITest {


}

class Main {
    @Test
    fun xxx() {

        val engine1 = ServiceLocator.getEngine()
        val engine2 = ServiceLocator.getEngine()
        println(engine1)
        println(engine2)
        println(engine2 === engine1)
//        val car = Car()
//        car.start()
    }
}
