package com.example.gachongo.api

import com.example.gachongo.data.response.ResponseDeliveryDto

interface DeliveryView {
    fun onGetDeliveryResultSuccess(result: MutableList<ResponseDeliveryDto.Result>)
    fun onGetDeliveryResultFailure(message: String)

}
