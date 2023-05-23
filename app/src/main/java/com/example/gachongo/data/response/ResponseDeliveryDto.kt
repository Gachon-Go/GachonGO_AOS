package com.example.gachongo.data.response

data class ResponseDeliveryDto(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>,
) {
    data class Result(
        val commentNum: Int,
        val deliveryId: Int,
        val estimatedTime: String,
        val progress: String,
        val title: String,
    )
}
