package com.wangweijun.myapplication.mycoroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel(val loginRepository: LoginRepository): ViewModel() {

    // 创建一个新的协程，然后在 I/O 线程上执行网络请求
    fun login(username: String, token: String) {
        // 必须在协程里面执行
        /*val jsonBody = "{ username: \"$username\", token: \"$token\"}"
        loginRepository.makeLoginRequest(jsonBody)*/

        // Create a new coroutine to move the execution off the UI thread
        // import lifecycle-viewmodel-ktx
        // viewModelScope 是ViewModel扩展属性, 是CoroutineScope对象
        // public val ViewModel.viewModelScope: CoroutineScope
        // 所有协程必须在一个作用域内运行, 一个CoroutineScope管理着多个协程
        // 创建协程, 指定此协程在Dispatchers.IO线程上执行
        viewModelScope.launch(Dispatchers.IO) {
            val jsonBody = "{ username: \"$username\", token: \"$token\"}"
            loginRepository.makeLoginRequest2(jsonBody)
        }
    }

    /**
     *
     login 函数现在按以下方式执行：
    应用从主线程上的 View 层调用 login2() 函数。
    launch 创建一个新的协程，以在主线程上发出网络请求，然后该协程开始执行。
    在协程内，调用 loginRepository.makeLoginRequest2() 现在会挂起协程的
    进一步执行操作，直至 makeLoginRequest() 中的 withContext 块结束运行。
    withContext 块结束运行后，login2() 中的协程在主线程上恢复执行操作，
    并返回网络请求的结果。
     *
     */
    fun login2(username: String, token: String) {
        // Create a new coroutine on the UI thread
        // 注意: 现在没有传参 Dispatchers.IO, 因为makeLoginRequest2有io
        // 如果您未将 Dispatcher 传递至 launch，则从 viewModelScope
        // 启动的所有协程都会在主线程中运行
        viewModelScope.launch {
            val jsonBody = "{ username: \"$username\", token: \"$token\"}"

            // Make the network call and suspend execution until it finishes
            // 注意: 此处仍需要协程，因为 makeLoginRequest 是一个 suspend 函数，
            // 而所有 suspend 函数都必须在协程中执行
            val result = loginRepository.makeLoginRequest2(jsonBody)

            // Display result of the network request to the user
            when (result) {
                is Result.Success<LoginResponse> -> println("success")// happy
                else -> println(" Show error in UI")
            }
        }
    }

    /**
     * 为了处理 Repository 层可能抛出的异常,增加try catch
     */
    fun login3(username: String, token: String) {
        // Create a new coroutine on the UI thread
        // 注意: 现在没有传参 Dispatchers.IO, 因为makeLoginRequest2有io
        // 如果您未将 Dispatcher 传递至 launch，则从 viewModelScope
        // 启动的所有协程都会在主线程中运行
        viewModelScope.launch {
            val jsonBody = "{ username: \"$username\", token: \"$token\"}"

            // Make the network call and suspend execution until it finishes
            // 注意: 此处仍需要协程，因为 makeLoginRequest 是一个 suspend 函数，
            // 而所有 suspend 函数都必须在协程中执行
            // try catch
            val result = try {
                loginRepository.makeLoginRequest2(jsonBody)
            }catch (e: Exception) {
                Result.Error(Exception("Network request failed"))
            }
            // Display result of the network request to the user
            when (result) {
                is Result.Success<LoginResponse> -> println("success")// happy
                else -> println(" Show error in UI")
            }
        }
    }
}