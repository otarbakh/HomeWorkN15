package com.example.homeworkn15.UI.Register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeworkn15.Model.Request.LoginRequest
import com.example.loginregister.Model.Response.RegisterResponse
import com.example.homeworkn15.Network.RetrofitHelper
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val _registeredState = MutableStateFlow<Resource<RegisterResponse>>(Resource.Loading(false))
    val registeredState = _registeredState.asStateFlow()



    fun register(email: String,password: String){
        viewModelScope.launch {
            registerResponse(email,password).collect{
                _registeredState.value = it
            }
        }
    }


    private fun registerResponse(email: String, password: String) = flow {
        val response = RetrofitHelper.authService.register(LoginRequest(email, password))

        try {
            if (response.isSuccessful) {
                val body = response.body()
                Resource.Success(body).let {
                    emit(Resource.Success(body!!))
                }
            } else {
                val error = response.errorBody()
                emit(Resource.Error(error.toString()))
            }

        }catch (e:Throwable){
            emit(Resource.Error(e.message.toString()))
        }

    }

}
