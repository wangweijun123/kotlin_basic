package com.wangweijun.myapplication.zhangtao.unit09

import org.junit.Test

interface DB {
    fun save()
}

class SqlDB() : DB {
    override fun save() { println("save to sql") }
}

class GreenDaoDB() : DB {
    override fun save() { println("save to GreenDao") }
}
// 看起来就是一个包装
//               参数  通过 by 将接口实现委托给 db
//                ↓            ↓
class UniversalDB(db: DB) : DB by db

class DelegateTest {
    /*
        输出：
        save to sql
        save to GreenDao
        */

    @Test
    fun main() {
        UniversalDB(SqlDB()).save()
        UniversalDB(GreenDaoDB()).save()
    }

    @Test
    fun testModel() {
        val model = Model()
        model.load()
        println(model.data.size)
    }
}

