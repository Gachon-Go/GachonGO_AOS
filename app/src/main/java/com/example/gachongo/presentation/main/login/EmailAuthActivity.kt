package com.example.gachongo.presentation.main.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.gachongo.api.EmailCodeView
import com.example.gachongo.api.EmailService
import com.example.gachongo.api.EmailView
import com.example.gachongo.util.extension.backPressed
import com.example.gachongo.util.extension.hideKeyboard
import com.example.gachongo.util.extension.showToast
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.ActivityEmailAuthBinding
import saveUserEmail

class EmailAuthActivity: AppCompatActivity(), EmailView, EmailCodeView {
    private lateinit var binding: ActivityEmailAuthBinding
    private lateinit var email: String
    private lateinit var code: String

    private var validEmail: Boolean = false // 이메일 유효성에 대한 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 이메일 유효성 검사 (@gachon.ac.kr)
        binding.emailAuthEmailEt.addTextChangedListener(object: TextWatcher {
            // text가 변경된 후 호출, p0에는 변경 후의 문자열이 담겨있음
            override fun afterTextChanged(p0: Editable?) {}

            // text가 변경되기 전 호출, p0에는 변경 전 문자열이 담겨있음
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            // text가 바뀔때마다 호출
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                validEmail = p0!!.endsWith("@gachon.ac.kr") // p0에 대한 유효성 검사
                changeEmailAttr() // button 변경
                if(validEmail) email = p0.toString()
            }
        })

        binding.emailAuthAuthBtn.setOnClickListener {
            getEmailAuthCode() // 이메일 인증 번호 받기
        }

        // 이메일 인증번호 검사
        binding.emailAuthAuthNumEt.addTextChangedListener(object: TextWatcher {
            // text가 변경된 후 호출, p0에는 변경 후의 문자열이 담겨있음
            override fun afterTextChanged(p0: Editable?) {}

            // text가 변경되기 전 호출, p0에는 변경 전 문자열이 담겨있음
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            // text가 바뀔때마다 호출
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                code = p0.toString()

                // 입력하기만 해도 확인 버튼 활성화
                if (code.length == 6) {
                    binding.emailAuthAuthNumBtn.isEnabled = true
                    binding.emailAuthAuthNumBtn.setTextColor(getColor(R.color.gray050))
                    binding.emailAuthAuthNumBtn.setBackgroundResource(R.drawable.bg_button_selected)
                } else {
                    binding.emailAuthAuthNumBtn.isEnabled = false
                    binding.emailAuthAuthNumBtn.setTextColor(getColor(R.color.gray700))
                    binding.emailAuthAuthNumBtn.setBackgroundResource(R.drawable.bg_button_default)
                }
            }
        })

        binding.emailAuthAuthNumBtn.setOnClickListener {
            checkEmailCode() // 이메일 인증코드 확인
        }

        // 다음 버튼 클릭 시 다음 화면으로 이동
        binding.emailAuthNextBtn.setOnClickListener {
            saveUserEmail(this, email)
            startActivity(Intent(this, NicknameActivity::class.java))
        }

        // 전체 화면 누르면 키보드 사라짐
        binding.emailAuthMainCl.setOnClickListener {
            hideKeyboard()
        }

        binding.emailAuthBackIv.setOnClickListener {
            backPressed()
        }
    }

    private fun changeEmailAttr() {

        // 이메일 유효
        if(validEmail) {
            // 밑줄 검정 + 에러메세지 안보이게
            binding.emailAuthEmailEt.backgroundTintList = getColorStateList(R.color.gray800)
            binding.emailAuthEmailCheckTv.visibility = View.GONE

            // 이메일이 유효할때만 이메일 인증 버튼 클릭 가능
            binding.emailAuthAuthBtn.isEnabled = true

            // button 변경
            binding.emailAuthAuthBtn.setTextColor(getColor(R.color.gray050))
            binding.emailAuthAuthBtn.setBackgroundResource(R.drawable.bg_button_selected)
        }

        // 이메일 유효하지 않음
        else {
            // edittext 변경
            binding.emailAuthEmailEt.backgroundTintList = getColorStateList(R.color.error)
            binding.emailAuthEmailCheckTv.visibility = View.VISIBLE

            binding.emailAuthAuthBtn.isEnabled = false

            binding.emailAuthAuthBtn.setTextColor(getColor(R.color.gray700))
            binding.emailAuthAuthBtn.setBackgroundResource(R.drawable.bg_button_default)
        }
    }

    private fun getEmailAuthCode() {
        val emailService = EmailService(this, this)
        emailService.getEmail(email)
    }

    override fun onGetEmailResultSuccess() {
        // 이메일 인증 번호 입력칸, 버튼 보이기
        binding.emailAuthAuthNumEt.visibility = View.VISIBLE
        binding.emailAuthAuthNumBtn.visibility = View.VISIBLE

        // 이메일 인증하기 버튼은 비활성화
        binding.emailAuthAuthBtn.isEnabled = false
        binding.emailAuthAuthBtn.setTextColor(getColor(R.color.gray700))
        binding.emailAuthAuthBtn.setBackgroundResource(R.drawable.bg_button_default)
    }

    override fun onGetEmailResultFailure(message: String) {
        // 이메일 인증 번호 입력칸, 버튼 숨기기
        binding.emailAuthAuthNumEt.visibility = View.GONE
        binding.emailAuthAuthNumBtn.visibility = View.GONE
    }

    private fun checkEmailCode() {
        val emailService = EmailService(this, this)
        emailService.checkEmailCode(email, code)
    }

    override fun onGetEmailCodeResultSuccess() {
        showToast("인증이 완료되었습니다!") // 인증 완료 메세지
        hideKeyboard() // 키보드 내림

        // 다음 버튼 활성화
        binding.emailAuthNextBtn.isEnabled = true
        binding.emailAuthNextBtn.setTextColor(getColor(R.color.gray050))
        binding.emailAuthNextBtn.setBackgroundResource(R.drawable.bg_button_selected)

        // 인증 번호 확인 버튼 비활성화
        binding.emailAuthAuthNumBtn.isEnabled = false
        binding.emailAuthAuthNumBtn.setTextColor(getColor(R.color.gray700))
        binding.emailAuthAuthNumBtn.setBackgroundResource(R.drawable.bg_button_default)
    }

    override fun onGetEmailCodeResultFailure(message: String) {
        showToast(message) // 인증 실패 메세지

        binding.emailAuthNextBtn.isEnabled = false
        binding.emailAuthNextBtn.setTextColor(getColor(R.color.gray700))
        binding.emailAuthNextBtn.setBackgroundResource(R.drawable.bg_button_default)
    }


}