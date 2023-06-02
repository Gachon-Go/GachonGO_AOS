package com.example.gachongo.presentation.main.mypage

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
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
import com.example.gachongo.util.saveUserProfileImg
import com.example.gachongo_aos.databinding.ActivityMyPageEditBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class MyPageEditActivity: AppCompatActivity(), NicknameEditView, ProfileEditView{
    private lateinit var binding: ActivityMyPageEditBinding

    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var profile: MultipartBody.Part

    private var nickname: String = ""
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
            else { editNickname() }

            if(image.isNullOrEmpty()) { finish() }
            else { editProfileImage(profile) }

            finish()
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

                val imagePath = result.data!!.data
                image = imagePath.toString()

                val file = File(absolutelyPath(imagePath, this))
                val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                profile = MultipartBody.Part.createFormData("image", file.name, requestFile)
                Glide.with(this).load(image).into(binding.myPageEditProfileRiv)
            }
        }
    }

    // 절대경로 변환
    private fun absolutelyPath(path: Uri?, context : Context): String {
        val proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        val c: Cursor? = context.contentResolver.query(path!!, proj, null, null, null)
        val index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        val result = c?.getString(index!!)

        return result!!
    }

    private fun editProfileImage(profile: MultipartBody.Part) {
        val profileEditService = ProfileEditService(this)
        profileEditService.editProfile(getUserJwt(this), profile)
        saveUserProfileImg(this, image)
    }

    override fun onGetProfileEditSuccess() {
        // saveUserProfileImg(this, image)
    }

    override fun onGetProfileEditFailure(message: String) {
        Log.d("GachonLog #image", "실패")
    }

    private fun editNickname() {
        val nicknameEditService = NicknameEditService(this)
        nicknameEditService.editNickname(getUserJwt(this), Nickname(nickname))
        saveUserNickname(this, nickname)
    }

    override fun onGetNicknameEditSuccess() {
        // saveUserNickname(this, nickname)
    }

    override fun onGetNicknameEditFailure(message: String) {
        binding.myPageEditNicknameCheckTv.visibility = View.VISIBLE
        binding.myPageEditNicknameCheckTv.text = message
    }
}