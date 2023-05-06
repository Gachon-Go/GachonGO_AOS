package com.example.gachongo.login

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gachongo_aos.databinding.ActivityKakaoLoginBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient

class KakaoLoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityKakaoLoginBinding

    private var profileImg: String = ""
    private var nickname: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakaoLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {    // 로그인 할 때 발생하는 에러
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        Toast.makeText(this, "앱에 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Unknown
                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else if (token != null) {
                // 로그인에 성공하면
                Log.d(TAG, "카카오톡 계정 연결 성공")

                // 사용자 정보 요청
                UserApiClient.instance.me { kakaoUser, error ->
                    if (error != null) {
                        Log.e(TAG, "사용자 정보 요청 실패", error)
                    }
                    else if (kakaoUser != null) {
                        profileImg = kakaoUser.kakaoAccount?.profile?.profileImageUrl.toString()
                        nickname = kakaoUser.kakaoAccount?.profile?.nickname.toString()

                        Log.d(TAG, "사용자 정보 요청 성공 - 프로필사진: $profileImg 닉네임: $nickname")
                    }
                }

                // 이메일 인증 창으로 넘어가기

                // 서버에 토큰값 넘겨주기

                // 서버에서 받아온 사용자 정보 저장
            }
        }

        binding.kakaoLoginIv.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }

    private fun getUserInfo() {
//        // 사용자 정보 요청
//        UserApiClient.instance.me { kakaoUser, error ->
//            if (error != null) {
//                Log.e(TAG, "사용자 정보 요청 실패", error)
//            }
//            else if (kakaoUser != null) {
//                profileImg = kakaoUser.kakaoAccount?.email.toString()
//                nickname = kakaoUser.kakaoAccount?.profile?.nickname.toString()
//
//                Log.d(TAG, "사용자 정보 요청 성공 - 프로필사진: $profileImg 닉네임: $nickname")
//            }
//        }
    }
}