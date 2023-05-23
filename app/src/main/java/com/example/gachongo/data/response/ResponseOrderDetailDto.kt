package com.example.gachongo.data.response

data class ResponseOrderDetailDto(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
) {
    data class Result(
        val commentNum: Int,
        val content: String,
        val estimatedTime: String,
        val mine: Boolean,
        val title: String,
        val writer: String
    )
}