package com.example.gachongo.api

import com.example.gachongo.data.BaseResponse
import com.example.gachongo.data.MyPageResponse
import com.example.gachongo.data.Nickname
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Path

interface MyPageInterface {
    // 마이 페이지 조회
    @GET("/user/my-page")
    fun getMyPage(
        @Header("Authorization") jwt: String
    ): Call<MyPageResponse>
}