package com.example.gachongo.api

import android.util.Log
import com.example.gachongo.data.BaseResponse
import com.example.gachongo.data.Nickname
import com.example.gachongo.util.NetworkModule
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileEditService(val profileEditView: ProfileEditView) {
    fun editProfile(jwt: String, profile: MultipartBody.Part){
        val profileEditService = NetworkModule.retrofit.create(ProfileEditInterface::class.java)

        profileEditService.editProfile(jwt, profile).enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val resp = response.body()
                if (resp != null) {
                    when(resp.code){
                        1000-> profileEditView.onGetProfileEditSuccess()
                        else-> profileEditView.onGetProfileEditFailure(resp.message)
                    }
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("프로필 이미지 변경 실패", t.message ?: "통신 오류")
            }
        })
    }
}