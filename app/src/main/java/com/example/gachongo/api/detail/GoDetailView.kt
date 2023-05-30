package com.example.gachongo.api.detail

import com.example.gachongo.data.response.ResponseDeliveryCommentDto
import com.example.gachongo.data.response.ResponseDeliveryDetailDto

interface GoDetailView {
    fun onGetDeliveryDetailResultSuccess(result: ResponseDeliveryDetailDto)
    fun onGetDeliveryDetailResultFailure(message: String)

    fun onGetDeliveryCommentResultSuccess(result: MutableList<ResponseDeliveryCommentDto.Result>)
    fun onGetDeliveryCommentResultFailure(message: String)
}
