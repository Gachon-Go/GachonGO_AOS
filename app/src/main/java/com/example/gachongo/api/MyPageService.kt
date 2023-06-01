package com.example.gachongo.api

import android.util.Log
import com.example.gachongo.data.MyPageResponse
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageService(val myPageView: MyPageView) {
    fun getMyPage(jwt: String){
        val myPageService = NetworkModule.retrofit.create(MyPageInterface::class.java)

        myPageService.getMyPage(jwt).enqueue(object : Callback<MyPageResponse> {
            override fun onResponse(call: Call<MyPageResponse>, response: Response<MyPageResponse>) {
                val resp = response.body()
                if (resp != null) {
                    when(resp.code){
                        1000-> myPageView.onGetMyPageSuccess(resp.result)
                        else-> myPageView.onGetMyPageFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<MyPageResponse>, t: Throwable) {
                Log.d("닉네임 변경 실패", t.message ?: "통신 오류")
            }
        })
    }
}