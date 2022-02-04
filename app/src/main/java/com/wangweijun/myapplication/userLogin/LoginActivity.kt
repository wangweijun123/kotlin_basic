package com.wangweijun.myapplication.userLogin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wangweijun.myapplication.Myapplication

class LoginActivity : AppCompatActivity(){
    private var loginViewModel:LoginViewModel? = null
    private var loginData: LoginUserData? = null
    private var appContainer:AppContainer = (application as Myapplication).appContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        loginViewModel = LoginViewModel(appContainer.userRepository)
//        loginViewModel = appContainer.loginViewModelFactory.create()
        appContainer.loginContainer = LoginContainer(appContainer.userRepository)

        loginViewModel = appContainer.loginContainer?.loginViewModelFactory?.create()
        loginData = appContainer.loginContainer?.loginData

    }

    override fun onDestroy() {
        // Login flow is finishing
        // Removing the instance of loginContainer in the AppContainer
        appContainer.loginContainer = null
        super.onDestroy()
    }

    fun clickLogin() {
        loginViewModel?.login()
    }
}