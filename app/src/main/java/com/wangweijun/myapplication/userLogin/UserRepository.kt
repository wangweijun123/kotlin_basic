package com.wangweijun.myapplication.userLogin

class UserRepository(
    private val localDataSource: UserLocalDataSource,
    private val remoteDataSource: UserRemoteDataSource
) {

    fun login() {

    }

}

class UserLocalDataSource {}

class UserRemoteDataSource(private val loginService: LoginRetrofitService) {

}
// 真正的登录服务
interface LoginRetrofitService{

}