package com.example.gachongo.api

import com.example.gachongo.data.BaseResponse
import com.example.gachongo.data.request.RequestCommentDto
import com.example.gachongo.data.response.ResponseDeliveryDetailDto
import com.example.gachongo.data.response.ResponseDeliveryDto
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeliveryService(val deliveryView: DeliveryView) {
    val deliveryService = NetworkModule.retrofit.create(DeliveryInterface::class.java)

    fun getItemList(jwt: String, page: Int, size: Int) {
        deliveryService.getDeliveryList(jwt, page, size).enqueue(object :
            Callback<ResponseDeliveryDto> {
            override fun onResponse(
                call: Call<ResponseDeliveryDto>,
                response: Response<ResponseDeliveryDto>,
            ) {
                val resp = response.body()
                if (resp != null) {
                    when (resp.code) {
                        1000 -> deliveryView.onGetDeliveryResultSuccess(resp.result)
                        else -> deliveryView.onGetDeliveryResultFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDeliveryDto>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

//    fun postDeliveryDone(deliveryPostId: Int) {
//        deliveryService.postDeliveryDone(deliveryPostId).enqueue(object : Callback<BaseResponse> {
//            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
////                val resp = response.body()
////                if (resp != null) {
////                    when (resp.code) {
////                        1000 -> deliveryView.onGetDeliveryResultSuccess()
////                        else -> deliveryView.onGetDeliveryResultFailure(resp.message)
////                    }
////                }
//            }
//
//            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })
//    }
//

//
}
