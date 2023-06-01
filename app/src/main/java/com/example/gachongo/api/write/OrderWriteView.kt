package com.example.gachongo.api.write

import com.example.gachongo.data.BaseResponse

interface OrderWriteView {
    fun onGetWantWriteResultSuccess(response: BaseResponse)
    fun onGetWantWriteResultFailure(messageDigest: String)
}