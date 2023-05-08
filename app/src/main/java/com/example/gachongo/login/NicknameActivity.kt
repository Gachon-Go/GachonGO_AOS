package com.example.gachongo.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gachongo_aos.databinding.ActivityNicknameBinding

class NicknameActivity: AppCompatActivity() {

    lateinit var binding: ActivityNicknameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNicknameBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}