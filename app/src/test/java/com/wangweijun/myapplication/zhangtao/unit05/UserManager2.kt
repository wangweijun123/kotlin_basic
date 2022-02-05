package com.wangweijun.myapplication.zhangtao.unit05

/**
 * 单例第二种写法
 * 伴生对象 Double Check
 */
class UserManager2 private constructor(name: String){
    companion object {
        @Volatile
        private var INSTANCE: UserManager2? = null

        fun getInstance(name: String): UserManager2 =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserManager2(name).also {
                    INSTANCE = it
                }
            }
    }
}