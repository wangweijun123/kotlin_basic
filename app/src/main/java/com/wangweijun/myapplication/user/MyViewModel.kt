package com.wangweijun.myapplication.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyViewModel : ViewModel(){

    init {
        // 如果viewmodel销毁, 则在viewModelScope内启动的协程自动取消
        viewModelScope.launch {
            // Coroutine that will be canceled when the ViewModel is cleared.
            println("xxxxxxxxxx")
        }
    }

    /*val user: LiveData<User> = liveData {
        val data = database.loadUser() // loadUser is a suspend function.
        emit(data)
    }*/
}