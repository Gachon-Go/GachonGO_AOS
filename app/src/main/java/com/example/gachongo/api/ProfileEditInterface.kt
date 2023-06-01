package com.example.gachongo.api

import com.example.gachongo.data.BaseResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.Part

interface ProfileEditInterface {
    // 프로필 이미지 변경
    @Multipart
    @PATCH("/user/image")
    fun editProfile (
        @Header("Authorization") jwt: String,
        @Part image: MultipartBody.Part
    ): Call<BaseResponse>
}