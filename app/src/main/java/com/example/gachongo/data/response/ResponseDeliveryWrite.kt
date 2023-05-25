package com.example.gachongo.data.response

data class ResponseDeliveryWrite(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: String
)