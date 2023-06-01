package com.example.gachongo.api

import com.example.gachongo.data.BaseResponse
import com.example.gachongo.data.request.RequestCommentDto
import com.example.gachongo.data.request.RequestOrderDto
import com.example.gachongo.data.response.ResponseOrderCommentDto
import com.example.gachongo.data.response.ResponseOrderDetailDto
import com.example.gachongo.data.response.ResponseOrderDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface OrderInterface {
    // 주문 게시물 조회
    @GET("/order")
    fun getOrderList(
        @Header("Authorization") jwt: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): Call<ResponseOrderDto>

    // 주문 게시물 작성
    @POST("/order")
    fun postOrder(
        @Header("Authorization") jwt: String,
        @Body requestBody: RequestOrderDto,
    ): Call<BaseResponse>

    // 주문 게시물 모집완료
    @POST("/order/{orderPostId}/done")
    fun postOrderDone(
        @Header("Authorization") jwt: String,
        @Path("orderPostId") orderPostId: Int,
    ): Call<BaseResponse>

    // 주문 게시물 상세 댓글 조회
    @GET("/order/{orderPostId}/comment")
    fun getOrderDetailComment(
        @Header("Authorization") jwt: String,
        @Path("orderPostId") orderPostId: Int,
    ): Call<List<ResponseOrderCommentDto.Result>>

    // 주문 게시물 상세 댓글 작성
    @POST("/order/{orderPostId}/comment")
    fun postOrderDetailComment(
        @Header("Authorization") jwt: String,
        @Path("orderPostId") orderPostId: Int,
        @Body requestBody: RequestCommentDto,
    ): Call<BaseResponse>

    // 주문 게시물 고객 선택
    @POST("/order/{orderPostId}/comment/{commentId}/select")
    fun postOrderDetailCommentSelect(
        @Header("Authorization") jwt: String,
        @Path("orderPostId") orderPostId: Int,
        @Path("commentId") commentId: Int,
    ): Call<BaseResponse>

    // 주문 게시물 상세 조회
    @GET("/order/{orderPostId}")
    fun getOrderDetail(
        @Header("Authorization") jwt: String,
        @Path("orderPostId") orderPostId: Int,
    ): Call<ResponseOrderDetailDto>
}
