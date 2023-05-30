package com.example.gachongo.api

import com.example.gachongo.data.BaseResponse
import com.example.gachongo.data.request.RequestCommentDto
import com.example.gachongo.data.request.RequestDeliveryDto
import com.example.gachongo.data.response.ResponseDeliveryCommentDto
import com.example.gachongo.data.response.ResponseDeliveryDetailDto
import com.example.gachongo.data.response.ResponseDeliveryDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface DeliveryInterface {
    // 배달 게시물 조회
    @GET("/delivery")
    fun getDeliveryList(
        @Header("Authorization") jwt: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Call<ResponseDeliveryDto>

    // 배달 게시물 작성
    @POST("/delivery")
    fun postDelivery(
        @Header("Authorization") jwt: String,
        @Body requestBody: RequestDeliveryDto,
    ): Call<BaseResponse>

    // 배달 게시물 모집완료
    @POST("/delivery/{deliveryPostId}/done")
    fun postDeliveryDone(
        @Path("deliveryPostId") deliveryPostId: Int,
    ): Call<BaseResponse>

    // 배달 게시물 상세 댓글 조회
    @GET("/delivery/{deliveryPostId}/comment")
    fun getDeliveryDetailComment(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Path("deliveryPostId") deliveryPostId: Int,
    ): Call<ResponseDeliveryCommentDto>

    // 배달 게시물 상세 댓글 작성
    @POST("/delivery/{deliveryPostId}/comment")
    fun postDeliveryDetailComment(
        @Path("deliveryPostId") deliveryPostId: Int,
        @Body requestBody: RequestCommentDto,
    ): Call<BaseResponse>

    // 배달 게시물 고객 선택
    @POST("/delivery/{deliveryPostId}/comment/{commentId}/select")
    fun postDeliveryDetailCommentSelect(
        @Path("deliveryPostId") deliveryPostId: Int,
        @Path("commentId") commentId: Int,
    ): Call<BaseResponse>

    // 배달 게시물 상세 조회
    @GET("/delivery/{deliveryPostId}")
    fun getDeliveryDetail(
        @Path("deliveryPostId") deliveryPostId: Int,
    ): Call<ResponseDeliveryDetailDto>
}
