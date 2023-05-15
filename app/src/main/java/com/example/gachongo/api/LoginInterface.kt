package com.example.gachongo.api

import com.example.gachongo.data.BaseResponse
import com.example.gachongo.data.Login
import com.example.gachongo.data.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginInterface {
    // 로그인 api
    @POST("/auth/login")
    fun login (
        @Body login: Login
    ): Call<LoginResponse>
}