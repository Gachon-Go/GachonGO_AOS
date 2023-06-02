package com.example.gachongo.presentation.main.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceRequest
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

        binding.kakaoPayWv.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request?.url.toString()
                return if (url.startsWith("http://") || url.startsWith("https://")) {
                    finish()
                    false
                } else {
                    val intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                    val existPackage = packageManager.getLaunchIntentForPackage(intent.getPackage()!!)
                    if (existPackage != null) startActivity(intent)
                    true
                }
            }
        }


        // 자바 스크립트 허용
        binding.kakaoPayWv.settings.javaScriptEnabled = true
        binding.kakaoPayWv.loadUrl(intent.getStringExtra("url").toString())

        Log.d("GachonLog #결제", "카카오페이 결제 페이지로 이동")
    }
}
