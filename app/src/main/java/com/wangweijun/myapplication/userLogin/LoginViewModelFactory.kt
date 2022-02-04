package com.wangweijun.myapplication.userLogin

class LoginViewModelFactory(private val userRepository: UserRepository) : Factory{
    override fun  create(): LoginViewModel  {
        return LoginViewModel(userRepository)
    }
}