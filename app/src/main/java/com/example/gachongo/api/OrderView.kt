package com.example.gachongo.api

import com.example.gachongo.data.response.ResponseOrderDto

interface OrderView {
    fun onGetOrderResultSuccess(result: MutableList<ResponseOrderDto.Result>)
    fun onGetOrderResultFailure(message: String)
}
