package com.example.gachongo.data.response

import com.google.gson.annotations.SerializedName

data class ResponseOrderCommentDto(
    @SerializedName("code")
    val code: Int,
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: MutableList<ResponseDeliveryDto.Result>,
) {
    data class Result(
        val commentId: Int,
        val commentWriter: String,
        val content: String,
    )
}
