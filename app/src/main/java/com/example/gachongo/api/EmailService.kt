package com.example.gachongo.api

import android.util.Log
import com.example.gachongo.data.BaseResponse
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmailService(val emailView: EmailView, val emailCodeView: EmailCodeView) {
    fun getEmail(email: String){
        val emailService = NetworkModule.retrofit.create(EmailInterface::class.java)

        emailService.sendEmail(email).enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val resp = response.body()
                if (resp != null) {
                    when(resp.code){
                        1000-> emailView.onGetEmailResultSuccess()
                        else-> emailView.onGetEmailResultFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("이메일 전송 실패", t.message ?: "통신 오류")
            }
        })
    }

    fun checkEmailCode(email: String, code: String){
        val emailService = NetworkModule.retrofit.create(EmailInterface::class.java)

        emailService.checkCode(email, code).enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val resp = response.body()
                if (resp != null) {
                    when(resp.code){
                        1000-> emailCodeView.onGetEmailCodeResultSuccess()
                        else-> emailCodeView.onGetEmailCodeResultFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("이메일 코드 전송 실패", t.message ?: "통신 오류")
            }
        })
    }
}