package com.example.gachongo.api.select

import com.example.gachongo.api.DeliveryInterface
import com.example.gachongo.data.BaseResponse
import com.example.gachongo.presentation.main.home.detail.CommentAdapter
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeliverySelectService(val deliverySelectView: DeliverySelectView) {
    val deliverySelectService = NetworkModule.retrofit.create(DeliveryInterface::class.java)

    fun postDeliveryDetailCommentSelect(jwt: String, deliveryPostId: Int, commentId: Int) {
        deliverySelectService.postDeliveryDetailCommentSelect(jwt, deliveryPostId, commentId)
            .enqueue(object : Callback<BaseResponse> {
                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>,
                ) {
                    val resp = response.body()
                    if (resp != null) {
                        when (resp.code) {
                            1000 -> deliverySelectView.onGetDeliverySelectResultSuccess()
                            else -> deliverySelectView.onDeliverySelectResultFailure(resp.message)
                        }
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
}
