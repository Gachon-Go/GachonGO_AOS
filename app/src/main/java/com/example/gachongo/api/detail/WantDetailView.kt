package com.example.gachongo.api.detail

import com.example.gachongo.data.response.ResponseOrderCommentDto
import com.example.gachongo.data.response.ResponseOrderDetailDto

interface WantDetailView {
    fun onGetOrderDetailResultSuccess(result: ResponseOrderDetailDto)
    fun onGetOrderDetailResultFailure(message: String)

    fun onGetOrderCommentResultSuccess(result: MutableList<ResponseOrderCommentDto.Result>)
    fun onGetOrderCommentResultFailure(message: String)

    fun onPostOrderCommentResultSuccess()
    fun onPostOrderCommentResultFailure(message: String)
}
