package com.example.gachongo.presentation.main.mypage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.gachongo.api.ChargePointService
import com.example.gachongo.api.ChargePointView
import com.example.gachongo.data.ChargePoint
import com.example.gachongo.data.ChargePointResult
import com.example.gachongo.util.extension.backPressed
import com.example.gachongo.util.extension.hideKeyboard
import com.example.gachongo.util.getUserJwt
import com.example.gachongo.util.getUserPoint
import com.example.gachongo.util.saveUserPoint
import com.example.gachongo_aos.databinding.ActivityPointChargeBinding


class PointChargeActivity: AppCompatActivity(), ChargePointView {
    private lateinit var binding: ActivityPointChargeBinding
    private var point: Int = 0
    private var chargePoint: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPointChargeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        point = getUserPoint(this)

        initView()

        binding.pointChargeAmountPointEt.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                chargePoint = s.toString().toInt()
            }
        })

        binding.pointChargeBackIv.setOnClickListener {
            backPressed()
        }

        binding.pointChargeMainCl.setOnClickListener {
            hideKeyboard()
            point += chargePoint
            binding.pointChargeAfterChargePointTv.text = point.toString()
        }

        binding.pointChargeFinishBtn.setOnClickListener {
            sendChargePoint()
        }
    }

    private fun initView() {
        // 뷰 시작할 때 edittext 포커스 맞추고 키보드 자동으로 올리기
        binding.pointChargeAmountPointEt.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding.pointChargeAmountPointEt.postDelayed({
            imm.showSoftInput(binding.pointChargeAmountPointEt, InputMethodManager.SHOW_IMPLICIT)
        }, 200)

        // 충전 전 금액
        binding.pointChargeAfterChargePointTv.text = point.toString()
    }

    private fun sendChargePoint() {
        val chargePointService = ChargePointService(this)
        chargePointService.sendChargePoint(getUserJwt(this), ChargePoint(chargePoint))
    }

    override fun onSendChargePointResultSuccess(result: ChargePointResult) {
        saveUserPoint(this, point)
        val intent = Intent(this, KakaoPayActivity::class.java)
        intent.putExtra("url", result.next_redirect_app_url)
        startActivity(intent)
        finish()
    }

    override fun onSendChargePointResultFailure(message: String) {
        Log.d("포인트 충전", "실패")
    }
}