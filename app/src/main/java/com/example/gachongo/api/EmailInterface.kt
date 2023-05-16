package com.example.gachongo.api

import com.example.gachongo.data.BaseResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface EmailInterface {
    // 이메일 인증번호 요청 api
    @POST("/mail")
    fun sendEmail(
        @Query("address") email: String,
    ): Call<BaseResponse>

    // 이메일 인증번호 검사 api
    @POST("/mail/auth")
    fun checkCode(
        @Query("mail") email: String,
        @Query("code") code: String
    ): Call<BaseResponse>
}