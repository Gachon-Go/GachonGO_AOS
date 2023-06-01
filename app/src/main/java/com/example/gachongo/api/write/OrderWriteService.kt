package com.example.gachongo.api.write

import android.util.Log
import com.example.gachongo.api.OrderInterface
import com.example.gachongo.data.BaseResponse
import com.example.gachongo.data.request.RequestOrderDto
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderWriteService(val orderWriteView: OrderWriteView) {
    val wantWriteService = NetworkModule.retrofit.create(OrderInterface::class.java)
    fun postOrder(jwt: String, requestBody: RequestOrderDto) {
        wantWriteService.postOrder(jwt, requestBody)
            .enqueue(object : Callback<BaseResponse> {
                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>,
                ) {
                    val resp = response.body()
                    if (resp != null) {
                        when (resp.code) {
                            1000 -> orderWriteView.onGetWantWriteResultSuccess(resp)
                            else -> orderWriteView.onGetWantWriteResultFailure(resp.message)
                        }
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    Log.d("글 작성 실패", t.message ?: "통신 오류")
                }
            })
    }
}
