package com.wangweijun.myapplication.di

/**
 *
 *
public final class ServiceLocator {
@NotNull
public static final ServiceLocator INSTANCE = new ServiceLocator();

private ServiceLocator() {
}

@NotNull
public final Engine getEngine() {
return new Engine();
}
}
 *
 * object 定义的类是单例, 便于调用里面方法就这么简单
 */
object ServiceLocator {

    fun getEngine(): Engine = Engine()
}