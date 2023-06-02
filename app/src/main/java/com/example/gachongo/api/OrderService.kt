package com.example.gachongo.api

import com.example.gachongo.data.response.ResponseOrderDto
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderService(val orderView: OrderView) {
    val orderService = NetworkModule.retrofit.create(OrderInterface::class.java)
    fun getItemList(jwt: String, page: Int, size: Int) {
        orderService.getOrderList(jwt, page, size).enqueue(object :
            Callback<ResponseOrderDto> {
            override fun onResponse(
                call: Call<ResponseOrderDto>,
                response: Response<ResponseOrderDto>,
            ) {
                val resp = response.body()
                if (resp != null) {
                    when (resp.code) {
                        1000 -> orderView.onGetOrderResultSuccess(resp.result)
                        else -> orderView.onGetOrderResultFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseOrderDto>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}
