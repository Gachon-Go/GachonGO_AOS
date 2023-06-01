package com.example.gachongo.api

import com.example.gachongo.data.BaseResponse
import com.example.gachongo.data.Point
import com.example.gachongo.data.TransactionIdResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface PayInterface {
    // 거래 api
    @POST("/point/transaction/code")
    fun sendPay(
        @Header("Authorization") jwt: String,
        @Query("TransactionId") TransactionId : String,
        @Body point: Point,
    ): Call<BaseResponse>

    // 거래 고유 번호 api
    @GET("/point/transaction/code")
    fun getTransactionCode(
        @Header("Authorization") jwt: String,
    ): Call<TransactionIdResponse>
}