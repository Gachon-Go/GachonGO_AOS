package com.example.gachongo.api

import com.example.gachongo.data.MyPageResponseResult

interface MyPageView {
    fun onGetMyPageSuccess(result: MyPageResponseResult)
    fun onGetMyPageFailure(message: String)
}