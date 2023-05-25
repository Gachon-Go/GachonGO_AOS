package com.example.gachongo.api

interface DeliveryWriteView {
    fun onGetDeliveryWriteResultSuccess(result: String)
    fun onGetDeliveryWriteResultFailure(messageDigest: String)
}
