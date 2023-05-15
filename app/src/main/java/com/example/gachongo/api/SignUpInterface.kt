package com.example.gachongo.api

import com.example.gachongo.data.BaseResponse
import com.example.gachongo.data.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpInterface {
    // 회원가입 api
    @POST("/auth/signup")
    fun signUp (
        @Body user: User
    ): Call<BaseResponse>
}