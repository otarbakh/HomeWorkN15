package com.example.homeworkn15.Network

import com.example.homeworkn15.Model.Request.LoginRequest
import com.example.loginregister.Model.Response.LoginResponse
import com.example.loginregister.Model.Response.RegisterResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

object RetrofitHelper {

    private const val BASE_URL = "https://reqres.in/api/"

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    val authServiceBuilder by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
    }

    val authService by lazy{
        authServiceBuilder.create(RegisterLoginAPI::class.java)
    }
}

interface RegisterLoginAPI {
    @POST("login")
    suspend fun logIn(@Body body: LoginRequest): Response<LoginResponse>
    @POST("register")
    suspend fun register(@Body body: LoginRequest): Response<RegisterResponse>
}