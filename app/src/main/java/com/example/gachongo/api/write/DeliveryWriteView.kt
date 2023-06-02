package com.example.gachongo.api.write

import com.example.gachongo.data.BaseResponse

interface DeliveryWriteView {
    fun onGetDeliveryWriteResultSuccess(response: BaseResponse)
    fun onGetDeliveryWriteResultFailure(messageDigest: String)
}
