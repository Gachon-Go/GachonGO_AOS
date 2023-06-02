package com.example.gachongo.api.select

import com.example.gachongo.api.OrderInterface
import com.example.gachongo.data.BaseResponse
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderSelectService(val orderSelectView: OrderSelectView) {
    val orderSelectService = NetworkModule.retrofit.create(OrderInterface::class.java)

    fun postOrderCommentSelect(jwt: String, orderPostId: Int, commentId: Int) {
        orderSelectService.postOrderDetailCommentSelect(jwt, orderPostId, commentId).enqueue(object : Callback<BaseResponse>{
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val resp = response.body()
                if (resp != null) {
                    when (resp.code) {
                        1000 -> orderSelectView.onGetOrderSelectResultSuccess()
                        else -> orderSelectView.onGetOrderSelectResultFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
//
        })
    }
}
