package com.example.gachongo.presentation.main.pay

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.gachongo.api.PayService
import com.example.gachongo.api.PayView
import com.example.gachongo.api.TransactionIdService
import com.example.gachongo.api.TransactionIdView
import com.example.gachongo.data.Point
import com.example.gachongo.data.TransactionIdResult
import com.example.gachongo.util.extension.backPressed
import com.example.gachongo.util.extension.hideKeyboard
import com.example.gachongo.util.getUserJwt
import com.example.gachongo_aos.databinding.ActivityCodePayBinding

class CodePayActivity: AppCompatActivity(), PayView {
    private lateinit var binding: ActivityCodePayBinding
    private var payAmount: Int = 0
    private var id: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCodePayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getStringExtra("id").toString()
        binding.codePayNextBtn.isEnabled = false

        binding.codePayAmountPointEt.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(s.isNullOrEmpty()) binding.codePayNextBtn.isEnabled = false
                else {
                    binding.codePayNextBtn.isEnabled = true
                    payAmount = s.toString().toInt()
                }
            }
        })

        binding.codePayNextBtn.setOnClickListener {
            sendPay()
        }

        binding.codePayBackIv.setOnClickListener {
            backPressed()
        }

        binding.codePayMainCl.setOnClickListener {
            hideKeyboard()
        }
    }

    private fun sendPay() {
        Log.d("결제", "포인트 전송 API 실행")
        val payService = PayService(this)
        payService.sendPay(getUserJwt(this), id, Point(payAmount))
    }

    override fun onSendPayResultSuccess() {
        Log.d("결제", "성공")
        startActivity(Intent(this, FinishPayActivity::class.java))
    }

    override fun onSendPayResultFailure(message: String) {
        Log.d("결제", "실패 $message")
    }
}