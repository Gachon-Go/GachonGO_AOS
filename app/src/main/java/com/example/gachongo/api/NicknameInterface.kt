package com.example.gachongo.api

import com.example.gachongo.data.BaseResponse
import com.example.gachongo.data.Nickname
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Path

interface NicknameInterface {
    // 닉네임 유효성 검사
    @GET("/auth/nickname/{nickname}/validation")
    fun checkNickname (
        @Path("nickname") nickname: String
    ): Call<BaseResponse>

    // 닉네임 변경
    @PATCH("/user/nickname")
    fun editNickname (
        @Header("Authorization") jwt: String,
        @Body nickname: Nickname
    ): Call<BaseResponse>
}