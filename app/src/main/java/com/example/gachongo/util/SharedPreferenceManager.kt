package com.example.gachongo.util

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

fun saveUserNickname(context: Context, nickname: String) {
    val spf = context.getSharedPreferences("nickname", AppCompatActivity.MODE_PRIVATE)
    val editor = spf.edit()

    editor.putString("nickname", nickname)
    editor.apply()
}

fun getUserNickname(context: Context): String {
    val spf = context.getSharedPreferences("nickname", AppCompatActivity.MODE_PRIVATE)

    return spf.getString("nickname", "")!!
}

fun saveUserProfileImg(context: Context, profileImg: String) {
    val spf = context.getSharedPreferences("profileImg", AppCompatActivity.MODE_PRIVATE)
    val editor = spf.edit()

    editor.putString("profileImg", profileImg)
    editor.apply()
}
fun getUserProfileImg(context: Context): String {
    val spf = context.getSharedPreferences("profileImg", AppCompatActivity.MODE_PRIVATE)

    return spf.getString("profileImg", "")!!
}

fun saveUserLocationCheck(context: Context, isFirstCheck: Boolean) {
    val spf = context.getSharedPreferences("isFirstCheck", AppCompatActivity.MODE_PRIVATE)
    val editor = spf.edit()

    editor.putBoolean("isFirstCheck", isFirstCheck)
    editor.apply()
}

fun getUserLocationCheck(context: Context): Boolean {
    val spf = context.getSharedPreferences("isFirstCheck", AppCompatActivity.MODE_PRIVATE)

    return spf.getBoolean("isFirstCheck", true)
}