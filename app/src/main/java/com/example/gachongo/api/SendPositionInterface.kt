package com.example.gachongo.api

import com.example.gachongo.data.BaseResponse
import com.example.gachongo.data.Position
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface SendPositionInterface {
    @POST("/map")
    fun sendPosition (
        @Header("Authorization") jwt: String,
        @Body position: Position,
    ): Call<BaseResponse>
}