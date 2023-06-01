package com.example.gachongo.api

import com.example.gachongo.data.ChargePoint
import com.example.gachongo.data.ChargePointResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ChargePointInterface {
    // 포인트 충전
    @POST("/pay/request")
    fun sendChargePoint(
        @Header("Authorization") jwt: String,
        @Body price: ChargePoint
    ): Call<ChargePointResponse>
}