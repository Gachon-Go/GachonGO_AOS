package com.example.gachongo.api

import android.util.Log
import com.example.gachongo.data.BaseResponse
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NicknameService(val nicknameView: NicknameView) {
    fun checkNickname(nickname: String){
        val nicknameService = NetworkModule.retrofit.create(NicknameInterface::class.java)

        nicknameService.checkNickname(nickname).enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val resp = response.body()
                if (resp != null) {
                    when(resp.code){
                        1000-> nicknameView.onGetNicknameResultSuccess()
                        else-> nicknameView.onGetNicknameResultFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("GachonLog #로그인", "checkNickName API 호출 실패")
            }
        })
    }
}