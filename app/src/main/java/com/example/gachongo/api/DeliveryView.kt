package com.example.gachongo.api

interface DeliveryView {
    fun onGetDeliveryResultSuccess()
    fun onGetDeliveryResultFailure(message: String)
}
