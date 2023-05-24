package com.example.gachongo.presentation.main.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gachongo.api.SignUpService
import com.example.gachongo.api.SignUpView
import com.example.gachongo.data.User
import com.example.gachongo.presentation.main.MainActivity
import com.example.gachongo.util.extension.showToast
import com.example.gachongo_aos.databinding.ActivityWelcomeBinding
import getUserEmail
import getUserLoginProvider
import getUserNickname
import getUserToken

class WelcomeActivity: AppCompatActivity(), SignUpView {
    private lateinit var binding: ActivityWelcomeBinding

    private var nickname: String = ""
    private var email: String = ""
    private var provider: String = ""
    private var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 정보 가져오기
        nickname = getUserNickname(this)
        email = getUserEmail(this)
        provider = getUserLoginProvider(this)
        token = getUserToken(this)

        binding.welcomeNicknameTv.text = getUserNickname(this)

        binding.welcomeCloseIv.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {
        val signUpService = SignUpService(this)
        signUpService.signUp(User(nickname, email, provider, token))
    }

    override fun onGetSignUpResultSuccess() {
        startActivity(Intent(this, MainActivity::class.java))
        finishAffinity()
    }

    override fun onGetSignUpResultFailure(message: String) {
        showToast(message)
    }

}