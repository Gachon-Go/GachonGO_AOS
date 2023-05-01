package com.example.gachongo // ktlint-disable package-name

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.gachongo.login.KakaoLoginActivity
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.ActivityMainBinding
import com.kakao.sdk.user.UserApiClient

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        // 로그인 정보 확인
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Log.d(ContentValues.TAG, "로그인 필요")
                startActivity(Intent(this, KakaoLoginActivity::class.java))
            }
            else if (tokenInfo != null) {   // 카카오 로그인이 이미 되어있으면
                Log.d(ContentValues.TAG, "로그인 유지 성공")
            }
        }
    }
}

