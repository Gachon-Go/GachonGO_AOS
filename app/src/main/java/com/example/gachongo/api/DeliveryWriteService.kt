package com.example.gachongo.api

import android.util.Log
import com.example.gachongo.data.RequestDeliveryWrite
import com.example.gachongo.data.ResponseDeliveryWrite
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Response

class DeliveryWriteService(val deliveryWriteView: DeliveryWriteView) {

    fun deliveryWrite(jwt: String, data: RequestDeliveryWrite) {
        val deliveryWriteService = NetworkModule.retrofit.create(DeliveryWriteInterface::class.java)

        deliveryWriteService.writeDelivery("Bearer $jwt", data)
            .enqueue(object : retrofit2.Callback<ResponseDeliveryWrite> {
                override fun onResponse(
                    call: Call<ResponseDeliveryWrite>,
                    response: Response<ResponseDeliveryWrite>,
                ) {
                    val resp = response.body()
                    if (resp != null) {
                        when (resp.code) {
                            1000 -> deliveryWriteView.onGetDeliveryWriteResultSuccess(resp.result)
                            else -> deliveryWriteView.onGetDeliveryWriteResultFailure(resp.message)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseDeliveryWrite>, t: Throwable) {
                    Log.d("로그인 실패", t.message ?: "통신 오류")
                }
            })
    }
}
