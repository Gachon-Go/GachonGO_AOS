package com.example.gachongo.api

import com.example.gachongo.data.RequestDeliveryWrite
import com.example.gachongo.data.ResponseDeliveryWrite
import com.example.gachongo.data.ResponseNotification
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface NotificationInterface {
    @GET("/notification")
    fun getNotification(
        @Header("Authorization") jwt: String
    ): Call<ResponseNotification>
}
