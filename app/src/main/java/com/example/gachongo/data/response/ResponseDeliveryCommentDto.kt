package com.example.gachongo.data.response

import com.google.gson.annotations.SerializedName

data class ResponseDeliveryCommentDto(
    @SerializedName("code")
    val code: Int,
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: List<Result>
) {
    data class Result(
        @SerializedName("commentId")
        val commentId: Int,
        @SerializedName("commentWriter")
        val commentWriter: String,
        @SerializedName("content")
        val content: String
    )
}