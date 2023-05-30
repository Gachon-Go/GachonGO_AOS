package com.example.gachongo.presentation.main.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.gachongo_aos.databinding.ActivityKakaoPayBinding

class KakaoPayActivity: AppCompatActivity() {
    private lateinit var binding: ActivityKakaoPayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakaoPayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent

        // 웹뷰에서 새 창이 뜨지 않도록 방지
//        binding.kakaoPayWv.webViewClient = object: WebViewClient() {
//            override fun onPageFinished(view: WebView?, url: String?) {
//                super.onPageFinished(view, url)
//                Log.d("카카오페이", "onPageFinished()")
//            }
//        }
        binding.kakaoPayWv.webChromeClient = WebChromeClient()

        // 자바 스크립트 허용
        binding.kakaoPayWv.settings.javaScriptEnabled = true

        binding.kakaoPayWv.loadUrl(intent.getStringExtra("url").toString())
    }
}