package com.wangweijun.myapplication.zhangtao.unit09.last

import com.wangweijun.myapplication.zhangtao.unit09.DB

// 并没有实现接口，实现交给了一个传入的对象
class LastUniversalDB(db: DB) : DB by db