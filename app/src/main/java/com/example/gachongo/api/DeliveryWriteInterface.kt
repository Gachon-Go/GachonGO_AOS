package com.example.gachongo.api

import com.example.gachongo.data.RequestDeliveryWrite
import com.example.gachongo.data.ResponseDeliveryWrite
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface DeliveryWriteInterface {
    @POST("/delivery")
    fun writeDelivery(
        @Header("Authorization") jwt: String,
        @Body request: RequestDeliveryWrite,
    ): Call<ResponseDeliveryWrite>
}
