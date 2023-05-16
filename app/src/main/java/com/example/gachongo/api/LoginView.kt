package com.example.gachongo.api

import com.example.gachongo.data.LoginResponseResult

interface LoginView {
    fun onGetLoginResultSuccess(result: LoginResponseResult)
    fun onGetLoginResultFailure(message: String)
}