package com.example.gachongo.api

import android.util.Log
import com.example.gachongo.data.Login
import com.example.gachongo.data.LoginResponse
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val loginView: LoginView) {
    fun login(login: Login) {
        val loginService = NetworkModule.retrofit.create(LoginInterface::class.java)

        loginService.login(login).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val resp = response.body()
                if (resp != null) {
                    when (resp.code) {
                        1000 -> loginView.onGetLoginResultSuccess(resp.result)
                        else -> loginView.onGetLoginResultFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("로그인 실패", t.message ?: "통신 오류")
            }
        })
    }
}
