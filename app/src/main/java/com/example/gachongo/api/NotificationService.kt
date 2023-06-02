package com.example.gachongo.api

import android.util.Log
import com.example.gachongo.data.ResponseNotification
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Response

class NotificationService(val notificationView: NotificationView) {
    fun getNotification(jwt: String) {
        val getNotificationService = NetworkModule.retrofit.create(NotificationInterface::class.java)
        getNotificationService.getNotification(jwt)
            .enqueue(object: retrofit2.Callback<ResponseNotification> {
                override fun onResponse(
                    call: Call<ResponseNotification>,
                    response: Response<ResponseNotification>
                ) {
                    val resp = response.body()
                    if (resp != null) {
                        when (resp.code) {
                            1000 -> notificationView.onGetNotificationSuccess(resp.result)
                            else -> notificationView.onGetNotificationFailure(resp.message)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseNotification>, t: Throwable) {
                    Log.d("GachonLog #알림", t.message ?: "알림 받아오기 실패")
                }
            })
    }
}