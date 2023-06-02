package com.example.gachongo.api

import android.util.Log
import com.example.gachongo.data.BaseResponse
import com.example.gachongo.data.Point
import com.example.gachongo.data.PointHistoryResponse
import com.example.gachongo.data.PointResponse
import com.example.gachongo.data.TransactionIdResponse
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PayService(val payView: PayView) {

    private var payService: PayInterface = NetworkModule.retrofit.create(PayInterface::class.java)

    fun sendPay(jwt: String, id: String, point: Point) {
        payService.sendPay(jwt, id, point).enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val resp = response.body()
                if (resp != null) {
                    when (resp.code) {
                        1000 -> payView.onSendPayResultSuccess()
                        else -> payView.onSendPayResultFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("GachonLog #결제", t.message ?: "send Pay API 통신 오류")
            }
        })
    }
}