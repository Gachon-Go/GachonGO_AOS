package com.example.gachongo

import android.app.Application
import com.example.gachongo_aos.BuildConfig
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, BuildConfig.KAKAO_API_KEY)
    }
}