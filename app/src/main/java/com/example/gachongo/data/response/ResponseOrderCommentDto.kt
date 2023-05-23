package com.example.gachongo.data.response

data class ResponseOrderCommentDto(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
) {
    data class Result(
        val commentId: Int,
        val commentWriter: String,
        val content: String
    )
}