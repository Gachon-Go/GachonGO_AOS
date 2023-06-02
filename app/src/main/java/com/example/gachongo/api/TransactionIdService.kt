package com.example.gachongo.api

import android.util.Log
import com.example.gachongo.data.TransactionIdResponse
import com.example.gachongo.util.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionIdService(val transactionIdView: TransactionIdView) {

    private var payService: PayInterface = NetworkModule.retrofit.create(PayInterface::class.java)

    fun getTransactionCode(jwt: String) {
        payService.getTransactionCode(jwt).enqueue(object : Callback<TransactionIdResponse> {
            override fun onResponse(call: Call<TransactionIdResponse>, response: Response<TransactionIdResponse>) {
                val resp = response.body()
                if (resp != null) {
                    when(resp.code){
                        1000-> transactionIdView.onGetTransactionIdResultSuccess(resp.result)
                        else-> transactionIdView.onGetTransactionIdResultFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<TransactionIdResponse>, t: Throwable) {
                Log.d("GachonLog #결제 고유번호 발급 실패", t.message ?: "통신 오류")
            }
        })
    }
}