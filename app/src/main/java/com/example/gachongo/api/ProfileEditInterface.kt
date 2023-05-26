package com.example.gachongo.api

import com.example.gachongo.data.BaseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH

interface ProfileEditInterface {
    // 프로필 이미지 변경
    @PATCH("/user/image")
    fun editProfile (
        @Header("Authorization") jwt: String,
        @Body image: String
    ): Call<BaseResponse>
}