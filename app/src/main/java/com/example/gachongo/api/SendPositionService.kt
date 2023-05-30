package com.example.gachongo.api

import android.util.Log
import com.example.gachongo.data.BaseResponse
import com.example.gachongo.data.Position
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SendPositionService(val sendPositionView: SendPositionView) {
    fun sendPosition(jwt: String, position: Position) {
        val sendPositionService = NetworkModule.retrofit.create(SendPositionInterface::class.java)
        sendPositionService.sendPosition(jwt, position).enqueue(object: Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val resp = response.body()
                if (resp != null) {
                    when (resp.code) {
                        1000 -> sendPositionView.onGetSendPositionResultSuccess()
                        else -> sendPositionView.onGetSendPositionResultFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("LOCATION_UPDATE", t.message ?: "통신 오류")
            }
        })
    }
}