package com.example.gachongo.data.response

import com.google.gson.annotations.SerializedName

data class ResponseOrderDto(
    @SerializedName("code")
    val code: Int,
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: MutableList<Result>,
) {
    data class Result(
        @SerializedName("commentNum")
        val commentNum: Int,
        @SerializedName("estimatedTime")
        val estimatedTime: String,
        @SerializedName("orderId")
        val orderId: Int,
        @SerializedName("progress")
        val progress: String,
        @SerializedName("title")
        val title: String
    )
}