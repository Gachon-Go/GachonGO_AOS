package com.example.gachongo // ktlint-disable package-name

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gachongo_aos.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

