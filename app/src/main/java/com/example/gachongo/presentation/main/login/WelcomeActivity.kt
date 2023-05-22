package com.example.gachongo.presentation.main.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gachongo_aos.databinding.ActivityWelcomeBinding

class WelcomeActivity: AppCompatActivity() {

    lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}