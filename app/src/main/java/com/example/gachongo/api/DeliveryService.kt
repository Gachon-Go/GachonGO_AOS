package com.example.gachongo.api

import com.example.gachongo.data.BaseResponse
import com.example.gachongo.data.request.RequestCommentDto
import com.example.gachongo.data.request.RequestDeliveryDto
import com.example.gachongo.data.response.ResponseDeliveryCommentDto
import com.example.gachongo.data.response.ResponseDeliveryDetailDto
import com.example.gachongo.data.response.ResponseDeliveryDto
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeliveryService(val deliveryView: DeliveryView) {
    val deliveryService = NetworkModule.retrofit.create(DeliveryInterface::class.java)

    fun getItemList() {
        deliveryService.getDeliveryList().enqueue(object :
            Callback<List<ResponseDeliveryDto.Result>> {
            override fun onResponse(
                call: Call<List<ResponseDeliveryDto.Result>>,
                response: Response<List<ResponseDeliveryDto.Result>>,
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<List<ResponseDeliveryDto.Result>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun postDelivery(body: RequestDeliveryDto) {
        deliveryService.postDelivery(body).enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val resp = response.body()
                if (resp != null) {
                    when (resp.code) {
                        1000 -> deliveryView.onGetDeliveryResultSuccess()
                        else -> deliveryView.onGetDeliveryResultFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun postDeliveryDone(deliveryPostId: Int) {
        deliveryService.postDeliveryDone(deliveryPostId).enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val resp = response.body()
                if (resp != null) {
                    when (resp.code) {
                        1000 -> deliveryView.onGetDeliveryResultSuccess()
                        else -> deliveryView.onGetDeliveryResultFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getDeliveryDetailComment(deliveryPostId: Int) {
        deliveryService.getDeliveryDetailComment(deliveryPostId)
            .enqueue(object : Callback<List<ResponseDeliveryCommentDto.Result>> {
                override fun onResponse(
                    call: Call<List<ResponseDeliveryCommentDto.Result>>,
                    response: Response<List<ResponseDeliveryCommentDto.Result>>,
                ) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(
                    call: Call<List<ResponseDeliveryCommentDto.Result>>,
                    t: Throwable,
                ) {
                    TODO("Not yet implemented")
                }
            })
    }

    fun postDeliveryDetailComment(deliveryPostId: Int, body: RequestCommentDto) {
        deliveryService.postDeliveryDetailComment(deliveryPostId, body)
            .enqueue(object : Callback<BaseResponse> {
                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>,
                ) {
                    val resp = response.body()
                    if (resp != null) {
                        when (resp.code) {
                            1000 -> deliveryView.onGetDeliveryResultSuccess()
                            else -> deliveryView.onGetDeliveryResultFailure(resp.message)
                        }
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

    fun postDeliveryDetailCommentSelect(deliveryPostId: Int, commentId: Int) {
        deliveryService.postDeliveryDetailCommentSelect(deliveryPostId, commentId)
            .enqueue(object : Callback<BaseResponse> {
                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>,
                ) {
                    val resp = response.body()
                    if (resp != null) {
                        when (resp.code) {
                            1000 -> deliveryView.onGetDeliveryResultSuccess()
                            else -> deliveryView.onGetDeliveryResultFailure(resp.message)
                        }
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

    fun getDeliveryDetail(deliveryPostId: Int) {
        deliveryService.getDeliveryDetail(deliveryPostId)
            .enqueue(object : Callback<ResponseDeliveryDetailDto> {
                override fun onResponse(
                    call: Call<ResponseDeliveryDetailDto>,
                    response: Response<ResponseDeliveryDetailDto>,
                ) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<ResponseDeliveryDetailDto>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
}
