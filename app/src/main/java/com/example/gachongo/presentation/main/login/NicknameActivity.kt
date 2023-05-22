package com.example.gachongo.presentation.main.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.gachongo.api.EmailService
import com.example.gachongo.api.NicknameService
import com.example.gachongo.api.NicknameView
import com.example.gachongo.util.extension.backPressed
import com.example.gachongo.util.extension.hideKeyboard
import com.example.gachongo.util.extension.showToast
import com.example.gachongo.util.saveUserNickname
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.ActivityNicknameBinding

class NicknameActivity: AppCompatActivity(), NicknameView {
    private lateinit var binding: ActivityNicknameBinding
    private lateinit var nickname: String

    private var isValidNickname: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNicknameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nicknameEt.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nickname = p0.toString()

                if(nickname.isNotEmpty()) { checkNicknameValid() }
                else {
                    // 닉네임 전부 다 지우면 중복 확인 버튼과 다음 버튼 초기화
                    binding.nicknameCheckIv.setImageResource(R.drawable.ic_check_disabled)

                    binding.nicknameNextBtn.isEnabled = false
                    binding.nicknameNextBtn.setTextColor(getColor(R.color.gray700))
                    binding.nicknameNextBtn.setBackgroundResource(R.drawable.bg_button_default)
                }
            }
        })

        binding.nicknameNextBtn.setOnClickListener {
            saveUserNickname(this, nickname) // 닉네임 저장
            startActivity(Intent(this, WelcomeActivity::class.java))
        }

        binding.nicknameMainCl.setOnClickListener {
            hideKeyboard()
        }

        binding.nicknameBackIv.setOnClickListener {
            backPressed()
        }
    }

    private fun changeNicknameAttr() {
        if(isValidNickname) {
            // 닉네임 중복 체크 버튼 변경
            binding.nicknameCheckIv.setImageResource(R.drawable.ic_check_enabled)

            // 다음 버튼 활성화
            binding.nicknameNextBtn.isEnabled = true
            binding.nicknameNextBtn.setTextColor(getColor(R.color.gray050))
            binding.nicknameNextBtn.setBackgroundResource(R.drawable.bg_button_selected)

            binding.nicknameCheckTv.visibility = View.GONE
            binding.nicknameEt.backgroundTintList = getColorStateList(R.color.gray900)
        } else {
            // 닉네임 전부 다 지우면 중복 확인 버튼과 다음 버튼 초기화
            binding.nicknameCheckIv.setImageResource(R.drawable.ic_check_disabled)

            binding.nicknameNextBtn.isEnabled = false
            binding.nicknameNextBtn.setTextColor(getColor(R.color.gray700))
            binding.nicknameNextBtn.setBackgroundResource(R.drawable.bg_button_default)

            binding.nicknameCheckTv.visibility = View.VISIBLE
            binding.nicknameEt.backgroundTintList = getColorStateList(R.color.error)
        }
    }

    private fun checkNicknameValid() {
        val nicknameService = NicknameService(this)
        nicknameService.checkNickname(nickname)
    }

    override fun onGetNicknameResultSuccess() {
        isValidNickname = true
        changeNicknameAttr()
    }

    override fun onGetNicknameResultFailure() {
        isValidNickname = false
        changeNicknameAttr()
    }

}