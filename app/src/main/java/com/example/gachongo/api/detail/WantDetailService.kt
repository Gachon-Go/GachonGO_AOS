package com.example.gachongo.api.detail

import android.util.Log
import com.example.gachongo.api.OrderInterface
import com.example.gachongo.data.BaseResponse
import com.example.gachongo.data.request.RequestCommentDto
import com.example.gachongo.data.response.ResponseOrderCommentDto
import com.example.gachongo.data.response.ResponseOrderDetailDto
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WantDetailService(val wantDetailView: WantDetailView) {
    val wantDetailService = NetworkModule.retrofit.create(OrderInterface::class.java)

    fun getDetail(jwt: String, orderPostId: Int) {
        wantDetailService.getOrderDetail(jwt, orderPostId).enqueue(object :
            Callback<ResponseOrderDetailDto> {
            override fun onResponse(
                call: Call<ResponseOrderDetailDto>,
                response: Response<ResponseOrderDetailDto>,
            ) {
                val resp = response.body()
                Log.d("GachonLog #게시글", "getDetail API 호출 결과: " + resp.toString())
                if (resp != null) {
                    when (resp.code) {
                        1000 -> wantDetailView.onGetOrderDetailResultSuccess(resp)
                        else -> wantDetailView.onGetOrderDetailResultFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseOrderDetailDto>, t: Throwable) {
                Log.d("GachonLog #게시글", "getDetail API 호출 실패")
            }
        })
    }

    fun getOrderDetailComment(jwt: String, orderPostId: Int, page: Int, size: Int) {
        wantDetailService.getOrderDetailComment(jwt, orderPostId, page, size)
            .enqueue(object : Callback<ResponseOrderCommentDto> {
                override fun onResponse(
                    call: Call<ResponseOrderCommentDto>,
                    response: Response<ResponseOrderCommentDto>,
                ) {
                    val resp = response.body()
                    if (resp != null) {
                        when (resp.code) {
                            1000 -> wantDetailView.onGetOrderCommentResultSuccess(resp.result)
                            else -> wantDetailView.onGetOrderCommentResultFailure(resp.message)
                        }
                    }
                }

                override fun onFailure(
                    call: Call<ResponseOrderCommentDto>,
                    t: Throwable,
                ) {
                    Log.d("GachonLog #게시글", "getOrderDetailComment API 호출 실패")
                }
            })
    }

    fun postOrderDetailComment(jwt: String, orderPostId: Int, body: RequestCommentDto) {
        wantDetailService.postOrderDetailComment(jwt, orderPostId, body)
            .enqueue(object : Callback<BaseResponse> {
                override fun onResponse(
                    call: Call<BaseResponse>,
                    response: Response<BaseResponse>,
                ) {
                    val resp = response.body()
                    if (resp != null) {
                        when (resp.code) {
                            1000 -> wantDetailView.onPostOrderCommentResultSuccess()
                            else -> wantDetailView.onPostOrderCommentResultFailure(resp.message)
                        }
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    Log.d("GachonLog #게시글", "postOrderDetailComment API 호출 실패")
                }
            })
    }
}
