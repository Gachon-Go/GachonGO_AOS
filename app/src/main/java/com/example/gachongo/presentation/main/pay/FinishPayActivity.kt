package com.example.gachongo.presentation.main.pay

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gachongo.presentation.main.MainActivity
import com.example.gachongo_aos.databinding.ActivityFinishPayBinding

class FinishPayActivity: AppCompatActivity() {

    private lateinit var binding: ActivityFinishPayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishPayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.finishPayIv.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

    }
}