package com.example.gachongo.api.write

interface DeliveryWriteView {
    fun onGetDeliveryWriteResultSuccess()
    fun onGetDeliveryWriteResultFailure(messageDigest: String)
}
