package com.example.gachongo.api

import com.example.gachongo.data.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NicknameInterface {
    // 닉네임 유효성 검사
    @GET("/auth/nickname/{nickname}/validation")
    fun checkNickname (
        @Path("nickname") nickname: String
    ): Call<BaseResponse>
}