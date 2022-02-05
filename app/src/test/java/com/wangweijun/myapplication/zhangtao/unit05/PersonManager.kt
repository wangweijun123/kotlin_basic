package com.wangweijun.myapplication.zhangtao.unit05

class PersonManager private constructor(name: String) {
    companion object {
        @Volatile private var INSTANCE: PersonManager? = null

        /*fun getInstance(name: String): PersonManager =
            INSTANCE?: synchronized(this) {
                INSTANCE?:PersonManager(name).also { INSTANCE = it }
            }*/

        fun getInstance(name: String): PersonManager {
            if (INSTANCE == null) {
                synchronized(this) {
                    /*if (INSTANCE == null) {
                        INSTANCE = PersonManager(name)
                    }*/
                    INSTANCE ?: PersonManager(name).also {
                        INSTANCE = it
                    }

                }
            }
            return INSTANCE!!
        }
    }
}