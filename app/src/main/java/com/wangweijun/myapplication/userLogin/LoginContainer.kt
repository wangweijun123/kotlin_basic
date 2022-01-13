package com.wangweijun.myapplication.userLogin

class LoginContainer(val userRepository: UserRepository) {
    val loginData = LoginUserData()

    val loginViewModelFactory = LoginViewModelFactory(userRepository)
}