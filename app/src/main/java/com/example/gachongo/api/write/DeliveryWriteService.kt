package com.example.gachongo.api.write

import android.util.Log
import com.example.gachongo.api.DeliveryInterface
import com.example.gachongo.data.BaseResponse
import com.example.gachongo.data.request.RequestDeliveryDto
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeliveryWriteService(val deliveryWriteView: DeliveryWriteView) {
    val deliveryWriteService = NetworkModule.retrofit.create(DeliveryInterface::class.java)
    fun postDelivery(jwt: String, requestBody: RequestDeliveryDto) {
        deliveryWriteService.postDelivery(jwt, requestBody)
            .enqueue(object : Callback<BaseResponse> {
                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>,
                ) {
                    val resp = response.body()
                    if (resp != null) {
                        when (resp.code) {
                            1000 -> deliveryWriteView.onGetDeliveryWriteResultSuccess(resp)
                            else -> deliveryWriteView.onGetDeliveryWriteResultFailure(resp.message)
                        }
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    Log.d("GachonLog #글 작성 실패", t.message ?: "통신 오류")
                }
            })
    }
}
