package com.example.gachongo.api

import android.util.Log
import com.example.gachongo.data.BaseResponse
import com.example.gachongo.data.User
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpService(val signupView: SignUpView) {
    fun signUp(user: User){
        val signUpService = NetworkModule.retrofit.create(SignUpInterface::class.java)

        signUpService.signUp(user).enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val resp = response.body()
                if (resp != null) {
                    when(resp.code){
                        1000-> signupView.onGetSignUpResultSuccess()
                        else-> signupView.onGetSignUpResultFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("회원가입 실패", t.message ?: "통신 오류")
            }
        })
    }
}