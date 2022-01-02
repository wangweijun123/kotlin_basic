package com.wangweijun.myapplication.mycoroutines

import java.io.InputStream

class LoginResponseParser {
    fun parse(inputStream: InputStream?): LoginResponse {
        return LoginResponse()
    }
}