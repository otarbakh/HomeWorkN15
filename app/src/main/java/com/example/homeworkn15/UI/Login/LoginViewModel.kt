package com.example.loginregister.UI.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworkn15.Model.Request.LoginRequest
import com.example.loginregister.Model.Response.LoginResponse
import com.example.homeworkn15.Network.RetrofitHelper
import com.example.loginregister.Resource
import kotlinx.coroutines.flow.*
//import com.example.loginregister.Resource
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _loginState = MutableStateFlow<Resource<LoginResponse>>(Resource.Loading(false))
    val loginState = _loginState.asStateFlow()




    fun logIn(email: String,password: String){
        viewModelScope.launch {
            loginResponse(email,password).collect{
                _loginState.value = it
            }
        }
    }


    private fun loginResponse(email: String, password: String) = flow {
        val response = RetrofitHelper.authService.logIn(LoginRequest(email, password))



        try {
            if (response.isSuccessful) {
                val body = response.body()
                Resource.Success(body).let {
                    emit(Resource.Success(body!!))
                }
            } else {
                val error = response.errorBody()?.string()
                emit(Resource.Error(error.toString()))
            }

        }catch (e:Throwable){
            emit(Resource.Error(e.message.toString()))
        }

    }

}