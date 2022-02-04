package com.wangweijun.myapplication.di

class Car {

    private val engine = ServiceLocator.getEngine()

    fun start() {
        engine.start()
    }
}