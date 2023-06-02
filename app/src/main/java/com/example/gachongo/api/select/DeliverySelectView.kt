package com.example.gachongo.api.select

interface DeliverySelectView {
    fun onGetDeliverySelectResultSuccess()

    fun onDeliverySelectResultFailure(message: String)
}