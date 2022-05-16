package com.wangweijun.myapplication.zhangtao.unit10.last

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/05/16 01:15
 * version: 1.0
 * desc   : in or out 需要看函数 泛型作为“参数”还是“返回值”
 */

//                   协变
//                    ↓
 interface MyList<out E> : Collection<E> {
    //                                泛型作为返回值
//                                       ↓
    operator fun get(index: Int): E
    //                                           泛型作为参数,必须使用@UnsafeVariance
//                                                 ↓
    override fun contains(element: @UnsafeVariance E): Boolean
    //                                        泛型作为参数
//                                              ↓
    fun indexOf(element: @UnsafeVariance E): Int
}