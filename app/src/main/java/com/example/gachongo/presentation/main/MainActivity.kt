package com.example.gachongo.presentation.main // ktlint-disable package-name

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.gachongo.login.KakaoLoginActivity
import com.example.gachongo.presentation.main.alarm.AlarmFragment
import com.example.gachongo.presentation.main.delivery.DeliveryFragment
import com.example.gachongo.presentation.main.home.HomeFragment
import com.example.gachongo.presentation.main.mypage.MypageFragment
import com.example.gachongo_aos.R
import com.example.gachongo_aos.databinding.ActivityMainBinding
import com.kakao.sdk.user.UserApiClient

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBnvItemSelectedListener()

        // 로그인 정보 확인
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Log.d(ContentValues.TAG, "로그인 필요")
                startActivity(Intent(this, KakaoLoginActivity::class.java))
            }
            else if (tokenInfo != null) {   // 카카오 로그인이 이미 되어있으면
                Log.d(ContentValues.TAG, "로그인 유지 성공")
            }
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
}
