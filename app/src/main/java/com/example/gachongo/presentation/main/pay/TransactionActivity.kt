package com.example.gachongo.presentation.main.pay

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.gachongo.api.TransactionIdService
import com.example.gachongo.api.TransactionIdView
import com.example.gachongo.data.TransactionIdResult
import com.example.gachongo.util.extension.backPressed
import com.example.gachongo.util.extension.hideKeyboard
import com.example.gachongo.util.getUserJwt
import com.example.gachongo_aos.databinding.ActivityTransactionBinding

class TransactionActivity: AppCompatActivity(), TransactionIdView {
    private lateinit var binding: ActivityTransactionBinding
    private var transactionId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getTransactionId()

        binding.transactionNextBtn.isEnabled = false

        binding.transactionEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                transactionId = s.toString()
                binding.transactionNextBtn.isEnabled = s.toString().length == 6
            }

        })

        binding.transactionNextBtn.setOnClickListener {
            val intent = Intent(this, CodePayActivity::class.java)
            intent.putExtra("id", transactionId)
            startActivity(intent)
        }

        binding.codePayMainCl.setOnClickListener {
            hideKeyboard()
        }

        binding.transactionBackIv.setOnClickListener {
            backPressed()
        }
    }


    private fun getTransactionId() {
        val transactionService = TransactionIdService(this)
        transactionService.getTransactionCode(getUserJwt(this))
    }

    override fun onGetTransactionIdResultSuccess(result: TransactionIdResult) {
        binding.transactionIdTv.text = result.transactionId
    }

    override fun onGetTransactionIdResultFailure(message: String) {
        Log.d("GachonLog #거래 고유번호", "실패")
    }
}