package com.example.gachongo.api.detail

import android.util.Log
import com.example.gachongo.api.DeliveryInterface
import com.example.gachongo.data.BaseResponse
import com.example.gachongo.data.request.RequestCommentDto
import com.example.gachongo.data.response.ResponseDeliveryCommentDto
import com.example.gachongo.data.response.ResponseDeliveryDetailDto
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GoDetailService(val goDetailView: GoDetailView) {
    val goDetailService = NetworkModule.retrofit.create(DeliveryInterface::class.java)

    fun getDetail(jwt: String, deliveryPostId: Int) {
        goDetailService.getDeliveryDetail(jwt, deliveryPostId).enqueue(object :
            Callback<ResponseDeliveryDetailDto> {
            override fun onResponse(
                call: Call<ResponseDeliveryDetailDto>,
                response: Response<ResponseDeliveryDetailDto>,
            ) {
                val resp = response.body()
                Log.d("GachonLog #게시글", "getDetail API 결과: " + resp.toString())
                if (resp != null) {
                    when (resp.code) {
                        1000 -> goDetailView.onGetDeliveryDetailResultSuccess(resp)
                        else -> goDetailView.onGetDeliveryDetailResultFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDeliveryDetailDto>, t: Throwable) {
                Log.d("GachonLog #게시글", "getDetail API 연결 실패")
            }
        })
    }

    fun getDeliveryDetailComment(jwt: String, deliveryPostId: Int, page: Int, size: Int) {
        goDetailService.getDeliveryDetailComment(jwt, deliveryPostId, page, size)
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
                    Log.d("GachonLog #게시글", "getDeliveryDetailComment API 연결 실패")
                }
            })
    }

    fun postDeliveryDetailComment(jwt: String, deliveryPostId: Int, body: RequestCommentDto) {
        goDetailService.postDeliveryDetailComment(jwt, deliveryPostId, body)
            .enqueue(object : Callback<BaseResponse> {
                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>,
                ) {
                    val resp = response.body()
                    if (resp != null) {
                        when (resp.code) {
                            1000 -> goDetailView.onPostDeliveryCommentResultSuccess()
                            else -> goDetailView.onPostDeliveryCommentResultFailure(resp.message)
                        }
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    Log.d("GachonLog #게시글", "postDeliveryDetailComment API 연결 실패")
                }
            })
    }

    fun postDeliveryDone(jwt: String, deliveryPostId: Int) {
        goDetailService.postDeliveryDone(jwt, deliveryPostId)
            .enqueue(object : Callback<BaseResponse> {
                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>,
                ) {
                    val resp = response.body()
                    if (resp != null) {
                        when (resp.code) {
                            1000 -> goDetailView.onPostDeliveryDoneResultSuccess()
                            else -> goDetailView.onPostDeliveryDoneResultFailure(resp.message)
                        }
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    Log.d("GachonLog #게시글", "postDeliveryDone API 연결 실패")
                }
            })
    }
}
