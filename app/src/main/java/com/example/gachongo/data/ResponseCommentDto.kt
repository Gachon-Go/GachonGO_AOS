package com.example.gachongo.data

data class ResponseCommentDto(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>,
) {
    data class Result(
        val commentId: Int,
        val commentWriter: String,
        val content: String,
    )
}
