package com.example.gachongo.presentation.main // ktlint-disable package-name

import android.Manifest
import android.app.ActivityManager
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.gachongo.presentation.main.login.KakaoLoginActivity
import com.example.gachongo.presentation.main.alarm.AlarmFragment
import com.example.gachongo.presentation.main.delivery.DeliveryFragment
import com.example.gachongo.presentation.main.home.HomeFragment
import com.example.gachongo.presentation.main.mypage.MypageFragment
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.ActivityMainBinding
import com.kakao.sdk.user.UserApiClient
import com.example.gachongo.api.LoginService
import com.example.gachongo.api.LoginView
import com.example.gachongo.data.Login
import com.example.gachongo.data.LoginResponseResult
import com.example.gachongo.presentation.main.delivery.Constants
import com.example.gachongo.presentation.main.delivery.LocationService
import com.example.gachongo.util.extension.showToast
import com.google.firebase.messaging.FirebaseMessaging
import com.example.gachongo.util.getUserLoginProvider
import com.example.gachongo.util.getUserToken
import com.example.gachongo.util.saveUserId
import com.example.gachongo.util.saveUserJwt

class MainActivity : AppCompatActivity(), LoginView {
    private lateinit var binding: ActivityMainBinding

    private var fcmId: String = ""
    private var provider: String = ""
    private var token: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFirebase()
        initBnvItemSelectedListener()

        // 로그인 정보 확인
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Log.d(ContentValues.TAG, "카카오 로그인 필요")
                startActivity(Intent(this, KakaoLoginActivity::class.java))
            }
            else if (tokenInfo != null) {   // 카카오 로그인이 이미 되어있으면
                Log.d(ContentValues.TAG, "카카오 로그인 유지 성공")
                login()
            }
        }

        // 백그라운드로 위치정보 전송
        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        } else {
            startLocationService()
        }

    }

    private fun initFirebase() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (!it.isSuccessful) {
                // 토큰 요청 실패
                return@addOnCompleteListener
            }
            // 토큰 요청 성공
            fcmId = it.result
        }
    }

    private fun initBnvItemSelectedListener() {
        supportFragmentManager.findFragmentById(R.id.fcv_main_container)
            ?: navigateTo<HomeFragment>()

        binding.bnvMain.setOnItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.menu_home -> navigateTo<HomeFragment>()
                R.id.menu_delivery -> navigateTo<DeliveryFragment>()
                R.id.menu_alarm -> navigateTo<AlarmFragment>()
                R.id.menu_mypage -> navigateTo<MypageFragment>()
            }
            true
        }
    }

    private inline fun <reified T : Fragment> navigateTo() {
        supportFragmentManager.commit {
            replace<T>(R.id.fcv_main_container, T::class.java.canonicalName)
        }
    }

    private fun login() {
        // 정보 불러오기
        provider = getUserLoginProvider(this)
        token = getUserToken(this)

        val loginService = LoginService(this)
        loginService.login(Login(fcmId, provider, token))
    }

    override fun onGetLoginResultSuccess(result: LoginResponseResult) {
        Log.d("서버 로그인", "성공")
        saveUserId(this, result.id)
        saveUserJwt(this, result.jwt)
    }

    override fun onGetLoginResultFailure(message: String) {
        showToast(message)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopLocationService()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationService()
            } else {
                showToast("location service: Permission denied!")
            }
        }
    }

    private fun isLocationServiceRunning(): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (LocationService::class.java.name == service.service.className) {
                if (service.foreground) {
                    return true
                }
            }
        }
        return false
    }

    private fun startLocationService() {
        if (!isLocationServiceRunning()) {
            val intent = Intent(applicationContext, LocationService::class.java)
            intent.action = Constants.ACTION_START_LOCATION_SERVICE
            startService(intent)
            Log.d("LOCATION_UPDATE", "위치정보 서비스 시작")
            showToast("Location service started")
        }
    }

    private fun stopLocationService() {
        if (isLocationServiceRunning()) {
            val intent = Intent(applicationContext, LocationService::class.java)
            intent.action = Constants.ACTION_STOP_LOCATION_SERVICE
            stopService(intent)
            showToast("Location service stopped")
            Log.d("LOCATION_UPDATE", "위치정보 서비스 종료")
        }
    }

}
