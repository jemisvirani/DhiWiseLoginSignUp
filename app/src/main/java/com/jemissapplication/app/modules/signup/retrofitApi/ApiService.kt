package com.jemissapplication.app.modules.signup.retrofitApi

import com.eclatsol.kotlinaapi.model.SignUpRequest
import com.eclatsol.kotlinaapi.model.SignUpResponse
import com.jemissapplication.app.modules.signup.data.modelclass.LoginRequest
import com.jemissapplication.app.modules.signup.data.modelclass.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("device/auth/register")
    fun signUpApi(@Body signUpRequest: SignUpRequest) : Call<SignUpResponse>

    @POST("device/auth/login")
    fun loginApi(@Body loginRequest: LoginRequest) : Call<LoginResponse>
}