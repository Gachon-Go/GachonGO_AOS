package com.example.gachongo.api.detail

import com.example.gachongo.api.DeliveryInterface
import com.example.gachongo.data.response.ResponseDeliveryCommentDto
import com.example.gachongo.data.response.ResponseDeliveryDetailDto
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GoDetailService(val goDetailView: GoDetailView) {
    val goDetailService = NetworkModule.retrofit.create(DeliveryInterface::class.java)

    fun getDetail(deliveryPostId: Int) {
        goDetailService.getDeliveryDetail(deliveryPostId).enqueue(object :
            Callback<ResponseDeliveryDetailDto> {
            override fun onResponse(
                call: Call<ResponseDeliveryDetailDto>,
                response: Response<ResponseDeliveryDetailDto>,
            ) {
                val resp = response.body()
                if (resp != null) {
                    when (resp.code) {
                        1000 -> goDetailView.onGetDeliveryDetailResultSuccess(resp)
                        else -> goDetailView.onGetDeliveryDetailResultFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDeliveryDetailDto>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getDeliveryDetailComment(page: Int, size: Int, deliveryPostId: Int) {
        goDetailService.getDeliveryDetailComment(page, size, deliveryPostId)
            .enqueue(object : Callback<ResponseDeliveryCommentDto> {
                override fun onResponse(
                    call: Call<ResponseDeliveryCommentDto>,
                    response: Response<ResponseDeliveryCommentDto>,
                ) {
                    val resp = response.body()
                    if (resp != null) {
                        when (resp.code) {
                            1000 -> goDetailView.onGetDeliveryCommentResultSuccess(resp.result)
                            else -> goDetailView.onGetDeliveryCommentResultFailure(resp.message)
                        }
                    }
                }

                override fun onFailure(
                    call: Call<ResponseDeliveryCommentDto>,
                    t: Throwable,
                ) {
                    TODO("Not yet implemented")
                }
            })
    }
}
