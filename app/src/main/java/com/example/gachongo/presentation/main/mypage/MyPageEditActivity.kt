package com.example.gachongo.presentation.main.mypage

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.gachongo.api.NicknameEditService
import com.example.gachongo.api.NicknameEditView
import com.example.gachongo.api.ProfileEditService
import com.example.gachongo.api.ProfileEditView
import com.example.gachongo.data.Nickname
import com.example.gachongo.util.extension.backPressed
import com.example.gachongo.util.extension.hideKeyboard
import com.example.gachongo.util.getUserJwt
import com.example.gachongo.util.getUserNickname
import com.example.gachongo.util.getUserProfileImg
import com.example.gachongo.util.saveUserNickname
import com.example.gachongo_aos.databinding.ActivityMyPageEditBinding

// TODO 갤러리 접근 권한 허용받기 & 프로필 이미지 변경
class MyPageEditActivity: AppCompatActivity(), NicknameEditView, ProfileEditView{
    private lateinit var binding: ActivityMyPageEditBinding

    private var nickname: String = ""

    private lateinit var launcher: ActivityResultLauncher<Intent>
    private var image: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        binding.myPageEditNicknameEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.myPageEditNicknameCheckTv.visibility = View.GONE
                nickname = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.myPageEditProfileRiv.setOnClickListener {
            val intent = Intent().also { intent ->
                intent.type = "image/"
                intent.action = Intent.ACTION_GET_CONTENT
            }
            launcher.launch(intent)
        }

        binding.myPageEditFinishBtn.setOnClickListener {
            if(nickname == getUserNickname(this) || nickname == "") { finish() }
            else {
                editNickname()
                //editProfileImage()
            }
        }

        binding.myPageEditMainCl.setOnClickListener {
            hideKeyboard()
        }

        binding.myPageEditBackIv.setOnClickListener {
            backPressed()
        }
    }

    private fun initView() {
        binding.myPageEditNicknameEt.setText(getUserNickname(this))
        nickname = getUserNickname(this)
        Glide.with(this).load(getUserProfileImg(this)).into(binding.myPageEditProfileRiv)

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val intent = checkNotNull(result.data)
                val imageUri = intent.data
                image = imageUri.toString()

                Glide.with(this).load(imageUri).into(binding.myPageEditProfileRiv)
            }
        }
    }

    private fun editProfileImage() {
        val profileEditService = ProfileEditService(this)
        profileEditService.editProfile(getUserJwt(this), image)
    }

    override fun onGetProfileEditSuccess() {
        Log.d("프로필 변경", "성공")
    }

    override fun onGetProfileEditFailure(message: String) {
        Log.d("프로필 변경", "실패")
    }

    private fun editNickname() {
        val nicknameEditService = NicknameEditService(this)
        nicknameEditService.editNickname(getUserJwt(this), Nickname(nickname))
    }

    override fun onGetNicknameEditSuccess() {
        saveUserNickname(this, nickname)
        finish()
    }

    override fun onGetNicknameEditFailure(message: String) {
        binding.myPageEditNicknameCheckTv.visibility = View.VISIBLE
        binding.myPageEditNicknameCheckTv.text = message
    }
}