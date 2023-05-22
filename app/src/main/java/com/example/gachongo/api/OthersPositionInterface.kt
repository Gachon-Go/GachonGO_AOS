package com.example.gachongo.api

import com.example.gachongo.data.OthersPositionResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OthersPositionInterface {
    // 사용자들의 위치를 받아와요
    @GET("/map")
    fun getOthersPosition(
        @Query("purpose") purpose: String,
        @Query("postId") postId: Int
    ): Call<OthersPositionResponse>
}