package com.example.gachongo.api

import android.util.Log
import com.example.gachongo.data.BaseResponse
import com.example.gachongo.data.Nickname
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NicknameEditService(val nicknameEditView: NicknameEditView) {
    fun editNickname(jwt: String, nickname: Nickname){
        val nicknameEditService = NetworkModule.retrofit.create(NicknameInterface::class.java)

        nicknameEditService.editNickname(jwt, nickname).enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val resp = response.body()
                if (resp != null) {
                    when(resp.code){
                        1000-> nicknameEditView.onGetNicknameEditSuccess()
                        else-> nicknameEditView.onGetNicknameEditFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("GachonLog #유저페이지", t.message ?: "닉네임 변경 실패")
            }
        })
    }
}