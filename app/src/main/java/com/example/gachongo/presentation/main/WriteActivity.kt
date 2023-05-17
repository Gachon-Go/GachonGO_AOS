package com.example.gachongo.presentation.main

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.ActivityWriteBinding
import java.util.Calendar

class WriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        initWriteFinishBtnClickLisnter()
        initTimpickerClickListener()
    }

    fun initWriteFinishBtnClickLisnter() {
        binding.btnWriteFinish.setOnClickListener() {
            // 글쓰기 완료 버튼
        }
    }

    fun initTimpickerClickListener() {
        binding.btnWriteTime.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                binding.btnWriteTime.text = "$hourOfDay: $minute"
            }
            TimePickerDialog(
                this,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true,
            ).show()
        }
    }
}
