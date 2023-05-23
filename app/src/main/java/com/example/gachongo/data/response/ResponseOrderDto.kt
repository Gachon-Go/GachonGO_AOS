package com.example.gachongo.data.response

data class ResponseOrderDto(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
) {
    data class Result(
        val commentNum: Int,
        val estimatedTime: String,
        val orderId: Int,
        val progress: String,
        val title: String
    )
}