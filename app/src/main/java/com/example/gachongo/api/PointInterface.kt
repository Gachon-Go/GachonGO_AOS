package com.example.gachongo.api

import com.example.gachongo.data.PointHistoryResponse
import com.example.gachongo.data.PointResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PointInterface {
    // 포인트 조회
    @GET("/point")
    fun getPoint(
        @Header("Authorization") jwt: String,
    ): Call<PointResponse>

    // 포인트 내역 조회
    @GET("/point/history")
    fun getPointHistory(
        @Header("Authorization") jwt: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Call<PointHistoryResponse>
}