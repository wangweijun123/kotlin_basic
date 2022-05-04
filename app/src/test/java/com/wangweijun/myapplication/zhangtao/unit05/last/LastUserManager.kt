package com.wangweijun.myapplication.zhangtao.unit05.last

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/05/03 09:20
 * version: 1.0
 * desc   : 变成单利
 */
class LastUserManager private constructor(val name: String){

    companion object {
        @Volatile
        private var instance: LastUserManager? = null
        fun getInstance(name: String): LastUserManager {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = LastUserManager(name)
                    }
                }
            }
            return instance!!
        }

        fun getInstanceKotlin(name: String): LastUserManager {
            // 第一次判断空
            instance ?: synchronized(this) {
                // 第二次判空
                instance ?: LastUserManager(name).also {
                    instance = it
                }
            }
            return instance!!
        }

        /**
         *  获取LastUserManager的单例
         * @param name String
         * @return LastUserManager
         */
        fun getInstanceKotlin2(name: String) =
            // 第一次判断空
            instance ?: synchronized(this) {
                // 第二次判空
                instance ?: LastUserManager(name).also {
                    instance = it
                }
            }
    }
}