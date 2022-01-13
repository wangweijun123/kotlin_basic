package com.wangweijun.myapplication.userLogin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel(private val userReposity: UserRepository) : ViewModel(){


    fun login() {
        userReposity.login()
    }


}