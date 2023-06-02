package com.example.gachongo.data.response

import com.google.gson.annotations.SerializedName

data class ResponseDeliveryDetailDto(
    @SerializedName("code")
    val code: Int,
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: Result,
) {
    data class Result(
        @SerializedName("commentNum")
        val commentNum: Int,
        @SerializedName("content")
        val content: String,
        @SerializedName("estimatedTime")
        val estimatedTime: String,
        @SerializedName("mine")
        val mine: Boolean,
        @SerializedName("title")
        val title: String,
        @SerializedName("writer")
        val writer: String,
        @SerializedName("writerImage")
        val writerImage: String,
    )
}
