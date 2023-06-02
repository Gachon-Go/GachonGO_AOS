package com.example.gachongo.api

import android.util.Log
import com.example.gachongo.data.ChargePoint
import com.example.gachongo.data.ChargePointResponse
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChargePointService(val chargePointView: ChargePointView) {
    fun sendChargePoint(jwt: String, price: ChargePoint){
        val chargePointService = NetworkModule.retrofit.create(ChargePointInterface::class.java)
        chargePointService.sendChargePoint(jwt, price).enqueue(object : Callback<ChargePointResponse> {
            override fun onResponse(call: Call<ChargePointResponse>, response: Response<ChargePointResponse>) {
                val resp = response.body()
                if (resp != null) {
                    when(resp.code){
                        1000-> chargePointView.onSendChargePointResultSuccess(resp.result)
                        else-> chargePointView.onSendChargePointResultFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<ChargePointResponse>, t: Throwable) {
                Log.d("GachonLog #결제", t.message ?: "카카오페이 포인트 충전 실패")
            }
        })
    }
}