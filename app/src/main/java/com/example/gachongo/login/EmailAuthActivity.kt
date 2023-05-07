package com.example.gachongo.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gachongo_aos.databinding.ActivityEmailAuthBinding

class EmailAuthActivity: AppCompatActivity() {

    lateinit var binding: ActivityEmailAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}